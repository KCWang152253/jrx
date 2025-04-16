package com.anytech.anytxn.biz.common.message;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * 国际化工具类
 * @author peidong.meng
 * @date 2021/2/26
 */
@Component
public class MessageSourceUtil implements ApplicationContextAware {

    private final static String MESSAGE_LANGUAGE="messageLanguage";

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        MessageSourceUtil.applicationContext = applicationContext;
    }


    public static MessageLanguage requestLanguage(){
        // 默认英文
        MessageLanguage result = MessageLanguage.EN;
        // 获取请求中Header中的语言格式
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            ServletRequestAttributes attr = (ServletRequestAttributes) requestAttributes;
            HttpServletRequest request = attr.getRequest();
            Object attribute = request.getHeader(MESSAGE_LANGUAGE);
            if (attribute != null) {
                try {
                    // 校验语言类型
                    result = MessageLanguage.valueOf(attribute.toString());
                }catch (Exception e){

                }
            }
        }
        return result;
    }

    /**
     * 使用springboot国际化需要添加配置
     * spring:
     *   messages:
     *     basename: i18n/messages
     * 并在resource下以Resource.Bundle模式创建messages
     * message会有一个默认文件，可以根据语言创建不同文件，如：zh-CN中文、en-US英文
     */
    public static MessageSource messageSource(){
        return applicationContext == null ? null : applicationContext.getBean(MessageSource.class);
    }

    /**
     * 读取前端请求header中的Accept-Language获取语言类型
     */
    public static Locale locale(){
        Locale result = Locale.CHINESE;
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            ServletRequestAttributes attr = (ServletRequestAttributes) requestAttributes;
            HttpServletRequest request = attr.getRequest();
            result = RequestContextUtils.getLocale(request);
        }
        return result;
    }
}
