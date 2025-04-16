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
//import org.apache.ibatis.executor.parameter.ParameterHandler;
//import org.apache.ibatis.plugin.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.core.annotation.AnnotationUtils;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.Field;
//import java.sql.PreparedStatement;
//import java.util.Objects;
//import java.util.Properties;
//
///**
// * @Author wangshuguan
// * @Description sql参数加解密处理
// * @Date 2020-09-13
// * @Version
// */
//@Intercepts({@Signature(type = ParameterHandler.class, method = "setParameters", args = PreparedStatement.class),
//})
//@ConditionalOnProperty(value = "anytxn.encrypt", havingValue = "true")
//@Component
//@Slf4j
//public class EncryptParamInterceptor implements Interceptor {
//    @Autowired
//    private IDataEncryptDecrypt encryptDecrypt;
//
//    @Override
//    public Object intercept(Invocation invocation) throws Throwable {
//
//        log.info("拦截器ParamInterceptor");
//        //拦截 ParameterHandler 的 setParameters 方法 动态设置参数
//        if (invocation.getTarget() instanceof ParameterHandler) {
//            ParameterHandler parameterHandler = (ParameterHandler) invocation.getTarget();
//            PreparedStatement ps = (PreparedStatement) invocation.getArgs()[0];
//
//            // 反射获取 BoundSql 对象，此对象包含生成的sql和sql的参数map映射
//			/*Field boundSqlField = parameterHandler.getClass().getDeclaredField("boundSql");
//			boundSqlField.setAccessible(true);
//			BoundSql boundSql = (BoundSql) boundSqlField.get(parameterHandler);*/
//
//            // 反射获取 参数对像
//            Field parameterField =
//                    parameterHandler.getClass().getDeclaredField("parameterObject");
//            parameterField.setAccessible(true);
//            Object parameterObject = parameterField.get(parameterHandler);
//            if (Objects.nonNull(parameterObject)){
//                Class<?> parameterObjectClass = parameterObject.getClass();
//                EncryptModel encryptDecryptClass = AnnotationUtils.findAnnotation(parameterObjectClass, EncryptModel.class);
//                if (Objects.nonNull(encryptDecryptClass)){
//                    final Object encrypt = encryptDecrypt.encrypt(parameterObject);
//                }
//            }
//        }
//        return invocation.proceed();
//    }
//
//    @Override
//    public Object plugin(Object o) {
//        return Plugin.wrap(o, this);
//    }
//
//    @Override
//    public void setProperties(Properties properties) {
//
//    }
//}
