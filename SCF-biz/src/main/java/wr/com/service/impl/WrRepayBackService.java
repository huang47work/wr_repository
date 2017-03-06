package wr.com.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseService;

import wr.com.mapper.WrRepayBackMapper;
import wr.com.mapper.base.BaseDao;
/**
 * 还款
 * @author guojie
 * @since Dec 8,2016
 * @version 1.0.1
 *
 */
@Service("WrRepayBackService")
public class WrRepayBackService<T> extends BaseService<T>{

	@Autowired
	WrRepayBackMapper<T> wrRepayBackMapper;

	@Override
	public BaseDao<T> getDao() {
		// TODO Auto-generated method stub
		return this.wrRepayBackMapper;
	}
	
}
