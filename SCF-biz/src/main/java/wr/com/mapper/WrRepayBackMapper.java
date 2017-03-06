package wr.com.mapper;

import wr.com.mapper.base.BaseDao;
import wr.com.pojo.WrRepayBack;

public interface WrRepayBackMapper<T> extends BaseDao<T>{

    int insertSelective(WrRepayBack record);

    int updateByPrimaryKey(WrRepayBack record);
}