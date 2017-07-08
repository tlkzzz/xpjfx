/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.entity;

import com.tlkzzz.jeesite.common.utils.excel.annotation.ExcelField;
import com.tlkzzz.jeesite.modules.cw.entity.FExpenRecord;
import com.tlkzzz.jeesite.modules.cw.entity.FPayment;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

import com.tlkzzz.jeesite.common.persistence.DataEntity;


/**
 * 仓库商品Entity
 * @author xrc
 * @version 2017-03-13ssss
 */
public class CHgoods extends DataEntity<CHgoods> {

	
	private static final long serialVersionUID = 1L;
	private CGoods goods;		// 商品
	private CHouse house;		// 仓库
	private String nub;		//商品数量
	private String yjnub;		//预警数量

	private String unit;		//单位数量
	private Double cbj;			//商品成本价
	private String rkState;		//入库类型 0进货入库1其他入库
	private String ckState;		//出库类型
	private String supplierid;	//供应商ID
	private CStore	store;		//客户
	private String qkzt;        //shizx 欠款状态
	private FPayment fPayment;		// shizx付款记录表
	private String kynub;
	public CHgoods() {
		super();
	}

	public CHgoods(String id){
		super(id);
	}

	@ExcelField(title="商品名称", align=2, sort=20)
	@NotNull(message = "商品不能为空")
	public CGoods getGoods() {
		return goods;
	}

	public void setGoods(CGoods goods) {
		this.goods = goods;
	}

	@ExcelField(title="所在仓库", align=2, sort=25)
	@NotNull(message = "仓库不能为空")
	public CHouse getHouse() {
		return house;
	}

	public void setHouse(CHouse house) {
		this.house = house;
	}

	@ExcelField(title="商品数量", align=2, sort=30)
	@Length(min=0, max=11, message="商品数量长度必须介于 0 和 11 位之间")
	public String getNub() {
		return nub;
	}

	public void setNub(String nub) {
		this.nub = nub;
	}

	@ExcelField(title="预警数", align=2, sort=35)
	public String getYjnub() {
		return yjnub;
	}

	public void setYjnub(String yjnub) {
		this.yjnub = yjnub;
	}

	@ExcelField(title="单位数量", align=2, sort=40)
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@ExcelField(title="成本价", align=2, sort=45)
	public Double getCbj() {
		return cbj;
	}

	public void setCbj(Double cbj) {
		this.cbj = cbj;
	}

	public String getRkState() {
		return rkState;
	}

	public void setRkState(String rkState) {
		this.rkState = rkState;
	}

	public String getCkState() {
		return ckState;
	}

	public void setCkState(String ckState) {
		this.ckState = ckState;
	}

	@ExcelField(title="供应商", align=2, sort=50)
	public String getSupplierid() {
		return supplierid;
	}

	public void setSupplierid(String supplierid) {
		this.supplierid = supplierid;
	}

	public CStore getStore() {
		return store;
	}

	public void setStore(CStore store) {
		this.store = store;
	}

	//shizx 新增字段getset 方法


	public String getQkzt() {
		return qkzt;
	}


	public void setQkzt(String qkzt) {
		this.qkzt = qkzt;
	}

	public FPayment getfPayment() {
		return fPayment;
	}

	public void setfPayment(FPayment fPayment) {
		this.fPayment = fPayment;
	}

	public String getKynub() {
		return kynub;
	}

	public void setKynub(String kynub) {
		this.kynub = kynub;
	}
}