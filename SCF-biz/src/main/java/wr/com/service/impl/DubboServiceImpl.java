package wr.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import wr.com.mapper.UserMapper;
import wr.com.pojo.User;
import wr.com.service.DubboDao;

@Service("DubboService")
public class DubboServiceImpl implements DubboDao{

	@Autowired
    public UserMapper userMapper;
	
	public void dubboTest() {
		System.out.println("web层引用service服务成功");
	}
	@Cacheable(value="userCache")
	public User getUserById(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

	
}
