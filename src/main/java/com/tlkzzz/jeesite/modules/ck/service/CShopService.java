/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.service;

import java.util.Date;
import java.util.List;

import com.tlkzzz.jeesite.common.utils.StringUtils;
import com.tlkzzz.jeesite.modules.ck.dao.CCgzbinfoDao;
import com.tlkzzz.jeesite.modules.ck.entity.CCgzbinfo;
import com.tlkzzz.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.ck.entity.CShop;
import com.tlkzzz.jeesite.modules.ck.dao.CShopDao;

/**
 * 购物车Service
 * @author xrc
 * @version 2017-03-23
 */
@Service
@Transactional(readOnly = true)
public class CShopService extends CrudService<CShopDao, CShop> {
	@Autowired
	private CCgzbinfoDao cCgzbinfoDao;

	public CShop get(String id) {
		return super.get(id);
	}

	public CShop getShopByGoods(CShop cShop){
		return dao.getShopByGoods(cShop);
	}
	
	public List<CShop> findList(CShop cShop) {
		return super.findList(cShop);
	}
	
	public Page<CShop> findPage(Page<CShop> page, CShop cShop) {
		return super.findPage(page, cShop);
	}
	
	@Transactional(readOnly = false)
	public void save(CShop cShop) {
		cShop.setUserid(UserUtils.getUser().getId());
		cShop.setSpbh("P"+new Date().getTime());
		CShop cs = getShopByGoods(cShop);
//		cs.setSupplier(cShop.getSupplier());
//		CCgzbinfo cCgzbinfo = cCgzbinfoDao.getZbByGoods(cShop.getGoods().getId());
		if(cs!=null){
			cs.setNub(String.valueOf(Integer.parseInt(cs.getNub())+Integer.parseInt(cShop.getNub())));
//			cs.setSupplier(cShop.getSupplier());
			cShop = cs;
		}
//		if(cShop.getGoods().getName().equals(cCgzbinfo.getGoods().getName())) {
//			Double ss=Double.parseDouble(cCgzbinfo.getNub());
//			Double sss=Double.parseDouble(cShop.getNub());
//			cCgzbinfo.setNub(String.valueOf(ss+sss));
//			cCgzbinfoDao.update(cCgzbinfo);
//		}
		super.save(cShop);
	}
	@Transactional(readOnly = false)
	public void upsave(CShop cShop) {
		CCgzbinfo cCgzbinfo = cCgzbinfoDao.getZbByGoods(cShop.getGoods().getId());
		if(cShop.getGoods().getName().equals(cCgzbinfo.getGoods().getName()) || StringUtils.isNotBlank(cShop.getGoods().getName())) {
			Double ss=Double.parseDouble(cCgzbinfo.getNub());
			Double sss=Double.parseDouble(cShop.getNub());
			cCgzbinfo.setNub(String.valueOf(ss+sss));
			cCgzbinfoDao.update(cCgzbinfo);
		}
		super.save(cShop);
	}

	@Transactional(readOnly = false)
	public void delete(CShop cShop) {
		super.delete(cShop);
	}

	@Transactional(readOnly = false)
	public void deleteByUserId(String userId, String state) {
		dao.deleteByUserId(userId, state);
	}
	
}