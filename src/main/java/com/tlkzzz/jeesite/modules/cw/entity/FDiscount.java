/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.cw.entity;

import org.hibernate.validator.constraints.Length;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

/**
 * 优惠表Entity
 * @author xlc
 * @version 2017-04-10
 */
public class FDiscount extends DataEntity<FDiscount> {
	
	private static final long serialVersionUID = 1L;
	private String yhje;		// 优惠金额
	private String lx;		// 类型
	private String storeid;		// 客户id
	private String ddid;		// 订单id
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
	
	@Length(min=0, max=64, message="客户id长度必须介于 0 和 64 之间")
	public String getStoreid() {
		return storeid;
	}

	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}
	
	@Length(min=0, max=64, message="订单id长度必须介于 0 和 64 之间")
	public String getDdid() {
		return ddid;
	}

	public void setDdid(String ddid) {
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