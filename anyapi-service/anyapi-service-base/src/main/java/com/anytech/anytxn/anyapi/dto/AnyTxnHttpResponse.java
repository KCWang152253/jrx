package com.anytech.anytxn.anyapi.dto;

//import lombok.Data;
//import org.apache.commons.lang3.builder.ToStringBuilder;
//import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 通用的http请求返回结果封装.
 * 由于此类为公用类,无法使用工程内定义的枚举对象作为入参,这里只能使用字符串类型参数
 * @author fhp
 *
 */
//@Data
public class AnyTxnHttpResponse<T> {
	/**
	 * 响应码
	 */
    private String code;
    /**
     * 通用显示信息
     */
    private String message;
    /**
     * 具体错误信息(通常会显示上下文信息,如参数)
     * 仅在返回错误时使用
     */
    private String detail;
    /**
     * 正常返回的数据
     */
    private T data;

    /**
     * 请求成功(适用于不需要返回具体数据,且前端不关注提示信息场景)
     * 如果操作完成后前端页面不关注提示信息,或者不需要提示信息可以使用
     * @return AnyTxnHttpResponse<T>
     */
    public static <T> AnyTxnHttpResponse<T> success() {
    	return new AnyTxnHttpResponse<>();
    }
    /**
     * 请求成功(适用于不需要返回具体数据,且前端会关注提示信息场景)
     * 操作完成,前端需要展示提示信息
     * @param <T>
     * @param detail 返回给下=前端的提示信息
     * @return
     */
    public static <T> AnyTxnHttpResponse<T> successDetail(String detail) {
    	AnyTxnHttpResponse<T> resp = new AnyTxnHttpResponse<>();
    	resp.setDetail(detail);
    	return resp;
    }
    /**
     * 请求成功(适用于需要返回具体数据的操作,且不关注提示信息)
     * 类似场景分页列表,点击上下页,直接返回页面数据即可
     * @param data 返回具体数据
     * @return AnyTxnHttpResponse
     */
    public static <T> AnyTxnHttpResponse<T> success(T data) {
        return new AnyTxnHttpResponse<>(data);
    }
    /**
     * 请求成功(适用于需要返回具体数据的操作,且关注提示信息)
     * @param data 返回具体数据
     * @param detail
     * @return AnyTxnHttpResponse
     */
    public static <T> AnyTxnHttpResponse<T> success(T data, String detail) {
    	AnyTxnHttpResponse<T> resp = new AnyTxnHttpResponse<>(data);
    	resp.setDetail(detail);
    	return resp;
    }
    /**
     * 请求发生异常(不需要返回具体上下文信息)
     * 此方法适用于该响应码是专用响应码,也就是为每个具体错误定义类对应具体响应码.
     * 通过响应码的msg就可以对错误进行准确描述
     * 一句话,人比较勤快,针对各种错误都定义了具体对应的响应码就用这个方法
     * @param code 响应码
     * @param msg 响应信息
     * @return AnyTxnHttpResponse
     */
    public static <T> AnyTxnHttpResponse<T> fail(String code, String msg) {
        return fail(code, msg, null);
    }
    /**
     * 请求发生异常(需要返回具体上下文信息,需要在抛出自定义异常时自己设置)
     * 此方法适用于该响应码会负责多种错误信息的处理,而相应码自身的msg不能对每种错误进行专有描述,这时候需要通过detail来弥补
     * 一句话,相对于上面不够勤快,想通过少量的响应码满足所有错误场景,那就需要通过这个方法设置detail来弥补
     * @param code 响应码
     * @param msg 响应信息
     * @param detail 在抛出自定义异常时自己设置的信息
     * @return AnyTxnHttpResponse
     */
    public static <T> AnyTxnHttpResponse<T> fail(String code, String msg, String detail) {
        return new AnyTxnHttpResponse<>(code, msg, detail);
    }

    private AnyTxnHttpResponse(String code, String message, String detail) {
        this.code = code;
        this.message = message;
        this.detail = detail;
        this.data = null;
    }

    private AnyTxnHttpResponse(T data) {
        this.code = "1000000000";
        this.message = "success";
        this.data = data;
    }

    public AnyTxnHttpResponse() {
    }

//    @Override
//    public String toString() {
//    	return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
//    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
