/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.service;

import java.text.DecimalFormat;
import java.util.List;

import com.tlkzzz.jeesite.common.utils.StringUtils;
import com.tlkzzz.jeesite.modules.ck.entity.*;
import com.tlkzzz.jeesite.modules.sys.utils.ToolsUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.ck.dao.CRkinfoDao;

/**
 * 入库记录Service
 * @author xrc
 * @version 2017-03-15
 */
@Service
@Transactional(readOnly = true)
public class CRkinfoService extends CrudService<CRkinfoDao, CRkinfo> {

	public CRkinfo get(String id) {
		return super.get(id);
	}
	
	public List<CRkinfo> findList(CRkinfo cRkinfo) {
		return super.findList(cRkinfo);
	}
	
	public Page<CRkinfo> findPage(Page<CRkinfo> page, CRkinfo cRkinfo) {
		page = super.findPage(page, cRkinfo);
		page.setList(process(page.getList()));
		return page;
	}

	public List<CRkinfo> findReportList(CRkinfo cRkinfo) {
		List<CRkinfo> list = dao.findReportList(cRkinfo);
		DecimalFormat df = new DecimalFormat("#.####");
		for(CRkinfo cc: list){
			if(cc.getGoods()!=null&&StringUtils.isNotBlank(cc.getGoods().getSj()))cc.getGoods().setSj(df.format(Double.parseDouble(cc.getGoods().getSj())));
			if(StringUtils.isNotBlank(cc.getTotal()))cc.setTotal(df.format(Double.parseDouble(cc.getTotal())));
			String[] unit = {cc.getGoods().getBig().getName(),cc.getGoods().getZong().getName(),cc.getGoods().getSmall().getName()};
			cc.setRknub(ToolsUtils.unitTools(cc.getGoods().getSpec().getName(), unit, Integer.parseInt(cc.getRknub())));
		}
		return list;
	}

	public List<CRkinfo> process(List<CRkinfo> list){
		DecimalFormat df = new DecimalFormat("#.####");
		for(CRkinfo cc: list){
			if(StringUtils.isNotBlank(cc.getRkqcbj()))cc.setRkqcbj(df.format(Double.parseDouble(cc.getRkqcbj())));
			if(StringUtils.isNotBlank(cc.getRkhcbj()))cc.setRkhcbj(df.format(Double.parseDouble(cc.getRkhcbj())));
			if(StringUtils.isNotBlank(cc.getRkhcbj())&&StringUtils.isNotBlank(cc.getRknub())){
				cc.setTotal(df.format(Double.parseDouble(cc.getRkhcbj())*Integer.parseInt(cc.getRknub())));
			}
			String[] unit = {cc.getGoods().getBig().getName(),cc.getGoods().getZong().getName(),cc.getGoods().getSmall().getName()};
			cc.setRkhnub(ToolsUtils.unitTools(cc.getGoods().getSpec().getName(), unit, Integer.parseInt(cc.getRkhnub())));
			cc.setRknub(ToolsUtils.unitTools(cc.getGoods().getSpec().getName(), unit, Integer.parseInt(cc.getRknub())));
		}
		return list;
	}
	
	@Transactional(readOnly = false)
	public void save(CRkinfo cRkinfo) {
		super.save(cRkinfo);
	}

	@Transactional(readOnly = false)
	public void saveInfo(CHgoods cHgoods, String oldPrice, String newNum) {
		CRkinfo cRkinfo = new CRkinfo();
		cRkinfo.preInsert();
		cRkinfo.setHouse(cHgoods.getHouse());
		cRkinfo.setGoods(cHgoods.getGoods());
		cRkinfo.setRknub(cHgoods.getNub());//入库添加量
		cRkinfo.setRkhnub(newNum);
		cRkinfo.setRkhcbj(String.valueOf(cHgoods.getGoods().getCbj()));
		cRkinfo.setRkqcbj(oldPrice);
		if(StringUtils.isNotBlank(cHgoods.getCkState()))
			cRkinfo.setCgzbid(cHgoods.getCkState());//ckState中存放的采购订单ID
		cRkinfo.setSupplier(new CSupplier(cHgoods.getSupplierid()));
		cRkinfo.setState(cHgoods.getRkState());
		cRkinfo.setRemarks(cHgoods.getRemarks());
		dao.insert(cRkinfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(CRkinfo cRkinfo) {
		super.delete(cRkinfo);
	}
	
}