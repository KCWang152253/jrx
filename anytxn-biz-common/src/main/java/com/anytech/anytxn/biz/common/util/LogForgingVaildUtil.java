package com.anytech.anytxn.biz.common.util;

import org.apache.commons.lang3.StringUtils;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

/**
 * Log Forging漏洞校验
 * @author ping
 * @date 2021/7/20
 */
public class LogForgingVaildUtil {
    /**
     * Log Forging漏洞校验
     * @param logs 日志
     * @return String
     */
    public static String vaildLog(String logs) {
        if(StringUtils.isEmpty(logs)){
            return logs;
        }
        List<String> list = new ArrayList<>();
        list.add("%0a");
        list.add("%0A");
        list.add("%0d");
        list.add("%0D");
        list.add("\r");
        list.add("\n");
        String normalize = Normalizer.normalize(logs, Normalizer.Form.NFKC);
        for (String str : list) {
            normalize = normalize.replace(str, "");
        }
        return normalize;
    }

}
