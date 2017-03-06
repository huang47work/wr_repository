package wr.com.service;

import java.util.List;
import java.util.Map;

import wr.com.pojo.Menu;
import wr.com.pojo.Role;

public interface MenuService {
	public List<Menu> getMenuTree();
	
	public List<Menu> getUserMenuTree(List<Role> rlist);
	
	public List<List> selectSonListByParent(String parentId);
	
	public List<String> getUserMenuIds(List<Role> rlist);
	
	public int addUserMenuPageButtonIds(Map map);
	
	public int updateUserMenuPageButtonIds(Map map);
}
