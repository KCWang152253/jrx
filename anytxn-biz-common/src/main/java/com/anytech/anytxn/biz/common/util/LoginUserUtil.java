package com.anytech.anytxn.biz.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

/**
 * @author sukang
 * cms页面获取登录用户的中文名称
 */
@Slf4j
public class LoginUserUtil{

    private static final String LOGIN_USER_KEY = "user_name";

    public static String getLoginUserName() {

        try {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            if (requestAttributes != null) {
                ServletRequestAttributes attr = (ServletRequestAttributes) requestAttributes;
                HttpServletRequest request = attr.getRequest();
                String requestHeader = request.getHeader(LOGIN_USER_KEY);
                if (StringUtils.isNotBlank(requestHeader)) {
                    return base64Decode(requestHeader);
                } else {
                    log.info("获取登录名失败，使用系统默认用户名");
                    return "app-admin";
                }
            }
        }catch (Exception e){
            log.error("获取登录名异常");
        }
        return "app-admin";
    }

    private static String base64Decode(String source){
        return new String(Base64.getDecoder().decode(source));
    }
}
