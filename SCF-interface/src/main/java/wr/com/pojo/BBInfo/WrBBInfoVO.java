package wr.com.pojo.BBInfo;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import wr.com.pojo.BaseEntity;

/**
 * 借款信息
 * 
 * @author 郭杰
 * @since Dec 7,2016
 * @version 1.0.1
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WrBBInfoVO extends BaseEntity {

	private String orderId;

	//金融机构Id
	private String financialInstitutionId;

	private String mobilePhone;

	private Integer borrowerId;

	private String orderNum;

	private BigDecimal borrowAmount;

	private BigDecimal onceInterestRate;

	private Integer feeRate;

	private BigDecimal feeAmount;

	private Integer overdueFeeRate;

	private BigDecimal breakRate;

	private BigDecimal rateMonth;

	private BigDecimal rateYear;

	private String status;

	private Date borrowApplyTime;

	private Date borrowBeginTime;

	private Date borrowEndTime;

	private Date profitBeginTime;

	private Date profitEndTime;

	private String handleType;

	private String payWay;

	private String borrower;

	private Integer infoId;

	private Integer diyaId;

	private String loanType;

	private Integer actDate;

	private Integer maxDate;

	private Integer prepaymentTime;

	private String borrowImg;

	private String remark;
	
	private String financeUserName;
	
	private String applyMid;
	
	private Date updateDate;
	
	private String processStatus;
	
	private Integer agreementId;
	
	private String instanceId;
	
	private String borrowerName;
	
	private String Fname;

}