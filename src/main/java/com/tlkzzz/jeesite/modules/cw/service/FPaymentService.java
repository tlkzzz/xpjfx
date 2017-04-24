/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.cw.service;

import java.util.Date;
import java.util.List;

import com.tlkzzz.jeesite.modules.sys.utils.UserUtils;
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

	/**
	 * 数组元素筛选，筛选出包含states字符串中的所有类型
	 * @param states	类型串，每个类型用‘，’隔开
	 * @param fPayment
	 * @return
	 */
	public List<FPayment> selectList(String states,FPayment fPayment) {
		fPayment.setPaymentType(states);
		return super.findList(fPayment);
	}

	public Page<FPayment> findPage(Page<FPayment> page, FPayment fPayment) {
		return super.findPage(page, fPayment);
	}

	@Transactional(readOnly = false)
	public void save(FPayment fPayment) {
        fPayment.setJsr(UserUtils.getUser());
        fPayment.setApprovalStatus("1");
		super.save(fPayment);
	}

	@Transactional(readOnly = false)
	public void saveDefualt(FPayment fPayment) {
        fPayment.setJsr(UserUtils.getUser());
        fPayment.setApprovalStatus("0");
		fPayment.setThstatus("0");
		super.save(fPayment);
	}

	@Transactional(readOnly = false)
	public void updateApprovalStatus(FPayment payment){
		dao.updateApprovalStatus(payment);
	}

	@Transactional(readOnly = false)
	public void thstatusUpdate(FPayment payment){
		dao.updateApprovalStatus(payment);
	}

	@Transactional(readOnly = false)
	public void paymentAddHtje(FPayment payment){
		dao.paymentAddHtje(payment);
	}

	@Transactional(readOnly = false)
	public void addHTJE(FPayment payment){
		dao.addHTJE(payment);
	}

	@Transactional(readOnly = false)
	public boolean minHTJE(FPayment payment){
		boolean ret = false;
		FPayment py = get(payment);//如果付款存在且合同金额大于减少金额
		if(py!=null&&Double.parseDouble(py.getHtje())>Double.parseDouble(payment.getHtje())){
			dao.minHTJE(payment);
			ret = true;
		}
		return ret;
	}

    /**
     * 现金费用单保存信息
     * @param fPayment
     * @param paymentType	 收款类型
     * @param approvalStatus	是否审核
     */
    @Transactional(readOnly = false)
    public void outOfTheLibrary(FPayment fPayment, String paymentType, String approvalStatus) {
        //	CStore travelUnit = cStoreDao.get(fReceipt.getTravelUnit());
        //	CKm   subjectCode=cKmDao.get(fReceipt.getSubjectCode());
//		CStore travelUnit=new CStore();
//		CKm   subjectCode=new CKm();
//		subjectCode.setKmname(fPayment.getSubjectCode().getId());
//		travelUnit.setId(fPayment.getTravelUnit().getId());
		if(fPayment.getHtje().isEmpty()){
			fPayment.setHtje("0");
		}
		if(fPayment.getJe().isEmpty()){
			fPayment.setJe("0");
		}
        fPayment.setSubjectCode(fPayment.getSubjectCode());
        fPayment.setPaymentDate(new Date());
        fPayment.setTravelUnit(fPayment.getTravelUnit());
        fPayment.setPaymentType(paymentType); //收款类型
        fPayment.setJsr(UserUtils.getUser());
        fPayment.setAuditor(UserUtils.getUser());
        fPayment.setApprovalStatus(approvalStatus); //审批
        super.save(fPayment);
    }

	@Transactional(readOnly = false)
	public void delete(FPayment fPayment) {
		super.delete(fPayment);
	}

}