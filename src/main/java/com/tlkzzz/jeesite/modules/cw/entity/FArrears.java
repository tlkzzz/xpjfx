/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.cw.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

/**
 * 欠款记录Entity
 * @author xrc
 * @version 2017-04-05
 */
public class FArrears extends DataEntity<FArrears> {
	
	private static final long serialVersionUID = 1L;
	private String arrearsUnit;		// 欠款单位
	private String arrearsType;		// 欠款类型（0客户欠，1欠供应商）
	private String arrearsMode;		// 欠款方式
	private String total;		// 欠款金额
	private Date arrearsDate;		// 欠款日期
	private Date repaymentDate;		// 还款日期
	
	public FArrears() {
		super();
	}

	public FArrears(String id){
		super(id);
	}

	@Length(min=0, max=100, message="欠款单位长度必须介于 0 和 100 之间")
	public String getArrearsUnit() {
		return arrearsUnit;
	}

	public void setArrearsUnit(String arrearsUnit) {
		this.arrearsUnit = arrearsUnit;
	}
	
	@Length(min=0, max=1, message="欠款类型（0客户欠，1欠供应商）长度必须介于 0 和 1 之间")
	public String getArrearsType() {
		return arrearsType;
	}

	public void setArrearsType(String arrearsType) {
		this.arrearsType = arrearsType;
	}
	
	@Length(min=0, max=1, message="欠款方式长度必须介于 0 和 1 之间")
	public String getArrearsMode() {
		return arrearsMode;
	}

	public void setArrearsMode(String arrearsMode) {
		this.arrearsMode = arrearsMode;
	}
	
	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getArrearsDate() {
		return arrearsDate;
	}

	public void setArrearsDate(Date arrearsDate) {
		this.arrearsDate = arrearsDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRepaymentDate() {
		return repaymentDate;
	}

	public void setRepaymentDate(Date repaymentDate) {
		this.repaymentDate = repaymentDate;
	}
	
}