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
 * 订单Entity
 * @author xrc
 * @version 2017-03-27
 */
public class CDdinfo extends DataEntity<CDdinfo> {
	
	private static final long serialVersionUID = 1L;
	private CRkckddinfo rkckddinfo;		// 入库出库总订单
	private CCgzbinfo cgzbinfo;		// 采购统计信息
	private CHouse house;		// 仓库
	private CGoods goods;		// 商品
	private CSupplier supplier;		// 供应商
	private CStore store;		// 客户
	private String je;		// 金额
	private Date rkckdate;		// 入库出库时间
	private String ddbh;		// 订单编号
	private String nub;		// 数量
	private String rkqcbj;		// 入库前成本价
	private String rksjcbj;		// 入库实际成本价
	
	public CDdinfo() {
		super();
	}

	public CDdinfo(String id){
		super(id);
	}

	@NotNull(message="总订单不能为空")
	public CRkckddinfo getRkckddinfo() {
		return rkckddinfo;
	}

	public void setRkckddinfo(CRkckddinfo rkckddinfo) {
		this.rkckddinfo = rkckddinfo;
	}

	public CCgzbinfo getCgzbinfo() {
		return cgzbinfo;
	}

	public void setCgzbinfo(CCgzbinfo cgzbinfo) {
		this.cgzbinfo = cgzbinfo;
	}

	public CHouse getHouse() {
		return house;
	}

	public void setHouse(CHouse house) {
		this.house = house;
	}
	
	@NotNull(message="商品不能为空")
	public CGoods getGoods() {
		return goods;
	}

	public void setGoods(CGoods goods) {
		this.goods = goods;
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
	
	public String getJe() {
		return je;
	}

	public void setJe(String je) {
		this.je = je;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRkckdate() {
		return rkckdate;
	}

	public void setRkckdate(Date rkckdate) {
		this.rkckdate = rkckdate;
	}
	
	@Length(min=0, max=64, message="订单编号长度必须介于 0 和 64 之间")
	public String getDdbh() {
		return ddbh;
	}

	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}
	
	@Length(min=0, max=11, message="数量长度必须介于 0 和 11 之间")
	public String getNub() {
		return nub;
	}

	public void setNub(String nub) {
		this.nub = nub;
	}
	
	public String getRkqcbj() {
		return rkqcbj;
	}

	public void setRkqcbj(String rkqcbj) {
		this.rkqcbj = rkqcbj;
	}
	
	public String getRksjcbj() {
		return rksjcbj;
	}

	public void setRksjcbj(String rksjcbj) {
		this.rksjcbj = rksjcbj;
	}
	
}