/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.dao;

import com.tlkzzz.jeesite.common.persistence.CrudDao;
import com.tlkzzz.jeesite.common.persistence.annotation.MyBatisDao;
import com.tlkzzz.jeesite.modules.ck.entity.CCgzbinfo;
import com.tlkzzz.jeesite.modules.ck.entity.CCkinfo;

import java.util.List;

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
     * 通过商品的小类查询出他的总价格
     * @param cCgzbinfo
     * @return
     */
    public List<CCgzbinfo> findListxl(CCgzbinfo cCgzbinfo);
    /**
     * 查询所有订单编号（批次）
     * @param cCgzbinfo
     * @return
     */
    public List<CCgzbinfo> findOrderCodeList(CCgzbinfo cCgzbinfo);
    /**
     * 通过ID修改订单状态
     * @param id
     * @param state
     */
	public void changeState(String id,String state);
    /**
     * 通过ID修改订单编号（同一批次标识）
     * @param id
     * @param orderCode
     */
	public void updateOrderCode(String id,String orderCode);
}