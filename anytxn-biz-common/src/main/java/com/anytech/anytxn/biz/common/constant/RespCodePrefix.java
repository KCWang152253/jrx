package com.anytech.anytxn.biz.common.constant;
/**
 * 响应码常量定义,所有工程均在次定义各自的前缀
 * 具体说明参见[http://10.0.11.43:8090/pages/viewpage.action?pageId=26709675]
 * @author fhp
 *
 */
public interface RespCodePrefix {

	//////////////////////////////////////////////////////////////////////////////////////////////////////
	// 所有业务响应码前缀定义
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 全局统一正常响应码前缀
	 */
	String PREFIX_COMMON = "100";
	/**
	 * BIZ-COMMON响应码前缀
	 */
	String PREFIX_BIZ_COMMON = "101";
	/**
	 * PARAMETER响应码前缀
	 */
	String PREFIX_PARAMETER = "102";
	/**
	 * RULE响应码前缀
	 */
	String PREFIX_RULE = "103";
	/**
	 * NUMBER响应码前缀
	 */
	String PREFIX_NUMBER = "104";
	/**
	 * MESSAGE响应码前缀
	 */
	String PREFIX_MESSAGE = "105";
	/**
	 * CMS响应码前缀
	 */
	String PREFIX_CMS = "106";
	/**
	 * GATEWAY响应码前缀
	 */
	String PREFIX_GATEWAY = "107";
	/**
	 * GATEWAY_ADMIN响应码前缀
	 */
	String PREFIX_GATEWAY_ADMIN = "108";
	/**
	 * AUTH_ADMIN响应码前缀
	 */
	String PREFIX_AUTH_ADMIN = "109";
	
	
	/**
	 * BIS-CORE响应码前缀
	 */
	String PREFIX_BIS_CORE = "110";
	/**
	 * ACCOUNTING响应码前缀
	 */
	String PREFIX_ACCOUNTING = "111";
	/**
	 * CARD响应码前缀
	 */
	String PREFIX_CARD = "112";
	/**
	 * CARDHOLDER响应码前缀
	 */
	String PREFIX_CARDHOLDER = "113";
	/**
	 * INSTALLMENT响应码前缀
	 */
	String PREFIX_INSTALLMENT = "114";
	
	/**
	 * AUTH响应码前缀
	 */
	String PREFIX_AUTH = "121";
	/**
	 * ACCOUNTANT响应码前缀
	 */
	String PREFIX_ACCOUNTANT = "122";
	/**
	 * LIMIT响应码前缀
	 */
	String PREFIX_LIMIT = "123";
	/**
	 * PUBSERVICE响应码前缀
	 */
	String PREFIX_PUBSERVICE = "124";
	/**
	 * TRANSACTION响应码前缀
	 */
	String PREFIX_TRANSACTION = "125";
	/**
	 * SETTLE响应码前缀
	 */
	String PREFIX_SETTLE = "126";

	/**
	 * CUSTACCOUNT响应码前缀
	 */
	String PREFIX_CUST_ACCOUNT = "127";

	/**
	 * CID_MAPPING响应码前缀
	 */
	String PREFIX_CID_MAPPING = "130";
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	// 所有响应码类型定义
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 正常类型响应码前缀
	 */
	String PREFIX_N = "00";
	/**
	 * 参数类型响应码前缀
	 */
	String PREFIX_P = "10";
	/**
	 * 数据类型响应码前缀
	 */
	String PREFIX_D = "20";
	/**
	 * 服务类型响应码前缀
	 */
	String PREFIX_S = "30";
	/**
	 * 资源类型响应码前缀
	 */
	String PREFIX_R = "40";

	//////////////////////////////////////////////////////////////////////////////////////////////////////
	// 特定响应码定义
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 最大响应码,所有响应码不能超过此值(保证不超过Integer.MAX_VALUE=2147483647)
	 */
	String MAX_RESPONSE_CODE = "2109999999";
	/**
	 * 请求应答未知异常-后5位
	 */
	String RESPONSE_UNKNOWN = "99999";
	/**
	 * 请求应答正常-后5位
	 */
	String RESPONSE_SUCESS = "00000";
	
