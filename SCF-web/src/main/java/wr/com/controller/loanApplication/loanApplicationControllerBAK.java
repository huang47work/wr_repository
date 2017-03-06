package wr.com.controller.loanApplication;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import wr.com.controller.BaseController;
import wr.com.pojo.ApplyMailingAddress;
import wr.com.pojo.FinancialProductsInstace;
import wr.com.pojo.LoanApplication;
import wr.com.pojo.BBInfo.WrBBInfoVO;
import wr.com.result.Constants;
import wr.com.service.LoanApplicationService;
import wr.com.utils.DateUtil;
import wr.com.utils.ErrorCode;
import wr.com.utils.MathRandom;
import wr.com.utils.RandomUtils;
import wr.com.utils.Result;
import wr.com.utils.UUid;

@SuppressWarnings("Duplicates")
@RestController
@RequestMapping("/loanApply")
@Api(value = "advice", description = "借款申请单接口")
public class loanApplicationControllerBAK extends BaseController{
	private  int flow=0;
	@Autowired
	LoanApplicationService loanApplicationServiceImpl;
	public Result<Object> skipLoanApplication(String invoiceApplyId){
		System.out.println(invoiceApplyId);	
		return null; 
	}
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ApiOperation(value = "增加申清单")
	public Result<Object> addLoanApplication(@Valid LoanApplication loanApplication){
		System.out.println("申请单增加申请单参数："+loanApplication);
		flow++;
		if (flow==10000) {
			flow = 0;
		}
		String applicationNum = DateUtil.getyyMMdd()+MathRandom.getRandomNumber(2)+String.format("%04d",flow);
		System.out.println(applicationNum);
		loanApplication.setAid(UUid.getUuid());
		loanApplication.setApplicationNum(applicationNum);
		loanApplication.setApplicationDate(new Date());
		loanApplication.setApplyUserId(getSessionUser().getUserId());
		
		if (loanApplication.getTicketServiceStatus().equals("0")) {
			loanApplication.setProcessStatus("0");
			loanApplication.setNextStatus("1");
		}
		if (loanApplication.getIfAgreement().equals("0")) {
		if (loanApplication.getTicketServiceStatus().equals("1")) {
			loanApplication.setProcessStatus("1");
			loanApplication.setCoreUserId("1070002");
			loanApplication.setPlatformUserId("1070001"); 
			loanApplication.setNextStatus("21");
			loanApplication.setNextRole("2");
		}
//		未签协议与签协议（并联方式）
//		if (loanApplication.getIfAgreement().equals("0")) {
//			if (loanApplication.getTicketServiceStatus().equals("1")) {
//				loanApplication.setProcessStatus("1");
//				loanApplication.setCoreUserId("1070002");
//				loanApplication.setPlatformUserId("1070001"); 
//				loanApplication.setNextStatus("21");
//			}
//		}else if (loanApplication.getIfAgreement().equals("1")) {
//			if (loanApplication.getTicketServiceStatus().equals("1")) {
//				loanApplication.setProcessStatus("1");
//				loanApplication.setPlatformUserId("1070001"); 
//				loanApplication.setNextStatus("31");
//			}
		} 
		
		loanApplication.setTicketStatus("0");
		System.out.println(loanApplication);
		loanApplicationServiceImpl.addLoanApplication(loanApplication);
		return Result.wrapSuccessfulResult("添加成功");
	};
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	@ApiOperation(value = "通过aid来删除申请单")
	public Result<Object> deleteLoanApplication(String aid){
		loanApplicationServiceImpl.deleteLoanApplication(aid);
		return Result.wrapSuccessfulResult("删除成功");
	};
	/**
	 * 基本数据（每部都需要）
	 * ifAgreement
	 * ticketStatus
	 * ticketServiceStatus
	 * nextStatus
	 * aid
	 * 分布数据（部分需要）
	 * applyUserId  
	 * platformUserId
	 * financeUserId
	 * coreUserId
	 * 
	 * */
	@RequestMapping(value = "/changeStatus",method = RequestMethod.POST)
	@ApiOperation(value = "更改申请单状态")
	public Result<Object> changeTicketServiceStatus(@Valid LoanApplication loanApplication,@RequestParam(required=false,value="role")String role,
			@Valid ApplyMailingAddress applyMailingAddress,
			@Valid FinancialProductsInstace financialProductsInstace
			){
		flow++;
		if (flow==10000) {
			flow = 0;
		}
		System.out.println("金融实例"+financialProductsInstace);
		System.out.println("申请单"+loanApplication);
		System.out.println("邮寄地址"+applyMailingAddress);
		String TicketServiceStatus = loanApplication.getTicketServiceStatus();
		System.out.println(TicketServiceStatus);
		loanApplication.setUpdateDate(new Date());
		loanApplication.setUpdateUserId(getSessionUser().getUserId());
		if (loanApplication.getIfAgreement().equals("0")&&loanApplication.getTicketStatus().equals("0")) {
			System.out.println("没签协议********************************");
			if (TicketServiceStatus.equals("0")&&TicketServiceStatus.equals(loanApplication.getNextStatus())) {
				loanApplication.setProcessStatus("1");
				loanApplication.setNextStatus("1");
				loanApplicationServiceImpl.updateStatus(loanApplication);
			}else if  (TicketServiceStatus.equals("1")&&TicketServiceStatus.equals(loanApplication.getNextStatus())) {
				loanApplication.setProcessStatus("1");
				loanApplication.setCoreUserId("1070002");
				loanApplication.setNextStatus("21");
				loanApplication.setNextRole("2");
				loanApplicationServiceImpl.updateStatus(loanApplication);
			}   
//			else if  (TicketServiceStatus.equals("1")&&TicketServiceStatus.equals(loanApplication.getNextStatus())) {
//				loanApplication.setProcessStatus("1");
//				loanApplication.setCoreUserId("1070002");
//				loanApplication.setPlatformUserId("1070001");     
//				loanApplication.setNextStatus("21");
//				loanApplicationServiceImpl.updateStatus(loanApplication);
//			}
//			核心与平台并联方式
//			else if  ((TicketServiceStatus.equals("21")&&role.equals("1"))||(TicketServiceStatus.equals("21")&&role.equals("2"))) {
//				if (role.equals("1")) {
//					loanApplication.setProcessStatus("21");
//					loanApplication.setNextRole("2");
//				}
//				if (role.equals("2")) {
//					loanApplication.setProcessStatus("22");
//					loanApplication.setNextRole("1");
//				}
//				loanApplication.setNextStatus("31");
//				loanApplicationServiceImpl.updateStatus(loanApplication);
//			}
//			先核心再平台
			else if  (TicketServiceStatus.equals("21")&&role.equals(loanApplication.getNextRole()))    {
				loanApplication.setProcessStatus("23");
				loanApplication.setNextStatus("31");
				loanApplication.setPlatformUserId("1070001");     
				loanApplication.setNextRole("1");
				loanApplicationServiceImpl.updateStatus(loanApplication);       
			}
			else if  (TicketServiceStatus.equals("31")&&role.equals(loanApplication.getNextRole()))    {
				loanApplication.setProcessStatus("21");
				loanApplication.setNextStatus("41");
				loanApplication.setFinanceUserId("1070003");
				loanApplication.setNextRole("3");
				loanApplicationServiceImpl.updateStatus(loanApplication);       
			}
			else if (TicketServiceStatus.equals("41")&&TicketServiceStatus.equals(loanApplication.getNextStatus())&&role.equals("3")) {
				financialProductsInstace.setIid(UUid.getUuid());
				financialProductsInstace.setProduceDate(new Date());
				loanApplication.setProcessStatus("3");
				loanApplication.setTicketStatus("1");
				loanApplication.setNextStatus("0");
				loanApplication.setFinanceUserName(getSessionUser().getUserName());
				applyMailingAddress.setApplyMid(UUid.getUuid());
				loanApplication.setApplyMid(applyMailingAddress.getApplyMid());
				String aid = loanApplication.getAid();
				LoanApplication loanApplication1 = loanApplicationServiceImpl.findByKey(aid);
				if (loanApplication1==null) {
					return Result.wrapErrorResult(ErrorCode.OPERATION_FAILED);
				}
				WrBBInfoVO wrBBInfo = new WrBBInfoVO();
				Constants.init(wrBBInfo);
				//TODO 
				wrBBInfo.setOrderId(RandomUtils.generateRandomNum() + String.format("%03d", flow));
				wrBBInfo.setOrderNum(loanApplication1.getAid());
				//TODO 修改金额类型 BigDecail
				wrBBInfo.setStatus("0");
				wrBBInfo.setBorrowerId(Integer.parseInt(loanApplication1.getApplyUserId()));
				wrBBInfo.setBorrowerName(loanApplication1.getApplyUserName());
				wrBBInfo.setBorrowAmount(BigDecimal.valueOf(loanApplication1.getSum()));
				wrBBInfo.setRemark(loanApplication1.getGroupId());
				wrBBInfo.setInstanceId(financialProductsInstace.getIid());
				wrBBInfo.setFinancialInstitutionId(loanApplication1.getFinanceUserId());
				try {
					loanApplicationServiceImpl.financialUpdateStatus(loanApplication, applyMailingAddress,wrBBInfo,financialProductsInstace);
				} catch (Exception e) {
					e.printStackTrace();
					return Result.wrapErrorResult(ErrorCode.OPERATION_FAILED);
				}
			}
			else if (TicketServiceStatus.equals("40")) {
				loanApplication.setProcessStatus("0");
				loanApplication.setTicketStatus("");
				loanApplication.setPlatformUserId("");
				loanApplication.setCoreUserId("");
				loanApplication.setFinanceUserId("");
				loanApplicationServiceImpl.updateStatus(loanApplication);
				return Result.wrapSuccessfulResult("驳回成功");
			}
			else{
				return Result.wrapErrorResult(ErrorCode.OPERATION_FAILED);
			}
			
			return Result.wrapSuccessfulResult("通过成功");
		}
		if (loanApplication.getIfAgreement().equals("1")&&loanApplication.getTicketStatus().equals("0")) {
			System.out.println("签协议********************************");
			if (TicketServiceStatus.equals("0")&&TicketServiceStatus.equals(loanApplication.getNextStatus())) {
				loanApplication.setProcessStatus("0");
				loanApplication.setNextStatus("1");
				loanApplicationServiceImpl.updateStatus(loanApplication);
			}
			else if  (TicketServiceStatus.equals("1")&&TicketServiceStatus.equals(loanApplication.getNextStatus())) {
				loanApplication.setProcessStatus("1");
				loanApplication.setPlatformUserId("1070001");
				loanApplication.setNextStatus("31");
				loanApplicationServiceImpl.updateStatus(loanApplication);
			}else if  (TicketServiceStatus.equals("31")&&role.equals("1"))    {
				System.out.println("访问到签协议的东西");
				loanApplication.setProcessStatus("23");
				loanApplication.setNextStatus("41");
				loanApplication.setNextRole("3");
				loanApplication.setFinanceUserId("1070003");
				loanApplicationServiceImpl.updateStatus(loanApplication);
			}
			else if (TicketServiceStatus.equals("41")&&TicketServiceStatus.equals(loanApplication.getNextStatus())&&role.equals("3")) {
				financialProductsInstace.setIid(UUid.getUuid());
				financialProductsInstace.setProduceDate(new Date());
				loanApplication.setProcessStatus("3");
				loanApplication.setTicketStatus("1");
				loanApplication.setNextStatus("0");
				loanApplication.setFinanceUserName(getSessionUser().getUserName());
				applyMailingAddress.setApplyMid(UUid.getUuid());
				applyMailingAddress.setUpdateDate(new Date());
				applyMailingAddress.setUpdateUserId(getSessionUser().getUserId());
				loanApplication.setApplyMid(applyMailingAddress.getApplyMid());
				String aid = loanApplication.getAid();
				LoanApplication loanApplication1 = loanApplicationServiceImpl.findByKey(aid);
				if (loanApplication1==null) {
					return Result.wrapErrorResult(ErrorCode.OPERATION_FAILED);
				}
				WrBBInfoVO wrBBInfo = new WrBBInfoVO(); 
				Constants.init(wrBBInfo);
				//TODO 
				wrBBInfo.setOrderId(RandomUtils.generateRandomNum() + String.format("%03d", flow));
				wrBBInfo.setOrderNum(loanApplication1.getAid());
				//TODO 修改金额类型 BigDecail
				wrBBInfo.setStatus("0");
				wrBBInfo.setBorrowerId(Integer.parseInt(loanApplication1.getApplyUserId()));
				wrBBInfo.setBorrowerName(loanApplication1.getApplyUserName());
				wrBBInfo.setBorrowAmount(BigDecimal.valueOf(loanApplication1.getSum()));
				wrBBInfo.setRemark(loanApplication1.getGroupId());
				wrBBInfo.setInstanceId(financialProductsInstace.getIid());
				wrBBInfo.setFinancialInstitutionId(loanApplication1.getFinanceUserId());
				try {
					loanApplicationServiceImpl.financialUpdateStatus(loanApplication, applyMailingAddress,wrBBInfo,financialProductsInstace);
				} catch (Exception e) {
					e.printStackTrace();
					return Result.wrapErrorResult(ErrorCode.OPERATION_FAILED);
				}
			}
			else if (TicketServiceStatus.equals("40")) {
				loanApplication.setProcessStatus("0");
				loanApplication.setTicketStatus("1");
				loanApplication.setPlatformUserId("");
				loanApplication.setCoreUserId("");
				loanApplication.setFinanceUserId("");
				loanApplicationServiceImpl.updateStatus(loanApplication);
				return Result.wrapSuccessfulResult("驳回成功");
			}
			else {
				return Result.wrapErrorResult(ErrorCode.OPERATION_FAILED);
			}
			return Result.wrapSuccessfulResult("通过成功");
		}
		return Result.wrapErrorResult(ErrorCode.OPERATION_FAILED);
	};
	//根据状态 0贸易公司，1核心企业，2平台，3金融机构
	@RequestMapping(value = "/findByDate",method = RequestMethod.POST)
	@ApiOperation(value = "通过日期条件查询入库单")
	public Result<Object> findByNearDate(String start,String end) throws ParseException{
		System.out.println(start);
		String col = getcol();
		String id = getSessionUser().getUserId();
		Map<String, Object> map = new HashMap<>();
		map.put("col", col);
		map.put("id", id);
		map.put("start", start);
		map.put("end", end);
		return Result.wrapSuccessfulResult(loanApplicationServiceImpl.findByNearDate(map));
	};
	@RequestMapping(value = "/findByStatus",method = RequestMethod.POST)
	public Result<Object> findByLoanApplicationServiceStatus(String ticketServiceStatus,String start,String end) throws ParseException{
	System.out.println(start.equals(null)+"^^"+end.equals(null));
	System.out.println("票状态："+ticketServiceStatus+"start:"+start+"end:"+end);
	//全部
	end = end+" 24:00:00";
	String col = getcol();
	String id = getSessionUser().getUserId();
	if (ticketServiceStatus.equals("99")) {
		ticketServiceStatus = null;
		}
		//根据状态 0贸易公司，1核心企业，2平台，3金融机构
	if (start.equals("")) {
		start=null;
	}
	if (end.equals("")) {
		end=null;
	}
	
		Map<String, Object> map = new HashMap<>();
		map.put("col", col);
		map.put("id", id);
		map.put("serviceStatus", ticketServiceStatus);
		map.put("start", start);
		map.put("end", end);
		return Result.wrapSuccessfulResult(loanApplicationServiceImpl.findByLoanApplicationServiceStatus(map));
	};
	@RequestMapping(value = "/findByName",method = RequestMethod.POST)
	@ApiOperation(value = "通过四方字段名和字段值来查询申请单")
	public Result<Object> findByName(){
		Map<String, String> map = new HashMap<>();
		map.put("col", getcol());
		map.put("id", getSessionUser().getUserId());
		return Result.wrapSuccessfulResult(loanApplicationServiceImpl.findByName(map));
	}
	@RequestMapping(value = "/findById",method = RequestMethod.POST)
	@ApiOperation(value = "通过adi来查询申请单")
	public Result<Object> findById(String aid){
		return Result.wrapSuccessfulResult(loanApplicationServiceImpl.findByKey(aid));
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
