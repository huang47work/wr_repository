package wr.com.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import wr.com.mapper.FinancialProductsInstaceMapper;
import wr.com.pojo.WrRBInfo;
import wr.com.pojo.WrRBInfoPage;
import wr.com.pojo.WrRepayBack;
import wr.com.pojo.WrUserFlow;
import wr.com.pojo.BBInfo.WrBBInfoVO;
import wr.com.pojo.historyLog.WrHistoryLogVO;
import wr.com.result.Constants;
import wr.com.result.ErrorCode;
import wr.com.result.Result;
import wr.com.service.impl.WrBBInfoService;
import wr.com.service.impl.WrHistoryLogService;
import wr.com.service.impl.WrRBInfoService;
import wr.com.service.impl.WrRepayBackService;
import wr.com.service.impl.WrUserFlowService;

/**
 * 
 * @author guojie
 * @since Dec 8,2016
 * @version 1.0.1
 *
 */
@RequestMapping("RBInfo/manage")
@Controller
@Api(value = "RBInfo/manage", description = "还款单接口")
public class WrRBInfoController extends BaseController {

	@Autowired
	WrRepayBackService<WrRepayBack> wrRepayBackService;

	@Autowired
	WrHistoryLogService<WrHistoryLogVO> wrHistoryLogService;

	@Autowired
	WrRBInfoService<WrRBInfo> wrRBInfoService;

	@Autowired
	WrBBInfoService<WrBBInfoVO> wrBBInfoService;

	@Autowired
	WrUserFlowService<WrUserFlow> wrUserFlowService;

	@Autowired
	FinancialProductsInstaceMapper financialProductsInstaceMapper;

	private static Logger logger = (Logger) LoggerFactory.getLogger(WrRBInfoController.class);

	/**
	 * 查询列表
	 * 
	 * @param model
	 * @param status
	 * @return
	 */
	@RequestMapping(value ="/list",method = RequestMethod.POST)
	@ApiOperation(value = "还款单列表查询")
	@ResponseBody
	public Result<?> queryRBInfoList(Model model, @ApiParam(name = "status", value = "状态") Integer status) {

		logger.info("*****************查询还款列表开始******************");
		List<WrRBInfo> RbInfoList = new ArrayList<>();
		WrRBInfoPage apage = new WrRBInfoPage();
		if (null != status) {
			apage.setStatus(status);
		}
		try {
			RbInfoList = wrRBInfoService.queryByList(apage);
			if (RbInfoList.size() > 0) {
				return Result.wrapSuccessfulResult(RbInfoList);
			} else {
				return Result.wrapErrorResult(ErrorCode.BLANK_RESULT);
			}
		} catch (Exception e) {
			logger.error("*************还款单列表查询错误信息:" + e.getMessage());
			logger.error("还款单列表查询错误信息结束***************");
			return Result.wrapErrorResult(ErrorCode.QUERY_FAIL);
		}

	}

	/**
	 * 查询详情
	 * 
	 * @param model
	 * @param status
	 * @return
	 */
	@RequestMapping(value ="/detail",method = RequestMethod.POST)
	@ApiOperation(value = "还款单详情")
	public Result<?> queryRBInfoDetail(Model model, @ApiParam(name = "id", value = "ID") Integer id) {

		logger.info("*****************查询还款详情开始******************");
		WrRBInfoPage apage = new WrRBInfoPage();
		List<WrRBInfo> RbInfoList = new ArrayList<>();
		if (id == null) {
			return Result.wrapErrorResult(ErrorCode.BLANK_ID);
		} else {
			apage.setId(id);
		}
		try {
			RbInfoList = wrRBInfoService.queryByList(apage);
			if (RbInfoList.size() > 0) {
				return Result.wrapSuccessfulResult(RbInfoList.get(0));
			} else {
				return Result.wrapErrorResult(ErrorCode.BLANK_RESULT);
			}
		} catch (Exception e) {
			logger.error("*************还款单详情查询错误信息:" + e.getMessage());
			logger.error("还款单详情查询错误信息结束***************");
			return Result.wrapErrorResult(ErrorCode.QUERY_FAIL);
		}

	}

