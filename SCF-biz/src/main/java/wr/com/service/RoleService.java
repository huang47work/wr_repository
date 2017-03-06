package wr.com.service;

import java.util.List;
import java.util.Map;

public interface RoleService {
	public List getUserRoles(String userId);
	
	public int addUserRole(List<Map> maps);
}
