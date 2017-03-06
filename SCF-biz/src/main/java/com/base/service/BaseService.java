package com.base.service;

import java.util.List;

import com.wangrun.util.page.BasePage;

import wangrun.exception.BizException;
import wr.com.mapper.base.BaseDao;

/**
 * 基本Service
 * 
 * @author 郭杰
 * @since Dec 14,2016
 * @version 1.0.1
 *
 * @param <T>
 */
public abstract class BaseService<T> {

	public abstract BaseDao<T> getDao();

	public int add(T t) throws BizException {
		// 设置主键.字符类型采用UUID,数字类型采用自增
//		ClassReflectUtil.setIdKeyValue(t, "id", UUID.randomUUID().toString());
		return getDao().insert(t);
	}

	public void update(T t) throws BizException {
		getDao().updateById(t);
	}

	public int queryByCount(BasePage page) throws BizException {
		return getDao().selectCount(page);
	}

	/**
	 * 根据Id删除
	 * 
	 * @param id
	 * @return
	 */
	public int deleteById(int id) throws BizException{
		return getDao().deleteById(id);
	};

	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @return
	 */
	public int deleteByIds(Integer[] ids) throws BizException{
		return getDao().deleteByIds(ids);
	};

	/**
	 * 修改
	 * 
	 * @param id
	 * @return
	 */
	public int updateById(T t) throws BizException{
		return getDao().updateById(t);
	};

	/**
	 * 分页列表
	 * 
	 * @param page
	 * @return
	 * @throws BizException
	 */
	public List<T> queryByList(BasePage page) throws BizException {
		Integer rowCount = queryByCount(page);
		page.getPager().setRowCount(rowCount);
		return getDao().select(page);
	}

	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 * @throws BizException
	 */
	public T queryById(Object id) throws BizException {
		return getDao().selectById(id);
	} 
	
	public  List<T> queryByIds(Object[] ids){
		return getDao().selectByIds(ids);
	}
}
