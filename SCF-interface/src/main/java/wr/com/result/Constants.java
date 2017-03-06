package wr.com.result;

import java.util.Date;

import wr.com.pojo.BaseEntity;

public class Constants {

	/**
	 *  上传文件存储目录
	 */
	public static final String UPLOAD_DIRECTORY = "upload";
    
	/*********************************************
	 * 成功，失败,消息
	 *********************************************/
	public final static String SUCCESS = "success";

	public final static String MSG = "msg";
	
	public final static String FAIL = "fail";
	
	public final static String N = "N";

	/**
	 * ID
	 */
	public final static String ID = "id";

	/**
	 * 状态
	 */
	public final static String STATUS = "status";
	/**
	 * 创建时间
	 */
	public final static String CREATE_TIME = "createTime";
	/**
	 * 起始日
	 */
	public final static String START_DAY = "startDay";
	/**
	 * 结束日
	 */
	public final static String END_DAY = "endDay";

	/**
	 * 发票号码
	 */
	public final static String INVOICE_NUM = "invoiceNum";

	/**
	 * 发票代码
	 */
	public final static String INVOICE_CODE = "invoiceCode";

	/**
	 * 入库单号
	 */
	public final static String ENTRY_ID = "entryId";

	/**
	 * insert初始化base參數
	 * 
	 * @param base
	 */
	public static void init(BaseEntity base) {
		Date currentDate = new Date();
		base.setModifier(1000);// default: 1000,admin
		base.setCreateTime(currentDate);
		base.setLastModifyTime(currentDate);
		base.setIsDeleted(N);
	}

	/**
	 * 数字0
	 */
	public final static int NUMBER_0 = 0;

	/**
	 * 数字1
	 */
	public final static int NUMBER_1 = 1;

	/**
	 * 数字2
	 */
	public final static int NUMBER_2 = 2;

	/**
	 * 数字3
	 */
	public final static int NUMBER_3 = 3;

	/**
	 * 数字4
	 */
	public final static int NUMBER_4 = 4;

	/**
	 * 数字5
	 */
	public final static int NUMBER_5 = 5;
	
	/**
	 * 数字30
	 */
	public final static int NUMBER_30 = 30;
	
	/**
	 * 千分位
	 */
	public final static double QIAN_FEN_WEI = 0.001;
	
	/**
	 * 逗号
	 */
	public final static String DOU_HAO = ",";
	
	/**
	 * 字符串0
	 */
	public final static String STRING_ZERO = "0";
	
	/**
	 * 字符串1
	 */
	public final static String STRING_ONE = "1";
	
	/**
	 * 字符串2
	 */
	public final static String STRING_TWO = "2";
	
	/**
	 * 字符串3
	 */
	public final static String STRING_THREE = "3";
	/**
	 * 字符串4
	 */
	public final static String STRING_FOUR = "4";
	
	/**
	 * 字符串5
	 */
	public final static String STRING_FIVE = "5";
	
	/**
	 * 字符串6
	 */
	public final static String STRING_SIX = "6";
	
	
	/**
	 * 还款方式
	 */
	public final static String PAY_WAY_XXHB = "先息后本";
	public final static String PAY_WAY_DQHBFX = "到期还本付息";
	public final static String PAY_WAY_FQFK = "分期付款";
	

	/**
	 * 成功
	 */
	public final static String ADD_SUCCESS = "********* 新增成功！**********";
	public final static String UPDATE_SUCCESS = "********* 修改成功！**********";
	public final static String DELETE_SUCCESS = "********* 删除成功！**********";
	public final static String UPLOAD_SUCCESS = "********* 上传成功！**********";
	
	/**
	 * 手机短信
	 */
	
	public final static String CONTENT_APPLY_PAST = "您编号为XXXXXX的借款单已通过审核，放款金额为XXXXXX元（XX银行），请您及时查询，如有问题请致电400-XXX-XXXX【网润平台】";
	
	public final static String CONTENT_LOAN_SUCCESS = "编号为XXXXXX（姓名）的借款单正式放款，金额为XXXXXX元（XX银行），如有问题请致电400-XXX-XXXX【网润平台】";
}
