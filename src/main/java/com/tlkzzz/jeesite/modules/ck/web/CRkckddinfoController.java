/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tlkzzz.jeesite.modules.ck.entity.CDdinfo;
import com.tlkzzz.jeesite.modules.ck.entity.CShop;
import com.tlkzzz.jeesite.modules.ck.entity.CStore;
import com.tlkzzz.jeesite.modules.ck.service.CDdinfoService;
import com.tlkzzz.jeesite.modules.ck.service.CShopService;
import com.tlkzzz.jeesite.modules.ck.service.CStoreService;
import com.tlkzzz.jeesite.modules.cw.entity.FDiscount;
import com.tlkzzz.jeesite.modules.cw.entity.FPayment;
import com.tlkzzz.jeesite.modules.cw.entity.FReceipt;
import com.tlkzzz.jeesite.modules.cw.service.FDiscountService;
import com.tlkzzz.jeesite.modules.cw.service.FPaymentService;
import com.tlkzzz.jeesite.modules.cw.service.FReceiptService;
import com.tlkzzz.jeesite.modules.sys.utils.UserUtils;
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
import com.tlkzzz.jeesite.modules.ck.entity.CRkckddinfo;
import com.tlkzzz.jeesite.modules.ck.service.CRkckddinfoService;

import java.util.List;

/**
 * 总订单Controller
 * @author xr4
 * @version 2017-03-27
 */
@Controller
@RequestMapping(value = "${adminPath}/ck/cRkckddinfo")
public class CRkckddinfoController extends BaseController {

	@Autowired
	private CRkckddinfoService cRkckddinfoService;
	@Autowired
	private FReceiptService fReceiptService;
	@Autowired
	private FPaymentService fPaymentService;
	@Autowired
	private CStoreService cStoreService;
	@Autowired
	private CDdinfoService cDdinfoService;
	@Autowired
	private CShopService cShopService;
	@Autowired
	private FDiscountService fDiscountService;
	
