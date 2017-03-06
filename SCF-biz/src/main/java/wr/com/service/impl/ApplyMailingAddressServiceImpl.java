package wr.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wr.com.mapper.ApplyMailingAddressMapper;
import wr.com.pojo.ApplyMailingAddress;
import wr.com.service.ApplyMailingAddressService;
@Service
public class ApplyMailingAddressServiceImpl implements ApplyMailingAddressService {
	@Autowired
	ApplyMailingAddressMapper mailingAddressMapper;
	public void deleteByPrimaryKey(String applyMid) {
			mailingAddressMapper.deleteByPrimaryKey(applyMid);
	}

	public void insertSelective(ApplyMailingAddress record) {
		mailingAddressMapper.insertSelective(record);
	}

	public ApplyMailingAddress selectByPrimaryKey(String applyMid) {
		return mailingAddressMapper.selectByPrimaryKey(applyMid);
	}

	public void updateByPrimaryKeySelective(ApplyMailingAddress record) {
		mailingAddressMapper.updateByPrimaryKeySelective(record);
		return ;
	}

}
