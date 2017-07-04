/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.entity;

import org.hibernate.validator.constraints.Length;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

/**
 * app权限设置Entity
 * @author szx
 * @version 2017-06-29
 */
public class CApprole extends DataEntity<CApprole> {
	
	private static final long serialVersionUID = 1L;
	private String userid;		// userid
	private String roleid;		// roleid
	
	public CApprole() {
		super();
	}

	public CApprole(String id){
		super(id);
	}

	@Length(min=1, max=64, message="userid长度必须介于 1 和 64 之间")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Length(min=1, max=64, message="roleid长度必须介于 1 和 64 之间")
	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	
}