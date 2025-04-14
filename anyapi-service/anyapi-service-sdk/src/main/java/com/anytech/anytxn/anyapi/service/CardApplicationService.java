package com.anytech.anytxn.anyapi.service;


import com.anytech.anytxn.anyapi.dto.card.CardApplicationReq;
import com.anytech.anytxn.anyapi.dto.card.CardApplicationResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author ping
 * @description:
 * @date 2025/4/1217:23
 */
@Service
public class CardApplicationService {

    private static final Logger log = LoggerFactory.getLogger(CardApplicationService.class);


    public CardApplicationResp cardApplication(CardApplicationReq cardApplicationReq){
        CardApplicationResp cardApplicationResp = new CardApplicationResp();
        cardApplicationResp.setAccountReferenceNumber("112234");
        log.info("---------cardApplication----");
        return cardApplicationResp;
    }
}
