/**
 * Copyright (c) 2021. 北京江融信科技有限公司 版权所有
 * Created on 2021-07-06.
 */

package com.anytech.anytxn.biz.common.util.encryption;

/**
 * @Author wangshuguan
 * @Description sm4加密pinblock
 * @Date 2021-07-06
 * @Version
 */

import java.util.Arrays;

/**
 * SMS 工具类
 */
public class Sm4UtilNew {

    private static final int ENCRYPT = 1;
    private static final int DECRYPT = 0;
    private static final int ROUND = 32;
    private static final int BLOCK = 16;

    private byte[] Sbox = {(byte) 0xd6, (byte) 0x90, (byte) 0xe9, (byte) 0xfe,
            (byte) 0xcc, (byte) 0xe1, 0x3d, (byte) 0xb7, 0x16, (byte) 0xb6,
            0x14, (byte) 0xc2, 0x28, (byte) 0xfb, 0x2c, 0x05, 0x2b, 0x67,
            (byte) 0x9a, 0x76, 0x2a, (byte) 0xbe, 0x04, (byte) 0xc3,
            (byte) 0xaa, 0x44, 0x13, 0x26, 0x49, (byte) 0x86, 0x06,
            (byte) 0x99, (byte) 0x9c, 0x42, 0x50, (byte) 0xf4, (byte) 0x91,
            (byte) 0xef, (byte) 0x98, 0x7a, 0x33, 0x54, 0x0b, 0x43,
            (byte) 0xed, (byte) 0xcf, (byte) 0xac, 0x62, (byte) 0xe4,
            (byte) 0xb3, 0x1c, (byte) 0xa9, (byte) 0xc9, 0x08, (byte) 0xe8,
            (byte) 0x95, (byte) 0x80, (byte) 0xdf, (byte) 0x94, (byte) 0xfa,
            0x75, (byte) 0x8f, 0x3f, (byte) 0xa6, 0x47, 0x07, (byte) 0xa7,
            (byte) 0xfc, (byte) 0xf3, 0x73, 0x17, (byte) 0xba, (byte) 0x83,
            0x59, 0x3c, 0x19, (byte) 0xe6, (byte) 0x85, 0x4f, (byte) 0xa8,
            0x68, 0x6b, (byte) 0x81, (byte) 0xb2, 0x71, 0x64, (byte) 0xda,
            (byte) 0x8b, (byte) 0xf8, (byte) 0xeb, 0x0f, 0x4b, 0x70, 0x56,
            (byte) 0x9d, 0x35, 0x1e, 0x24, 0x0e, 0x5e, 0x63, 0x58, (byte) 0xd1,
            (byte) 0xa2, 0x25, 0x22, 0x7c, 0x3b, 0x01, 0x21, 0x78, (byte) 0x87,
            (byte) 0xd4, 0x00, 0x46, 0x57, (byte) 0x9f, (byte) 0xd3, 0x27,
            0x52, 0x4c, 0x36, 0x02, (byte) 0xe7, (byte) 0xa0, (byte) 0xc4,
            (byte) 0xc8, (byte) 0x9e, (byte) 0xea, (byte) 0xbf, (byte) 0x8a,
            (byte) 0xd2, 0x40, (byte) 0xc7, 0x38, (byte) 0xb5, (byte) 0xa3,
            (byte) 0xf7, (byte) 0xf2, (byte) 0xce, (byte) 0xf9, 0x61, 0x15,
            (byte) 0xa1, (byte) 0xe0, (byte) 0xae, 0x5d, (byte) 0xa4,
            (byte) 0x9b, 0x34, 0x1a, 0x55, (byte) 0xad, (byte) 0x93, 0x32,
            0x30, (byte) 0xf5, (byte) 0x8c, (byte) 0xb1, (byte) 0xe3, 0x1d,
            (byte) 0xf6, (byte) 0xe2, 0x2e, (byte) 0x82, 0x66, (byte) 0xca,
            0x60, (byte) 0xc0, 0x29, 0x23, (byte) 0xab, 0x0d, 0x53, 0x4e, 0x6f,
            (byte) 0xd5, (byte) 0xdb, 0x37, 0x45, (byte) 0xde, (byte) 0xfd,
            (byte) 0x8e, 0x2f, 0x03, (byte) 0xff, 0x6a, 0x72, 0x6d, 0x6c, 0x5b,
            0x51, (byte) 0x8d, 0x1b, (byte) 0xaf, (byte) 0x92, (byte) 0xbb,
            (byte) 0xdd, (byte) 0xbc, 0x7f, 0x11, (byte) 0xd9, 0x5c, 0x41,
            0x1f, 0x10, 0x5a, (byte) 0xd8, 0x0a, (byte) 0xc1, 0x31,
            (byte) 0x88, (byte) 0xa5, (byte) 0xcd, 0x7b, (byte) 0xbd, 0x2d,
            0x74, (byte) 0xd0, 0x12, (byte) 0xb8, (byte) 0xe5, (byte) 0xb4,
            (byte) 0xb0, (byte) 0x89, 0x69, (byte) 0x97, 0x4a, 0x0c,
            (byte) 0x96, 0x77, 0x7e, 0x65, (byte) 0xb9, (byte) 0xf1, 0x09,
            (byte) 0xc5, 0x6e, (byte) 0xc6, (byte) 0x84, 0x18, (byte) 0xf0,
            0x7d, (byte) 0xec, 0x3a, (byte) 0xdc, 0x4d, 0x20, 0x79,
            (byte) 0xee, 0x5f, 0x3e, (byte) 0xd7, (byte) 0xcb, 0x39, 0x48};

