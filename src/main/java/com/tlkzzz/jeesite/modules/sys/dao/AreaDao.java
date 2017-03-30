/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.sys.dao;

import com.tlkzzz.jeesite.common.persistence.TreeDao;
import com.tlkzzz.jeesite.common.persistence.annotation.MyBatisDao;
import com.tlkzzz.jeesite.modules.sys.entity.Area;

import java.util.List;

/**
 * 区域DAO接口
 * @author tlkzzz
 * @version 2014-05-16
 */
@MyBatisDao
public interface AreaDao extends TreeDao<Area> {
	public List<Area> findListByParent(Area area);
}
