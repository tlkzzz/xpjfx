/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.entity;

import org.hibernate.validator.constraints.Length;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

/**
 * 销售订单临时表Entity
 * @author szx
 * @version 2017-05-16
 */
public class CXsddls extends DataEntity<CXsddls> {
	
	private static final long serialVersionUID = 1L;
	private String fid;		//父级订单ID
	private String storeId;		// 客户ID
	private String nub;		//数量
	private String sj;		//售价
	private String goodsid;  //商品ID
	private String sjje; //实际金额
	private String dates;  //订货时间
	private String state;  //状态
	private int fybs;  //分页标识
	private String userid;    //用户
	
	public CXsddls() {
		super();
	}

	public CXsddls(String id){
		super(id);
	}

	@Length(min=1, max=64, message="fid长度必须介于 1 和 64 之间")
	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}
	
	@Length(min=1, max=64, message="store_id长度必须介于 1 和 64 之间")
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	
	@Length(min=1, max=11, message="nub长度必须介于 1 和 11 之间")
	public String getNub() {
		return nub;
	}

	public void setNub(String nub) {
		this.nub = nub;
	}
	
	public String getSj() {
		return sj;
	}

	public void setSj(String sj) {
		this.sj = sj;
	}

	public String getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}

	public String getSjje() {
		return sjje;
	}

	public void setSjje(String sjje) {
		this.sjje = sjje;
	}

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getFybs() {
		return fybs;
	}

	public void setFybs(int fybs) {
		this.fybs = fybs;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
}