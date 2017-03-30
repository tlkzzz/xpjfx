/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.entity;

import org.hibernate.validator.constraints.Length;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

import javax.validation.constraints.NotNull;

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
	
}