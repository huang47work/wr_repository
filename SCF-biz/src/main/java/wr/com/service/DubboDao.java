package wr.com.service;

import wr.com.pojo.User;

public interface DubboDao {
	public void dubboTest();
	
	public User getUserById(Integer id);
}
