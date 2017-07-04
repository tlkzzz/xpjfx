/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.web;

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
import com.tlkzzz.jeesite.modules.ck.entity.CJdlddk;
import com.tlkzzz.jeesite.modules.ck.service.CJdlddkService;

/**
 * 进店离店打卡Controller
 * @author szx
 * @version 2017-06-20
 */
@Controller
@RequestMapping(value = "${adminPath}/ck/cJdlddk")
public class CJdlddkController extends BaseController {

	@Autowired
	private CJdlddkService cJdlddkService;
	
	@ModelAttribute
	public CJdlddk get(@RequestParam(required=false) String id) {
		CJdlddk entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cJdlddkService.get(id);
		}
		if (entity == null){
			entity = new CJdlddk();
		}
		return entity;
	}
	
	@RequiresPermissions("ck:cJdlddk:view")
	@RequestMapping(value = {"list", ""})
	public String list(CJdlddk cJdlddk, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CJdlddk> page = cJdlddkService.findPage(new Page<CJdlddk>(request, response), cJdlddk); 
		model.addAttribute("page", page);
		return "modules/ck/cJdlddkList";
	}

	@RequiresPermissions("ck:cJdlddk:view")
	@RequestMapping(value = "form")
	public String form(CJdlddk cJdlddk, Model model) {
		model.addAttribute("cJdlddk", cJdlddk);
		return "modules/ck/cJdlddkForm";
	}

	@RequiresPermissions("ck:cJdlddk:edit")
	@RequestMapping(value = "save")
	public String save(CJdlddk cJdlddk, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cJdlddk)){
			return form(cJdlddk, model);
		}
		cJdlddkService.save(cJdlddk);
		addMessage(redirectAttributes, "保存进店离店打卡成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cJdlddk/?repage";
	}
	
	@RequiresPermissions("ck:cJdlddk:edit")
	@RequestMapping(value = "delete")
	public String delete(CJdlddk cJdlddk, RedirectAttributes redirectAttributes) {
		cJdlddkService.delete(cJdlddk);
		addMessage(redirectAttributes, "删除进店离店打卡成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cJdlddk/?repage";
	}

}