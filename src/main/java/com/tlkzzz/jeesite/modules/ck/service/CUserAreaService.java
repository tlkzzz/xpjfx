/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.ck.entity.CUserArea;
import com.tlkzzz.jeesite.modules.ck.dao.CUserAreaDao;

/**
 * 人员区域表Service
 * @author xrc
 * @version 2017-03-13
 */
@Service
@Transactional(readOnly = true)
public class CUserAreaService extends CrudService<CUserAreaDao, CUserArea> {

	
	public CUserArea get(String id) {
		CUserArea cUserArea = super.get(id);
		return cUserArea;
	}
	
	public List<CUserArea> findList(CUserArea cUserArea) {
		return super.findList(cUserArea);
	}
	
	public Page<CUserArea> findPage(Page<CUserArea> page, CUserArea cUserArea) {
		return super.findPage(page, cUserArea);
	}
	
	@Transactional(readOnly = false)
	public void save(CUserArea cUserArea) {
		super.save(cUserArea);
	}
	
	@Transactional(readOnly = false)
	public void delete(CUserArea cUserArea) {
		super.delete(cUserArea);
	}
	
}