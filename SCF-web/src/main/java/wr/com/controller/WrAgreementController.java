package wr.com.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import wr.com.pojo.WrAgreement;
import wr.com.pojo.WrAgreementPage;
import wr.com.pojo.historyLog.WrHistoryLogVO;
import wr.com.result.Constants;
import wr.com.result.ErrorCode;
import wr.com.result.Result;
import wr.com.service.impl.WrAgreementService;
import wr.com.service.impl.WrHistoryLogService;
import wr.com.utils.DateUtil;
import wr.com.utils.HtmlUtil;
import wr.com.utils.QiNiuUtil;

/**
 * 授信批复Controller
 * 
 * @author 郭杰
 * @since Dec 16,2016
 * @version 1.0.1
 *
 */
@RequestMapping("agreement/manage")
@Controller
@Api(value = "agreement/manage", description = "授信批复接口")
public class WrAgreementController extends BaseController {

	@Autowired
	WrHistoryLogService<WrHistoryLogVO> wrHistoryLogService;

	@Autowired
	WrAgreementService<WrAgreement> agreementService;

	private static Logger logger = (Logger) LoggerFactory.getLogger(WrAgreementController.class);

	/**
	 * 查询列表
	 * 
	 * @param model
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/list",method = RequestMethod.POST)
	@ApiOperation(value = "授信批复列表查询")
	@ResponseBody
	public Result<?> queryAgreementList(Model model,
			@ApiParam(name = "agreementNum", value = "协议编号") String agreementNum,
			@ApiParam(name = "agreementName", value = "协议名称") String agreementName,
			@ApiParam(name = "status", value = "授信批复 状态") Integer status,
			@ApiParam(name = "financingType", value = "协议类型") Integer financingType,
			@ApiParam(name = "lender", value = "出借人") String lender,
			@ApiParam(name = "blRepayment", value = "借款人") String blRepayment,
			@ApiParam(name = "signStartDay", value = "签署起始日") String signStartDay,
			@ApiParam(name = "signEndDay", value = "签署结束日") String signEndDay) {
		logger.info("*****************查询授信批复列表开始******************");
		WrAgreementPage agreementPage = new WrAgreementPage();
		List<WrAgreement> agreementList = new ArrayList<>();
		if (StringUtils.isNotBlank(agreementNum)) {
			agreementPage.setAgreementNum(agreementNum);
		}
		if (StringUtils.isNotBlank(agreementName)) {
			agreementPage.setAgreementName(agreementName);
		}
		if (null != status) {
			agreementPage.setStatus(status);
		}
		if (null != financingType) {
			agreementPage.setFinancingType(financingType);
		}
		if (StringUtils.isNotBlank(lender)) {
			agreementPage.setLender(lender);
		}
		if (StringUtils.isNotBlank(blRepayment)) {
			agreementPage.setBlRepayment(lender);
		}
		if (StringUtils.isNotBlank(signStartDay) && StringUtils.isNotBlank(signEndDay)) {
			agreementPage.setSignStartDay(signStartDay);
			agreementPage.setSignEndDay(signEndDay);
		}
		try {
			logger.info("*********查询授信批复列表！**********");
			agreementList = agreementService.queryByList(agreementPage);
			if (agreementList.size() > 0) {
				return Result.wrapSuccessfulResult(agreementList);
			} else {
				return Result.wrapErrorResult(ErrorCode.BLANK_RESULT);
			}
		} catch (Exception e) {
			logger.error("*************查询授信批复列表错误信息:" + e.getMessage());
			logger.error("查询授信批复列表错误信息结束***************");
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
	@RequestMapping(value = "/detail",method = RequestMethod.POST)
	@ApiOperation(value = "授信批复详情")
	@ResponseBody
	public Result<?> queryAgreementDetail(Model model, @ApiParam(name = "id", value = "ID") Integer id) {

		logger.info("*****************查询授信批复详情开始******************");
		List<WrAgreement> AgreementList = new ArrayList<>();
		WrAgreementPage apage = new WrAgreementPage();
		if (id == null) {
			logger.error("id:" + ErrorCode.BLANK_ID.getMessage());
			return Result.wrapErrorResult(ErrorCode.BLANK_ID);
		} else {
			apage.setId(id);
		}
		try {
			AgreementList = agreementService.queryByList(apage);
			if (AgreementList.size() > 0) {
				return Result.wrapSuccessfulResult(AgreementList.get(0));
			} else {
				logger.error("RESULT:" + ErrorCode.BLANK_RESULT.getMessage());
				return Result.wrapErrorResult(ErrorCode.BLANK_RESULT);
			}
		} catch (Exception e) {
			logger.error("*************查询授信批复详情错误信息:" + e.getMessage());
			logger.error("查询授信批复详情错误信息结束***************");
			return Result.wrapErrorResult(ErrorCode.QUERY_FAIL);
		}

	}

	/**
	 * 新增
	 * 
	 * @param model
	 * @param status
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ApiOperation(value = "创建授信批复")
	@ResponseBody
	public Result<?> addAgreement(Model model, @RequestParam MultipartFile[] myfiles, HttpServletRequest request)
			throws UnsupportedEncodingException {

		logger.info(myfiles.length + "ppppppppppppppp");
		logger.info("请求者IP：" + this.getIpAddr(request));
		Map<String, Cookie> cookieMap = this.getCookieMap();
		/* 获取查询条件 */
		Date signDate = (Date) this.getViewRequestParam(cookieMap, "signDate", new Date());
		Date startDate = (Date) this.getViewRequestParam(cookieMap, "startDate", new Date());
		Date endDate = (Date) this.getViewRequestParam(cookieMap, "endDate", new Date());
		BigDecimal financingAmount = (BigDecimal) this.getViewRequestParam(cookieMap, "financingAmount",
				new BigDecimal(0));
		String agreementNum = (String) this.getViewRequestParam(cookieMap, "agreementNum", "");
		String agreementName = (String) this.getViewRequestParam(cookieMap, "agreementName", "");
		Integer financingType = (Integer) this.getViewRequestParam(cookieMap, "financingType", new Integer(0));
		String applyCompany = (String) this.getViewRequestParam(cookieMap, "applyCompany", "");
		String lender = (String) this.getViewRequestParam(cookieMap, "lender", "");
		String coreCompany = (String) this.getViewRequestParam(cookieMap, "coreCompany", "");
		String platform = (String) this.getViewRequestParam(cookieMap, "platform", "");
		BigDecimal blYearInterestRate = (BigDecimal) this.getViewRequestParam(cookieMap, "blYearInterestRate",
				new BigDecimal(0));
		BigDecimal blOverdueInterestRate = (BigDecimal) this.getViewRequestParam(cookieMap, "blOverdueInterestRate",
				new BigDecimal(0));
		String blRepayment = (String) this.getViewRequestParam(cookieMap, "blRepayment", "");
		String blRepaymentAccount = (String) this.getViewRequestParam(cookieMap, "blRepaymentAccount", "");
		String blRepaymentBank = (String) this.getViewRequestParam(cookieMap, "blRepaymentBank", "");
		String blRepaymentBranchBank = (String) this.getViewRequestParam(cookieMap, "blRepaymentBranchBank", "");
		BigDecimal pledgeRate = (BigDecimal) this.getViewRequestParam(cookieMap, "pledgeRate", new BigDecimal(0));
		BigDecimal zyYearInterestRate = (BigDecimal) this.getViewRequestParam(cookieMap, "zyYearInterestRate",
				new BigDecimal(0));
		BigDecimal zyOverdueInterestRate = (BigDecimal) this.getViewRequestParam(cookieMap, "zyOverdueInterestRate",
				new BigDecimal(0));
		String zyRepayment = (String) this.getViewRequestParam(cookieMap, "zyRepayment", "");
		String zyRepaymentAccount = (String) this.getViewRequestParam(cookieMap, "zyRepaymentAccount", "");
		String zyRepaymentBank = (String) this.getViewRequestParam(cookieMap, "zyRepaymentBank", "");
		String zyRepaymentBranchBank = (String) this.getViewRequestParam(cookieMap, "zyRepaymentBranchBank", "");
		String isApproval = (String) this.getViewRequestParam(cookieMap, "isApproval", "");
		String invoiceMust = (String) this.getViewRequestParam(cookieMap, "invoiceMust", "");
		Integer blChecked = (Integer) this.getViewRequestParam(cookieMap, "blChecked", new Integer(0));
		Integer zyChecked = (Integer) this.getViewRequestParam(cookieMap, "zyChecked", new Integer(0));

