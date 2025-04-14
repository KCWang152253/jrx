package com.anytech.anytxn.anyapi.service;

import com.anytech.anytxn.anyapi.dto.card.CardRepostLossReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author ping
 * @description:
 * @date 2025/4/1114:33
 */
@Service
public class CardRepossLossService {

    private static final Logger log = LoggerFactory.getLogger(CardRepossLossService.class);


    public String cardLoss(CardRepostLossReq cardRepostLossReq){
        log.info("---------cardLoss----");
        return "sdfsd";
    }
}
