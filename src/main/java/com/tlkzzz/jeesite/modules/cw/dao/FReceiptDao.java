/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.cw.dao;

import com.tlkzzz.jeesite.common.persistence.CrudDao;
import com.tlkzzz.jeesite.common.persistence.annotation.MyBatisDao;
import com.tlkzzz.jeesite.modules.cw.entity.FReceipt;

/**
 * 收款DAO接口
 * @author xrc
 * @version 2017-04-05
 */
@MyBatisDao
public interface FReceiptDao extends CrudDao<FReceipt> {
    /**
     * 更新单据编号（订单ID）
     * @param fReceipt
     */
    public void updateReceiptCode(FReceipt fReceipt);

    /**
     * 通过订单ID获取对象
     * @param fReceipt
     */
    public FReceipt getByReceiptCode(FReceipt fReceipt);
}