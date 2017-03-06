package wr.com.service;

import java.util.List;

import wr.com.pojo.Advice;
/**接口说明：
 * addAdvice：添加
 * findByStatus：通过状态查询
 * findAll：查询所有
 * changeStatusById：通过mid来改变状态
 * */
public interface AdviceService {
   
	void addAdvice(Advice record);
		
	List<Advice> findByStatus(String status);
	
	List<Advice> findAll();

	void changeStatusById(String mid, String status);
}
