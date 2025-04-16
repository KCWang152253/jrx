package com.anytech.anytxn.biz.common.constant;

/**
 * 响应描述
 * @author peidong.meng
 * @date 2021/2/26
 */
public enum CommonRepMessage implements MessageHandle{
    SUCCESS("成功", "success"),
    P_ERROR("参数异常","param error"),
    CLASS_CASE("对象类型转换异常", "class cast exception"),
    PARTITION_NULL("partitionKey为空","partitionKey exception"),
    DATA_ERROR("数据异常","data exception"),
    PRIMARY_KEY("违反唯一约束","unique index"),
    UN_TYPE("不支持的枚举类型", "unknown supported"),
    DISK_FULL("磁盘空间满","disk full"),
    NETWORK("网络不通","network is not working"),
    PORT("端口被占用", "port is use"),
    OTHER_ERROR("其他错误", "unknown error");

    private String CnMsg;
    private String EnMsg;

    CommonRepMessage(String cnMsg, String enMsg) {
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
