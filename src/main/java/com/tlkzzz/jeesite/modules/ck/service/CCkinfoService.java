/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.service;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import com.tlkzzz.jeesite.common.utils.StringUtils;
import com.tlkzzz.jeesite.modules.ck.dao.CGoodsDao;
import com.tlkzzz.jeesite.modules.ck.dao.CHgoodsDao;
import com.tlkzzz.jeesite.modules.ck.entity.CGoods;
import com.tlkzzz.jeesite.modules.ck.entity.CHgoods;
import com.tlkzzz.jeesite.modules.ck.entity.CRkinfo;
import com.tlkzzz.jeesite.modules.sys.utils.ToolsUtils;
import com.tlkzzz.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.ck.entity.CCkinfo;
import com.tlkzzz.jeesite.modules.ck.dao.CCkinfoDao;

/**
 * 出库信息Service
 * @author xrc
 * @version 2017-03-21
 */
@Service
@Transactional(readOnly = true)
public class CCkinfoService extends CrudService<CCkinfoDao, CCkinfo> {

	@Autowired
	private CGoodsDao cGoodsDao;
	@Autowired
	private CHgoodsDao cHgoodsDao;

	public CCkinfo get(String id) {
		return super.get(id);
	}
	
	public List<CCkinfo> findList(CCkinfo cCkinfo) {
		return super.findList(cCkinfo);
	}


	public List<CCkinfo> findListStore(CCkinfo cCkinfo) {
		List<CCkinfo> list = dao.findListStore(cCkinfo);
		return list;
	}

	public List<CCkinfo> findListBands(CCkinfo cCkinfo){
		List<CCkinfo> list=dao.findListBands(cCkinfo);
	    return list;
	}
	
	public Page<CCkinfo> findPage(Page<CCkinfo> page, CCkinfo cCkinfo) {
		page = super.findPage(page, cCkinfo);
		DecimalFormat df = new DecimalFormat("#.####");
		for(CCkinfo c:page.getList()){
			c.setJe(df.format(Double.parseDouble(c.getJe())));
			c.setCkqcbj(df.format(Double.parseDouble(c.getCkqcbj())));
		}
		return page;
	}

	public List<CCkinfo> selectList(String states,CCkinfo cCkinfo) {
		cCkinfo.setState(states);
		return super.findList(cCkinfo);
	}
	public List<CCkinfo> storeList(String states,CCkinfo cCkinfo) {
		cCkinfo.setState(states);
		return dao.findListStore(cCkinfo);
	}
	public  List<CCkinfo> bandsList(String states,CCkinfo cCkinfo){
		cCkinfo.setState(states);
		return dao.findListBands(cCkinfo);
	}

	@Transactional(readOnly = false)
	public void save(CCkinfo cCkinfo) {
		super.save(cCkinfo);
	}

	/**
	 * 出库记录保存方法（减库存）
	 * @param cCkinfo
	 * @param state	 出库类型
	 * @param issp	是否审核
	 */
	@Transactional(readOnly = false)
	public void outOfTheLibrary(CCkinfo cCkinfo, String state, String issp) {
		CGoods goods = cGoodsDao.get(cCkinfo.getGoods());
		cCkinfo.setCkqcbj(goods.getCbj());
		cCkinfo.setState(state);//出库类型
		cCkinfo.setIssp(issp);
		cCkinfo.setCkdate(new Date());
		cCkinfo.setJsr(UserUtils.getUser());
		CHgoods cHgoods = new CHgoods();
		cHgoods.setHouse(cCkinfo.getHouse());
		cHgoods.setGoods(cCkinfo.getGoods());
		cHgoods.setNub(cCkinfo.getNub());
		cHgoodsDao.minStock(cHgoods);
		super.save(cCkinfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(CCkinfo cCkinfo) {
		super.delete(cCkinfo);
	}
	
}