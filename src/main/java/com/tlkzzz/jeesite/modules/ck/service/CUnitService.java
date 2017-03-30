/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.ck.entity.CUnit;
import com.tlkzzz.jeesite.modules.ck.dao.CUnitDao;

/**
 * 单位表Service
 * @author xrc
 * @version 2017-03-13
 */
@Service
@Transactional(readOnly = true)
public class CUnitService extends CrudService<CUnitDao, CUnit> {

	public CUnit get(String id) {
		return super.get(id);
	}

	public List<CUnit> getname(String name) {

		return dao.getname(name);
	}
	
	public List<CUnit> findList(CUnit cUnit) {
		return super.findList(cUnit);
	}

	
	public Page<CUnit> findPage(Page<CUnit> page, CUnit cUnit) {
		return super.findPage(page, cUnit);
	}
	
	@Transactional(readOnly = false)
	public void save(CUnit cUnit) {
		super.save(cUnit);
	}
	
	@Transactional(readOnly = false)
	public void delete(CUnit cUnit) {
		super.delete(cUnit);
	}
	
}