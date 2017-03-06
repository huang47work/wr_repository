package wr.com.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import wr.com.mapper.EntryticketMapper;
import wr.com.mapper.TicketGoodsMapper;
import wr.com.pojo.Entryticket;
import wr.com.pojo.TicketGoods;
import wr.com.pojo.WrInvoice;
import wr.com.pojo.WrInvoiceApply;
import wr.com.pojo.WrInvoiceApplyPage;
import wr.com.pojo.WrInvoicePage;
import wr.com.pojo.BBInfo.WrBBInfoPage;
import wr.com.pojo.BBInfo.WrBBInfoVO;
import wr.com.pojo.historyLog.WrHistoryLogVO;
import wr.com.result.Constants;
import wr.com.result.ErrorCode;
import wr.com.result.Result;
import wr.com.service.EntryticketService;
import wr.com.service.LoanApplicationService;
import wr.com.service.impl.WrBBInfoService;
import wr.com.service.impl.WrHistoryLogService;
import wr.com.service.impl.WrInvoiceApplyService;
import wr.com.service.impl.WrInvoiceService;
import wr.com.utils.DateUtil;
import wr.com.utils.QiNiuUtil;
import wr.com.utils.RandomUtils;
import wr.com.utils.StringUtil;

/**
 * 供应链款项Controller
 * 
 * @author guojie
 * @since Dec 12,2016
 * @version 1.0.1
 *
 */
@RequestMapping("invoiceApply/manage")
@Controller
@Api(value = "invoiceApply/manage", description = "供应链款项接口")
public class WrInvoiceApplyController extends BaseController{

	@Autowired
	WrHistoryLogService<WrHistoryLogVO> wrHistoryLogService;

	@Autowired
	WrInvoiceApplyService<WrInvoiceApply> wrInvoiceApplyService;

	@Autowired
	WrInvoiceService<WrInvoice> wrInvoiceService;

	@Autowired
	WrBBInfoService<WrBBInfoVO> bbInfoService;
	
	@Autowired
	EntryticketService enrtyService;

	@Autowired
	LoanApplicationService loanApplicationService;

	@Autowired
	EntryticketMapper entryticketMapper;// 入庫單

	@Autowired
	TicketGoodsMapper ticketGoodsMapper;// 關聯產品

	private static Logger logger = (Logger) LoggerFactory.getLogger(WrInvoiceApplyController.class);

	/**
	 * 查询列表
	 * 
	 * @param model
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/list",method = RequestMethod.POST)
	@ApiOperation(value = "供应链款项列表查询")
	@ResponseBody
	public Result<?> queryInvoiceApplyApplyList(Model model,
			@ApiParam(name = "InvoiceNum", value = "发票号") String invoiceNum,
			@ApiParam(name = "entryId", value = "入库单号") String entryId,
			@ApiParam(name = "status", value = "状态") String status,
			@ApiParam(name = "startDay", value = "起始日") String startDay,
			@ApiParam(name = "endDay", value = "结束日") String endDay,
			@ApiParam(name = "buyer", value = "买方") String buyer) {

		logger.info("*****************查询供应链款项列表开始******************");
		List<WrInvoiceApply> InvoiceApplyList = new ArrayList<>();
		WrInvoiceApplyPage apage = new WrInvoiceApplyPage();
		if (StringUtils.isNotBlank(invoiceNum)) {
			apage.setInvoiceNum(invoiceNum);
		}
		if (StringUtils.isNotBlank(status)) {
			apage.setStatus(Integer.valueOf(status));
		}
		if (StringUtils.isNotBlank(startDay) && StringUtils.isNotBlank(endDay)) {
			apage.setStartDay(startDay);
			apage.setEndDay(endDay);
		}
		if (StringUtils.isNotBlank(buyer)) {
			apage.setBuyer(buyer);
		}
		try {
			InvoiceApplyList = wrInvoiceApplyService.queryByList(apage);
			if (InvoiceApplyList.size() > 0) {
				//查询列表 买方（入库单买方）和总金额（入库单总金额）
				
				return Result.wrapSuccessfulResult(InvoiceApplyList);
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
	 * 查询详情
	 * 
	 * @param model
	 * @param status
	 * @return
	 */
	@RequestMapping(value ="/detail",method = RequestMethod.POST)
	@ApiOperation(value = "供应链款项详情")
	public Result<?> queryInvoiceApplyDetail(Model model, @ApiParam(name = "id", value = "ID") Integer id) {

		logger.info("*****************查询供应链款项详情开始******************");
		WrInvoiceApplyPage apage = new WrInvoiceApplyPage();
		List<WrInvoiceApply> InvoiceApplyList = new ArrayList<>();
		if (id == null) {
			return Result.wrapErrorResult(ErrorCode.BLANK_ID);
		} else {
			apage.setId(id);
		}
		try {
			InvoiceApplyList = wrInvoiceApplyService.queryByList(apage);
			if (InvoiceApplyList.size() > 0) {
				return Result.wrapSuccessfulResult(InvoiceApplyList.get(0));
			} else {
				return Result.wrapErrorResult(ErrorCode.BLANK_RESULT);
			}
		} catch (Exception e) {
			logger.error("*************查询供应链款项详情错误信息:" + e.getMessage());
			logger.error("查询供应链款项详情错误信息结束***************");
			return Result.wrapErrorResult(ErrorCode.QUERY_FAIL);
		}

	}

