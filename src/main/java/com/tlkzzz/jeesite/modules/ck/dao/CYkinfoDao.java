/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.dao;

import com.tlkzzz.jeesite.common.persistence.CrudDao;
import com.tlkzzz.jeesite.common.persistence.annotation.MyBatisDao;
import com.tlkzzz.jeesite.modules.ck.entity.CYkinfo;

/**
 * 移库记录DAO接口
 * @author xrc
 * @version 2017-03-15
 */
@MyBatisDao
public interface CYkinfoDao extends CrudDao<CYkinfo> {
	
}