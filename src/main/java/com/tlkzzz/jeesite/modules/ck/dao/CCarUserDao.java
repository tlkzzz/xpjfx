/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.dao;

import com.tlkzzz.jeesite.common.persistence.CrudDao;
import com.tlkzzz.jeesite.common.persistence.annotation.MyBatisDao;
import com.tlkzzz.jeesite.modules.ck.entity.CCarUser;
import com.tlkzzz.jeesite.modules.sys.entity.User;

import java.util.List;

/**
 * 车辆人员表生成DAO接口
 * @author xrc
 * @version 2017-03-13
 */
@MyBatisDao
public interface CCarUserDao extends CrudDao<CCarUser> {
	public void deleteByCar(CCarUser carUser);
	public List<User> findListByCar(CCarUser carUser);
}