    private int[] CK = {0x00070e15, 0x1c232a31, 0x383f464d, 0x545b6269,
            0x70777e85, 0x8c939aa1, 0xa8afb6bd, 0xc4cbd2d9, 0xe0e7eef5,
            0xfc030a11, 0x181f262d, 0x343b4249, 0x50575e65, 0x6c737a81,
            0x888f969d, 0xa4abb2b9, 0xc0c7ced5, 0xdce3eaf1, 0xf8ff060d,
            0x141b2229, 0x30373e45, 0x4c535a61, 0x686f767d, 0x848b9299,
            0xa0a7aeb5, 0xbcc3cad1, 0xd8dfe6ed, 0xf4fb0209, 0x10171e25,
            0x2c333a41, 0x484f565d, 0x646b7279};

    private int Rotl(int x, int y) {
        return x << y | x >>> (32 - y);
    }

    private int ByteSub(int A) {
        return (Sbox[A >>> 24 & 0xFF] & 0xFF) << 24
                | (Sbox[A >>> 16 & 0xFF] & 0xFF) << 16
                | (Sbox[A >>> 8 & 0xFF] & 0xFF) << 8 | (Sbox[A & 0xFF] & 0xFF);
    }

    private int L1(int B) {
        return B ^ Rotl(B, 2) ^ Rotl(B, 10) ^ Rotl(B, 18) ^ Rotl(B, 24);
        // return B^(B<<2|B>>>30)^(B<<10|B>>>22)^(B<<18|B>>>14)^(B<<24|B>>>8);
    }

    private int L2(int B) {
        return B ^ Rotl(B, 13) ^ Rotl(B, 23);
        // return B^(B<<13|B>>>19)^(B<<23|B>>>9);
    }

