/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.cw.dao;

import com.tlkzzz.jeesite.common.persistence.CrudDao;
import com.tlkzzz.jeesite.common.persistence.annotation.MyBatisDao;
import com.tlkzzz.jeesite.modules.cw.entity.FReceipt;

import java.util.List;

/**
 * 收款DAO接口
 * @author xrc
 * @version 2017-04-10
 */
@MyBatisDao
public interface FReceiptDao extends CrudDao<FReceipt> {
    /**
     * 更新单据编号（订单ID）
     * @param fReceipt
     */
    public void updateReceiptCode(FReceipt fReceipt);

    /**
     * 更新审批状态和审批人
     * @param fReceipt
     */
    public void updateApprovalStatus(FReceipt fReceipt);
    /**
     * 通过订单ID获取对象
     * @param fReceipt
     */
    public FReceipt getByReceiptCode(FReceipt fReceipt);

    /**
     * 通过ID增加合同金额
     * @param receipt
     */
    public void addHTJE(FReceipt receipt);

    /**
     * 通过ID减少合同金额
     * @param receipt
     */
    public void minHTJE(FReceipt receipt);

    public void thstatusUpdate(FReceipt receipt);

    /**
     * 通过客户汇总付款报表
     * @param receipt
     * @return
     */
    public List<FReceipt> findListByStore(FReceipt receipt);

    /**
     * 通过订单编号查询客户欠款记录
     * @param receipt
     * @return
     */
    public List<FReceipt> findArrearsList(FReceipt receipt);

    public List<FReceipt> fyfindList(FReceipt receipt);
}