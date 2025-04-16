package com.anytech.anytxn.biz.common.exception;

import com.anytech.anytxn.biz.common.constant.AnyTxnCommonRespCode;
import com.anytech.anytxn.biz.common.constant.CommonRepDetail;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * <p>自定义异常类，如果使用此类，请注意不同构造函数适用的场景
 * 场景一：由于业务逻辑，需要主动生成一个异常对象
 * 场景二：catch住程序运行过程中抛出的异常，转换为自定义异常
 * 
 * 各个工程中均需要定义自己工程内的异常,并继承AnyTxnException
 *
 * @author zcli
 */
public class AnyTxnCommonException extends AnyTxnException {

	private static final long serialVersionUID = -8243161236301013355L;

    /**
     * <p>此方法适用于场景一：由于业务逻辑，需要主动生成一个异常对象，
     * 不适用于将原始异常转为自己封装异常场景
     * @param e	自定义枚举对象
     */
	public AnyTxnCommonException(AnyTxnCommonRespCode e) {
		this(e, null);
	}
    /**
     * <p>此方法适用于场景一：由于业务逻辑，需要主动生成一个异常对象，
     * 不适用于将原始异常转为自己封装异常场景
     * @param e	自定义枚举对象
     * @param detail 手动设置的具体信息,比如参数/上下文环境,方便排查问题
     */
	public AnyTxnCommonException(AnyTxnCommonRespCode e, CommonRepDetail detail, Object... params) {
		this(e, detail, null, params);
	}

	/**
     * <p>此方法适用于场景二：catch住程序运行过程中抛出的异常，转换为自定义异常。
     * 代码中捕获了异常后转换为自己异常的处理均须调用此方法，必须保证原始异常的堆栈信息不丢失（将原始异常作为参数传入）
     * @param e	自定义枚举对象
     * @param cause	原始异常
     */
	public AnyTxnCommonException(AnyTxnCommonRespCode e, Throwable cause) {
		this(e, null, cause);
	}
    /**
     * <p>此方法适用于场景二：catch住程序运行过程中抛出的异常，转换为自定义异常。
     * 代码中捕获了异常后转换为自己异常的处理均须调用此方法，必须保证原始异常的堆栈信息不丢失（将原始异常作为参数传入）
     * @param e	自定义枚举对象
     * @param detail 手动设置的具体信息,比如参数/上下文环境,方便排查问题
     * @param cause	原始异常
	 * @param params detail描述中的参数
     */
	public AnyTxnCommonException(AnyTxnCommonRespCode e, CommonRepDetail detail, Throwable cause, Object... params) {
        super(e.getCode(),e.getMsg(), detail(detail, params), cause);
	}

	private static String detail(CommonRepDetail detail, Object... params){
		if (detail == null ) {
			return null;
		}

		return params == null ? detail.message() : String.format(detail.message(), params);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
}
