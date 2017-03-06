package wr.com.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import wr.com.pojo.Advice;
@Repository
public interface AdviceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Advice record);

    int insertSelective(Advice record);

    Advice selectByPrimaryKey(String aid);
    
    List<Advice> findByStatus(String status);
    
    List<Advice> findAll();

    int updateByPrimaryKeySelective(Advice record);

    int updateByPrimaryKey(Advice record);
    
    void changeStatusById(String status,String aid);
}