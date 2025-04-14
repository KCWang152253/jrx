package com.anytech.anytxn.anyapi.dto.card;

//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;

/**
 * @author ping
 * @description:
 * @date 2025/4/1217:18
 */
//@Getter
//@Setter
//@ToString
public class CardApplicationReq {
    /**
     * 申请编号
     */
    private String applyNumber;

    public String getApplyNumber() {
        return applyNumber;
    }

    public void setApplyNumber(String applyNumber) {
        this.applyNumber = applyNumber;
    }
}
