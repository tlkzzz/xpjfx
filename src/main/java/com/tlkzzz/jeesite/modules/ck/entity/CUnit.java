/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.entity;

import org.hibernate.validator.constraints.Length;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

/**
 * 单位表Entity
 * @author xrc
 * @version 2017-03-13
 */
public class CUnit extends DataEntity<CUnit> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 单位名称
	
	public CUnit() {
		super();
	}

	public CUnit(String id){
		super(id);
	}

	@Length(min=1, max=64, message="单位名称长度必须介于 1 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}