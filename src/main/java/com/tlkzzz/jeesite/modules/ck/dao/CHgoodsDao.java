/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.dao;

import com.tlkzzz.jeesite.common.persistence.CrudDao;
import com.tlkzzz.jeesite.common.persistence.annotation.MyBatisDao;
import com.tlkzzz.jeesite.modules.ck.entity.CHgoods;

import java.util.List;

/**
 * 仓库商品DAO接口
 * @author xrc
 * @version 2017-03-13
 */
@MyBatisDao
public interface CHgoodsDao extends CrudDao<CHgoods> {
    /**
     * 查询当前仓库中是否存在商品
     * @param cHgoods
     * @return
     */
    public CHgoods findHGByHG(CHgoods cHgoods);

    /**
     * 查询仓库中商品的总库存（所有仓库中商品总量）
     * @param goodsId
     * @return
     */
	public Integer findStockSumNum(String goodsId);

    /**
     * 查询当前仓库中商品库存
     * @param house
     * @return
     */
	public Integer findStockNum(CHgoods house);

    /**
     * 添加库存数量
     * @param cHgoods
     */
	public void addStock(CHgoods cHgoods);

    /**
     * 减少库存数量
     * @param cHgoods
     */
	public void minStock(CHgoods cHgoods);

    /**
     * 退货更新库存数量
     * */
    public void kcsl(CHgoods cHgoods);

    /**
     * 通过商品分组查询库存报表
     * @param cHgoods
     * @return
     */
    public List<CHgoods> findReportListByG(CHgoods cHgoods);

    /**
     * 通过商品品牌分组查询库存报表
     * @param cHgoods
     * @return
     */
    public List<CHgoods> findReportListByBands(CHgoods cHgoods);
}