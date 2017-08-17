/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.service;

import java.util.Date;
import java.util.List;

import com.tlkzzz.jeesite.common.utils.StringUtils;
import com.tlkzzz.jeesite.modules.ck.dao.*;
import com.tlkzzz.jeesite.modules.ck.entity.*;
import com.tlkzzz.jeesite.modules.cw.dao.FPaymentDao;
import com.tlkzzz.jeesite.modules.cw.entity.FPayment;
import com.tlkzzz.jeesite.modules.cw.service.FExpenRecordService;
import com.tlkzzz.jeesite.modules.sys.utils.ToolsUtils;
import com.tlkzzz.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;

/**
 * 采购订单Service
 * @author xrc
 * @version 2017-03-17
 */
@Service
@Transactional(readOnly = true)
public class CCgzbinfoService extends CrudService<CCgzbinfoDao, CCgzbinfo> {

	@Autowired
	private CHgoodsService cHgoodsService;
	@Autowired
	private FExpenRecordService fExpenRecordService;
	@Autowired
	private CHouseDao cHouseDao;
	@Autowired
	private CGoodsDao cGoodsDao;
	@Autowired
	private CRkckddinfoDao cRkckddinfoDao;
	@Autowired
	private CDdinfoDao cDdinfoDao;
	@Autowired
	private FPaymentDao fPaymentDao;

	public CCgzbinfo get(String id) {
		return super.get(id);
	}
	
	public List<CCgzbinfo> findList(CCgzbinfo cCgzbinfo) {
		return super.findList(cCgzbinfo);
	}

	public List<CCgzbinfo> findListxl(CCgzbinfo cCgzbinfo) {
		return dao.findListxl(cCgzbinfo);
	}

	public List<CCgzbinfo> findOrderCodeList(CCgzbinfo cCgzbinfo) {
		return dao.findOrderCodeList(cCgzbinfo);
	}

	public Page<CCgzbinfo> findPage(Page<CCgzbinfo> page, CCgzbinfo cCgzbinfo) {
		page = super.findPage(page, cCgzbinfo);
		return page.setList(processUnit(page.getList()));
	}

	public List<CCgzbinfo> processUnit(List<CCgzbinfo> list){
		for(CCgzbinfo cc: list){
			String[] unit = {cc.getGoods().getBig().getName(),cc.getGoods().getZong().getName(),cc.getGoods().getSmall().getName()};
			cc.setNub(ToolsUtils.unitTools(cc.getGoods().getSpec().getName(), unit, Integer.parseInt(cc.getNub())));
			if(StringUtils.isNotBlank(cc.getRknub()))
				cc.setRknub(ToolsUtils.unitTools(cc.getGoods().getSpec().getName(), unit, Integer.parseInt(cc.getRknub())));
		}
		return list;
	}
	
