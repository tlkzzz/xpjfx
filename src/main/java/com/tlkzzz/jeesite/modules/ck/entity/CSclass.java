/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.entity;

import com.tlkzzz.jeesite.common.utils.excel.annotation.ExcelField;
import org.hibernate.validator.constraints.Length;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

/**
 * 客户分类表Entity
 * @author xrc
 * @version 2017-03-13
 */
public class CSclass extends DataEntity<CSclass> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 分类名称
	
	public CSclass() {
		super();
	}

	public CSclass(String id){
		super(id);
	}

	@Length(min=1, max=64, message="分类名称长度必须介于 1 和 64 之间")
	@ExcelField(title="name", align=2, sort=60)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}