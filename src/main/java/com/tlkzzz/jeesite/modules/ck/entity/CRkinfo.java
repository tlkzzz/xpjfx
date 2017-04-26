/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.entity;

import com.tlkzzz.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 入库记录Entity
 * @author xrc
 * @version 2017-03-15
 */
public class CRkinfo extends DataEntity<CRkinfo> {
	
	private static final long serialVersionUID = 1L;
	private CGoods goods;		// 商品
	private CHouse house;		// 仓库
	private String rknub;		// 入库数量
	private String rkhnub;		// 入库后的总数量
	private String rkqcbj;		// 入库前成本价
	private String rkhcbj;		// 入库成本价
	private String cgzbid;		// 采购总订单ID
	private CSupplier supplier;	// 供应商
	private String state;		// 入库类型 0进货入库1其他入库
	private CStore storeId;      //客户ID

	private Date startDate;		//开始入库时间
	private Date endDate;		//结束入库时间
	private String total;		//入库总金额

	public CRkinfo() {
		super();
	}

	public CRkinfo(String id){
		super(id);
	}

	@NotNull(message = "商品不能为空")
	public CGoods getGoods() {
		return goods;
	}

	public void setGoods(CGoods goods) {
		this.goods = goods;
	}

	@NotNull(message = "仓库不能为空")
	public CHouse getHouse() {
		return house;
	}

	public void setHouse(CHouse house) {
		this.house = house;
	}
	
	@Length(min=0, max=11, message="入库数量长度必须介于 0 和 11 之间")
	public String getRknub() {
		return rknub;
	}

	public void setRknub(String rknub) {
		this.rknub = rknub;
	}
	
	@Length(min=0, max=11, message="入库后的总数量长度必须介于 0 和 11 之间")
	public String getRkhnub() {
		return rkhnub;
	}

	public void setRkhnub(String rkhnub) {
		this.rkhnub = rkhnub;
	}
	
	public String getRkqcbj() {
		return rkqcbj;
	}

	public void setRkqcbj(String rkqcbj) {
		this.rkqcbj = rkqcbj;
	}
	
	public String getRkhcbj() {
		return rkhcbj;
	}

	public void setRkhcbj(String rkhcbj) {
		this.rkhcbj = rkhcbj;
	}

	public CSupplier getSupplier() {
		return supplier;
	}

	public String getCgzbid() {
		return cgzbid;
	}

	public void setCgzbid(String cgzbid) {
		this.cgzbid = cgzbid;
	}

	public void setSupplier(CSupplier supplier) {
		this.supplier = supplier;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public CStore getStoreId() {
		return storeId;
	}

	public void setStoreId(CStore storeId) {
		this.storeId = storeId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

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
}