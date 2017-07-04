/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.dao;

import com.tlkzzz.jeesite.common.persistence.CrudDao;
import com.tlkzzz.jeesite.common.persistence.annotation.MyBatisDao;
import com.tlkzzz.jeesite.modules.ck.entity.CApprole;

/**
 * app权限设置DAO接口
 * @author szx
 * @version 2017-06-29
 */
@MyBatisDao
public interface CApproleDao extends CrudDao<CApprole> {
	
}