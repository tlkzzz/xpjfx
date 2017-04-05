/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.dao;

import com.tlkzzz.jeesite.common.persistence.TreeDao;
import com.tlkzzz.jeesite.common.persistence.annotation.MyBatisDao;
import com.tlkzzz.jeesite.modules.ck.entity.CKm;

/**
 * 科目类别表DAO接口
 * @author szx
 * @version 2017-04-05
 */
@MyBatisDao
public interface CKmDao extends TreeDao<CKm> {
	
}