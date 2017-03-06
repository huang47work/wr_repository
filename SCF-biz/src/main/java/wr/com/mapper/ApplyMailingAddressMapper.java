package wr.com.mapper;

import wr.com.pojo.ApplyMailingAddress;

public interface ApplyMailingAddressMapper {
   
	int deleteByPrimaryKey(String applyMid);

    int insertSelective(ApplyMailingAddress record);

    ApplyMailingAddress selectByPrimaryKey(String applyMid);

    int updateByPrimaryKeySelective(ApplyMailingAddress record);

}