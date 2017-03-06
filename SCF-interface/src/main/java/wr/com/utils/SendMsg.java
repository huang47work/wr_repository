package wr.com.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

import com.esms.MessageData;
import com.esms.PostMsg;
import com.esms.common.entity.Account;
import com.esms.common.entity.GsmsResponse;
import com.esms.common.entity.MTPack;

/**
 * 手机短信接口
 * 
 * @author neoking
 *
 */
public class SendMsg {

	public static void sendMsg(String phoneNum, String borrowNum, double amount, String fName, String fMobile) {
		Account ac = new Account("stjt@stjt", "UZ934f8E");//
		PostMsg pm = new PostMsg(false, 5000, 2);
		pm.getCmHost().setHost("211.147.239.62", 9080);// 设置网关的IP和port，用于发送信息
		pm.getWsHost().setHost("211.147.239.62", 9070);// 设置网关的
														// IP和port，用于获取账号信息、上行、状态报告等等
		String content = "您编号为" + borrowNum + "的借款单已通过审核，放款金额为" + amount + "元（" + fName + "），请您及时查询，如有问题请致电" + fMobile
				+ "【网润平台】";// 短信内容

		try {
			doSendSms(pm, ac, phoneNum, content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Timer timer = new Timer(true);
		// 注意，javax.swing包中也有一个Timer类，如果import中用到swing包,要注意名字的冲突。

		TimerTask task = new TimerTask() {
			public void run() {
				// TODO 任务内容

			}
		};
		timer.schedule(task, 300000);
	}

	public static void doSendSms(PostMsg pm, Account ac, String phoneNum, String content) throws Exception {
		MTPack pack = new MTPack();
		pack.setBatchID(UUID.randomUUID());
		pack.setBatchName("短信测试批次");
		pack.setMsgType(MTPack.MsgType.SMS);
		pack.setBizType(0);
		pack.setDistinctFlag(false);
		ArrayList<MessageData> msgs = new ArrayList<MessageData>();
		/** 群发，多号码一内容 */
		pack.setSendType(MTPack.SendType.MASS);
		String contents = content;
		msgs.add(MessageData.getInstance(phoneNum, contents));
		pack.setMsgs(msgs);

		/**
		 * 设置模板编号(静态模板将以模板内容发送;
		 * 动态模板需要发送变量值，JSON格式：[{"key":"变量名1","value":"变量值1"},{"key":"变量名2",
		 * "value":"变量值2"}])
		 */
		// pack.setTemplateNo("test");

		GsmsResponse resp = pm.post(ac, pack);
		System.out.println(resp);
	}
}
