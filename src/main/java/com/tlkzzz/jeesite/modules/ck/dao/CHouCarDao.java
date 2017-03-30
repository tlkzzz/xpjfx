/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.dao;

import com.tlkzzz.jeesite.common.persistence.CrudDao;
import com.tlkzzz.jeesite.common.persistence.annotation.MyBatisDao;
import com.tlkzzz.jeesite.modules.ck.entity.CHouCar;

/**
 * 仓库车辆表生成DAO接口
 * @author xrc
 * @version 2017-03-13
 */
@MyBatisDao
public interface CHouCarDao extends CrudDao<CHouCar> {

    /**
     * 通过仓库和车获取对象
     * @param cHouCar
     * @return
     */
    public CHouCar getByHouCar(CHouCar cHouCar);
}