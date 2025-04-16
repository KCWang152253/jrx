package com.anytech.anytxn.biz.common.interceptor;///**
// * Copyright (c) 2020. 北京江融信科技有限公司 版权所有
// * Created on 2020-09-13.
// */
//
//package jrx.anytxn.biz.common.interceptor;
//
//import jrx.anytxn.biz.common.annotation.EncryptModel;
//import jrx.anytxn.biz.common.util.encryption.IDataEncryptDecrypt;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.ibatis.executor.resultset.ResultSetHandler;
//import org.apache.ibatis.plugin.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.core.annotation.AnnotationUtils;
//import org.springframework.stereotype.Component;
//import org.springframework.util.CollectionUtils;
//
//import java.beans.Statement;
//import java.util.ArrayList;
//import java.util.Objects;
//import java.util.Properties;
//
///**
// * @Author wangshuguan
// * @Description sql结果加解密处理
// * @Date 2020-09-13
// * @Version
// */
//@Intercepts({@Signature(type = ResultSetHandler.class, method = "handleResultSets", args={Statement.class})
//})
//@ConditionalOnProperty(value = "anytxn.decrypt", havingValue = "true")
//@Component
//@Slf4j
//public class EncryptResultInterceptor implements Interceptor {
//    @Autowired
//    private IDataEncryptDecrypt encryptDecrypt;
//
//    @Override
//    public Object intercept(Invocation invocation) throws Throwable {
//        Object result = invocation.proceed();
//        if (Objects.isNull(result)){
//            return null;
//        }
//
//        if (result instanceof ArrayList) {
//            ArrayList resultList = (ArrayList) result;
//            if (!CollectionUtils.isEmpty(resultList) && needToDecrypt(resultList.get(0))){
//                for (int i = 0; i < resultList.size(); i++) {
//                    encryptDecrypt.decrypt(resultList.get(i));
//                }
//            }
//        }else {
//            if (needToDecrypt(result)){
//                encryptDecrypt.decrypt(result);
//            }
//        }
//        return result;
//    }
//
//    public boolean needToDecrypt(Object object){
//        Class<?> objectClass = object.getClass();
//        EncryptModel encryptDecryptClass = AnnotationUtils.findAnnotation(objectClass, EncryptModel.class);
//        if (Objects.nonNull(encryptDecryptClass)){
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public Object plugin(Object target) {
//        return Plugin.wrap(target, this);
//    }
//
//    @Override
//    public void setProperties(Properties properties) {
//
//    }
//}
