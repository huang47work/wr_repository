package wr.com.pojo.historyLog;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import wr.com.result.PageParam;

/**
 * 操作日志
 * 
 * @author 郭杰
 * @since Dec 6,2016
 * @version 1.0.1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WrHistoryLogPage extends PageParam {
	private Integer id;

	private Integer modifier;

	private Date createTime;

	private Date lastModifyTime;

	private String isDeleted;

	private Integer repayId;

	private Integer historyId;

	private Integer orderId;

}