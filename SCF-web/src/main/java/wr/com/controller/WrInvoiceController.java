package wr.com.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import wr.com.initBinder.DoubleEditor;
import wr.com.initBinder.FloatEditor;
import wr.com.initBinder.IntegerEditor;
import wr.com.initBinder.LongEditor;
import wr.com.pojo.WrInvoice;
import wr.com.pojo.WrInvoicePage;
import wr.com.pojo.historyLog.WrHistoryLogVO;
import wr.com.result.Constants;
import wr.com.result.ErrorCode;
import wr.com.result.Result;
import wr.com.service.impl.WrHistoryLogService;
import wr.com.service.impl.WrInvoiceService;
import wr.com.utils.DateUtil;
import wr.com.utils.QiNiuUtil;

/**
 * 发票Controller
 * 
 * @author guojie
 * @since Dec 12,2016
 * @version 1.0.1
 *
 */
@RequestMapping("invoice/manage")
@Controller
@Api(value = "invoice/manage", description = "发票接口")
public class WrInvoiceController extends BaseController {

	@Autowired
	WrHistoryLogService<WrHistoryLogVO> wrHistoryLogService;

	@Autowired
	WrInvoiceService<WrInvoice> invoiceService;

	private static Logger logger = (Logger) LoggerFactory.getLogger(WrInvoiceController.class);

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class,new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
		binder.registerCustomEditor(int.class, new IntegerEditor());
		binder.registerCustomEditor(long.class, new LongEditor());
		binder.registerCustomEditor(double.class, new DoubleEditor());
		binder.registerCustomEditor(float.class, new FloatEditor());
	}
	/**
	 * 查询列表
	 * 
	 * @param model
	 * @param status
	 * @return
	 */
	@RequestMapping(value ="/list",method = RequestMethod.POST)
	@ApiOperation(value = "发票列表查询")
	public Result<?> queryInvoiceList(Model model, @ApiParam(name = "invoiceNum", value = "发票号") String invoiceNum,
			@ApiParam(name = "invoiceCode", value = "发票 代码") String invoiceCode) {
		logger.info("*****************查询发票列表开始******************");
		WrInvoicePage invoicePage = new WrInvoicePage();
		List<WrInvoice> InvoiceList = new ArrayList<>();
		if (StringUtils.isBlank(invoiceNum) && StringUtils.isBlank(invoiceCode)) {
			// 参数为空，查询所有list
			logger.info("*********查询发票列表！**********");
		} else if (StringUtils.isBlank(invoiceCode) && StringUtils.isNotBlank(invoiceNum)) {
			invoicePage.setInvoiceNum(invoiceNum);

		} else if (StringUtils.isNotBlank(invoiceCode) && StringUtils.isBlank(invoiceNum)) {
			invoicePage.setInvoiceCode(invoiceCode);
		} else {
			invoicePage.setInvoiceNum(invoiceNum);
			invoicePage.setInvoiceCode(invoiceCode);
		}
		try {
			InvoiceList = invoiceService.queryByList(invoicePage);
			if (InvoiceList.size() > 0) {
				return Result.wrapSuccessfulResult(InvoiceList);
			} else {
				return Result.wrapErrorResult(ErrorCode.BLANK_RESULT);
			}
		} catch (Exception e) {
			logger.error("*************查询发票列表错误信息:" + e.getMessage());
			logger.error("查询发票列表错误信息结束***************");
			return Result.wrapErrorResult(ErrorCode.QUERY_FAIL);
		}

	}
	
	/**
	 * 发票号码唯一性验证
	 * 
	 * @param model
	 * @param status
	 * @return
	 */
	@RequestMapping(value ="/verfyCode",method = RequestMethod.POST)
	@ApiOperation(value = "发票号码唯一性验证")
	public Result<?> verifyCode(Model model, @ApiParam(name = "invoiceNum", value = "发票号") String invoiceNum) {
		logger.info("*****************发票号码唯一性验证开始******************");
		WrInvoicePage invoicePage = new WrInvoicePage();
		if(StringUtils.isNotBlank(invoiceNum)){
			invoicePage.setInvoiceNum(invoiceNum);
		}
		List<WrInvoice> InvoiceList = new ArrayList<>();
		try {
			InvoiceList = invoiceService.queryByList(invoicePage);
			if (InvoiceList.size() > 0) {
				return Result.wrapErrorResult(ErrorCode.VERIFY_CODE_FAIL);
			} else {
				return Result.wrapSuccessfulResult("验证通过！");
			}
		} catch (Exception e) {
			logger.error("*************发票号码唯一性验证错误信息:" + e.getMessage());
			logger.error("发票号码唯一性验证结束***************");
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
	@ApiOperation(value = "发票详情")
	public Result<?> queryInvoiceDetail(Model model, @ApiParam(name = "id", value = "ID") Integer id) {

		logger.info("*****************查询发票详情开始******************");
		List<WrInvoice> invoiceList = new ArrayList<>();
		WrInvoicePage apage = new WrInvoicePage();
		if (id == null) {
			logger.error("id:" + ErrorCode.BLANK_ID.getMessage());
			return Result.wrapErrorResult(ErrorCode.BLANK_ID);
		} else {
			apage.setId(id);
		}
		try {
			invoiceList = invoiceService.queryByList(apage);
			if (invoiceList.size() > 0) {
				return Result.wrapSuccessfulResult(invoiceList.get(0));
			} else {
				logger.error("RESULT:" + ErrorCode.BLANK_RESULT.getMessage());
				return Result.wrapErrorResult(ErrorCode.BLANK_RESULT);
			}
		} catch (Exception e) {
			logger.error("*************查询发票详情错误信息:" + e.getMessage());
			logger.error("查询发票详情错误信息结束***************");
			return Result.wrapErrorResult(ErrorCode.QUERY_FAIL);
		}

	}

	/**
	 * 新增
	 * 
	 * @param model
	 * @param status
	 * @return
	 */
	@RequestMapping(value ="/add",method = RequestMethod.POST)
	@ApiOperation(value = "创建发票")
	@ResponseBody
	public Result<?> addInvoice(Model model, @RequestParam MultipartFile[] myfiles, HttpServletRequest request) {

		Map<String, Cookie> cookieMap = this.getCookieMap();
		/* 获取查询条件 */
		String invoiceNum = (String) this.getViewRequestParam(cookieMap, "invoiceNum", "");
		String invoiceCode = (String) this.getViewRequestParam(cookieMap, "invoiceCode", "");
		//应收账款Id
		String applyId = (String) this.getViewRequestParam(cookieMap, "applyId", "");
		String invoiceFrom = (String) this.getViewRequestParam(cookieMap, "invoiceFrom", "");
		String invoiceTo = (String) this.getViewRequestParam(cookieMap, "invoiceTo", "");
		BigDecimal invoiceAmount = (BigDecimal) this.getViewRequestParam(cookieMap, "invoiceAmount", new BigDecimal(0));
		Integer taxPoint = (Integer) this.getViewRequestParam(cookieMap, "taxPoint", new Integer(0));
		Integer type = (Integer) this.getViewRequestParam(cookieMap, "type", new Integer(0));

		/* 附件上传 */
//		StringBuffer url = new StringBuffer();
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
//				String realPath = request.getSession().getServletContext().getRealPath("/") + Constants.UPLOAD_DIRECTORY
//						+ File.separator;
				// 如果目录不存在则创建
//				File uploadDir = new File(realPath);
//				if (!uploadDir.exists()) {
//					uploadDir.mkdir();
//				}
				// 这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
				try {
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
		WrInvoice invoice = new WrInvoice();
		invoice.setInvoiceImg(urlImgs);
		invoice.setInvoiceNum(invoiceNum);
		invoice.setInvoiceCode(invoiceCode);
		invoice.setInvoiceFrom(invoiceFrom);
		invoice.setInvoiceTo(invoiceTo);
		invoice.setType(type);
		invoice.setInvoiceAmount(invoiceAmount);
		invoice.setTaxPoint(taxPoint);
		logger.info("*****************创建发票开始******************");

		try {
			this.init(invoice);
			return Result.wrapSuccessfulResult(invoiceService.addAndUpdateApply(invoice, applyId,invoiceNum,invoiceAmount.doubleValue()));
		} catch (Exception e) {
			logger.error("*************创建发票错误信息:" + e.getMessage());
			logger.error("创建发票错误信息结束***************");
			return Result.wrapErrorResult(ErrorCode.ADD_FAIL);
		}
	}

	/**
	 * 修改
	 * 
	 * @param model
	 * @param status
	 * @return
	 */
	@RequestMapping(value ="/modify",method = RequestMethod.POST)
	@ApiOperation(value = "修改发票")
	@ResponseBody
	public Result<?> modifyInvoice(Model model, @ApiParam(name = "WrInvoice", value = "WrInvoice") WrInvoice invoice) {

		logger.info("*****************修改发票开始******************");
		try {
			// 当前操作者 currentUserId
			invoice.setModifier(1000);
			invoice.setLastModifyTime(new Date());
			return Result.wrapSuccessfulResult(invoiceService.updateById(invoice));
		} catch (Exception e) {
			logger.error("*************修改发票错误信息:" + e.getMessage());
			logger.error("修改发票错误信息结束***************");
			return Result.wrapErrorResult(ErrorCode.UPDATE_FAIL);
		}
	}

	/**
	 * 删除发票，删除应收账款里发票
	 * 
	 * @param model
	 * @param status
	 * @return
	 */
	@RequestMapping(value ="/delete",method = RequestMethod.POST)
	@ApiOperation(value = "删除发票")
	@ResponseBody
	public Result<?> deleteInvoice(Model model, @ApiParam(name = "id", value = "id") Integer id,
			@ApiParam(name = "id", value = "id") String applyId,
			HttpServletRequest req) {

		logger.info("*****************删除发票开始******************");
		if (null == id) {
			logger.error("ID:" + ErrorCode.BLANK_ID.getMessage());
			return Result.wrapErrorResult(ErrorCode.BLANK_ID);
		}
		try {
			int result = invoiceService.deleteByApplyId(id, applyId);
			if (result == Constants.NUMBER_1) {
				logger.info(Constants.DELETE_SUCCESS);
				return Result.wrapSuccessfulResult(ErrorCode.DELETE_SUCCESS.getMessage());
			} else {
				logger.error(ErrorCode.DELETE_FAIL.getMessage());
				return Result.wrapErrorResult(ErrorCode.DELETE_FAIL);
			}
		} catch (Exception e) {
			logger.error("*************删除发票错误信息:" + e.getMessage());
			logger.error("删除发票错误信息结束***************");
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

		logger.info("*****************批量删除发票开始******************");
		if (null == ids) {
			logger.error("IDs:" + ErrorCode.BLANK_OBJECT.getMessage());
			return Result.wrapErrorResult(ErrorCode.BLANK_OBJECT);
		}
		if (ids.length == Constants.NUMBER_0) {
			logger.error("IDs:" + ErrorCode.BLANK_ARRAY.getMessage());
			return Result.wrapErrorResult(ErrorCode.BLANK_ARRAY);
		}
		try {
			int result = invoiceService.deleteByIds(ids);
			if (result == ids.length) {
				logger.info(Constants.DELETE_SUCCESS);
				return Result.wrapSuccessfulResult(ErrorCode.DELETE_SUCCESS.getMessage());
			} else {
				logger.error(ErrorCode.DELETE_FAIL.getMessage());
				return Result.wrapErrorResult(ErrorCode.DELETE_FAIL);
			}
		} catch (Exception e) {
			logger.error("*************删除发票错误信息:" + e.getMessage());
			logger.error("删除发票错误信息结束***************");
			return Result.wrapErrorResult(ErrorCode.DELETE_FAIL);
		}
	}

}
