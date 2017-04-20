/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.cw.entity;

import com.tlkzzz.jeesite.modules.ck.entity.CKm;
import com.tlkzzz.jeesite.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

/**
 * 转账调账Entity
 * @author xrc
 * @version 2017-04-05
 */
public class FTransferAccount extends DataEntity<FTransferAccount> {
	
	private static final long serialVersionUID = 1L;
	private String orderId;			//订单ID
	private String transferType;	// 转账调账类型 0:转账；1:应收增加；2:应收减少；3：应付增加；4：应付减少；5：资金增加；6：资金减少
	private String outAccount;		// 转出帐户
	private String inAccount;		// 转入账户
	private String travelUnit;		// 来往单位
	private String transMoney;		// 交易金额
	private Date transferDate;		// 转账日期
	private User jsr;		// 经手人
	private CKm subjectCode;		// 科目编码
	private String approvalStatus;		// 审核状态
	private User auditor;		// 审核人
	private FAccount fAccount; //账户信息
	
	public FTransferAccount() {
		super();
	}

	public FTransferAccount(String id){
		super(id);
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Length(min=0, max=1, message="转账调账类型长度必须介于 0 和 1 之间")
	public String getTransferType() {
		return transferType;
	}

	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}
	
	@Length(min=0, max=100, message="转出帐户长度必须介于 0 和 100 之间")
	public String getOutAccount() {
		return outAccount;
	}

	public void setOutAccount(String outAccount) {
		this.outAccount = outAccount;
	}
	
	@Length(min=0, max=100, message="转入账户长度必须介于 0 和 100 之间")
	public String getInAccount() {
		return inAccount;
	}

	public void setInAccount(String inAccount) {
		this.inAccount = inAccount;
	}
	
	@Length(min=0, max=100, message="来往单位长度必须介于 0 和 100 之间")
	public String getTravelUnit() {
		return travelUnit;
	}

	public void setTravelUnit(String travelUnit) {
		this.travelUnit = travelUnit;
	}
	
	public String getTransMoney() {
		return transMoney;
	}

	public void setTransMoney(String transMoney) {
		this.transMoney = transMoney;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
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

	public FAccount getfAccount() {
		return fAccount;
	}

	public void setfAccount(FAccount fAccount) {
		this.fAccount = fAccount;
	}
}