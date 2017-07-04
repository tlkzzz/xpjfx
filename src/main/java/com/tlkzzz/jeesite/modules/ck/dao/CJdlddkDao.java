/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.dao;

import com.tlkzzz.jeesite.common.persistence.CrudDao;
import com.tlkzzz.jeesite.common.persistence.annotation.MyBatisDao;
import com.tlkzzz.jeesite.modules.ck.entity.CJdlddk;

import java.util.List;

/**
 * 进店离店打卡DAO接口
 * @author szx
 * @version 2017-06-20
 */
@MyBatisDao
public interface CJdlddkDao extends CrudDao<CJdlddk> {
	public List<CJdlddk> dkfindList(CJdlddk cJdlddk);

	public List<CJdlddk> fyfindList(CJdlddk cJdlddk);

	public void ldupdate(CJdlddk cJdlddk);
}