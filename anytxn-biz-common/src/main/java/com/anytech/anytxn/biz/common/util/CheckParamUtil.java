package com.anytech.anytxn.biz.common.util;

import com.anytech.anytxn.biz.common.constant.GlobalConstants;
import com.anytech.anytxn.biz.common.enums.IdType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.Random;

@Slf4j
public class CheckParamUtil {

    /**
     * 计算数字各位和
     *
     * @param number
     * @return
     */
    public static int calculate(int number) {
        String str = String.valueOf(number);
        int total = 0;
        for (int i = 0; i < str.length(); i++) {
            total += Integer.valueOf(Integer.parseInt(String.valueOf(str.charAt(i))));
        }
        return total;
    }

    /**
     * 大莱校验卡号
     * @param  cardNumber 卡号
     * @return true:校验通过  false:校验不通过
     */
    public static boolean checkCardNum(String cardNumber) {
        if (StringUtils.isBlank(cardNumber)) {
            log.info("卡号校验失败！卡号为空");
            return false;
        }
        if (cardNumber.length() != 14 && cardNumber.length() != 16) {
            log.info("卡号校验失败！卡号长度非14或16位");
            return false;
        }
        if (cardNumber.length() == 16) {
            //如果是16位的卡号，采用16位的校验规则
            return checkCardNum16(cardNumber);
        }
        /*if (!"36".equals(cardNumber.substring(0, 2))){
            log.info("卡号校验失败！卡号非36开头");
            return false;
        }*/
        int totalNumber = 0;
        int oneNum = Integer.parseInt(String.valueOf(cardNumber.charAt(10)));
        for (int i = 2; i < 10; i++) {
            int tmpNumber = Integer.parseInt(String.valueOf(cardNumber.charAt(i)));
            if (i % 2 == 0) {
                totalNumber += calculate(tmpNumber);
            } else {
                totalNumber += calculate(2 * tmpNumber);
            }
        }
        if ((oneNum - totalNumber % 10) == 0 || oneNum == (10 - totalNumber % 10)) {
            totalNumber = 0;
            int twoNum = Integer.parseInt(cardNumber.substring(cardNumber.length() - 1));
            for (int j = 0; j < cardNumber.length() - 1; j++) {
                int tmpNumber = Integer.parseInt(String.valueOf(cardNumber.charAt(j)));
                if (j % 2 == 0) {
                    totalNumber += calculate(2 * tmpNumber);
                } else {
                    totalNumber += calculate(tmpNumber);
                }
            }
            if ((twoNum - totalNumber % 10) == 0 || twoNum == (10 - totalNumber % 10))
                return true;
        }
        log.info("卡号校验失败！非法卡号,校验不通过");
        return false;
    }

    private static boolean checkCardNum16(String cardNumber) {
        int checkNumber = getCheckNumber16(cardNumber.substring(0, cardNumber.length() - 1));
        return checkNumber == Integer.parseInt(cardNumber.substring(cardNumber.length() - 1));
    }

