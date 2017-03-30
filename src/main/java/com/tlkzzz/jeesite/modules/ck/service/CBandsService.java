/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.ck.entity.CBands;
import com.tlkzzz.jeesite.modules.ck.dao.CBandsDao;

/**
 * 品牌Service
 * @author xrc
 * @version 2017-03-13
 */
@Service
@Transactional(readOnly = true)
public class CBandsService extends CrudService<CBandsDao, CBands> {

	public CBands get(String id) {
		return super.get(id);
	}
	
	public List<CBands> findList(CBands cBands) {
		return super.findList(cBands);
	}
	
	public Page<CBands> findPage(Page<CBands> page, CBands cBands) {
		return super.findPage(page, cBands);
	}
	
	@Transactional(readOnly = false)
	public void save(CBands cBands) {
		super.save(cBands);
	}
	
	@Transactional(readOnly = false)
	public void delete(CBands cBands) {
		super.delete(cBands);
	}
	
}