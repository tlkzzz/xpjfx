/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.dao;

import com.tlkzzz.jeesite.common.persistence.CrudDao;
import com.tlkzzz.jeesite.common.persistence.annotation.MyBatisDao;
import com.tlkzzz.jeesite.modules.ck.entity.CGoods;

import java.util.List;

/**
 * 商品生成DAO接口
 * @author xrc
 * @version 2017-03-13
 */
@MyBatisDao
public interface CGoodsDao extends CrudDao<CGoods> {
    /**
     * 修改商品成本价
     * @param goods
     */
	public void updateCBJ(CGoods goods);
	public List<CGoods> getsort(String sort);
}