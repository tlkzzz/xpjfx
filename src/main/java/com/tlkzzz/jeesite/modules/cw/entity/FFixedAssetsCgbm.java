/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.cw.entity;

import com.tlkzzz.jeesite.modules.ck.entity.CHouse;
import org.hibernate.validator.constraints.Length;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

import javax.validation.constraints.NotNull;

/**
 * 固定资产采购变卖Entity
 * @author xrc
 * @version 2017-04-05
 */
public class FFixedAssetsCgbm extends DataEntity<FFixedAssetsCgbm> {

	private static final long serialVersionUID = 1L;
	private FFixedAssets ffixedassets;		// 资产名称
	private String travelUnit;		// 往来单位
	private String paymentAccount;		// 付款账户
	private String travelAccount;		// 往来账户
	private String total;		// 合计金额
	private String paymentMode;		// 支付方式
	private String approvalStatus;		// 审核状态
	private String auditor;		// 审核人
	
	public FFixedAssetsCgbm() {
		super();
	}

	public FFixedAssetsCgbm(String id){
		super(id);
	}


	@NotNull(message = "资产名称不能为空")
	public FFixedAssets getFfixedassets() {
		return ffixedassets;
	}

	public void setFfixedassets(FFixedAssets ffixedassets) {
		this.ffixedassets = ffixedassets;
	}

	
	@Length(min=0, max=100, message="往来单位长度必须介于 0 和 100 之间")
	public String getTravelUnit() {
		return travelUnit;
	}

	public void setTravelUnit(String travelUnit) {
		this.travelUnit = travelUnit;
	}
	
	@Length(min=0, max=100, message="付款账户长度必须介于 0 和 100 之间")
	public String getPaymentAccount() {
		return paymentAccount;
	}

	public void setPaymentAccount(String paymentAccount) {
		this.paymentAccount = paymentAccount;
	}
	
	@Length(min=0, max=100, message="往来账户长度必须介于 0 和 100 之间")
	public String getTravelAccount() {
		return travelAccount;
	}

	public void setTravelAccount(String travelAccount) {
		this.travelAccount = travelAccount;
	}
	
	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
	
	@Length(min=0, max=1, message="支付方式长度必须介于 0 和 1 之间")
	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
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