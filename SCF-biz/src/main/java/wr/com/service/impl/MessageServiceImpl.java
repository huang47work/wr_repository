package wr.com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wr.com.mapper.MessageMapper;
import wr.com.pojo.Message;
import wr.com.service.MessageService;
@Service("MessageServiceImpl")
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private MessageMapper messageMapper;
	
	public List<Message> getMessagesByType(String type,String userId) {
		
		return messageMapper.selectByType(type,userId);
	}

	public void changeStatues(List<String> mids,String statue) {
		for (String mid : mids) {
			messageMapper.changeStatue(mid, statue);
		}		
	}

	public void deleteByMids(List<String> mids) {
		for (String mid : mids) {
			messageMapper.deleteByMid(mid);
		}
	}
	public void add(Message message){
		messageMapper.insert(message);
	}

	@Override
	public List<Message> selectRecentlyDate(String date,String userId) {
		return messageMapper.selectMoreDate(date,userId);
	}
}
