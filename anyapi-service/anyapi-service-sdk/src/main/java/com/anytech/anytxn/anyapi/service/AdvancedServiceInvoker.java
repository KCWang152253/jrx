package com.anytech.anytxn.anyapi.service;

import org.springframework.context.ApplicationContext;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Arrays;

@Component
public class AdvancedServiceInvoker {

    private final ApplicationContext applicationContext;
    private final ParameterNameDiscoverer parameterNameDiscoverer;

    public AdvancedServiceInvoker(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        this.parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
    }

    /**
     * 增强版反射调用
     * @param serviceClass Service接口类
     * @param methodName 方法名
     * @param args 参数数组
     * @return 方法执行结果
     */
    public Object invoke(Class<?> serviceClass, String methodName, Object... args) throws Exception {
        // 从Spring容器获取Service实例
        Object service = applicationContext.getBean(serviceClass);

        // 获取参数类型数组
        Class<?>[] parameterTypes = Arrays.stream(args)
                .map(Object::getClass)
                .toArray(Class<?>[]::new);

        // 查找方法
        Method method = ReflectionUtils.findMethod(serviceClass, methodName, parameterTypes);
        if (method == null) {
            throw new NoSuchMethodException(
                    String.format("Method %s not found in %s with parameters %s",
                            methodName, serviceClass.getName(), Arrays.toString(parameterTypes))
            );
        }

        // 获取参数名（可选，可用于日志记录等）
        String[] paramNames = parameterNameDiscoverer.getParameterNames(method);

        // 调用方法
        return ReflectionUtils.invokeMethod(method, service, args);
    }
}