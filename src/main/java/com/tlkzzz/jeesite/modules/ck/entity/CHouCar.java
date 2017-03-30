/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.entity;

import com.tlkzzz.jeesite.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 仓库车辆表生成Entity
 * @author xrc
 * @version 2017-03-13
 */
public class CHouCar extends DataEntity<CHouCar> {
	
	private static final long serialVersionUID = 1L;
	private CHouse house;		// 仓库主键
	private CCar car;		// 车辆主键
	private User user;		//人员信息
	private List<User> userList;	//人员列表
	
	public CHouCar() {
		super();
	}

	public CHouCar(String id){
		super(id);
	}

	@NotNull(message="仓库不能为空")
	public CHouse getHouse() {
		return house;
	}

	public void setHouse(CHouse house) {
		this.house = house;
	}

	@NotNull(message="车辆不能为空")
	public CCar getCar() {
		return car;
	}

	public void setCar(CCar car) {
		this.car = car;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
}