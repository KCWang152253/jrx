package com.anytech.anytxn.biz.common.enums;


import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: sukang
 * @Date: 2021/4/24 9:39
 * <p>
 * 1.  业务应用的规则类型和数据库对应， 业务应用从枚举中获取;
 */
public enum RuleTypeEnum {


    /**
     * 授权管理 交易识别 银联
     */
    TRANSACTION_IDENTIFICATION_RULE("transaction_identification_rule", "Transaction Identification (DINERS)",
            IRuleResult.TRANSACTION_IDENTIFICATION_RULE.getCodeMapping()),

    /**
     * 授权管理 交易识别 visa
     */
    TRANSACTION_VISA_IDENTIFICATION_RULE("transaction_visa_identification_rule", "Transaction Identification (VISA)",
            IRuleResult.TRANSACTION_IDENTIFICATION_RULE.getCodeMapping()),
    /**
     * 授权管理 交易识别 mc
     */
    TRANSACTION_MC_IDENTIFICATION_RULE("transaction_mc_identification_rule", "Transaction Identification (MASTERCARD)",
            IRuleResult.TRANSACTION_IDENTIFICATION_RULE.getCodeMapping()),
    /**
     * 授权管理 交易识别 jcb
     */
    TRANSACTION_JCB_IDENTIFICATION_RULE("transaction_jcb_identification_rule", "Transaction Identification (JCB)",
            IRuleResult.TRANSACTION_IDENTIFICATION_RULE.getCodeMapping()),
    /**
     * 授权管理 交易识别 网联
     */
    TRANSACTION_EPCC_IDENTIFICATION_RULE("transaction_epcc_identification_rule", "Transaction Identification (EPCC)",
            IRuleResult.TRANSACTION_IDENTIFICATION_RULE.getCodeMapping()),
    /**
     * 授权管理 交易识别 连通
     */
    TRANSACTION_EXPRESS_IDENTIFICATION_RULE("transaction_express_identification_rule", "Transaction Identification (EXPRESS)",
            IRuleResult.TRANSACTION_IDENTIFICATION_RULE.getCodeMapping()),

    TRANSACTION_ON_US_IDENTIFICATION_RULE("transaction_onus_identification_rule", "Transaction Identification (On US)",
            IRuleResult.TRANSACTION_IDENTIFICATION_RULE.getCodeMapping()),


    AUTHORIZATION_CHECK_RULE("authorization_check_rule", "Authorization Check Rules",
            IRuleResult.AUTHORIZATION_CHECK_RULE.getCodeMapping()),


    AUTH_VELOCITY_CHECK_RULE("auth_velocity_check_rule", "Advanced Velocity Rules",
            IRuleResult.AUTH_VELOCITY_CHECK_RULE.getCodeMapping()),

    /**
     * 授权管理  授权对应账产品规则
     */
    ACCOUNT_GROUP_AUTH_CONTROLLER_RULE("account_group_auth_controller_rule", "Account Check Rules",
            IRuleResult.ACCOUNT_GROUP_AUTH_CONTROLLER_RULE.getCodeMapping()),

    /**
     * 组件参数  分期产品组件参数  自动分期规则
     */
    AUTO_INSTALL_RULE("auto_install_rule", "Automatic Installment Rules",
            IRuleResult.AUTO_INSTALL_RULE.getCodeMapping()),
    /**
     * 组件参数  分期产品组件参数  分期产品查询规则
     */
    INSTALL_PRODUCT_SELECT_RULE("install_product_select_rule", "Installment Product Select Rules",
            IRuleResult.INSTALL_PRODUCT_SELECT_RULE.getCodeMapping()),

    /**
     * 会计管理    会计核算规则
     */
    CASH_GL_AMS_PARA_RULES("cashGlAmsParaRules", "Principal Parameter Rules",
            IRuleResult.CASH_GL_AMS_PARA_RULES.getCodeMapping()),

    FEE_GLAMSPARA_RULES("feeGlAmsParaRules", "Parameter Rules",
            IRuleResult.FEE_GLAMSPARA_RULES.getCodeMapping()),

    INT_GLAMS_PARA_RULES("intGlAmsParaRules", "Interest Parameter Rules",
            IRuleResult.INT_GLAMS_PARA_RULES.getCodeMapping()),

    ABS_GLAMS_PARA_RULES("absGlAmsParaRules", "Abs Parameter Rules",
            IRuleResult.ABS_GLAMS_PARA_RULES.getCodeMapping()),

    /**
     * 额度管理   限额管理规则
     */
    LIMIT_SPECIAL_RULE("limitSpecialRule", "Quota Management Rules",
            IRuleResult.LIMIT_SPECIAL_RULE.getCodeMapping()),

    /**
     * 定价管理   最低还款比例参数规则
     */
    MIN_REPAYMENT_RULE("min_repayment_rule", "Minimum Repayment Rules",
            IRuleResult.MIN_REPAYMENT_RULE.getCodeMapping()),

    /**
     * 定价管理   利率参数规则
     */
    INTEREST_RATE_PARAMETRIC_RULES("interestRateParametricRules", "Interest Rate Rules",
            IRuleResult.INTEREST_RATE_PARAMETRIC_RULES.getCodeMapping()),

    /**
     * 定价管理   交易费参数规则
     */
    TRANSACTION_COST_PARAMETER_RULES("transactionCostParameterRules", "Transaction Fee Rules",
            IRuleResult.TRANSACTION_COST_PARAMETER_RULES.getCodeMapping()),

    /**
     * 定价管理   还款顺序参数规则
     */
    REIMBURSEMENT_ORDER_PARAMETER_RULES("reimbursementOrderParameterRules", "Strike Balance Order Rules"),

