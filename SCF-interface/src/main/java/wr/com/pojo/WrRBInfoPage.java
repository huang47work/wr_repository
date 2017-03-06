package wr.com.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.wangrun.util.page.BasePage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 借款
 * 
 * @author 郭杰
 * @since Dec 6,2016
 * @version 1.0.1
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WrRBInfoPage extends BasePage {

	private Integer id;
	private Date createTime;
	private Date lastModifyTime;
	private Integer modifier;
	private String isDeleted;

	private Integer repayId;

	private Integer borrowId;

	private Integer periods;

	private Integer userId;

	private String mobilePhone;

	private Integer borrowerId;

	private BigDecimal shouldRepayAmount;

	private BigDecimal realRepayAmount;

	private BigDecimal shouldRepayRate;

	private BigDecimal realRepayRate;

	private BigDecimal restRepayAmount;

	private Date endDate;

	private Integer status;

	private String remark;

}