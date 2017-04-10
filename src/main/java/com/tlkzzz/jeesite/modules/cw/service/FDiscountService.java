/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.cw.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.cw.entity.FDiscount;
import com.tlkzzz.jeesite.modules.cw.dao.FDiscountDao;

/**
 * 优惠表Service
 * @author xlc
 * @version 2017-04-10
 */
@Service
@Transactional(readOnly = true)
public class FDiscountService extends CrudService<FDiscountDao, FDiscount> {

	public FDiscount get(String id) {
		return super.get(id);
	}
	
	public List<FDiscount> findList(FDiscount fDiscount) {
		return super.findList(fDiscount);
	}
	
	public Page<FDiscount> findPage(Page<FDiscount> page, FDiscount fDiscount) {
		return super.findPage(page, fDiscount);
	}
	
	@Transactional(readOnly = false)
	public void save(FDiscount fDiscount) {
		super.save(fDiscount);
	}
	
	@Transactional(readOnly = false)
	public void delete(FDiscount fDiscount) {
		super.delete(fDiscount);
	}
	
}