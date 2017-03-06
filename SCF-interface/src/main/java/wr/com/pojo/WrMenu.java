package wr.com.pojo;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 菜单
 * 
 * @author neoking
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WrMenu extends BaseEntity {

	private String creator;

	private String menuId;

	private String name;

	private String parentId;

	private String parentName;

	private Integer layerNum;

	private Integer isLeaf;

	private Integer orderNum;

	private String url;

	private Integer status;

	private String remark;

}