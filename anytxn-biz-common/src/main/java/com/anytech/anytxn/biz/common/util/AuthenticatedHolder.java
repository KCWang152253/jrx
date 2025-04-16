package com.anytech.anytxn.biz.common.util;//package jrx.anytxn.biz.common.util;
//
//import jrx.anytxn.biz.common.constant.GlobalConstants;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 鉴权持有，保持当前登录用户的基础鉴权信息
// * @author yxy
// * @version 3.0
// * @date 2020/6/19
// */
//public class AuthenticatedHolder {
//
//    private static ThreadLocal<Map<String,Object>> localHolder = ThreadLocal.withInitial(HashMap::new);
//
//    private AuthenticatedHolder() {}
//
//    /**系统编号主键 */
//    private static String SYSTEM_ID_KEY = "SYSTEM_ID_KEY";
//
//    /** 组织编号主键 */
//    private static String ORG_NUMBER_KEY = "ORG_NUMBER_KEY";
//
//    /** 授权用户主键 */
//    private static String AUTH_USER = "AUTH_USER";
//
//    /**
//     * 注册当前线程等用户注册信息，在管理平台端可在filter中初始化注册信息
//     * @param systemId
//     * @param orgNumber
//     * @param authUser
//     */
//    public static void register(String systemId,String orgNumber,String authUser){
//        if(systemId!=null){
//            localHolder.get().put(SYSTEM_ID_KEY,systemId);
//        }
//        if(orgNumber!=null){
//            localHolder.get().put(ORG_NUMBER_KEY,orgNumber);
//        }
//        if(authUser!=null){
//            localHolder.get().put(AUTH_USER,authUser);
//        }
//    }
//
//    /**
//     * 清除登录和鉴权信息
//     */
//    public static void unregister(){
//        localHolder.get().clear();
//    }
//
//    /**
//     * 当前鉴权用户
//     * @return
//     */
//    public static String authUser(){
//        Object v = localHolder.get().get(AUTH_USER);
//        if(v==null){
//            return GlobalConstants.DEFAULT_UPDATE_BY;
//        }else{
//            return String.valueOf(v);
//        }
//    }
//
//    /**
//     * 当前系统编码
//     * @return
//     */
//    public static String systemId(){
//        Object v = localHolder.get().get(SYSTEM_ID_KEY);
//        if(v==null){
//            return GlobalConstants.DEFAULT_SYSTEM_ID;
//        }else{
//            return String.valueOf(v);
//        }
//    }
//
//    /**
//     * 当前机构编码
//     * @return
//     */
//    public static String orgNumber(){
//        return OrgNumberUtil.getOrg();
//    }
//
//}
