package wr.com.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wr.com.mapper.RoleMapper;
import wr.com.pojo.Role;
import wr.com.service.RoleService;
@Service
public class RoleServiceImp implements RoleService {
	@Autowired
	RoleMapper roleMapper;
	
	//从角色组合角色中查出来然后排除重复元素
	public List<Role> getUserRoles(String userId) {
		List<Role> prlist = roleMapper.selectRolePackgeByUserId(userId);
		List<Role> rlist = roleMapper.selectByUserId(userId);
		if (prlist.isEmpty()) {
			return rlist;
		}else if (rlist.isEmpty()) {
			return prlist;
		}else{
		List<String> slist = new ArrayList<String>();
		for(Role role:rlist){
			slist.add(role.getRoleId());
		}
		Iterator  it = prlist.iterator();
		while(it.hasNext()){
			Role role = (Role) it.next();
			String roleId = role.getRoleId();
			if (slist.contains(roleId)) {
				it.remove();
			}
		}
//		prlist.removeAll(rlist);
		prlist.addAll(rlist);
		return prlist;
		}
	}

	@Override
	public int addUserRole(List<Map> maps) {
		// TODO Auto-generated method stub
		return 0;
	}

}
