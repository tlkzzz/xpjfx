/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.cw.dao;

import com.tlkzzz.jeesite.common.persistence.CrudDao;
import com.tlkzzz.jeesite.common.persistence.annotation.MyBatisDao;
import com.tlkzzz.jeesite.modules.cw.entity.FDiscount;

/**
 * 优惠表DAO接口
 * @author xlc
 * @version 2017-04-10
 */
@MyBatisDao
public interface FDiscountDao extends CrudDao<FDiscount> {
	
}