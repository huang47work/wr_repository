//package wr.com.utils;
//
//import wr.com.utils.HttpSender;
//public class HttpSenderTest {
//	public static void main(String[] args) {
//		String url = "http://sapi.253.com/msg/";// 应用地址
//		String account = "jk-cs-xs02";// 账号
//		String pswd = "Gi4xzd7C0";// 密码
//		String mobile = "18335835137";// 手机号码，多个号码使用","分割
//		String msg = "...";// 短信内容
//		boolean needstatus = true;// 是否需要状态报告，需要true，不需要false
//		String extno = null;// 扩展码
//
//		try {
//			String returnString = HttpSender.batchSend(url, account, pswd, mobile, msg, needstatus, extno);
//			System.out.println(returnString);
//			// TODO 处理返回值,参见HTTP协议文档
//		} catch (Exception e) {
//			// TODO 处理异常
//			e.printStackTrace();
//		}
//	}
//}
