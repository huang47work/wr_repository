package wr.com.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wr.com.mapper.ButtonMapper;
import wr.com.mapper.MenuMapper;
import wr.com.mapper.PageMapper;
import wr.com.pojo.AssociateRoleButton;
import wr.com.pojo.AssociateRoleMenu;
import wr.com.pojo.AssociateRolePage;
import wr.com.pojo.Menu;
import wr.com.pojo.Page;
import wr.com.pojo.Role;
import wr.com.service.MenuService;
@Service
public class MenuServiceImpl implements MenuService{
	@Autowired
	private MenuMapper menuMapper;
	@Autowired
	private PageMapper pageMapper;
	@Autowired
	private ButtonMapper buttonMapper;
	//所有：生成菜单树，通过最上层父菜单来获得之下的所有子菜单
	public List<Menu> getMenuTree() {
		List<Menu> list = menuMapper.findChildMenu("0");
		recursion(list);
		return list;
	}
//	 所有：纵向解析树根据父菜单获得子菜单以及页面
	 public List<List> selectSonListByParent(String parentId){
		 Menu maxMenu = menuMapper.selectByPrimaryKey(parentId);
		 List<Menu> list = menuMapper.findChildMenu(parentId);
		 recursion(list);
		 System.out.println("树为!!!!!!!!!!!!!!!!"+list);
		 List<List<Menu>> listAdd = new ArrayList();
		 recursion1(list,listAdd);
		 System.out.println("分层后集合为!!!!!!!!!!!!!!!!"+listAdd);
		 List<List> listR = new ArrayList();
//		 List<Menu>  list1 = listAdd.get(listAdd.size()-1);
		 List<Menu>  list1 = getLowList(listAdd);
		 System.out.println("###################"+list1);
		 for (int i = 0; i < list1.size(); i++) {
					 List<Menu> list2 = new ArrayList<>();
					 List<Page> list3 = new ArrayList<>();
					 String Name = list1.get(i).getName();
					 String pId = list1.get(i).getParentId();
					 String pageId = list1.get(i).getPageId();
					 Page page = pageMapper.selectByPrimaryKey(pageId);
					 System.out.println("page为：……………………………………………………"+page);
//					 装入底层页面 名称
					 list3.add(page);			 
//					 装入最底层菜单
					 list2.add(list1.get(i));
					 addName(listAdd, list2, pId);
					 list2.add(maxMenu);
//					反排序
					 Collections.reverse(list2);
					 List list4 = new ArrayList<>();
					 list4.add(list2);
					 list4.add(list3);
					 listR.add(list4);
					 
		 }
		 return listR;
	 }
	//动态：获得特定角色树
	public List<Menu> getUserMenuTree(List<Role> rlist){
		List<Menu> list = menuMapper.findByRoleIds(rlist);
		System.out.println("%%%%%%%%%%%%%%%%"+list);
		List<Menu> listR = new ArrayList<Menu>();
		for (int i = 0; i < list.size(); i++) {
			Menu menu = list.get(i);
			if (menu.getParentId().equals("0")) {
				listR.add(menu);
			}
		}
		recursion2(listR,list);
		return listR;
	}
//	动态：获得一个角色对应的所有menuId
	 public List<String> getUserMenuIds(List<Role> rlist){
		 return menuMapper.getMenuIdsByRoleIds(rlist);
	 }
	public int addUserMenuPageButtonIds(Map map) {
		menuMapper.addRoleMenu((List<AssociateRoleMenu>)map.get("roleMenu"));
		buttonMapper.addRoleButton((List<AssociateRoleButton>)map.get("roleButton"));
		pageMapper.addRolePage((List<AssociateRolePage>)map.get("rolePage"));
		return 1;
	}
	public int delete(Map map){
		String roleId = (String)map.get("roleId");
		menuMapper.deleteRoleMenuByRoleId(roleId);
		buttonMapper.deleteRoleButtonByRoleId(roleId);
		pageMapper.deleteRolePageByRoleId(roleId);
		return 1;
	}
	public int updateUserMenuPageButtonIds(Map map) {
		String roleId = (String)map.get("roleId");
		menuMapper.deleteRoleMenuByRoleId(roleId);
		buttonMapper.deleteRoleButtonByRoleId(roleId);
		pageMapper.deleteRolePageByRoleId(roleId);
		addUserMenuPageButtonIds(map);
		return 1;
	}
//	 ------------------------------------private方法区----------------------------------
//		递归
		private void recursion2(List<Menu> listR,List<Menu> list){
			List<Menu> list1 = new ArrayList<Menu>();
			for (Menu menu:listR) {
				menu.setMenus(new ArrayList<Menu>());
				for(Menu menu1:list){
					if (menu.getMenuId().equals(menu1.getParentId())) {
						menu.getMenus().add(menu1);
						if (menu1.getIsLeaf()==1) {
							list1.add(menu1);
						}
					}
				}
			}
			if (!list1.equals(null)&&!list1.isEmpty()) {
				recursion2(list1,list);
			}
			list1.clear();
			return;
		}
//	 获得最底层元素
	 private List<Menu> getLowList(List<List<Menu>> listAdd){
		 List<Menu> list= new ArrayList<>();
		 for (int a = 0; a < listAdd.size(); a++) {
			 for(Menu menu:listAdd.get(a)){
				if (menu.getIsLeaf()==0) {    
					list.add(menu);
				} 
			 }
		 }
		 return list;
	 }
//	 获得某个元素上面所有元素 递归
	 private void addName(List<List<Menu>> listAdd,List<Menu> list2,String pId){
		 for (int a = 0; a < listAdd.size(); a++) {
			 for(Menu menu:listAdd.get(a)){
				if (menu.getMenuId().equals(pId)) {
					list2.add(menu);
					addName(listAdd,list2,menu.getParentName());
				} 
			 }
		 }
	 return;
	 }
	//递归
	private void recursion(List<Menu> list){
		for(Menu menu:list){
			if (menu.getIsLeaf()==1) {
				List list1 = menuMapper.findChildMenu(menu.getMenuId());
				menu.setMenus(list1);
				recursion(list1);
			}
		}
		return;
	}
	//递归1将树平行分层遍历
	private void recursion1(List<Menu> list,List listAdd){
		 List<Menu> listname = new ArrayList();
		 List<Menu> list1 = new ArrayList<>();
		 for(Menu menu:list){
			 listname.add(menu);
			 System.out.println(menu.getMenus());
			 if (menu.getMenus()!=null&&!menu.getMenus().isEmpty()) {
				 for(Menu menu1:menu.getMenus()){
					 list1.add(menu1);
				 }
			}
		 }
		 System.out.println("平行分层list1大小："+list1.size());
		 listAdd.add(listname);
		if (!list1.isEmpty()&&list1!=null) {
			recursion1(list1,listAdd);
		}
		return;
	}
	


	
}
	

