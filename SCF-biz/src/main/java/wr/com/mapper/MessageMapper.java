package wr.com.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import wr.com.pojo.Message;
@Repository
public interface MessageMapper {
    int deleteByMid(String mid);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer id);
    
    List<Message> selectByType(String type,String userId);
    
    List<Message> selectMoreDate(String date,String userId);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);
    
    void changeStatue(String mid,String statue);
}