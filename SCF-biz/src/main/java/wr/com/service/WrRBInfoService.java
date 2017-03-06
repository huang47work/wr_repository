package wr.com.service;

import java.util.List;
import java.util.Map;

import wr.com.pojo.WrRBInfo;
import wr.com.result.Result;
/**
 * 
 * @author 郭杰
 * @since Dec 8,2016
 * @version 1.0.1
 */
public interface WrRBInfoService {
	/**
	 * 根据Id查询还款
	 * @param id
	 * @return
	 */
    public WrRBInfo getWrRBInfoById(int id);
    
    /**
     * 查询还款列表
     * @param RBInfo
     * @return
     */
    public List<WrRBInfo> getWrRBInfoList(Map<String,Object> params);
    /**
     *  新增
     * @param wrRBInfo
     */
    public Result<?> saveRBInfo(WrRBInfo wrRBInfo);
    /**
     * 根据Id删除还款
     * @param id
     * @return
     */
    public int deleteRBInfoById(int id);
    /**
     * 批量删除还款
     * @param ids
     * @return
     */
    public int deleteRBInfo(Integer[] ids);
    /**
     * 修改基本信息
     * @param info
     * @return
     */
    public int updateById(WrRBInfo info);
}