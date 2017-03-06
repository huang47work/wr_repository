package wr.com.mapper;

import wr.com.mapper.base.BaseDao;
import wr.com.pojo.WrInvoiceApply;

public interface WrInvoiceApplyMapper<T> extends BaseDao<T> {

	int insertSelective(WrInvoiceApply record);

}