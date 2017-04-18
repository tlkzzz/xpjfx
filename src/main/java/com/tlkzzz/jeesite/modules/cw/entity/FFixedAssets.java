/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.cw.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tlkzzz.jeesite.modules.sys.entity.Office;
import com.tlkzzz.jeesite.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

import javax.validation.constraints.NotNull;

/**
 * 固定资产登记Entity
 * @author xrc
 * @version 2017-04-05
 */
public class FFixedAssets extends DataEntity<FFixedAssets> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 资产名称
	private Date payDate;		// 购买日期
	private String total;		// 折算金额
	private Office office;		// 归属部门
	private User fzr;		// 负责人
	
	public FFixedAssets() {
		super();
	}

	public FFixedAssets(String id){
		super(id);
	}

	@Length(min=0, max=100, message="资产名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	
	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	@JsonIgnore
	@NotNull(message="归属部门不能为空")
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	@JsonIgnore
	@NotNull(message="归属部门不能为空")
	public User getFzr() {
		return fzr;
	}

	public void setFzr(User fzr) {
		this.fzr = fzr;
	}
	
}