package wr.com.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * baseEntity
 * 
 * @author 郭杰
 * @since Dec 6,2016
 * @version 1.0.1
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BaseEntity {
	private Integer id;
	private Date createTime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:dd")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:dd",timezone = "GMT+8")
	private Date lastModifyTime;
	private Integer modifier;
	private String isDeleted;

}
