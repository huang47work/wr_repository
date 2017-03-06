package wr.com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import wr.com.mapper.base.BaseDao;
import wr.com.pojo.historyLog.WrHistoryLogPage;
import wr.com.pojo.historyLog.WrHistoryLogVO;

/**
 * 
 * @author 郭杰
 * @since Dec 6,2016
 * @version 1.0.1
 */
public interface WrHistoryLogMapper<T> extends BaseDao<T>{

	int insertSelective(@Param("param") WrHistoryLogVO record);

	List<WrHistoryLogPage> selectHistoryLogList(WrHistoryLogPage historyLog);
}