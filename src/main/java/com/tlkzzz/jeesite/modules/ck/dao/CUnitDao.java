/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.dao;

import com.tlkzzz.jeesite.common.persistence.CrudDao;
import com.tlkzzz.jeesite.common.persistence.annotation.MyBatisDao;
import com.tlkzzz.jeesite.modules.ck.entity.CUnit;

import java.util.List;

/**
 * 单位表DAO接口
 * @author xrc
 * @version 2017-03-13
 */
@MyBatisDao
public interface CUnitDao extends CrudDao<CUnit> {
public List<CUnit> getname(String name);
}