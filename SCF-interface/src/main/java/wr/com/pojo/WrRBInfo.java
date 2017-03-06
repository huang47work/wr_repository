package wr.com.pojo;

import java.math.BigDecimal;
import java.util.Date;

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
public class WrRBInfo extends BaseEntity {

	private String repayId;

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