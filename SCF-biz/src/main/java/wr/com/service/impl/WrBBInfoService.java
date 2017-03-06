package wr.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseService;

import wr.com.mapper.LoanApplicationMapper;
import wr.com.mapper.WrBBInfoMapper;
import wr.com.pojo.LoanApplication;

/**
 * 借款
 * 
 * @author guojie
 * @since Dec 6,2016
 * @version 1.0.1
 *
 */
@Service("WrBBInfoService")
public class WrBBInfoService<T> extends BaseService<T> {

	@Autowired
	WrBBInfoMapper<T> wrBBInfoMapper;

	@Override
	public WrBBInfoMapper<T> getDao() {
		return this.wrBBInfoMapper;
	}

	@Autowired
	LoanApplicationMapper loanApplicationMapper;

	/**
	 * 修改审批流程状态-网签协议
	 * 
	 * @param t
	 * @param loanApplication
	 * @return
	 * @throws Exception
	 */
	public int updateById(T t, LoanApplication loanApplication) throws Exception {
		// 修改审批流程状态
		loanApplicationMapper.updateByPrimaryKeySelective(loanApplication);
		//修改借款单状态为贷放款
		return getDao().updateBaseInfo(t);
	}
}
