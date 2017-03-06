package wr.com.mapper;

import java.util.List;

import wr.com.pojo.FinancialProducts;

public interface FinancialProductsMapper {
    int deleteByPrimaryKey(String pid);

    int insert(FinancialProducts record);

    int insertSelective(FinancialProducts record);
    
    List<FinancialProducts> findAll();
    
    FinancialProducts selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(FinancialProducts record);

    int updateByPrimaryKey(FinancialProducts record);
}