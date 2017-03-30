/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.ck.entity.CSpec;
import com.tlkzzz.jeesite.modules.ck.dao.CSpecDao;

/**
 * 规格表Service
 * @author xrc
 * @version 2017-03-13
 */
@Service
@Transactional(readOnly = true)
public class CSpecService extends CrudService<CSpecDao, CSpec> {

	public CSpec get(String id) {
		return super.get(id);
	}
	public List<CSpec> getname(String name) {
		return dao.getname(name);
	}

	public List<CSpec> findList(CSpec cSpec) {
		return super.findList(cSpec);
	}
	
	public Page<CSpec> findPage(Page<CSpec> page, CSpec cSpec) {
		return super.findPage(page, cSpec);
	}
	
	@Transactional(readOnly = false)
	public void save(CSpec cSpec) {
		super.save(cSpec);
	}
	
	@Transactional(readOnly = false)
	public void delete(CSpec cSpec) {
		super.delete(cSpec);
	}
	
}