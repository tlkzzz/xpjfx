/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.dao;

import com.tlkzzz.jeesite.common.persistence.CrudDao;
import com.tlkzzz.jeesite.common.persistence.annotation.MyBatisDao;
import com.tlkzzz.jeesite.modules.ck.entity.CStore;

import java.util.List;

/**
 * 客户表DAO接口
 * @author xrc
 * @version 2017-03-13
 */
@MyBatisDao
public interface CStoreDao extends CrudDao<CStore> {
	public List<CStore> fyfindList(CStore cStore);

	//查询出未审核的条数
	public List<CStore> tslist(CStore cStore);
}