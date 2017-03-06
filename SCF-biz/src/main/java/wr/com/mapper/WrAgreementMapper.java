package wr.com.mapper;

import wr.com.mapper.base.BaseDao;
import wr.com.pojo.WrAgreement;

public interface WrAgreementMapper<T> extends BaseDao<T>{
    int deleteByPrimaryKey(Integer id);

    int insert(WrAgreement record);

    int insertSelective(WrAgreement record);

    public abstract T selectByFName(Object fName);

    int updateByPrimaryKeySelective(WrAgreement record);

    int updateByPrimaryKey(WrAgreement record);
}