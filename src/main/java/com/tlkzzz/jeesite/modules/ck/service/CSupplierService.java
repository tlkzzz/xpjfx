/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.ck.entity.CSupplier;
import com.tlkzzz.jeesite.modules.ck.dao.CSupplierDao;

/**
 * 供应商表Service
 * @author xrc
 * @version 2017-03-13
 */
@Service
@Transactional(readOnly = true)
public class CSupplierService extends CrudService<CSupplierDao, CSupplier> {

	public CSupplier get(String id) {
		return super.get(id);
	}
	public List<CSupplier> getname(String name) {
		return dao.getname(name);
	}
	public List<CSupplier> getcode(String code) {
		return dao.getcode(code);
	}
	
	public List<CSupplier> findList(CSupplier cSupplier) {
		return super.findList(cSupplier);
	}
	
	public Page<CSupplier> findPage(Page<CSupplier> page, CSupplier cSupplier) {
		return super.findPage(page, cSupplier);
	}
	
	@Transactional(readOnly = false)
	public void save(CSupplier cSupplier) {
		super.save(cSupplier);
	}
	
	@Transactional(readOnly = false)
	public void delete(CSupplier cSupplier) {
		super.delete(cSupplier);
	}

	public Page<CSupplier> findUser(Page<CSupplier> page, CSupplier cSupplier ) {
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		//tCmMsg.getSqlMap().put("dsf", dataScopeFilter(tCmMsg.getCurrentUser(), "o", "a"));
		// 设置分页参数
		cSupplier.setPage(page);
		List<CSupplier> list = dao.findList(cSupplier);
		page.setList((list));
		return page;
	}
}