package com.anytech.anytxn.anyapi.dto.card;

//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;

/**
 * @author ping
 * @description:
 * @date 2025/4/1221:11
 */
//@Getter
//@Setter
//@ToString
public class CardApplicationResp {

    /**
     * 申请编号
     */
    private String applyNumber;

    /**
     * 外部账户号
     */
    private String accountReferenceNumber;

    public String getApplyNumber() {
        return applyNumber;
    }

    public void setApplyNumber(String applyNumber) {
        this.applyNumber = applyNumber;
    }

    public String getAccountReferenceNumber() {
        return accountReferenceNumber;
    }

    public void setAccountReferenceNumber(String accountReferenceNumber) {
        this.accountReferenceNumber = accountReferenceNumber;
    }
}
