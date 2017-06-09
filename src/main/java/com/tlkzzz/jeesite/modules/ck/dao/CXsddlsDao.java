/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.dao;

import com.tlkzzz.jeesite.common.persistence.CrudDao;
import com.tlkzzz.jeesite.common.persistence.annotation.MyBatisDao;
import com.tlkzzz.jeesite.modules.ck.entity.CXsddls;

import java.util.List;

/**
 * 销售订单临时表DAO接口
 * @author szx
 * @version 2017-05-16
 */
@MyBatisDao
public interface CXsddlsDao extends CrudDao<CXsddls> {
	public void stateUpdate(CXsddls cXsddls);

	public List<CXsddls> fyfindList(CXsddls cXsddls);
}