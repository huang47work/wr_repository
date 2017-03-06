package wr.com.result;

/**
 * 错误码
 * 
 * @author 郭杰
 * @since Dec 14，2016
 * @version 1.0.1
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
	BLANK_INVOICE_NUM("-2001", "发票号不能为空！"), 
	BLANK_INVOICE_CODE("-2002", "发票码不能为空！"), 
	BLANK_STATUS_CODE("-2003", "发票状态错误！"), 
	BLANK_INVOICE_GROUP_NUM("-2004", "应收账款ID不能为空！"), 
	BLANK_ENTRY_ID("-2005", "入库单号为空！"),
	BLANK_INVOICE_APPLY_ID("-2006", "授信批复id为空！"),

	/****************************************************
	 * 用户相关错误码
	 ****************************************************/
	USER_NOT_LOGIN("-1302", "用户未登录，请先登录！"),

	/************************************************
	 * OrderExt相关错误码(业务级错误码)
	 ************************************************/
	ORDER_EXT_ID_EMPTY("-2301", "订单ID为空"), ORDER_EXT_NOT_EXIST("-2302", "不存在该订单"),

	/************************************************
	 * 接口安全验证失败(系统级错误码)
	 ************************************************/
	ERROR_SECURITY_CODE("-1", "接口安全认证失败！"),
	/************************************************
	 * 接口安全验证失败(系统级错误码)
	 ************************************************/
	BLANK_OBJECT("-1600", "对象为空！"),
	/************************************************
	 * 状态为空(系统级错误码)
	 ************************************************/
	BLANK_STATUS("-1601", "状态为空！"),
	/************************************************
	 * 查询结果为空(系统级错误码)
	 ************************************************/
	BLANK_RESULT("-1602", " 查询结果为空！"),
	/************************************************
	 * 状态为空(系统级错误码)
	 ************************************************/
	BLANK_ID("-1603", "ID为空！"),
	/************************************************
	 * 借款单号为空(系统级错误码)
	 ************************************************/
	BLANK_BORROW_ID("-1604", "借款单号为空！"),
	/************************************************
	 * 数组为空(系统级错误码)
	 ************************************************/
	BLANK_ARRAY("-1609", "参数为空！"),

	/************************************************
	 * 数据库操作相关(系统级成功码)
	 ************************************************/
	ADD_SUCCESS("-1801", "新增成功！"), 
	UPDATE_SUCCESS("-1802", "修改成功！"), 
	DELETE_SUCCESS("-1803", "删除成功！"),

	/************************************************
	 * 数据库操作相关(系统级错误码)
	 ************************************************/
	ADD_FAIL("-1605", "新增失败！"), 
	UPDATE_FAIL("-1606", "修改失败！"), 
	DELETE_FAIL("-1607", "删除失败！"),
	QUERY_FAIL("-1608", "查询失败！"),
	UPLOAD_FAIL("-1610", "附件上传失败！"),
	VERIFY_CODE_FAIL("-1611", "号码被占用！");

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