	/**
	 * 全局统一正常类型响应码为1000000000
	 */
	String NORMAL_RESP = PREFIX_COMMON+PREFIX_N+RESPONSE_SUCESS;
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	// BIZ-COMMON响应码前两段,正常响应码为全部
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * BIZ-COMMON正常类型响应码为全部
	 */
	String PREFIX_BIZ_COMMON_N = PREFIX_COMMON+PREFIX_N+RESPONSE_SUCESS;
	/**
	 * BIZ-COMMON参数类型响应码前两段
	 */
	String PREFIX_BIZ_COMMON_P = PREFIX_BIZ_COMMON+PREFIX_P;
	/**
	 * BIZ-COMMON数据类型响应码前两段
	 */
	String PREFIX_BIZ_COMMON_D = PREFIX_BIZ_COMMON+PREFIX_D;
	/**
	 * BIZ-COMMON服务类型响应码前两段
	 */
	String PREFIX_BIZ_COMMON_S = PREFIX_BIZ_COMMON+PREFIX_S;
	/**
	 * BIZ-COMMON资源类型响应码前两段
	 */
	String PREFIX_BIZ_COMMON_R = PREFIX_BIZ_COMMON+PREFIX_R;


	//////////////////////////////////////////////////////////////////////////////////////////////////////
	// PARAMETER响应码前两段,正常响应码为全部
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	String PREFIX_PARAMETER_N = PREFIX_COMMON+PREFIX_N+RESPONSE_SUCESS;
	String PREFIX_PARAMETER_P = PREFIX_PARAMETER+PREFIX_P;
	String PREFIX_PARAMETER_D = PREFIX_PARAMETER+PREFIX_D;
	String PREFIX_PARAMETER_S = PREFIX_PARAMETER+PREFIX_S;
	String PREFIX_PARAMETER_R = PREFIX_PARAMETER+PREFIX_R;

	//////////////////////////////////////////////////////////////////////////////////////////////////////
	// RULE响应码前两段,正常响应码为全部
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	String PREFIX_RULE_N = PREFIX_COMMON+PREFIX_N+RESPONSE_SUCESS;
	String PREFIX_RULE_P = PREFIX_RULE+PREFIX_P;
	String PREFIX_RULE_D = PREFIX_RULE+PREFIX_D;
	String PREFIX_RULE_S = PREFIX_RULE+PREFIX_S;
	String PREFIX_RULE_R = PREFIX_RULE+PREFIX_R;

	//////////////////////////////////////////////////////////////////////////////////////////////////////
	// NUMBER响应码前两段,正常响应码为全部
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	String PREFIX_NUMBER_N = PREFIX_COMMON+PREFIX_N+RESPONSE_SUCESS;
	String PREFIX_NUMBER_P = PREFIX_NUMBER+PREFIX_P;
	String PREFIX_NUMBER_D = PREFIX_NUMBER+PREFIX_D;
	String PREFIX_NUMBER_S = PREFIX_NUMBER+PREFIX_S;
	String PREFIX_NUMBER_R = PREFIX_NUMBER+PREFIX_R;

	//////////////////////////////////////////////////////////////////////////////////////////////////////
	// MESSAGE响应码前两段,正常响应码为全部
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	String PREFIX_MESSAGE_N = PREFIX_COMMON+PREFIX_N+RESPONSE_SUCESS;
	String PREFIX_MESSAGE_P = PREFIX_MESSAGE+PREFIX_P;
	String PREFIX_MESSAGE_D = PREFIX_MESSAGE+PREFIX_D;
	String PREFIX_MESSAGE_S = PREFIX_MESSAGE+PREFIX_S;
	String PREFIX_MESSAGE_R = PREFIX_MESSAGE+PREFIX_R;

	//////////////////////////////////////////////////////////////////////////////////////////////////////
	// CMS响应码前两段,正常响应码为全部
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	String PREFIX_CMS_N = PREFIX_COMMON+PREFIX_N+RESPONSE_SUCESS;
	String PREFIX_CMS_P = PREFIX_CMS+PREFIX_P;
	String PREFIX_CMS_D = PREFIX_CMS+PREFIX_D;
	String PREFIX_CMS_S = PREFIX_CMS+PREFIX_S;
	String PREFIX_CMS_R = PREFIX_CMS+PREFIX_R;

	//////////////////////////////////////////////////////////////////////////////////////////////////////
	// BIS_CORE响应码前两段,正常响应码为全部
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	String PREFIX_BIS_CORE_N = PREFIX_COMMON+PREFIX_N+RESPONSE_SUCESS;
	String PREFIX_BIS_CORE_P = PREFIX_BIS_CORE+PREFIX_P;
	String PREFIX_BIS_CORE_D = PREFIX_BIS_CORE+PREFIX_D;
	String PREFIX_BIS_CORE_S = PREFIX_BIS_CORE+PREFIX_S;
	String PREFIX_BIS_CORE_R = PREFIX_BIS_CORE+PREFIX_R;

