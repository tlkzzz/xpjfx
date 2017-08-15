/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.dao;

import com.tlkzzz.jeesite.common.persistence.CrudDao;
import com.tlkzzz.jeesite.common.persistence.annotation.MyBatisDao;
import com.tlkzzz.jeesite.modules.ck.entity.CRkckddinfo;

import java.util.List;

/**
 * 总订单DAO接口
 * @author xrc
 * @version 2017-03-27
 */
@MyBatisDao
public interface CRkckddinfoDao extends CrudDao<CRkckddinfo> {
    /**
     * 通过ID修改审批状态
     * @param cRkckddinfo
     */
    public void changeIssp(CRkckddinfo cRkckddinfo);

    /**
     * 更新总订单下所有订单的总金额
     * @param cRkckddinfo
     */
    public void updateJe(CRkckddinfo cRkckddinfo);

    /**
     * 更新总订单备注
     * @param cRkckddinfo
     */
    public void updateRemark(CRkckddinfo cRkckddinfo);

    /**
     * shizx 查询总订单ID
     * */
    public List<CRkckddinfo> findListId(CRkckddinfo cRkckddinfo);
   //查询出出库未审核的信息
    public List<CRkckddinfo> shenhelist(CRkckddinfo cRkckddinfo);
   //查询出采购未审核的信息
    public List<CRkckddinfo> shenheruku(CRkckddinfo cRkckddinfo);

    /**
     * 通过状态查询未审批订单数量
     * @param state
     * @return
     */
    public Integer getNotIsspCount(String state);

    /**
     * shizx APP分页查询
     * */
    public List<CRkckddinfo> fyfindList(CRkckddinfo cRkckddinfo);

    /**
     * shizx APP分页查询
     * */
    public List<CRkckddinfo> ywyfindList(CRkckddinfo cRkckddinfo);


    /**
     * shizx 更新状态updateIssp
     * */
    public void updateIssp(CRkckddinfo cRkckddinfo);

    /**
     * 查询当前登录的业务员的销售额
     * @param cRkckddinfo
     * @return
     */
    public List<CRkckddinfo> ddywy(CRkckddinfo cRkckddinfo);

    public List<CRkckddinfo> findOrderCodeList(CRkckddinfo cRkckddinfo);

    public List<CRkckddinfo> findcxOrderList(CRkckddinfo cRkckddinfo);
}