    private void SMS4Crypt(byte[] Input, byte[] Output, int[] rk) {
        int r, mid, x0, x1, x2, x3;
        int[] x = new int[4];
        int[] tmp = new int[4];
        for (int i = 0; i < 4; i++) {
            tmp[0] = Input[0 + 4 * i] & 0xff;
            tmp[1] = Input[1 + 4 * i] & 0xff;
            tmp[2] = Input[2 + 4 * i] & 0xff;
            tmp[3] = Input[3 + 4 * i] & 0xff;
            x[i] = tmp[0] << 24 | tmp[1] << 16 | tmp[2] << 8 | tmp[3];
            // x[i]=(Input[0+4*i]<<24|Input[1+4*i]<<16|Input[2+4*i]<<8|Input[3+4*i]);
        }
        for (r = 0; r < 32; r += 4) {
            mid = x[1] ^ x[2] ^ x[3] ^ rk[r + 0];
            mid = ByteSub(mid);
            x[0] = x[0] ^ L1(mid); // x4

            mid = x[2] ^ x[3] ^ x[0] ^ rk[r + 1];
            mid = ByteSub(mid);
            x[1] = x[1] ^ L1(mid); // x5

            mid = x[3] ^ x[0] ^ x[1] ^ rk[r + 2];
            mid = ByteSub(mid);
            x[2] = x[2] ^ L1(mid); // x6

            mid = x[0] ^ x[1] ^ x[2] ^ rk[r + 3];
            mid = ByteSub(mid);
            x[3] = x[3] ^ L1(mid); // x7
        }

        // Reverse
        for (int j = 0; j < 16; j += 4) {
            Output[j] = (byte) (x[3 - j / 4] >>> 24 & 0xFF);
            Output[j + 1] = (byte) (x[3 - j / 4] >>> 16 & 0xFF);
            Output[j + 2] = (byte) (x[3 - j / 4] >>> 8 & 0xFF);
            Output[j + 3] = (byte) (x[3 - j / 4] & 0xFF);
        }
    }

    private void SMS4KeyExt(byte[] Key, int[] rk, int CryptFlag) {
        int r, mid;
        int[] x = new int[4];
        int[] tmp = new int[4];
        for (int i = 0; i < 4; i++) {
            tmp[0] = Key[0 + 4 * i] & 0xFF;
            tmp[1] = Key[1 + 4 * i] & 0xff;
            tmp[2] = Key[2 + 4 * i] & 0xff;
            tmp[3] = Key[3 + 4 * i] & 0xff;
            x[i] = tmp[0] << 24 | tmp[1] << 16 | tmp[2] << 8 | tmp[3];
            // x[i]=Key[0+4*i]<<24|Key[1+4*i]<<16|Key[2+4*i]<<8|Key[3+4*i];
        }
        x[0] ^= 0xa3b1bac6;
        x[1] ^= 0x56aa3350;
        x[2] ^= 0x677d9197;
        x[3] ^= 0xb27022dc;
        for (r = 0; r < 32; r += 4) {
            mid = x[1] ^ x[2] ^ x[3] ^ CK[r + 0];
            mid = ByteSub(mid);
            rk[r + 0] = x[0] ^= L2(mid); // rk0=K4

            mid = x[2] ^ x[3] ^ x[0] ^ CK[r + 1];
            mid = ByteSub(mid);
            rk[r + 1] = x[1] ^= L2(mid); // rk1=K5

            mid = x[3] ^ x[0] ^ x[1] ^ CK[r + 2];
            mid = ByteSub(mid);
            rk[r + 2] = x[2] ^= L2(mid); // rk2=K6

            mid = x[0] ^ x[1] ^ x[2] ^ CK[r + 3];
            mid = ByteSub(mid);
            rk[r + 3] = x[3] ^= L2(mid); // rk3=K7
        }

        // 解密时轮密钥使用顺序：rk31,rk30,...,rk0
        if (CryptFlag == DECRYPT) {
            for (r = 0; r < 16; r++) {
                mid = rk[r];
                rk[r] = rk[31 - r];
                rk[31 - r] = mid;
            }
        }
    }

