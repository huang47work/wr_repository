package wr.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseService;

import wr.com.mapper.WrUserFlowMapper;
import wr.com.mapper.base.BaseDao;

/**
 * 资金流水
 * 
 * @author guojie
 * @since Dec 8,2016
 * @version 1.0.1
 *
 */
@Service("WrUserFlowService")
public class WrUserFlowService<T> extends BaseService<T> {

	@Autowired
	WrUserFlowMapper<T> wrUserFlowMapper;

	@Override
	public BaseDao<T> getDao() {
		return this.wrUserFlowMapper;
	}

}
