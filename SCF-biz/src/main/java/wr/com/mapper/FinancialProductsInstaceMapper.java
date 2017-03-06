package wr.com.mapper;

import org.springframework.stereotype.Service;

import wr.com.pojo.FinancialProductsInstace;

@Service
public interface FinancialProductsInstaceMapper {
    int deleteByPrimaryKey(String iid);

    int insert(FinancialProductsInstace record);

    int insertSelective(FinancialProductsInstace record);

    FinancialProductsInstace selectByPrimaryKey(String iid);

    int updateByPrimaryKeySelective(FinancialProductsInstace record);

    int updateByPrimaryKey(FinancialProductsInstace record);
}