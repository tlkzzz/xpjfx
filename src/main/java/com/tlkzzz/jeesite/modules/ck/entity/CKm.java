/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.entity;

import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.validation.constraints.NotNull;

import com.tlkzzz.jeesite.common.persistence.TreeEntity;

/**
 * 科目类别表Entity
 * @author szx
 * @version 2017-04-05
 */
public class CKm extends TreeEntity<CKm> {
	
	private static final long serialVersionUID = 1L;
	private String kmname;		// 科目名称
	private CKm parent;		// 父级Id
	private String parentIds;		// 所有父级Id
	private String kmnb;		// 科目编号
	private String kmlbid;		// 科目类别Id
	
	public CKm() {
		super();
	}

	public CKm(String id){
		super(id);
	}

	@Length(min=0, max=64, message="科目名称长度必须介于 0 和 64 之间")
	public String getKmname() {
		return kmname;
	}

	public void setKmname(String kmname) {
		this.kmname = kmname;
	}
	
	@JsonBackReference
	@NotNull(message="父级Id不能为空")
	public CKm getParent() {
		return parent;
	}

	public void setParent(CKm parent) {
		this.parent = parent;
	}
	
	@Length(min=1, max=64, message="所有父级Id长度必须介于 1 和 64 之间")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	@Length(min=0, max=64, message="科目编号长度必须介于 0 和 64 之间")
	public String getKmnb() {
		return kmnb;
	}

	public void setKmnb(String kmnb) {
		this.kmnb = kmnb;
	}
	
	@Length(min=0, max=64, message="科目类别Id长度必须介于 0 和 64 之间")
	public String getKmlbid() {
		return kmlbid;
	}

	public void setKmlbid(String kmlbid) {
		this.kmlbid = kmlbid;
	}
	
	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : "0";
	}
}