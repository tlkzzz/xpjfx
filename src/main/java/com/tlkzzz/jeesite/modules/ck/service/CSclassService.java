/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.ck.entity.CSclass;
import com.tlkzzz.jeesite.modules.ck.dao.CSclassDao;

/**
 * 客户分类表Service
 * @author xrc
 * @version 2017-03-13
 */
@Service
@Transactional(readOnly = true)
public class CSclassService extends CrudService<CSclassDao, CSclass> {

	public CSclass get(String id) {
		return super.get(id);
	}
	
	public List<CSclass> findList(CSclass cSclass) {
		return super.findList(cSclass);
	}
	
	public Page<CSclass> findPage(Page<CSclass> page, CSclass cSclass) {
		return super.findPage(page, cSclass);
	}
	
	@Transactional(readOnly = false)
	public void save(CSclass cSclass) {
		super.save(cSclass);
	}
	
	@Transactional(readOnly = false)
	public void delete(CSclass cSclass) {
		super.delete(cSclass);
	}
	
}