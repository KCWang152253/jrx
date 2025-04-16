package com.anytech.anytxn.biz.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: sukang
 * @Date: 2021/6/9 12:21
 */
public interface IRuleResult {


    /**
     * 交易识别的规则返回值枚举
     */
    @Getter
    @AllArgsConstructor
    enum TRANSACTION_IDENTIFICATION_RULE implements IRuleResult {
        /**
         * 交易识别结果的返回值枚举
         */
        AUTH_TRANSACTION_TYPE_TOP_CODE("authTransactionTypeTopCode", "授权交易大类"),
        AUTH_TRANSACTION_TYPE_DETAIL_CODE("authTransactionTypeDetailCode", "授权交易细类"),
        POSTING_TRANSACTION_CODE("postingTransactionCode", "入账交易码值"),
        POSTING_TRANSACTION_CODE_REV("postingTransactionCodeRev", "入账交易码值(撤销)");

        String code;
        String desc;

        public static Map<String, String> getCodeMapping() {
            return Arrays.stream(TRANSACTION_IDENTIFICATION_RULE.values())
                    .collect(Collectors.toMap(TRANSACTION_IDENTIFICATION_RULE::getCode, TRANSACTION_IDENTIFICATION_RULE::getDesc));
        }
    }


    @Getter
    @AllArgsConstructor
    enum AUTH_VELOCITY_CHECK_RULE implements IRuleResult {
        /**
         * 流量扩展规则 结果描述
         */
        TABLE_ID("tableId", "流量检查定义编号");

        String code;
        String desc;

        public static Map<String, String> getCodeMapping() {
            return Arrays.stream(AUTH_VELOCITY_CHECK_RULE.values())
                    .collect(Collectors.toMap(AUTH_VELOCITY_CHECK_RULE::getCode, AUTH_VELOCITY_CHECK_RULE::getDesc));
        }


    }


    @Getter
    @AllArgsConstructor
    enum ACCOUNT_GROUP_AUTH_CONTROLLER_RULE implements IRuleResult {
        /**
         * 授权对应账产品规则 结果描述
         */
        ACCOUNT_PRODUCT_GROUP_ID("tableId", "账户产品组编号");

        String code;
        String desc;

        public static Map<String, String> getCodeMapping() {
            return Arrays.stream(ACCOUNT_GROUP_AUTH_CONTROLLER_RULE.values())
                    .collect(Collectors.toMap(ACCOUNT_GROUP_AUTH_CONTROLLER_RULE::getCode, ACCOUNT_GROUP_AUTH_CONTROLLER_RULE::getDesc));
        }
    }

    @Getter
    @AllArgsConstructor
    enum AUTO_INSTALL_RULE implements IRuleResult {
        /**
         * 自动分期规则 结果描述
         */
        TABLE_ID("tableId", "自动分期产品ID");

        String code;
        String desc;

        public static Map<String, String> getCodeMapping() {
            return Arrays.stream(AUTO_INSTALL_RULE.values())
                    .collect(Collectors.toMap(AUTO_INSTALL_RULE::getCode, AUTO_INSTALL_RULE::getDesc));
        }
    }


    @Getter
    @AllArgsConstructor
    enum INSTALL_PRODUCT_SELECT_RULE implements IRuleResult {
        /**
         * 分期产品查询规则 结果描述
         */
        TABLE_ID("tableId", "分期产品ID");

        String code;
        String desc;

        public static Map<String, String> getCodeMapping() {
            return Arrays.stream(INSTALL_PRODUCT_SELECT_RULE.values())
                    .collect(Collectors.toMap(INSTALL_PRODUCT_SELECT_RULE::getCode, INSTALL_PRODUCT_SELECT_RULE::getDesc));
        }
    }


    @Getter
    @AllArgsConstructor
    enum CASH_GL_AMS_PARA_RULES implements IRuleResult {
        /**
         * 会计核算规则 结果描述
         */
        TABLE_ID("tableId", "本金参数规则");

        String code;
        String desc;

        public static Map<String, String> getCodeMapping() {
            return Arrays.stream(CASH_GL_AMS_PARA_RULES.values())
                    .collect(Collectors.toMap(CASH_GL_AMS_PARA_RULES::getCode, CASH_GL_AMS_PARA_RULES::getDesc));
        }
    }

    @Getter
    @AllArgsConstructor
    enum FEE_GLAMSPARA_RULES implements IRuleResult {
        /**
         * 会计核算费用参数规则 结果描述
         */
        TABLE_ID("tableId", "费用参数规则");

        String code;
        String desc;

        public static Map<String, String> getCodeMapping() {
            return Arrays.stream(FEE_GLAMSPARA_RULES.values())
                    .collect(Collectors.toMap(FEE_GLAMSPARA_RULES::getCode, FEE_GLAMSPARA_RULES::getDesc));
        }
    }

