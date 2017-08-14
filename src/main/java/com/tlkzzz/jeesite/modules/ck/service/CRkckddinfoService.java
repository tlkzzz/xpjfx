/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.service;

import java.util.Date;
import java.util.List;

import com.tlkzzz.jeesite.common.utils.IdGen;
import com.tlkzzz.jeesite.common.utils.StringUtils;
import com.tlkzzz.jeesite.modules.ck.dao.CCkinfoDao;
import com.tlkzzz.jeesite.modules.ck.dao.CGoodsDao;
import com.tlkzzz.jeesite.modules.ck.dao.CHgoodsDao;
import com.tlkzzz.jeesite.modules.ck.entity.*;
import com.tlkzzz.jeesite.modules.cw.dao.FAccountDao;
import com.tlkzzz.jeesite.modules.cw.dao.FDiscountDao;
import com.tlkzzz.jeesite.modules.cw.dao.FIncomeRecordDao;
import com.tlkzzz.jeesite.modules.cw.dao.FReceiptDao;
import com.tlkzzz.jeesite.modules.cw.entity.FAccount;
import com.tlkzzz.jeesite.modules.cw.entity.FDiscount;
import com.tlkzzz.jeesite.modules.cw.entity.FIncomeRecord;
import com.tlkzzz.jeesite.modules.cw.entity.FReceipt;
import com.tlkzzz.jeesite.modules.cw.service.FDiscountService;
import com.tlkzzz.jeesite.modules.sys.entity.User;
import com.tlkzzz.jeesite.modules.sys.utils.UserUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.ck.dao.CRkckddinfoDao;

/**
 * 总订单Service
 * @author xrc
 * @version 2017-03-27
 */
@Service
@Transactional(readOnly = true)
public class CRkckddinfoService extends CrudService<CRkckddinfoDao, CRkckddinfo> {

	@Autowired
	private CDdinfoService cDdinfoService;
	@Autowired
	private FDiscountDao fDiscountDao;
	@Autowired
	private CGoodsDao cGoodsDao;
	@Autowired
	private FReceiptDao fReceiptDao;
	@Autowired
	private FIncomeRecordDao fIncomeRecordDao;
	@Autowired
	private FAccountDao fAccountDao;
	@Autowired
	private CHgoodsDao cHgoodsDao;
	@Autowired
	private CCkinfoDao cCkinfoDao;

	public CRkckddinfo get(String id) {
		return super.get(id);
	}
	
	public List<CRkckddinfo> findList(CRkckddinfo cRkckddinfo) {
		return super.findList(cRkckddinfo);
	}

	public List<CRkckddinfo> shenhelist(CRkckddinfo cRkckddinfo) {
		return dao.shenhelist(cRkckddinfo);
	}

	public List<CRkckddinfo> selectList(String state, CRkckddinfo cRkckddinfo) {
		cRkckddinfo.setState(state);
		return dao.shenhelist(cRkckddinfo);
	}
	public List<CRkckddinfo> selectlx(String state, CRkckddinfo cRkckddinfo) {
		cRkckddinfo.setState(state);
		return dao.shenheruku(cRkckddinfo);
	}

	public Integer getNotIsspCount(String state){
		return dao.getNotIsspCount(state);
	}

	public List<CRkckddinfo> shenheruku( CRkckddinfo cRkckddinfo) {
		return dao.shenheruku(cRkckddinfo);
	}
/**
 * shizx 查询总订单ID
 * */
	public List<CRkckddinfo> findListId(CRkckddinfo cRkckddinfo) {
		return dao.findListId(cRkckddinfo);
	}

	public List<CRkckddinfo> fyfindList(CRkckddinfo cRkckddinfo) {
		return dao.fyfindList(cRkckddinfo);
	}

	public List<CRkckddinfo> ywyfindList(CRkckddinfo cRkckddinfo) {
		return dao.ywyfindList(cRkckddinfo);
	}

	public Page<CRkckddinfo> findPage(Page<CRkckddinfo> page, CRkckddinfo cRkckddinfo) {
		return super.findPage(page, cRkckddinfo);
	}
	public List<CRkckddinfo> ddywy(CRkckddinfo cRkckddinfo) {
		return dao.ddywy(cRkckddinfo);
	}

	@Transactional(readOnly = false)
	public void saveRkInfo(CRkckddinfo cRkckddinfo, List<CShop> shopList) {
		if(shopList.size()==0)return;
		cRkckddinfo.setDdbh("Z"+new Date().getTime());
		super.save(cRkckddinfo);
		for(CShop cs:shopList){//将购物车中订单保存到订单表
			cDdinfoService.saveInfo(cs,cRkckddinfo);
		}
	}

