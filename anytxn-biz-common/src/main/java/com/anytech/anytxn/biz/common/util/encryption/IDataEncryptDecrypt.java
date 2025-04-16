/**
 * Copyright (c) 2020. 北京江融信科技有限公司 版权所有
 * Created on 2020-09-13.
 */

package com.anytech.anytxn.biz.common.util.encryption;

import org.springframework.stereotype.Component;

/**
 * @Author wangshuguan
 * @Description 加解密处理
 * @Date 2020-09-13
 * @Version
 */
@Component
public interface IDataEncryptDecrypt {

    /**
     * 加密方法
     * @param result Mybatis入参
     * @param <T>
     * @return
     */
    public <T> T encrypt(T result);


    /**
     * 解密方法
     * @param result Mybatis 返回值，需要判断是否是ArrayList类型
     * @param <T>
     * @return
     */
    public <T> T decrypt(T result) throws IllegalAccessException;
}