	@Transactional(readOnly = false,rollbackFor = Exception.class)
	public void save(CCgzbinfo cCgzbinfo) {
		Date date = new Date();
		CGoods goods = cGoodsDao.get(cCgzbinfo.getGoods());
		/**	生成并保存采购统计订单 **/
		cCgzbinfo.setRknub(cCgzbinfo.getNub());
		cCgzbinfo.setRkDate(date);
		cCgzbinfo.setState("2");
		super.save(cCgzbinfo);
		/**	生成并保存入库总订单	**/
		CRkckddinfo cRkckddinfo = new CRkckddinfo();
		cRkckddinfo.preInsert();
		cRkckddinfo.setDdbh("Z"+date.getTime());
		cRkckddinfo.setLx("0");//入库
		cRkckddinfo.setState("1");//其他入库
		double sumMoney = Integer.parseInt(cCgzbinfo.getNub())*Double.parseDouble(cCgzbinfo.getJg());
		cRkckddinfo.setJe(String.valueOf(sumMoney));
		cRkckddinfo.setIssp("1");//已审批
		cRkckddinfo.setSpr(UserUtils.getUser());
		cRkckddinfo.setSpsj(date);
		cRkckddinfoDao.insert(cRkckddinfo);
		/**	生成并保存入库子订单	**/
		CDdinfo cDdinfo = new CDdinfo();
		cDdinfo.preInsert();
		cDdinfo.setRkckddinfo(cRkckddinfo);
		cDdinfo.setCgzbinfo(cCgzbinfo);
		cDdinfo.setJe(String.valueOf(sumMoney));
		cDdinfo.setGoods(cCgzbinfo.getGoods());
		cDdinfo.setHouse(cCgzbinfo.getHouse());
		cDdinfo.setSupplier(cCgzbinfo.getRkinfo().getSupplier());
		cDdinfo.setRkckdate(date);
		cDdinfo.setDdbh("P"+date.getTime());
		cDdinfo.setNub(cCgzbinfo.getNub());
		cDdinfo.setRkqcbj(goods.getCbj());
		cDdinfo.setRksjcbj(cCgzbinfo.getJg());
		cDdinfoDao.insert(cDdinfo);
		/**	生成库存对象并添加库存	**/
		CHgoods cHgoods = new CHgoods();
		cHgoods.setRkState("0");
		cHgoods.setGoods(cCgzbinfo.getGoods());
		cHgoods.setHouse(cCgzbinfo.getHouse());
		cHgoods.setNub(cCgzbinfo.getNub());
		cHgoods.setCkState(cCgzbinfo.getId());
		cHgoods.setCbj(Double.parseDouble(cCgzbinfo.getJg()));
		cHgoods.setSupplierid(cCgzbinfo.getRkinfo().getSupplier().getId());
		cHgoods.setRemarks(cCgzbinfo.getRemarks());
		cHgoodsService.save(cHgoods);
	}

	@Transactional(readOnly = false,rollbackFor = Exception.class)
	public boolean saveInfo(CDdinfo cDdinfo) {//保存基本信息
		CCgzbinfo cCgzbinfo = dao.getZbByGoodsAndState(cDdinfo.getGoods().getId(),"0");
		if(cCgzbinfo!=null){//未采购总表存在
			int goodsNum = Integer.parseInt(cCgzbinfo.getNub())+Integer.parseInt(cDdinfo.getNub());
			cCgzbinfo.setNub(String.valueOf(goodsNum));
		}else {//总表订单不存在
			cCgzbinfo = new CCgzbinfo();
			cCgzbinfo.setGoods(cDdinfo.getGoods());
			cCgzbinfo.setNub(cDdinfo.getNub());
			cCgzbinfo.setState("0");
		}
		super.save(cCgzbinfo);
		cDdinfo.setCgzbinfo(cCgzbinfo);
		cDdinfoDao.updateCgzbInfo(cDdinfo);
		return true;
	}

	@Transactional(readOnly = false,rollbackFor = Exception.class)
	public boolean saveCdList(List<CDdinfo> cdList,CRkckddinfo rkckddinfo,
							  String account,String travelAccount,String total) {//商品列表入库
		Date date = new Date();
		double sumMoney = 0.0;
		for (CDdinfo cd: cdList) {
			sumMoney += Integer.parseInt(cd.getNub())*Double.parseDouble(cd.getJe());
			/**修改采购申请信息**/
			CCgzbinfo cCgzbinfo = dao.getZbByGoodsAndState(cd.getGoods().getId(),"0");
			if(cCgzbinfo!=null) {
				cCgzbinfo.setRknub(cd.getNub());
				cCgzbinfo.setRkDate(date);
				cCgzbinfo.setJg(String.valueOf(cd.getJe()));
				cCgzbinfo.setState("2");
				super.save(cCgzbinfo);
			}
			/**修改库存信息**/
			CHgoods cHgoods = new CHgoods();
			cHgoods.setRkState("0");
			cHgoods.setGoods(cd.getGoods());
			cHgoods.setHouse(rkckddinfo.getcHouse());
			cHgoods.setNub(cd.getNub());
			cHgoods.setCkState((cCgzbinfo!=null)?cCgzbinfo.getId():null);
			cHgoods.setCbj(Double.parseDouble(cd.getJe()));
			cHgoods.setSupplierid(rkckddinfo.getSupplier().getId());
			cHgoods.setRemarks(cd.getRemarks());
			cHgoodsService.save(cHgoods);
			/**修改订单信息**/
			cd.setRkckdate(date);
			cd.setHouse(cHgoods.getHouse());
			cd.setSupplier(new CSupplier(cHgoods.getSupplierid()));
			cd.setRkqcbj(String.valueOf(cHgoods.getCbj()));
			cd.setRksjcbj(String.valueOf(cHgoods.getCbj()));
			cd.setJe(String.valueOf(cHgoods.getCbj()));
			cDdinfoDao.updateCGInfo(cd);
			cd.setCgzbinfo(cCgzbinfo);
			cDdinfoDao.updateCgzbInfo(cd);
		}
		/**添加财务信息**/
		FPayment payment = new FPayment();
		payment.preInsert();
		payment.setPaymentCode(rkckddinfo.getId());
		payment.setPaymentDate(date);
		payment.setPaymentAccount(account);
		payment.setTravelAccount(travelAccount);
		payment.setTravelUnit(rkckddinfo.getSupplier());
		payment.setJe(total);
		payment.setApprovalStatus("1");
		payment.setAuditor(UserUtils.getUser());
		payment.setHtje(String.valueOf(sumMoney));
		payment.setJsr(UserUtils.getUser());
		fPaymentDao.insert(payment);
		fExpenRecordService.saveByPayment(payment);
		/**修改总订单审批状态**/
		rkckddinfo.setIssp("1");
		rkckddinfo.setSpr(UserUtils.getUser());
		rkckddinfo.setSpsj(new Date());
		cRkckddinfoDao.changeIssp(rkckddinfo);
		return true;
	}

