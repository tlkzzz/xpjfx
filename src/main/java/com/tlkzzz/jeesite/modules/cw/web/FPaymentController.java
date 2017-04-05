/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.cw.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.tlkzzz.jeesite.modules.cw.entity.FPayment;
import com.tlkzzz.jeesite.modules.cw.service.FPaymentService;

/**
 * 付款Controller
 * @author xrc
 * @version 2017-04-05
 */
@Controller
@RequestMapping(value = "${adminPath}/cw/fPayment")
public class FPaymentController extends BaseController {

	@Autowired
	private FPaymentService fPaymentService;
	
	@ModelAttribute
	public FPayment get(@RequestParam(required=false) String id) {
		FPayment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = fPaymentService.get(id);
		}
		if (entity == null){
			entity = new FPayment();
		}
		return entity;
	}
	
	@RequiresPermissions("cw:fPayment:view")
	@RequestMapping(value = {"list", ""})
	public String list(FPayment fPayment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FPayment> page = fPaymentService.findPage(new Page<FPayment>(request, response), fPayment); 
		model.addAttribute("page", page);
		return "modules/cw/fPaymentList";
	}

	@RequiresPermissions("cw:fPayment:view")
	@RequestMapping(value = "form")
	public String form(FPayment fPayment, Model model) {
		model.addAttribute("fPayment", fPayment);
		return "modules/cw/fPaymentForm";
	}

	@RequiresPermissions("cw:fPayment:edit")
	@RequestMapping(value = "save")
	public String save(FPayment fPayment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fPayment)){
			return form(fPayment, model);
		}
		fPaymentService.save(fPayment);
		addMessage(redirectAttributes, "保存付款成功");
		return "redirect:"+Global.getAdminPath()+"/cw/fPayment/?repage";
	}
	
	@RequiresPermissions("cw:fPayment:edit")
	@RequestMapping(value = "delete")
	public String delete(FPayment fPayment, RedirectAttributes redirectAttributes) {
		fPaymentService.delete(fPayment);
		addMessage(redirectAttributes, "删除付款成功");
		return "redirect:"+Global.getAdminPath()+"/cw/fPayment/?repage";
	}

}