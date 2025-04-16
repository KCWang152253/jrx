package com.anytech.anytxn.biz.common.constant;

/**
 * BIZ-COMMON工程专有响应码枚举类
 * 作为自定义异常对象的入参,为使用自定义异常必须针对异常进系枚举定义.这里的XXX/YYY均需要替换成各个真实的变量.
 * 变量的命名规则:保证简介无歧义,不可过长(>10)也不能太短导致无法辨识含义
 * 
 * 具体可参考Message工程案例(目前在dev_adjust_20200203,后续会迁移至develop)
 * @author fhp
 *
 */
public enum AnyTxnCommonRespCode implements RespCodePrefix {
	/**
	 * 正常响应码枚举定义
	 */
	REQ_SUCCESS(PREFIX_BIZ_COMMON_N, CommonRepMessage.SUCCESS, ""),
	/**
	 * 参数类型响应码枚举定义
	 */
	P_ERR(PREFIX_BIZ_COMMON_P + "10000", CommonRepMessage.P_ERROR, ""),
	P_CLASS_CAST_ERR(PREFIX_BIZ_COMMON_P + "10001", CommonRepMessage.CLASS_CASE, ""),
	P_PARTITION_NULL(PREFIX_BIZ_COMMON_P + "10002", CommonRepMessage.PARTITION_NULL, ""),

	/**
	 * 数据类型响应码枚举定义
	 */
	D_ERR(PREFIX_BIZ_COMMON_D + "20000", CommonRepMessage.DATA_ERROR, ""),
	D_DUPLICATEKEY(PREFIX_BIZ_COMMON_D + "20001", CommonRepMessage.PRIMARY_KEY, ""),
	
	/**
	 * 服务类型响应码枚举定义
	 */
	U_TYPE_NOT_SUPPORTED(PREFIX_BIZ_COMMON_S + "30007", CommonRepMessage.UN_TYPE, ""),

	/**
	 * 物理资源类型响应码枚举定义
	 */
	R_DISK_FULL(PREFIX_BIZ_COMMON_R + "40001", CommonRepMessage.DISK_FULL, ""),
	R_OFFLINE(PREFIX_BIZ_COMMON_R + "40002", CommonRepMessage.NETWORK, ""),
	R_PORT_IN_USER(PREFIX_BIZ_COMMON_R + "40003", CommonRepMessage.PORT, ""),

	/**
	 * 未知异常枚举定义
	 */
	UNKONWN_ERR(MAX_RESPONSE_CODE+RESPONSE_UNKNOWN, CommonRepMessage.OTHER_ERROR, "");

	/**
	 * 响应码编码,格式为 3位业务编码+2位响应码类型编码+5位具体响应码
	 */
	private String code;
	/**
	 * 中文通用描述
	 */
	private CommonRepMessage msg;
	/**
	 * 预留字段，暂时不用
	 */
	private String detail;

	AnyTxnCommonRespCode(String code, CommonRepMessage msg, String detail ) {
		this.code = code;
		this.msg = msg;
		this.detail = detail;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getMsg() {
		return msg.message();
	}

	@Override
	public String getDetail() {
		return detail;
	}

}
