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
     * shizx 查询总订单ID
     * */
    public List<CRkckddinfo> findListId(CRkckddinfo cRkckddinfo);
}