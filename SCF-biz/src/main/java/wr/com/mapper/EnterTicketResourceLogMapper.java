package wr.com.mapper;

import java.util.Date;
import java.util.List;

import wr.com.pojo.EnterTicketResourceLog;

public interface EnterTicketResourceLogMapper {
    int deleteByPrimaryKey(String sjbh);

    int insert(EnterTicketResourceLog record);

    int insertSelective(EnterTicketResourceLog record);
    
    int insertList(List<EnterTicketResourceLog> list);
    
    EnterTicketResourceLog selectByPrimaryKey(String sjbh);

    int updateByPrimaryKeySelective(EnterTicketResourceLog record);

    int updateByPrimaryKey(EnterTicketResourceLog record);
  
    Date selectMaxDate();
    
    List<EnterTicketResourceLog> selectAfterDate(Date date);
}