	/**
	 * 查询详情和入库单列表
	 * 
	 * @param model
	 * @param status
	 * @return
	 */
	@RequestMapping(value ="/detailMore",method = RequestMethod.POST)
	@ApiOperation(value = "供应链款项详情")
	public Result<?> queryInvoiceApplyDetailMore(Model model, @ApiParam(name = "id", value = "ID") Integer id) {

		logger.info("*****************查询供应链款项详情开始******************");
		WrInvoiceApplyPage apage = new WrInvoiceApplyPage();
		List<WrInvoiceApply> InvoiceApplyList = new ArrayList<>();
		if (id == null) {
			return Result.wrapErrorResult(ErrorCode.BLANK_ID);
		} else {
			apage.setId(id);
		}
		try {
			InvoiceApplyList = wrInvoiceApplyService.queryByList(apage);
			if (InvoiceApplyList.size() > 0) {
				// String ids = InvoiceApplyList.get(0).get
				return Result.wrapSuccessfulResult(InvoiceApplyList.get(0));
			} else {
				return Result.wrapErrorResult(ErrorCode.BLANK_RESULT);
			}
		} catch (Exception e) {
			logger.error("*************查询供应链款项详情错误信息:" + e.getMessage());
			logger.error("查询供应链款项详情错误信息结束***************");
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
	@ApiOperation(value = "创建供应链款项")
	@ResponseBody
	public Result<?> addInvoiceApply(Model model, String[] entryIds,String status,String buyer,String seller,double amount) {

		logger.info("*****************创建供应链款项开始******************");
		WrInvoiceApply invoiceApply = new WrInvoiceApply();
		StringBuffer sf = new StringBuffer();
		for (int i = 0; i < entryIds.length; i++) {
			if (sf.length() > 0) {
				sf.append(",");
				sf.append(entryIds[i]);
			} else {
				sf.append(entryIds[i]);
			}
		}
		try {
			invoiceApply.setEntryId(sf.toString());
			invoiceApply.setInvoiceGroupNum(RandomUtils.getRandom32(28));
			invoiceApply.setStatus(Constants.NUMBER_0);
			invoiceApply.setBuyer(buyer);
			invoiceApply.setSeller(seller);
			invoiceApply.setAmount(BigDecimal.valueOf(amount));
			Constants.init(invoiceApply);
			int result = wrInvoiceApplyService.addAndUpdate(invoiceApply,status,entryIds);
			if (result == Constants.NUMBER_1) {
				
				WrInvoiceApplyPage apage = new WrInvoiceApplyPage();
				apage.setEntryId(sf.toString());
				return Result.wrapSuccessfulResult(wrInvoiceApplyService.queryByList(apage).get(Constants.NUMBER_0));
			} else {
				return Result.wrapErrorResult(ErrorCode.ADD_FAIL);
			}
		} catch (Exception e) {
			logger.error("*************创建供应链款项错误信息:" + e.getMessage());
			logger.error("创建供应链款项错误信息结束***************");
			return Result.wrapErrorResult(ErrorCode.ADD_FAIL);
		}
	}

	/**
	 * 修改
	 * 
	 * @see 添加发票后提交接口
	 * @param model
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/modify",method = RequestMethod.POST)
	@ApiOperation(value = "修改供应链款项")
	@ResponseBody
	public Result<?> modifyInvoiceApply(Model model,
			@ApiParam(name = "WrInvoiceApply", value = "WrInvoiceApply") WrInvoiceApply InvoiceApply) {

		logger.info("*****************修改供应链款项开始******************");
		//多张发票号，中间“，”隔开
		String invoiceNum = InvoiceApply.getInvoiceNum();
		if (StringUtil.isEmpty(invoiceNum)) {
			logger.error(ErrorCode.BLANK_INVOICE_NUM.getMessage());
			return Result.wrapErrorResult(ErrorCode.BLANK_INVOICE_NUM);
		}
		try {
			/**
			 * 应收账款列表
			 * 
			 * @see 额度：上传发票的额度总和
			 * @see 买方：收票方
			 * @see 业务类型：暂定 玉米销售
			 * 				 后续应该从Goods表中获取。通过入库单关联操作
			 */
			String[] invoiceNums = invoiceNum.split(",");
			double invoiceAmount = 0;
			String buyer = "";
			for (int i = 0; i < invoiceNums.length; i++) {
				WrInvoicePage apage = new WrInvoicePage();
				apage.setInvoiceNum(invoiceNums[i]);
				//发票金额总和
				invoiceAmount += wrInvoiceService.queryByList(apage).get(Constants.NUMBER_0).getInvoiceAmount().doubleValue();
				buyer = wrInvoiceService.queryByList(apage).get(Constants.NUMBER_0).getInvoiceTo();
			}
			
			// 当前操作者 currentUserId
			InvoiceApply.setModifier(1000);
			InvoiceApply.setLastModifyTime(new Date());
			InvoiceApply.setInvoiceNum(invoiceNum);
			InvoiceApply.setAmount(BigDecimal.valueOf(invoiceAmount));
			InvoiceApply.setBuyer(buyer);
			return Result.wrapSuccessfulResult(wrInvoiceApplyService.updateById(InvoiceApply));
		} catch (Exception e) {
			logger.error("*************修改供应链款项错误信息:" + e.getMessage());
			logger.error("修改供应链款项错误信息结束***************");
			return Result.wrapErrorResult(ErrorCode.UPDATE_FAIL);
		}
	}

