package wr.com.service;

import java.util.List;

import wr.com.pojo.historyLog.WrHistoryLogPage;
import wr.com.pojo.historyLog.WrHistoryLogVO;
/**
 * 
 * @author 郭杰
 * @since Dec 6,2016
 * @version 1.0.1
 */
public interface WrHistoryLogService {
	/**
	 * 根据Id查询操作日志
	 * @param id
	 * @return
	 */
    public WrHistoryLogVO getWrHistoryLogById(int id);
    
    /**
     * 查询日志列表
     * @param historyLog
     * @return
     */
    public List<WrHistoryLogPage> getWrHistoryLogList(WrHistoryLogPage historyLog);
    /**
     *  新增
     * @param wrHistoryLog
     */
    public void saveHistoryLog(WrHistoryLogVO wrHistoryLog);
    /**
     * 根据Id删除日志
     * @param id
     * @return
     */
    public int deleteHistoryLogById(int id);
    /**
     * 批量删除日志
     * @param ids
     * @return
     */
    public int deleteHistoryLog(Integer[] ids);
}