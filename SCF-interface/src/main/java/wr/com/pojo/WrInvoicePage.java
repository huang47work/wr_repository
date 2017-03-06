package wr.com.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.wangrun.util.page.BasePage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 发票基本信息
 * 
 * @author 郭杰
 * @since Dec 12,2016
 * @version 1.0.1
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WrInvoicePage extends BasePage {

	private Integer id;
	private Date createTime;
	private Date lastModifyTime;
	private Integer modifier;
	private String isDeleted;

	private String invoiceNum;

	private String invoiceCode;

	private String type;

	private BigDecimal invoiceAmount;

	private String invoiceFrom;

	private String invoiceTo;

	private Integer applyId;

	private Integer userId;

	private Integer taxPoint;

	private String invoiceImg;

	private Integer status;

	private String invoiceGroupNum;

	private Integer mailId;

	private String remark;

}