	/**
	 * 通过json数组保存订单列表
	 * @param cRkckddinfo
	 * @param jsonArray
	 */
	@Transactional(readOnly = false,rollbackFor = Exception.class)
	public void saveRkCkInfo(CRkckddinfo cRkckddinfo, JSONArray jsonArray) {
		if(jsonArray.size()<=0)return;
		boolean isNewRecord = cRkckddinfo.getIsNewRecord();
		if(isNewRecord) {
			cRkckddinfo.preInsert();
			cRkckddinfo.setDdbh("Z" + new Date().getTime());
		}else {
			cRkckddinfo.preUpdate();
		}
		double htje = 0;
		for (Object o:jsonArray){
			JSONObject object = JSONObject.fromObject(o);
			if(object.get("id")==null||object.get("num")==null||object.get("price")==null)continue;
			CGoods goods = cGoodsDao.get(object.get("id").toString().trim());
			CDdinfo cd = new CDdinfo();
			if(object.get("cdId")!=null&&!"".equals(object.get("cdId"))){
				cd = cDdinfoService.get(object.get("cdId").toString().trim());
			}
			if(cd==null||cd.getGoods()==null){
				cd = new CDdinfo();
				cd.setDdbh("P"+new Date().getTime());
				cd.setRkckddinfo(cRkckddinfo);
				cd.setGoods(goods);
			}
			cd.setNub(object.get("num").toString().trim());
			cd.setJe(object.get("price").toString().trim());
			cd.setRemarks(object.get("remark").toString().trim());
			double yhje = Double.parseDouble(goods.getSj())-Double.parseDouble(cd.getJe());
			if(yhje>0)cd.setYhje(String.valueOf(yhje));
			htje += Integer.parseInt(cd.getNub())*Double.parseDouble(cd.getJe());
			cDdinfoService.save(cd);
		}
		cRkckddinfo.setHtje(String.valueOf(htje));
		if(isNewRecord){
			dao.insert(cRkckddinfo);
		}else {
			dao.update(cRkckddinfo);
		}
	}

	@Transactional(readOnly = false)
	public void save(CRkckddinfo cRkckddinfo) {
		super.save(cRkckddinfo);
	}

	@Transactional(readOnly = false)
	public void changeIssp(CRkckddinfo cRkckddinfo) {
		dao.changeIssp(cRkckddinfo);
	}

	@Transactional(readOnly = false)
	public void updateRemark(CRkckddinfo cRkckddinfo) {
		dao.updateRemark(cRkckddinfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(CRkckddinfo cRkckddinfo) {
		super.delete(cRkckddinfo);
	}

	public List<CRkckddinfo> findOrderCodeList(CRkckddinfo cRkckddinfo) {
		return dao.findOrderCodeList(cRkckddinfo);
	}


	/**
	 * shizx 2017/08/08
	 * 销售出库审批方法
	 * */
	@Transactional(readOnly = false,rollbackFor = Exception.class)
	public void saveInfo(CRkckddinfo cRkckddinfo, FDiscount fDiscount, List<CDdinfo> cDdinfoList,
						 FReceipt fReceipt, FIncomeRecord fIncomeRecord, FAccount fAccount,
						 FAccount fAccounttwo,String biaoshi){
		if(StringUtils.isNotBlank(cRkckddinfo.getId())){
			cRkckddinfo.setIssp("1");
			dao.updateIssp(cRkckddinfo);
		}
		if(StringUtils.isNotBlank(fReceipt.getReceiptCode())){
			fReceiptDao.insert(fReceipt);
		}
		if(StringUtils.isNotBlank(fIncomeRecord.getOrderId())){
			fIncomeRecordDao.insert(fIncomeRecord);
		}
		if(StringUtils.isNotBlank(fAccount.getId())){
			fAccountDao.BalanceAdd(fAccount);
		}
		if(StringUtils.isNotBlank(fAccounttwo.getId())){
			fAccountDao.Balancejs(fAccount);
		}
		Double zh=0.0;
		if(cDdinfoList.size()>0){
			for (int i = 0; i < cDdinfoList.size(); i++) {//遍历子订单集合
			//库存表减少
			CHgoods cHgoods=new CHgoods();//new  库存对象
			cHgoods.setGoods(new CGoods(cDdinfoList.get(i).getGoods().getId()));//set商品ID
			cHgoods.setHouse(new CHouse(cDdinfoList.get(i).getHouse().getId()));//set仓库ID
			cHgoods.setNub(cDdinfoList.get(i).getNub());
			//-----------------调用库存增加减少方法
			cHgoodsDao.minStock(cHgoods);

			Double yhje=Double.parseDouble(cDdinfoList.get(i).getYhje());
			zh+=yhje;//子订单优惠金额
				/**
				 * 出库记录预留
				 * */
				if(StringUtils.isNotBlank(biaoshi)){
					CCkinfo cCkinfo=new CCkinfo();
					cCkinfo.preInsert();
					cCkinfo.setJe(cDdinfoList.get(i).getSjje());
					cCkinfo.setCkqcbj(cDdinfoList.get(i).getRksjcbj());
					cCkinfo.setCkhcbj(cDdinfoList.get(i).getRksjcbj());
					cCkinfo.setNub(cDdinfoList.get(i).getNub());
					cCkinfo.setGoods(new CGoods(cDdinfoList.get(i).getGoods().getId()));
					cCkinfo.setHouse(new CHouse(cDdinfoList.get(i).getHouse().getId()));
					cCkinfo.setCkdate(cDdinfoList.get(i).getRkckdate());
					cCkinfo.setJsr(new User(UserUtils.getUser().getId()));
					cCkinfo.setDdinfo(new CDdinfo(cDdinfoList.get(i).getId()));
					cCkinfo.setIssp("1");
					if(biaoshi.equals("5")){
						cCkinfo.setSupplier(new CSupplier(cDdinfoList.get(i).getSupplier().getId()));
						cCkinfo.setState("1");
					}else{
						cCkinfo.setStore(new CStore(cDdinfoList.get(i).getStore().getId()));
						cCkinfo.setState("4");
					}
					cCkinfoDao.insert(cCkinfo);
				}

		}
		}
		if(StringUtils.isNotBlank(fDiscount.getYhje())){
			fDiscountDao.insert(fDiscount);
		}else{
			fDiscount.setYhje(zh.toString());
			fDiscount.setLx("0");
			fDiscountDao.insert(fDiscount);
		}
	}

}