package wr.com.service;

import java.util.List;

import wr.com.pojo.Message;
/**接口说明：
	*getMessagesByType：通过类型查询实体
	*selectMoreDate：查询最近几天 消息
	*changeStatues：改变状态
	*deleteByMids：通过mid进行删除操作
	*add添加
* */
public interface MessageService {
 
	public List<Message> getMessagesByType(String type,String userId);
	
	List<Message> selectRecentlyDate(String date,String userId);
	
	public void changeStatues(List<String> mids,String statue);
	
	public void deleteByMids(List<String> mids);
	
	public void add(Message message);
}
  