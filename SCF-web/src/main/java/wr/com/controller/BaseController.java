package wr.com.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import wangrun.exception.BizException;
import wr.com.initBinder.DoubleEditor;
import wr.com.initBinder.FloatEditor;
import wr.com.initBinder.IntegerEditor;
import wr.com.initBinder.LongEditor;
import wr.com.pojo.BaseEntity;
import wr.com.pojo.User;
import wr.com.result.Constants;
import wr.com.utils.CookieUtils;
import wr.com.utils.DateUtils;
import wr.com.utils.HtmlUtil;

/**
 * BaseController
 * 
 * @author guojie
 * @since Dec 12,2016
 * @version 1.0.1
 *
 */
public abstract class BaseController {// extends PropertyEditorSupport{

	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 数据绑定
	 * 
	 * @param binder
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
		binder.registerCustomEditor(int.class, new IntegerEditor());
		binder.registerCustomEditor(long.class, new LongEditor());
		binder.registerCustomEditor(double.class, new DoubleEditor());
		binder.registerCustomEditor(float.class, new FloatEditor());
	}

	/**
	 * ThreadLocal确保高并发下每个请求的request，response都是独立的
	 */
	private static ThreadLocal<ServletRequest> currentRequest = new ThreadLocal<ServletRequest>();
	private static ThreadLocal<ServletResponse> currentResponse = new ThreadLocal<ServletResponse>();
	private static HttpSession session;
	/**
	 * 线程安全 初始化Request，Response对象
	 * 
	 * @author guojie
	 * @since Dec 13,2016
	 * @version 1.0.1
	 * @return
	 */
	@ModelAttribute
	public void initReqAndRep(HttpServletRequest request, HttpServletResponse response,HttpSession session1) {
		currentRequest.set(request);
		currentResponse.set(response);
		session = session1;
	}

	/**
	 * 线程安全
	 * 
	 * @author guojie
	 * @since Dec 13,2016
	 * @version 1.0.1
	 * @return
	 */
	public HttpServletRequest request() {
		return (HttpServletRequest) currentRequest.get();
	}

	/**
	 * 线程安全
	 * 
	 * @author guojie
	 * @since Dec 13,2016
	 * @version 1.0.1
	 * @return
	 */
	public HttpServletResponse response() {
		return (HttpServletResponse) currentResponse.get();
	}

	/**
	 * 获取IP地址
	 * 
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 所有ActionMap 统一从这里获取
	 * 
	 * @return
	 */
	public Map<String, Object> getRootMap() {
		Map<String, Object> rootMap = new HashMap<String, Object>();
		// 添加url到 Map中
		// rootMap.putAll(URLUtils.getUrlMap());
		return rootMap;
	}

//	public ModelAndView forword(String viewName, Map context) {
//		return new ModelAndView(viewName, context);
//	}

	public ModelAndView error(String errMsg) {
		return new ModelAndView("error");
	}

	/**
	 *
	 * 提示成功信息
	 *
	 * @param message
	 *
	 */
	public void sendSuccessMessage(HttpServletResponse response, String message) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constants.SUCCESS, true);
		result.put(Constants.MSG, message);
		HtmlUtil.writerJson(response, result);
	}

	/**
	 *
	 * 提示失败信息
	 *
	 * @param message
	 *
	 */
	public void sendFailureMessage(HttpServletResponse response, String message) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constants.SUCCESS, false);
		result.put(Constants.MSG, message);
		HtmlUtil.writerJson(response, result);
	}

	/**
	 * 获取Cookies
	 * 
	 * @author guojie
	 * @since Dec 13，2016
	 * @version 1.0.1
	 * @return
	 */
	protected Map<String, Cookie> getCookieMap() {
		Cookie[] cookies = request().getCookies();
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				String param = null;
				try {
					param = CookieUtils.decode(cookie.getValue(), CookieUtils.UTF_8);
					logger.info("params:" + param);
				} catch (UnsupportedEncodingException e) {
					throw new BizException("参数编码失败:" + cookie.getValue(), e);
				}
				cookie.setValue(param);
				cookie.setPath(request().getRequestURI());
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}

	protected void removeCookie(String cookieKey) {
		Cookie[] cookies = request().getCookies();
		Cookie toRemoveCookie = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(cookieKey)) {
					toRemoveCookie = cookie;
					break;
				}
			}
		}
		if (toRemoveCookie != null) {
			toRemoveCookie.setValue(null);
			toRemoveCookie.setMaxAge(0);
			response().addCookie(toRemoveCookie);
		}
	}

	private String getViewRequestParam(Map<String, Cookie> cookieMap, String key) {
		String param = null;
		param = request().getParameter(key);
		if (param == null) {
			Cookie cookie = cookieMap.get(key);
			if (cookie != null) {
				param = cookie.getValue();
			}
		}
		return param;
	}

	protected Object getViewRequestParam(Map<String, Cookie> cookieMap, String key, Object sample) {
		String strParam = this.getViewRequestParam(cookieMap, key);
		if (sample instanceof java.lang.Integer) {
			Integer param = null;
			try {
				param = Integer.parseInt(strParam);
			} catch (Exception e) {
			}
			return param;
		} else if (sample instanceof java.lang.Long) {
			Long param = null;
			try {
				param = Long.parseLong(strParam);
			} catch (Exception e) {
			}
			return param;
		} else if (sample instanceof java.lang.Double) {
			Double param = null;
			try {
				param = Double.parseDouble(strParam);
			} catch (Exception e) {
			}
			return param;
		} else if (sample instanceof java.math.BigDecimal) {
			BigDecimal param = null;
			try {
				param = new BigDecimal(strParam);
			} catch (Exception e) {
			}
			return param;
		} else if (sample instanceof String) {
			String param = strParam;
			return param;
		} else if (sample instanceof Date) {
			Date param = null;
			try {
				param = DateUtils.smartFormat(strParam);
			} catch (Exception e) {
			}
			return param;
		}
		return strParam;
	}

	/**
	 * insert初始化base參數
	 * 
	 * @param base
	 */
	public void init(BaseEntity base) {
		Date currentDate = new Date();
		base.setModifier(1);// default: 1000,admin
		base.setCreateTime(currentDate);
		base.setLastModifyTime(currentDate);
		base.setIsDeleted("N");
	}
	public Session  getSession(){
		 Subject currentUser = SecurityUtils.getSubject();
		 Session session = currentUser.getSession();
		return session;
	}
	public Object getSessionAtrribute(String key){
		return session.getAttribute(key);
	}
	public User getSessionUser(){
		
		return (User) getSession().getAttribute("user");
	}
	public void lookSession(){
	    Iterator<Object>   e   =   getSession().getAttributeKeys().iterator();   
	    while( e.hasNext())   {   
	        String sessionName=(String)e.next();   
	        System.out.println(sessionName+":"+session.getAttribute(sessionName));
	    }   
	}
}
