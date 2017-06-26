/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.service;

import java.util.Date;
import java.util.List;

import com.tlkzzz.jeesite.common.utils.StringUtils;
import com.tlkzzz.jeesite.modules.ck.entity.CHgoods;
import com.tlkzzz.jeesite.modules.ck.entity.CHouseCheckInventory;
import com.tlkzzz.jeesite.modules.ck.entity.CHouseComponentCheck;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.ck.entity.CHouseCheck;
import com.tlkzzz.jeesite.modules.ck.dao.CHouseCheckDao;

/**
 * 仓库总盘点Service
 * @author xrc
 * @version 2017-04-12
 */
@Service
@Transactional(readOnly = true)
public class CHouseCheckService extends CrudService<CHouseCheckDao, CHouseCheck> {

	public CHouseCheck get(String id) {
		return super.get(id);
	}

	public CHouseCheck getByHouse(CHouseCheck cHouseCheck){
		return dao.getByHouse(cHouseCheck);
	}
	
	public List<CHouseCheck> findList(CHouseCheck cHouseCheck) {
		return super.findList(cHouseCheck);
	}
	
	public Page<CHouseCheck> findPage(Page<CHouseCheck> page, CHouseCheck cHouseCheck) {
		return super.findPage(page, cHouseCheck);
	}
	
	@Transactional(readOnly = false)
	public void save(CHouseCheck cHouseCheck) {
		super.save(cHouseCheck);
	}

	@Transactional(readOnly = false)
	public void saveByInventory(CHouseCheck cHouseCheck, List<CHgoods> cHgoodsList) {
		if(cHgoodsList.size()>0) {
			double cbzje = 0;
			double sszje = 0;
			for (CHgoods ch : cHgoodsList) {
				if (StringUtils.isNotBlank(ch.getNub()) && StringUtils.isNotBlank(ch.getGoods().getCbj())) {
					cbzje += (Double.parseDouble(ch.getNub()) * Double.parseDouble(ch.getGoods().getCbj()));
					if(ch.getGoods()!=null&&StringUtils.isNotBlank(ch.getGoods().getSj())){
						sszje += (Integer.parseInt(ch.getNub())*Double.parseDouble(ch.getGoods().getSj()));
					}
				}
			}
			cHouseCheck.setCheckDate(new Date());
			cHouseCheck.setState("1");
			cHouseCheck.setCbzje(String.valueOf(cbzje));
			cHouseCheck.setSszje(String.valueOf(sszje));
			super.save(cHouseCheck);
		}
	}

	@Transactional(readOnly = false)
	public void	createCheckByComponentList(CHouseCheck cHouseCheck, List<CHouseComponentCheck> componentCheckList){
		if(componentCheckList.size()>0) {
			double cbzje = 0;
			double sszje = 0;
			for(CHouseComponentCheck cc:componentCheckList){
				if(StringUtils.isNotBlank(cc.getCbzje()))cbzje += Double.parseDouble(cc.getCbzje());
				if(StringUtils.isNotBlank(cc.getSszje()))sszje += Double.parseDouble(cc.getSszje());
			}
			cHouseCheck.setState("1");
			cHouseCheck.setCheckDate(new Date());
			cHouseCheck.setCbzje(String.valueOf(cbzje));
			cHouseCheck.setSszje(String.valueOf(sszje));
			super.save(cHouseCheck);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(CHouseCheck cHouseCheck) {
		super.delete(cHouseCheck);
	}
	
}