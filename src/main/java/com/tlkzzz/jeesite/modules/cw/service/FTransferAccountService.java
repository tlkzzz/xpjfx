/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.cw.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.cw.entity.FTransferAccount;
import com.tlkzzz.jeesite.modules.cw.dao.FTransferAccountDao;

/**
 * 转账调账Service
 * @author xrc
 * @version 2017-04-05
 */
@Service
@Transactional(readOnly = true)
public class FTransferAccountService extends CrudService<FTransferAccountDao, FTransferAccount> {

	public FTransferAccount get(String id) {
		return super.get(id);
	}
	
	public List<FTransferAccount> findList(FTransferAccount fTransferAccount) {
		return super.findList(fTransferAccount);
	}
	
	public Page<FTransferAccount> findPage(Page<FTransferAccount> page, FTransferAccount fTransferAccount) {
		return super.findPage(page, fTransferAccount);
	}
	
	@Transactional(readOnly = false)
	public void save(FTransferAccount fTransferAccount) {
		super.save(fTransferAccount);
	}


	@Transactional(readOnly = false)
	public void updateApprovalState(FTransferAccount transferAccount){
		dao.updateApprovalState(transferAccount);
	}

	@Transactional(readOnly = false)
	public void delete(FTransferAccount fTransferAccount) {
		super.delete(fTransferAccount);
	}
	
}