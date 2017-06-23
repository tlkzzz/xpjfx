/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.entity;

import org.hibernate.validator.constraints.Length;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 采购订单Entity
 * @author xrc
 * @version 2017-03-17
 */
public class CCgzbinfo extends DataEntity<CCgzbinfo> {
	
	private static final long serialVersionUID = 1L;
	private CGoods goods;		// 商品
	private CHouse house;		// 仓库
	private String nub;			// 采购数量
	private String jg;			// 价格
	private String rknub;		// 实际入库量
	private Date rkDate;		// 入库时间
	private CRkinfo rkinfo;		//入库记录
	private String state;		// 采购状态0子订单未审批1审批完采购中2采购完成已入库
	private String orderCode;	// 订单编号，作为采购批次编号
	
	public CCgzbinfo() {
		super();
	}

	public CCgzbinfo(String id){
		super(id);
	}

	@NotNull(message = "商品不能为空")
	public CGoods getGoods() {
		return goods;
	}

	public void setGoods(CGoods goods) {
		this.goods = goods;
	}

	public CHouse getHouse() {
		return house;
	}

	public void setHouse(CHouse house) {
		this.house = house;
	}

	@Length(min=0, max=11, message="采购数量长度必须介于 0 和 11 之间")
	public String getNub() {
		return nub;
	}

	public void setNub(String nub) {
		this.nub = nub;
	}
	
	public String getJg() {
		return jg;
	}

	public void setJg(String jg) {
		this.jg = jg;
	}
	
	public String getRknub() {
		return rknub;
	}

	public void setRknub(String rknub) {
		this.rknub = rknub;
	}
	
	public Date getRkDate() {
		return rkDate;
	}

	public void setRkDate(Date rkDate) {
		this.rkDate = rkDate;
	}

	public CRkinfo getRkinfo() {
		return rkinfo;
	}

	public void setRkinfo(CRkinfo rkinfo) {
		this.rkinfo = rkinfo;
	}

	@Length(min=0, max=1, message="采购状态长度必须介于 0 和 1 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
}