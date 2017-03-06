package wr.com.pojo;

import java.math.BigDecimal;
import java.util.Date;

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
public class WrAgreement extends BaseEntity {

	/* 协议编号 */
	private String agreementNum;

	/* 协议名称 */
	private String agreementName;

	/* 签署日期 */
	private Date signDate;

	/* 融资类型 0：应收账款融资 1：预付账款融资 2：存货融资 */
	private Integer financingType;

	/* 申请融资公司 */
	private String applyCompany;

	/* 出借方:一般指金融机构 */
	private String lender;

	/* 核心企业 */
	private String coreCompany;

	/* 平台：默认网润公司，且不可修改 */
	private String platform;

	/* 融资额度 */
	private BigDecimal financingAmount;

	/* 协议起始日 */
	private Date startDate;

	/* 协议结束日 */
	private Date endDate;

	private String subAgreementNum;

	/* 保理-年利率 */
	private BigDecimal blYearInterestRate;

	/* 保理-逾期利率 */
	private BigDecimal blOverdueInterestRate;

	/* 保理-还款方 */
	private String blRepayment;

	/* 保理-还款方账户 */
	private String blRepaymentAccount;

	/* 保理-还款方账户银行 */
	private String blRepaymentBank;

	/* 保理-还款方账户银行分行 */
	private String blRepaymentBranchBank;

	/* 质押率 */
	private BigDecimal pledgeRate;

	/* 质押-年利率 */
	private BigDecimal zyYearInterestRate;

	/* 质押-逾期利率 */
	private BigDecimal zyOverdueInterestRate;

	/* 质押-还款方 */
	private String zyRepayment;

	/* 质押-还款方账户 */
	private String zyRepaymentAccount;

	/* 质押-还款方账户银行 */
	private String zyRepaymentBank;

	/* 质押-还款方账户银行分行 */
	private String zyRepaymentBranchBank;

	/* 附件地址 */
	private String imgUrl;

	/* 核心企业是否要审核（Y:是，N：不是 ） */
	private String isApproval;

	/* 是否需要发票 （Y:是，N：不是） */
	private String invoiceMust;

	/* 状态：0：激活:1：失效 */
	private Integer status;

	/* 是否选择保理 */
	private Integer blChecked;

	/* 是否选择质押 */
	private Integer zyChecked;

	/* 备注 */
	private String remark;
	
	/*授信期限*/
	private String maxDate;

}