package wr.com.pojo;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 地区
 * 
 * @author 郭杰
 * @since Dec 19，2016
 * @version 1.0.1
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WrRegion extends BaseEntity{

	private Integer creator;

	private Integer parentId;

	private String regionName;

	private Integer regionType;

	private String regionNamePys;

	private String firstLetter;

	private String fullIdPath;

	private String fullNamePath;

	private BigDecimal lon;

	private BigDecimal lat;

}