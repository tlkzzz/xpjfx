/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.dao;

import com.tlkzzz.jeesite.common.persistence.CrudDao;
import com.tlkzzz.jeesite.common.persistence.annotation.MyBatisDao;
import com.tlkzzz.jeesite.modules.ck.entity.CShop;

/**
 * 购物车DAO接口
 * @author xrc
 * @version 2017-03-23
 */
@MyBatisDao
public interface CShopDao extends CrudDao<CShop> {
    /**
     * 通过用户ID删除该用户下购物车中的所有订单
     * @param userId
     */
    public void deleteByUserId(String userId,String state);

    /**
     * 通过商品ID和创建人ID查询订单
     * @param cShop
     * @return
     */
    public CShop getShopByGoods(CShop cShop);
}