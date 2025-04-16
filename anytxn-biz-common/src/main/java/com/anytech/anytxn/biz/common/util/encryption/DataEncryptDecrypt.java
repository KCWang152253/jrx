/**
 * Copyright (c) 2020. 北京江融信科技有限公司 版权所有
 * Created on 2020-09-14.
 */

package com.anytech.anytxn.biz.common.util.encryption;

import com.anytech.anytxn.biz.common.annotation.EncryptField;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * @Author wangshuguan
 * @Description
 * @Date 2020-09-14
 * @Version
 */
@Slf4j
@Component
public class DataEncryptDecrypt {

    /**
     * sm4key 加密key
     */
    private static String sm4key;



    @Value("${anytxn.encrypt-smkey:95BA76CF3B8D7CCC84329EAE48677BDB}")
    public static void setSm4key(String sm4key) {
        DataEncryptDecrypt.sm4key = sm4key;
    }



    public static   <T> T encrypt(T t) {
        Field[] declaredFields = t.getClass().getDeclaredFields();
        try {
            if (declaredFields != null && declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    if (field.isAnnotationPresent(EncryptField.class) && field.getType().toString().endsWith("String")) {
                        field.setAccessible(true);
                        String fieldValue = (String) field.get(t);
                        if (StringUtils.isNotEmpty(fieldValue)) {
                            field.set(t, Sm4EncryptUtils.encryptEcb(sm4key,fieldValue));
                        }
                        field.setAccessible(false);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return t;
    }


    public static <T> T decrypt(T result) throws IllegalAccessException {
        Field[] declaredFields = result.getClass().getDeclaredFields();
        try {
            if (declaredFields != null && declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    if (field.isAnnotationPresent(EncryptField.class) && field.getType().toString().endsWith("String")) {
                        field.setAccessible(true);
                        String fieldValue = (String)field.get(result);
                        if(StringUtils.isNotEmpty(fieldValue)) {
                            field.set(result, Sm4EncryptUtils.decryptEcb(sm4key, fieldValue));
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return result;

    }
}