	//////////////////////////////////////////////////////////////////////////////////////////////////////
	// ACCOUNTING响应码前两段,正常响应码为全部
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	String PREFIX_ACCOUNTING_N = PREFIX_COMMON+PREFIX_N+RESPONSE_SUCESS;
	String PREFIX_ACCOUNTING_P = PREFIX_ACCOUNTING+PREFIX_P;
	String PREFIX_ACCOUNTING_D = PREFIX_ACCOUNTING+PREFIX_D;
	String PREFIX_ACCOUNTING_S = PREFIX_ACCOUNTING+PREFIX_S;
	String PREFIX_ACCOUNTING_R = PREFIX_ACCOUNTING+PREFIX_R;

	//////////////////////////////////////////////////////////////////////////////////////////////////////
	// CARD响应码前两段,正常响应码为全部
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	String PREFIX_CARD_N = PREFIX_COMMON+PREFIX_N+RESPONSE_SUCESS;
	String PREFIX_CARD_P = PREFIX_CARD+PREFIX_P;
	String PREFIX_CARD_D = PREFIX_CARD+PREFIX_D;
	String PREFIX_CARD_S = PREFIX_CARD+PREFIX_S;
	String PREFIX_CARD_R = PREFIX_CARD+PREFIX_R;

	//////////////////////////////////////////////////////////////////////////////////////////////////////
	// CARDHOLDER响应码前两段,正常响应码为全部
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	String PREFIX_CARDHOLDER_N = PREFIX_COMMON+PREFIX_N+RESPONSE_SUCESS;
	String PREFIX_CARDHOLDER_P = PREFIX_CARDHOLDER+PREFIX_P;
	String PREFIX_CARDHOLDER_D = PREFIX_CARDHOLDER+PREFIX_D;
	String PREFIX_CARDHOLDER_S = PREFIX_CARDHOLDER+PREFIX_S;
	String PREFIX_CARDHOLDER_R = PREFIX_CARDHOLDER+PREFIX_R;

	//////////////////////////////////////////////////////////////////////////////////////////////////////
	// INSTALLMENT响应码前两段,正常响应码为全部
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	String PREFIX_INSTALLMENT_N = PREFIX_COMMON+PREFIX_N+RESPONSE_SUCESS;
	String PREFIX_INSTALLMENT_P = PREFIX_INSTALLMENT+PREFIX_P;
	String PREFIX_INSTALLMENT_D = PREFIX_INSTALLMENT+PREFIX_D;
	String PREFIX_INSTALLMENT_S = PREFIX_INSTALLMENT+PREFIX_S;
	String PREFIX_INSTALLMENT_R = PREFIX_INSTALLMENT+PREFIX_R;

	//////////////////////////////////////////////////////////////////////////////////////////////////////
	// AUTH响应码前两段,正常响应码为全部
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	String PREFIX_AUTH_N = PREFIX_COMMON+PREFIX_N+RESPONSE_SUCESS;
	String PREFIX_AUTH_P = PREFIX_AUTH+PREFIX_P;
	String PREFIX_AUTH_D = PREFIX_AUTH+PREFIX_D;
	String PREFIX_AUTH_S = PREFIX_AUTH+PREFIX_S;
	String PREFIX_AUTH_R = PREFIX_AUTH+PREFIX_R;

	//////////////////////////////////////////////////////////////////////////////////////////////////////
	// ACCOUNTANT响应码前两段,正常响应码为全部
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	String PREFIX_ACCOUNTANT_N = PREFIX_COMMON+PREFIX_N+RESPONSE_SUCESS;
	String PREFIX_ACCOUNTANT_P = PREFIX_ACCOUNTANT+PREFIX_P;
	String PREFIX_ACCOUNTANT_D = PREFIX_ACCOUNTANT+PREFIX_D;
	String PREFIX_ACCOUNTANT_S = PREFIX_ACCOUNTANT+PREFIX_S;
	String PREFIX_ACCOUNTANT_R = PREFIX_ACCOUNTANT+PREFIX_R;

	//////////////////////////////////////////////////////////////////////////////////////////////////////
	// LIMIT响应码前两段,正常响应码为全部
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	String PREFIX_LIMIT_N = PREFIX_COMMON+PREFIX_N+RESPONSE_SUCESS;
	String PREFIX_LIMIT_P = PREFIX_LIMIT+PREFIX_P;
	String PREFIX_LIMIT_D = PREFIX_LIMIT+PREFIX_D;
	String PREFIX_LIMIT_S = PREFIX_LIMIT+PREFIX_S;
	String PREFIX_LIMIT_R = PREFIX_LIMIT+PREFIX_R;

	//////////////////////////////////////////////////////////////////////////////////////////////////////
	// PUBSERVICE响应码前两段,正常响应码为全部
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	String PREFIX_PUBSERVICE_N = PREFIX_COMMON+PREFIX_N+RESPONSE_SUCESS;
	String PREFIX_PUBSERVICE_P = PREFIX_PUBSERVICE+PREFIX_P;
	String PREFIX_PUBSERVICE_D = PREFIX_PUBSERVICE+PREFIX_D;
	String PREFIX_PUBSERVICE_S = PREFIX_PUBSERVICE+PREFIX_S;
	String PREFIX_PUBSERVICE_R = PREFIX_PUBSERVICE+PREFIX_R;

