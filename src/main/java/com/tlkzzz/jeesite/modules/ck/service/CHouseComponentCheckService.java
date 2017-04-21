/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.service;

import java.util.Date;
import java.util.List;

import com.tlkzzz.jeesite.common.utils.StringUtils;
import com.tlkzzz.jeesite.modules.ck.entity.CHgoods;
import com.tlkzzz.jeesite.modules.ck.entity.CHouseCheckInventory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.ck.entity.CHouseComponentCheck;
import com.tlkzzz.jeesite.modules.ck.dao.CHouseComponentCheckDao;

/**
 * 分量盘点Service
 * @author xrc
 * @version 2017-04-12
 */
@Service
@Transactional(readOnly = true)
public class CHouseComponentCheckService extends CrudService<CHouseComponentCheckDao, CHouseComponentCheck> {

	public CHouseComponentCheck get(String id) {
		return super.get(id);
	}

	public List<CHouseComponentCheck> getByHouse(CHouseComponentCheck cHouseComponentCheck){
		return dao.getByHouse(cHouseComponentCheck);
	}
	
	public List<CHouseComponentCheck> findList(CHouseComponentCheck cHouseComponentCheck) {
		return super.findList(cHouseComponentCheck);
	}
	
	public Page<CHouseComponentCheck> findPage(Page<CHouseComponentCheck> page, CHouseComponentCheck cHouseComponentCheck) {
		return super.findPage(page, cHouseComponentCheck);
	}
	
	@Transactional(readOnly = false)
	public void save(CHouseComponentCheck cHouseComponentCheck) {
		super.save(cHouseComponentCheck);
	}

	@Transactional(readOnly = false)
	public void updateStateByHouse(CHouseComponentCheck cHouseComponentCheck){
		dao.updateStateByHouse(cHouseComponentCheck);
	}

	@Transactional(readOnly = false)
	public void saveBycHgoodsList(CHouseComponentCheck cHCC, List<CHgoods> cHgoodsList) {
		if(cHgoodsList.size()>0) {
			CHouseComponentCheck componentCheck = new CHouseComponentCheck();
			componentCheck.setHouse(cHCC.getHouse());
			double cbzje = 0;
			double sszje = 0;
			for (CHgoods hg : cHgoodsList) {
				if (StringUtils.isNotBlank(hg.getNub()) && hg.getCbj() > 0) {
					if (hg.getGoods() != null && StringUtils.isNotBlank(hg.getGoods().getSj())) {
						sszje += (Integer.parseInt(hg.getNub()) * Double.parseDouble(hg.getGoods().getSj()));
					}
					cbzje += (Integer.parseInt(hg.getNub()) * hg.getCbj());
				}
			}
			componentCheck.setState("0");
			componentCheck.setCheckDate(new Date());
			componentCheck.setCbzje(String.valueOf(cbzje));
			componentCheck.setSszje(String.valueOf(sszje));
			super.save(componentCheck);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(CHouseComponentCheck cHouseComponentCheck) {
		super.delete(cHouseComponentCheck);
	}
	
}