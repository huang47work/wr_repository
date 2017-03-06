package wr.com.mapper;

import org.springframework.stereotype.Component;

import wr.com.pojo.User;
@Component
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);
    
    User findByUserNameOrPhone(String userName);

    User selectByPrimaryKey(Integer id);
    
    User selectByUserId(String userId);
    
    User findByPhone(String phone);
    
    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}