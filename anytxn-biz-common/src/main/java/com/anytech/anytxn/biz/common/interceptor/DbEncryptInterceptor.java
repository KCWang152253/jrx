/**
 * Copyright (c) 2020. 北京江融信科技有限公司 版权所有
 * Created on 2020-09-15.
 */

package com.anytech.anytxn.biz.common.interceptor;

import com.anytech.anytxn.biz.common.util.encryption.DataEncryptDecrypt;
import com.anytech.anytxn.biz.common.annotation.EncryptModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.core.annotation.AnnotationUtils;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Properties;

/**
 * @Author wangshuguan
 * @Description
 * @Date 2020-09-15
 * @Version
 */
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
@Slf4j
public class DbEncryptInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement statement = (MappedStatement) invocation.getArgs()[0];
        String methodName = invocation.getMethod().getName();
        Object parameter = invocation.getArgs()[1];
        BoundSql sql = statement.getBoundSql(parameter);
        log.info("sql is: {}", sql.getSql());
        Class<?> objectClass = parameter.getClass();
        EncryptModel encryptDecryptClass = AnnotationUtils.findAnnotation(objectClass, EncryptModel.class);

        if (!Objects.nonNull(encryptDecryptClass)) {
            return invocation.proceed();
        }

        /**
         * 处理查询
         */
        if (StringUtils.equalsIgnoreCase("query", methodName)) {
            DataEncryptDecrypt.decrypt(parameter);
            return invocation.proceed();
        }

        /**
         * 拦截批量插入操作不仅繁琐，而且为了通用逐一通过反射加密不妥
         * 如果有批量操作，最好在传递参数之前，向list中添加之前就加密
         */
        if (StringUtils.equalsIgnoreCase("update", methodName)
                || StringUtils.equalsIgnoreCase("insert", methodName)) {
            DataEncryptDecrypt.encrypt(parameter);
        }

        Object returnValue = invocation.proceed();

        try {
            if (returnValue instanceof ArrayList<?>) {
                DataEncryptDecrypt.decrypt(returnValue);
            }

        } catch (Exception e) {
            // 打印异常，由于拦截器本身抛出异常，比如拦截到很奇葩的返回，应算正常
            // 直接返回原结果即可
            log.info("拦截器加解密异常", e);
            return returnValue;
        }
        return returnValue;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        // TODO Auto-generated method stub
    }
}
