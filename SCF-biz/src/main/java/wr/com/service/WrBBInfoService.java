package wr.com.service;

import java.util.List;
import java.util.Map;

import wr.com.pojo.BBInfo.WrBBInfoVO;
import wr.com.result.Result;
/**
 * 
 * @author 郭杰
 * @since Dec 7,2016
 * @version 1.0.1
 */
public interface WrBBInfoService {
	/**
	 * 根据Id查询借款
	 * @param id
	 * @return
	 */
    public WrBBInfoVO getWrBBInfoById(int id);
    
    /**
     * 查询借款列表
     * @param BBInfo
     * @return
     */
    public List<WrBBInfoVO> getWrBBInfoList(Map<String,Object> params);
    /**
     *  新增
     * @param wrBBInfo
     */
    public Result<?> saveBBInfo(WrBBInfoVO wrBBInfo);
    /**
     * 根据Id删除借款
     * @param id
     * @return
     */
    public int deleteBBInfoById(int id);
    /**
     * 批量删除借款
     * @param ids
     * @return
     */
    public int deleteBBInfo(Integer[] ids);
    /**
     * 
     * 修改基本信息
     * @param wrBBInfo
     * @return
     */
    public boolean updateBBInfo(WrBBInfoVO wrBBInfo);
}