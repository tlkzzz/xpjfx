/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.entity;

import com.tlkzzz.jeesite.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.tlkzzz.jeesite.common.persistence.DataEntity;

import javax.validation.constraints.NotNull;

/**
 * 出库信息Entity
 * @author xrc
 * @version 2017-03-21
 */
public class CCkinfo extends DataEntity<CCkinfo> {
	
	private static final long serialVersionUID = 1L;
	private String je;		// 金额
	private String ckqcbj;		// 出库前成本价
	private String ckhcbj;		// 出库后成本价
	private String nub;		// 数量
	private CSupplier supplier;		// 供应商
	private CGoods goods;		// 商品
	private CHouse house;		// 仓库
	private CStore store;		// 客户
	private Date ckdate;		// 出库时间
	private String state;		// 出库方式1(进货退货)2(报废录单)3(其他出库)4(出库录单)9(移库出库)
	private String issp;		// 是否审批0未审批1已审批
	private User jsr;		// 经手人
	
	public CCkinfo() {
		super();
	}

	public CCkinfo(String id){
		super(id);
	}

	public String getJe() {
		return je;
	}

	public void setJe(String je) {
		this.je = je;
	}
	
	public String getCkqcbj() {
		return ckqcbj;
	}

	public void setCkqcbj(String ckqcbj) {
		this.ckqcbj = ckqcbj;
	}
	
	public String getCkhcbj() {
		return ckhcbj;
	}

	public void setCkhcbj(String ckhcbj) {
		this.ckhcbj = ckhcbj;
	}
	
	@Length(min=0, max=11, message="数量长度必须介于 0 和 11 之间")
	public String getNub() {
		return nub;
	}

	public void setNub(String nub) {
		this.nub = nub;
	}
	
	public CSupplier getSupplier() {
		return supplier;
	}

	public void setSupplier(CSupplier supplier) {
		this.supplier = supplier;
	}
	
	@NotNull(message="商品不能为空")
	public CGoods getGoods() {
		return goods;
	}

	public void setGoods(CGoods goods) {
		this.goods = goods;
	}
	
	@NotNull(message="仓库不能为空")
	public CHouse getHouse() {
		return house;
	}

	public void setHouse(CHouse house) {
		this.house = house;
	}
	
	public CStore getStore() {
		return store;
	}

	public void setStore(CStore store) {
		this.store = store;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCkdate() {
		return ckdate;
	}

	public void setCkdate(Date ckdate) {
		this.ckdate = ckdate;
	}
	
	@Length(min=0, max=1, message="出库方式1(进货退货)2(报废录单)3(其他出库)长度必须介于 0 和 1 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Length(min=1, max=1, message="是否审批0未审批1已审批长度必须介于 1 和 1 之间")
	public String getIssp() {
		return issp;
	}

	public void setIssp(String issp) {
		this.issp = issp;
	}

	public User getJsr() {
		return jsr;
	}

	public void setJsr(User jsr) {
		this.jsr = jsr;
	}
	
}