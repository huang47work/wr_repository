package wr.com.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

/**
 * 自己建造了一个ModelAndView，只是为了让自己调用方法舒服些，不喜勿用
 * 
 * @author limanman
 * @date 2015年11月18日
 */
public class BaseModelAndView extends ModelAndView {
	private Logger logger = LoggerFactory.getLogger(getClass());

	public void setState(String state) {
		super.addObject("state", state);
	}

	public void put(String key, Object value) {
		addObject(key, value);
	}

	public BaseModelAndView() {

	}

	public BaseModelAndView(String viewName) {
		if ("json/json".equals(viewName)) {
			setView(new MappingJacksonJsonView());
		} else {
			logger.debug("即将跳转到页面：" + viewName);
			setViewName(viewName);
		}
	}

	public void noticeSuccess() {
		setState("success");
	}

	public void noticeFailure(String message) {
		this.setState("error");
		this.setMessage(message);
		if (StringUtils.isNotEmpty(message)) {
			logger.warn("invoke method failure:" + message);

		}
	}

	public void setState(String state, String message) {
		super.addObject("state", state);
		super.addObject("message", message);
	}

	public void setMessage(String message) {
		super.addObject("message", message);

	}
}