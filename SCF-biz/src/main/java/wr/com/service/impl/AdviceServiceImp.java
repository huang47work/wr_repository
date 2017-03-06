package wr.com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wr.com.mapper.AdviceMapper;
import wr.com.pojo.Advice;
import wr.com.service.AdviceService;
@Service("LoanApplicationService")
public class AdviceServiceImp implements AdviceService {
	@Autowired
	AdviceMapper adviceMapper;
	@Override
	public void addAdvice(Advice record) {
		adviceMapper.insert(record);
	}

	@Override
	public void changeStatusById(String mid,String status) {
		adviceMapper.changeStatusById(status, mid);
	}

	@Override
	public List<Advice> findByStatus(String status) {
		return adviceMapper.findByStatus(status);
	}
	
	@Override
	public List<Advice> findAll() {
		return adviceMapper.findAll();
	}



}
