package wr.com.service;

import java.util.List;
import java.util.Map;

import wr.com.pojo.WrUserFlow;
/**
 * 
 * @author 郭杰
 * @since Dec 8,2016
 * @version 1.0.1
 */
public interface WrUserFlowService {
	/**
	 * 根据Id查询资金流水
	 * @param id
	 * @return
	 */
    public WrUserFlow getWrUserFlowById(int id);
    
    /**
     * 查询资金流水列表
     * @param UserFlow
     * @return
     */
    public List<WrUserFlow> getWrUserFlowList(Map<String,Object> params);
    /**
     *  新增
     * @param wrUserFlow
     */
    public void saveUserFlow(WrUserFlow wrUserFlow);
    /**
     * 根据Id删除资金流水
     * @param id
     * @return
     */
    public int deleteUserFlowById(int id);
    /**
     * 批量删除资金流水
     * @param ids
     * @return
     */
    public int deleteUserFlow(Integer[] ids);
}