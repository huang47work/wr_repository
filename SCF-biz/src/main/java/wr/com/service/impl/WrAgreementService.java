package wr.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseService;

import wr.com.mapper.WrAgreementMapper;
import wr.com.mapper.base.BaseDao;

/**
 * 授信批复
 * 
 * @author 郭杰
 * @since Dec 16,2016
 * @version 1.0.1
 * @param <T>
 *
 */
@Service("WrAgreementService")
public class WrAgreementService<T> extends BaseService<T> {

	@Autowired
	WrAgreementMapper<T> wrAgreementMapper;

	@Override
	public WrAgreementMapper<T> getDao() {
		return this.wrAgreementMapper;
	}
	/**
	 * 根据金融机构名字查询信息
	 * @param fName
	 * @return
	 */
	public T queryByFName(Object fName) {
		return getDao().selectByFName(fName);
	}
}
