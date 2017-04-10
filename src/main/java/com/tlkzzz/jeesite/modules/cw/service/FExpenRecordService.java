/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.cw.service;

import java.util.List;

import com.tlkzzz.jeesite.modules.cw.entity.FPayment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.cw.entity.FExpenRecord;
import com.tlkzzz.jeesite.modules.cw.dao.FExpenRecordDao;

/**
 * 支出记录Service
 * @author xrc
 * @version 2017-04-05
 */
@Service
@Transactional(readOnly = true)
public class FExpenRecordService extends CrudService<FExpenRecordDao, FExpenRecord> {

	public FExpenRecord get(String id) {
		return super.get(id);
	}
	
	public List<FExpenRecord> findList(FExpenRecord fExpenRecord) {
		return super.findList(fExpenRecord);
	}
	
	public Page<FExpenRecord> findPage(Page<FExpenRecord> page, FExpenRecord fExpenRecord) {
		return super.findPage(page, fExpenRecord);
	}
	
	@Transactional(readOnly = false)
	public void save(FExpenRecord fExpenRecord) {
		super.save(fExpenRecord);
	}

	@Transactional(readOnly = false)
	public void saveByPayment(FPayment payment){
		FExpenRecord record = new FExpenRecord();
		record.setOrderId(payment.getPaymentCode());
		record.setExpenAccount(payment.getPaymentAccount());
		record.setTravelAccount(payment.getTravelAccount());
		record.setExpenDate(payment.getPaymentDate());
		record.setExpenMode(payment.getPaymentMode());
		record.setExpenType(payment.getPaymentType());
		record.setExpenMoney(payment.getJe());
		record.setJsr(payment.getJsr().getId());
		super.save(record);
	}
	
	@Transactional(readOnly = false)
	public void delete(FExpenRecord fExpenRecord) {
		super.delete(fExpenRecord);
	}
	
}