/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.cw.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tlkzzz.jeesite.modules.ck.entity.CDdinfo;
import com.tlkzzz.jeesite.modules.ck.entity.CRkckddinfo;
import com.tlkzzz.jeesite.modules.ck.service.CDdinfoService;
import com.tlkzzz.jeesite.modules.ck.service.CRkckddinfoService;
import com.tlkzzz.jeesite.modules.ck.service.CStoreService;
import com.tlkzzz.jeesite.modules.sys.utils.NumberToCN;
import com.tlkzzz.jeesite.modules.sys.utils.UserUtils;
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
import com.tlkzzz.jeesite.modules.cw.entity.FArrears;
import com.tlkzzz.jeesite.modules.cw.service.FArrearsService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 欠款记录Controller
 * @author xrc
 * @version 2017-04-05
 */
@Controller
@RequestMapping(value = "${adminPath}/cw/fArrears")
public class FArrearsController extends BaseController {

	@Autowired
	private FArrearsService fArrearsService;
	@Autowired
	private CRkckddinfoService cRkckddinfoService;
	@Autowired
	private CDdinfoService cDdinfoService;
	@Autowired
	private CStoreService cStoreService;
	
	@ModelAttribute
	public FArrears get(@RequestParam(required=false) String id) {
		FArrears entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = fArrearsService.get(id);
		}
		if (entity == null){
			entity = new FArrears();
		}
		return entity;
	}

	/**
	 * 客户欠款列表
	 */
	@RequiresPermissions("cw:fArrears:view")
	@RequestMapping(value = "storeList")
	public String storeList(FArrears fArrears, HttpServletRequest request, HttpServletResponse response, Model model) {
		fArrears.setArrearsType("0");
		Page<FArrears> page = fArrearsService.findPage(new Page<FArrears>(request, response), fArrears);
		Double Sum=0.00;
		if(page.getList().size()>0){
			List<FArrears> fArrearsList=page.getList();
			for(int i=0;i<fArrearsList.size();i++) {
				if (StringUtils.isNotBlank(fArrearsList.get(i).getTotal())) {
					Double je = Double.parseDouble(fArrearsList.get(i).getTotal());
					Sum += je;
				}
			}
		}
		model.addAttribute("page", page);
		model.addAttribute("fArrears",fArrears);
		model.addAttribute("Sum", Sum);
		return "modules/cw/fArrearsList";
	}

	/**
	 * 客户欠款打印
	 */
	@RequiresPermissions("cw:fArrears:view")
	@RequestMapping(value = "storeArrearsPrint")
	public String storeArrearsPrint(FArrears fArrears, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(fArrears==null||fArrears.getRkckdd()==null) {//参数为空
			return "error/400";
		}
		CRkckddinfo cRkckddinfo = cRkckddinfoService.get(fArrears.getRkckdd());
		if(cRkckddinfo==null||StringUtils.isBlank(fArrears.getRkckdd().getId())){//查询不到总订单
			return "error/400";
		}
		CDdinfo cDdinfo = new CDdinfo();
		cDdinfo.setRkckddinfo(cRkckddinfo);
		List<CDdinfo> list = cDdinfoService.findList(cDdinfo);
		double sumMoney = 0;
		int sumNub =0;
		for(CDdinfo cd: list){
			int nub = Integer.parseInt(cd.getNub());
			sumMoney += Double.parseDouble(cd.getJe())*nub;
			sumNub += nub;
		}
		cDdinfoService.processUnit(list);
		BigDecimal numberOfMoney = new BigDecimal(sumMoney);
		model.addAttribute("CNMoney", NumberToCN.number2CNMontrayUnit(numberOfMoney));
		model.addAttribute("store", cStoreService.get(cRkckddinfo.getStore()));
		model.addAttribute("user", UserUtils.getUser());
		model.addAttribute("cRkckddinfo", cRkckddinfo);
		model.addAttribute("sumMoney", sumMoney);
		model.addAttribute("sumNub", sumNub);
		model.addAttribute("date", new Date());
		model.addAttribute("list", list);
		model.addAttribute("fArrears", fArrears);
		return "modules/cw/FArrearsStorePrint";
	}

	/**
	 *	欠供应商列表
	 */
	@RequiresPermissions("cw:fArrears:view")
	@RequestMapping(value = "supplierList")
	public String supplierList(FArrears fArrears, HttpServletRequest request, HttpServletResponse response, Model model) {
		fArrears.setArrearsType("1");
		Page<FArrears> page = fArrearsService.findSupplierPage(new Page<FArrears>(request, response), fArrears);
		Double Sum=0.00;
		if(page.getList().size()>0){
			List<FArrears> fArrearsList=page.getList();
			for(int i=0;i<fArrearsList.size();i++) {
				if (StringUtils.isNotBlank(fArrearsList.get(i).getTotal())) {
					Double je = Double.parseDouble(fArrearsList.get(i).getTotal());
					Sum += je;
				}
			}
		}
		model.addAttribute("page", page);
		model.addAttribute("fArrears",fArrears);
		model.addAttribute("Sum", Sum);
		return "modules/cw/fArrearsSupplierList";
	}

	@RequiresPermissions("cw:fArrears:view")
	@RequestMapping(value = "form")
	public String form(FArrears fArrears, Model model) {
		model.addAttribute("fArrears", fArrears);
		return "modules/cw/fArrearsForm";
	}

	@RequiresPermissions("cw:fArrears:edit")
	@RequestMapping(value = "save")
	public String save(FArrears fArrears, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fArrears)){
			return form(fArrears, model);
		}
		fArrearsService.save(fArrears);
		addMessage(redirectAttributes, "保存欠款记录成功");
		return "redirect:"+Global.getAdminPath()+"/cw/fArrears/?repage";
	}
	
	@RequiresPermissions("cw:fArrears:edit")
	@RequestMapping(value = "delete")
	public String delete(FArrears fArrears, RedirectAttributes redirectAttributes) {
		fArrearsService.delete(fArrears);
		addMessage(redirectAttributes, "删除欠款记录成功");
		return "redirect:"+Global.getAdminPath()+"/cw/fArrears/?repage";
	}

	/**
	 * 欠客户的
	 * @param fArrears
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("cw:fArrears:view")
	@RequestMapping(value = "cgDdList")
	public String cgDdList(FArrears fArrears, HttpServletRequest request, HttpServletResponse response, Model model) {
		fArrears.setArrearsType("0");
		Page<FArrears> page = fArrearsService.findStorePage(new Page<FArrears>(request, response), fArrears);
		model.addAttribute("fArrears", fArrears);
		model.addAttribute("page", page);
		return "modules/cw/storedList";
	}
	/**
	 * 欠供应商的
	 * @param fArrears
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("cw:fArrears:view")
	@RequestMapping(value = "gyList")
	public String gyList(FArrears fArrears, HttpServletRequest request, HttpServletResponse response, Model model) {
		fArrears.setArrearsType("1");
		Page<FArrears> page = fArrearsService.finPage(new Page<FArrears>(request, response), fArrears);
		model.addAttribute("fArrears", fArrears);
		model.addAttribute("page", page);
		return "modules/cw/gysList";
	}
}