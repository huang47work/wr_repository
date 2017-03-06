package wr.com.mapper;

import org.springframework.stereotype.Repository;

import wr.com.pojo.IdentityNum;
@Repository
public interface IdentityNumMapper {
    int deleteByMobile(String mobile);

    int insert(IdentityNum record);

    int insertSelective(IdentityNum record);

    IdentityNum selectByMobile(String mobile);

    int updateByPrimaryKeySelective(IdentityNum record);

    int updateByPrimaryKey(IdentityNum record);
}