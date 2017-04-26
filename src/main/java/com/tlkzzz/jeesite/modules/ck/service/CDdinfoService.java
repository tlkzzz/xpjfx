/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.service;

import java.util.List;

import com.tlkzzz.jeesite.modules.ck.entity.CRkckddinfo;
import com.tlkzzz.jeesite.modules.ck.entity.CShop;
import com.tlkzzz.jeesite.modules.sys.utils.ToolsUtils;
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

	public List<CDdinfo> findReportList(CDdinfo cDdinfo){
		return processUnit(dao.findReportList(cDdinfo));
	}

	public List<CDdinfo> processUnit(List<CDdinfo> list){
		for(CDdinfo cd: list){
			if(cd==null||cd.getGoods()==null)continue;
			String[] unit = {cd.getGoods().getBig().getName(),cd.getGoods().getZong().getName(),cd.getGoods().getSmall().getName()};
			cd.setType(ToolsUtils.unitTools(cd.getGoods().getSpec().getName(),unit,Integer.parseInt(cd.getNub())));
		}
		return list;
	}

	public Page<CDdinfo> findPage(Page<CDdinfo> page, CDdinfo cDdinfo) {
		return super.findPage(page, cDdinfo);
	}
/**
 * 查询退货单信息
 * */
	public List<CDdinfo> thfindList(CDdinfo cDdinfo) {
		return dao.thfindList(cDdinfo);
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
		if(cs.getJe()!=null)cd.setJe(String.valueOf(cs.getJe()));
		cd.setStore(cs.getStore());
		if(cs.getYhje()!=null)cd.setYhje(String.valueOf(cs.getYhje()));
		super.save(cd);
	}
	
	@Transactional(readOnly = false)
	public void delete(CDdinfo cDdinfo) {
		super.delete(cDdinfo);
	}


	/**
	 * 退货信息保存，更新原有自订单，新增退货信息添加
	 * */
	@Transactional(readOnly = false)
	public void thUpdate(CDdinfo cddinfo) {
		dao.thUpdate(cddinfo);
	}
	
}