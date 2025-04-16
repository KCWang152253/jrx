package com.anytech.anytxn.biz.common.constant;

/**
 * @author peidong.meng
 * @date 2021/3/2
 */
public enum CommonRepDetail implements MessageHandle{
    CACHE_KEY_NULL("缓存模块名称未赋值", "cache key is null"),
    UNKNOWN_ORG_NUMBER("机构获取异常，不能请求服务接口！", "request service must have an organizationNumber, but it does not!"),
    ORG_NUMBER_ERROR("非法机构!", "illegal organizationNumber"),
    BATCH_ORG_NUMBER_EXIST("批次机构不存在！","Batch organizationNumber does not exist!"),
    BATCH_ORG_NUMBER_ERROR("批次机构错误！","Batch organizationNumber is error!"),
    PARTITIONKEY_LONG("partitionKey过长！值：%s", "partitionKey is too long!，source: %s"),
    PARAM_NULL("入参为空", "Input parameter is empty"),
    DATABASE_INSERT("数据库插入结果异常", "Abnormal database insertion result"),
    DATABASE("数据库异常", "Database exception"),

    ;

    private String CnMsg;
    private String EnMsg;

    CommonRepDetail(String cnMsg, String enMsg) {
        CnMsg = cnMsg;
        EnMsg = enMsg;
    }

    @Override
    public String getCnMsg() {
        return CnMsg;
    }

    @Override
    public String getEnMsg() {
        return EnMsg;
    }
}
