/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.cw.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

/**
 * 支出记录Entity
 * @author xrc
 * @version 2017-04-05
 */
public class FExpenRecord extends DataEntity<FExpenRecord> {
	
	private static final long serialVersionUID = 1L;
	private String orderId;		// 订单ID
	private String expenAccount;		// 支出帐号
	private String travelAccount;		// 来往帐号
	private String expenMoney;		// 支出金额
	private Date expenDate;		// 支出时间
	private String jsr;		// 经手人
	private String expenMode;		// 支出方式
	private String expenType;		// 支出类型

	private String ddbh;		//订单编号
	
	public FExpenRecord() {
		super();
	}

	public FExpenRecord(String id){
		super(id);
	}

	@Length(min=0, max=64, message="订单ID长度必须介于 0 和 64 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@Length(min=0, max=100, message="支出帐号长度必须介于 0 和 100 之间")
	public String getExpenAccount() {
		return expenAccount;
	}

	public void setExpenAccount(String expenAccount) {
		this.expenAccount = expenAccount;
	}
	
	@Length(min=0, max=100, message="来往帐号长度必须介于 0 和 100 之间")
	public String getTravelAccount() {
		return travelAccount;
	}

	public void setTravelAccount(String travelAccount) {
		this.travelAccount = travelAccount;
	}
	
	public String getExpenMoney() {
		return expenMoney;
	}

	public void setExpenMoney(String expenMoney) {
		this.expenMoney = expenMoney;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getExpenDate() {
		return expenDate;
	}

	public void setExpenDate(Date expenDate) {
		this.expenDate = expenDate;
	}
	
	@Length(min=0, max=64, message="经手人长度必须介于 0 和 64 之间")
	public String getJsr() {
		return jsr;
	}

	public void setJsr(String jsr) {
		this.jsr = jsr;
	}
	
	@Length(min=0, max=1, message="支出方式长度必须介于 0 和 1 之间")
	public String getExpenMode() {
		return expenMode;
	}

	public void setExpenMode(String expenMode) {
		this.expenMode = expenMode;
	}
	
	@Length(min=0, max=1, message="支出类型长度必须介于 0 和 1 之间")
	public String getExpenType() {
		return expenType;
	}

	public void setExpenType(String expenType) {
		this.expenType = expenType;
	}

	public String getDdbh() {
		return ddbh;
	}

	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}
}