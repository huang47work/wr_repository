package wr.com.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseService;

import wr.com.mapper.FinancialProductsInstaceMapper;
import wr.com.mapper.LoanApplicationMapper;
import wr.com.mapper.UserMapper;
import wr.com.mapper.WrBBInfoMapper;
import wr.com.mapper.WrRBInfoMapper;
import wr.com.mapper.WrUserFlowMapper;
import wr.com.mapper.base.BaseDao;
import wr.com.pojo.FinancialProductsInstace;
import wr.com.pojo.LoanApplication;
import wr.com.pojo.WrRBInfo;
import wr.com.pojo.WrRBInfoPage;
import wr.com.pojo.BBInfo.WrBBInfoPage;
import wr.com.pojo.BBInfo.WrBBInfoVO;
import wr.com.result.Constants;
import wr.com.utils.DateUtil;
import wr.com.utils.DateUtils;
import wr.com.utils.RandomUtils;
import wr.com.utils.SendMsg;
import wr.com.utils.UUid;

/**
 * 还款
 * 
 * @author guojie
 * @since Dec 8,2016
 * @version 1.0.1
 * @param <T>
 *
 */
@Service("WrRBInfoService")
public class WrRBInfoService<T> extends BaseService<T> {

	@Autowired
	WrRBInfoMapper<T> wrRBInfoMapper;

	@Autowired
	WrBBInfoMapper<T> wrBBInfoMapper;

	@Autowired
	WrUserFlowMapper<T> wrUserFlowMapper;

	@Autowired
	FinancialProductsInstaceMapper financialProductsInstaceMapper;

	@Autowired
	private LoanApplicationMapper loanApplicationMapper;
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public BaseDao<T> getDao() {
		// TODO Auto-generated method stub
		return this.wrRBInfoMapper;
	}

