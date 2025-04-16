package com.anytech.anytxn.biz.common.constant;

import com.anytech.anytxn.biz.common.message.MessageLanguage;
import com.anytech.anytxn.biz.common.message.MessageSourceUtil;

/**
 * 异常描述获取方法接口
 * @author peidong.meng
 * @date 2021/3/1
 */
public interface MessageHandle {

    String getCnMsg();

    String getEnMsg();

    default String message() {
        return message(MessageSourceUtil.requestLanguage());
    }

    default String message(MessageLanguage ml) {
        switch (ml){
            case CN:
                return getCnMsg();
            case EN:
                return getEnMsg();
            default:
                return "";
        }
    }
}
