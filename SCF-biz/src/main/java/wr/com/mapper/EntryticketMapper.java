package wr.com.mapper;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import wr.com.pojo.Entryticket;
import wr.com.pojo.SearchEntryticket;

@Repository()
public interface EntryticketMapper {
    
	int deleteByPrimaryKey(String enterId);

    int insert(Entryticket record);

    int insertSelective(Entryticket record);
    
    List<Entryticket> selectByCondition(SearchEntryticket searchEntryticket);
    
    List<Entryticket> selectByUserId(String userId);
    
    List<Entryticket> selectAll();
    
    Entryticket selectByenterId(String enterId);

    int updateByPrimaryKeySelective(Entryticket record);

    int updateByPrimaryKey(Entryticket record);
}