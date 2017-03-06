package wr.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseService;

import wangrun.exception.BizException;
import wr.com.mapper.EntryticketMapper;
import wr.com.mapper.WrInvoiceApplyMapper;
import wr.com.mapper.base.BaseDao;
import wr.com.pojo.Entryticket;
import wr.com.result.Constants;

/**
 * 供应链款项
 * 
 * @author guojie
 * @since Dec 8,2016
 * @version 1.0.1
 *
 */
@Service("WrInvoiceApplyService")
public class WrInvoiceApplyService<T> extends BaseService<T> {

	@Autowired
	WrInvoiceApplyMapper<T> wrInvoiceApplyMapper;

	@Override
	public BaseDao<T> getDao() {
		return this.wrInvoiceApplyMapper;
	}

	@Autowired
	EntryticketMapper entryticketMapper;
	/**
	 * 生成应收账款
	 *   修改入库单状态为  已挂账 1
	 * @param t
	 * @param status
	 * @return
	 * @throws BizException
	 */
	public int addAndUpdate(T t,String status,String[] entryIds)throws BizException{
		//生成应收账款
		getDao().insert(t);
		Entryticket et = new Entryticket();
		et.setStatus(status);
		for (int i = 0; i < entryIds.length; i++) {
			et.setEnterId(entryIds[i]);
			entryticketMapper.updateByPrimaryKeySelective(et);
		System.out.println(et+"^&*^&(&*(#$%#$%####################################");
		}
		return Constants.NUMBER_1;
	}
//	@Cacheable(value = "userCache")
//	@Override
//	public List<WrInvoiceApply> getWrInvoiceApplyList(Map<String, Object> params) {
//		return wrInvoiceApplyMapper.select(params);
//	}
//
//	@Override
//	public Result<?> saveInvoiceApply(WrInvoiceApply wrInvoiceApply) {
//		return Result.wrapSuccessfulResult(wrInvoiceApplyMapper.insert(wrInvoiceApply));
//	}
//
//	@Override
//	public int deleteInvoiceApplyById(int id) {
//		return wrInvoiceApplyMapper.deleteById(id);
//	}
//
//	@Override
//	public int deleteInvoiceApply(Integer[] ids) {
//		return wrInvoiceApplyMapper.deleteByIds(ids);
//	}
//
//	@Override
//	public int updateById(WrInvoiceApply InvoiceApply) {
//		return wrInvoiceApplyMapper.updateById(InvoiceApply);
//	}
//
//	@Override
//	public WrInvoiceApply getWrInvoiceApplyById(int id) {
//		return wrInvoiceApplyMapper.selectById(id);
//	}

}
