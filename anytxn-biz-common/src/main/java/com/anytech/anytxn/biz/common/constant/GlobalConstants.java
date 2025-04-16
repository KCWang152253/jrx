package com.anytech.anytxn.biz.common.constant;

import java.time.LocalDate;

/**
 * @author peidong.meng
 * @date 2020/6/18
 *
 * 全局系统常量
 */
public class GlobalConstants {

    private GlobalConstants() {

    }

    /**
     * 默认系统编号
     */
    public static final String DEFAULT_SYSTEM_ID = "0000";

    /**
     * 初始化日期
     */
    public static final LocalDate INITIAL_DATE = null;

    /**
     * 初始化日期
     */
    public static final LocalDate INITIAL_LOCAL_DATE = LocalDate.of(1, 1, 1);

    /**
     * 默认用户
     */
    public static final String DEFAULT_UPDATE_BY = "SYSTEM";

    /** 禁用 */
    public static final String DISABLED = "0";

    /** 启用 */
    public static final String ENABLED = "1";

    /**
     * 人民币
     */
    public static final String RMB = "156";

    /**
     * 新加坡国籍标识
     */
    public static final String COUNTRY_SGP="SGP";
}
