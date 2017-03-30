/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.entity;

import org.hibernate.validator.constraints.Length;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

/**
 * 人员区域表Entity
 * @author xrc
 * @version 2017-03-13
 */
public class CUserArea extends DataEntity<CUserArea> {
	
	private static final long serialVersionUID = 1L;
	private String areaId;		// 区域主键
	private String userid;		// 人员主键
	
	public CUserArea() {
		super();
	}

	public CUserArea(String id){
		super(id);
	}

	@Length(min=1, max=64, message="区域主键长度必须介于 1 和 64 之间")
	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	
	@Length(min=1, max=64, message="人员主键长度必须介于 1 和 64 之间")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
}