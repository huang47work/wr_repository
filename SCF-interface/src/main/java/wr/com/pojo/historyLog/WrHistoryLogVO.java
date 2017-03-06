package wr.com.pojo.historyLog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import wr.com.pojo.BaseEntity;

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
@EqualsAndHashCode(callSuper = true)
public class WrHistoryLogVO extends BaseEntity {

	private Integer repayId;

	private Integer historyId;

	private Integer orderId;

}