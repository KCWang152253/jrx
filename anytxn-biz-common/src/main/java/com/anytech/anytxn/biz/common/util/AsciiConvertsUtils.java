package com.anytech.anytxn.biz.common.util;
import com.google.common.base.CharMatcher;
import java.util.regex.Pattern;
public class AsciiConvertsUtils {
    final  static Pattern NON_ASCII_PATTERN = Pattern.compile("[^\\p{ASCII}]");
    public static  String  nonAsciiReplaceBlank(String  str){
        if (!CharMatcher.ascii().matchesAllOf(str)){
            return NON_ASCII_PATTERN.matcher(str).replaceAll(" ");
        }
        return str;
    }
}
