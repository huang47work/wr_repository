package wr.com.mapper;

import wr.com.mapper.base.BaseDao;
import wr.com.pojo.WrRBInfo;

public interface WrRBInfoMapper<T> extends BaseDao<T>{

    int insertSelective(WrRBInfo record);

}