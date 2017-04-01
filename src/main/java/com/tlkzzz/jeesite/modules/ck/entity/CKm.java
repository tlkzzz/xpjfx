/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.entity;

import org.hibernate.validator.constraints.Length;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

/**
 * 科目类别表Entity
 * @author szx
 * @version 2017-03-31
 */
public class CKm extends DataEntity<CKm> {
	
	private static final long serialVersionUID = 1L;
	private String kmlbid;		// 科目类别ID
	private String kmnb;		// 科目编号
	private String kmname;		// 科目名称
	private String zh;		// 账户
	private String je;		// 余额
	
	public CKm() {
		super();
	}

	public CKm(String id){
		super(id);
	}

	@Length(min=0, max=64, message="科目类别ID长度必须介于 0 和 64 之间")
	public String getKmlbid() {
		return kmlbid;
	}

	public void setKmlbid(String kmlbid) {
		this.kmlbid = kmlbid;
	}
	
	@Length(min=0, max=64, message="科目编号长度必须介于 0 和 64 之间")
	public String getKmnb() {
		return kmnb;
	}

	public void setKmnb(String kmnb) {
		this.kmnb = kmnb;
	}
	
	@Length(min=0, max=64, message="科目名称长度必须介于 0 和 64 之间")
	public String getKmname() {
		return kmname;
	}

	public void setKmname(String kmname) {
		this.kmname = kmname;
	}
	
	@Length(min=0, max=64, message="账户长度必须介于 0 和 64 之间")
	public String getZh() {
		return zh;
	}

	public void setZh(String zh) {
		this.zh = zh;
	}
	
	public String getJe() {
		return je;
	}

	public void setJe(String je) {
		this.je = je;
	}
	
}