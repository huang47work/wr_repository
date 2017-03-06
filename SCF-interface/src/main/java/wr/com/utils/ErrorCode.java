package wr.com.utils;

/**
 * 
* @Author Result Create on 12-01     
 * Copyright (c) 2016 by 郭杰. 
 *
 */

public enum ErrorCode {
    /************************************************
     * 登录注册相关错误码(业务级错误码)
     ************************************************/
    BLANK_MOBILE("-1000", "手机号码为空！"),
    BLANK_VERIFY_CODE("-1001", "图形验证码为空！"),
    INVALID_MOBILE("-1002", "无效手机号码！"),
    BLANK_PASSWORD("-1003", "密码不能为空！"),
    INVALID_PASSWORD("-1004", "密码长度至少6位数！"),
    EXPIRE_VERIFY_CODE("-1005", "图形验证码已过期！"),
    ERROR_VERIFY_CODE("-1006", "图形验证码错误！"),
    BLANK_MESSAGE_VERIFY_CODE("-1007", "短信验证码为空！"),
    EXPIRE_MESSAGE_VERIFY_CODE("-1008", "短信验证码已过期！"),
    ERROR_MESSAGE_VERIFY_CODE("-1009", "短信验证码错误！"),
    EXIST_REGISTER_MOBILE_PHONE("-1010", "已经注册过的手机号码！"),
    BLANK_USER("-1011", "用户名或手机号为空！"),
    UN_REGISTER_USER("-1012", "未注册过用户名或手机号！"),
    ERROR_PASSWORD("-1013", "登录密码错误！"),

    /************************************************
     * 开票验证错误码
     ************************************************/
    BLANK_INVOICE_NUM("-2001","发票号不能为空！"),
    BLANK_INVOICE_CODE("-2002","发票码不能为空！"),
    BLANK_STATUS_CODE("-2003","发票状态错误！"),

    /****************************************************
     * 用户相关错误码
     ****************************************************/
    USER_FAIL_LOGIN("-1301","用户名或密码错误"),
    USER_NOT_LOGIN ("-1302","用户未登录，请先登录！"),
    USER_ALREADY_EXISTS("-1303","用户名已经存在"),
    USER_IDENTITUNUMBER_WRONG("-1304","短信验证码错误"),
    USER_MESSAGE_VERIFY_CODE("-1007", "短信验证码为空！"),
    /************************************************
     * OrderExt相关错误码(业务级错误码)
     ************************************************/
    ORDER_EXT_ID_EMPTY("-2301","订单ID为空"),
    ORDER_EXT_NOT_EXIST("-2302","不存在该订单"),

    /************************************************
     * 接口安全验证失败(系统级错误码)
     ************************************************/
    ERROR_SECURITY_CODE("-1", "接口安全认证失败！"),

    /************************************************
     * OrderExt相关错误码(业务级错误码)
     ************************************************/
	MESSAGE_NOT_NULL("1100","不能为空"),

	/************************************************
     * 业务操作错误码(业务级错误码)
     ************************************************/
	OPERATION_FAILED("3001","操作失败");
	
    private String code;
    private String message;

    private ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

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
}