    private int sms4(byte[] in, int inLen, byte[] key, byte[] out, int CryptFlag) {
        int point = 0;
        int[] round_key = new int[ROUND];
        // int[] round_key={0};
        SMS4KeyExt(key, round_key, CryptFlag);
        byte[] input = new byte[16];
        byte[] output = new byte[16];

        while (inLen >= BLOCK) {
            input = Arrays.copyOfRange(in, point, point + 16);
            // output=Arrays.copyOfRange(out, point, point+16);
            SMS4Crypt(input, output, round_key);
            System.arraycopy(output, 0, out, point, BLOCK);
            inLen -= BLOCK;
            point += BLOCK;
        }

        return 0;
    }

    /**
     * 加密：SMS4加密后转换为Hex
     *
     * @param plaintext 16进制明文
     * @param key       秘钥
     * @return
     */
    public static String encodeSMS4ToHex(String plaintext, byte[] key) {
        return bytesToHexString(Sm4UtilNew.encodeSMS4(plaintext, key));
    }

    /**
     * 加密 16进制明文
     * @param hexSrc 16进制待加密字符串
     * @param hexKey 16进制明文秘钥
     * @return
     */
    public static String encodeSMS4ToHex(String hexSrc, String hexKey) {
        return encodeSMS4ToHex(hexSrc, hexStringToBytes(hexKey));
    }

    /**
     * 解密：由SMS4加密转换的Hex转为字符串
     *
     * @param enHex 密文
     * @param key   秘钥
     * @return
     */
    public static String decodeSMS4HexToString(String enHex, byte[] key) {
        return Sm4UtilNew.decodeSMS4toString(hexToByteArray(enHex), key);
    }

    /**
     * 解密：由SMS4加密转换的Hex转为字符串
     *
     * @param srcHex 密文
     * @param hexKey   秘钥
     * @return
     */
    public static String decodeSMS4HexToString(String srcHex, String hexKey) {
        return decodeSMS4HexToString(srcHex, hexStringToBytes(hexKey));
    }

    /**
     * 不限明文长度的SMS4加密，加密字符串
     *
     * @param plaintext
     * @param key
     * @return
     */
    private static byte[] encodeSMS4(String plaintext, byte[] key) {
//        if (plaintext == null || plaintext.equals("")) {
//            return null;
//        }
//        for (int i = plaintext.getBytes().length % 16; i < 16; i++) {
//            plaintext += '\0';
//        }

//        return Sm4UtilNew.encodeSMS4(plaintext.getBytes(), key);
        return Sm4UtilNew.encodeSMS4(hexToByteArray(plaintext), key);
    }

    /**
     * 不限明文长度的SMS4加密，加密字符数组
     *
     * @param plaintext
     * @param key
     * @return
     */
    private static byte[] encodeSMS4(byte[] plaintext, byte[] key) {
        byte[] ciphertext = new byte[plaintext.length];

        int k = 0;
        int plainLen = plaintext.length;
        while (k + 16 <= plainLen) {
            byte[] cellPlain = new byte[16];
            for (int i = 0; i < 16; i++) {
                cellPlain[i] = plaintext[k + i];
            }
            byte[] cellCipher = encode16(cellPlain, key);
            for (int i = 0; i < cellCipher.length; i++) {
                ciphertext[k + i] = cellCipher[i];
            }

            k += 16;
        }

        return ciphertext;
    }

    /**
     * 不限明文长度的SMS4解密
     *
     * @param ciphertext
     * @param key
     * @return
     */
    private static byte[] decodeSMS4(byte[] ciphertext, byte[] key) {
        byte[] plaintext = new byte[ciphertext.length];

        int k = 0;
        int cipherLen = ciphertext.length;
        while (k + 16 <= cipherLen) {
            byte[] cellCipher = new byte[16];
            for (int i = 0; i < 16; i++) {
                cellCipher[i] = ciphertext[k + i];
            }
            byte[] cellPlain = decode16(cellCipher, key);
            for (int i = 0; i < cellPlain.length; i++) {
                plaintext[k + i] = cellPlain[i];
            }
            k += 16;
        }

        return plaintext;
    }

