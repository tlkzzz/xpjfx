/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.service;

import java.util.ArrayList;
import java.util.List;

import com.tlkzzz.jeesite.common.mapper.JsonMapper;
import com.tlkzzz.jeesite.modules.ck.entity.CHgoods;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.ck.entity.CHouseCheckInventory;
import com.tlkzzz.jeesite.modules.ck.dao.CHouseCheckInventoryDao;

/**
 * 盘点抄帐Service
 * @author xrc
 * @version 2017-04-12
 */
@Service
@Transactional(readOnly = true)
public class CHouseCheckInventoryService extends CrudService<CHouseCheckInventoryDao, CHouseCheckInventory> {

	public CHouseCheckInventory get(String id) {
		return super.get(id);
	}

	public List<CHouseCheckInventory> getByHouse(CHouseCheckInventory cHouseCheckInventory){
		return dao.getByHouse(cHouseCheckInventory);
	}

	/**
	 * 解析抄帐对象中的json为库存列表
	 * @param cHouseCheckInventory
	 * @return
	 */
	public List<CHgoods> JsonToList(CHouseCheckInventory cHouseCheckInventory){
		List<CHgoods> cHgoodsList = new ArrayList<CHgoods>();
		if(cHouseCheckInventory!=null) {
			JSONArray jsonArray = JSONArray.fromObject(cHouseCheckInventory.getInventoryText());
			for (Object o : jsonArray) {
				JSONObject object = JSONObject.fromObject(o);
				cHgoodsList.add((CHgoods) JSONObject.toBean(object, CHgoods.class));
			}
		}
		return cHgoodsList;
	}
	
	public List<CHouseCheckInventory> findList(CHouseCheckInventory cHouseCheckInventory) {
		return super.findList(cHouseCheckInventory);
	}
	
	public Page<CHouseCheckInventory> findPage(Page<CHouseCheckInventory> page, CHouseCheckInventory cHouseCheckInventory) {
		return super.findPage(page, cHouseCheckInventory);
	}
	
	@Transactional(readOnly = false)
	public void save(CHouseCheckInventory cHouseCheckInventory, List<CHgoods> hgoodsList) {
		cHouseCheckInventory.setInventoryText(JsonMapper.toJsonString(hgoodsList));
		super.save(cHouseCheckInventory);
	}

	@Transactional(readOnly = false)
	public void	updateStateByHouse(CHouseCheckInventory checkInventory){
		dao.updateStateByHouse(checkInventory);
	}
	
	@Transactional(readOnly = false)
	public void delete(CHouseCheckInventory cHouseCheckInventory) {
		super.delete(cHouseCheckInventory);
	}
	
}