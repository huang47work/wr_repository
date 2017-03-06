package wr.com.mapper;

import wr.com.mapper.base.BaseDao;

public interface WrBBInfoMapper<T> extends BaseDao<T>{

    public abstract int updateBaseInfo(T t);
   
}