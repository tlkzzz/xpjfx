/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.cw.service;

import java.util.List;

import com.tlkzzz.jeesite.modules.cw.entity.FReceipt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.cw.entity.FIncomeRecord;
import com.tlkzzz.jeesite.modules.cw.dao.FIncomeRecordDao;

/**
 * 收入记录Service
 * @author xrc
 * @version 2017-04-05
 */
@Service
@Transactional(readOnly = true)
public class FIncomeRecordService extends CrudService<FIncomeRecordDao, FIncomeRecord> {

	public FIncomeRecord get(String id) {
		return super.get(id);
	}
	
	public List<FIncomeRecord> findList(FIncomeRecord fIncomeRecord) {
		return super.findList(fIncomeRecord);
	}
	
	public Page<FIncomeRecord> findPage(Page<FIncomeRecord> page, FIncomeRecord fIncomeRecord) {
		return super.findPage(page, fIncomeRecord);
	}
	
	@Transactional(readOnly = false)
	public void save(FIncomeRecord fIncomeRecord) {
		super.save(fIncomeRecord);
	}

	@Transactional(readOnly = false)
	public void saveByReceipt(FReceipt receipt){
		FIncomeRecord record = new FIncomeRecord();
		record.setTraverAccount(receipt.getTravelAccount());
		record.setIncomeAccount(receipt.getReceiptAccount());
		record.setIncomeMoney(receipt.getJe());
		record.setIncomeDate(receipt.getReceiptDate());
		record.setOrderId(receipt.getReceiptCode());
		record.setIncomeMode(receipt.getReceiptMode());
		record.setIncomeType(receipt.getReceiptType());
		record.setJsr(receipt.getJsr());
		super.save(record);
	}
	
	@Transactional(readOnly = false)
	public void delete(FIncomeRecord fIncomeRecord) {
		super.delete(fIncomeRecord);
	}
	
}