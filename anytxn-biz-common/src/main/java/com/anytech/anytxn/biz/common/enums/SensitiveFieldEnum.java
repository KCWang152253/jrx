/**
 * Copyright (c) 2020. 北京江融信科技有限公司 版权所有
 * Created on 2020-09-12.
 */
package com.anytech.anytxn.biz.common.enums;

import com.anytech.anytxn.biz.common.util.DesensitizedUtils;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * @Author wangshuguan
 * @Description 脱敏字段枚举
 * @Date 2020-09-12
 * @Version
 */
@Getter
public enum SensitiveFieldEnum {
    /**
     * 中文名
     */
    CHINESE_NAME,
    /**
     * 身份证号
     */
    ID_CARD,
    /**
     * 座机号
     */
    FIXED_PHONE,
    /**
     * 手机号
     */
    MOBILE_PHONE(Arrays.asList("phoneNumber","phone")){
        @Override
        public String desensitized(String value) {
            return DesensitizedUtils.mobilePhone(value);
        }
    },
    /**
     * 电子邮件
     */
    EMAIL,
    /**
     * 银行卡
     */
    BANK_CARD(Arrays.asList("cardNumber", "card")){
        @Override
        public String desensitized(String value) {
            return DesensitizedUtils.bankCard(value);
        }
    },

    /**
     * 密码
     */
    PASSWORD,

    /**
     * 消息域
     */
    MSG_FIELD(Arrays.asList("f035", "f045", "f052")){
        @Override
        public String desensitized(String value) {
            return DesensitizedUtils.messageField(value);
        }
    }

    ;



    private List<String> mapKeys;


    SensitiveFieldEnum(List<String> mapKeys) {
        this.mapKeys = mapKeys;
    }

    SensitiveFieldEnum() {
    }


    public String desensitized(String value){
        return "";
    }


    public static String desensitized(String key, String value){
        SensitiveFieldEnum sensitiveFieldEnum = Arrays.stream(SensitiveFieldEnum.values())
                .filter(t -> t.getMapKeys() != null &&  t.getMapKeys().contains(String.valueOf(key)))
                .findFirst().orElse(null);

        if (sensitiveFieldEnum == null){
            return value;
        }

        return sensitiveFieldEnum.desensitized(value);

    }


}