    /**
     * 大莱新加坡证件号校验
     * <pre>
     * I类型(10位即起始字符+7位数字+/字符+校验字符)类似本国身份证国籍需是新加坡包含S开头(2000年之前出生)和T开头的(2000年及之后出生的且证件号2、3位对应出生年份3、4位)
     *   S开头数字位解释如下几种情况:
     *      前2位是出生年份YY或20-29:NRIC出生年份>=1968
     *      第1位是0或1:NRIC出生年份<=1967
     *      第1位是2或3:出生年份<=1967的非本地新加坡人
     *      第1位是4或5:注册日期>2008非本地新加坡人
     * E类型(9位即起始字符+7位数字+校验字符)类似绿卡包含F开头(2000年之前注册)和G开头的(2000年及之后注册)
     * I、E类型校验位按照7位数字权重进行求和取模11获取映射值对应字符(S、F开头如下,T、G开头和值+4再计算获值)
     * I类型取模对应校验位字符   J Z I H G F E D C B A       7位数字  d1 d2 d3 d4 d5 d6 d7
     * E类型取模对应校验位字符   X W U T R Q P N M L K       权重     2  7  6  5  4  3  2
     * 模值                  0 1 2 3 4 5 6 7 8 9 10
     * 计算示例:7位数字1234567  x = 2*1 + 7*2 + 6*3 + 5*4 + 4*5 + 3*6 + 2*7 = 106 x/11 = 9余7
     *                       y = x + 4 = 110 y/11 = 0余0
     *                       则I类型S开头取字符D,T开头取字符J;E类型F开头取字符N,G开头取字符X;
     * 其他类型不进行校验,返回true
     * </pre>
     * @param  identityCard 证件号
     * @param  type 证件类型
     * @param  birthDate 生日
     * @param  country 国籍
     * @param  isNative 是否本地居民
     * @param  startDate 注册时间
     * @return true:校验通过  false:校验不通过
     */
    public static boolean checkIdentityCard(String identityCard, String type, String birthDate, String country, String isNative, String startDate) {

        if (StringUtils.isBlank(identityCard)) {
            log.info("身份证校验失败！身份证号为空");
            return false;
        }
        String star = identityCard.substring(0, 1);
        try {
            if (StringUtils.isNotBlank(type)) {
                IdType Type = IdType.getIdType(type);
                switch (Type) {
                    case ID_CARD:
                        if (!(identityCard.length()==10 && String.valueOf(identityCard.charAt(8)).equals("/"))) {
                            log.info("身份证校验失败！选择证件类型为I时:最后一位校验位前须有/字符并且长度为10位");
                            return false;
                        }
                        identityCard = identityCard.replace("/", "");
                        if(StringUtils.isNotBlank(birthDate)){
                            int birthYear = Integer.parseInt(birthDate.substring(0, 4));
                            //I国家注册身份证
                            if (birthYear < 2000 && !"S".equals(star) || birthYear >= 2000 && !"T".equals(star)) {
                                log.info("身份证校验失败！选择证件类型为I时:出生年份<2000的是S开头或>=2000的是T开头");
                                return false;
                            }
                            int years = Integer.parseInt(String.valueOf(birthYear).substring(2, 4));
                            int age = Integer.parseInt(identityCard.substring(1, 3));
                            if ("T".equals(star) && age != years) {
                                log.info("身份证校验失败！选择证件类型为I时:出生年份(如2012)后两位需要与身份证2、3位对应");
                                return false;
                            }
                            if ("S".equals(star)) {
                                if (birthYear >= 1968) {
                                    if (!(age == years || (age >= 20 && age <= 29))) {
                                        log.info("身份证校验失败！选择证件类型为I时:出生年份(如1978)后两位需要与身份证2、3位对应或者身份证2、3位处于20-29");
                                        return false;
                                    }
                                } else {
                                    /*if (StringUtils.isBlank(isNative)) {
                                        log.info("身份证校验失败！选择证件类型为I时:是否本地居民下拉框选择为空");
                                        return false;
                                    }*/
                                    String sternum = identityCard.substring(1, 2);
                                        if (StringUtils.isNotBlank(startDate)) {
                                            int startTime = Integer.parseInt(startDate.substring(0, 4));
                                            if (startTime > 2008) {
                                                if (!"45".contains(sternum)) {
                                                    log.info("身份证校验失败！选择证件类型为I时:对于出生年份<1968、身份证有效起始时间>2008的并且选择非本地居民后身份证第2位需要是4或5");
                                                    return false;
                                                }
                                            } else {
                                                if (!"23".contains(sternum) && !"01".contains(sternum)) {
                                                    log.info("身份证校验失败！选择证件类型为I时:对于出生年份<1968、身份证有效起始时间<=2008的并且选择非本地居民后身份证第2位需要是0、1或2、3");
                                                    return false;
                                                }
                                            }
                                        }
                                }
                            }
                            if (StringUtils.isBlank(country) || !"JZIHGFEDCBA".contains(identityCard.substring(identityCard.length() - 1))) {
                                log.info("身份证校验失败！选择证件类型为I时:国籍为空或非新加坡或者身份证的最后一位校验位值错误(非JZIHGFEDCBA之一)");
                                return false;
                            }
                        }else{
                            //并非仅校验证件号的校验位合法性
                            if (StringUtils.isNotBlank(country)){
                                return false;
                            }
                        }
                        break;
                    case FOREIGNER_RESIDENTS_PERMIT:
                        //E电子就业证/工作许可证
                        if (StringUtils.isNotBlank(startDate)) {
                            int startTime = Integer.parseInt(startDate.substring(0, 4));
                            if (startTime < 2000 && !"F".equals(star) || startTime >= 2000 && !"G".equals(star)) {
                                log.info("身份证校验失败！选择证件类型为E时:注册年份<2000的是F开头或>=2000的是G开头");
                                return false;
                            }
                        }else{
                            if(!"FG".contains(star)){
                                log.info("身份证校验失败！选择证件类型为E时:应该以F或G开头");
                                return false;
                            }
                        }

                        if (!"XWUTRQPNMLK".contains(identityCard.substring(identityCard.length() - 1))) {
                            log.info("身份证校验失败！选择证件类型为E时:身份证的最后一位校验位值错误(非XWUTRQPNMLK之一)");
                            return false;
                        }
                        break;
                    default:
                        //非I非E
                        return true;
                }
            }else{
                log.info("身份证校验失败！证件类型为空");
                return false;
            }
            if (identityCard.length() != 9) {
                log.info("身份证校验失败！身份证位数非9位");
                return false;
            }
            int totalNumber = 2 * Integer.parseInt(String.valueOf(identityCard.charAt(1)));
            ;
            for (int i = 2; i < identityCard.length() - 1; i++) {
                int tmpNumber = Integer.parseInt(String.valueOf(identityCard.charAt(identityCard.length() - i)));
                totalNumber += i * tmpNumber;
            }
            if ("T".equals(star) || "G".equals(star)) {
                totalNumber += 4;
            }
            String endStr = identityCard.substring(identityCard.length() - 1);
            int index = "JZIHGFEDCBAXWUTRQPNMLK".indexOf(endStr);
            if (index % 11 == totalNumber % 11)
                return true;
            log.info("身份证校验失败！该身份证号非法,校验不通过");
            return false;
        } catch (Exception e) {
            log.error("身份证校验参数异常!", e);
            //e.printStackTrace();
            return false;
        }
    }
    /**
     * 大莱新加坡证件号校验(仅校验证件号的校验位合法性)
     * @param  identityCard 证件号
     * @param  type 证件类型
     * @return true:校验通过  false:校验不通过
     */
    public static boolean checkIdentityCard(String identityCard, String type) {
        return  checkIdentityCard(identityCard,type,null,null,null,null);
    }
    /**
     * 生成测试用的卡号
     * @return 随机生成的测试用新加坡身份证号
     */
    public static String getSgCardNumber() {
        Random ran = new Random();
        String number = "36" + String.format("%010d", ran.nextInt(1000000000));
        return generateCardNumber(number);
    }

