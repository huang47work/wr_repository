package wr.com.mapper;

import java.util.List;

import wr.com.pojo.AssociateRoleButton;
import wr.com.pojo.AssociateRolePage;
import wr.com.pojo.Page;
import wr.com.pojo.Role;

public interface PageMapper {
    int deleteByPrimaryKey(String pageId);
    
    int deleteRolePageByRoleId(String roleId);

    int addRolePage(List<AssociateRolePage> list);
    
    int insert(Page record);

    int insertSelective(Page record);

    Page selectByPrimaryKey(String pageId);
    
    List<Page> findAllPages();
    
    List<Page> findByRoleIds(List<Role> roleIds);
    
    List<String> getPageIdsByRoleIds(List<Role> roleIds);
    
    int updateByPrimaryKeySelective(Page record);

    int updateByPrimaryKey(Page record);
}