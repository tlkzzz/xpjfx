/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.test.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

/**
 * 微信红包Entity
 * @author xrc
 * @version 2017-03-20
 */
public class ShopWxgoods extends DataEntity<ShopWxgoods> {
	
	private static final long serialVersionUID = 1L;
	private String spbh;		// 商品编号 P+时间戳
	private String name;		// 名称
	private String xsqy;		// 销售区域
	private String scs;			// 生产商
	private String state;		// 有效0无效1
	private String scsj;		// 生产时间
	private String je;			// 金额
	private String hdmc;		// 活动名称
	private String bz;			// 备注
	private String cjsj;		// 创建时间
	private String fszmc;		// 发送者名称
	private String tgfmc;		// 提供方名称
	private String hbzfy;		// 红包祝福语
	private Integer num;		//生成红包数量
	
	public ShopWxgoods() {
		super();
	}

	public ShopWxgoods(String id){
		super(id);
	}

	@Length(min=1, max=50, message="商品编号长度必须介于 1 和 50 之间")
	public String getSpbh() {
		return spbh;
	}

	public void setSpbh(String spbh) {
		this.spbh = spbh;
	}
	
	@Length(min=1, max=50, message="名称长度必须介于 1 和 50 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=100, message="销售区域长度必须介于 1 和 100 之间")
	public String getXsqy() {
		return xsqy;
	}

	public void setXsqy(String xsqy) {
		this.xsqy = xsqy;
	}
	
	@Length(min=1, max=100, message="生产商长度必须介于 1 和 100 之间")
	public String getScs() {
		return scs;
	}

	public void setScs(String scs) {
		this.scs = scs;
	}
	
	@Length(min=1, max=1, message="有效0无效1长度必须介于 1 和 1 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public String getScsj() {
		return scsj;
	}

	public void setScsj(String scsj) {
		this.scsj = scsj;
	}
	
	@Length(min=1, max=11, message="金额长度必须介于 1 和 11 之间")
	public String getJe() {
		return je;
	}

	public void setJe(String je) {
		this.je = je;
	}
	
	@Length(min=1, max=100, message="活动名称长度必须介于 1 和 100 之间")
	public String getHdmc() {
		return hdmc;
	}

	public void setHdmc(String hdmc) {
		this.hdmc = hdmc;
	}
	
	@Length(min=1, max=100, message="备注长度必须介于 1 和 100 之间")
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public String getCjsj() {
		return cjsj;
	}

	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
	}
	
	@Length(min=1, max=50, message="发送者名称长度必须介于 1 和 50 之间")
	public String getFszmc() {
		return fszmc;
	}

	public void setFszmc(String fszmc) {
		this.fszmc = fszmc;
	}
	
	@Length(min=1, max=50, message="提供方名称长度必须介于 1 和 50 之间")
	public String getTgfmc() {
		return tgfmc;
	}

	public void setTgfmc(String tgfmc) {
		this.tgfmc = tgfmc;
	}
	
	@Length(min=1, max=50, message="红包祝福语长度必须介于 1 和 50 之间")
	public String getHbzfy() {
		return hbzfy;
	}

	public void setHbzfy(String hbzfy) {
		this.hbzfy = hbzfy;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
}