package wr.com.mapper;

import wr.com.mapper.base.BaseDao;
import wr.com.pojo.WrMenu;

public interface WrMenuMapper<T> extends BaseDao<T>{
    int deleteByPrimaryKey(Integer id);

    int insert(WrMenu record);

    int insertSelective(WrMenu record);

    WrMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WrMenu record);

    int updateByPrimaryKey(WrMenu record);
}