/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.cw.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.cw.entity.FAccount;
import com.tlkzzz.jeesite.modules.cw.dao.FAccountDao;

/**
 * 账户管理Service
 * @author xrc
 * @version 2017-04-05
 */
@Service
@Transactional(readOnly = true)
public class FAccountService extends CrudService<FAccountDao, FAccount> {

	public FAccount get(String id) {
		return super.get(id);
	}
	
	public List<FAccount> findList(FAccount fAccount) {
		return super.findList(fAccount);
	}
	
	public Page<FAccount> findPage(Page<FAccount> page, FAccount fAccount) {
		return super.findPage(page, fAccount);
	}
	
	@Transactional(readOnly = false)
	public void save(FAccount fAccount) {
		super.save(fAccount);
	}
	
	@Transactional(readOnly = false)
	public void delete(FAccount fAccount) {
		super.delete(fAccount);
	}

	@Transactional(readOnly = false)
	public void capitalHtje(FAccount fAccount) {
		dao.capitalHtje(fAccount);
	}

}