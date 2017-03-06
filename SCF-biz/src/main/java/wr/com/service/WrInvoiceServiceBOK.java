package wr.com.service;

import java.util.List;
import java.util.Map;

import wr.com.pojo.WrInvoice;
import wr.com.result.Result;

/**
 * 发票
 * 
 * @author 郭杰
 * @since Dec 12,2016
 * @version 1.0.1
 */
public interface WrInvoiceServiceBOK {
	/**
	 * 根据Id查询发票
	 * 
	 * @param id
	 * @return
	 */
	public WrInvoice getWrInvoiceById(int id);

	/**
	 * 查询发票列表
	 * 
	 * @param Invoice
	 * @return
	 */
	public List<WrInvoice> getWrInvoiceList(Map<String, Object> params);

	/**
	 * 新增
	 * 
	 * @param wrInvoice
	 */
	public Result<?> saveInvoice(WrInvoice wrInvoice);

	/**
	 * 根据Id删除发票
	 * 
	 * @param id
	 * @return
	 */
	public int deleteInvoiceById(int id);

	/**
	 * 批量删除发票
	 * 
	 * @param ids
	 * @return
	 */
	public int deleteInvoice(Integer[] ids);

	/**
	 * 修改发票信息
	 * 
	 * @param id
	 * @return
	 */
	public int updateById(WrInvoice invoice);
}