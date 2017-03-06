package wr.com.service;

import java.util.List;

import wr.com.pojo.Org;

public interface OrgService {
	
	public List<Org> getAllOrgByParentId();
	
	public List<Org> getOrgsByUserId();
	
	
}
