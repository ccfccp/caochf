package com.caochf.nature.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * D. 字典项
 * 
 * @author huangwenjun
 * @date 2020-07-22 10:05:51
 */
@Data
@Table(name = "C_DICT_ITEM")
@ApiModel(value = "CDictItem",description = "D. 字典项")
public class CDictItemEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 *  字典项内码
	 */
	@ApiModelProperty(value = "字典项内码")
	@Id
	private String itemId;

	/**
	 *  字典内码
	 */
	@ApiModelProperty(value = "字典内码")
	private String dictId;

	/**
	 *  字典项代码
	 */
	@ApiModelProperty(value = "字典项代码")
	private String itemKey;

	/**
	 *  字典项名称
	 */
	@ApiModelProperty(value = "字典项名称")
	private String itemValue;

	/**
	 *  有效性
	 */
	@ApiModelProperty(value = "有效性")
	private String isValid;

	/**
	 *  排序号
	 */
	@ApiModelProperty(value = "排序号")
	private Integer sortNo;

	
}