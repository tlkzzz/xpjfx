/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.cw.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.tlkzzz.jeesite.modules.ck.entity.CKm;
import com.tlkzzz.jeesite.modules.ck.entity.CStore;
import com.tlkzzz.jeesite.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

/**
 * 收款Entity
 * @author xrc
 * @version 2017-04-10
 */
public class FReceipt extends DataEntity<FReceipt> {
	
	private static final long serialVersionUID = 1L;
	private Date receiptDate;		// 收款日期
	private String receiptCode;		// 单据编号
	private CStore travelUnit;		// 来往单位
	private String travelAccount;		// 来往帐号
	private String receiptAccount;		// 收款帐号
	private String receiptType;		// 收款类型	0临时采购入库1其他入库2出库录单3其它出库4报废录单5退货录单
	private String receiptMode;		// 收款方式 0:现金 1:银行转账
	private String je;				// 收款金额
	private User jsr;				// 经手人
	private CKm subjectCode;		// 科目编码
	private String approvalStatus;		// 审核状态
	private User auditor;		// 审核人
	private String htje;		//合同金额
	private String ddbh;		//单据的订单编号
	
	public FReceipt() {
		super();
	}

	public FReceipt(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
	}
	
	@Length(min=0, max=100, message="单据编号长度必须介于 0 和 100 之间")
	public String getReceiptCode() {
		return receiptCode;
	}

	public void setReceiptCode(String receiptCode) {
		this.receiptCode = receiptCode;
	}
	
	public CStore getTravelUnit() {
		return travelUnit;
	}

	public void setTravelUnit(CStore travelUnit) {
		this.travelUnit = travelUnit;
	}
	
	@Length(min=0, max=100, message="来往帐号长度必须介于 0 和 100 之间")
	public String getTravelAccount() {
		return travelAccount;
	}

	public void setTravelAccount(String travelAccount) {
		this.travelAccount = travelAccount;
	}
	
	@Length(min=0, max=100, message="收款帐号长度必须介于 0 和 100 之间")
	public String getReceiptAccount() {
		return receiptAccount;
	}

	public void setReceiptAccount(String receiptAccount) {
		this.receiptAccount = receiptAccount;
	}
	
	@Length(min=0, max=1, message="收款类型长度必须介于 0 和 1 之间")
	public String getReceiptType() {
		return receiptType;
	}

	public void setReceiptType(String receiptType) {
		this.receiptType = receiptType;
	}
	
	@Length(min=0, max=1, message="收款方式长度必须介于 0 和 1 之间")
	public String getReceiptMode() {
		return receiptMode;
	}

	public void setReceiptMode(String receiptMode) {
		this.receiptMode = receiptMode;
	}

	@Length(min=0, max=1, message="收款金额长度必须介于 0 和 20 之间")
	public String getJe() {
		return je;
	}

	public void setJe(String je) {
		this.je = je;
	}

	public User getJsr() {
		return jsr;
	}

	public void setJsr(User jsr) {
		this.jsr = jsr;
	}
	
	public CKm getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(CKm subjectCode) {
		this.subjectCode = subjectCode;
	}

	@Length(min=0, max=1, message="审核状态长度必须介于 0 和 1 之间")
	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	
	public User getAuditor() {
		return auditor;
	}

	public void setAuditor(User auditor) {
		this.auditor = auditor;
	}

	@Length(min=0, max=20, message="合同金额长度必须介于 0 和 20 之间")
	public String getHtje() {
		return htje;
	}

	public void setHtje(String htje) {
		this.htje = htje;
	}

	public String getDdbh() {
		return ddbh;
	}

	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}
}