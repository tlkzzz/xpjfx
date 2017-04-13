/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.cw.dao;

import com.tlkzzz.jeesite.common.persistence.CrudDao;
import com.tlkzzz.jeesite.common.persistence.annotation.MyBatisDao;
import com.tlkzzz.jeesite.modules.cw.entity.FArrears;

import java.util.List;

/**
 * 欠款记录DAO接口
 * @author xrc
 * @version 2017-04-05
 */
@MyBatisDao
public interface FArrearsDao extends CrudDao<FArrears> {
    /**
     * 查询客户欠款列表
     * @param fArrears
     * @return
     */
    public List<FArrears> findStoreList(FArrears fArrears);

    /**
     * 查询欠供应商款列表
     * @param fArrears
     * @return
     */
    public List<FArrears> findSupplierList(FArrears fArrears);
}