/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.service;

import java.util.List;

import com.tlkzzz.jeesite.modules.ck.entity.CSupplier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.ck.entity.CHouse;
import com.tlkzzz.jeesite.modules.ck.dao.CHouseDao;

/**
 * 仓库表Service
 * @author xrc
 * @version 2017-03-13
 */
@Service
@Transactional(readOnly = true)
public class CHouseService extends CrudService<CHouseDao, CHouse> {

	public CHouse get(String id) {
		return super.get(id);
	}

	public CHouse getMainHouse(){
		return dao.getMainHouse();
	}

	public List<CHouse> getname(String name) {
		return dao.getname(name);
	}
	public List<CHouse> getcode(String code) {
		return dao.getcode(code);
	}
	
	public List<CHouse> findList(CHouse cHouse) {
		return super.findList(cHouse);
	}

	public Page<CHouse> findPage(Page<CHouse> page, CHouse cHouse) {
		return super.findPage(page, cHouse);
	}
	
	@Transactional(readOnly = false)
	public void save(CHouse cHouse) {
		super.save(cHouse);
	}
	
	@Transactional(readOnly = false)
	public void delete(CHouse cHouse) {
		super.delete(cHouse);
	}

	/**
	 * 修改主仓库标记
	 * @param id
	 */
	@Transactional(readOnly = false)
	public void changeIsMainStock(String id){
		dao.changeIsMainStock(id);
	}

	/**
	 * 清除主仓库
	 */
	@Transactional(readOnly = false)
	public void clearMainStock(){
		dao.clearMainStock();
	}
}
