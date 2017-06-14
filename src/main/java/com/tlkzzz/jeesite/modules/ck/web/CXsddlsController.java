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
import com.tlkzzz.jeesite.modules.ck.entity.CXsddls;
import com.tlkzzz.jeesite.modules.ck.service.CXsddlsService;

/**
 * 销售订单临时表Controller
 * @author szx
 * @version 2017-05-16
 */
@Controller
@RequestMapping(value = "${adminPath}/ck/cXsddls")
public class CXsddlsController extends BaseController {

	@Autowired
	private CXsddlsService cXsddlsService;
	
	@ModelAttribute
	public CXsddls get(@RequestParam(required=false) String id) {
		CXsddls entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cXsddlsService.get(id);
		}
		if (entity == null){
			entity = new CXsddls();
		}
		return entity;
	}
	
	@RequiresPermissions("ck:cXsddls:view")
	@RequestMapping(value = {"list", ""})
	public String list(CXsddls cXsddls, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CXsddls> page = cXsddlsService.findPage(new Page<CXsddls>(request, response), cXsddls); 
		model.addAttribute("page", page);
		return "modules/ck/cXsddlsList";
	}

	@RequiresPermissions("ck:cXsddls:view")
	@RequestMapping(value = "form")
	public String form(CXsddls cXsddls, Model model) {
		model.addAttribute("cXsddls", cXsddls);
		return "modules/ck/cXsddlsForm";
	}

	@RequiresPermissions("ck:cXsddls:edit")
	@RequestMapping(value = "save")
	public String save(CXsddls cXsddls, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cXsddls)){
			return form(cXsddls, model);
		}
		cXsddlsService.save(cXsddls);
		addMessage(redirectAttributes, "保存销售订单临时表成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cXsddls/?repage";
	}
	
	@RequiresPermissions("ck:cXsddls:edit")
	@RequestMapping(value = "delete")
	public String delete(CXsddls cXsddls, RedirectAttributes redirectAttributes) {
		cXsddlsService.delete(cXsddls);
		addMessage(redirectAttributes, "删除销售订单临时表成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cXsddls/?repage";
	}

}