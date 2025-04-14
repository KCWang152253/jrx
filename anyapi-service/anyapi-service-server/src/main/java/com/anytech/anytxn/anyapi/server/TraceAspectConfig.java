/**
 * Copyright (c) 2020. 北京江融信科技有限公司 版权所有
 * Created on 2020-09-10.
 */

package com.anytech.anytxn.anyapi.server;

import com.plumelog.trace.aspect.AbstractAspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author wangshuguan
 * @Description 链路跟踪
 * @Date 2020-09-10
 * @Version
 */
@Aspect
@Component
public class TraceAspectConfig extends AbstractAspect {

    @Value("${anytxn.log.trace.enable:false}")
    private boolean isEnable;

    @Around("within(com.anytech.anytxn..*))")
    public Object around(JoinPoint joinPoint) throws Throwable {
        if (isEnable){
            return aroundExecute(joinPoint);
        }
        return ((ProceedingJoinPoint)joinPoint).proceed();
    }
}
