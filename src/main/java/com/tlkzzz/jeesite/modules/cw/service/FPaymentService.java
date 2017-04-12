/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.cw.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.cw.entity.FPayment;
import com.tlkzzz.jeesite.modules.cw.dao.FPaymentDao;

/**
 * 付款Service
 * @author xrc
 * @version 2017-04-05
 */
@Service
@Transactional(readOnly = true)
public class FPaymentService extends CrudService<FPaymentDao, FPayment> {

	public FPayment get(String id) {
		return super.get(id);
	}

	public FPayment getByPaymentCode(FPayment payment){
		return dao.getByPaymentCode(payment);
	}
	
	public List<FPayment> findList(FPayment fPayment) {
		return super.findList(fPayment);
	}
	
	public Page<FPayment> findPage(Page<FPayment> page, FPayment fPayment) {
		return super.findPage(page, fPayment);
	}
	
	@Transactional(readOnly = false)
	public void save(FPayment fPayment) {
		super.save(fPayment);
	}

	@Transactional(readOnly = false)
	public void updateApprovalStatus(FPayment payment){
		dao.updateApprovalStatus(payment);
	}

	@Transactional(readOnly = false)
	public void paymentAddHtje(FPayment payment){
		dao.paymentAddHtje(payment);
	}
	
	@Transactional(readOnly = false)
	public void delete(FPayment fPayment) {
		super.delete(fPayment);
	}
	
}