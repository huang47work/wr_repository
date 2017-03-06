package wr.com.service;

import java.util.List;
import java.util.Map;

import wr.com.pojo.WrInvoiceApply;
import wr.com.result.Result;

/**
 * 供应链款项
 * 
 * @author 郭杰
 * @since Dec 12,2016
 * @version 1.0.1
 */
public interface WrInvoiceApplyService {
	/**
	 * 根据Id查询供应链款项
	 * 
	 * @param id
	 * @return
	 */
	public WrInvoiceApply getWrInvoiceApplyById(int id);

	/**
	 * 查询供应链款项列表
	 * 
	 * @param InvoiceApply
	 * @return
	 */
	public List<WrInvoiceApply> getWrInvoiceApplyList(Map<String, Object> params);

	/**
	 * 新增
	 * 
	 * @param wrInvoiceApply
	 */
	public Result<?> saveInvoiceApply(WrInvoiceApply wrInvoiceApply);

	/**
	 * 根据Id删除供应链款项
	 * 
	 * @param id
	 * @return
	 */
	public int deleteInvoiceApplyById(int id);

	/**
	 * 批量删除供应链款项
	 * 
	 * @param ids
	 * @return
	 */
	public int deleteInvoiceApply(Integer[] ids);

	/**
	 * 修改供应链款项信息
	 * 
	 * @param id
	 * @return
	 */
	public int updateById(WrInvoiceApply InvoiceApply);
}