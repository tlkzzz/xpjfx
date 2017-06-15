/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.service;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.tlkzzz.jeesite.common.utils.StringUtils;
import com.tlkzzz.jeesite.modules.ck.entity.CRkckddinfo;
import com.tlkzzz.jeesite.modules.ck.entity.CShop;
import com.tlkzzz.jeesite.modules.sys.utils.ToolsUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.ck.entity.CDdinfo;
import com.tlkzzz.jeesite.modules.ck.dao.CDdinfoDao;

/**
 * 订单Service
 * @author xrc
 * @version 2017-03-27
 */
@Service
@Transactional(readOnly = true)
public class CDdinfoService extends CrudService<CDdinfoDao, CDdinfo> {

	public CDdinfo get(String id) {
		return super.get(id);
	}

	public CDdinfo getSalesSum(CDdinfo cDdinfo) {
		return dao.getSalesSum(cDdinfo);
	}

	public List<CDdinfo>  tblist(CDdinfo cDdinfo) {
		return dao.tblist(cDdinfo);
	}

	public List<CDdinfo> ywylrlist(CDdinfo cDdinfo){
		return dao.ywylrlist(cDdinfo);
	}

	public CDdinfo getGoodsSalesSum(CDdinfo cDdinfo) {
		return dao.getGoodsSalesSum(cDdinfo);
	}

	public List<CDdinfo> findList(CDdinfo cDdinfo) {
		return super.findList(cDdinfo);
	}

	public List<CDdinfo> findUserList(CDdinfo cDdinfo) {
		return dao.findUserList(cDdinfo);
	}

	public List<CDdinfo> findGoodsList(CDdinfo cDdinfo) {
		return dao.findGoodsList(cDdinfo);
	}
	public List<CDdinfo> getgclass(CDdinfo cDdinfo) {
		return dao.getgclass(cDdinfo);
	}

	public List<CDdinfo> setgclass(CDdinfo cDdinfo) {

		return dao.setgclass(cDdinfo);
	}
	public List<CDdinfo> ywylist(CDdinfo cDdinfo) {
		return dao.ywylist(cDdinfo);
	}

	public List<CDdinfo> findReportList(CDdinfo cDdinfo){
		return processUnit(dao.findReportList(cDdinfo));
	}

	public List<CDdinfo> findDiscountList(CDdinfo cDdinfo){
		return processUnit(dao.findDiscountList(cDdinfo));
	}

	/**
	 * 将数组中的数量变为规格数量
	 * @param list
	 * @return
	 */
	public List<CDdinfo> processUnit(List<CDdinfo> list){
		for(CDdinfo cd: list){
			if(cd==null||cd.getGoods()==null)continue;
			String[] unit = {cd.getGoods().getBig().getName(),cd.getGoods().getZong().getName(),cd.getGoods().getSmall().getName()};
			cd.setType(ToolsUtils.unitTools(cd.getGoods().getSpec().getName(),unit,Integer.parseInt(cd.getNub())));
		}
		return list;
	}

	/**
	 * 初始化年份
	 * @param cDdinfo
	 * @param date
	 * @return
	 */
	public CDdinfo processYear(CDdinfo	cDdinfo,Date date){
		if (cDdinfo.getRkckdate() == null) {
			cDdinfo.setRkckdate(date);
			cDdinfo.setStartDate(new Date(date.getYear(), 0, 0));
			cDdinfo.setEndDate(new Date(date.getYear() + 1, 0, 0));
		} else {
			cDdinfo.setStartDate(new Date(cDdinfo.getRkckdate().getYear(), 0, 0));
			cDdinfo.setEndDate(new Date(cDdinfo.getRkckdate().getYear() + 1, 0, 0));
		}
		return cDdinfo;
	}

