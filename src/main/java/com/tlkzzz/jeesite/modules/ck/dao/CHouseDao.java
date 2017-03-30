/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.dao;

import com.tlkzzz.jeesite.common.persistence.CrudDao;
import com.tlkzzz.jeesite.common.persistence.annotation.MyBatisDao;
import com.tlkzzz.jeesite.modules.ck.entity.CHouse;

import java.util.List;

/**
 * 仓库表DAO接口
 * @author xrc
 * @version 2017-03-13
 */
@MyBatisDao
public interface CHouseDao extends CrudDao<CHouse> {
    /**
     * 根据name拿到所有的值
     */
    public List<CHouse> getname(String name);
    public List<CHouse> getcode(String code);

    /**
     * 获取主仓库信息
     * @return
     */
    public CHouse getMainHouse();
    /**
     * 修改主仓库标记
     * @param id
     */
	public void changeIsMainStock(String id);

    /**
     * 清除主仓库
     */
	public void clearMainStock();
}