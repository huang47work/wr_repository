package wr.com.mapper;

import wr.com.mapper.base.BaseDao;
import wr.com.pojo.WrInvoice;

public interface WrInvoiceMapper<T> extends BaseDao<T>{

    int insertSelective(WrInvoice record);
    
}