package com.anytech.anytxn.biz.common.filter;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * <pre>
 * Description
 * Dept:		江融信
 * Author:		lichao
 * Version:		1.0
 * Create at:	2020-08-19 14:04
 *
 * 修改历史:
 * 日期    作者    版本  修改描述
 * ------------------------------------------------------------------
 **/

@WebFilter(filterName = "druidWebStatFilter", urlPatterns = "/*",
        initParams = {@WebInitParam(name="exclusions", value = "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*")})
public class DruidStatFilter extends WebStatFilter {
}
