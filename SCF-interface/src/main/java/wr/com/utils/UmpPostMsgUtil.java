package wr.com.utils;

import java.net.ConnectException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;


import com.esms.MOMsg;
import com.esms.MessageData;
import com.esms.PostMsg;
import com.esms.common.entity.Account;
import com.esms.common.entity.BusinessType;
import com.esms.common.entity.GsmsResponse;
import com.esms.common.entity.MTPack;
import com.esms.common.entity.MTPack.SendType;
import com.esms.common.entity.MTReport;
import com.esms.common.entity.MTResponse;
import com.esms.common.util.MediaUtil;

public class UmpPostMsgUtil {
	private static String identityNum;
	
	public static String getidentityNum(){
		System.out.println(identityNum);
		return identityNum;
	}
	public static void setidentityNum(String identityNum1){
		identityNum = identityNum1;
	}
	public void test(){
		try {
			sendMsg("14782096190"); //扩展接口范例
//			compatibility(); //兼容接口范例
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	//扩展接口范例
	public static void sendMsg(String phoneNum) {
		Account ac = new Account("wrxx@xmaz", "Oq3WGYU6");//
		PostMsg pm = new PostMsg(false, 5000, 2);
		pm.getCmHost().setHost("211.147.239.62", 9080);//设置网关的IP和port，用于发送信息
		pm.getWsHost().setHost("211.147.239.62", 9070);//设置网关的 IP和port，用于获取账号信息、上行、状态报告等等
		Random ne=new Random();//实例化一个random的对象ne
		int identy=ne.nextInt(9999-1000+1)+1000;//为变量赋随机值1000-9999
		setidentityNum(identy+"");
		String content = "您好，您的验证码是"+identy+"，请在5分钟之内完成。";// 短信内容
//		/**代理上网设置,如果需要*/
//		HostInfo proxyHost = new HostInfo("192.168.0.47", 1080);
//		proxyHost.setType(HostInfo.ConnectionType.SOCKET4);  	//设置连接类型
//		proxyHost.setUsername("004");						//设置用户名
//		proxyHost.setPassword("123");							//设置密码
//		pm.getProxy().setProxy(proxyHost);					//设置代理			
		
//		singlePackMsgId(pm,ac);//一个批次，一个扩展码
//		singleTicketMsgId(pm,ac);//每条短信，一个扩展码
//		template(pm,ac);//动态模板
//		staticTemplate(pm,ac);//静态模板,SDK只发没有参数的静态模板(有参数的模板无法替换参数)
		try {
			doSendSms(pm, ac,phoneNum,content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("发送失败");
			e.printStackTrace();
		}
		Timer timer = new Timer(true);   
		// 注意，javax.swing包中也有一个Timer类，如果import中用到swing包,要注意名字的冲突。   
		   
		TimerTask task = new TimerTask() {   
		    public void run() {   
		       setidentityNum("");   
		    }  
		}; 
		timer.schedule(task, 300000);
		//短信下行
//		doSendMms(pm, ac); //彩信下行
		
//		doGetAccountInfo(pm, ac); //获取账号信息
//		doModifyPwd(pm, ac); //修改密码
		
//		doFindResps(pm, ac); //查询提交报告
//		doFindReports(pm, ac); //查询状态报告
	
//		doGetMos(pm, ac); //获取上行信息
//		doGetResps(pm, ac); //获取提交报告
//		doGetReports(pm, ac); //获取状态报告
	}
	
	public static void singlePackMsgId(PostMsg pm,Account ac,String phoneNum,String content) throws Exception{
  		
  		MTPack pack = new MTPack();
  		pack.setBatchID(UUID.randomUUID());
  		pack.setBatchName("短信测试批次");
  		pack.setMsgType(MTPack.MsgType.SMS);
  		pack.setBizType(0); //业务类型id
  		pack.setDistinctFlag(false);
  		pack.setSendType(MTPack.SendType.MASS);//0群发 1 组发       
  		pack.setCustomNum("001");
  		
  		String contents = "短信群发送测试群发测试群发群发";
  		String phone = "13430258222";
  		String orgCode = "1234";
  		int msgFmt = 246;
  		ArrayList<MessageData> msgs = new ArrayList<MessageData>();
  		MessageData messageData1 = MessageData.getInstance(phone, contents, orgCode, msgFmt);
		MessageData messageData2 = MessageData.getInstance(phone, contents, orgCode, msgFmt);
		msgs.add(messageData1);
		msgs.add(messageData2);
		
		pack.setMsgs(msgs);
		GsmsResponse resp = pm.post(ac, pack);
		System.out.println(resp);
	}
	
	public static void singleTicketMsgId(PostMsg pm,Account ac) throws Exception{
  		
  		MTPack pack = new MTPack();
  		pack.setBatchID(UUID.randomUUID());
  		pack.setBatchName("短信测试批次");
  		pack.setMsgType(MTPack.MsgType.SMS);
  		pack.setBizType(0); //业务类型id
  		pack.setDistinctFlag(false);
  		pack.setSendType(MTPack.SendType.MASS);//0群发 1 组发       
  		
  		String content = "短信群发送测试群发测试群发群发";
  		String phone = "18335835137";
  		String orgCode = "1234";
  		int msgFmt = 246;
  		ArrayList<MessageData> msgs = new ArrayList<MessageData>();
  		MessageData messageData1 = MessageData.getInstance(phone,content,orgCode,msgFmt);
		messageData1.setCustomMsgID("004");
		MessageData messageData2 = MessageData.getInstance(phone,content,orgCode,msgFmt);
		messageData2.setCustomMsgID("005");
		msgs.add(messageData1);
		msgs.add(messageData2);
		
		pack.setMsgs(msgs);
		GsmsResponse resp = pm.post(ac, pack);
		System.out.println(resp);
	}
	
	/**
	 * 短信下发范例
	 * @param pm
	 * @param ac
	 */
	public static void doSendSms(PostMsg pm, Account ac,String phoneNum,String content) throws Exception{
		MTPack pack = new MTPack();
		pack.setBatchID(UUID.randomUUID());
		pack.setBatchName("短信测试批次");
		pack.setMsgType(MTPack.MsgType.SMS);
		pack.setBizType(0);
		pack.setDistinctFlag(false);
		ArrayList<MessageData> msgs = new ArrayList<MessageData>();
//		/** 单发，一号码一内容 */
//		msgs.add(new MessageData("13430258111", "短信单发测试"));
//		pack.setMsgs(msgs);
//		String orgCode = "1222";
//		int msgFmt = 8;
		/** 群发，多号码一内容 */
		pack.setSendType(MTPack.SendType.MASS);
		String contents = content;
		msgs.add(MessageData.getInstance(phoneNum, contents));
//		msgs.add(MessageData.getInstance("18502081562", content,orgCode,msgFmt));
//		msgs.add(MessageData.getInstance("13430258111", content,orgCode,msgFmt));
		pack.setMsgs(msgs);
		
//		/** 组发，多号码多内容 */
//		pack.setSendType(SendType.GROUP);
//		msgs.add(MessageData.getInstance("13430258111", content,orgCode,msgFmt));
//		msgs.add(MessageData.getInstance("13430258222", content,orgCode,msgFmt));
//		msgs.add(MessageData.getInstance("13430258333", content,orgCode,msgFmt));
//		pack.setMsgs(msgs);
		
		/** 设置模板编号(静态模板将以模板内容发送; 动态模板需要发送变量值，JSON格式：[{"key":"变量名1","value":"变量值1"},{"key":"变量名2","value":"变量值2"}]) */
		//pack.setTemplateNo("test");
		
		GsmsResponse resp = pm.post(ac, pack);
		System.out.println(resp);
	}
	
	private static void staticTemplate(PostMsg pm, Account ac) throws Exception {
		// 静态模板发送
		MTPack pack = new MTPack();
		pack.setBatchID(UUID.randomUUID());
		pack.setBatchName("短信测试批次");
		pack.setMsgType(MTPack.MsgType.SMS);
		pack.setBizType(0);
		pack.setDistinctFlag(false);
		ArrayList<MessageData> msgs = new ArrayList<MessageData>();

		pack.setSendType(MTPack.SendType.MASS);
		String content = "";
		String orgCode = "1234";
		int msgFmt = 8;
		msgs.add(MessageData.getInstance("13430258111", content,orgCode ,msgFmt));
		msgs.add(MessageData.getInstance("13430258222", content,orgCode ,msgFmt));
		msgs.add(MessageData.getInstance("13430258333", content,orgCode ,msgFmt));
		pack.setMsgs(msgs);
		
		pack.setTemplateNo("1");// "test"模板名称

		GsmsResponse resp = pm.post(ac, pack);
		System.out.println(resp);
	}
	
	private static void template(PostMsg pm, Account ac) throws Exception{
		MTPack pack = new MTPack();
		pack.setBatchID(UUID.randomUUID());
		pack.setBatchName("短信测试批次");
		pack.setMsgType(MTPack.MsgType.SMS);
		pack.setBizType(0); //业务类型根据自己的业务类型编号配置，如果不设置则应用默认业务类型
		pack.setDistinctFlag(false);
		ArrayList<MessageData> msgs = new ArrayList<MessageData>();
		pack.setSendType(SendType.GROUP);//如果是模板发送只能用组发方式发送
		String orgCode = "1234";
		int msgFmt = 8;

		/**
		 * 设置模板编号(静态模板将以模板内容发送;
		 * 动态模板需要发送变量值，JSON格式：[{"key":"变量名1","value":"变量值1"}
		 * ,{"key":"变量名2","value":"变量值2"}])
		 */
		//[{"key":"变量名1","value":"变量值1"},{"key":"变量名2","value":"变量值2"}]
		//假设模板有两个固定变量
//		msgs.add(MessageData.getInstance("13430258333", "[{\"key\":\"变量名1\",\"value\":\"张三\"}]",orgCode ,msgFmt));
//		msgs.add(MessageData.getInstance("13430258222","[{\"key\":\"变量名1\",\"value\":\"李四\"}]",orgCode ,msgFmt));
//		pack.setMsgs(msgs);
//		pack.setTemplateNo("test"); //"test"模板名称
		
		//假设模板由多个固定变量，这里以2个为例
		msgs.add(MessageData.getInstance("13430258333", "[{\"key\":\"变量名1\",\"value\":\"张三\"},{\"key\":\"变量名2\",\"value\":\"23\"}]",orgCode ,msgFmt));
		msgs.add(MessageData.getInstance("13430258333", "[{\"key\":\"变量名1\",\"value\":\"李四\"},{\"key\":\"变量名2\",\"value\":\"27\"}]",orgCode ,msgFmt));
		pack.setMsgs(msgs);
		pack.setTemplateNo("test2");//"test2"模板名称
		
		GsmsResponse resp = pm.post(ac, pack);
		System.out.println(resp);
		
	}


	
	/**
	 * 彩信下发范例
	 * @param pm
	 * @param ac
	 */
	public static void doSendMms(PostMsg pm, Account ac) throws Exception{
		MTPack pack = new MTPack();
		pack.setBatchID(UUID.randomUUID());
		pack.setBatchName("彩信测试批次");
		pack.setMsgType(MTPack.MsgType.MMS);
		pack.setBizType(1);
		pack.setDistinctFlag(false);
		ArrayList<MessageData> msgs = new ArrayList<MessageData>();

		String path = PostMsgTest.class.getClassLoader().getResource("mms_test").getPath();
		path = URLDecoder.decode(path, "utf-8");
		
		//设置公共彩信资源
		pack.setMedias(MediaUtil.getMediasFromFolder(path));
		
//		/** 单发，一号码一内容 */
//		msgs.add(new MessageData("13430258111", null));
//		pack.setMsgs(msgs);
		
		/** 群发，多号码一内容 */
		pack.setSendType(MTPack.SendType.MASS);
		msgs.add(MessageData.getInstance("13430258111", null,"0123"));
		msgs.add(MessageData.getInstance("13430258222", null,"0123"));
		msgs.add(MessageData.getInstance("13430258333", null,"0123"));
		pack.setMsgs(msgs);
		
//		/** 组发，多号码多内容 */
//		pack.setSendType(MTPack.SendType.GROUP);
//		//设置私有彩信资源
//		MessageData msg1 = MessageData.getInstance("13430258111", null);
//		msg1.setMedias(MediaUtil.getMediasFromFolder("F:/mms_test/msg1"));
//		msgs.add(msg1);
//		MessageData msg2 = MessageData.getInstance("13430258222", null);
//		msg2.setMedias(MediaUtil.getMediasFromFolder("F:/mms_test/msg2"));
//		msgs.add(msg2);
//		MessageData msg3 = MessageData.getInstance("13430258333", null);
//		msg3.setMedias(MediaUtil.getMediasFromFolder("F:/mms_test/msg3"));
//		msgs.add(msg3);
//		pack.setMsgs(msgs);
		
		/** 设置模板编号(静态模板将以模板内容发送; 动态模板需要发送变量值，JSON格式：[{"key":"变量名1","value":"变量值1"},{"key":"变量名2","value":"变量值2"}]) */
		//pack.setTemplateNo("test");
		
		GsmsResponse resp = pm.post(ac, pack);
		System.out.println(resp);
	}
	
	/**
	 * 获取账号信息
	 * @param pm
	 * @param account
	 * @throws Exception
	 */
	public static void doGetAccountInfo(PostMsg pm, Account ac) throws Exception{
		System.out.println(pm.getAccountInfo(ac));   //获取账号详细信息
		
		BusinessType[] bizTypes = pm.getBizTypes(ac); //获取账号绑定业务类型
		if(bizTypes != null){
			for(BusinessType bizType : bizTypes){
				System.out.println(bizType);
			}
		}
	}
	
	/**
	 * 获取上行信息
	 * @param pm
	 * @param account
	 * @throws Exception
	 */
	public static void doGetMos(PostMsg pm, Account ac) throws Exception{
		MOMsg[] mos = pm.getMOMsgs(ac, 100);
		if(mos != null){
			for(MOMsg mo : mos){
				System.out.println(mo);
			}
		}
	}
	
	/**
	 * 查询提交报告
	 * @param pm
	 * @param ac
	 * @throws Exception
	 */
	public static void doFindResps(PostMsg pm, Account ac) throws Exception{	
		UUID batchID = UUID.fromString("3e1f13f4-1677-41f1-b67d-702f2c01eafb"); //如果需要按批次ID来查询
		MTResponse[] foundMtResps = pm.findResps(ac, 1, batchID, null, 0);
		if(foundMtResps != null){
			for(MTResponse resp : foundMtResps){
				System.out.println(resp);
			}
		}
	}
	
	/**
	 * 获取提交报告
	 * @param pm
	 * @param ac
	 * @throws Exception
	 */
	public static void doGetResps(PostMsg pm, Account ac) throws Exception{	
		MTResponse[] mtResps = pm.getResps(ac, 100);
		if(mtResps != null){
			for(MTResponse resp : mtResps){
				System.out.println(resp);
			}
		}
	}
	
	/**
	 * 查询状态报告
	 * @param pm
	 * @param ac
	 * @throws Exception
	 */
	public static void doFindReports(PostMsg pm, Account ac) throws Exception {
		UUID batchID = UUID.fromString("f6d62b12-efe1-406d-9de0-9f3a4440f135"); //如果需要按批次ID来查询
		MTReport[] foundMtReports = pm.findReports(ac, 1, batchID, null, 0);
		if(foundMtReports != null){
			for(MTReport report : foundMtReports){
				System.out.println(report);
			}
		}
	}
	
	/**
	 * 获取状态报告
	 * @param pm
	 * @param ac
	 * @throws Exception
	 */
	public static void doGetReports(PostMsg pm, Account ac) throws Exception{	
		MTReport[] mtReports = pm.getReports(ac, 2);
		if(mtReports != null){
			for(MTReport report : mtReports){
				System.out.println(report);
			}
		}
	}
	
	/**
	 * 修改密码
	 * @param pm
	 * @param ac
	 * @throws Exception 
	 */
	public static void doModifyPwd(PostMsg pm, Account ac) throws Exception{	
		System.out.println(pm.modifyPassword(ac, "123456"));
	}
	
	//兼容接口范例
	public static void compatibility() throws ConnectException{
		
		//设置部分
		PostMsg pm = new PostMsg("admin", "123456");		//创建实例时，需输入用户名与密码
		pm.getCmHost().setHost("127.0.0.1", 8089);  	//设置网关的IP和port
		pm.getWsHost().setHost("127.0.0.1", 8088);  //设置WebService的 IP和port
//		pm.getProxy().setProxy(ProxyServer.PROXY_TYPE_SOCKS4, "192.168.0.47", 1080); //设置代理
//		
//		
		int resp = -1;
		/** 单发，一号码一内容 */
		resp = pm.post("13430258111", "短信单发测试", "");
//		
//		/** 群发，多号码一内容 */
//		resp = pm.post(new String[]{"13430258111", "13430258222", "13430258333"}, "短信群发测试", "");
//		
//		/** 组发，多号码多内容 */
//		MessageData[] msgs = new MessageData[3];
//		msgs[0] = new MessageData("13430258111", "短信组发测试111");
//		msgs[1] = new MessageData("13430258222", "短信组发测试222");
//		msgs[2] = new MessageData("13430258333", "短信组发测试333");
//		resp = pm.post(msgs, "");
		
		System.out.println("响应：" + resp);
		
		//修改密码
//		System.out.println(pm.modifyPassword("123456"));
			
//		System.out.println(pm.getConfigInfo()); 					//获取用户详细信息
//		MOMsg[] momsgs = pm.getMOMsg();								//获取上行信息
//		if(momsgs != null){
//			for(MOMsg momsg : momsgs){
//				System.out.println(momsg);
//			}
//		}
	}
}
