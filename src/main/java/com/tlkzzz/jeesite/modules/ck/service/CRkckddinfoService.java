/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.service;

import java.util.Date;
import java.util.List;

import com.tlkzzz.jeesite.modules.ck.entity.CShop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.ck.entity.CRkckddinfo;
import com.tlkzzz.jeesite.modules.ck.dao.CRkckddinfoDao;

/**
 * 总订单Service
 * @author xrc
 * @version 2017-03-27
 */
@Service
@Transactional(readOnly = true)
public class CRkckddinfoService extends CrudService<CRkckddinfoDao, CRkckddinfo> {

	@Autowired
	private CDdinfoService cDdinfoService;

	public CRkckddinfo get(String id) {
		return super.get(id);
	}
	
	public List<CRkckddinfo> findList(CRkckddinfo cRkckddinfo) {
		return super.findList(cRkckddinfo);
	}
/**
 * shizx 查询总订单ID
 * */
	public List<CRkckddinfo> findListId(CRkckddinfo cRkckddinfo) {
		return dao.findListId(cRkckddinfo);
	}

	public List<CRkckddinfo> fyfindList(CRkckddinfo cRkckddinfo) {
		return dao.fyfindList(cRkckddinfo);
	}

	public List<CRkckddinfo> ywyfindList(CRkckddinfo cRkckddinfo) {
		return dao.ywyfindList(cRkckddinfo);
	}

	public Page<CRkckddinfo> findPage(Page<CRkckddinfo> page, CRkckddinfo cRkckddinfo) {
		return super.findPage(page, cRkckddinfo);
	}
	
	@Transactional(readOnly = false)
	public void saveRkInfo(CRkckddinfo cRkckddinfo, List<CShop> shopList) {
		if(shopList.size()==0)return;
		cRkckddinfo.setDdbh("Z"+new Date().getTime());
		super.save(cRkckddinfo);
		for(CShop cs:shopList){//将购物车中订单保存到订单表
			cDdinfoService.saveInfo(cs,cRkckddinfo);
		}
	}

	@Transactional(readOnly = false)
	public void save(CRkckddinfo cRkckddinfo) {
		super.save(cRkckddinfo);
	}

	@Transactional(readOnly = false)
	public void changeIssp(CRkckddinfo cRkckddinfo) {
		dao.changeIssp(cRkckddinfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(CRkckddinfo cRkckddinfo) {
		super.delete(cRkckddinfo);
	}
	
}