/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.cw.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.cw.entity.FPropaidExpenses;
import com.tlkzzz.jeesite.modules.cw.dao.FPropaidExpensesDao;

/**
 * 待摊费用Service
 * @author xrc
 * @version 2017-04-05
 */
@Service
@Transactional(readOnly = true)
public class FPropaidExpensesService extends CrudService<FPropaidExpensesDao, FPropaidExpenses> {

	public FPropaidExpenses get(String id) {
		return super.get(id);
	}
	
	public List<FPropaidExpenses> findList(FPropaidExpenses fPropaidExpenses) {
		return super.findList(fPropaidExpenses);
	}
	
	public Page<FPropaidExpenses> findPage(Page<FPropaidExpenses> page, FPropaidExpenses fPropaidExpenses) {
		return super.findPage(page, fPropaidExpenses);
	}
	
	@Transactional(readOnly = false)
	public void save(FPropaidExpenses fPropaidExpenses) {
		super.save(fPropaidExpenses);
	}
	
	@Transactional(readOnly = false)
	public void delete(FPropaidExpenses fPropaidExpenses) {
		super.delete(fPropaidExpenses);
	}
	
}