	@Transactional(readOnly = false)
	public void savePrice(CHgoods cHgoods){
		Date date = new Date();
		CCgzbinfo cCgzbinfo = dao.get(cHgoods.getCkState());//ckState字段值保存的采购订单ID
		if(cCgzbinfo==null)return;
		cCgzbinfo.setRknub(cHgoods.getNub());
		cCgzbinfo.setRkDate(date);
		cCgzbinfo.setJg(String.valueOf(cHgoods.getCbj()));
		cCgzbinfo.setState("2");
		super.save(cCgzbinfo);
		/**	保存入库信息到子订单中	 **/
		CDdinfo cDdinfo = new CDdinfo();
		cDdinfo.setCgzbinfo(cCgzbinfo);
		List<CDdinfo> cdList = cDdinfoDao.findList(cDdinfo);
		CGoods goods = cGoodsDao.get(cHgoods.getGoods());
		for(CDdinfo cd: cdList){
			cd.setRkckdate(date);
			cd.setHouse(cHgoods.getHouse());
			cd.setSupplier(new CSupplier(cHgoods.getSupplierid()));
			cd.setRkqcbj(goods.getCbj());
			cd.setRksjcbj(String.valueOf(cHgoods.getCbj()));
			cd.setJe(String.valueOf(cHgoods.getCbj()));
			cDdinfoDao.updateCGInfo(cd);
			/**	保存入库信息到总订单中	**/
			double sumMoney = 0.0;
			cDdinfo.setCgzbinfo(null);
			cDdinfo.setRkckddinfo(cd.getRkckddinfo());
			List<CDdinfo> cDdinfoList = cDdinfoDao.findList(cDdinfo);//获取同一个总订单下的所有子订单
			for(CDdinfo cdd: cDdinfoList){
				if(cdd.getId().equals(cd.getId())){
					sumMoney += Integer.parseInt(cd.getNub())*Double.parseDouble(cd.getJe());
				}else {
					sumMoney += Integer.parseInt(cdd.getNub())*Double.parseDouble(cdd.getJe());
				}
			}
			cDdinfo.getRkckddinfo().setJe(String.valueOf(sumMoney));
			cRkckddinfoDao.updateJe(cDdinfo.getRkckddinfo());
		}

	}

	@Transactional(readOnly = false)
	public void changeState(String id,String state){
		dao.changeState(id,state);
	}

	@Transactional(readOnly = false)
	public void updateOrderCode(String id,String orderCode){
		dao.updateOrderCode(id,orderCode);
	}
	
	@Transactional(readOnly = false)
	public void delete(CCgzbinfo cCgzbinfo) {
		super.delete(cCgzbinfo);
	}
	
}