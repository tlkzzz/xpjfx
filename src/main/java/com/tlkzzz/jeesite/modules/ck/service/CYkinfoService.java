/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.service;

import java.util.List;

import com.tlkzzz.jeesite.modules.ck.entity.CHgoods;
import com.tlkzzz.jeesite.modules.ck.entity.CHouse;
import com.tlkzzz.jeesite.modules.sys.utils.ToolsUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.ck.entity.CYkinfo;
import com.tlkzzz.jeesite.modules.ck.dao.CYkinfoDao;

/**
 * 移库记录Service
 * @author xrc
 * @version 2017-03-15
 */
@Service
@Transactional(readOnly = true)
public class CYkinfoService extends CrudService<CYkinfoDao, CYkinfo> {

	public CYkinfo get(String id) {
		return super.get(id);
	}
	
	public List<CYkinfo> findList(CYkinfo cYkinfo) {
		List<CYkinfo> list = super.findList(cYkinfo);
		for(CYkinfo cc: list){
			String[] unit = {cc.getGoods().getBig().getName(),cc.getGoods().getZong().getName(),cc.getGoods().getSmall().getName()};
			cc.setSpecNub(ToolsUtils.unitTools(cc.getGoods().getSpec().getName(), unit, Integer.parseInt(cc.getNub())));
		}
		return list;
	}
	
	public Page<CYkinfo> findPage(Page<CYkinfo> page, CYkinfo cYkinfo) {
		page = super.findPage(page, cYkinfo);
		for(CYkinfo cc:page.getList()){
			String[] unit = {cc.getGoods().getBig().getName(),cc.getGoods().getZong().getName(),cc.getGoods().getSmall().getName()};
			cc.setNub(ToolsUtils.unitTools(cc.getGoods().getSpec().getName(), unit, Integer.parseInt(cc.getNub())));
		}
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(CYkinfo cYkinfo) {
		super.save(cYkinfo);
	}

	@Transactional(readOnly = false)
	public void saveInfo(CHgoods cHgoods,String outHouseId) {
		CYkinfo cYkinfo = new CYkinfo();
		cYkinfo.setStartHouse(new CHouse(outHouseId));
		cYkinfo.setEndHouse(cHgoods.getHouse());
		cYkinfo.setGoods(cHgoods.getGoods());
		cYkinfo.setNub(cHgoods.getNub());
		super.save(cYkinfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(CYkinfo cYkinfo) {
		super.delete(cYkinfo);
	}
	
}