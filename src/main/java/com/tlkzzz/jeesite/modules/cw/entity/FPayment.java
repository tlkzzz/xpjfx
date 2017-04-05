/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.cw.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

/**
 * 付款Entity
 * @author xrc
 * @version 2017-04-05
 */
public class FPayment extends DataEntity<FPayment> {
	
	private static final long serialVersionUID = 1L;
	private Date paymentDate;		// 付款日期
	private String paymentCode;		// 单据编号
	private String paymentAccount;		// 付款帐号
	private String travelUnit;		// 来往单位
	private String travelAccount;		// 来往帐号
	private String paymentType;		// 付款类型
	private String paymentMode;		// 付款方式
	private String jsr;		// 经手人
	private String subjectCode;		// 科目编码
	private String approvalStatus;		// 审核状态
	private String auditor;		// 审核人
	
	public FPayment() {
		super();
	}

	public FPayment(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	@Length(min=0, max=100, message="单据编号长度必须介于 0 和 100 之间")
	public String getPaymentCode() {
		return paymentCode;
	}

	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}
	
	@Length(min=0, max=100, message="付款帐号长度必须介于 0 和 100 之间")
	public String getPaymentAccount() {
		return paymentAccount;
	}

	public void setPaymentAccount(String paymentAccount) {
		this.paymentAccount = paymentAccount;
	}
	
	@Length(min=0, max=100, message="来往单位长度必须介于 0 和 100 之间")
	public String getTravelUnit() {
		return travelUnit;
	}

	public void setTravelUnit(String travelUnit) {
		this.travelUnit = travelUnit;
	}
	
	@Length(min=0, max=100, message="来往帐号长度必须介于 0 和 100 之间")
	public String getTravelAccount() {
		return travelAccount;
	}

	public void setTravelAccount(String travelAccount) {
		this.travelAccount = travelAccount;
	}
	
	@Length(min=0, max=1, message="付款类型长度必须介于 0 和 1 之间")
	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	
	@Length(min=0, max=1, message="付款方式长度必须介于 0 和 1 之间")
	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	
	@Length(min=0, max=64, message="经手人长度必须介于 0 和 64 之间")
	public String getJsr() {
		return jsr;
	}

	public void setJsr(String jsr) {
		this.jsr = jsr;
	}
	
	@Length(min=0, max=100, message="科目编码长度必须介于 0 和 100 之间")
	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	
	@Length(min=0, max=1, message="审核状态长度必须介于 0 和 1 之间")
	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	
	@Length(min=0, max=64, message="审核人长度必须介于 0 和 64 之间")
	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}
	
}