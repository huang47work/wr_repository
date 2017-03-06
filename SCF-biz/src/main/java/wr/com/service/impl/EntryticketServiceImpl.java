package wr.com.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wr.com.mapper.EntryticketMapper;
import wr.com.mapper.TicketGoodsMapper;
import wr.com.pojo.Entryticket;
import wr.com.pojo.SearchEntryticket;
import wr.com.pojo.TicketGoods;
import wr.com.service.EntryticketService;
import wr.com.utils.ArrColUtil;
@Service
public class EntryticketServiceImpl implements EntryticketService {
	
	@Autowired
	EntryticketMapper entryticketMapper;
	@Autowired
	TicketGoodsMapper ticketGoodsMapper;
	public void addTicket(Entryticket entryticket) {
		entryticketMapper.insertSelective(entryticket);
	}

	public void changeTicket(Entryticket entryticket) {
		entryticketMapper.updateByPrimaryKeySelective(entryticket);
	}

	public void deleteTicket(String enterId) {
		entryticketMapper.deleteByPrimaryKey(enterId);
	}

	public List<Entryticket> selectByCondition(SearchEntryticket searchEntryticket) {
		if (searchEntryticket.getSellerName().equals("")) {
			searchEntryticket.setSellerName(null);
		}
		if (searchEntryticket.getBuyerName().equals("")) {
			searchEntryticket.setBuyerName(null);
		}
		if (searchEntryticket.getEnterNumber().equals("")) {
			searchEntryticket.setEnterNumber(null);
		}
		if (searchEntryticket.getGoodsName().equals("")) {
			searchEntryticket.setGoodsName(null);
		}
//		if (searchEntryticket.getStartDate().equals("")) {
//			searchEntryticket.setStartDate(null);
//		}
//		if (searchEntryticket.getEndDate().equals("")) {
//			searchEntryticket.setEndDate(null);
//		}
		return entryticketMapper.selectByCondition(searchEntryticket);
	}

	public List<Entryticket> selectByUserId(String userId) {
		return entryticketMapper.selectByUserId(userId);
	}
	public Entryticket selectByEnterId(String enterId) {
		return entryticketMapper.selectByenterId(enterId);
	}
	public List<Entryticket> findAll(){
		return entryticketMapper.selectAll();
	}

	@Override
	public List<Object> selectByEnterIds(String[] enterIds) {
		List<Object> list = new ArrayList<>();
		for (int i = 0; i < enterIds.length; i++) {
			Map map = new HashMap();
			List<TicketGoods> list2 = new ArrayList<>();
			Entryticket entryticket=entryticketMapper.selectByenterId(enterIds[i]);
			List<String> list1= ArrColUtil.colToList(entryticket.getGoodsId());
			for (int j = 0; j < list1.size(); j++) {
				TicketGoods ticketGoods = ticketGoodsMapper.selectByPrimaryKey(list1.get(j));
				list2.add(ticketGoods);
			}
			map.put("ticketGoods", list2);
			map.put("entryticket", entryticket);
			list.add(map);
		}
		return list;  
	}

	
}