	/**
	 * 修改
	 * 
	 * @see 应收账款修改
	 * @see 1.金额
	 * @see 2.驳回意见
	 * @param model
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/modifyStatus",method = RequestMethod.POST)
	@ApiOperation(value = "修改供应链款项")
	@ResponseBody
	public Result<?> modifyIApply(Model model,
			@ApiParam(name = "WrInvoiceApply", value = "WrInvoiceApply") WrInvoiceApply InvoiceApply) {

		logger.info("*****************修改供应链款项开始******************");
		try {
			// 当前操作者 currentUserId
			InvoiceApply.setModifier(1000);
			InvoiceApply.setLastModifyTime(new Date());
			// InvoiceApply.setInvoiceNum(invoiceNum);
			return Result.wrapSuccessfulResult(wrInvoiceApplyService.updateById(InvoiceApply));
		} catch (Exception e) {
			logger.error("*************修改供应链款项错误信息:" + e.getMessage());
			logger.error("修改供应链款项错误信息结束***************");
			return Result.wrapErrorResult(ErrorCode.UPDATE_FAIL);
		}
	}

	/**
	 * 修改
	 * 
	 * @see 应收账款修改
	 * @see 1.还款承诺书
	 * @see 2.承诺还款日
	 * @param model
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/modifyFile",method = RequestMethod.POST)
	@ApiOperation(value = "修改供应链款项")
	@ResponseBody
	public Result<?> modifyFile(Model model,
			@ApiParam(name = "WrInvoiceApply", value = "WrInvoiceApply") WrInvoiceApply InvoiceApply,
			@RequestParam MultipartFile[] myfiles, HttpServletRequest request) {

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
//					File localFile = new File(realPath, myfile.getOriginalFilename());
//					myfile.transferTo(localFile);
//
//					if (url.length() != 0) {
//						url.append(",");
//						url.append(realPath + myfile.getOriginalFilename());
//					} else {
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
		logger.info("*****************修改供应链款项开始******************");
		try {
			// 当前操作者 currentUserId
			InvoiceApply.setModifier(1000);
			InvoiceApply.setLastModifyTime(new Date());
			InvoiceApply.setImgUrl(urlImgs);
			return Result.wrapSuccessfulResult(wrInvoiceApplyService.updateById(InvoiceApply));
		} catch (Exception e) {
			logger.error("*************修改供应链款项错误信息:" + e.getMessage());
			logger.error("修改供应链款项错误信息结束***************");
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
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	@ApiOperation(value = "修改供应链款项")
	@ResponseBody
	public Result<?> deleteInvoice(Model model, @ApiParam(name = "id", value = "id") Integer id) {

		logger.info("*****************删除供应链款项开始******************");
		if (null == id) {
			logger.error("ID:" + ErrorCode.BLANK_ID.getMessage());
			return Result.wrapErrorResult(ErrorCode.BLANK_ID);
		}
		try {
			int result = wrInvoiceApplyService.deleteById(id);
			if (result == 1) {
				logger.info(Constants.DELETE_SUCCESS);
				return Result.wrapSuccessfulResult(ErrorCode.DELETE_SUCCESS.getMessage());
			} else {
				logger.error(ErrorCode.DELETE_FAIL.getMessage());
				return Result.wrapErrorResult(ErrorCode.DELETE_FAIL);
			}
		} catch (Exception e) {
			logger.error("*************删除供应链款项错误信息:" + e.getMessage());
			logger.error("删除供应链款项错误信息结束***************");
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

		logger.info("*****************批量删除供应链款项开始******************");
		if (null == ids) {
			logger.error("IDs:" + ErrorCode.BLANK_OBJECT.getMessage());
			return Result.wrapErrorResult(ErrorCode.BLANK_OBJECT);
		}
		if (ids.length == Constants.NUMBER_0) {
			logger.error("IDs:" + ErrorCode.BLANK_ARRAY.getMessage());
			return Result.wrapErrorResult(ErrorCode.BLANK_ARRAY);
		}
		try {
			int result = wrInvoiceApplyService.deleteByIds(ids);
			if (result == ids.length) {
				logger.info(Constants.DELETE_SUCCESS);
				return Result.wrapSuccessfulResult(ErrorCode.DELETE_SUCCESS.getMessage());
			} else {
				logger.error(ErrorCode.DELETE_FAIL.getMessage());
				return Result.wrapErrorResult(ErrorCode.DELETE_FAIL);
			}
		} catch (Exception e) {
			logger.error("*************删除供应链款项错误信息:" + e.getMessage());
			logger.error("删除供应链款项错误信息结束***************");
			return Result.wrapErrorResult(ErrorCode.DELETE_FAIL);
		}
	}

	/**
	 * 根据应收账款ID查询入库单和发票列表
	 * 
	 * @param model
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/getInfosById",method = RequestMethod.POST)
	@ApiOperation(value = " 根据应收账款ID查询入库单和发票列表")
	@ResponseBody
	public Result<?> getInfosById(Model model,
			@ApiParam(name = "invoiceGroupNum", value = "invoiceGroupNum") String invoiceGroupNum) {

		logger.info("*****************查询操作开始******************");
		if (StringUtils.isBlank(invoiceGroupNum)) {
			logger.error("invoiceGroupNum:" + ErrorCode.BLANK_INVOICE_GROUP_NUM.getMessage());
			return Result.wrapErrorResult(ErrorCode.BLANK_INVOICE_GROUP_NUM);
		}
		try {
			WrInvoiceApply invoiceApply = wrInvoiceApplyService.queryById(invoiceGroupNum);

			if (null != invoiceApply) {

				Map<String, Object> listMap = new HashMap<>();

				/* 入库单列表查询 */
				String entryIds = invoiceApply.getEntryId();
				String[] entryIdsArray = StringUtil.split(entryIds, ",");
				double amount = 0;
				double leight = 0;
				for (int i = 0; i < entryIdsArray.length; i++) {
					Entryticket enterTicket = entryticketMapper.selectByenterId(entryIdsArray[i]);
					if(null != enterTicket){
						amount += enterTicket.getSum();
						String[] goodsArray = StringUtil.split(enterTicket.getGoodsId(), ",");
						for (int j = 0; j < goodsArray.length; j++) {
							TicketGoods goods = ticketGoodsMapper.selectByPrimaryKey(goodsArray[j]);
							leight += goods.getAmount();
						}
					}
				}
				
