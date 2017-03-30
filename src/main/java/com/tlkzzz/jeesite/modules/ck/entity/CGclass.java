/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.entity;

import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.validation.constraints.NotNull;

import com.tlkzzz.jeesite.common.persistence.TreeEntity;

/**
 * 商品分类表生成Entity
 * @author xrc
 * @version 2017-03-13
 */
public class CGclass extends TreeEntity<CGclass> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 分类名称
	private CGclass parent;		// 父级编号
	private String parentIds;		// 所有父级编号
	private String code;		// 商品编码
	
	public CGclass() {
		super();
	}

	public CGclass(String id){
		super(id);
	}

	@Length(min=1, max=64, message="分类名称长度必须介于 1 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonBackReference
	@NotNull(message="父级编号不能为空")
	public CGclass getParent() {
		return parent;
	}

	public void setParent(CGclass parent) {
		this.parent = parent;
	}
	
	@Length(min=1, max=64, message="所有父级编号长度必须介于 1 和 64 之间")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	@Length(min=0, max=64, message="商品编码长度必须介于 0 和 64 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : "0";
	}
}