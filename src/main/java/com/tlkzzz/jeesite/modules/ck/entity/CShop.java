/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

import javax.validation.constraints.NotNull;

/**
 * 购物车Entity
 * @author xrc
 * @version 2017-03-23
 */
public class CShop extends DataEntity<CShop> {
	
	private static final long serialVersionUID = 1L;
	private String userid;		//用户ID
	private CGoods goods;		// 商品
	private String spbh;		//订单编号
	private CSupplier supplier;		// 供应商
	private CStore store;		// 客户
	private String nub;		// 数量
	private String je;		// 金额
	private String state;		// 0:入库保留，1:采购入库，2：出库录单，3：其他出库，4：报废录单，5：退货录单
	private Date xqdate;		// 需求时间
	private String  yhje;		//优惠金额
	
	public CShop() {
		super();
	}

	public CShop(String id){
		super(id);
	}

	@Length(min=0, max=64, message="用户ID必须介于 0 和 64 之间")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@NotNull(message="商品不能为空")
	public CGoods getGoods() {
		return goods;
	}

	public void setGoods(CGoods goods) {
		this.goods = goods;
	}

	@Length(min=0, max=64, message="订单编号必须介于 0 和 64 之间")
	public String getSpbh() {
		return spbh;
	}

	public void setSpbh(String spbh) {
		this.spbh = spbh;
	}

	public CSupplier getSupplier() {
		return supplier;
	}

	public void setSupplier(CSupplier supplier) {
		this.supplier = supplier;
	}
	
	public CStore getStore() {
		return store;
	}

	public void setStore(CStore store) {
		this.store = store;
	}
	
	@Length(min=0, max=11, message="数量长度必须介于 0 和 11 之间")
	public String getNub() {
		return nub;
	}

	public void setNub(String nub) {
		this.nub = nub;
	}
	
	public String getJe() {
		return je;
	}

	public void setJe(String je) {
		this.je = je;
	}
	
	@Length(min=0, max=1, message="0出库1入库长度必须介于 0 和 1 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getXqdate() {
		return xqdate;
	}

	public void setXqdate(Date xqdate) {
		this.xqdate = xqdate;
	}

	@Length(min=0, max=18, message="优惠金额长度必须介于 0 和 18 之间")
	public String getYhje() {
		return yhje;
	}

	public void setYhje(String yhje) {
		this.yhje = yhje;
	}
}