    @Getter
    @AllArgsConstructor
    enum INT_GLAMS_PARA_RULES implements IRuleResult {
        /**
         * 会计核算利息参数规则 结果描述
         */
        TABLE_ID("tableId", "利息参数规则");

        String code;
        String desc;

        public static Map<String, String> getCodeMapping() {
            return Arrays.stream(INT_GLAMS_PARA_RULES.values())
                    .collect(Collectors.toMap(INT_GLAMS_PARA_RULES::getCode, INT_GLAMS_PARA_RULES::getDesc));
        }
    }

    @Getter
    @AllArgsConstructor
    enum ABS_GLAMS_PARA_RULES implements IRuleResult {
        /**
         * 会计核算abs参数规则 结果描述
         */
        TABLE_ID("tableId", "abs参数规则");

        String code;
        String desc;

        public static Map<String, String> getCodeMapping() {
            return Arrays.stream(ABS_GLAMS_PARA_RULES.values())
                    .collect(Collectors.toMap(ABS_GLAMS_PARA_RULES::getCode, ABS_GLAMS_PARA_RULES::getDesc));
        }
    }

    @Getter
    @AllArgsConstructor
    enum LIMIT_SPECIAL_RULE implements IRuleResult {
        /**
         * 限额管理规则 结果描述
         */
        TABLE_ID("tableId", "最大额度限制");

        String code;
        String desc;

        public static Map<String, String> getCodeMapping() {
            return Arrays.stream(LIMIT_SPECIAL_RULE.values())
                    .collect(Collectors.toMap(LIMIT_SPECIAL_RULE::getCode, LIMIT_SPECIAL_RULE::getDesc));
        }
    }


    @Getter
    @AllArgsConstructor
    enum MIN_REPAYMENT_RULE implements IRuleResult {
        /**
         * 最低还款比例参数规则 结果描述
         */
        TABLE_ID("tableId", "最低还款比例参数规则");

        String code;
        String desc;

        public static Map<String, String> getCodeMapping() {
            return Arrays.stream(MIN_REPAYMENT_RULE.values())
                    .collect(Collectors.toMap(MIN_REPAYMENT_RULE::getCode, MIN_REPAYMENT_RULE::getDesc));
        }
    }


    @Getter
    @AllArgsConstructor
    enum INTEREST_RATE_PARAMETRIC_RULES implements IRuleResult {
        /**
         * 利率参数规则 结果描述
         */
        TABLE_ID("tableId", "计息参数表ID");


        String code;
        String desc;

        public static Map<String, String> getCodeMapping() {
            return Arrays.stream(INTEREST_RATE_PARAMETRIC_RULES.values())
                    .collect(Collectors.toMap(INTEREST_RATE_PARAMETRIC_RULES::getCode, INTEREST_RATE_PARAMETRIC_RULES::getDesc));
        }
    }


    @Getter
    @AllArgsConstructor
    enum TRANSACTION_COST_PARAMETER_RULES implements IRuleResult {
        /**
         * 交易费参数规则 结果描述
         */
        TABLE_ID("tableId", "交易费参数规则");

        String code;
        String desc;

        public static Map<String, String> getCodeMapping() {
            return Arrays.stream(TRANSACTION_COST_PARAMETER_RULES.values())
                    .collect(Collectors.toMap(TRANSACTION_COST_PARAMETER_RULES::getCode, TRANSACTION_COST_PARAMETER_RULES::getDesc));
        }
    }


    @Getter
    @AllArgsConstructor
    enum ANNUAL_FEE_PARAMTER_RULE implements IRuleResult {
        /**
         * 年费参数规则 结果描述
         */
        TABLE_ID("tableId", "年费参数规则");

        String code;
        String desc;

        public static Map<String, String> getCodeMapping() {
            return Arrays.stream(ANNUAL_FEE_PARAMTER_RULE.values())
                    .collect(Collectors.toMap(ANNUAL_FEE_PARAMTER_RULE::getCode, ANNUAL_FEE_PARAMTER_RULE::getDesc));
        }
    }


    @Getter
    @AllArgsConstructor
    enum PENALTY_PARAMTER_RULE implements IRuleResult {
        /**
         * 违约金参数规则 结果描述
         */
        TABLE_ID("tableId", "违约金参数规则");

        String code;
        String desc;

        public static Map<String, String> getCodeMapping() {
            return Arrays.stream(PENALTY_PARAMTER_RULE.values())
                    .collect(Collectors.toMap(PENALTY_PARAMTER_RULE::getCode, PENALTY_PARAMTER_RULE::getDesc));
        }
    }


