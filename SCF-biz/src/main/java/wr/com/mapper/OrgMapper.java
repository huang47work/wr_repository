package wr.com.mapper;

import wr.com.pojo.Org;

public interface OrgMapper {
    int deleteByPrimaryKey(String orgId);

    int insert(Org record);

    int insertSelective(Org record);

    Org selectByPrimaryKey(String orgId);

    int updateByPrimaryKeySelective(Org record);

    int updateByPrimaryKey(Org record);
}