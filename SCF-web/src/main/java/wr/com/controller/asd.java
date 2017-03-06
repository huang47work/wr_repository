package wr.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import wr.com.pojo.Menu;
import wr.com.pojo.Page;
import wr.com.pojo.Role;
import wr.com.service.impl.MenuServiceImpl;
import wr.com.service.impl.PageServiceImpl;
import wr.com.service.impl.RoleServiceImp;
import wr.com.utils.DateJsonValueProcessor;

@RequestMapping("/test")
@RestController
public class asd {
	@Autowired
	private  MenuServiceImpl goodsQualityMapper; 
	@Autowired
	private PageServiceImpl goodsQualityMapper1;
	@Autowired
	private RoleServiceImp goodsQualityMapper2;
	@RequestMapping("/a")
	public Object a(){
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		List<Role> rlist = goodsQualityMapper2.getUserRoles("1070000");
		  List<Menu> mlist = goodsQualityMapper.getMenuTree();
		  List<Menu> umlist =goodsQualityMapper.getUserMenuTree(rlist);
		  List<Page> plist = goodsQualityMapper1.getUserTreepage(rlist);
		  String a = JSONArray.fromObject(rlist,jsonConfig).toString();
		  String b = JSONArray.fromObject(mlist,jsonConfig).toString();
		  String ub = JSONArray.fromObject(umlist,jsonConfig).toString();
		  String c = JSONArray.fromObject(plist,jsonConfig).toString();
		  System.out.println(b);
		  return ub;
	}
	@RequestMapping("/b")
	public Object b(){
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		List<Role> rlist = goodsQualityMapper2.getUserRoles("1070000");
		  List<Menu> mlist = goodsQualityMapper.getMenuTree();
		  List<Menu> umlist =goodsQualityMapper.getUserMenuTree(rlist);
		  List<Page> plist = goodsQualityMapper1.getUserTreepage(rlist);
		  String a = JSONArray.fromObject(rlist,jsonConfig).toString();
		  String b = JSONArray.fromObject(mlist,jsonConfig).toString();
		  String ub = JSONArray.fromObject(umlist,jsonConfig).toString();
		  String c = JSONArray.fromObject(plist,jsonConfig).toString();
		  System.out.println(b);
		  return b;
	}
	@RequestMapping("/c")
	public Object c(String id){
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		List<Role> rlist = goodsQualityMapper2.getUserRoles("1070000");
		  List<Menu> mlist = goodsQualityMapper.getMenuTree();
		  List<Menu> umlist =goodsQualityMapper.getUserMenuTree(rlist);
		  List<Page> plist = goodsQualityMapper1.getUserTreepage(rlist);
		  List<List> slist= goodsQualityMapper.selectSonListByParent(id);
		  String a = JSONArray.fromObject(rlist,jsonConfig).toString();
		  String b = JSONArray.fromObject(mlist,jsonConfig).toString();
		  String ub = JSONArray.fromObject(umlist,jsonConfig).toString();
		  String c = JSONArray.fromObject(plist,jsonConfig).toString();
		  String s = JSONArray.fromObject(slist,jsonConfig).toString();
		  System.out.println(b);
		  return s;
	}
	
}