    @Getter
    @AllArgsConstructor
    enum BILL_PROCESSING_PARAMETER_RULES implements IRuleResult {
        /**
         * 账单处理参数规则 结果描述
         */
        TABLE_ID("tableId", "账单处理参数规则");

        String code;
        String desc;

        public static Map<String, String> getCodeMapping() {
            return Arrays.stream(BILL_PROCESSING_PARAMETER_RULES.values())
                    .collect(Collectors.toMap(BILL_PROCESSING_PARAMETER_RULES::getCode, BILL_PROCESSING_PARAMETER_RULES::getDesc));
        }
    }


    @Getter
    @AllArgsConstructor
    enum LARGE_GRACE_PARAMETER_RULES implements IRuleResult {
        /**
         * 超长免息期参数规则 结果描述
         */
        TABLE_ID("tableId", "超长免息期参数规则");

        String code;
        String desc;

        public static Map<String, String> getCodeMapping() {
            return Arrays.stream(LARGE_GRACE_PARAMETER_RULES.values())
                    .collect(Collectors.toMap(LARGE_GRACE_PARAMETER_RULES::getCode, LARGE_GRACE_PARAMETER_RULES::getDesc));
        }
    }


    @Getter
    @AllArgsConstructor
    enum ACCOUNTING_RECORD_RULES implements IRuleResult {
        /**
         * 入账核算规则 结果描述
         */
        PRODUCT_NUMBER("productNumber", "账产品");

        String code;
        String desc;

        public static Map<String, String> getCodeMapping() {
            return Arrays.stream(ACCOUNTING_RECORD_RULES.values())
                    .collect(Collectors.toMap(ACCOUNTING_RECORD_RULES::getCode, ACCOUNTING_RECORD_RULES::getDesc));
        }
    }


    @Getter
    @AllArgsConstructor
    enum ACCOUNT_BALANCE_CREATE_RULE implements IRuleResult {
        /**
         * 交易账户创建规则 结果描述
         */
        TABLE_ID("tableId", "交易账户汇总参数规则");

        String code;
        String desc;

        public static Map<String, String> getCodeMapping() {
            return Arrays.stream(ACCOUNT_BALANCE_CREATE_RULE.values())
                    .collect(Collectors.toMap(ACCOUNT_BALANCE_CREATE_RULE::getCode, ACCOUNT_BALANCE_CREATE_RULE::getDesc));
        }
    }


    @Getter
    @AllArgsConstructor
    enum ACCOUNT_PMT_ALLOC_RULE implements IRuleResult {
        /**
         * 账户间还款分配规则 结果描述
         */
        TABLE_ID("tableId", "账户间还款分配参数规则");

        String code;
        String desc;

        public static Map<String, String> getCodeMapping() {
            return Arrays.stream(ACCOUNT_PMT_ALLOC_RULE.values())
                    .collect(Collectors.toMap(ACCOUNT_PMT_ALLOC_RULE::getCode, ACCOUNT_PMT_ALLOC_RULE::getDesc));
        }
    }


    @Getter
    @AllArgsConstructor
    enum PARAM_SMS_FEE_RULE_TYPE implements IRuleResult {
        /**
         * 消息收费规则 结果描述
         */
        TABLE_ID("tableId", "消息收费参数");

        String code;
        String desc;

        public static Map<String, String> getCodeMapping() {
            return Arrays.stream(PARAM_SMS_FEE_RULE_TYPE.values())
                    .collect(Collectors.toMap(PARAM_SMS_FEE_RULE_TYPE::getCode, PARAM_SMS_FEE_RULE_TYPE::getDesc));
        }
    }


    @Getter
    @AllArgsConstructor
    enum MESSAGE_NOTIFICATION_RULE implements IRuleResult {
        /**
         * 消息通道规则 结果描述
         */
        TABLE_ID("tableId", "消息通道类型");

        String code;
        String desc;

        public static Map<String, String> getCodeMapping() {
            return Arrays.stream(MESSAGE_NOTIFICATION_RULE.values())
                    .collect(Collectors.toMap(MESSAGE_NOTIFICATION_RULE::getCode, MESSAGE_NOTIFICATION_RULE::getDesc));
        }
    }


    @Getter
    @AllArgsConstructor
    enum TRANSACTION_ROUTING_RULE implements IRuleResult {
        /**
         * 交易路由规则 结果描述
         */
        TABLE_ID("tableId", "交易路由单元");

        String code;
        String desc;

        public static Map<String, String> getCodeMapping() {
            return Arrays.stream(TRANSACTION_ROUTING_RULE.values())
                    .collect(Collectors.toMap(TRANSACTION_ROUTING_RULE::getCode, TRANSACTION_ROUTING_RULE::getDesc));
        }
    }


