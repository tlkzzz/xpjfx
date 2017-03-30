/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.ck.entity.CCar;
import com.tlkzzz.jeesite.modules.ck.dao.CCarDao;

/**
 * 车辆表生成Service
 * @author xrc
 * @version 2017-03-13
 */
@Service
@Transactional(readOnly = true)
public class CCarService extends CrudService<CCarDao, CCar> {

	public CCar get(String id) {
		return super.get(id);
	}

	public List<CCar> getcode(String code)
	{
		return dao.getcode(code);
	}
	public List<CCar> findList(CCar cCar) {
		return super.findList(cCar);
	}
	
	public Page<CCar> findPage(Page<CCar> page, CCar cCar) {
		return super.findPage(page, cCar);
	}
	
	@Transactional(readOnly = false)
	public void save(CCar cCar) {
		super.save(cCar);
	}
	
	@Transactional(readOnly = false)
	public void delete(CCar cCar) {
		super.delete(cCar);
	}
	
}