package wr.com.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wr.com.mapper.ApplyMailingAddressMapper;
import wr.com.mapper.FinancialProductsInstaceMapper;
import wr.com.mapper.LoanApplicationMapper;
import wr.com.mapper.UserMapper;
import wr.com.mapper.WrBBInfoMapper;
import wr.com.mapper.WrInvoiceApplyMapper;
import wr.com.pojo.ApplyMailingAddress;
import wr.com.pojo.FinancialProductsInstace;
import wr.com.pojo.LoanApplication;
import wr.com.pojo.WrInvoiceApply;
import wr.com.pojo.BBInfo.WrBBInfoVO;
import wr.com.result.Constants;
import wr.com.service.LoanApplicationService;
import wr.com.utils.SendMsg;

@Service
public class LoanApplicationServiceImpl implements LoanApplicationService {
	@Autowired
	private LoanApplicationMapper loanApplicationMapper;

	@Autowired
	private WrBBInfoMapper<WrBBInfoVO> bbInfoMapper;

	@Autowired
	private ApplyMailingAddressMapper applyMailingAddressMapper;

	@Autowired
	private FinancialProductsInstaceMapper financialProductsInstaceMapper;

	@Autowired
	WrInvoiceApplyMapper<WrInvoiceApply> wrInvoiceApplyMapper;

	@Autowired
	UserMapper userMapper;

	public void addLoanApplication(LoanApplication loanApplication) {
		loanApplicationMapper.insertSelective(loanApplication);
		// 修改应收账款状态，已经申请借款： 4
		WrInvoiceApply invoiceApply = new WrInvoiceApply();
		invoiceApply.setStatus(Constants.NUMBER_4);
		invoiceApply.setInvoiceGroupNum((loanApplication.getGroupId()));
		int result = wrInvoiceApplyMapper.updateById(invoiceApply);
		if (result != Constants.NUMBER_1) {
			throw new RuntimeException("生成借款申请单异常！");
		}
		return;
	}

	public void deleteLoanApplication(String aid) {
		loanApplicationMapper.deleteByPrimaryKey(aid);
		return;
	}

	public void updateStatus(LoanApplication loanApplication) {
		loanApplicationMapper.updateByPrimaryKeySelective(loanApplication);
		return;
	}

	public List<LoanApplication> findByNearDate(Map map) {
		return loanApplicationMapper.findByNearDate(map);
	}

	public List<LoanApplication> findByLoanApplicationServiceStatus(Map map) {
		return loanApplicationMapper.findByLoanApplicationServiceStatus(map);
	}

	public List<LoanApplication> findByName(Map map) {
		return loanApplicationMapper.findByName(map);
	}

	@Override
	public LoanApplication findByKey(String aid) {
		return loanApplicationMapper.findByKey(aid);
	}

	@Override
	public void financialUpdateStatus(LoanApplication loanApplication, ApplyMailingAddress applyMailingAddress,
			WrBBInfoVO wrBBInfoVO, FinancialProductsInstace financialProductsInstace) throws RuntimeException {
		if (loanApplication.getTicketServiceStatus().equals("40")) {
			String groupId = loanApplicationMapper.findByKey(loanApplication.getAid()).getGroupId();
			WrInvoiceApply wrInvoiceApply = new WrInvoiceApply();
			wrInvoiceApply.setInvoiceGroupNum(groupId);
			wrInvoiceApply.setStatus(Constants.NUMBER_2);
			wrInvoiceApplyMapper.updateById(wrInvoiceApply);
		}
		System.out.println("最终金融产品数据" + financialProductsInstace);
		loanApplicationMapper.updateByPrimaryKeySelective(loanApplication);
		applyMailingAddressMapper.insertSelective(applyMailingAddress);
		bbInfoMapper.insert(wrBBInfoVO);
		financialProductsInstaceMapper.insertSelective(financialProductsInstace);
		// 发送短信给借款人
		SendMsg.sendMsg(userMapper.selectByUserId(financialProductsInstace.getBorrowUserId()).getPhone(),
				wrBBInfoVO.getOrderId(), financialProductsInstace.getBorrowingBalance(),
				loanApplication.getFinanceUserName(),
				userMapper.selectByUserId(loanApplication.getFinanceUserId()).getPhone());
		return;
	}

}
