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
import com.tlkzzz.jeesite.modules.ck.entity.CRkinfo;
import com.tlkzzz.jeesite.modules.ck.service.CRkinfoService;

/**
 * 入库记录Controller
 * @author xrc
 * @version 2017-03-15
 */
@Controller
@RequestMapping(value = "${adminPath}/ck/cRkinfo")
public class CRkinfoController extends BaseController {

	@Autowired
	private CRkinfoService cRkinfoService;
	
	@ModelAttribute
	public CRkinfo get(@RequestParam(required=false) String id) {
		CRkinfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cRkinfoService.get(id);
		}
		if (entity == null){
			entity = new CRkinfo();
		}
		return entity;
	}
	
	@RequiresPermissions("ck:cRkinfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(CRkinfo cRkinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CRkinfo> page = cRkinfoService.findPage(new Page<CRkinfo>(request, response), cRkinfo); 
		model.addAttribute("cRkinfo", cRkinfo);
		model.addAttribute("page", page);
		return "modules/ck/cRkinfoList";
	}

	@RequiresPermissions("ck:cRkinfo:view")
	@RequestMapping(value = "form")
	public String form(CRkinfo cRkinfo, Model model) {
		model.addAttribute("cRkinfo", cRkinfo);
		return "modules/ck/cRkinfoForm";
	}

	@RequiresPermissions("ck:cRkinfo:edit")
	@RequestMapping(value = "save")
	public String save(CRkinfo cRkinfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cRkinfo)){
			return form(cRkinfo, model);
		}
//		cRkinfoService.save(cRkinfo);
		addMessage(redirectAttributes, "保存入库记录成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cRkinfo/?repage";
	}
	
	@RequiresPermissions("ck:cRkinfo:edit")
	@RequestMapping(value = "delete")
	public String delete(CRkinfo cRkinfo, RedirectAttributes redirectAttributes) {
//		cRkinfoService.delete(cRkinfo);
		addMessage(redirectAttributes, "删除入库记录成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cRkinfo/?repage";
	}

	/** 	报表	start	**/
	@RequiresPermissions("ck:cRkinfoInquire:view")
	@RequestMapping(value = "rkInquire")
	public String rkInquire(CRkinfo cRkinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CRkinfo> page = cRkinfoService.findPage(new Page<CRkinfo>(request, response), cRkinfo);
		model.addAttribute("cRkinfo", cRkinfo);
		model.addAttribute("page", page);
		return "modules/report/cRkinfoInquireList";
	}

}