package wr.com.service;

import java.util.List;
import java.util.Map;

import wr.com.pojo.WrRepayBack;
/**
 * 
 * @author 郭杰
 * @since Dec 8,2016
 * @version 1.0.1
 */
public interface WrRepayBackService {
	/**
	 * 根据Id查询还款
	 * @param id
	 * @return
	 */
    public WrRepayBack getWrRepayBackById(int id);
    
    /**
     * 查询还款列表
     * @param RepayBack
     * @return
     */
    public List<WrRepayBack> getWrRepayBackList(Map<String,Object> params);
    /**
     *  新增
     * @param wrRepayBack
     */
    public void saveRepayBack(WrRepayBack wrRepayBack);
    /**
     * 根据Id删除还款
     * @param id
     * @return
     */
    public int deleteRepayBackById(int id);
    /**
     * 批量删除还款
     * @param ids
     * @return
     */
    public int deleteRepayBack(Integer[] ids);
}