    /**
     * 生成测试用的新加坡身份证号
     * @param StarChar  起始字符  S、T、F、G
     * @return 随机生成的测试用新加坡身份证号
     */
    public static String getSgIdCardNumber(String StarChar) {
        String idcardnumber = "";
        if (StringUtils.isEmpty(StarChar) || StarChar.length() != 1 || !"STFG".contains(StarChar.toUpperCase())) {
            StarChar = "T";
        }
        StarChar = StarChar.toUpperCase();
        Random ran = new Random();
        String number = String.format("%07d", ran.nextInt(9999999));
        int totalNumber = 0;
        if ("T".equals(StarChar) || "G".equals(StarChar)) {
            number = String.format("%02d", ran.nextInt((LocalDate.now().getYear() - 15) % 100)) + number.substring(2);
            totalNumber += 4;
        }
        totalNumber += 2 * Integer.parseInt(String.valueOf(number.charAt(0)));
        ;
        for (int i = 2; i < 8; i++) {
            int tmpNumber = Integer.parseInt(String.valueOf(number.charAt(8 - i)));
            totalNumber += i * tmpNumber;
        }
        switch (StarChar) {
            case "S":
            case "T":
                idcardnumber = StarChar + number + "/" + "JZIHGFEDCBA".charAt(totalNumber % 11);
                break;
            case "F":
            case "G":
                idcardnumber = StarChar + number + "XWUTRQPNMLK".charAt(totalNumber % 11);
                break;
            default:
                break;
        }
        return idcardnumber;
    }

