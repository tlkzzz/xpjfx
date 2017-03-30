/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.entity;

import com.tlkzzz.jeesite.common.utils.excel.annotation.ExcelField;
import org.hibernate.validator.constraints.Length;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

/**
 * 仓库表Entity
 * @author xrc
 * @version 2017-03-13
 */
public class CHouse extends DataEntity<CHouse> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 仓库名称
	private String code;		// 仓库编号
	private String state;		// 是否主仓库

	public CHouse() {
		super();
	}

	public CHouse(String id){
		super(id);
	}

	@Length(min=1, max=64, message="仓库名称长度必须介于 1 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Length(min=0, max=64, message="仓库编号长度必须介于 0 和 64 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}