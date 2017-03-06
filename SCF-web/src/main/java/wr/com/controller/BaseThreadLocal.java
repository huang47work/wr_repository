/*package wr.com.controller;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

*//**
 * 基础控制类 ，持续扩展
 * 
 * @author limanman
 * @date 2015年11月24日
 *//*
public class BaseThreadLocal {
	private Logger logger = LoggerFactory.getLogger(getClass());
	*//**
	 * ThreadLocal确保高并发下每个请求的request，response都是独立的
	 *//*
	private static ThreadLocal<ServletRequest> currentRequest = new ThreadLocal<ServletRequest>();
	private static ThreadLocal<ServletResponse> currentResponse = new ThreadLocal<ServletResponse>();
	private static ThreadLocal<CommonParams> currentParams = new ThreadLocal<CommonParams>();

	*//**
	 * 线程安全初始化reque，respose对象
	 * 
	 * @param request
	 * @param response
	 * @date 2015年11月24日
	 * @author 漂泊者及其影子
	 *//*
	@ModelAttribute
	public void initReqAndRep(HttpServletRequest request, HttpServletResponse response) {
		currentRequest.set(request);
		currentResponse.set(response);
	}

	@ModelAttribute
	public void initCommonParams(String para) {
		if (StringUtils.isNotEmpty(para)) {
			try {
				CommonParams commonParams = JsonUtil.formJson(para, CommonParams.class);
				currentParams.set(commonParams);
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				logger.info(gson.toJson(commonParams));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	*//**
	 * 获取app传递的params参数
	 * 
	 * @return
	 * @date 2015年11月24日
	 * @author 漂泊者及其影子
	 *//*
	public CommonParams getParams() {
		return currentParams.get();
	}

	*//**
	 * 线程安全
	 * 
	 * @return
	 * @date 2015年11月24日
	 * @author 漂泊者及其影子
	 *//*
	public HttpServletRequest request() {
		return (HttpServletRequest) currentRequest.get();
	}

	*//**
	 * 线程安全
	 * 
	 * @return
	 * @date 2015年11月24日
	 * @author 漂泊者及其影子
	 *//*
	public HttpServletResponse response() {
		return (HttpServletResponse) currentResponse.get();
	}

	public static final String SUCESS = "success";

	// jsp 返回
	public static final String FAILURE = "failure";

	// app端返回
	public static final String ERROR = "error";

	// 返回json
	public static final String JSON_VIEW = "json/json";

	// ------------------------------------------------------------------constants

	*//**
	 * 偷下懒
	 * 
	 * @param mv
	 * @return
	 * @date 2015年11月18日
	 * @author 佚名
	 *//*
	public ModelAndView modelAndView(ModelAndView mv) {
		if (mv.getModel().get("state") == null) {
			mv.noticeSuccess();
		}

		if (!mv.hasView()) {
			mv.setView(new MappingJacksonJsonView());
		}
		return mv;

	}

	*//**
	 * 跳转到到登录页面(jsp接口调用)
	 * 
	 * @return
	 * @date 2015年11月18日
	 * @author 佚名
	 *//*
	public ModelAndView toLoginView() {
		return new ModelAndView("leaguer/loginNew");
	}

}
*/