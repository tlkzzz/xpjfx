/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.service;

import java.util.List;

import com.tlkzzz.jeesite.modules.ck.dao.CCarUserDao;
import com.tlkzzz.jeesite.modules.ck.entity.CCarUser;
import com.tlkzzz.jeesite.modules.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.common.utils.StringUtils;
import com.tlkzzz.jeesite.modules.ck.entity.CHouCar;
import com.tlkzzz.jeesite.modules.ck.dao.CHouCarDao;

/**
 * 仓库车辆表生成Service
 * @author xrc
 * @version 2017-03-13
 */
@Service
@Transactional(readOnly = true)
public class CHouCarService extends CrudService<CHouCarDao, CHouCar> {

	@Autowired
	private CCarUserDao cCarUserDao;
	
	public CHouCar get(String id) {
		CHouCar cHouCar = super.get(id);
		return cHouCar;
	}
	
	public List<CHouCar> findList(CHouCar cHouCar) {
		return super.findList(cHouCar);
	}
	
	public Page<CHouCar> findPage(Page<CHouCar> page, CHouCar cHouCar) {
		page = super.findPage(page, cHouCar);
		CCarUser carUser = new CCarUser();
		for(CHouCar houCar: page.getList()){
			carUser.setCar(houCar.getCar());
			houCar.setUserList(cCarUserDao.findListByCar(carUser));
		}
		return page;
	}

	public CHouCar findUserList(CHouCar cHouCar){
		cHouCar.setUser(new User());
		CCarUser carUser = new CCarUser();
		carUser.setCar(cHouCar.getCar());
		cHouCar.setUserList(cCarUserDao.findListByCar(carUser));
		for(User user:cHouCar.getUserList()){
			cHouCar.getUser().setId(user.getId()+((StringUtils.isBlank(cHouCar.getUser().getId()))?"":
					(","+cHouCar.getUser().getId())));
			cHouCar.getUser().setName(user.getName()+((StringUtils.isBlank(cHouCar.getUser().getName()))?"":
					(","+cHouCar.getUser().getName())));
		}
		return cHouCar;
	}
	
	@Transactional(readOnly = false)
	public void save(CHouCar cHouCar) {
		if(cHouCar.getUser()!=null&&StringUtils.isNotBlank(cHouCar.getUser().getId())) {
			String[] idList = cHouCar.getUser().getId().split(",");
			CCarUser carUser = new CCarUser();
			carUser.preInsert();
			carUser.setCar(cHouCar.getCar());
			cCarUserDao.deleteByCar(carUser);
			for(String id: idList){
				carUser.setUser(new User(id));
				cCarUserDao.insert(carUser);
			}
		}
		if(dao.getByHouCar(cHouCar)==null) {
			super.save(cHouCar);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(CHouCar cHouCar) {
		super.delete(cHouCar);
	}
	
}