/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.dao;

import com.tlkzzz.jeesite.common.persistence.CrudDao;
import com.tlkzzz.jeesite.common.persistence.annotation.MyBatisDao;
import com.tlkzzz.jeesite.modules.ck.entity.CHouseCheckInventory;

import java.util.List;

/**
 * 盘点抄帐DAO接口
 * @author xrc
 * @version 2017-04-12
 */
@MyBatisDao
public interface CHouseCheckInventoryDao extends CrudDao<CHouseCheckInventory> {
    /**
     * 通过仓库获取抄帐对象列表
     * @param cHouseCheckInventory
     * @return
     */
	public List<CHouseCheckInventory> getByHouse(CHouseCheckInventory cHouseCheckInventory);

    /**
     * 通过仓库修改所有该仓库抄帐状态
     * @param cHouseCheckInventory
     */
	public void updateStateByHouse(CHouseCheckInventory cHouseCheckInventory);
}