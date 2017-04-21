/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.cw.entity;

import com.tlkzzz.jeesite.modules.ck.entity.CRkckddinfo;
import com.tlkzzz.jeesite.modules.ck.entity.CStore;
import org.hibernate.validator.constraints.Length;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

import javax.validation.constraints.NotNull;

/**
 * 优惠表Entity
 * @author xlc
 * @version 2017-04-10
 */
public class FDiscount extends DataEntity<FDiscount> {
	
	private static final long serialVersionUID = 1L;
	private String yhje;		// 优惠金额
	private String lx;		// 类型	0:优惠，1:总体抹零
	private CStore storeid;		// 客户id
	private CRkckddinfo ddid;		// 订单id
	private String remaks;		// 备注
	
	public FDiscount() {
		super();
	}

	public FDiscount(String id){
		super(id);
	}

	public String getYhje() {
		return yhje;
	}

	public void setYhje(String yhje) {
		this.yhje = yhje;
	}
	
	@Length(min=0, max=1, message="类型长度必须介于 0 和 1 之间")
	public String getLx() {
		return lx;
	}

	public void setLx(String lx) {
		this.lx = lx;
	}

	@NotNull(message = "客户名称不能为空")
	public CStore getStoreid() {
		return storeid;
	}

	public void setStoreid(CStore storeid) {
		this.storeid = storeid;
	}

	@NotNull(message = "订单编号不能为空")
	public CRkckddinfo getDdid() {
		return ddid;
	}

	public void setDdid(CRkckddinfo ddid) {
		this.ddid = ddid;
	}
	
	@Length(min=0, max=255, message="备注长度必须介于 0 和 255 之间")
	public String getRemaks() {
		return remaks;
	}

	public void setRemaks(String remaks) {
		this.remaks = remaks;
	}
	
}