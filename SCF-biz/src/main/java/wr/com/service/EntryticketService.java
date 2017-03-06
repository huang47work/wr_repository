package wr.com.service;

import java.util.Date;
import java.util.List;

import wr.com.pojo.Entryticket;
import wr.com.pojo.SearchEntryticket;

/**
 * 接口需求说明
 * 入库单增加（）
 * 修改入库单
 * 删除入库单
 * 入库单条件搜索(包含模糊查询参数和非模糊参数)
 * 查看本账号所有入库单
 * */
public interface EntryticketService {
	
	public void addTicket(Entryticket entryticket);
	
	public void changeTicket(Entryticket entryticket);
	
	public void deleteTicket(String enterId);
	
	public List<Entryticket> selectByCondition(SearchEntryticket searchEntryticket);
	
	public List<Entryticket> selectByUserId(String userId);
	
	public List<Object> selectByEnterIds(String[] enterIds);
	
	public Entryticket selectByEnterId(String enterId);
}
