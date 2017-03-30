/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.entity;

import org.hibernate.validator.constraints.Length;
import com.tlkzzz.jeesite.modules.sys.entity.User;
import javax.validation.constraints.NotNull;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

/**
 * 车辆人员表生成Entity
 * @author xrc
 * @version 2017-03-13
 */
public class CCarUser extends DataEntity<CCarUser> {
	
	private static final long serialVersionUID = 1L;
	private CCar car;		// 车辆
	private User user;		// 人员
	
	public CCarUser() {
		super();
	}

	public CCarUser(String id){
		super(id);
	}

	@NotNull(message="车辆不能为空")
	public CCar getCar() {
		return car;
	}

	public void setCar(CCar car) {
		this.car = car;
	}
	
	@NotNull(message="人员不能为空")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}