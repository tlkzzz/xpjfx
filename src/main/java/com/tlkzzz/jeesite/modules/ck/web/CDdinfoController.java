/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tlkzzz.jeesite.common.mapper.JsonMapper;
import com.tlkzzz.jeesite.modules.ck.entity.*;
import com.tlkzzz.jeesite.modules.ck.service.*;
import com.tlkzzz.jeesite.modules.cw.entity.*;
import com.tlkzzz.jeesite.modules.cw.service.*;
import com.tlkzzz.jeesite.modules.sys.utils.ExcelCreateUtils;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tlkzzz.jeesite.common.config.Global;
import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.web.BaseController;
import com.tlkzzz.jeesite.common.utils.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 订单Controller
 * @author xrc
 * @version 2017-03-27
 */
@Controller
@RequestMapping(value = "${adminPath}/ck/cDdinfo")
public class CDdinfoController extends BaseController {

	@Autowired
	private CDdinfoService cDdinfoService;
	@Autowired
	private CSupplierService cSupplierService;
	@Autowired
	private CStoreService cStoreService;
	@Autowired
	private CHouseService cHouseService;
	@Autowired
	private CHgoodsService cHgoodsService;
	@Autowired
	private CGoodsService cGoodsService;
	@Autowired
	private CRkinfoService cRkinfoService;
	@Autowired
	private FAccountService fAccountService;
	@Autowired
	private FPaymentService fPaymentService;
	@Autowired
	private FExpenRecordService fExpenRecordService;
	@Autowired
	private FReceiptService fReceiptService;
	@Autowired
	private FIncomeRecordService fIncomeRecordService;
	@ModelAttribute
	public CDdinfo get(@RequestParam(required=false) String id) {
		CDdinfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cDdinfoService.get(id);
		}
		if (entity == null){
			entity = new CDdinfo();
		}
		return entity;
	}
	/**		采购部分代码开始		 **/
	@RequiresPermissions("ck:cCginfo:view")
	@RequestMapping(value = "cgDdList")
	public String cgDdList(CDdinfo cDdinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CDdinfo> page = cDdinfoService.findPage(new Page<CDdinfo>(request, response), cDdinfo);
		model.addAttribute("cDdinfo", cDdinfo);
		model.addAttribute("supplierList", cSupplierService.findList(new CSupplier()));
		model.addAttribute("houseList", cHouseService.findList(new CHouse()));
		model.addAttribute("goodsList", cGoodsService.findList(new CGoods()));
		model.addAttribute("storeList", cStoreService.findList(new CStore()));
		model.addAttribute("page", page);
		return "modules/ck/cDdinfoList";
	}

	/**		采购部分代码结束		 **/

	@RequiresPermissions("ck:cDdinfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(CDdinfo cDdinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CDdinfo> page = cDdinfoService.findPage(new Page<CDdinfo>(request, response), cDdinfo);
		model.addAttribute("cDdinfo", cDdinfo);
		model.addAttribute("page", page);
		return "modules/ck/cDdinfoList";
	}

	@RequiresPermissions("ck:cDdinfo:view")
	@RequestMapping(value = "returnGoodsList")
	public String returnGoodsList(CDdinfo cDdinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CDdinfo> page = cDdinfoService.findPage(new Page<CDdinfo>(request, response), cDdinfo);
		model.addAttribute("cDdinfoList", cDdinfoService.thfindList(cDdinfo));
		model.addAttribute("cDdinfo", cDdinfo);
		model.addAttribute("page", page);
		return "modules/ck/returnGoodsList";
	}

	@RequiresPermissions("ck:cDdinfo:view")
	@RequestMapping(value = "form")
	public String form(CDdinfo cDdinfo, Model model) {
		model.addAttribute("cDdinfo", cDdinfo);
		return "modules/ck/cDdinfoForm";
	}

	@RequiresPermissions("ck:cDdinfo:edit")
	@RequestMapping(value = "save")
	public String save(CDdinfo cDdinfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cDdinfo)){
			return form(cDdinfo, model);
		}
		cDdinfoService.save(cDdinfo);
		addMessage(redirectAttributes, "保存订单成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cDdinfo/?repage";
	}

	@RequiresPermissions("ck:cDdinfo:edit")
	@RequestMapping(value = "delete")
	public String delete(CDdinfo cDdinfo, RedirectAttributes redirectAttributes) {
		cDdinfoService.delete(cDdinfo);
		addMessage(redirectAttributes, "删除订单成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cDdinfo/?repage";
	}

	@RequiresPermissions("User")
	@RequestMapping(value = "thsh")
	public String thsh(String ids,Model model) {
		model.addAttribute("IDcarddList", fAccountService.findList(new FAccount()));
		model.addAttribute("cDdinfo",new CDdinfo());
		model.addAttribute("ids",ids);
		return "modules/ck/shxxym";
	}

	/**
	 * 销售退货单审批
	 * */
	@RequiresPermissions("User")
	@RequestMapping(value = "thsp")
	public String thsp(String ids,CDdinfo cDdinfos) {

		CDdinfo cDdinfo=new CDdinfo();
		cDdinfo.setSfkId(ids);
		List<CDdinfo> cDdinfoList=cDdinfoService.thfindList(cDdinfo);//子订单信息
		FPayment fPayment=new FPayment();
		fPayment.setId(ids);
		List<FPayment> fPaymentList=fPaymentService.findList(fPayment);
		for(int i=0;i<cDdinfoList.size();i++) {
			String supplier = cDdinfoList.get(i).getSupplier().getId();
			if (StringUtils.isNotBlank(supplier)) {
				//退货给供应商
			} else {
				//客户退货
				String goodsId = cDdinfoList.get(i).getGoods().getId();
				String housId = cDdinfoList.get(i).getHouse().getId();
				CHgoods cHgoods = new CHgoods();
				cHgoods.setGoods(new CGoods(goodsId));
				cHgoods.setHouse(new CHouse(housId));
				List<CHgoods> cHgoodsList = cHgoodsService.findList(cHgoods);//库存信息
				//库存表添加
				Double nub = Double.parseDouble(cHgoodsList.get(i).getNub());//库存数量
				Double thsl = Double.parseDouble(cDdinfoList.get(i).getThsl());//退货数量
				Double kcsl = nub + thsl;
				if (cHgoodsList.size() > 0) {//库存中存在此商品,更新库存
					String id = cHgoodsList.get(0).getId();
					cHgoods.setNub(kcsl.toString());
					cHgoods.setId(id);
					cHgoodsService.kcsl(cHgoods);
				} else {//库存中不存在此商品,新增商品库存
					cHgoods.setNub(cDdinfoList.get(i).getThsl());
					cHgoodsService.save(cHgoods);
				}
				//库存表添加完毕

				//入库记录表添加
				CRkinfo cRkinfo = new CRkinfo();
				cRkinfo.setGoods(new CGoods(goodsId));
				cRkinfo.setHouse(new CHouse(housId));
				cRkinfo.setRknub(cDdinfoList.get(i).getThsl());
				cRkinfo.setRkhnub(kcsl.toString());
				CGoods cGoods = new CGoods();
				cGoods.setId(goodsId);
				List<CGoods> cGoodsList = cGoodsService.findList(cGoods);
				cRkinfo.setRkqcbj(cGoodsList.get(0).getCbj());
				Double cbj = Double.parseDouble(cGoodsList.get(0).getCbj());
				Double thjg = Double.parseDouble(cDdinfoList.get(i).getRksjcbj());
				Double hcbj = (kcsl * cbj - thsl * thjg) / (kcsl - thsl);
				cRkinfo.setRkhcbj(hcbj.toString());
				cRkinfo.setStoreId(cDdinfoList.get(i).getStore());
				cRkinfoService.save(cRkinfo);
				//入库记录表添加完毕

				//账户表中扣除退货金额
				String accountId=cDdinfos.getfAccount().getName();
				FAccount fAccount = new FAccount();
				fAccount.setId(accountId);
				List<FAccount> fAccountList=fAccountService.findList(fAccount);
				Double accountBa=Double.parseDouble(fAccountList.get(0).getAccountBalance());

				Double je=Double.parseDouble(fPaymentList.get(0).getJe());
				Double syje=accountBa-je;
				fAccount.setAccountBalance(syje.toString());
				fAccountService.syjeUpdate(fAccount);     //更新账户余额
				//账户表中扣除退货金额

				//更新付款表状态
				fPayment.setApprovalStatus("1");
				fPayment.setThstatus("1");
				fPaymentService.thstatusUpdate(fPayment);
				//更新付款表状态


			}
		}
		//添加支出记录表
		FExpenRecord fExpenRecord = new FExpenRecord();
		fExpenRecord.setOrderId(ids);
		fExpenRecord.setExpenAccount(cDdinfos.getfAccount().getName());
		fExpenRecord.setTravelAccount(fPaymentList.get(0).getTravelAccount());
		fExpenRecord.setExpenMoney(fPaymentList.get(0).getJe());
		fExpenRecord.setExpenDate(new Date());
		fExpenRecord.setJsr(fPaymentList.get(0).getJsr().getId());
		fExpenRecord.setExpenMode(fPaymentList.get(0).getPaymentMode());
		fExpenRecord.setExpenType(fPaymentList.get(0).getPaymentType());
		fExpenRecordService.save(fExpenRecord);
		//添加支出记录表
		return "redirect:"+Global.getAdminPath()+"/ck/cDdinfo/?repage";
	}


	@RequiresPermissions("User")
	@RequestMapping(value = "Gysthsp")
	public String Gysthsp(String ids,CDdinfo cDdinfos) {
		CDdinfo cDdinfo=new CDdinfo();
		cDdinfo.setSfkId(ids);
		List<CDdinfo> cDdinfoList=cDdinfoService.thfindList(cDdinfo);//子订单信息
		FReceipt fReceipt=new FReceipt();
		fReceipt.setId(ids);
		List<FReceipt> fReceiptList=fReceiptService.findList(fReceipt);
		for(int i=0;i<cDdinfoList.size();i++) {
			String goodsId = cDdinfoList.get(i).getGoods().getId();
			String housId = cDdinfoList.get(i).getHouse().getId();
			CHgoods cHgoods = new CHgoods();
			cHgoods.setGoods(new CGoods(goodsId));
			cHgoods.setHouse(new CHouse(housId));
			List<CHgoods> cHgoodsList = cHgoodsService.findList(cHgoods);//库存信息
			//库存表添加
			Double nub = Double.parseDouble(cHgoodsList.get(i).getNub());//库存数量
			Double thsl = Double.parseDouble(cDdinfoList.get(i).getThsl());//退货数量
			Double kcsl = nub - thsl;
			if (cHgoodsList.size() > 0) {//库存中存在此商品,更新库存dd
				String id = cHgoodsList.get(0).getId();
				cHgoods.setNub(kcsl.toString());
				cHgoods.setId(id);
				cHgoodsService.kcsl(cHgoods);
			} else {//库存中不存在此商品,新增商品库存
				cHgoods.setNub(cDdinfoList.get(i).getThsl());
				cHgoodsService.save(cHgoods);
			}
			//库存表添加完毕
			//账户表中扣除退货金额
			String accountId=cDdinfos.getfAccount().getName();
			FAccount fAccount = new FAccount();
			fAccount.setId(accountId);
			List<FAccount> fAccountList=fAccountService.findList(fAccount);
			Double accountBa=Double.parseDouble(fAccountList.get(0).getAccountBalance());

			Double je=Double.parseDouble(fReceiptList.get(0).getJe());
			Double syje=accountBa+je;
			fAccount.setAccountBalance(syje.toString());
			fAccountService.syjeUpdate(fAccount);     //更新账户余额
			//账户表中扣除退货金额
			//更新付款表状态
			fReceipt.setApprovalStatus("1");
			fReceipt.setThstatus("1");
			fReceiptService.thstatusUpdate(fReceipt);
			//更新付款表状态
		}
		//添加支出记录表
		FIncomeRecord fIncomeRecord = new FIncomeRecord();
		fIncomeRecord.setOrderId(ids);
		fIncomeRecord.setIncomeAccount(cDdinfos.getfAccount().getName());
		fIncomeRecord.setTraverAccount(fReceiptList.get(0).getTravelAccount());
		fIncomeRecord.setIncomeMoney(fReceiptList.get(0).getJe());
		fIncomeRecord.setIncomeDate(new Date());
		fIncomeRecord.setJsr(fReceiptList.get(0).getJsr().getId());
		fIncomeRecord.setIncomeMode(fReceiptList.get(0).getReceiptMode());
		fIncomeRecord.setIncomeType(fReceiptList.get(0).getReceiptType());
		fIncomeRecordService.save(fIncomeRecord);
		//添加支出记录表
		return "modules/ck/GysReturn";
	}

	/**		报表start	**/
	/**
	 * 报废报表
	 * @param cDdinfo
	 * @param type 1或null:商品明细，2:商品汇总
	 * @param model
	 * @return
	 */
	@RequiresPermissions("ck:cDdinfoReport:view")
	@RequestMapping(value = "scrapList")
	public String scrapList(CDdinfo cDdinfo, String type, Model model) {
		cDdinfo.setRkckddinfo(new CRkckddinfo());
		cDdinfo.getRkckddinfo().setState("4");//报废录单
		List<CDdinfo> list = new ArrayList<CDdinfo>();
		if(StringUtils.isNotBlank(type)&&"2".equals(type)){
		 	list = cDdinfoService.findReportList(cDdinfo);
		}else {
			list = cDdinfoService.processUnit(cDdinfoService.findList(cDdinfo));
		}
		model.addAttribute("list",list);
		model.addAttribute("type",type);
		model.addAttribute("cDdinfo",cDdinfo);
		model.addAttribute("houseList", cHouseService.findList(new CHouse()));
		model.addAttribute("goodsList",cGoodsService.findList(new CGoods()));
		return "modules/report/cDdinfoScrapList";
	}
//报废导出
@RequestMapping(value = "bfexcel")
public String bfexcel(CDdinfo cDdinfo, String type, Model model,HttpServletResponse response) {
	cDdinfo.setRkckddinfo(new CRkckddinfo());
	cDdinfo.getRkckddinfo().setState("4");//报废录单
	List<CDdinfo> list = new ArrayList<CDdinfo>();
	if(StringUtils.isNotBlank(type)&&"2".equals(type)){
		list = cDdinfoService.findReportList(cDdinfo);
		ExcelCreateUtils.bfexportlist(response,list,"2");
	}else {
		list = cDdinfoService.processUnit(cDdinfoService.findList(cDdinfo));
		ExcelCreateUtils.bfexportlist(response,list,"1");
	}
	return null;
}
	@RequiresPermissions("cw:fDiscountReport:view")
	@RequestMapping(value = "discountDDinfoReport")
	public String discountDDinfoReport(CDdinfo cDdinfo, Model model){
		model.addAttribute("list", cDdinfoService.findDiscountList(cDdinfo));
		model.addAttribute("houseList", cHouseService.findList(new CHouse()));
		model.addAttribute("goodsList", cGoodsService.findList(new CGoods()));
		model.addAttribute("cDdinfo", cDdinfo);
		return "modules/report/fDisDDinfoReportList";
	}
	@RequestMapping(value = "goodsexcel")
	public String goodsexcel(CDdinfo cDdinfo,HttpServletResponse response){
		List<CDdinfo> list=cDdinfoService.findList(cDdinfo);
		ExcelCreateUtils.goodsexcel(response,list,"1");
		return null;
	}
	/**
	 * 业务员销售分析
	 * @param cDdinfo
	 * @param type
	 * @param model
	 * @return
	 */
	@RequiresPermissions("ck:salesAnalysisReport:view")
	@RequestMapping(value = "salesAnalysis")
	public String salesAnalysis(CDdinfo cDdinfo, String type, String rkckdate, Model model){
		SimpleDateFormat sdf = new SimpleDateFormat();
		if(cDdinfo.getRkckdate()==null&&StringUtils.isNotBlank(rkckdate)){
			cDdinfo.setRkckdate(new Date(Integer.parseInt(rkckdate)-1900,0,1));
		}
		Date date = new Date();
		cDdinfo.setType("2,3,4,5,9");//只查询状态为2,3,4,5,9的数据(销售的)
		List<Map> mapList = new ArrayList<Map>();
		if(StringUtils.isBlank(type)||"1".equals(type)) {
			sdf.applyPattern("yyyy");
			cDdinfo = cDdinfoService.processYear(cDdinfo, date);
			List<CDdinfo> userList = cDdinfoService.findUserList(cDdinfo);
			for (CDdinfo cd : userList) {
				Map map = new HashMap();
				List<CDdinfo> cdList = new ArrayList<CDdinfo>();
				for (int i = 0; i < 12; i++) {
					cd.setStartDate(new Date(cDdinfo.getEndDate().getYear(), i, 1));
					cd.setEndDate(new Date(cDdinfo.getEndDate().getYear(), i + 1, 1));
					cdList.add(i, cDdinfoService.getSalesSum(cd));
				}
				map.put("cDdinfo", cd);
				map.put("cdList", cdList);
				map.put("date", sdf.format(cd.getStartDate()));
				mapList.add(map);
			}
			model.addAttribute("mapList", mapList);
		}else if("2".equals(type)){
			sdf.applyPattern("yyyy年MM");
			cDdinfo = cDdinfoService.processYearMonth(cDdinfo, date);
			List<CDdinfo> userList = cDdinfoService.findUserList(cDdinfo);
			for (CDdinfo cd : userList) {
				Map map = new HashMap();
				List<CDdinfo> cdList = new ArrayList<CDdinfo>();
				cDdinfo.getEndDate().setDate(cDdinfo.getEndDate().getDate()-1);
				for (int i=1;i<=cDdinfo.getEndDate().getDate();i++) {
					cd.setStartDate(new Date(cDdinfo.getStartDate().getYear(), cDdinfo.getStartDate().getMonth(), i));
					cd.setEndDate(new Date(cDdinfo.getStartDate().getYear(), cDdinfo.getStartDate().getMonth(), i+1));
					cdList.add(i-1, cDdinfoService.getSalesSum(cd));
				}
				map.put("cDdinfo", cd);
				map.put("cdList", cdList);
				map.put("date", sdf.format(cd.getStartDate()));
				mapList.add(map);
			}
			model.addAttribute("mapList", mapList);
		}
		model.addAttribute("cDdinfo", cDdinfo);
		model.addAttribute("type", type);
		return "modules/report/cDdinfoSalesAnalysis";//页面需要调整样式和值的展示
	}

	/**
	 * 商品销售分析
	 * @param cDdinfo
	 * @param type
	 * @param model
	 * @return
	 */
	@RequiresPermissions("ck:salesAnalysisReport:view")
	@RequestMapping(value = "goodsSalesAnalysis")
	public String goodsSalesAnalysis(CDdinfo cDdinfo, String type, String rkckdate, Model model){
		SimpleDateFormat sdf = new SimpleDateFormat();
		if(cDdinfo.getRkckdate()==null&&StringUtils.isNotBlank(rkckdate)){
			cDdinfo.setRkckdate(new Date(Integer.parseInt(rkckdate)-1900,0,1));
		}
		Date date = new Date();
		cDdinfo.setType(null);
		List<Map> mapList = new ArrayList<Map>();
		if(StringUtils.isBlank(type)||"1".equals(type)) {
			sdf.applyPattern("yyyy");
			cDdinfo = cDdinfoService.processYear(cDdinfo,date);
			List<CDdinfo> goodsList = cDdinfoService.findGoodsList(cDdinfo);
			for (CDdinfo cd : goodsList) {
				Map map = new HashMap();
				List<CDdinfo> cdList = new ArrayList<CDdinfo>();
				for (int i = 0; i < 12; i++) {
					cd.setStartDate(new Date(cDdinfo.getEndDate().getYear(), i, 1));
					cd.setEndDate(new Date(cDdinfo.getEndDate().getYear(), i + 1, 1));
					cdList.add(i, cDdinfoService.getGoodsSalesSum(cd));
				}
				map.put("cDdinfo", cd);
				map.put("cdList", cdList);
				map.put("date", sdf.format(cd.getStartDate()));
				mapList.add(map);
			}
			model.addAttribute("mapList", mapList);
		}else if("2".equals(type)){
			sdf.applyPattern("yyyy年MM");
			cDdinfo = cDdinfoService.processYearMonth(cDdinfo,date);
			List<CDdinfo> goodsList = cDdinfoService.findGoodsList(cDdinfo);
			for (CDdinfo cd : goodsList) {
				Map map = new HashMap();
				List<CDdinfo> cdList = new ArrayList<CDdinfo>();
				cDdinfo.getEndDate().setDate(cDdinfo.getEndDate().getDate()-1);
				for (int i=1;i<=cDdinfo.getEndDate().getDate();i++) {
					cd.setStartDate(new Date(cDdinfo.getStartDate().getYear(), cDdinfo.getStartDate().getMonth(), i));
					cd.setEndDate(new Date(cDdinfo.getStartDate().getYear(), cDdinfo.getStartDate().getMonth(), i+1));
					cdList.add(i-1, cDdinfoService.getGoodsSalesSum(cd));
				}
				map.put("cDdinfo", cd);
				map.put("cdList", cdList);
				map.put("date", sdf.format(cd.getStartDate()));
				mapList.add(map);
			}
			model.addAttribute("mapList", mapList);
		}
		model.addAttribute("cDdinfo", cDdinfo);
		model.addAttribute("type", type);
		return "modules/report/cDdinfoGoodsSalesAnalysis";
	}
	/**
	 * 业务员品项销售
	 * @param cDdinfo
	 * @param type
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "xslist")
	public String xslist(CDdinfo cDdinfo, String type, Model model ) {
		cDdinfo.setType("2,3,5");
		List<CDdinfo> list = new ArrayList<CDdinfo>();
		List<CDdinfo> userList=cDdinfoService.findUserList(cDdinfo);
		if (StringUtils.isNotBlank(type) && "2".equals(type)) {
			list = cDdinfoService.getgclass(cDdinfo);
		} else if (StringUtils.isNotBlank(type) && "3".equals(type)) {
			list = cDdinfoService.setgclass(cDdinfo);
		}
		else {
				list = cDdinfoService.getgclass(cDdinfo);

		}
		model.addAttribute("goodsList", cGoodsService.findList(new CGoods()));
		model.addAttribute("houseList", cHouseService.findList(new CHouse()));
		model.addAttribute("cDdinfo", cDdinfo);
		model.addAttribute("type", type);
		model.addAttribute("list", list);
		model.addAttribute("userList", userList);
		return "modules/report/cDdinfoxsList";
	}
	/**
	 * 业务员订单查询
	 * @param cDdinfo
	 * @param model
	 * @return
	 */
	@RequiresPermissions("ck:ywylistInquire:view")
	@RequestMapping(value = "ywylistInquire")
	public String ywylistInquire(CDdinfo cDdinfo,  Model model ) {
		List<CDdinfo> list = new ArrayList<CDdinfo>();
		list = cDdinfoService.ywylist(cDdinfo);
		model.addAttribute("goodsList", cGoodsService.findList(new CGoods()));
		model.addAttribute("houseList", cHouseService.findList(new CHouse()));
		model.addAttribute("cDdinfo", cDdinfo);
		model.addAttribute("list", list);
		return "modules/report/cDdinfoywyList";
	}
	/**
	 * 业务员订单导出
	 * @param cDdinfo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "ywyExcel")
	public String ywyExcel(CDdinfo cDdinfo,  Model model,HttpServletResponse response  ) {
		List<CDdinfo> list = new ArrayList<CDdinfo>();
		list = cDdinfoService.ywylist(cDdinfo);
		ExcelCreateUtils.ywyexport(response,list,"1");
		return null;
	}
	@RequiresPermissions("ck:cDdinfo:view")
	@RequestMapping(value = "lrtblist")
	public String lrtblist(CDdinfo cDdinfo,String startDate, Model model) {
		cDdinfo.setType("3,4");
		if(StringUtils.isEmpty(startDate)){
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM");
			startDate=format.format(date);
			startDate=startDate.substring(0,4);
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMM");
		String a;String a1;String a2;String a3;String a4;String a5;String a6;String a7;String a8;String a9;String a10;String a11;
		a=startDate+"01";
		a1=startDate+"02";
		a2=startDate+"03";
		a3=startDate+"04";
		a4=startDate+"05";
		a5=startDate+"06";
		a6=startDate+"07";
		a7=startDate+"08";
		a8=startDate+"09";
		a9=startDate+"10";
		a10=startDate+"11";
		a11=startDate+"12";
		List<CDdinfo> list = cDdinfoService.tblist(cDdinfo);
		Double yiyue=0.0;
		Double eryue=0.0;
		Double sanyue=0.0;
		Double siyue=0.0;
		Double wuyue=0.0;
		Double liuyue=0.0;
		Double qiyue=0.0;
		Double baiyue=0.0;
		Double jiuyue=0.0;
		Double shiyue=0.0;
		Double shiyiyue=0.0;
		Double shieryue=0.0;
		for(int i=0; i< list.size(); i++) {
			cDdinfo = list.get(i);
			Date dd = list.get(i).getCreateDate();
			String str = sdf.format(dd);
			//一月份
			if (a.equals(str)) {
				Double lr = cDdinfoService.lr(cDdinfo);
				yiyue += lr;
				String s = String.valueOf(yiyue);
				String newD = s.substring(0,s.indexOf("."));
				model.addAttribute("newD",newD);
			}else {
				String s = String.valueOf(yiyue);
				String newD = s.substring(0,s.indexOf("."));
				model.addAttribute("newD",newD);
			}
			//二月份
			if (a1.equals(str)) {
				Double lr = cDdinfoService.lr(cDdinfo);
				eryue += lr;
				String s = String.valueOf(eryue);
				String newD2 = s.substring(0,s.indexOf("."));
				model.addAttribute("newD2",newD2);
			}else{
				String s = String.valueOf(eryue);
				String newD2 = s.substring(0,s.indexOf("."));
				model.addAttribute("newD2",newD2);
			}
			//三月份
			if (a2.equals(str)) {
				Double lr = cDdinfoService.lr(cDdinfo);
				sanyue += lr;
				String s = String.valueOf(sanyue);
				String newD3 = s.substring(0,s.indexOf("."));
				model.addAttribute("newD3",newD3);
			}else{
				String s = String.valueOf(sanyue);
				String newD3 = s.substring(0,s.indexOf("."));
				model.addAttribute("newD3",newD3);
			}
			//四月份
			if (a3.equals(str)) {
				Double lr = cDdinfoService.lr(cDdinfo);
				siyue += lr;
				String s = String.valueOf(siyue);
				String newD4 = s.substring(0,s.indexOf("."));
				model.addAttribute("newD4",newD4);
			}
			else{
				String s = String.valueOf(siyue);
				String newD4 = s.substring(0,s.indexOf("."));
				model.addAttribute("newD4",newD4);
			}
			//五月份
			if (a4.equals(str)) {
				Double lr = cDdinfoService.lr(cDdinfo);
				wuyue += lr;
				String s = String.valueOf(wuyue);
				String newD5 = s.substring(0,s.indexOf("."));
				model.addAttribute("newD5",newD5);
			}else{
				String s = String.valueOf(wuyue);
				String newD5 = s.substring(0,s.indexOf("."));
				model.addAttribute("newD5",newD5);
			}
			//六月份
			if (a5.equals(str)) {
				Double lr = cDdinfoService.lr(cDdinfo);
				liuyue += lr;
				String s = String.valueOf(liuyue);
				String newD6 = s.substring(0,s.indexOf("."));
				model.addAttribute("newD6",newD6);
			}else{
				String s = String.valueOf(liuyue);
				String newD6 = s.substring(0,s.indexOf("."));
				model.addAttribute("newD6",newD6);
			}
			//七月份
			if (a6.equals(str)) {
				Double lr = cDdinfoService.lr(cDdinfo);
				qiyue += lr;
				String s = String.valueOf(qiyue);
				String newD7 = s.substring(0,s.indexOf("."));
				model.addAttribute("newD7",newD7);
			}else{
				String s = String.valueOf(qiyue);
				String newD7 = s.substring(0,s.indexOf("."));
				model.addAttribute("newD7",newD7);
			}
			//八月份
			if (a7.equals(str)) {
				Double lr = cDdinfoService.lr(cDdinfo);
				baiyue += lr;
				String s = String.valueOf(baiyue);
				String newD8 = s.substring(0,s.indexOf("."));
				model.addAttribute("newD8",newD8);
			}else{
				String s = String.valueOf(baiyue);
				String newD8 = s.substring(0,s.indexOf("."));
				model.addAttribute("newD8",newD8);
			}
			//九月份
			if (a8.equals(str)) {
				Double lr = cDdinfoService.lr(cDdinfo);
				jiuyue += lr;
				String s = String.valueOf(jiuyue);
				String newD9 = s.substring(0,s.indexOf("."));
				model.addAttribute("newD9",newD9);
			}else{
				String s = String.valueOf(jiuyue);
				String newD9 = s.substring(0,s.indexOf("."));
				model.addAttribute("newD9",newD9);
			}
			//十月份
			if (a9.equals(str)) {
				Double lr = cDdinfoService.lr(cDdinfo);
				shiyue += lr;
				String s = String.valueOf(shiyue);
				String newD10 = s.substring(0,s.indexOf("."));
				model.addAttribute("newD10",newD10);
			}else{
				String s = String.valueOf(shiyue);
				String newD10 = s.substring(0,s.indexOf("."));
				model.addAttribute("newD10",newD10);
			}
			//十一月份
			if (a10.equals(str)) {
				Double lr = cDdinfoService.lr(cDdinfo);
				shiyiyue += lr;
				String s = String.valueOf(shiyiyue);
				String newD11 = s.substring(0,s.indexOf("."));
				model.addAttribute("newD11",newD11);
			}else{
				String s = String.valueOf(shiyiyue);
				String newD11 = s.substring(0,s.indexOf("."));
				model.addAttribute("newD11",newD11);
			}
			//12月份
			if (a11.equals(str)) {
				Double lr = cDdinfoService.lr(cDdinfo);
				shieryue += lr;
				String s = String.valueOf(shieryue);
				String newD12 = s.substring(0,s.indexOf("."));
				model.addAttribute("newD12",newD12);
			}else{
				String s = String.valueOf(shieryue);
				String newD12 = s.substring(0,s.indexOf("."));
				model.addAttribute("newD12",newD12);
			}
		}
		model.addAttribute("startDate",startDate);
		return "modules/ck/cCkinfolvList";
	}

	@ResponseBody
	@RequestMapping(value = "lrajax")
	public List lrajax(CDdinfo cDdinfo,String startDate, Model model) {
		 cDdinfo.setType("3,4");
		 if(StringUtils.isEmpty(startDate)){
			 Date date=new Date();
			 DateFormat format=new SimpleDateFormat("yyyy-MM");
			 startDate=format.format(date);
			 startDate=startDate.substring(0,4);
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMM");
		String a;String a1;String a2;String a3;String a4;String a5;String a6;String a7;String a8;String a9;String a10;String a11;
		a=startDate+"01";
		a1=startDate+"02";
		a2=startDate+"03";
		a3=startDate+"04";
		a4=startDate+"05";
		a5=startDate+"06";
		a6=startDate+"07";
		a7=startDate+"08";
		a8=startDate+"09";
		a9=startDate+"10";
		a10=startDate+"11";
		a11=startDate+"12";
		List<CDdinfo> list = cDdinfoService.tblist(cDdinfo);
//		List<Map> mapList = new ArrayList<Map>();
//		List<Double> aa=new ArrayList<Double>();
		Double yiyue=0.0;
		Double eryue=0.0;
		Double sanyue=0.0;
		Double siyue=0.0;
		Double wuyue=0.0;
		Double liuyue=0.0;
		Double qiyue=0.0;
		Double baiyue=0.0;
		Double jiuyue=0.0;
		Double shiyue=0.0;
		Double shiyiyue=0.0;
		Double shieryue=0.0;

//		Map map=new HashMap();
		List yuex=new ArrayList();
		for(int i=0; i< list.size(); i++) {
			cDdinfo = list.get(i);
			Date dd = list.get(i).getCreateDate();
			String str = sdf.format(dd);
			//一月份
			if (a.equals(str)) {
				Double lr = cDdinfoService.lr(cDdinfo);
				yiyue += lr;
				yuex.add(0,yiyue);
//		      mapList.add(map);
			}else {
				yuex.add(0,yiyue);
			}
			//二月份
			if (a1.equals(str)) {
				Double lr = cDdinfoService.lr(cDdinfo);
				eryue += lr;
				yuex.add(1,eryue);
			}else{
				yuex.add(1,eryue);
			}
			//三月份
			if (a2.equals(str)) {
				Double lr = cDdinfoService.lr(cDdinfo);
				sanyue += lr;
				yuex.add(2,sanyue);
			}else{
				yuex.add(2,sanyue);
			}
			//四月份
			if (a3.equals(str)) {
				Double lr = cDdinfoService.lr(cDdinfo);
				siyue += lr;
				yuex.add(3,siyue);
			}
			else{
				yuex.add(3,siyue);
			}
			//五月份
			if (a4.equals(str)) {
				Double lr = cDdinfoService.lr(cDdinfo);
				wuyue += lr;
				yuex.add(4,wuyue);
			}else{
				yuex.add(4,wuyue);
			}
			//六月份
			if (a5.equals(str)) {
				Double lr = cDdinfoService.lr(cDdinfo);
				liuyue += lr;
				yuex.add(5,liuyue);
			}else{
				yuex.add(5,liuyue);
			}
			//七月份
			if (a6.equals(str)) {
				Double lr = cDdinfoService.lr(cDdinfo);
				qiyue += lr;
				yuex.add(6,qiyue);
			}else{
				yuex.add(6,qiyue);
			}
			//八月份
			if (a7.equals(str)) {
				Double lr = cDdinfoService.lr(cDdinfo);
				baiyue += lr;
				yuex.add(7,baiyue);
			}else{
				yuex.add(7,baiyue);
			}
			//九月份
			if (a8.equals(str)) {
				Double lr = cDdinfoService.lr(cDdinfo);
				jiuyue += lr;
				yuex.add(8,jiuyue);
			}else{
				yuex.add(8,jiuyue);
			}
			//十月份
			if (a9.equals(str)) {
				Double lr = cDdinfoService.lr(cDdinfo);
				shiyue += lr;
				yuex.add(9,shiyue);
			}else{
				yuex.add(9,shiyue);
			}
			//十一月份
			if (a10.equals(str)) {
				Double lr = cDdinfoService.lr(cDdinfo);
				shiyiyue += lr;
				yuex.add(10,shiyiyue);
			}else{
				yuex.add(10,shiyiyue);
			}
			//12月份
			if (a11.equals(str)) {
				Double lr = cDdinfoService.lr(cDdinfo);
				shieryue += lr;
				yuex.add(11,shieryue);
			}else{
				yuex.add(11,shieryue);
			}
//				double htje =0.0; double lr =0.0;  double yhje=0.0; double sl=0.0; double cbj=0.0; double thje=0.0; double thcbj=0.0;
//				double thsl=0.0;
//				htje =(StringUtils.isNotBlank(cDdinfo.getSjje()))?Double.parseDouble(cDdinfo.getSjje()):0.0;
//				sl	=(StringUtils.isNotBlank(cDdinfo.getNub()))?Double.parseDouble(cDdinfo.getNub()):0.0;
//				yhje=(StringUtils.isNoneBlank(cDdinfo.getYhje()))?Double.parseDouble(cDdinfo.getYhje()):0.0; //优惠金额
//				cbj =(StringUtils.isNoneBlank(cDdinfo.getRksjcbj()))?Double.parseDouble(cDdinfo.getRksjcbj()):0.0;
//				lr=(htje*sl)-yhje-(cbj*sl);
//				if(StringUtils.isNotBlank(cDdinfo.getThje())){
//					//退货金额
//					thje=(StringUtils.isNotBlank(cDdinfo.getThje()))?Double.parseDouble(cDdinfo.getThje()):0.0;
//					thcbj=(StringUtils.isNoneBlank(cDdinfo.getRksjcbj()))?Double.parseDouble(cDdinfo.getRksjcbj()):0.0;
//					thsl=(StringUtils.isNoneBlank(cDdinfo.getNub()))?Double.parseDouble(cDdinfo.getNub()):0.0;
//					double thlr=thje+(thcbj*thsl);
//					//退货利润
//					lr=lr-thlr;
//				}
//				Map map = new HashMap();
//				map.put("lr", lr);
//				mapList.add(i,map);
//			}else{
//				 double lr=0.0;
//				 Map map = new HashMap();
//				 map.put("lr", lr);
//				 mapList.add(i,map);
//			 }
		}
		return yuex;
	}

	/**
	 * 业务员
	 * @param cDdinfo
	 * @param type
	 * @param startDate
	 * @param endDate
	 * @param model
	 * @return
	 */
	@RequiresPermissions("ck:cDdinfo:view")
	@RequestMapping(value = "ywylr")
	public String ywylr(CDdinfo cDdinfo, String type, String startDate, String endDate, Model model) {
		if(StringUtils.isEmpty(startDate)){
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			startDate=format.format(date);
		}
		if(StringUtils.isEmpty(endDate)){
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			endDate=format.format(date);
		}
		model.addAttribute("startDate",startDate);
		model.addAttribute("endDate",endDate);
		return "modules/ck/cDdinfoywyList";
	}
	@ResponseBody
	@RequestMapping(value = "ywylrAjax")
	public List ywylrAjax(CDdinfo cDdinfo, String type, String startDate, String endDate, Model model) {
		cDdinfo.setType("3,4");
		if(StringUtils.isEmpty(startDate)){
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			startDate=format.format(date);
		}
		if(StringUtils.isEmpty(endDate)){
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			endDate=format.format(date);
		}
		Map map=new HashMap();
		List<Map> mapList = new ArrayList<Map>();
		Double zlr=0.0;
		List<CDdinfo> list=cDdinfoService.ywylrlist(cDdinfo);
		for(int i=0; i< list.size(); i++) {
			cDdinfo=list.get(i);
			if(StringUtils.isNotBlank(cDdinfo.getCreateBy().getName())){
				Double lr = cDdinfoService.lr(cDdinfo);
				String a=cDdinfo.getCreateBy().getName();
				if(list.get(i).getCreateBy().getName().contains(a)){
					zlr+=lr;
					map.put("zlr",zlr);
					map.put("name",cDdinfo.getHouse().getName());
					mapList.add(map);
				}else{
					map.put("zlr",zlr);
					map.put("name",cDdinfo.getHouse().getName());
					mapList.add(map);
				}
			}
		}
		return mapList;
	}
	/**
	 * 仓库
	 * @param cDdinfo
	 * @param type
	 * @param startDate
	 * @param endDate
	 * @param model
	 * @return
	 */
	@RequiresPermissions("ck:cDdinfo:view")
	@RequestMapping(value = "cklist")
	public String cklist(CDdinfo cDdinfo, String type, String startDate, String endDate, Model model) {
		if(StringUtils.isEmpty(startDate)){
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			startDate=format.format(date);
		}
		if(StringUtils.isEmpty(endDate)){
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			endDate=format.format(date);
		}
		model.addAttribute("startDate",startDate);
		model.addAttribute("endDate",endDate);
		return "modules/ck/cDdinfockList";
	}
	@ResponseBody
	@RequestMapping(value = "ckAjax")
	public List ckAjax(CDdinfo cDdinfo, String type, String startDate, String endDate, Model model) {
		cDdinfo.setType("3,4");
		if(StringUtils.isEmpty(startDate)){
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			startDate=format.format(date);
		}
		if(StringUtils.isEmpty(endDate)){
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			endDate=format.format(date);
		}
		Map map=new HashMap();
		List<Map> mapList = new ArrayList<Map>();
		Double zlr=0.0;
		List<CDdinfo> list=cDdinfoService.ywylrlist(cDdinfo);
		for(int i=0; i< list.size(); i++) {
			cDdinfo=list.get(i);
			if(StringUtils.isNotBlank(cDdinfo.getHouse().getName())){
				Double lr = cDdinfoService.lr(cDdinfo);
				String a=cDdinfo.getHouse().getName();
				if(list.get(i).getHouse().getName().contains(a)){
					zlr+=lr;
					map.put("zlr",zlr);
					map.put("name",cDdinfo.getHouse().getName());
					mapList.add(map);
				}else{
					map.put("zlr",zlr);
					map.put("name",cDdinfo.getHouse().getName());
					mapList.add(map);
				}
			}
		}
		return mapList;
	}
	/**
	 * 客户
	 * @param cDdinfo
	 * @param type
	 * @param startDate
	 * @param endDate
	 * @param model
	 * @return
	 */
	@RequiresPermissions("ck:cDdinfo:view")
	@RequestMapping(value = "cstore")
	public String cstore(CDdinfo cDdinfo, String type, String startDate, String endDate, Model model) {
		if(StringUtils.isEmpty(startDate)){
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			startDate=format.format(date);
		}
		if(StringUtils.isEmpty(endDate)){
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			endDate=format.format(date);
		}
		model.addAttribute("startDate",startDate);
		model.addAttribute("endDate",endDate);
		return "modules/ck/cDdinfostoreList";
	}
	@ResponseBody
	@RequestMapping(value = "cstoreAjax")
	public List cstoreAjax(CDdinfo cDdinfo, String type, String startDate, String endDate, Model model) {
		cDdinfo.setType("3,4");
		if(StringUtils.isEmpty(startDate)){
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			startDate=format.format(date);
		}
		if(StringUtils.isEmpty(endDate)){
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			endDate=format.format(date);
		}
		Map map=new HashMap();
		List<Map> mapList = new ArrayList<Map>();
		Double zlr=0.0;
		List<CDdinfo> list=cDdinfoService.ywylrlist(cDdinfo);
		for(int i=0; i< list.size(); i++) {
			cDdinfo=list.get(i);
			if(StringUtils.isNotBlank(cDdinfo.getStore().getName())){
				Double lr = cDdinfoService.lr(cDdinfo);
				String a=cDdinfo.getStore().getName();
				if(list.get(i).getStore().getName().contains(a)){
					zlr+=lr;
					map.put("zlr",zlr);
					map.put("name",cDdinfo.getStore().getName());
					mapList.add(map);
				}else{
					map.put("zlr",zlr);
					map.put("name",cDdinfo.getStore().getName());
					mapList.add(map);
				}
			}
		}
		return mapList;
	}

	@ResponseBody
	@RequestMapping(value = "findListByCrk")
	public List<CDdinfo> findListByCrk(CDdinfo cDdinfo){
		if(cDdinfo==null||cDdinfo.getRkckddinfo()==null){
			return null;
		}
		return cDdinfoService.processUnit(cDdinfoService.findList(cDdinfo));
	}
}