	@ModelAttribute
	public CRkckddinfo get(@RequestParam(required=false) String id) {
		CRkckddinfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cRkckddinfoService.get(id);
		}
		if (entity == null){
			entity = new CRkckddinfo();
		}
		return entity;
	}
	
	/*@RequiresPermissions("ck:cRkckddinfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(CRkckddinfo cRkckddinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CRkckddinfo> page = cRkckddinfoService.findPage(new Page<CRkckddinfo>(request, response), cRkckddinfo); 
		model.addAttribute("page", page);
		return "modules/ck/cRkckddinfoList";
	}

	@RequiresPermissions("ck:cRkckddinfo:view")
	@RequestMapping(value = "form")
	public String form(CRkckddinfo cRkckddinfo, Model model) {
		model.addAttribute("cRkckddinfo", cRkckddinfo);
		return "modules/ck/cRkckddinfoForm";
	}

	@RequiresPermissions("ck:cRkckddinfo:edit")
	@RequestMapping(value = "save")
	public String save(CRkckddinfo cRkckddinfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cRkckddinfo)){
			return form(cRkckddinfo, model);
		}
		cRkckddinfoService.save(cRkckddinfo);
		addMessage(redirectAttributes, "保存总订单成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cRkckddinfo/?repage";
	}*/
	
	@RequiresPermissions("ck:cCginfo:edit")
	@RequestMapping(value = "delete")
	public String delete(CRkckddinfo cRkckddinfo, RedirectAttributes redirectAttributes) {
		CDdinfo cd = new CDdinfo();
		cd.setRkckddinfo(cRkckddinfo);
		List<CDdinfo> cdList = cDdinfoService.findList(cd);
		if(cdList.size()==0){
			cRkckddinfoService.delete(cRkckddinfo);
			addMessage(redirectAttributes, "删除总订单成功");
		}else {
			addMessage(redirectAttributes, "删除总订单失败，总订单下存在子订单请删除后再试！");
		}
		return "redirect:"+Global.getAdminPath()+"/ck/cRkckddinfo/?repage";
	}

	/**		采购申请开始 	 */
	@RequiresPermissions("ck:cCginfo:view")
	@RequestMapping(value = "cgList")
	public String cgList(CRkckddinfo cRkckddinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		cRkckddinfo.setLx("0");
		if(StringUtils.isNotBlank(cRkckddinfo.getState())) {
			UserUtils.removeCache("RKCKSTATE");
			UserUtils.putCache("RKCKSTATE", cRkckddinfo.getState());
			Page<CRkckddinfo> page = cRkckddinfoService.findPage(new Page<CRkckddinfo>(request, response), cRkckddinfo);
			model.addAttribute("cRkckddinfo", cRkckddinfo);
			model.addAttribute("page", page);
			return "modules/ck/cRkckddinfoList";
		}else {
			return "error/400";
		}
	}

	@RequiresPermissions("ck:cShop:view")
	@RequestMapping(value = "saveCgInfo")
	public String saveCgInfo(CRkckddinfo cRkckddinfo, Model model, RedirectAttributes redirectAttributes) {
		cRkckddinfo.setIssp("0");
		String retStr = "error/400";
		String state = UserUtils.getCache("RKCKSTATE").toString();
		if(StringUtils.isNotBlank(state)) {
			if("0".equals(state)||"1".equals(state)) {
				cRkckddinfo.setLx("0");//入库
				retStr = "redirect:"+Global.getAdminPath()+"/ck/cRkckddinfo/cgList?repage&state="+state;
			}else {
				cRkckddinfo.setLx("1");//出库
				retStr = "redirect:"+Global.getAdminPath()+"/ck/cRkckddinfo/libraryList?repage&state="+state;
			}
			cRkckddinfo.setState(state);
			CShop cs = new CShop();
			cs.setUserid(UserUtils.getUser().getId());
			List<CShop> shopList = cShopService.findList(cs);
			cRkckddinfoService.saveRkInfo(cRkckddinfo, shopList);
			cShopService.deleteByUserId(cs.getUserid());
			if(cRkckddinfo.getReceipt()!=null){//保存订单ID到收款表
				cRkckddinfo.getReceipt().setReceiptCode(cRkckddinfo.getId());
				fReceiptService.updateReceiptCode(cRkckddinfo.getReceipt());
				//如果receipt中有ID则为出库财务信息保存后跳转过来，并且带有收款记录ID为参数

				FReceipt receipt = fReceiptService.get(cRkckddinfo.getReceipt());
				double yhTotal = 0.0;
				for (CShop cShop:  shopList){
					if(StringUtils.isNotBlank(cShop.getYhje()))yhTotal += Double.parseDouble(cShop.getYhje());
//					if(Double.parseDouble(receipt.getHtje())>Double.parseDouble(cShop.getJe())){
//						yhTotal += Double.parseDouble(cShop.getYhje());
//					}
//					if(Double.parseDouble(receipt.getHtje())<Double.parseDouble(cShop.getJe())){
//						yhTotal=0.0;
//					}
				}
				FDiscount discount = new FDiscount();
				discount.setDdid(cRkckddinfo);
				discount.setStoreid(cs.getStore());
				discount.setYhje(String.valueOf(yhTotal));
				//		discount.setLx();
				fDiscountService.save(discount);
			}


		}
		return retStr;
	}



	/**		采购申请结束		*/
	/**		出库开始		**/
	@RequiresPermissions("ck:cCkinfo:view")
	@RequestMapping(value = "libraryList")
	public String libraryList(CRkckddinfo cRkckddinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		cRkckddinfo.setLx("1");//出库
		if(StringUtils.isNotBlank(cRkckddinfo.getState())) {
			UserUtils.removeCache("RKCKSTATE");
			UserUtils.putCache("RKCKSTATE", cRkckddinfo.getState());
			Page<CRkckddinfo> page = cRkckddinfoService.findPage(new Page<CRkckddinfo>(request, response), cRkckddinfo);
			model.addAttribute("cRkckddinfo", cRkckddinfo);
			model.addAttribute("page", page);
			return "modules/ck/cRkckLibraryList";
		}else {
			return "error/400";
		}
	}

	/**
	 * 出库提交订单打开填写财务信息页面（收款）
	 * @param fReceipt
	 * @param model
	 * @return
	 */
	@RequiresPermissions("ck:cCkinfo:view")
	@RequestMapping(value = "submitOrder")
	public String submitOrder(FReceipt fReceipt, Model model){
		CShop cs = new CShop();
		cs.setUserid(UserUtils.getUser().getId());
		cs.setState(UserUtils.getCache("RKCKSTATE").toString());
		List<CShop> csList = cShopService.findList(cs);
		double orderTotal = 0.0;
		double yhTotal = 0.0;
		for (CShop cShop: csList){
			orderTotal += (Double.parseDouble(cShop.getJe())*Integer.parseInt(cShop.getNub()));
			if(StringUtils.isNotBlank(cShop.getYhje()))yhTotal += Double.parseDouble(cShop.getYhje());
		}
		fReceipt.setHtje(String.valueOf(orderTotal));
		model.addAttribute("storeList", cStoreService.findList(new CStore()));
		model.addAttribute("toDiscount", (yhTotal > 0));
		model.addAttribute("fReceipt", fReceipt);
		return "modules/ck/submitOrder";
	}

	/**
	 * 出库提交订单财务信息(收款)保存
	 * @param fReceipt
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("ck:cCkinfo:edit")
	@RequestMapping(value = "submitOrderSave")
	public String submitOrderSave(FReceipt fReceipt, Model model) {
		if (!beanValidator(model, fReceipt)){
			return null;
		}
		fReceipt.setReceiptType(UserUtils.getCache("RKCKSTATE").toString());
		fReceiptService.save(fReceipt);
		return fReceipt.getId();
	}
	/**
	 * 出库提交订单打开填写财务信息页面（付款）
	 * @param payment
	 * @param model
	 * @return
	 */
	@RequiresPermissions("ck:cCkinfo:view")
	@RequestMapping(value = "submitOrderPayment")
	public String submitOrderPayment(FPayment payment, Model model){
		CShop cs = new CShop();
		cs.setUserid(UserUtils.getUser().getId());
		cs.setState(UserUtils.getCache("RKCKSTATE").toString());
		List<CShop> csList = cShopService.findList(cs);
		double orderTotal = 0.0;
		double yhTotal = 0.0;
		for (CShop cShop: csList){
			orderTotal += (Double.parseDouble(cShop.getJe())*Integer.parseInt(cShop.getNub()));
			if(StringUtils.isNotBlank(cShop.getYhje()))yhTotal += Double.parseDouble(cShop.getYhje());
		}
		payment.setJe(String.valueOf(orderTotal));
		model.addAttribute("storeList", cStoreService.findList(new CStore()));
		model.addAttribute("toDiscount", (yhTotal > 0));
		model.addAttribute("payment", payment);
		return "modules/ck/submitOrderPayment";
	}

	/**
	 * 出库提交订单财务信息(收款)保存
	 * @param payment
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("ck:cCkinfo:edit")
	@RequestMapping(value = "submitOrderPaymentSave")
	public String submitOrderPaymentSave(FPayment payment, Model model) {
		if (!beanValidator(model, payment)){
			return null;
		}
		payment.setPaymentType(UserUtils.getCache("RKCKSTATE").toString());
		fPaymentService.save(payment);
		return payment.getId();
	}

	/**		出库结束		**/

}