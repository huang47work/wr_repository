package wr.com.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import wr.com.pojo.AssociateRoleMenu;
import wr.com.pojo.Menu;
import wr.com.pojo.Role;
@Component
public interface MenuMapper {
    int deleteByPrimaryKey(String menuId);
    
    int deleteRoleMenuByRoleId(String roleId);
    int addRoleMenu(List<AssociateRoleMenu> list);
    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(String menuId);
    
    List<Menu> findHighByRoleIds(List<Role> roleIds);
    
    List<Menu> findByRoleIds(List<Role> roleIds);

    List<Menu> findChildMenu(String parentId);
    
    List<String> getMenuIdsByRoleIds(List<Role> roleIds);
    
    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
}