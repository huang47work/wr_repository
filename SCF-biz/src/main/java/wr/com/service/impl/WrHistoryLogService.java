package wr.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseService;

import wr.com.mapper.WrHistoryLogMapper;
import wr.com.mapper.base.BaseDao;

/**
 * 操作日志
 * 
 * @author guojie
 * @since Dec 6,2016
 * @version 1.0.1
 *
 */
@Service("WrHistoryLogService")
public class WrHistoryLogService<T> extends BaseService<T> {

	@Autowired
	WrHistoryLogMapper<T> wrHistoryLogMapper;

	@Override
	public BaseDao<T> getDao() {
		return this.wrHistoryLogMapper;
	}
}
