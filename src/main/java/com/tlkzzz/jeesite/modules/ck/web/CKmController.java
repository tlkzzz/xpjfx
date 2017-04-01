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
import com.tlkzzz.jeesite.modules.ck.entity.CKm;
import com.tlkzzz.jeesite.modules.ck.service.CKmService;

/**
 * 科目类别表Controller
 * @author szx
 * @version 2017-03-31
 */
@Controller
@RequestMapping(value = "${adminPath}/ck/cKm")
public class CKmController extends BaseController {

	@Autowired
	private CKmService cKmService;
	
	@ModelAttribute
	public CKm get(@RequestParam(required=false) String id) {
		CKm entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cKmService.get(id);
		}
		if (entity == null){
			entity = new CKm();
		}
		return entity;
	}
	
	@RequiresPermissions("ck:cKm:view")
	@RequestMapping(value = {"list", ""})
	public String list(CKm cKm, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CKm> page = cKmService.findPage(new Page<CKm>(request, response), cKm); 
		model.addAttribute("page", page);
		model.addAttribute("cKm", cKm);
		return "modules/ck/cKmList";
	}

	@RequiresPermissions("ck:cKm:view")
	@RequestMapping(value = "form")
	public String form(CKm cKm, Model model) {
		model.addAttribute("cKm", cKm);
		return "modules/ck/cKmForm";
	}

	@RequiresPermissions("ck:cKm:edit")
	@RequestMapping(value = "save")
	public String save(CKm cKm, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cKm)){
			return form(cKm, model);
		}
		cKmService.save(cKm);
		addMessage(redirectAttributes, "保存科目类别表成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cKm/?repage";
	}
	
	@RequiresPermissions("ck:cKm:edit")
	@RequestMapping(value = "delete")
	public String delete(CKm cKm, RedirectAttributes redirectAttributes) {
		cKmService.delete(cKm);
		addMessage(redirectAttributes, "删除科目类别表成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cKm/?repage";
	}

}