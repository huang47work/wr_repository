package wr.com.service;

import java.util.List;
import java.util.Map;

import wr.com.pojo.Button;
import wr.com.pojo.Page;
import wr.com.pojo.Role;

public interface PageService {
	
	public 	List<Page> getAllTreePage();
	
	public List<Page> getUserTreepage(List<Role> rlist);
	
	public List<String> getUserPageIds(List<Role> rlist);
	
	public List<String> getUserButtonIds(List<Role> rlist);
	
	public List<Button> getButtonsByPageId(String pageId);
	
	
}
