/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.cw.service;

import java.util.List;

import com.tlkzzz.jeesite.modules.sys.utils.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.cw.entity.FReceipt;
import com.tlkzzz.jeesite.modules.cw.dao.FReceiptDao;

/**
 * 收款Service
 * @author xrc
 * @version 2017-04-10
 */
@Service
@Transactional(readOnly = true)
public class FReceiptService extends CrudService<FReceiptDao, FReceipt> {

	public FReceipt get(String id) {
		return super.get(id);
	}

	public FReceipt getByReceiptCode(FReceipt fReceipt){
		return dao.getByReceiptCode(fReceipt);
	}

	public List<FReceipt> findList(FReceipt fReceipt) {
		return super.findList(fReceipt);
	}
	
	public Page<FReceipt> findPage(Page<FReceipt> page, FReceipt fReceipt) {
		return super.findPage(page, fReceipt);
	}
	
	@Transactional(readOnly = false)
	public void save(FReceipt fReceipt) {
		fReceipt.setJsr(UserUtils.getUser());
		fReceipt.setApprovalStatus("0");
		super.save(fReceipt);
	}

	@Transactional(readOnly = false)
	public void updateReceiptCode(FReceipt fReceipt){
		dao.updateReceiptCode(fReceipt);
	}

	@Transactional(readOnly = false)
	public void updateApprovalStatus(FReceipt fReceipt){
		dao.updateApprovalStatus(fReceipt);
	}

	@Transactional(readOnly = false)
	public void delete(FReceipt fReceipt) {
		super.delete(fReceipt);
	}
	
}