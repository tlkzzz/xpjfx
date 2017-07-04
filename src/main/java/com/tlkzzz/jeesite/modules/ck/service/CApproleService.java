/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.ck.entity.CApprole;
import com.tlkzzz.jeesite.modules.ck.dao.CApproleDao;

/**
 * app权限设置Service
 * @author szx
 * @version 2017-06-29
 */
@Service
@Transactional(readOnly = true)
public class CApproleService extends CrudService<CApproleDao, CApprole> {

	public CApprole get(String id) {
		return super.get(id);
	}
	
	public List<CApprole> findList(CApprole cApprole) {
		return super.findList(cApprole);
	}
	
	public Page<CApprole> findPage(Page<CApprole> page, CApprole cApprole) {
		return super.findPage(page, cApprole);
	}
	
	@Transactional(readOnly = false)
	public void save(CApprole cApprole) {
		super.save(cApprole);
	}
	
	@Transactional(readOnly = false)
	public void delete(CApprole cApprole) {
		super.delete(cApprole);
	}
	
}