	/**
	 * 初始化年月
	 * @param cDdinfo
	 * @param date
	 * @return
	 */
	public CDdinfo processYearMonth(CDdinfo	cDdinfo,Date date){
		if (cDdinfo.getRkckdate() == null) {
			cDdinfo.setRkckdate(date);
			cDdinfo.setStartDate(new Date(date.getYear(), date.getMonth()-1, 1));
			cDdinfo.setEndDate(new Date(date.getYear(), date.getMonth()+1-1, 1));
		} else {
			cDdinfo.setStartDate(new Date(cDdinfo.getRkckdate().getYear(), cDdinfo.getRkckdate().getMonth(), 1));
			cDdinfo.setEndDate(new Date(cDdinfo.getRkckdate().getYear(), cDdinfo.getRkckdate().getMonth()+1, 1));
		}
		return cDdinfo;
	}

	public Page<CDdinfo> findPage(Page<CDdinfo> page, CDdinfo cDdinfo) {
		return super.findPage(page, cDdinfo);
	}

	/**
	 * 查询退货单信息
	 */
	public List<CDdinfo> thfindList(CDdinfo cDdinfo) {
		return dao.thfindList(cDdinfo);
	}

	@Transactional(readOnly = false)
	public void save(CDdinfo cDdinfo) {
		super.save(cDdinfo);
	}

	/**
	 * 将购物车订单保存为订单
	 * @param cs
	 * @param cRkckddinfo
	 */
	@Transactional(readOnly = false)
	public void saveInfo(CShop cs, CRkckddinfo cRkckddinfo) {
		CDdinfo cd = new CDdinfo();
		cd.setRkckddinfo(cRkckddinfo);
		cd.setHouse(cRkckddinfo.getcHouse());
		cd.setGoods(cs.getGoods());
		cd.setDdbh(cs.getSpbh());
		cd.setSupplier(cs.getSupplier());
		cd.setNub(cs.getNub());
		if(cs.getJe()!=null)cd.setJe(String.valueOf(cs.getJe()));
		cd.setStore(cs.getStore());
		if(cs.getYhje()!=null)cd.setYhje(String.valueOf(cs.getYhje()));
		super.save(cd);
	}
	
	@Transactional(readOnly = false)
	public void delete(CDdinfo cDdinfo) {
		super.delete(cDdinfo);
	}


	/**
	 * 退货信息保存，更新原有自订单，新增退货信息添加
	 * */
	@Transactional(readOnly = false)
	public void thUpdate(CDdinfo cddinfo) {
		dao.thUpdate(cddinfo);
	}

	/**
	 * 计算利润方法
	 *
	 */
	public Double lr(CDdinfo cDdinfo) {
		double htje =0.0; double lr =0.0;  double yhje=0.0; double sl=0.0; double cbj=0.0; double thje=0.0; double thcbj=0.0;
		double thsl=0.0;
		htje =(StringUtils.isNotBlank(cDdinfo.getSjje()))?Double.parseDouble(cDdinfo.getSjje()):0.0;
		sl	=(StringUtils.isNotBlank(cDdinfo.getNub()))?Double.parseDouble(cDdinfo.getNub()):0.0;
		yhje=(StringUtils.isNoneBlank(cDdinfo.getYhje()))?Double.parseDouble(cDdinfo.getYhje()):0.0; //优惠金额
		cbj =(StringUtils.isNoneBlank(cDdinfo.getRksjcbj()))?Double.parseDouble(cDdinfo.getRksjcbj()):0.0;
		lr=(htje*sl)-yhje-(cbj*sl);
		if(StringUtils.isNotBlank(cDdinfo.getThje())){
			//退货金额
			thje=(StringUtils.isNotBlank(cDdinfo.getThje()))?Double.parseDouble(cDdinfo.getThje()):0.0;
			thcbj=(StringUtils.isNoneBlank(cDdinfo.getRksjcbj()))?Double.parseDouble(cDdinfo.getRksjcbj()):0.0;
			thsl=(StringUtils.isNoneBlank(cDdinfo.getNub()))?Double.parseDouble(cDdinfo.getNub()):0.0;
			double thlr=thje+(thcbj*thsl);
			//退货利润
			lr=lr-thlr;
		}
		return lr;
	}

}