package wr.com.mapper;

import java.util.List;

import wr.com.pojo.AssociateRoleButton;
import wr.com.pojo.Button;
import wr.com.pojo.Role;

public interface ButtonMapper {
    int deleteByPrimaryKey(String bBid);
    
    int deleteRoleButtonByRoleId(String roleId);
    
    int addRoleButton(List<AssociateRoleButton> list);

    int insert(Button record);

    int insertSelective(Button record);

    Button selectByPrimaryKey(String bBid);
    
    List<Button> findByPageId(String pageId);
    
    List<Button> findByRoleIds(List<Role> record);
    
    List<String> getButtonIdsByRoleIds(List<Role> roleIds);
    
    int updateByPrimaryKeySelective(Button record);

    int updateByPrimaryKey(Button record);
}