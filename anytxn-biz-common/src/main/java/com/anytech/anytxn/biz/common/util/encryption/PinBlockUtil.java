/**
 * Copyright (c) 2021. 北京江融信科技有限公司 版权所有
 * Created on 2021-07-05.
 */

package com.anytech.anytxn.biz.common.util.encryption;

import com.anytech.anytxn.biz.common.enums.EncryModeEnum;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

/**
 * @Author wangshuguan
 * @Description 计算银联明文pinblock
 * @Date 2021-07-05
 * @Version
 */
public class PinBlockUtil {
    private static final int PIN_MIN_LENGTH = 4;
    private static final int PIN_MAX_LENGTH = 12;
    private static final int HEXADECIMAL = 16;
    /**
     * DES/3SDE算法PIN BLOCK大小为8
     * SM4算法PIN BLOCK大小为16
     */
    private static final int PIN_BLOCK_SIZE = 16;

    /**
     * 生成 PIN Block
     * @param pin
     * @return
     */
    private static byte[] pinBlock(String pin) {
        int pinLength = pin.length();
        if (pinLength < PIN_MIN_LENGTH || pinLength > PIN_MAX_LENGTH) {
            throw new IllegalArgumentException("Pin should be 4 to 12 digits long");
        }

        /**
         * PIN 格式 16BYTE 大小
         * 1BYTE PIN长度 + 15BYTE Pin
         * Pin不足部分右补F
         */
        byte[] pinBlock = new byte[PIN_BLOCK_SIZE];
        //如果是奇数右补F转换成如果不足12位右补F,补到12位长度
        pin = StringUtils.rightPad(pin,PIN_MAX_LENGTH,'F');
        //先处理PIN BLOCK 长度
        pinBlock[0] = (byte) pinLength;
        //处理PIN
        for (int i = 0; i <= pin.length() - 1; i = i + 2) {
            int pinBlockIndex = (i / 2) + 1;
            pinBlock[pinBlockIndex] = (byte) Integer.parseInt(pin.substring(i, i + 2), HEXADECIMAL);
        }

        /**
         * 不足32位补,需要补的位长度为PinBlock减去PIN长度域的长度和PIN数据长度
         */

        int blockIndex = (pin.length() / 2) + 1;
        for (int i = blockIndex; i <= PIN_BLOCK_SIZE - 1; i++) {
            pinBlock[i] = (byte) 0xFF;
        }
        return pinBlock;
    }

    /**
     * 格式
     * 2BYTE %H0000
     * 14BYTE 取主账号的右12位（不包括最右边的校验位）,主账号不足12位左补0
     *
     * @param mAccNo 主账号
     * @return
     */
    private static byte[] pan(String mAccNo) {
        /**
         * 不足12位左补0,后面要取右12位,不包括校验位,全长是不足13位的就需要补
         */
        StringUtils.leftPad(mAccNo, 13, '0');
        byte[] panByte = new byte[PIN_BLOCK_SIZE];
        //截取右12位,包括校验位是13位
        String pan = StringUtils.left(StringUtils.right(mAccNo, 12 + 1), 12);
        /**
         * 2BYTE %H0000
         * 14BYTE 取主账号的右12位（不包括最右边的校验位）,主账号不足12位左补0
         */
        //初始化左边为0区域
        int leftSize = PIN_BLOCK_SIZE - 12 / 2;
        for (int i = 0; i <= leftSize - 1; i++) {
            panByte[i] = (byte) 0x00;
        }
        int panIndex = 0;
        for (int i = leftSize; i <= PIN_BLOCK_SIZE - 1; i++, panIndex+=2) {
            String a = pan.substring(panIndex, panIndex + 2);
            panByte[i] = (byte) Integer.parseInt(a, HEXADECIMAL);
        }
        return panByte;
    }

    /**
     * @param pin    Personal Identification Number
     * @param mAccNo Main account number
     * @return
     */
    private static byte[] pinBlock(String pin, String mAccNo) {
        byte[] pingBlock = pinBlock(pin);
        byte[] pan = pan(mAccNo);
        byte[] tByte = new byte[PIN_BLOCK_SIZE];
        for (int i = 0; i < PIN_BLOCK_SIZE; i++) {
            tByte[i] = (byte) (pingBlock[i] ^ pan[i]);
        }
        return tByte;
    }

    /**
     * zpk加密的pinBlock
     * @param pin Personal Identification Number
     * @param mAccNo Main account number
     * @param zpkKey zpk明文密钥
     * @param encryMode 加密方式
     * @return zpk加密的pinBlock
     */
    public static String pinBlockEncryByZpk(String pin, String mAccNo, String zpkKey, EncryModeEnum encryMode) {
        byte[] pingBlock = pinBlock(pin);
        byte[] pan = pan(mAccNo);
        byte[] tByte = new byte[PIN_BLOCK_SIZE];
        for (int i = 0; i < PIN_BLOCK_SIZE; i++) {
            tByte[i] = (byte) (pingBlock[i] ^ pan[i]);
        }
        String pinBlockByte = ByteUtils.toHexString(tByte);
        if(encryMode.equals(EncryModeEnum.SM4)){
            return Sm4UtilNew.encodeSMS4ToHex(pinBlockByte, zpkKey);
        }else if(encryMode.equals(EncryModeEnum.DES)){
            //todo 待开发
            return pin;
        }
        return null;
    }



    public static void main(String[] args) {
        String pin = "123456";
        String accNo = "6251935101005172";
        byte[] pinb = pinBlock(pin, accNo);
        System.out.println(ByteUtils.toHexString(pinb));
        String key = "0123456789ABCDEFFEDCBA9876543210";
        String pinBlockEncryByZpk = pinBlockEncryByZpk(pin, accNo, key, EncryModeEnum.DES);
        System.out.println(pinBlockEncryByZpk);
    }
}
