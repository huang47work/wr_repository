package wr.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseService;

import wr.com.mapper.WrMenuMapper;
import wr.com.mapper.base.BaseDao;

/**
 * 菜单
 * 
 * @author 郭杰
 * @since JAN 18,2017
 * @version 1.0.1
 * @param <T>
 *
 */
@Service("WrMenuService")
public class WrMenuService<T> extends BaseService<T> {

	@Autowired
	WrMenuMapper<T> wrMenuMapper;

	@Override
	public BaseDao<T> getDao() {
		return this.wrMenuMapper;
	}
}
