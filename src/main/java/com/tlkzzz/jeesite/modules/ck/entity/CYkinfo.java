/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.entity;

import org.hibernate.validator.constraints.Length;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 移库记录Entity
 * @author xrc
 * @version 2017-03-15
 */
public class CYkinfo extends DataEntity<CYkinfo> {
	
	private static final long serialVersionUID = 1L;
	private CHouse startHouse;		// 起始仓库
	private CHouse endHouse;		// 结束仓库
	private CGoods goods;			// 商品
	private String nub;			// 移库数量
	private String specNub;		//规格数量

	private Date startDate;		//报表起始时间
	private Date endDate;		//报表结束时间

	private String cbj;       //移库时成本价
	private String xsj;       //移库时销售价

	public CYkinfo() {
		super();
	}

	public CYkinfo(String id){
		super(id);
	}

	@NotNull(message = "起始仓库不能为空")
	public CHouse getStartHouse() {
		return startHouse;
	}

	public void setStartHouse(CHouse startHouse) {
		this.startHouse = startHouse;
	}

	@NotNull(message = "结束仓库不能为空")
	public CHouse getEndHouse() {
		return endHouse;
	}

	public void setEndHouse(CHouse endHouse) {
		this.endHouse = endHouse;
	}

	@NotNull(message = "商品不能为空")
	public CGoods getGoods() {
		return goods;
	}

	public void setGoods(CGoods goods) {
		this.goods = goods;
	}
	
	@Length(min=0, max=11, message="移库数量长度必须介于 0 和 11 之间")
	public String getNub() {
		return nub;
	}

	public void setNub(String nub) {
		this.nub = nub;
	}

	public String getSpecNub() {
		return specNub;
	}

	public void setSpecNub(String specNub) {
		this.specNub = specNub;
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

	public String getCbj() {
		return cbj;
	}

	public void setCbj(String cbj) {
		this.cbj = cbj;
	}

	public String getXsj() {
		return xsj;
	}

	public void setXsj(String xsj) {
		this.xsj = xsj;
	}
}