/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.service;

import java.util.List;

import com.tlkzzz.jeesite.modules.ck.entity.CRkckddinfo;
import com.tlkzzz.jeesite.modules.ck.entity.CShop;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.ck.entity.CDdinfo;
import com.tlkzzz.jeesite.modules.ck.dao.CDdinfoDao;

/**
 * 订单Service
 * @author xrc
 * @version 2017-03-27
 */
@Service
@Transactional(readOnly = true)
public class CDdinfoService extends CrudService<CDdinfoDao, CDdinfo> {

	public CDdinfo get(String id) {
		return super.get(id);
	}
	
	public List<CDdinfo> findList(CDdinfo cDdinfo) {
		return super.findList(cDdinfo);
	}
	
	public Page<CDdinfo> findPage(Page<CDdinfo> page, CDdinfo cDdinfo) {
		return super.findPage(page, cDdinfo);
	}
	
	@Transactional(readOnly = false)
	public void save(CDdinfo cDdinfo) {
		super.save(cDdinfo);
	}

	/**
	 * 将购物车订单保存为订单
	 * @param cs
	 * @param cRkckddinfo
	 */
	@Transactional(readOnly = false)
	public void saveInfo(CShop cs, CRkckddinfo cRkckddinfo) {
		CDdinfo cd = new CDdinfo();
		cd.setRkckddinfo(cRkckddinfo);
		cd.setGoods(cs.getGoods());
		cd.setDdbh(cs.getSpbh());
		cd.setSupplier(cs.getSupplier());
		cd.setNub(cs.getNub());
		cd.setJe(cs.getJe());
		cd.setStore(cs.getStore());
		super.save(cd);
	}
	
	@Transactional(readOnly = false)
	public void delete(CDdinfo cDdinfo) {
		super.delete(cDdinfo);
	}
	
}