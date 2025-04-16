package com.anytech.anytxn.biz.common.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 各工程controller继承此类,主要是设置uri前缀
 *
 * @author zcli
 */
@RestController
@RequestMapping(value="/anytxn/v2/api")
public class BizBaseController {
}
