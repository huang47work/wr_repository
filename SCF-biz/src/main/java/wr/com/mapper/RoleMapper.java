package wr.com.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import wr.com.pojo.Role;

@Component
public interface RoleMapper {
    int deleteByPrimaryKey(String roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String roleId);

    List<Role> selectByUserId(String userId);
    
    List<Role> selectRolePackgeByUserId(String userId);
    
    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
}