				if (entryIdsArray.length != Constants.NUMBER_0) {
					List<Object> entryList = enrtyService.selectByEnterIds(entryIdsArray);
//					entryList.add(amount);
//					entryList.add(leight);
					if (entryList.size() != 0) {
						listMap.put("entryList", entryList);
					}
				}
				/* 发票列表查询 */
				String invoiceNums = invoiceApply.getInvoiceNum();
				if(StringUtils.isNotBlank(invoiceNums)){
					String[] invocieArray = StringUtil.split(invoiceNums, ",");
					if (invocieArray.length != Constants.NUMBER_0) {
						List<WrInvoice> invoiceList = wrInvoiceService.queryByIds(invocieArray);
						if (invoiceList.size() != 0) {
							listMap.put("invoiceList", invoiceList);
						}
					} else {
						listMap.put("invoiceList", null);
					}
				}
				listMap.put("numbers", leight);
				listMap.put("amountSum", amount);
				listMap.put("createTime", invoiceApply.getCreateTime());
				listMap.put("amount", invoiceApply.getAmount());
				listMap.put("buyer", invoiceApply.getBuyer());
				listMap.put("seller", invoiceApply.getSeller());
				listMap.put("invoiceApply", invoiceApply);
				return Result.wrapSuccessfulResult(listMap);
			} else {
				return Result.wrapSuccessfulResult(ErrorCode.BLANK_RESULT);
			}
		} catch (Exception e) {
			logger.error("*************查询操作错误信息:" + e.getMessage());
			logger.error("查询操作错误信息结束***************");
			return Result.wrapErrorResult(ErrorCode.QUERY_FAIL);
		}
	}

	/**
	 * 消息中心列表
	 * 
	 * @param model
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/message",method = RequestMethod.POST)
	@ApiOperation(value = "首页列表")
	@ResponseBody
	public Result<?> getInfoList(Model model) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			// 应收账款
			WrInvoiceApplyPage apage = new WrInvoiceApplyPage();
			apage.setModifier(Integer.valueOf(1));
			int invoiceCount = wrInvoiceApplyService.queryByCount(apage);

			// 借款单列表
			WrBBInfoPage bbPage = new WrBBInfoPage();
			bbPage.setModifier(Integer.valueOf(1));
			int bbCount = bbInfoService.queryByCount(bbPage);
			Map<String, Object> loanMap = new HashMap<>();
			loanMap.put("col", getcol());
			loanMap.put("id", getSessionUser().getUserId());

			// 申请单列表
			resultMap.put("invoiceCount", invoiceCount);
			resultMap.put("bbCount", bbCount);
			resultMap.put("loanCount", loanApplicationService.findByName(loanMap).size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Result.wrapSuccessfulResult(resultMap);
	}
	public String getcol(){
		String col = null;
		System.out.println("#########sessionUser###########"+getSessionUser());
		int role = getSessionUser().getRole();
		if (role==0) {
			col="apply_user_id";
		}else if (role==2) {
			col="core_user_id";
		}else if (role==1) {
			col="platform_user_id";
		}else if (role==3) {
			col="finance_user_id";
		}else{
			return "************角色不对哦****************";
		}
		return col;
	}
}