    /**
     * 根据卡号池编号补充校验位生成卡号(大莱新加坡卡号前两位默认是36)
     * <pre>
     * 卡号由14位数字组成,其中第11、14位为校验位,生成卡号时提供12位数字序列参数,根据规则补充对应位校验数生成卡号,规则如下
     *    12位序列号中的前10位对应卡号的前10位,第11、12位对应卡号的12、13位,根据规则生成11、14校验位数组成14位卡号
     *    1st Round                S  D  S  D  S  D  S  D 1st      2st
     *    卡号                3  6  1  3  3  0  8  9  6  2  3  1  9  0
     *    2st Round          D  S  D  S  D  S  D  S  D  S  D  S  D
     *    1st 校验位根据第3-10列,S列权重1,D列权重2,各列按权重算乘积然后对值按位求和,再对各列计算值进行求和,计算其与取十位向上取整差值的绝对值
     *        val = 1*1 + 3*2 + 3*1 + 0*2 + 8*1 + 9*2 + 6*1 + 2*2
     *            =  1  +  6  +  3  +  0  +  8  +(1+8)+  6  +  4
     *            = 40 - 37
     *            = 3
     *    2st 校验位根据第1-13列,S列权重1,D列权重2,各列按权重算乘积然后对值按位求和,再对各列计算值进行求和,计算其与取十位向上取整差值的绝对值
     *        val = 3*2 + 6*1 + 1*2 + 3*1 + 3*2 + 0*1 + 8*2 + 9*1 + 6*2 + 2*1 + 3*2 + 1*1 + 9*2
     *            =  6  +  6  +  2  +  3  +  6  +  0  +(1+6)+  9  +(1+2)+  2  +  6  +  1  +(1+8)
     *            = 60 - 60
     *            = 0
     * </pre>
     * @param serial 12位序列号
     * @return 14位卡号
     */
    public static String generateCardNumber(String serial) {
        String number = serial.substring(0, 10);
        int totalNumber = 0;
        //第一个校验位计算是根据序列号的3-10位数字中奇数位+偶数位*2求和,例如和值是37则取40-37=3即校验位值为3
        for (int i = 2; i < number.length(); i++) {
            int tmpNumber = Integer.parseInt(String.valueOf(number.charAt(i)));
            if (i % 2 == 0) {
                totalNumber += calculate(tmpNumber);
            } else {
                totalNumber += calculate(2 * tmpNumber);
            }
        }
        //第一位校验位值
        int oneNum = (10 - totalNumber%10) % 10;
        number += oneNum + serial.substring(10, 12);
        totalNumber = 0;
        //第二位校验位是根据卡号的1-13位数字中奇数位*2+偶数位求和,例如和值是60则取60-60=0即校验位值为0
        for (int j = 0; j < number.length(); j++) {
            int tmpNumber = Integer.parseInt(String.valueOf(number.charAt(j)));
            if (j % 2 == 0) {
                totalNumber += calculate(2 * tmpNumber);
            } else {
                totalNumber += calculate(tmpNumber);
            }
        }
        //第二位校验位值
        int twoNum = (10 - totalNumber%10) % 10;
        return number + twoNum;
    }

    /**
     * Luhn算法
     * 根据卡号获取校验位
     *
     * @param cardNumber kahao
     * @return int
     */
    public static int getCheckNumber16(String cardNumber) {
        int totalNumber = 0;
        int step2 = 2 , step9 = 9;
        for (int i = cardNumber.length() - 1; i >= 0; i -= step2) {
            int tmpNumber = calculate(Integer.parseInt(String.valueOf(cardNumber.charAt(i))) * step2);
            if (i == 0) {
                totalNumber += tmpNumber;
            } else {
                totalNumber += tmpNumber + Integer.parseInt(String.valueOf(cardNumber.charAt(i - 1)));
            }
        }
        if (totalNumber >= 0 && totalNumber < step9) {
            return (10 - totalNumber);
        } else {
            String str = String.valueOf(totalNumber);
            String zero = "0";
            if (StringUtils.endsWith(str, zero)){
                return 0;
            } else {
                return (10 - Integer.parseInt(String.valueOf(str.charAt(str.length() - 1))));
            }
        }
    }

    public static String generateCardNumber16(String serial) {
        return serial + getCheckNumber16(serial);
    }
}
