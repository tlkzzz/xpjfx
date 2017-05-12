/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.dao;

import com.tlkzzz.jeesite.common.persistence.CrudDao;
import com.tlkzzz.jeesite.common.persistence.annotation.MyBatisDao;
import com.tlkzzz.jeesite.modules.ck.entity.CDdinfo;

import java.util.List;

/**
 * 订单DAO接口
 * @author xrc
 * @version 2017-03-27
 */
@MyBatisDao
public interface CDdinfoDao extends CrudDao<CDdinfo> {
    /**
     * 保存采购统计表ID
     * @param cDdinfo
     */
    public void updateCgzbInfo(CDdinfo cDdinfo);

    /**
     * 通过商品分组查询报表信息
     * @param cDdinfo
     * @return
     */
    public List<CDdinfo> findReportList(CDdinfo cDdinfo);
    /**
     * 退货信息保存，更新原有自订单，新增退货信息添加
     * */
    public void thUpdate(CDdinfo cddinfo);

    public List<CDdinfo> thfindList(CDdinfo cddinfo);

    /**
     * 查询出有进行优惠的所有订单
     * @param cDdinfo
     * @return
     */
    public List<CDdinfo> findDiscountList(CDdinfo cDdinfo);

    /**
     * 查询出所有下过订单的业务员
     * @param cDdinfo
     * @return
     */
    public List<CDdinfo> findUserList(CDdinfo cDdinfo);

    /**
     * 按照业务员查询时间段内的销售汇总数据
     * @param cDdinfo
     * @return
     */
    public CDdinfo getSalesSum(CDdinfo cDdinfo);
    /**
     * 查询出时间段内所有出售过的商品
     * @param cDdinfo
     * @return
     */
    public List<CDdinfo> findGoodsList(CDdinfo cDdinfo);

    /**
     * 按照商品查询时间段内的销售汇总数据
     * @param cDdinfo
     * @return
     */
    public CDdinfo getGoodsSalesSum(CDdinfo cDdinfo);
}