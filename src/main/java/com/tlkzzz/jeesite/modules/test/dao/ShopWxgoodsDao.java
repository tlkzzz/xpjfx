/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.test.dao;

import com.tlkzzz.jeesite.common.persistence.CrudDao;
import com.tlkzzz.jeesite.common.persistence.annotation.MyBatisDao;
import com.tlkzzz.jeesite.modules.test.entity.ShopWxgoods;

/**
 * 微信红包DAO接口
 * @author xrc
 * @version 2017-03-20
 */
@MyBatisDao
public interface ShopWxgoodsDao extends CrudDao<ShopWxgoods> {
	
}