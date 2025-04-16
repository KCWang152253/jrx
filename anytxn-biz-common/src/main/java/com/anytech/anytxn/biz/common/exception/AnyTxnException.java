/**
 * 
 */
package com.anytech.anytxn.biz.common.exception;

import com.anytech.anytxn.biz.common.constant.RespCodePrefix;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

/**
 * <p>自定义异常类，如果使用此类，请注意不同构造函数适用的场景
 * 场景一：由于业务逻辑，需要主动生成一个异常对象
 * 场景二：catch住程序运行过程中抛出的异常，转换为自定义异常
 * 
 * 此异常仅被各个工程自定义异常时继承,各个工程中不能直接使用
 * 各个工程中均需要定义自己工程内的异常,并继承AnyTxnException
 * 
 * @author fhp
 */
public class AnyTxnException extends RuntimeException {

	private static final long serialVersionUID = -8243161197901013355L;
	/**
	 * 自定义异常码
	 */
	protected String errCode;
	/**
	 * 自定义异常描述
	 */
	protected String errMsg;
	/**
	 * 自定义异常明细
	 * 提供具体数据时使用,便于后续分析查看问题
	 * 需要在抛出异常处通过对此属性进行赋值记录现场
	 */
	protected String errDetail;

    /**
     * 
     * <p>此方法适用于场景一：由于业务逻辑，需要主动生成一个异常对象，
     * 不适用于将原始异常转为自己封装异常场景
     * @param errCode 自定义枚举对象的响应码
     * @param errMsg 自定义枚举对象的响应信息
     * @param errDetail 自定义枚举对象的具体描述(比如上下文信息等,需要在生成异常时手动设置)
     */
	protected AnyTxnException(String errCode, String errMsg, String errDetail) {
		this(errCode, errMsg, errDetail, null);
	}

    /**
     * <p>此方法适用于场景二：catch住程序运行过程中抛出的异常，转换为自定义异常。
     * 代码中捕获了异常后转换为自己异常的处理均须调用此方法，必须保证原始异常的堆栈信息不丢失（将原始异常作为参数传入）
     * @param errCode 自定义枚举对象的响应码
     * @param errMsg 自定义枚举对象的响应信息
     * @param errDetail 自定义枚举对象的具体描述(比如上下文信息等,需要在生成异常时手动设置)
     * @param cause 原始异常
     */
	protected AnyTxnException(String errCode, String errMsg, String errDetail, Throwable cause) {
		//logger.error("异常",e)时，打印不到异常信息明细优化
        super(Optional.ofNullable(errCode).orElse("") + Optional.ofNullable(errMsg).orElse("")  + errDetail, cause);

		this.errCode = errCode;
		if (null != errDetail && !"".contentEquals(errDetail)) {
			this.errMsg = errMsg;
			if(null != errMsg){
                this.errDetail = errMsg + ":" + errDetail;
			}else{
				this.errDetail = errDetail;
			}
		} else {
			this.errMsg = errMsg;
			this.errDetail = errMsg;
		}
	}

	/**
	 * 按异常类型
	 * @param respCodePrefix
	 */
	public AnyTxnException(RespCodePrefix respCodePrefix){
		this(respCodePrefix.getCode(),respCodePrefix.getMsg(),respCodePrefix.getDetail());
	}

	public AnyTxnException(RespCodePrefix respCodePrefix,String detail){
		this(respCodePrefix.getCode(),respCodePrefix.getMsg(),detail);
	}
	
	public  String getErrCode() {
		return errCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public String getErrDetail() {
		return errDetail;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/**
	 * 输出堆栈信息到log err
	 *
	 * @param t
	 */
	public static String getTrace(Throwable t){
        StringWriter stringWriter =  new StringWriter();
        try( PrintWriter writer = new PrintWriter(stringWriter)) {
            t.printStackTrace(writer);
        }
        StringBuffer buffer = stringWriter.getBuffer();
        return buffer.toString();
	}
	
}
