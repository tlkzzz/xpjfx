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
import com.tlkzzz.jeesite.modules.ck.entity.CSclass;
import com.tlkzzz.jeesite.modules.ck.service.CSclassService;

/**
 * 客户分类Controller
 * @author xrc
 * @version 2017-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/ck/cSclass")
public class CSclassController extends BaseController {

	@Autowired
	private CSclassService cSclassService;
	
	@ModelAttribute
	public CSclass get(@RequestParam(required=false) String id) {
		CSclass entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cSclassService.get(id);
		}
		if (entity == null){
			entity = new CSclass();
		}
		return entity;
	}
	
	@RequiresPermissions("ck:cSclass:view")
	@RequestMapping(value = {"list", ""})
	public String list(CSclass cSclass, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CSclass> page = cSclassService.findPage(new Page<CSclass>(request, response), cSclass); 
		model.addAttribute("page", page);
		model.addAttribute("cSclass", cSclass);
		return "modules/ck/cSclassList";
	}

	@RequiresPermissions("ck:cSclass:view")
	@RequestMapping(value = "form")
	public String form(CSclass cSclass, Model model) {
		model.addAttribute("cSclass", cSclass);
		return "modules/ck/cSclassForm";
	}

	@RequiresPermissions("ck:cSclass:edit")
	@RequestMapping(value = "save")
	public String save(CSclass cSclass, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cSclass)){
			return form(cSclass, model);
		}
		cSclassService.save(cSclass);
		addMessage(redirectAttributes, "保存客户分类表成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cSclass/?repage";
	}
	
	@RequiresPermissions("ck:cSclass:edit")
	@RequestMapping(value = "delete")
	public String delete(CSclass cSclass, RedirectAttributes redirectAttributes) {
		cSclassService.delete(cSclass);
		addMessage(redirectAttributes, "删除客户分类表成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cSclass/?repage";
	}

}