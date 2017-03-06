package wr.com.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wr.com.mapper.ButtonMapper;
import wr.com.mapper.PageMapper;
import wr.com.pojo.Button;
import wr.com.pojo.Page;
import wr.com.pojo.Role;
import wr.com.service.PageService;
@Service
public class PageServiceImpl implements PageService {
	@Autowired
	PageMapper pageMapper;
	
	@Autowired
	ButtonMapper buttonMapper;
	
	public List<Page> getAllTreePage() {
		
		List<Page> plist = pageMapper.findAllPages();
		for(Page page:plist){
			page.setButtons(buttonMapper.findByPageId(page.getPageId()));
		}
		return plist;
	}
	public List<Page> getUserTreepage(List<Role> rlist){
		List<Page> plist = pageMapper.findByRoleIds(rlist);
		List<Button> list = buttonMapper.findByRoleIds(rlist);
		for(Page page:plist){
			page.setButtons(new ArrayList<Button>());
			for(Button button:list){
				if(button.getPageId().equals(page.getPageId())){
					page.getButtons().add(button);
				}
			}
		}
		return plist;
	}
	@Override
	public List<String> getUserPageIds(List<Role> rlist) {
		// TODO Auto-generated method stub
		return pageMapper.getPageIdsByRoleIds(rlist);
	}
	@Override
	public List<String> getUserButtonIds(List<Role> rlist) {
		// TODO Auto-generated method stub
		return buttonMapper.getButtonIdsByRoleIds(rlist);
	}
	
	public List<Button> getButtonsByPageId(String pageId) {
		// TODO Auto-generated method stub
		return buttonMapper.findByPageId(pageId);
	}
	
}
