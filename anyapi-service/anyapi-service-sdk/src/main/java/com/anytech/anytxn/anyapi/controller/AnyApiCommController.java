package com.anytech.anytxn.anyapi.controller;



import com.anytech.anytxn.anyapi.dto.AnyTxnHttpResponse;
import com.anytech.anytxn.anyapi.service.AdvancedServiceInvoker;
import com.anytech.anytxn.anyapi.service.CardRepossLossService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Map;

/**
 *
 *
 **/
@Api(tags = "对外接口")
@RestController
@RequestMapping({"/anytxn/v2/api"})
public class AnyApiCommController {

    @Autowired
    private AdvancedServiceInvoker serviceInvoker;


    @PostMapping("/standard/api")
    public AnyTxnHttpResponse standardProcess(@RequestBody Map<String,Object> objectMap) throws Exception{
        String serverCode = (String)objectMap.get("serverCode");
        AnyTxnHttpResponse txnHttpResponse = (AnyTxnHttpResponse) serviceInvoker.invoke(CardRepossLossService.class, serverCode+"Service", objectMap);
        return txnHttpResponse;
    }
}
