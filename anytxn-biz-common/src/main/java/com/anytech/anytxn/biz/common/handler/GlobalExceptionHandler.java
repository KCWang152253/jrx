package com.anytech.anytxn.biz.common.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.anytech.anytxn.biz.common.constant.AnyTxnCommonRespCode;
import com.anytech.anytxn.biz.common.exception.AnyTxnException;
import com.anytech.anytxn.biz.common.web.AnyTxnHttpResponse;


/**
 * 全局异常处理器, 应用端在configuration的自定类中 集成基础类，扩展注解方法
 * @author
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * error级别异常拦截
	 */
	@ResponseBody
	@ExceptionHandler(value = Throwable.class)
	public AnyTxnHttpResponse<?> txnExceptionHandler(Throwable e) {
//		logger.error("service exception ,throwable:{}",e.getMessage(),e);
		logger.error("service exception ,throwable:{}",e);
		return AnyTxnHttpResponse.fail(AnyTxnCommonRespCode.UNKONWN_ERR.getCode(),
				AnyTxnCommonRespCode.UNKONWN_ERR.getMsg(),"data exception ");
	}

	/**
	 * 对不能单独处理的异常进行统一处理
	 */
	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public AnyTxnHttpResponse<?> txnExceptionHandler(Exception e) {
//		logger.error("service exception ,Exception:{}:",e.getMessage(),e);
		logger.error("service exception ,Exception:{}:",e);
		return AnyTxnHttpResponse.fail(AnyTxnCommonRespCode.UNKONWN_ERR.getCode(),
				AnyTxnCommonRespCode.UNKONWN_ERR.getMsg(),"data exception ");
	}
	/**
	 * 对不能单独处理的异常进行统一处理
	 */
	@ResponseBody
	@ExceptionHandler(value = RuntimeException.class)
	public AnyTxnHttpResponse<?> runTimeExceptionHandler(RuntimeException e) {
//		logger.error("service exception {},Exception:",e.getMessage(),e);
		logger.error("service exception {},Exception:",e);
		if (e instanceof AnyTxnException) {
			AnyTxnException ae = (AnyTxnException)e;
			return AnyTxnHttpResponse.fail(ae.getErrCode(), ae.getErrMsg(), ae.getErrDetail());
		}
		return AnyTxnHttpResponse.fail(AnyTxnCommonRespCode.UNKONWN_ERR.getCode(),
				AnyTxnCommonRespCode.UNKONWN_ERR.getMsg(),"data exception ");
	}

	/**
	 * 对业务异常进行处理，自定义的业务异常可以打印日志
	 */
	@ResponseBody
	@ExceptionHandler(value = AnyTxnException.class)
	public AnyTxnHttpResponse<?> txnExceptionHandler(AnyTxnException e){
		logger.error("data error ",e);
		return AnyTxnHttpResponse.fail(e.getErrCode(),e.getErrMsg(),e.getErrDetail());
	}

}