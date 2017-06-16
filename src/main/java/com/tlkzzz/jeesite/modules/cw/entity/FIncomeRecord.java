/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.cw.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

/**
 * 收入记录Entity
 * @author xrc
 * @version 2017-04-05
 */
public class FIncomeRecord extends DataEntity<FIncomeRecord> {
	
	private static final long serialVersionUID = 1L;
	private String orderId;		// 订单ID
	private String incomeAccount;		// 收入帐号
	private String traverAccount;		// 来往帐号
	private String incomeMoney;		// 收入金额
	private Date incomeDate;		// 收入时间
	private String jsr;		// 经手人
	private String incomeMode;		// 收入方式
	private String incomeType;		// 收入类型
	private String khName;    //客户名称

	private String ddbh;		//订单编号
	
	public FIncomeRecord() {
		super();
	}

	public FIncomeRecord(String id){
		super(id);
	}

	@Length(min=0, max=64, message="订单ID长度必须介于 0 和 64 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@Length(min=0, max=100, message="收入帐号长度必须介于 0 和 100 之间")
	public String getIncomeAccount() {
		return incomeAccount;
	}

	public void setIncomeAccount(String incomeAccount) {
		this.incomeAccount = incomeAccount;
	}
	
	@Length(min=0, max=100, message="来往帐号长度必须介于 0 和 100 之间")
	public String getTraverAccount() {
		return traverAccount;
	}

	public void setTraverAccount(String traverAccount) {
		this.traverAccount = traverAccount;
	}
	
	public String getIncomeMoney() {
		return incomeMoney;
	}

	public void setIncomeMoney(String incomeMoney) {
		this.incomeMoney = incomeMoney;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getIncomeDate() {
		return incomeDate;
	}

	public void setIncomeDate(Date incomeDate) {
		this.incomeDate = incomeDate;
	}
	
	@Length(min=0, max=64, message="经手人长度必须介于 0 和 64 之间")
	public String getJsr() {
		return jsr;
	}

	public void setJsr(String jsr) {
		this.jsr = jsr;
	}
	
	@Length(min=0, max=1, message="收入方式长度必须介于 0 和 1 之间")
	public String getIncomeMode() {
		return incomeMode;
	}

	public void setIncomeMode(String incomeMode) {
		this.incomeMode = incomeMode;
	}
	
	@Length(min=0, max=1, message="收入类型长度必须介于 0 和 1 之间")
	public String getIncomeType() {
		return incomeType;
	}

	public void setIncomeType(String incomeType) {
		this.incomeType = incomeType;
	}

	public String getKhName() {
		return khName;
	}

	public void setKhName(String khName) {
		this.khName = khName;
	}

	public String getDdbh() {
		return ddbh;
	}

	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}
}