		/* 附件上传 */
		StringBuffer url = new StringBuffer();
		String urlImgs = "";
		for (MultipartFile myfile : myfiles) {
			if (myfile.isEmpty()) {
				logger.info("文件未上传");
			} else {
				logger.info("文件长度: " + myfile.getSize());
				logger.info("文件类型: " + myfile.getContentType());
				logger.info("文件名称: " + myfile.getName());
				logger.info("文件原名: " + myfile.getOriginalFilename());
				logger.info("========================================");
				// 如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中
				String realPath = request.getSession().getServletContext().getRealPath("/") + Constants.UPLOAD_DIRECTORY
						+ File.separator;
				// 如果目录不存在则创建
				File uploadDir = new File(realPath);
				if (!uploadDir.exists()) {
					uploadDir.mkdir();
				}
				// 这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
				try {
//					FileUtils.copyInputStreamToFile(myfile.getInputStream(),
//							new File(realPath, myfile.getOriginalFilename()));
//					File localFile = new File(realPath, myfile.getOriginalFilename());
//					myfile.transferTo(localFile);
//					
//					if(url.length() != 0){
//						url.append(",");
//						url.append(realPath + myfile.getOriginalFilename());
//					}else{
//						url.append(realPath + myfile.getOriginalFilename());
//					}
					byte[] bytes = myfile.getBytes();
			        String fileName = DateUtil.getNumDate()+myfile.getOriginalFilename();
					String urlImg = QiNiuUtil.upload(bytes, fileName);
					if(urlImgs.length() > 0){
						urlImgs += ",";
						urlImgs += urlImg;
					}else{
						urlImgs += urlImg;
					}
					urlImgs += urlImg;
					
				} catch (IOException e) {
					logger.error("*************上传附件错误信息:" + e.getMessage());
					logger.error("上传附件错误信息结束***************");
					return Result.wrapErrorResult(ErrorCode.UPLOAD_FAIL);
				}
			}
		}
		// request.setCharacterEncoding("utf-8");
		logger.info("*****************创建授信批复开始******************");
		WrAgreement agreement = new WrAgreement();
		agreement.setSignDate(signDate);
		agreement.setStartDate(startDate);
		agreement.setEndDate(endDate);
		agreement.setAgreementNum(agreementNum);
		agreement.setAgreementName(agreementName);
		agreement.setApplyCompany(applyCompany);
		agreement.setPlatform(platform);
		agreement.setBlRepayment(blRepayment);
		agreement.setBlOverdueInterestRate(blOverdueInterestRate);
		agreement.setBlRepaymentAccount(blRepaymentAccount);
		agreement.setBlRepaymentBank(blRepaymentBank);
		agreement.setBlRepaymentBranchBank(blRepaymentBranchBank);
		agreement.setBlYearInterestRate(blYearInterestRate);
		agreement.setZyRepayment(zyRepayment);
		agreement.setZyOverdueInterestRate(zyOverdueInterestRate);
		agreement.setZyRepaymentAccount(zyRepaymentAccount);
		agreement.setZyRepaymentBank(zyRepaymentBank);
		agreement.setZyRepaymentBranchBank(zyRepaymentBranchBank);
		agreement.setZyYearInterestRate(zyYearInterestRate);
		agreement.setCoreCompany(coreCompany);
		agreement.setFinancingAmount(financingAmount);
		agreement.setFinancingType(financingType);
		agreement.setLender(lender);
		agreement.setInvoiceMust(invoiceMust);
		agreement.setIsApproval(isApproval);
		agreement.setPledgeRate(pledgeRate);
		agreement.setImgUrl(urlImgs);
		agreement.setStatus(Constants.NUMBER_0);
		agreement.setBlChecked(blChecked);
		agreement.setZyChecked(zyChecked);
		this.init(agreement);
		try {
			return Result.wrapSuccessfulResult(agreementService.add(agreement));
		} catch (Exception e) {
			logger.error("*************创建授信批复错误信息:" + e.getMessage());
			logger.error("创建授信批复错误信息结束***************");
			return Result.wrapErrorResult(ErrorCode.ADD_FAIL);
		}
	}

	/**
	 * 新增
	 * 
	 * @param model
	 * @param status
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/add2",method = RequestMethod.POST)
	@ApiOperation(value = "创建授信批复")
	@ResponseBody
	public void saveAgreement(Model model, @RequestParam MultipartFile[] myfiles, HttpServletRequest request,HttpServletResponse response)
			{

		Map<String, Cookie> cookieMap = this.getCookieMap();
		/* 获取查询条件 */
		Date signDate = (Date) this.getViewRequestParam(cookieMap, "signDate", new Date());
		Date startDate = (Date) this.getViewRequestParam(cookieMap, "startDate", new Date());
		Date endDate = (Date) this.getViewRequestParam(cookieMap, "endDate", new Date());
		BigDecimal financingAmount = (BigDecimal) this.getViewRequestParam(cookieMap, "financingAmount",
				new BigDecimal(0));
		String agreementNum = (String) this.getViewRequestParam(cookieMap, "agreementNum", "");
		String agreementName = (String) this.getViewRequestParam(cookieMap, "agreementName", "");
		Integer financingType = (Integer) this.getViewRequestParam(cookieMap, "financingType", new Integer(0));
		String applyCompany = (String) this.getViewRequestParam(cookieMap, "applyCompany", "");
		String lender = (String) this.getViewRequestParam(cookieMap, "lender", "");
		String coreCompany = (String) this.getViewRequestParam(cookieMap, "coreCompany", "");
		String platform = (String) this.getViewRequestParam(cookieMap, "platform", "");
		BigDecimal blYearInterestRate = (BigDecimal) this.getViewRequestParam(cookieMap, "blYearInterestRate",
				new BigDecimal(0));
		BigDecimal blOverdueInterestRate = (BigDecimal) this.getViewRequestParam(cookieMap, "blOverdueInterestRate",
				new BigDecimal(0));
		String blRepayment = (String) this.getViewRequestParam(cookieMap, "blRepayment", "");
		String blRepaymentAccount = (String) this.getViewRequestParam(cookieMap, "blRepaymentAccount", "");
		String blRepaymentBank = (String) this.getViewRequestParam(cookieMap, "blRepaymentBank", "");
		String blRepaymentBranchBank = (String) this.getViewRequestParam(cookieMap, "blRepaymentBranchBank", "");
		BigDecimal pledgeRate = (BigDecimal) this.getViewRequestParam(cookieMap, "pledgeRate", new BigDecimal(0));
		BigDecimal zyYearInterestRate = (BigDecimal) this.getViewRequestParam(cookieMap, "zyYearInterestRate",
				new BigDecimal(0));
		BigDecimal zyOverdueInterestRate = (BigDecimal) this.getViewRequestParam(cookieMap, "zyOverdueInterestRate",
				new BigDecimal(0));
		String zyRepayment = (String) this.getViewRequestParam(cookieMap, "zyRepayment", "");
		String zyRepaymentAccount = (String) this.getViewRequestParam(cookieMap, "zyRepaymentAccount", "");
		String zyRepaymentBank = (String) this.getViewRequestParam(cookieMap, "zyRepaymentBank", "");
		String zyRepaymentBranchBank = (String) this.getViewRequestParam(cookieMap, "zyRepaymentBranchBank", "");
		String isApproval = (String) this.getViewRequestParam(cookieMap, "isApproval", "");
		String invoiceMust = (String) this.getViewRequestParam(cookieMap, "invoiceMust", "");
		Integer blChecked = (Integer) this.getViewRequestParam(cookieMap, "blChecked", new Integer(0));
		Integer zyChecked = (Integer) this.getViewRequestParam(cookieMap, "zyChecked", new Integer(0));

		/* 附件上传 */
		StringBuffer url = new StringBuffer();
		String urlImgs = "";
		for (MultipartFile myfile : myfiles) {
			if (myfile.isEmpty()) {
				logger.info("文件未上传");
			} else {
				logger.info("文件长度: " + myfile.getSize());
				logger.info("文件类型: " + myfile.getContentType());
				logger.info("文件名称: " + myfile.getName());
				logger.info("文件原名: " + myfile.getOriginalFilename());
				logger.info("========================================");
				// 如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中
				String realPath = request.getSession().getServletContext().getRealPath("/") + Constants.UPLOAD_DIRECTORY
						+ File.separator;
				// 如果目录不存在则创建
				File uploadDir = new File(realPath);
				if (!uploadDir.exists()) {
					uploadDir.mkdir();
				}
				// 这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
				try {
//					FileUtils.copyInputStreamToFile(myfile.getInputStream(),
//							new File(realPath, myfile.getOriginalFilename()));
//					if(url.length() != 0){
//						url.append(",");
//						url.append(realPath + myfile.getOriginalFilename());
//					}else{
//						url.append(realPath + myfile.getOriginalFilename());
//					}
					byte[] bytes = myfile.getBytes();
			        String fileName = DateUtil.getNumDate()+myfile.getOriginalFilename();
					String urlImg = QiNiuUtil.upload(bytes, fileName);
					if(urlImgs.length() > 0){
						urlImgs += ",";
						urlImgs += urlImg;
					}else{
						urlImgs += urlImg;
					}
					urlImgs += urlImg;
					
				} catch (IOException e) {
					logger.error("*************上传附件错误信息:" + e.getMessage());
					logger.error("上传附件错误信息结束***************");
					HtmlUtil.writerJson(response, "上传附件错误信息");
				}
			}
		}
		// request.setCharacterEncoding("utf-8");
		logger.info("*****************创建授信批复开始******************");
		WrAgreement agreement = new WrAgreement();
		agreement.setSignDate(signDate);
		agreement.setStartDate(startDate);
		agreement.setEndDate(endDate);
		agreement.setAgreementNum(agreementNum);
		agreement.setAgreementName(agreementName);
		agreement.setApplyCompany(applyCompany);
		agreement.setPlatform(platform);
		agreement.setBlRepayment(blRepayment);
		agreement.setBlOverdueInterestRate(blOverdueInterestRate);
		agreement.setBlRepaymentAccount(blRepaymentAccount);
		agreement.setBlRepaymentBank(blRepaymentBank);
		agreement.setBlRepaymentBranchBank(blRepaymentBranchBank);
		agreement.setBlYearInterestRate(blYearInterestRate);
		agreement.setZyRepayment(zyRepayment);
		agreement.setZyOverdueInterestRate(zyOverdueInterestRate);
		agreement.setZyRepaymentAccount(zyRepaymentAccount);
		agreement.setZyRepaymentBank(zyRepaymentBank);
		agreement.setZyRepaymentBranchBank(zyRepaymentBranchBank);
		agreement.setZyYearInterestRate(zyYearInterestRate);
		agreement.setCoreCompany(coreCompany);
		agreement.setFinancingAmount(financingAmount);
		agreement.setFinancingType(financingType);
		agreement.setLender(lender);
		agreement.setInvoiceMust(invoiceMust);
		agreement.setIsApproval(isApproval);
		agreement.setPledgeRate(pledgeRate);
		agreement.setImgUrl(url.toString());
		agreement.setStatus(Constants.NUMBER_0);
		agreement.setBlChecked(blChecked);
		agreement.setZyChecked(zyChecked);
		this.init(agreement);
		try {
			throw new Exception("fail!");
//			agreementService.add(agreement);
//			HtmlUtil.writerHtml(response, "上传成功");
		} catch (Exception e) {
			logger.error("*************创建授信批复错误信息:" + e.getMessage());
			logger.error("创建授信批复错误信息结束***************");
			HtmlUtil.writerHtml(response, "上传失败");
		}
	}
	
	/**
	 * 修改-审核
	 * 
	 * @param model
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/modify",method = RequestMethod.POST)
	@ApiOperation(value = "修改授信批复")
	@ResponseBody
	public Result<?> modifyAgreement(Model model,
			@ApiParam(name = "WrAgreement", value = "WrAgreement") WrAgreement agreement) {

		logger.info("*****************修改授信批复开始******************");
		try {
			// 当前操作者 currentUserId
			agreement.setModifier(1000);
			agreement.setLastModifyTime(new Date());
			return Result.wrapSuccessfulResult(agreementService.updateById(agreement));
		} catch (Exception e) {
			logger.error("*************修改授信批复错误信息:" + e.getMessage());
			logger.error("修改授信批复错误信息结束***************");
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
	@RequestMapping("/delete")
	@ApiOperation(value = "修改授信批复")
	@ResponseBody
	public Result<?> deleteAgreement(Model model, @ApiParam(name = "id", value = "id") Integer id,
			HttpServletRequest req) {

		logger.info("*****************删除授信批复开始******************");
		if (null == id) {
			logger.error("ID:" + ErrorCode.BLANK_ID.getMessage());
			return Result.wrapErrorResult(ErrorCode.BLANK_ID);
		}
		try {
			int result = agreementService.deleteById(id);
			if (result == Constants.NUMBER_1) {
				logger.info(Constants.DELETE_SUCCESS);
				return Result.wrapSuccessfulResult(ErrorCode.DELETE_SUCCESS.getMessage());
			} else {
				logger.error(ErrorCode.DELETE_FAIL.getMessage());
				return Result.wrapErrorResult(ErrorCode.DELETE_FAIL);
			}
		} catch (Exception e) {
			logger.error("*************删除授信批复错误信息:" + e.getMessage());
			logger.error("删除授信批复错误信息结束***************");
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
	public Result<?> BatchDeleteAgreement(Model model, @ApiParam(name = "ids", value = "ids") Integer[] ids) {

		logger.info("*****************批量删除授信批复开始******************");
		if (null == ids) {
			logger.error("IDs:" + ErrorCode.BLANK_OBJECT.getMessage());
			return Result.wrapErrorResult(ErrorCode.BLANK_OBJECT);
		}
		if (ids.length == Constants.NUMBER_0) {
			logger.error("IDs:" + ErrorCode.BLANK_ARRAY.getMessage());
			return Result.wrapErrorResult(ErrorCode.BLANK_ARRAY);
		}
		try {
			int result = agreementService.deleteByIds(ids);
			if (result == ids.length) {
				logger.info(Constants.DELETE_SUCCESS);
				return Result.wrapSuccessfulResult(ErrorCode.DELETE_SUCCESS.getMessage());
			} else {
				logger.error(ErrorCode.DELETE_FAIL.getMessage());
				return Result.wrapErrorResult(ErrorCode.DELETE_FAIL);
			}
		} catch (Exception e) {
			logger.error("*************删除授信批复错误信息:" + e.getMessage());
			logger.error("删除授信批复错误信息结束***************");
			return Result.wrapErrorResult(ErrorCode.DELETE_FAIL);
		}
	}
	
	/**
	 * Object
	 * 
	 * @param model
	 * @param status
	 * @return
	 */
	@RequestMapping(value="/getByObject",method = RequestMethod.POST)
	@ApiOperation(value = "修改授信批复")
	@ResponseBody
	public Result<?> getAgreement(Model model, WrAgreement agreement,
			HttpServletRequest req) {
		this.init(agreement);
		agreement.setStatus(Constants.NUMBER_0);
		try {
			int result = agreementService.add(agreement);
			if(result == Constants.NUMBER_1){
				logger.error(Constants.ADD_SUCCESS);
				return Result.wrapSuccessfulResult(Constants.ADD_SUCCESS);
			}else{
				logger.error(ErrorCode.ADD_FAIL.getMessage());
				return Result.wrapSuccessfulResult(ErrorCode.DELETE_FAIL);
			}
		} catch (Exception e) {
			logger.error(ErrorCode.ADD_FAIL.getMessage());
			return Result.wrapErrorResult(ErrorCode.DELETE_FAIL);
		}
	}
}
