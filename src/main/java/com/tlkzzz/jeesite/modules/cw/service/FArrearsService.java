/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.cw.service;

import java.util.List;

import com.tlkzzz.jeesite.common.utils.StringUtils;
import com.tlkzzz.jeesite.modules.ck.entity.CRkckddinfo;
import com.tlkzzz.jeesite.modules.cw.entity.FReceipt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.cw.entity.FArrears;
import com.tlkzzz.jeesite.modules.cw.dao.FArrearsDao;

/**
 * 欠款记录Service
 * @author xrc
 * @version 2017-04-05
 */
@Service
@Transactional(readOnly = true)
public class FArrearsService extends CrudService<FArrearsDao, FArrears> {

	public FArrears get(String id) {
		return super.get(id);
	}
	
	public List<FArrears> findList(FArrears fArrears) {
		return super.findList(fArrears);
	}

	public Page<FArrears> findStorePage(Page<FArrears> page, FArrears fArrears){
		fArrears.setPage(page);
		page.setList(dao.finStoreList(fArrears));
		return page;
	}

	public Page<FArrears> findSupplierPage(Page<FArrears> page, FArrears fArrears){
		fArrears.setPage(page);
		page.setList(dao.finSupplierList(fArrears));
		return page;
	}

	/**
	 * 必须通过欠款类型进行查询 0：客户欠款，1：欠供应商款
	 * @param page 分页对象
	 * @param fArrears
	 * @return
	 */
	public Page<FArrears> findPage(Page<FArrears> page, FArrears fArrears) {
		fArrears.setPage(page);
		if(StringUtils.isNotBlank(fArrears.getArrearsType())){
			if("0".equals(fArrears.getArrearsType())){
				page.setList(dao.findStoreList(fArrears));
			}else if("1".equals(fArrears.getArrearsType())){
				page.setList(dao.findSupplierList(fArrears));
			}
		}
		return page;
	}

	public Page<FArrears> finPage(Page<FArrears> page, FArrears fArrears) {
		fArrears.setPage(page);
		if(StringUtils.isNotBlank(fArrears.getArrearsType())){
			if("0".equals(fArrears.getArrearsType())){
				page.setList(dao.finStoreList(fArrears));
			}else if("1".equals(fArrears.getArrearsType())){
				page.setList(dao.finSupplierList(fArrears));
			}
		}
		return page;
	}


	@Transactional(readOnly = false)
	public void save(FArrears fArrears) {
		super.save(fArrears);
	}

	@Transactional(readOnly = false)
	public void saveByReceipt(FReceipt receipt, Double htje, Double sfje){
		FArrears arrears = new FArrears();
		arrears.setRkckdd(new CRkckddinfo(receipt.getReceiptCode()));//保存订单信息
		arrears.setArrearsUnit(receipt.getTravelUnit().getId());
		arrears.setArrearsMode("0");
		arrears.setArrearsDate(receipt.getReceiptDate());
		arrears.setArrearsType("0");
		arrears.setTotal(String.valueOf(htje-sfje));
		super.save(arrears);
	}
	
	@Transactional(readOnly = false)
	public void delete(FArrears fArrears) {
		super.delete(fArrears);
	}


	/**
	 * 更新客户欠款金额
	 * */
	public void khhkUpdate(FArrears fArrears){dao.khhkUpdate(fArrears);}
	
}