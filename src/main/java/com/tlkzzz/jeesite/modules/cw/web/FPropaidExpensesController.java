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
import com.tlkzzz.jeesite.modules.cw.entity.FPropaidExpenses;
import com.tlkzzz.jeesite.modules.cw.service.FPropaidExpensesService;

/**
 * 待摊费用Controller
 * @author xrc
 * @version 2017-04-05
 */
@Controller
@RequestMapping(value = "${adminPath}/cw/fPropaidExpenses")
public class FPropaidExpensesController extends BaseController {

	@Autowired
	private FPropaidExpensesService fPropaidExpensesService;
	
	@ModelAttribute
	public FPropaidExpenses get(@RequestParam(required=false) String id) {
		FPropaidExpenses entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = fPropaidExpensesService.get(id);
		}
		if (entity == null){
			entity = new FPropaidExpenses();
		}
		return entity;
	}
	
	@RequiresPermissions("cw:fPropaidExpenses:view")
	@RequestMapping(value = {"list", ""})
	public String list(FPropaidExpenses fPropaidExpenses, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FPropaidExpenses> page = fPropaidExpensesService.findPage(new Page<FPropaidExpenses>(request, response), fPropaidExpenses); 
		model.addAttribute("page", page);
		return "modules/cw/fPropaidExpensesList";
	}

	@RequiresPermissions("cw:fPropaidExpenses:view")
	@RequestMapping(value = "form")
	public String form(FPropaidExpenses fPropaidExpenses, Model model) {
		model.addAttribute("fPropaidExpenses", fPropaidExpenses);
		return "modules/cw/fPropaidExpensesForm";
	}

	@RequiresPermissions("cw:fPropaidExpenses:edit")
	@RequestMapping(value = "save")
	public String save(FPropaidExpenses fPropaidExpenses, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fPropaidExpenses)){
			return form(fPropaidExpenses, model);
		}
		fPropaidExpensesService.save(fPropaidExpenses);
		addMessage(redirectAttributes, "保存待摊费用成功");
		return "redirect:"+Global.getAdminPath()+"/cw/fPropaidExpenses/?repage";
	}
	
	@RequiresPermissions("cw:fPropaidExpenses:edit")
	@RequestMapping(value = "delete")
	public String delete(FPropaidExpenses fPropaidExpenses, RedirectAttributes redirectAttributes) {
		fPropaidExpensesService.delete(fPropaidExpenses);
		addMessage(redirectAttributes, "删除待摊费用成功");
		return "redirect:"+Global.getAdminPath()+"/cw/fPropaidExpenses/?repage";
	}

}