    /**
     * 定价管理   年费参数规则
     */
    ANNUAL_FEE_PARAMTER_RULE("annual_fee_paramter_rule", "Annual Fee Rules",
            IRuleResult.ANNUAL_FEE_PARAMTER_RULE.getCodeMapping()),

    /**
     * 定价管理   违约金参数规则
     */
    PENALTY_PARAMTER_RULE("penalty_paramter_rule", "Late Fee Rules",
            IRuleResult.PENALTY_PARAMTER_RULE.getCodeMapping()),

    /**
     * 定价管理   账单处理参数规则
     */
    BILL_PROCESSING_PARAMETER_RULES("billProcessingParameterRules", "Statement Processing Rules",
            IRuleResult.BILL_PROCESSING_PARAMETER_RULES.getCodeMapping()),

    /**
     * 定价管理   超长免息期参数规则
     */
    LARGE_GRACE_PARAMETER_RULES("largeGraceParameterRules", "Long Interest-free Rules",
            IRuleResult.LARGE_GRACE_PARAMETER_RULES.getCodeMapping()),

    /**
     * 定价管理   分期手续费差异化定价参数规则
     */
    INSTALLMENT_COMMISSION_DIFF_PRICE_RULE("installment_commission_diff_price_rule", "Installment Fee Rules"),


    /**
     * 系统参数 机构组件参数  入账核算规则
     */
    ACCOUNTING_RECORD_RULES("AccountingRecordRules", "Accounting Rules",
            IRuleResult.ACCOUNTING_RECORD_RULES.getCodeMapping()),

    /**
     * 系统参数 机构组件参数  交易账户创建规则
     */
    ACCOUNT_BALANCE_CREATE_RULE("account_balance_create_rule", "Account Summary Rules",
            IRuleResult.ACCOUNT_BALANCE_CREATE_RULE.getCodeMapping()),

    /**
     * 系统参数 机构组件参数  账户间还款分配规则
     */
    ACCOUNT_PMT_ALLOC_RULE("account_pmt_alloc_rule", "Accounts Repayment Distribution Rules",
            IRuleResult.ACCOUNT_PMT_ALLOC_RULE.getCodeMapping()),

    /**
     * 消息管理  消息收费规则
     */
    PARAM_SMS_FEE_RULE_TYPE("param_sms_fee_rule_type", "Messages Inform Charging Rules",
            IRuleResult.PARAM_SMS_FEE_RULE_TYPE.getCodeMapping()),

    /**
     * 消息管理  消息通道规则
     */
    MESSAGE_NOTIFICATION_RULE("message_notification_rule", "Message Channel Rules",
            IRuleResult.MESSAGE_NOTIFICATION_RULE.getCodeMapping()),


    TRANSACTION_ROUTING_RULE("transactionRoutingRule", "Transaction Routing Rules",
            IRuleResult.TRANSACTION_ROUTING_RULE.getCodeMapping()),

    //货币转换费率规则
    MARK_UP_FEE_RULE("markUpFeeRule", "Markup Fee Rules",
            IRuleResult.MARK_UP_FEE_RULE.getCodeMapping()),

    //DCC费率规则
    DCC_FEE_RULE("dccFeeRule", "DCC Rate Rules",
            IRuleResult.DCC_FEE_RULE.getCodeMapping()),
    /**
     * 查询交易费规则
     */
    TRANSACTION_QUERY_PARAMETER_RULES("transactionQueryParameterRules", "Transaction Query Parameter Rules",
                                     IRuleResult.TRANSACTION_QUERY_PARAMETER_RULES.getCodeMapping()),

    /**
     * 消费交易费规则
     */
    CONSUME_FEE_PARAMETER_RULES("consumeFeeParameterRules", "Consume Fee Parameter Rules",
                                      IRuleResult.CONSUME_FEE_PARAMETER_RULES.getCodeMapping()),

    /**
     * 清算交易识别-万事达
     */
    SETTLE_MC_IDENTIFICATION_RULE("settle_mc_identification_rule", "Settlement Transaction Identification (MASTERCARD)",
            IRuleResult.SETTLE_MC_IDENTIFICATION_RULE.getCodeMapping()),

    /**
     * 清算交易识别-VISA
     */
    SETTLE_VISA_IDENTIFICATION_RULE("settle_visa_identification_rule", "Settlement Transaction Identification (VISA)",
            IRuleResult.SETTLE_VISA_IDENTIFICATION_RULE.getCodeMapping()),

    /**
     * 充值手续费规则
     */
    RECHARGE_FEE_PARAMETER_RULES("RechargeFeeParameterRules", "Recharge Fee Parameter Rules",
                                IRuleResult.RECHARGE_FEE_PARAMETER_RULES.getCodeMapping());



    /**
     * 规则类型 ，业务中使用
     */
    private String ruleType;

    private String ruleDesc;

    public String getRuleType() {
        return ruleType;
    }

    public String getRuleDesc() {
        return ruleDesc;
    }

    private Map<String, String> codeMapping;


    public Map<String, String> getCodeMapping() {
        return codeMapping;
    }

    RuleTypeEnum(String ruleType, String ruleDesc) {
        this.ruleType = ruleType;
        this.ruleDesc = ruleDesc;
    }

    RuleTypeEnum(String ruleType, String ruleDesc, Map<String, String> codeMapping) {
        this.ruleType = ruleType;
        this.ruleDesc = ruleDesc;
        this.codeMapping = codeMapping;
    }

    public static RuleTypeEnum getRuleTypeEnum(String ruleType) {
        return Arrays.stream(RuleTypeEnum.values())
                .filter(t -> Objects.equals(ruleType, t.getRuleType()))
                .findFirst()
                .orElse(null);

    }


}
