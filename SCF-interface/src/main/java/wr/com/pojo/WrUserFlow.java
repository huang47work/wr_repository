package wr.com.pojo;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 资金流水
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
public class WrUserFlow extends BaseEntity {

	private Integer historyId;

	private BigDecimal repayAmount;

	private BigDecimal restAmount;

	private Integer repayId;

	private Integer fundType;

	private String remark;

}