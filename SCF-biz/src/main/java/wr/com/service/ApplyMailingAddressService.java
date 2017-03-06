package wr.com.service;

import org.springframework.stereotype.Service;

import wr.com.pojo.ApplyMailingAddress;

@Service(value="ApplyMailingAddressService")
public interface ApplyMailingAddressService {
	
	void deleteByPrimaryKey(String applyMid);

    void insertSelective(ApplyMailingAddress record);
    	
    ApplyMailingAddress selectByPrimaryKey(String applyMid);

    void updateByPrimaryKeySelective(ApplyMailingAddress record);
}
