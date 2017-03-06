package wr.com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import wr.com.pojo.FinancialProductsInstace;
import wr.com.pojo.LoanApplication;
import wr.com.pojo.WrAgreement;
import wr.com.pojo.WrRBInfo;
import wr.com.pojo.WrRBInfoPage;
import wr.com.pojo.WrRepayBack;
import wr.com.pojo.BBInfo.WrBBInfoPage;
import wr.com.pojo.BBInfo.WrBBInfoVO;
import wr.com.pojo.historyLog.WrHistoryLogVO;
import wr.com.result.Constants;
import wr.com.result.ErrorCode;
import wr.com.result.Result;
import wr.com.service.ApplyMailingAddressService;
import wr.com.service.LoanApplicationService;
import wr.com.service.impl.WrAgreementService;
import wr.com.service.impl.WrBBInfoService;
import wr.com.service.impl.WrHistoryLogService;
import wr.com.service.impl.WrRBInfoService;
import wr.com.service.impl.WrRepayBackService;
import wr.com.utils.DateUtil;

/**
 * 
 * @author guojie
 * @since Dec 8,2016
 * @version 1.0.1
 *
 */
@RequestMapping(value = "BBInfo/manage")
@Controller
@Api(value = "BBInfo/manage", description = "借款单接口")
public class WrBBInfoController {

	@Autowired
	WrRepayBackService<WrRepayBack> wrRepayBackService;

	@Autowired
	WrHistoryLogService<WrHistoryLogVO> wrHistoryLogService;

	@Autowired
	WrBBInfoService<WrBBInfoVO> wrBBInfoService;
	
	@Autowired
	WrRBInfoService<WrRBInfo> wrRBInfoService;

	@Autowired
	LoanApplicationService loadApplicationService;

	@Autowired
	WrAgreementService<WrAgreement> wrAgreementService;

	@Autowired
	ApplyMailingAddressService applyMailingAddressService;
	
	@Autowired
	FinancialProductsInstaceMapper financialProductsInstaceMapper;

	private static Logger logger = (Logger) LoggerFactory.getLogger(WrBBInfoController.class);

	/**
	 * 查询列表
	 * 
	 * @param model
	 * @param status
	 * @return
	 */
	@RequestMapping(value ="/list")
	@ApiOperation(value = "借款单列表查询")
	@ResponseBody
	public Result<?> queryBBInfoList(Model model, @ApiParam(name = "status", value = "状态") String status,
			@ApiParam(name = "startDay", value = "起始日") String startDay,
			@ApiParam(name = "endDay", value = "结束日") String endDay) {

		logger.info("*****************查询借款列表开始******************");
		List<WrBBInfoVO> BBInfoList = new ArrayList<>();
		WrBBInfoPage apage = new WrBBInfoPage();
		if (StringUtils.isNotBlank(startDay) && StringUtils.isNotBlank(endDay)) {
			apage.setStartDay(startDay);
			apage.setEndDay(DateUtil.dateToString(DateUtil.increaseDay(DateUtil.stringToDate(endDay, "yyyy-MM-dd"), 1), "yyyy-MM-dd"));
		}
		if (StringUtils.isNotBlank(status)) {
			apage.setStatus(status);
		}
		try {
			BBInfoList = wrBBInfoService.queryByList(apage);
			if (BBInfoList.size() > 0) {
				return Result.wrapSuccessfulResult(BBInfoList);
			} else {
				return Result.wrapErrorResult(ErrorCode.BLANK_RESULT);
			}
		} catch (Exception e) {
			logger.error("*************查询供应链款项列表错误信息:" + e.getMessage());
			logger.error("查询供应链款项列表错误信息结束***************");
			return Result.wrapErrorResult(ErrorCode.QUERY_FAIL);
		}

	}

