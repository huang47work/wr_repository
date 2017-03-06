package wr.com.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import wr.com.pojo.ApplyMailingAddress;
import wr.com.pojo.FinancialProductsInstace;
import wr.com.pojo.LoanApplication;
import wr.com.pojo.BBInfo.WrBBInfoVO;

/**
 * 申请单接口说明
 * 增加申请单 
 * 删除申请单
 * 更改申请单状态----单据状态：0有效状态，1失效状态
 * 更改申请单业务状态----单据业务状态：0新建，1待确定，21已确认（平台或核心企业），31已发布 （平台或核心企业），41已反馈（金融机构）40已作废
 * 更改流程状态-----流程状态：0无：1借款申请，2(21平台，22核心企业，23平台核心企业都完成)平台与核心企业审核，3金融机构审核，4网签协议，5放款 
 * 根据时间查找申请单
 * 根据申请单据业务状态查找申请单
 * --------------------------
 * 三种状态之间关系《以单据业务状态为基准说明》：（单据业务状态，流程状态,单据状态）（0，0，0）（1,1，0）（21，2，0）（31,2，0）（41,3，1）（40,0，0）
 * */
@Service
public interface LoanApplicationService {
	
	public void addLoanApplication(LoanApplication loanApplication);
	
	public void deleteLoanApplication(String aid);

	public void updateStatus(LoanApplication loanApplication);
	
	public void financialUpdateStatus(LoanApplication loanApplication,ApplyMailingAddress applyMailingAddress,WrBBInfoVO wrBBInfoVO,FinancialProductsInstace financialProductsInstace); 
	
	List<LoanApplication> findByNearDate(Map map);
	
	List<LoanApplication> findByLoanApplicationServiceStatus(Map map);
	
	List<LoanApplication> findByName(Map map);
	
	LoanApplication findByKey(String aid);
}
