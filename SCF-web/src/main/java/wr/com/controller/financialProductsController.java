package wr.com.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import wr.com.mapper.FinancialProductsMapper;
import wr.com.pojo.LoanApplication;
import wr.com.pojo.WrAgreement;
import wr.com.pojo.WrAgreementPage;
import wr.com.pojo.WrInvoiceApply;
import wr.com.pojo.BBInfo.WrBBInfoPage;
import wr.com.pojo.BBInfo.WrBBInfoVO;
import wr.com.service.impl.WrAgreementService;
import wr.com.service.impl.WrBBInfoService;
import wr.com.service.impl.WrInvoiceApplyService;
import wr.com.utils.BeanAllFieldUtil;
import wr.com.utils.DateUtil;
import wr.com.utils.Result;

@RestController
@RequestMapping("/products")
@Api(value = "advice", description = "金融产品接口")

public class financialProductsController extends BaseController {

	@Autowired
	FinancialProductsMapper financialProductsMapper;
	@Autowired
	WrAgreementService<WrAgreement> agreementService;
	@Autowired
	WrBBInfoService<WrBBInfoVO> bbInfoService;
	@Autowired
	WrInvoiceApplyService<WrInvoiceApply> wrInvoiceApplyService;
	@RequestMapping(value = "/findAll",method = RequestMethod.POST)
	@ApiOperation(value = "查询所有金融产品")
	public Result<Object> findAll() {
		return Result.wrapSuccessfulResult(financialProductsMapper.findAll());
	}

	@RequestMapping(value = "/findById",method = RequestMethod.POST)
	@ApiOperation(value = "通过pid来查询金融产品")
	public Result<Object> findById(String pid, @Valid LoanApplication application) throws Exception {
		pid = "1";
		WrAgreementPage agreementPage = new WrAgreementPage();
		agreementPage.setFinancingType(1);
		agreementPage.setLender(application.getFinanceUserId());
		agreementPage.setApplyCompany(application.getApplyUserId());
		System.out.println("-----查询商品接口传来的application:"+application);
		String days = "0";
		BigDecimal financingAmount = null;
		double alreadyAmount = 0;
		double restAmount = 0;
		try {
			WrAgreement wrAgreement = agreementService.queryByList(agreementPage).get(0);
			//总额度
			financingAmount = wrAgreement.getFinancingAmount();
			WrBBInfoPage bbInfoPage = new WrBBInfoPage();

			bbInfoPage.setBorrowerId(Integer.valueOf(application.getApplyUserId()));
			List<WrBBInfoVO> bbInfoList = bbInfoService.queryByList(bbInfoPage);
		System.out.println(bbInfoList+"%%%%%%%%%%%%%");
			//已用额度
//			alreadyAmount = new BigDecimal(0);
			if (bbInfoList.size() > 0 ) {
				for (WrBBInfoVO bbinfo : bbInfoList) {
					alreadyAmount += bbinfo.getBorrowAmount().doubleValue();
					System.out.println(bbinfo.getBorrowAmount() + "--------------------");
//					alreadyAmount.add(bbinfo.getBorrowAmount());
//					System.out.println(alreadyAmount+"#$%#$%^&");
				}
			}
			//剩余额度
			restAmount = financingAmount.doubleValue()-alreadyAmount;
			
			//授信期限
			days = wrAgreement.getMaxDate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String promiseRepayDate = DateUtil.getDateLong(wrInvoiceApplyService.queryById(application.getGroupId()).getPaymentDay());
		System.out.println(promiseRepayDate+"还款承诺日期%%%%%%%%%%%%%%%%%%%%%%%%%%");
		Map map = BeanAllFieldUtil.beanToMap(financialProductsMapper.selectByPrimaryKey(pid));
		map.put("financingAmount", financingAmount);
		map.put("alreadyAmount", alreadyAmount);
		map.put("restAmount", restAmount);
		map.put("days", days);
		
		map.put("promiseRepayDate",promiseRepayDate);
		System.out.println(map);
		return Result.wrapSuccessfulResult(map);
	}
}