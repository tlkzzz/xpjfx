/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.cw.service;

import java.util.Date;
import java.util.List;

import com.tlkzzz.jeesite.modules.ck.dao.CGoodsDao;
import com.tlkzzz.jeesite.modules.ck.dao.CKmDao;
import com.tlkzzz.jeesite.modules.ck.dao.CStoreDao;
import com.tlkzzz.jeesite.modules.ck.entity.*;
import com.tlkzzz.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private CStoreDao cStoreDao;
	@Autowired
	private CKmDao cKmDao;




	public FReceipt get(String id) {
		return super.get(id);
	}

	public FReceipt getByReceiptCode(FReceipt fReceipt){
		return dao.getByReceiptCode(fReceipt);
	}

	public List<FReceipt> findList(FReceipt fReceipt) {
		return super.findList(fReceipt);
	}

	/**
	 * 数组元素筛选，筛选出包含states字符串中的所有类型
	 * @param states	类型串，每个类型用‘，’隔开
	 * @param fReceipt
	 * @return
	 */
	public List<FReceipt> selectList(String states,FReceipt fReceipt) {
		fReceipt.setReceiptType(states);
		return super.findList(fReceipt);
	}

	public Page<FReceipt> findPage(Page<FReceipt> page, FReceipt fReceipt) {
		return super.findPage(page, fReceipt);
	}
	
	@Transactional(readOnly = false)
	public void save(FReceipt fReceipt) {
		fReceipt.setJsr(UserUtils.getUser());
		fReceipt.setApprovalStatus("1");
		super.save(fReceipt);
	}

	@Transactional(readOnly = false)
	public void saveDefualt(FReceipt fReceipt) {
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
	public void addHTJE(FReceipt receipt){
		dao.addHTJE(receipt);
	}

	@Transactional(readOnly = false)
	public boolean minHTJE(FReceipt receipt){
		boolean ret = false;
		FReceipt re = get(receipt);//如果收款存在且合同金额答应减少金额
		if(re!=null&&Double.parseDouble(re.getHtje())>Double.parseDouble(receipt.getHtje())){
			dao.minHTJE(receipt);
			ret = true;
		}
		return ret;
	}
	/**
	 * 现金费用单保存信息
	 * @param fReceipt
	 * @param receiptType	 收款类型
	 * @param approvalStatus	是否审核
	 */
	@Transactional(readOnly = false)
	public void outOfTheLibrary(FReceipt fReceipt, String receiptType, String approvalStatus) {
	//	CStore travelUnit = cStoreDao.get(fReceipt.getTravelUnit());
	//	CKm   subjectCode=cKmDao.get(fReceipt.getSubjectCode());
//		CStore travelUnit=new CStore();
//		CKm   subjectCode=new CKm();
//		subjectCode.setKmname(fReceipt.getSubjectCode().getId());
//		travelUnit.setId(fReceipt.getTravelUnit().getId());
		if(fReceipt.getJe().isEmpty()){
			fReceipt.setJe("0");
		}
		if(fReceipt.getHtje().isEmpty()){
			fReceipt.setHtje("0");
		}
		fReceipt.setSubjectCode(fReceipt.getSubjectCode());
		fReceipt.setReceiptDate(new Date());
		fReceipt.setTravelUnit(fReceipt.getTravelUnit());
		fReceipt.setReceiptType(receiptType); //收款类型
		fReceipt.setJsr(UserUtils.getUser());
		fReceipt.setAuditor(UserUtils.getUser());
		fReceipt.setApprovalStatus(approvalStatus); //审批
		super.save(fReceipt);
	}

	@Transactional(readOnly = false)
	public void delete(FReceipt fReceipt) {
		super.delete(fReceipt);
	}

	public void thstatusUpdate(FReceipt fReceipt){dao.thstatusUpdate(fReceipt);}

}