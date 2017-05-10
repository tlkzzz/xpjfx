/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.entity;

import org.hibernate.validator.constraints.Length;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

/**
 * 规格表Entity
 * @author xrc
 * @version 2017-03-13
 */
public class CSpec extends DataEntity<CSpec> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 规格名称
	private String[] arrSpec;	// 规格数组

	public CSpec() {
		super();
	}

	public CSpec(String id){
		super(id);
	}

	@Length(min=1, max=64, message="规格名称长度必须介于 1 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getArrSpec() {
		return arrSpec;
	}

	public void setArrSpec(String[] arrSpec) {
		this.arrSpec = arrSpec;
	}
}