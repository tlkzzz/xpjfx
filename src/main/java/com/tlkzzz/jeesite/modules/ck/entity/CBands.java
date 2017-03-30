/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.entity;

import org.hibernate.validator.constraints.Length;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

/**
 * 品牌Entity
 * @author xrc
 * @version 2017-03-13
 */
public class CBands extends DataEntity<CBands> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 品牌名称
	
	public CBands() {
		super();
	}

	public CBands(String id){
		super(id);
	}

	@Length(min=1, max=64, message="品牌名称长度必须介于 1 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}