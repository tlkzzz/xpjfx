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
import com.tlkzzz.jeesite.modules.cw.entity.FArrears;
import com.tlkzzz.jeesite.modules.cw.service.FArrearsService;

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
	
	@RequiresPermissions("cw:fArrears:view")
	@RequestMapping(value = {"list", ""})
	public String list(FArrears fArrears, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FArrears> page = fArrearsService.findPage(new Page<FArrears>(request, response), fArrears); 
		model.addAttribute("page", page);
		model.addAttribute("fArrears",fArrears);
		return "modules/cw/fArrearsList";
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

}