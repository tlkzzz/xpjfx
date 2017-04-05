/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.cw.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

/**
 * 待摊费用Entity
 * @author xrc
 * @version 2017-04-05
 */
public class FPropaidExpenses extends DataEntity<FPropaidExpenses> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 待摊名称
	private String subjectCode;		// 科目编码
	private String cycle;		// 分摊周期
	private Date endDate;		// 截止日期
	private String total;		// 总金额
	private String jsr;		// 经手人
	private String approvalStatus;		// 审批状态
	private String auditor;		// 审批人
	
	public FPropaidExpenses() {
		super();
	}

	public FPropaidExpenses(String id){
		super(id);
	}

	@Length(min=0, max=100, message="待摊名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=100, message="科目编码长度必须介于 0 和 100 之间")
	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	
	@Length(min=0, max=20, message="分摊周期长度必须介于 0 和 20 之间")
	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
	
	@Length(min=0, max=64, message="经手人长度必须介于 0 和 64 之间")
	public String getJsr() {
		return jsr;
	}

	public void setJsr(String jsr) {
		this.jsr = jsr;
	}
	
	@Length(min=0, max=1, message="审批状态长度必须介于 0 和 1 之间")
	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	
	@Length(min=0, max=64, message="审批人长度必须介于 0 和 64 之间")
	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}
	
}