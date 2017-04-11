/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.cw.dao;

import com.tlkzzz.jeesite.common.persistence.CrudDao;
import com.tlkzzz.jeesite.common.persistence.annotation.MyBatisDao;
import com.tlkzzz.jeesite.modules.cw.entity.FAccount;

/**
 * 账户管理DAO接口
 * @author xrc
 * @version 2017-04-05
 */
@MyBatisDao
public interface FAccountDao extends CrudDao<FAccount> {
	public FAccount capitalHtje(FAccount fAccount);
}