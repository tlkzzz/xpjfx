/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.cw.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tlkzzz.jeesite.modules.ck.entity.CRkckddinfo;
import com.tlkzzz.jeesite.modules.ck.entity.CStore;
import com.tlkzzz.jeesite.modules.ck.service.CRkckddinfoService;
import com.tlkzzz.jeesite.modules.ck.service.CStoreService;
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
import com.tlkzzz.jeesite.modules.cw.entity.FDiscount;
import com.tlkzzz.jeesite.modules.cw.service.FDiscountService;

import java.util.ArrayList;
import java.util.List;

/**
 * 优惠表Controller
 * @author xlc
 * @version 2017-04-10
 */
@Controller
@RequestMapping(value = "${adminPath}/cw/fDiscount")
public class FDiscountController extends BaseController {

	@Autowired
	private FDiscountService fDiscountService;
	@Autowired
	private CStoreService cStoreService;
	@Autowired
	private CRkckddinfoService cRkckddinfoService;
	
	@ModelAttribute
	public FDiscount get(@RequestParam(required=false) String id) {
		FDiscount entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = fDiscountService.get(id);
		}
		if (entity == null){
			entity = new FDiscount();
		}
		return entity;
	}
	
	@RequiresPermissions("cw:fDiscount:view")
	@RequestMapping(value = {"list", ""})
	public String list(FDiscount fDiscount, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FDiscount> page = fDiscountService.findPage(new Page<FDiscount>(request, response), fDiscount); 
		model.addAttribute("page", page);
		model.addAttribute("fDiscount",fDiscount);
		model.addAttribute("storeList", cStoreService.findList(new CStore()));
		return "modules/cw/fDiscountList";
	}

	@RequiresPermissions("cw:fDiscount:view")
	@RequestMapping(value = "form")
	public String form(FDiscount fDiscount, Model model) {

		model.addAttribute("fDiscount", fDiscount);
	//	model.addAttribute("cStorelist",cStoreService.findList(new CStore()));
	//	model.addAttribute("cRkckddinfolist",cRkckddinfoService.findList(new CRkckddinfo()));
		return "modules/cw/fDiscountForm";
	}

	@RequiresPermissions("cw:fDiscount:edit")
	@RequestMapping(value = "save")
	public String save(FDiscount fDiscount, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fDiscount)){
			return form(fDiscount, model);
		}
		fDiscountService.save(fDiscount);
		addMessage(redirectAttributes, "保存优惠表成功");
		return "redirect:"+Global.getAdminPath()+"/cw/fDiscount/?repage";
	}
	
	@RequiresPermissions("cw:fDiscount:edit")
	@RequestMapping(value = "delete")
	public String delete(FDiscount fDiscount, RedirectAttributes redirectAttributes) {
		fDiscountService.delete(fDiscount);
		addMessage(redirectAttributes, "删除优惠表成功");
		return "redirect:"+Global.getAdminPath()+"/cw/fDiscount/?repage";
	}

	@RequiresPermissions("cw:fDiscountReport:view")
	@RequestMapping(value = "discountReport")
	public String discountReport(FDiscount fDiscount, Model model) {
		model.addAttribute("list", fDiscountService.findList(fDiscount));
		model.addAttribute("fDiscount", fDiscount);
		return "modules/report/fDiscountReportList";
	}

}