	//////////////////////////////////////////////////////////////////////////////////////////////////////
	// TRANSACTION响应码前两段,正常响应码为全部
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	String PREFIX_TRANSACTION_N = PREFIX_COMMON+PREFIX_N+RESPONSE_SUCESS;
	String PREFIX_TRANSACTION_P = PREFIX_TRANSACTION+PREFIX_P;
	String PREFIX_TRANSACTION_D = PREFIX_TRANSACTION+PREFIX_D;
	String PREFIX_TRANSACTION_S = PREFIX_TRANSACTION+PREFIX_S;
	String PREFIX_TRANSACTION_R = PREFIX_TRANSACTION+PREFIX_R;

	//////////////////////////////////////////////////////////////////////////////////////////////////////
	// SETTLE响应码前两段,正常响应码为全部
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	String PREFIX_SETTLE_N = PREFIX_COMMON+PREFIX_N+RESPONSE_SUCESS;
	String PREFIX_SETTLE_P = PREFIX_SETTLE+PREFIX_P;
	String PREFIX_SETTLE_D = PREFIX_SETTLE+PREFIX_D;
	String PREFIX_SETTLE_S = PREFIX_SETTLE+PREFIX_S;
	String PREFIX_SETTLE_R = PREFIX_SETTLE+PREFIX_R;

	//////////////////////////////////////////////////////////////////////////////////////////////////////
	// GATEWAY响应码前两段,正常响应码为全部
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	String PREFIX_GATEWAY_N = PREFIX_COMMON+PREFIX_N+RESPONSE_SUCESS;
	String PREFIX_GATEWAY_P = PREFIX_GATEWAY+PREFIX_P;
	String PREFIX_GATEWAY_D = PREFIX_GATEWAY+PREFIX_D;
	String PREFIX_GATEWAY_S = PREFIX_GATEWAY+PREFIX_S;
	String PREFIX_GATEWAY_R = PREFIX_GATEWAY+PREFIX_R;

	//////////////////////////////////////////////////////////////////////////////////////////////////////
	// GATEWAY_ADMIN响应码前两段,正常响应码为全部
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	String PREFIX_GATEWAY_ADMIN_N = PREFIX_COMMON+PREFIX_N+RESPONSE_SUCESS;
	String PREFIX_GATEWAY_ADMIN_P = PREFIX_GATEWAY_ADMIN+PREFIX_P;
	String PREFIX_GATEWAY_ADMIN_D = PREFIX_GATEWAY_ADMIN+PREFIX_D;
	String PREFIX_GATEWAY_ADMIN_S = PREFIX_GATEWAY_ADMIN+PREFIX_S;
	String PREFIX_GATEWAY_ADMIN_R = PREFIX_GATEWAY_ADMIN+PREFIX_R;

	//////////////////////////////////////////////////////////////////////////////////////////////////////
	// AUTH_SERVER响应码前两段,正常响应码为全部
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	String PREFIX_AUTH_ADMIN_N = PREFIX_COMMON+PREFIX_N+RESPONSE_SUCESS;
	String PREFIX_AUTH_ADMIN_P = PREFIX_AUTH_ADMIN+PREFIX_P;
	String PREFIX_AUTH_ADMIN_D = PREFIX_AUTH_ADMIN+PREFIX_D;
	String PREFIX_AUTH_ADMIN_S = PREFIX_AUTH_ADMIN+PREFIX_S;
	String PREFIX_AUTH_ADMIN_R = PREFIX_AUTH_ADMIN+PREFIX_R;

	//////////////////////////////////////////////////////////////////////////////////////////////////////
	// CID_MAPPING响应码前两段,正常响应码为全部
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	String PREFIX_CID_MAPPING_N = PREFIX_CID_MAPPING+PREFIX_N+RESPONSE_SUCESS;
	String PREFIX_CID_MAPPING_P = PREFIX_CID_MAPPING+PREFIX_P;
	String PREFIX_CID_MAPPING_D = PREFIX_CID_MAPPING+PREFIX_D;
	String PREFIX_CID_MAPPING_S = PREFIX_CID_MAPPING+PREFIX_S;
	String PREFIX_CID_MAPPING_R = PREFIX_CID_MAPPING+PREFIX_R;


	/**
	 * 返回状态码
	 * @return
	 */
	String getCode();

	/**
	 * 返回消息
	 * @return
	 */
	String getMsg();

	/**
	 * 返回明细描述
	 * @return
	 */
	String getDetail();
	
}