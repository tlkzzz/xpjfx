/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.entity;

import com.tlkzzz.jeesite.modules.cw.entity.FAccount;
import com.tlkzzz.jeesite.modules.sys.entity.User;
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
	private String yhje;		//优惠金额
	private String thsl;        //退货数量
	private String sjje;       //实际金额
	private String thje;       //退货金额
	private String zh;       //账户金额
	private String thck;       //退货仓库
	private User spr;       //审批人
	private String spzt;       //审批状态
	private Date thsj;       //退货时间
	private String sfkId;     //收付款表ID
	private FAccount fAccount;   //账号信息

	public CDdinfo() {
		super();
	}

	public CDdinfo(String id){
		super(id);
	}

	public String getSjje() {
		return sjje;
	}

	public void setSjje(String sjje) {
		this.sjje = sjje;
	}

	public String getThsl() {
		return thsl;
	}

	public void setThsl(String thsl) {
		this.thsl = thsl;
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

	@Length(min=0, max=18, message="优惠金额长度必须介于 0 和 18 之间")
	public String getYhje() {
		return yhje;
	}

	public void setYhje(String yhje) {
		this.yhje = yhje;
	}

	public String getThje() {
		return thje;
	}

	public void setThje(String thje) {
		this.thje = thje;
	}

	public String getZh() {
		return zh;
	}

	public void setZh(String zh) {
		this.zh = zh;
	}

	public String getThck() {
		return thck;
	}

	public void setThck(String thck) {
		this.thck = thck;
	}

	public User getSpr() {
		return spr;
	}

	public void setSpr(User spr) {
		this.spr = spr;
	}

	public String getSpzt() {
		return spzt;
	}

	public void setSpzt(String spzt) {
		this.spzt = spzt;
	}

	public Date getThsj() {
		return thsj;
	}

	public void setThsj(Date thsj) {
		this.thsj = thsj;
	}

	public String getSfkId() {
		return sfkId;
	}

	public void setSfkId(String sfkId) {
		this.sfkId = sfkId;
	}

	public FAccount getfAccount() {
		return fAccount;
	}

	public void setfAccount(FAccount fAccount) {
		this.fAccount = fAccount;
	}
}