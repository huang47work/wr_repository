package wr.com.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.wangrun.util.page.BasePage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 授信批复
 * 
 * @author 郭杰
 * @since Dec 16，2016
 * @version 1.0.1
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WrAgreementPage extends BasePage {

	private Integer id;
	private Date createTime;
	private Date lastModifyTime;
	private Integer modifier;
	private String isDeleted;
	private String agreementNum;

	private String agreementName;

	private Date signDate;

	private Integer financingType;

	private String applyCompany;

	private String lender;

	private String coreCompany;

	private String platform;

	private BigDecimal financingAmount;

	private Date startDate;

	private Date endDate;

	private String subAgreementNum;

	private BigDecimal blYearInterestRate;

	private BigDecimal blOverdueInterestRate;

	private String blRepayment;

	private String blRepaymentAccount;

	private String blRepaymentBank;

	private String blRepaymentBranchBank;

	private BigDecimal pledgeRate;

	private BigDecimal zyYearInterestRate;

	private BigDecimal zyOverdueInterestRate;

	private String zyRepayment;

	private String zyRepaymentAccount;

	private String zyRepaymentBank;

	private String zyRepaymentBranchBank;

	private String imgUrl;

	private Integer isApproval;

	private Integer invoiceMust;

	private Integer status;
	
	private String signStartDay;
	
	private String signEndDay;
	
	private Integer blChecked;
	
	private Integer zyChecked;

	private String remark;

}