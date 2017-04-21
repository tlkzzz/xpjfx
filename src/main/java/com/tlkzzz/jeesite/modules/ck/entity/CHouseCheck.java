/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

/**
 * 仓库总盘点Entity
 * @author xrc
 * @version 2017-04-12
 */
public class CHouseCheck extends DataEntity<CHouseCheck> {
	
	private static final long serialVersionUID = 1L;
	private CHouse house;		// 仓库
	private String cbzje;		// 成本总价值
	private String sszje;		// 销售总价值
	private Date checkDate;		// 盘点时间
	private String state;		// 状态
	
	public CHouseCheck() {
		super();
	}

	public CHouseCheck(String id){
		super(id);
	}

	public CHouse getHouse() {
		return house;
	}

	public void setHouse(CHouse house) {
		this.house = house;
	}
	
	public String getCbzje() {
		return cbzje;
	}

	public void setCbzje(String cbzje) {
		this.cbzje = cbzje;
	}
	
	public String getSszje() {
		return sszje;
	}

	public void setSszje(String sszje) {
		this.sszje = sszje;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	
	@Length(min=0, max=1, message="状态长度必须介于 0 和 1 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}