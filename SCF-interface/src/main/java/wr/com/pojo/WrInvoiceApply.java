package wr.com.pojo;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 供应链款项信息
 * 
 * @author 郭杰
 * @since Dec 12，2016
 * @version 1.0.1
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WrInvoiceApply extends BaseEntity {

	private String invoiceGroupNum;

    private String invoiceNum;

    private String entryId;

    private String imgUrl;

    private Date paymentDay;

    private String buyer;

    private String seller;

    private BigDecimal amount;

    private String advice;

    private Integer status;

    private String remark;
    
    private String startDay;
    
    private String endDay;

}