    @Getter
    @AllArgsConstructor
    enum AUTHORIZATION_CHECK_RULE implements IRuleResult {
        /**
         * 交易路由规则 结果描述
         */
        TABLE_ID("tableId", "检查组编号");

        String code;
        String desc;

        public static Map<String, String> getCodeMapping() {
            return Arrays.stream(TRANSACTION_ROUTING_RULE.values())
                    .collect(Collectors.toMap(TRANSACTION_ROUTING_RULE::getCode, TRANSACTION_ROUTING_RULE::getDesc));
        }
    }

    @Getter
    @AllArgsConstructor
    enum MARK_UP_FEE_RULE implements IRuleResult {
        /**
         * 货币转换费ID 结果描述
         */
        TABLE_ID("tableId", "货币转换费ID");

        String code;
        String desc;

        public static Map<String, String> getCodeMapping() {
            return Arrays.stream(MARK_UP_FEE_RULE.values())
                    .collect(Collectors.toMap(MARK_UP_FEE_RULE::getCode, MARK_UP_FEE_RULE::getDesc));
        }
    }

    @Getter
    @AllArgsConstructor
    enum DCC_FEE_RULE implements IRuleResult {
        /**
         * DCC费ID 结果描述
         */
        TABLE_ID("tableId", "DCC费ID");

        String code;
        String desc;

        public static Map<String, String> getCodeMapping() {
            return Arrays.stream(DCC_FEE_RULE.values())
                    .collect(Collectors.toMap(DCC_FEE_RULE::getCode, DCC_FEE_RULE::getDesc));
        }
    }

    @Getter
    @AllArgsConstructor
    enum TRANSACTION_QUERY_PARAMETER_RULES implements IRuleResult {
        /**
         * DCC费ID 结果描述
         */
        TABLE_ID("tableId", "查询交易费ID");

        String code;
        String desc;

        public static Map<String, String> getCodeMapping() {
            return Arrays.stream(TRANSACTION_QUERY_PARAMETER_RULES.values())
                    .collect(Collectors.toMap(TRANSACTION_QUERY_PARAMETER_RULES::getCode, TRANSACTION_QUERY_PARAMETER_RULES::getDesc));
        }
    }

    @Getter
    @AllArgsConstructor
    enum CONSUME_FEE_PARAMETER_RULES implements IRuleResult {
        /**
         * DCC费ID 结果描述
         */
        TABLE_ID("tableId", "消费交易费ID");

        String code;
        String desc;

        public static Map<String, String> getCodeMapping() {
            return Arrays.stream(CONSUME_FEE_PARAMETER_RULES.values())
                    .collect(Collectors.toMap(CONSUME_FEE_PARAMETER_RULES::getCode, CONSUME_FEE_PARAMETER_RULES::getDesc));
        }
    }
    @Getter
    @AllArgsConstructor
    enum RECHARGE_FEE_PARAMETER_RULES implements IRuleResult {
        /**
         * 充值手续费ID 结果描述
         */
        TABLE_ID("tableId", "充值手续费ID");

        String code;
        String desc;

        public static Map<String, String> getCodeMapping() {
            return Arrays.stream(RECHARGE_FEE_PARAMETER_RULES.values())
                    .collect(Collectors.toMap(RECHARGE_FEE_PARAMETER_RULES::getCode, RECHARGE_FEE_PARAMETER_RULES::getDesc));
        }
    }
    @Getter
    @AllArgsConstructor
    enum SETTLE_MC_IDENTIFICATION_RULE implements IRuleResult {
        /**
         * 清算交易识别-万事达  入账交易码 结果描述
         */
        TABLE_ID("transactionCode", "入账交易码");

        String code;
        String desc;

        public static Map<String, String> getCodeMapping() {
            return Arrays.stream(SETTLE_MC_IDENTIFICATION_RULE.values())
                    .collect(Collectors.toMap(SETTLE_MC_IDENTIFICATION_RULE::getCode, SETTLE_MC_IDENTIFICATION_RULE::getDesc));
        }
    }

    @Getter
    @AllArgsConstructor
    enum SETTLE_VISA_IDENTIFICATION_RULE implements IRuleResult {
        /**
         * 清算交易识别-VISA  入账交易码 结果描述
         */
        TABLE_ID("transactionCode", "入账交易码");

        String code;
        String desc;

        public static Map<String, String> getCodeMapping() {
            return Arrays.stream(SETTLE_VISA_IDENTIFICATION_RULE.values())
                    .collect(Collectors.toMap(SETTLE_VISA_IDENTIFICATION_RULE::getCode, SETTLE_VISA_IDENTIFICATION_RULE::getDesc));
        }
    }

}