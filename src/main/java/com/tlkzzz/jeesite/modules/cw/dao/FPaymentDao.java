/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.cw.dao;

import com.tlkzzz.jeesite.common.persistence.CrudDao;
import com.tlkzzz.jeesite.common.persistence.annotation.MyBatisDao;
import com.tlkzzz.jeesite.modules.cw.entity.FPayment;

/**
 * 付款DAO接口
 * @author xrc
 * @version 2017-04-05
 */
@MyBatisDao
public interface FPaymentDao extends CrudDao<FPayment> {
    /**
     * 通过订单ID获取付款对象
     * @param payment
     * @return
     */
	public FPayment getByPaymentCode(FPayment payment);

    /**
     * 更新审批状态和审批人信息
     * @param payment
     */
	public void updateApprovalStatus(FPayment payment);


	public FPayment paymentAddHtje(FPayment payment);
}