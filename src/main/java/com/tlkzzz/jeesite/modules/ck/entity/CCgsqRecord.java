/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.entity;

import org.hibernate.validator.constraints.Length;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

/**
 * 订单申请记录表Entity
 * @author xlc
 * @version 2017-06-28
 */
public class CCgsqRecord extends DataEntity<CCgsqRecord> {
	
	private static final long serialVersionUID = 1L;
	private CGoods goods;		// 商品
	private Integer nub;		// 数量

	
	public CCgsqRecord() {
		super();
	}

	public CCgsqRecord(String id){
		super(id);
	}

	public CGoods getGoods() {
		return goods;
	}

	public void setGoods(CGoods goods) {
		this.goods = goods;
	}
	
	public Integer getNub() {
		return nub;
	}

	public void setNub(Integer nub) {
		this.nub = nub;
	}
	
}