	/**
	 * 查询详情-协议+地址
	 * 
	 * @param model
	 * @param status
	 * @return
	 */
	@RequestMapping(value ="/detail",method = RequestMethod.POST)
	@ApiOperation(value = "借款单详情")
	@ResponseBody
	public Result<?> queryBBInfoDetail(Model model, @ApiParam(name = "id", value = "ID") Integer id) {

		logger.info("*****************查询还款列表开始******************");
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			WrBBInfoVO bbinfo = wrBBInfoService.queryById(id);
			param.put("bbInfo", bbinfo);
			WrRBInfoPage rbInfo = new WrRBInfoPage();
			rbInfo.setBorrowId(id);
			param.put("rbInfo", wrRBInfoService.queryByList(rbInfo));
			// 协议
			if (null != bbinfo) {
				//产品实例id
				FinancialProductsInstace financialProductsInstace = financialProductsInstaceMapper
						.selectByPrimaryKey(bbinfo.getInstanceId());
				param.put("instance", financialProductsInstace);
				param.put("agreement", wrAgreementService.queryByFName(bbinfo.getFinanceUserName()));
				param.put("address", applyMailingAddressService.selectByPrimaryKey(bbinfo.getApplyMid()));
			} else {
				logger.error("*************查询结果为空***************");
			}
			return Result.wrapSuccessfulResult(param);
		} catch (Exception e) {
			logger.error("*************查询借款单详情错误信息:" + e.getMessage());
			logger.error("查询借款单详情错误信息结束***************");
			return Result.wrapErrorResult(ErrorCode.QUERY_FAIL);
		}

	}

	/**
	 * 新增
	 * 
	 * @see 金融机构审核通过，就生成借款单
	 * @see 后续线上网签，确定相关信息
	 * @see 然后放款，生成还款单
	 * 
	 * @param model
	 * @param status
	 * @return
	 */
	@RequestMapping(value ="/add",method = RequestMethod.POST)
	@ApiOperation(value = "创建借款单")
	@ResponseBody
	public Result<?> addBBInfo(Model model, @ApiParam(name = "WrBBInfo", value = "WrBBInfo") WrBBInfoVO infos) {

		logger.info("*****************创建借款单开始******************");
		Object orderId = infos.getOrderId();
		logger.info("*****************关联单号：" + orderId + "*****************");
		if (orderId == null) {
			return Result.wrapErrorResult(ErrorCode.BLANK_BORROW_ID);
		}
		try {
			Constants.init(infos);
			infos.setStatus(Constants.STRING_ZERO);
			return Result.wrapSuccessfulResult(wrBBInfoService.add(infos));
		} catch (Exception e) {
			logger.error("*************创建借款单错误信息:" + e.getMessage());
			logger.error("创建借款单错误信息结束***************");
			return Result.wrapErrorResult(ErrorCode.ADD_FAIL);
		}
	}

	/**
	 * 修改-网签协议
	 * 
	 * @param model
	 * @param status
	 * @return
	 */
	@RequestMapping(value ="/modify",method = RequestMethod.POST)
	@ApiOperation(value = "修改借款单")
	@ResponseBody
	public Result<?> modifyBBInfo(Model model, @ApiParam(name = "WrBBInfo", value = "Object") WrBBInfoVO infos) {

		logger.info("*****************修改借款单开始******************");
		Object id = infos.getId();
		logger.info("*****************ID：" + id + "*****************");
		if (StringUtils.isBlank(id.toString())) {
			return Result.wrapErrorResult(ErrorCode.BLANK_ID);
		}
		try {
			// 修改流程审批状态
			LoanApplication loanApplication = new LoanApplication();
			loanApplication.setAid(infos.getOrderNum());
			// 网签协议-4,放款-5
			Constants.init(infos);
			infos.setStatus(Constants.STRING_ONE);//待放款
			loanApplication.setProcessStatus(Constants.STRING_FOUR);
			// loadApplicationService.updateStatus(loanApplication);
			return Result.wrapSuccessfulResult(wrBBInfoService.updateById(infos, loanApplication));
		} catch (Exception e) {
			logger.error("*************修改借款单错误信息:" + e.getMessage());
			logger.error("修改借款单错误信息结束***************");
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
	@ApiOperation(value = "删除借款单")
	@ResponseBody
	public Result<?> deleteInvoice(Model model, @ApiParam(name = "id", value = "id") Integer id) {

		logger.info("*****************删除借款单开始******************");
		if (null == id) {
			logger.error("ID:" + ErrorCode.BLANK_ID.getMessage());
			return Result.wrapErrorResult(ErrorCode.BLANK_ID);
		}
		try {
			int result = wrBBInfoService.deleteById(id);
			if (result == 1) {
				logger.info(Constants.DELETE_SUCCESS);
				return Result.wrapSuccessfulResult(ErrorCode.DELETE_SUCCESS.getMessage());
			} else {
				logger.error(ErrorCode.DELETE_FAIL.getMessage());
				return Result.wrapErrorResult(ErrorCode.DELETE_FAIL);
			}
		} catch (Exception e) {
			logger.error("*************删除借款单错误信息:" + e.getMessage());
			logger.error("删除借款单错误信息结束***************");
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
	@RequestMapping(value = "/batchDel",method = RequestMethod.POST)
	@ApiOperation(value = "批量删除")
	@ResponseBody
	public Result<?> BatchDeleteInvoice(Model model, @ApiParam(name = "ids", value = "ids") Integer[] ids) {

		logger.info("*****************批量删除借款单开始******************");
		if (null == ids) {
			logger.error("IDs:" + ErrorCode.BLANK_OBJECT.getMessage());
			return Result.wrapErrorResult(ErrorCode.BLANK_OBJECT);
		}
		if (ids.length == Constants.NUMBER_0) {
			logger.error("IDs:" + ErrorCode.BLANK_ARRAY.getMessage());
			return Result.wrapErrorResult(ErrorCode.BLANK_ARRAY);
		}
		try {
			int result = wrBBInfoService.deleteByIds(ids);
			if (result == Constants.NUMBER_1) {
				logger.info(Constants.DELETE_SUCCESS);
				return Result.wrapSuccessfulResult(ErrorCode.DELETE_SUCCESS.getMessage());
			} else {
				logger.error(ErrorCode.DELETE_FAIL.getMessage());
				return Result.wrapErrorResult(ErrorCode.DELETE_FAIL);
			}
		} catch (Exception e) {
			logger.error("*************删除借款单错误信息:" + e.getMessage());
			logger.error("删除借款单错误信息结束***************");
			return Result.wrapErrorResult(ErrorCode.DELETE_FAIL);
		}
	}
}