	/**
	 * 生成还款单,修改审核流程状态为5,已放款
	 * 
	 * @param info
	 * @param bbInfo
	 * @return
	 * @throws Exception
	 */
	public void saveRepayMentInfo(int id, WrRBInfo info, WrBBInfoVO bbInfo) throws Exception {
		int flow = 0 ;
		flow++;
		if (flow==10000) {
			flow = 0;
		}

		// 产品实例信息
		FinancialProductsInstace financialProductsInstace = financialProductsInstaceMapper
				.selectByPrimaryKey(bbInfo.getInstanceId());
		// 利率
		double productRate = financialProductsInstace.getProductInterestRate() * Constants.QIAN_FEN_WEI;
		// 本次借款金额
		double borrowAmount = financialProductsInstace.getBorrowingBalance();
		// 还款周期-天数
		double payDay = financialProductsInstace.getProductLimit();
		/**
		 * 還款方式
		 * 
		 * @see 1.先息后本
		 * @see 2.到期还本付息
		 * @see 3.分期付款
		 */
		String payMethod = financialProductsInstace.getRepaymentMethod();
		/**
		 * 还款方式
		 * 
		 * @author repayment_method
		 * 
		 * @see 先息后本:分两期， 1.一期金融机构手续费和利息， 2.二期全部本金
		 * 
		 * @see 到期还本付息：分一期，一次还清，利息加本金
		 * 
		 * @see 分期付款：三期，六期，十二期，十八期，二十四期
		 * 
		 */

		/**
		 * 起始日
		 */
		Date startDay = financialProductsInstace.getStarDate();
		// 一个月后的日期
		String oneMonth = DateUtils.increaseMonth(startDay);
		Date oneMonthDate = DateUtil.fromStringToDate("yyyy-MM-dd", oneMonth);
		/**
		 * 结束日
		 */
		// Date endDay = financialProductsInstace.getEndDate();
		/**
		 * 从放款日开始，下一个月的天数
		 * 
		 * @see 天数有可能是31天，也有可能是28天，所以不能按照天数直接算利息
		 */
		int betweenDays = DateUtils.daysBetween(startDay, oneMonthDate);

		/**
		 * 一个月利息
		 */
		double rateSum = borrowAmount * productRate;
		/**
		 * 日息
		 */
		double dayRate = (productRate / Constants.NUMBER_30);

		// 返回结果
		int result = Constants.NUMBER_0;

		// 先息后本
		if (StringUtils.isNotBlank(payMethod) && payMethod.equals(Constants.PAY_WAY_XXHB)) {
			/**
			 * @author neoking
			 * 
			 *         放款方式： 先扣除手续费和利息，利息作为第一笔还款单，本金作为第二笔还款单 实际放款 ：放款金额 = 总金额 -
			 *         手续费 - 利息
			 * 
			 * @see 利息计算方式，默认按照月利率，超过一个月但不足两个月的，足月的按照月利率计算，不足一月的按照日利率计算
			 * @see 整月算法：当月申请日，但下月当日前一天算一个月，算出这个月的天数，然后与产品授信期限作比较
			 * @see 日利率 = 月利率 / 30
			 */
			/********************************************************************************************************/

			// 利息
			double rateSums = new Double(0);
			// 本金
			double resultRate = 0;
			/**
			 * 
			 * 如果小于一个月,按照日息来算
			 */
			if (payDay < Constants.NUMBER_30) {
				// 放款单第一笔：利息
				resultRate = borrowAmount * (dayRate * payDay);
			}
			/**
			 * 如果大于一个月，计算出到下月的日期，然后时间差按照日息来计算
			 */
			else {
				// 超过一个月，除以30天，取余，乘以 * 月利率，然后加上剩余天数 * 乘以日利率
				resultRate = rateSum * ((int) payDay / Constants.NUMBER_30)
						+ (payDay - betweenDays) * dayRate * borrowAmount;
			}
			// 剩余本金，即为本次借款金额
			double restAmount = borrowAmount;
			for (int i = 0; i < Constants.NUMBER_2; i++) {
				/**
				 * 第一笔：利息，本金都是为0 第二笔：本金，利息都是为0
				 */
				if (Constants.NUMBER_0 == i) {
					// 应还本金
					info.setShouldRepayAmount(new BigDecimal(0));
					// 应还利息
					info.setShouldRepayRate(BigDecimal.valueOf(resultRate));
					// 实还本金
					info.setRealRepayAmount(new BigDecimal(0));
					// 实还利息
					info.setRealRepayRate(BigDecimal.valueOf(resultRate));
				} else {
					// 应还本金
					info.setShouldRepayAmount(BigDecimal.valueOf(restAmount));
					// 应还利息
					info.setShouldRepayRate(new BigDecimal(0));
					// 实还本金
					info.setRealRepayAmount(BigDecimal.valueOf(restAmount));
					// 实还利息
					info.setRealRepayRate(new BigDecimal(0));
				}
				// 生成还款单
				Constants.init(info);
				info.setStatus(Constants.NUMBER_0);
				info.setBorrowId(id);
				info.setRepayId(RandomUtils.generateRandomNum() + String.format("%03d", flow));
//						DateUtils.formatDate(new Date(), "yyyyMMdd") + UUid.getUuid().substring(0, 8).toUpperCase());
				result = wrRBInfoMapper.insert((T) info);
				if (result != Constants.NUMBER_1) {
					throw new RuntimeException("放款失败！");
				} else {
					// 发送短信给借款人
					SendMsg.sendMsg(userMapper.selectByUserId(financialProductsInstace.getBorrowUserId()).getPhone(),
							bbInfo.getOrderId(), 
							financialProductsInstace.getBorrowingBalance(),
							loanApplicationMapper.findByKey(bbInfo.getOrderNum()).getFinanceUserName(),
							userMapper.selectByUserId(loanApplicationMapper.findByKey(bbInfo.getOrderNum()).getFinanceUserId()).getPhone());
				}
			}

			/**
			 * 到期还本付息
			 */
		} else if (payMethod.equals(Constants.PAY_WAY_DQHBFX)) {
			// 利息+本金
			double amountSum = borrowAmount * productRate;
			// 应还本金
			info.setShouldRepayAmount(BigDecimal.valueOf(amountSum));
			// 应还利息
			info.setShouldRepayRate(BigDecimal.valueOf(rateSum));
			// 实还本金
			info.setRealRepayAmount(BigDecimal.valueOf(amountSum));
			// 实还利息
			info.setRealRepayRate(BigDecimal.valueOf(rateSum));

			// 生成还款单
			Constants.init(info);
			info.setStatus(Constants.NUMBER_0);
			info.setBorrowId(id);
			info.setRepayId(RandomUtils.generateRandomNum() + String.format("%03d", flow));
//					DateUtils.formatDate(new Date(), "yyMMdd") + UUid.getUuid().substring(0, 6).toUpperCase());
			result = wrRBInfoMapper.insert((T) info);
			if (result != Constants.NUMBER_1) {
				throw new RuntimeException("放款失败！");
			}else {
				// 发送短信给借款人
				SendMsg.sendMsg(userMapper.selectByUserId(financialProductsInstace.getBorrowUserId()).getPhone(),
						bbInfo.getOrderId(), 
						financialProductsInstace.getBorrowingBalance(),
						loanApplicationMapper.findByKey(bbInfo.getOrderNum()).getFinanceUserName(),
						userMapper.selectByUserId(loanApplicationMapper.findByKey(bbInfo.getOrderNum()).getFinanceUserId()).getPhone());
			}
		}
		// TODO
		// 修改审批流程状态
		LoanApplication loanApplication = new LoanApplication();
		loanApplication.setProcessStatus(Constants.STRING_FIVE);
		loanApplication.setAid(bbInfo.getOrderNum());
		result = loanApplicationMapper.updateByPrimaryKeySelective(loanApplication);
		// 修改借款单状态为 ：借款开始--2
		WrBBInfoVO bb = new WrBBInfoVO();
		bb.setStatus(Constants.STRING_TWO);
		bb.setId(id);
		result = wrBBInfoMapper.updateById((T) bb);
		if (result != Constants.NUMBER_1) {
			throw new RuntimeException("放款失败！");
		}

	}

	/**
	 * 修改借款单状态 和还款单状态和申请单状态
	 * 
	 * @param t
	 * @throws Exception
	 */
	public int updateStatus(WrRBInfo rbInfo) throws Exception {
		// 修改还款单状态
		int result = getDao().updateById((T) rbInfo);
		// 判断是否已经全部还款
		WrRBInfoPage apage = new WrRBInfoPage();
		apage.setBorrowId(rbInfo.getBorrowId());
		List<WrRBInfo> rbInfoList = (List<WrRBInfo>) wrRBInfoMapper.select(apage);
		int temp = 0;
		//查询对于申请单
		WrBBInfoPage bb = (WrBBInfoPage) wrBBInfoMapper.selectById(rbInfo.getBorrowId());
		LoanApplication loanA = loanApplicationMapper.findByKey(bb.getOrderNum());
		loanA.setTicketStatus("2");
		
		for (int i = 0; i < rbInfoList.size(); i++) {
			if (Constants.NUMBER_1 == rbInfoList.get(i).getStatus()) {
				temp++;
			}
		}
		if (temp == rbInfoList.size()) {
			WrBBInfoVO bbinfo = new WrBBInfoVO();
			bbinfo.setStatus(Constants.STRING_FOUR);
			bbinfo.setId(rbInfo.getBorrowId());
			loanApplicationMapper.updateByPrimaryKeySelective(loanA);
			return wrBBInfoMapper.updateById((T) bbinfo);
		}
		return 1;
	}
}