    /**
     * 解密，获得明文字符串
     *
     * @param ciphertext
     * @param key
     * @return
     */
    private static String decodeSMS4toString(byte[] ciphertext, byte[] key) {
        byte[] plaintext = new byte[ciphertext.length];
        plaintext = decodeSMS4(ciphertext, key);
        return bytesToHex(plaintext);
    }

    /**
     * 只加密16位明文
     *
     * @param plaintext
     * @param key
     * @return
     */
    private static byte[] encode16(byte[] plaintext, byte[] key) {
        byte[] cipher = new byte[16];
        Sm4UtilNew sm4 = new Sm4UtilNew();
        sm4.sms4(plaintext, 16, key, cipher, ENCRYPT);

        return cipher;
    }

    /**
     * 只解密16位密文
     *
     * @param ciphertext
     * @param key
     * @return
     */
    private static byte[] decode16(byte[] ciphertext, byte[] key) {
        byte[] plain = new byte[16];
        Sm4UtilNew sm4 = new Sm4UtilNew();
        sm4.sms4(ciphertext, 16, key, plain, DECRYPT);

        return plain;
    }

    /**
     * 只加密32位明文
     *
     * @param plaintext
     * @param key
     * @return
     */
    private static byte[] encode32(byte[] plaintext, byte[] key) {
        byte[] cipher = new byte[32];
        Sm4UtilNew sm4 = new Sm4UtilNew();
        sm4.sms4(plaintext, 32, key, cipher, ENCRYPT);

        return cipher;
    }

    /**
     * 只解密32位密文
     *
     * @param
     * @param key
     * @return
     */
    private static byte[] decode32(byte[] ciphertext, byte[] key) {
        byte[] plain = new byte[32];
        Sm4UtilNew sm4 = new Sm4UtilNew();
        sm4.sms4(ciphertext, 32, key, plain, DECRYPT);

        return plain;
    }

    private static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv.toUpperCase());
        }
        return stringBuilder.toString();
    }

    /**
     * Hex To byte[]
     *
     * @param hexString the hex string
     * @return byte[]
     */
    private static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    /**
     * hex字符串转byte数组
     * @param inHex 待转换的Hex字符串
     * @return  转换后的byte数组结果
     */
    public static byte[] hexToByteArray(String inHex){
        int hexlen = inHex.length();
        byte[] result;
        if (hexlen % 2 == 1){
            //奇数
            hexlen++;
            result = new byte[(hexlen/2)];
            inHex="0"+inHex;
        }else {
            //偶数
            result = new byte[(hexlen/2)];
        }
        int j=0;
        for (int i = 0; i < hexlen; i+=2){
            result[j]=hexToByte(inHex.substring(i,i+2));
            j++;
        }
        return result;
    }


    /**
     * Hex字符串转byte
     * @param inHex 待转换的Hex字符串
     * @return  转换后的byte
     */
    public static byte hexToByte(String inHex){
        return (byte)Integer.parseInt(inHex,16);
    }


    /**
     * 字节数组转16进制
     * @param bytes 需要转换的byte数组
     * @return  转换后的Hex字符串
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if(hex.length() < 2){
                sb.append(0);
            }
            sb.append(hex);
        }
        return sb.toString();
    }


    /**
     * 字节转十六进制
     * @param b 需要进行转换的byte字节
     * @return  转换后的Hex字符串
     */
    public static String byteToHex(byte b){
        String hex = Integer.toHexString(b & 0xFF);
        if(hex.length() < 2){
            hex = "0" + hex;
        }
        return hex;
    }

    /**
     * Convert char to byte
     *
     * @param c char
     * @return byte
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static void main(String[] args) {
        String pinBlock = "06123456ffffffffffffe6caefeffae8";
        String key = "0123456789ABCDEFFEDCBA9876543210";

        String enStr = encodeSMS4ToHex(pinBlock, key);
        System.out.println("加密后：" + enStr);

        String deStr = decodeSMS4HexToString(enStr, key);
        System.out.println("解密后：" + deStr);
    }
}