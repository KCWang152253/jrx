package com.anytech.anytxn.anyapi.dto.card;

//import lombok.Getter;
//import lombok.Setter;

/**
 * 卡片挂失
 * @author ping
 * @date 2025/4/10
 */

public class CardRepostLossReq {
    /**
     * 专用卡ID
     */
    private String cardTkID;

    /**
     * 是否产生新卡号
     * Y-Yes
     * N-No
     */
    private String generateNewCard;

    public String getCardTkID() {
        return cardTkID;
    }

    public void setCardTkID(String cardTkID) {
        this.cardTkID = cardTkID;
    }

    public String getGenerateNewCard() {
        return generateNewCard;
    }

    public void setGenerateNewCard(String generateNewCard) {
        this.generateNewCard = generateNewCard;
    }
}
