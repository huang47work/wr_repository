package wr.com.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CookieUtils {

    public static final String UTF_8 = "UTF-8";

    private static final Logger logger = LoggerFactory.getLogger(CookieUtils.class);
//
//    public String getCookieValue(HttpServletRequest request, String cookieName){
//
//
//    }

//    public String setCookieValue(HttpServletResponse response, String cookieName, String cook){
//
//        Cookie cookie = new Cookie("cookiename","cookievalue");
//        cookie.setPath("/");
//        response.addCookie(cookie);
//        return null;
//
//    }

    public static void addCookie(HttpServletResponse response,String name,String value,int maxAge){
        Cookie cookie = new Cookie(name,value);
        cookie.setPath("/");
        if(maxAge>0){
            cookie.setMaxAge(maxAge);
        }
        response.addCookie(cookie);
    }

    /**
     *
     * @param s 需要编码的字符串
     * @param ens 编码类型
     * @return 被编码的字符串
     * @throws UnsupportedEncodingException
     */
    public static String decode(String s,String ens) throws UnsupportedEncodingException {
        s = URLDecoder.decode(s,ens);
        return s;
    }
}
