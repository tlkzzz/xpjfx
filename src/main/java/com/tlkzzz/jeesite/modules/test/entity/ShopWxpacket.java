/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.test.entity;

import org.hibernate.validator.constraints.Length;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

import javax.validation.constraints.NotNull;

/**
 * 红包Entity
 * @author xrc
 * @version 2017-03-20
 */
public class ShopWxpacket extends DataEntity<ShopWxpacket> {
	
	private static final long serialVersionUID = 1L;
	private ShopWxgoods goods;		// 微信红包主
	private String sjzfc;		// 红包P字符串
	private String spbh;		// 商品编号
	private String smr;		// 扫描人
	private String smsj;		// 扫描时间
	private String nb;		// 扫描次数
	private String state;		// 状态
	private String cjsj;		// 创建时间
	private String bz;		// 备注
	
	public ShopWxpacket() {
		super();
	}

	public ShopWxpacket(String id){
		super(id);
	}

	@NotNull(message = "主表对象不能为空")
	public ShopWxgoods getGoods() {
		return goods;
	}

	public void setGoods(ShopWxgoods goods) {
		this.goods = goods;
	}
	
	@Length(min=1, max=64, message="红包P字符串长度必须介于 1 和 64 之间")
	public String getSjzfc() {
		return sjzfc;
	}

	public void setSjzfc(String sjzfc) {
		this.sjzfc = sjzfc;
	}
	
	@Length(min=1, max=50, message="商品编号长度必须介于 1 和 50 之间")
	public String getSpbh() {
		return spbh;
	}

	public void setSpbh(String spbh) {
		this.spbh = spbh;
	}
	
	@Length(min=0, max=50, message="扫描人长度必须介于 0 和 50 之间")
	public String getSmr() {
		return smr;
	}

	public void setSmr(String smr) {
		this.smr = smr;
	}
	
	@Length(min=0, max=50, message="扫描时间长度必须介于 0 和 50 之间")
	public String getSmsj() {
		return smsj;
	}

	public void setSmsj(String smsj) {
		this.smsj = smsj;
	}
	
	@Length(min=0, max=11, message="扫描次数长度必须介于 0 和 11 之间")
	public String getNb() {
		return nb;
	}

	public void setNb(String nb) {
		this.nb = nb;
	}
	
	@Length(min=1, max=1, message="状态长度必须介于 1 和 1 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Length(min=1, max=50, message="创建时间长度必须介于 1 和 50 之间")
	public String getCjsj() {
		return cjsj;
	}

	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
	}
	
	@Length(min=0, max=200, message="备注长度必须介于 0 和 200 之间")
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
	
}