/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.cw.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.cw.entity.FArrears;
import com.tlkzzz.jeesite.modules.cw.dao.FArrearsDao;

/**
 * 欠款记录Service
 * @author xrc
 * @version 2017-04-05
 */
@Service
@Transactional(readOnly = true)
public class FArrearsService extends CrudService<FArrearsDao, FArrears> {

	public FArrears get(String id) {
		return super.get(id);
	}
	
	public List<FArrears> findList(FArrears fArrears) {
		return super.findList(fArrears);
	}
	
	public Page<FArrears> findPage(Page<FArrears> page, FArrears fArrears) {
		return super.findPage(page, fArrears);
	}
	
	@Transactional(readOnly = false)
	public void save(FArrears fArrears) {
		super.save(fArrears);
	}
	
	@Transactional(readOnly = false)
	public void delete(FArrears fArrears) {
		super.delete(fArrears);
	}
	
}