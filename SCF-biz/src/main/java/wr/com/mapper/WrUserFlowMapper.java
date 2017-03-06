package wr.com.mapper;

import wr.com.mapper.base.BaseDao;
import wr.com.pojo.WrUserFlow;

public interface WrUserFlowMapper<T> extends BaseDao<T>{

    int insertSelective(WrUserFlow record);

    int updateByPrimaryKey(WrUserFlow record);
}