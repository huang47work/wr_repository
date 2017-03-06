package wr.com.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 收款
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
public class WrRepayBack extends BaseEntity {

	private Integer repayId;

	private Integer borrowId;

	private Integer userId;

	private String remark;

}