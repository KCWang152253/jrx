/**
 * Copyright (c) 2020. 北京江融信科技有限公司 版权所有
 * Created on 2020-09-12.
 */

package com.anytech.anytxn.biz.common.handler;

import com.alibaba.fastjson.JSON;
import com.anytech.anytxn.biz.common.util.DesensitizedUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author wangshuguan
 * @Description 脱敏调用入口
 * @Date 2020-09-12
 * @Version
 */
@Component
public class DesensitizedHandler {

    /**
     * isDesensitized true--脱敏  false-不脱敏
     * 默认不脱敏
     */
    @Value("${anytxn.desensitized:false}")
    private boolean isDesensitized;

    /**
     * 对象脱敏
     * @param object
     * @return
     */
    public String getObjectDesensitizedValue(Object object) {
        if (isDesensitized) {
            return DesensitizedUtils.getJson(object);
        } else {
            return JSON.toJSONString(object);
        }
    }
    /**
     * 卡号单独脱敏
     * @param cardNbr
     * @return
     */
    public String getCardDesensitizedValue(String cardNbr) {
        if (isDesensitized) {
            return DesensitizedUtils.bankCard(cardNbr);
        } else {
            return cardNbr;
        }
    }

    /**
     * 证件号码脱敏
     * @param idNbr
     * @return
     */
    public String getIdNbrDesensitizedValue(String idNbr) {
        if (isDesensitized) {
            return DesensitizedUtils.idCardNum(idNbr);
        } else {
            return idNbr;
        }
    }

    /**
     * 手机号码脱敏
     * @param phoneNbr
     * @return
     */
    public String getPhoneNbrDesensitizedValue(String phoneNbr) {
        if (isDesensitized) {
            return DesensitizedUtils.mobilePhone(phoneNbr);
        } else {
            return phoneNbr;
        }
    }

}
