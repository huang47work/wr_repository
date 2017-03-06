package wr.com.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import wr.com.pojo.LoanApplication;

@Component
public interface LoanApplicationMapper {

	int updateByPrimaryKeySelective(LoanApplication record);

	int deleteByPrimaryKey(String aid);

    int insertSelective(LoanApplication record);

    List<LoanApplication> findByNearDate(Map map);
	
	List<LoanApplication> findByLoanApplicationServiceStatus(Map map);
	
	List<LoanApplication> findByName(Map map);
    
	LoanApplication findByKey(String aid);
}