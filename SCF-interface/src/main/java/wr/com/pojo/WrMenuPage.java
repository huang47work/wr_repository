package wr.com.pojo;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wangrun.util.page.BasePage;

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
public class WrMenuPage extends BasePage {

	private Integer id;
	private Date createTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:dd")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:dd", timezone = "GMT+8")
	private Date lastModifyTime;
	private Integer modifier;
	private String isDeleted;

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