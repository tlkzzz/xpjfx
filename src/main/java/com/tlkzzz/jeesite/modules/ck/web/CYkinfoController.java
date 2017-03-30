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
import com.tlkzzz.jeesite.modules.ck.entity.CYkinfo;
import com.tlkzzz.jeesite.modules.ck.service.CYkinfoService;

/**
 * 移库记录Controller
 * @author xrc
 * @version 2017-03-15
 */
@Controller
@RequestMapping(value = "${adminPath}/ck/cYkinfo")
public class CYkinfoController extends BaseController {

	@Autowired
	private CYkinfoService cYkinfoService;
	
	@ModelAttribute
	public CYkinfo get(@RequestParam(required=false) String id) {
		CYkinfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cYkinfoService.get(id);
		}
		if (entity == null){
			entity = new CYkinfo();
		}
		return entity;
	}
	
	@RequiresPermissions("ck:cYkinfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(CYkinfo cYkinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CYkinfo> page = cYkinfoService.findPage(new Page<CYkinfo>(request, response), cYkinfo); 
		model.addAttribute("cYkinfo", cYkinfo);
		model.addAttribute("page", page);
		return "modules/ck/cYkinfoList";
	}

	@RequiresPermissions("ck:cYkinfo:view")
	@RequestMapping(value = "form")
	public String form(CYkinfo cYkinfo, Model model) {
		model.addAttribute("cYkinfo", cYkinfo);
		return "modules/ck/cYkinfoForm";
	}

	@RequiresPermissions("ck:cYkinfo:edit")
	@RequestMapping(value = "save")
	public String save(CYkinfo cYkinfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cYkinfo)){
			return form(cYkinfo, model);
		}
//		cYkinfoService.save(cYkinfo);
		addMessage(redirectAttributes, "保存移库记录成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cYkinfo/?repage";
	}
	
	@RequiresPermissions("ck:cYkinfo:edit")
	@RequestMapping(value = "delete")
	public String delete(CYkinfo cYkinfo, RedirectAttributes redirectAttributes) {
//		cYkinfoService.delete(cYkinfo);
		addMessage(redirectAttributes, "删除移库记录成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cYkinfo/?repage";
	}

}