	/**
	 * 新增-放款
	 * 
	 * @see 放款审核通过，就生成还款单
	 * @see 根据还款类型（handleType）判断生成几条还款数据
	 * @see 修改流程状态为已放款,状态-5
	 * 
	 * @param model
	 * @param status
	 * @return
	 */
	@RequestMapping(value ="/add",method = RequestMethod.POST)
	@ApiOperation(value = "创建还款单列表")
	@ResponseBody
	public Result<?> addUserFlow(Model model, @ApiParam(name = "id", value = "id") WrRBInfo info, Integer id) {

		logger.info("*****************创建还款单开始******************");
		try {
			/**
			 * TODO 根据还款方式来生成还款单列表
			 * 
			 * 还款方式
			 * 
			 * @see 1:等额本息
			 * @see 2:等额本金
			 */
			WrBBInfoVO bbInfo = wrBBInfoService.queryById(id);

			wrRBInfoService.saveRepayMentInfo(id, info, bbInfo);
			return Result.wrapSuccessfulResult("放款成功！");
		} catch (Exception e) {
			logger.error("*************创建还款单错误信息:" + e.getMessage());
			logger.error("创建还款单错误信息结束***************");
			return Result.wrapErrorResult(ErrorCode.ADD_FAIL);
		}
	}

	/**
	 * 修改-修改还款单状态
	 * 
	 * @see 如果多条还款单都已经还款，借款单状态修改为 还款完成
	 * 
	 * @param model
	 * @param status
	 * @return
	 */
	@RequestMapping(value ="/modify",method = RequestMethod.POST)
	@ApiOperation(value = "修改还款单")
	@ResponseBody
	public Result<?> modifyRBInfo(Model model, @ApiParam(name = "WrBBInfo", value = "WrBBInfo") WrRBInfo info) {

		logger.info("*****************修改还款单开始******************");
		Object id = info.getId();
		logger.info("*****************ID：" + id + "*****************");
		if (StringUtils.isBlank(id.toString())) {
			return Result.wrapErrorResult(ErrorCode.BLANK_ID);
		}
		try {
			return Result.wrapSuccessfulResult(wrRBInfoService.updateStatus(info));
		} catch (Exception e) {
			logger.error("*************修改还款单错误信息:" + e.getMessage());
			logger.error("修改还款单错误信息结束***************");
			return Result.wrapErrorResult(ErrorCode.UPDATE_FAIL);
		}
	}

	/**
	 * 删除
	 * 
	 * @param model
	 * @param status
	 * @return
	 */
	@RequestMapping(value ="/delete",method = RequestMethod.POST)
	@ApiOperation(value = "删除还款单")
	@ResponseBody
	public Result<?> deleteInvoice(Model model, @ApiParam(name = "id", value = "id") Integer id) {

		logger.info("*****************删除还款单开始******************");
		if (null == id) {
			logger.error("ID:" + ErrorCode.BLANK_ID.getMessage());
			return Result.wrapErrorResult(ErrorCode.BLANK_ID);
		}
		try {
			int result = wrRBInfoService.deleteById(id);
			if (result == 1) {
				logger.info(Constants.DELETE_SUCCESS);
				return Result.wrapSuccessfulResult(ErrorCode.DELETE_SUCCESS.getMessage());
			} else {
				logger.error(ErrorCode.DELETE_FAIL.getMessage());
				return Result.wrapErrorResult(ErrorCode.DELETE_FAIL);
			}
		} catch (Exception e) {
			logger.error("*************删除还款单错误信息:" + e.getMessage());
			logger.error("删除还款单错误信息结束***************");
			return Result.wrapErrorResult(ErrorCode.DELETE_FAIL);
		}
	}

	/**
	 * 批量删除
	 * 
	 * @param model
	 * @param status
	 * @return
	 */
	@RequestMapping(value ="/batchDel",method = RequestMethod.POST)
	@ApiOperation(value = "批量删除")
	@ResponseBody
	public Result<?> BatchDeleteInvoice(Model model, @ApiParam(name = "ids", value = "ids") Integer[] ids) {

		logger.info("*****************批量删除还款单开始******************");
		if (null == ids) {
			logger.error("IDs:" + ErrorCode.BLANK_OBJECT.getMessage());
			return Result.wrapErrorResult(ErrorCode.BLANK_OBJECT);
		}
		if (ids.length == Constants.NUMBER_0) {
			logger.error("IDs:" + ErrorCode.BLANK_ARRAY.getMessage());
			return Result.wrapErrorResult(ErrorCode.BLANK_ARRAY);
		}
		try {
			int result = wrRBInfoService.deleteByIds(ids);
			if (result == Constants.NUMBER_1) {
				logger.info(Constants.DELETE_SUCCESS);
				return Result.wrapSuccessfulResult(ErrorCode.DELETE_SUCCESS.getMessage());
			} else {
				logger.error(ErrorCode.DELETE_FAIL.getMessage());
				return Result.wrapErrorResult(ErrorCode.DELETE_FAIL);
			}
		} catch (Exception e) {
			logger.error("*************删除还款单错误信息:" + e.getMessage());
			logger.error("删除还款单错误信息结束***************");
			return Result.wrapErrorResult(ErrorCode.DELETE_FAIL);
		}
	}
}
