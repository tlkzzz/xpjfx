/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tlkzzz.jeesite.modules.ck.entity.*;
import com.tlkzzz.jeesite.modules.ck.service.*;
import com.tlkzzz.jeesite.modules.cw.entity.*;
import com.tlkzzz.jeesite.modules.cw.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tlkzzz.jeesite.common.config.Global;
import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.web.BaseController;
import com.tlkzzz.jeesite.common.utils.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
//	@RequiresPermissions("ck:cDdinfoReport:view")
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
}