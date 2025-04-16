package com.anytech.anytxn.biz.common.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
public class HttpServletRequestUtil {

    public static String getRequestURI(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            ServletRequestAttributes attr = (ServletRequestAttributes) requestAttributes;
            HttpServletRequest request = attr.getRequest();
            return request.getRequestURI();
        }
        return null;
    }
}
