package com.caochf.nature.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * D. 字典
 * 
 * @author huangwenjun
 * @date 2020-07-22 10:05:52
 */
@Data
@Table(name = "C_DICT")
@ApiModel(value = "CDict",description = "D. 字典")
public class CDictEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 *  字典内码
	 */
	@ApiModelProperty(value = "字典内码")
	@Id
	private String dictId;

	/**
	 *  字典代码
	 */
	@ApiModelProperty(value = "字典代码")
	private String dictCode;

	/**
	 *  字典名称
	 */
	@ApiModelProperty(value = "字典名称")
	private String dictName;

	/**
	 *  有效性
	 */
	@ApiModelProperty(value = "有效性")
	private String isActive;

	/**
	 *  排序号
	 */
	@ApiModelProperty(value = "排序号")
	private Integer sortNo;

	
}