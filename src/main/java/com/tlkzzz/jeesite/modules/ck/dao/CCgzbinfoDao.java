/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.dao;

import com.tlkzzz.jeesite.common.persistence.CrudDao;
import com.tlkzzz.jeesite.common.persistence.annotation.MyBatisDao;
import com.tlkzzz.jeesite.modules.ck.entity.CCgzbinfo;

/**
 * 采购订单DAO接口
 * @author xrc
 * @version 2017-03-17
 */
@MyBatisDao
public interface CCgzbinfoDao extends CrudDao<CCgzbinfo> {

    /**
     * 通过商品ID和订单状态获取对象
     * （订单状态必须为0未采购）
     * @param goodsId
     * @param state
     * @return
     */
	public CCgzbinfo getZbByGoodsAndState(String goodsId, String state);

    /**
     * 通过ID修改订单状态
     * @param id
     * @param state
     */
	public void changeState(String id,String state);
}