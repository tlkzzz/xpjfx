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
import com.tlkzzz.jeesite.modules.ck.entity.CApprole;
import com.tlkzzz.jeesite.modules.ck.service.CApproleService;

/**
 * app权限设置Controller
 * @author szx
 * @version 2017-06-29
 */
@Controller
@RequestMapping(value = "${adminPath}/ck/cApprole")
public class CApproleController extends BaseController {

	@Autowired
	private CApproleService cApproleService;
	
	@ModelAttribute
	public CApprole get(@RequestParam(required=false) String id) {
		CApprole entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cApproleService.get(id);
		}
		if (entity == null){
			entity = new CApprole();
		}
		return entity;
	}
	
	@RequiresPermissions("ck:cApprole:view")
	@RequestMapping(value = {"list", ""})
	public String list(CApprole cApprole, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CApprole> page = cApproleService.findPage(new Page<CApprole>(request, response), cApprole); 
		model.addAttribute("page", page);
		return "modules/ck/cApproleList";
	}

	@RequiresPermissions("ck:cApprole:view")
	@RequestMapping(value = "form")
	public String form(CApprole cApprole, Model model) {
		model.addAttribute("cApprole", cApprole);
		return "modules/ck/cApproleForm";
	}

	@RequiresPermissions("ck:cApprole:edit")
	@RequestMapping(value = "save")
	public String save(CApprole cApprole, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cApprole)){
			return form(cApprole, model);
		}
		cApproleService.save(cApprole);
		addMessage(redirectAttributes, "保存szx成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cApprole/?repage";
	}
	
	@RequiresPermissions("ck:cApprole:edit")
	@RequestMapping(value = "delete")
	public String delete(CApprole cApprole, RedirectAttributes redirectAttributes) {
		cApproleService.delete(cApprole);
		addMessage(redirectAttributes, "删除szx成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cApprole/?repage";
	}

}