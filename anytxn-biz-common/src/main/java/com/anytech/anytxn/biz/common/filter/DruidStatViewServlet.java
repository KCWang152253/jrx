package com.anytech.anytxn.biz.common.filter;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

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
@WebServlet(urlPatterns = "/druid/*", name = "druidStatViewServlet",
        initParams = {@WebInitParam(name = "loginUsername", value = "admin"),//用户名
                @WebInitParam(name = "loginPassword", value = "admin"),//密码
                @WebInitParam(name = "resetEnable", value = "false"),//页面重置按钮是否启用
                @WebInitParam(name = "allow", value = ""),  //白名单
                @WebInitParam(name = "deny", value = "") })//黑名单
public class DruidStatViewServlet extends StatViewServlet {
}
