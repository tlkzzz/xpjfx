/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.service;

import java.util.Date;
import java.util.List;

import com.tlkzzz.jeesite.common.utils.IdGen;
import com.tlkzzz.jeesite.modules.ck.dao.CGoodsDao;
import com.tlkzzz.jeesite.modules.ck.entity.CDdinfo;
import com.tlkzzz.jeesite.modules.ck.entity.CGoods;
import com.tlkzzz.jeesite.modules.ck.entity.CShop;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.ck.entity.CRkckddinfo;
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
	private CGoodsDao cGoodsDao;

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
	
}