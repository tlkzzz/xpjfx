/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.test.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.test.entity.ShopWxgoods;
import com.tlkzzz.jeesite.modules.test.dao.ShopWxgoodsDao;

/**
 * 微信红包Service
 * @author xrc
 * @version 2017-03-20
 */
@Service
@Transactional(readOnly = true)
public class ShopWxgoodsService extends CrudService<ShopWxgoodsDao, ShopWxgoods> {

	@Autowired
	private ShopWxpacketService shopWxpacketService;

	public ShopWxgoods get(String id) {
		return super.get(id);
	}
	
	public List<ShopWxgoods> findList(ShopWxgoods shopWxgoods) {
		return super.findList(shopWxgoods);
	}
	
	public Page<ShopWxgoods> findPage(Page<ShopWxgoods> page, ShopWxgoods shopWxgoods) {
		return super.findPage(page, shopWxgoods);
	}
	
	@Transactional(readOnly = false)
	public void save(ShopWxgoods shopWxgoods) {
		super.save(shopWxgoods);
	}

	@Transactional(readOnly = false)
	public void createSave(ShopWxgoods shopWxgoods) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		shopWxgoods.setState("0");
		shopWxgoods.setCjsj(sf.format(date));
		shopWxgoods.setSpbh("P"+date.getTime());
		boolean isNewRecord = shopWxgoods.getIsNewRecord();
		super.save(shopWxgoods);
		if(isNewRecord)shopWxpacketService.createSave(shopWxgoods);
	}

	@Transactional(readOnly = false)
	public void delete(ShopWxgoods shopWxgoods) {
		shopWxpacketService.deleteBygoods(shopWxgoods.getId());
		super.delete(shopWxgoods);
	}
	
}