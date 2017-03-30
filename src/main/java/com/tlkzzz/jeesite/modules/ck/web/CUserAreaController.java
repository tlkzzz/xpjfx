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
import com.tlkzzz.jeesite.modules.ck.entity.CUserArea;
import com.tlkzzz.jeesite.modules.ck.service.CUserAreaService;

/**
 * 人员区域表Controller
 * @author xrc
 * @version 2017-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/ck/cUserArea")
public class CUserAreaController extends BaseController {

	@Autowired
	private CUserAreaService cUserAreaService;
	
	@ModelAttribute
	public CUserArea get(@RequestParam(required=false) String id) {
		CUserArea entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cUserAreaService.get(id);
		}
		if (entity == null){
			entity = new CUserArea();
		}
		return entity;
	}
	
	@RequiresPermissions("ck:cUserArea:view")
	@RequestMapping(value = {"list", ""})
	public String list(CUserArea cUserArea, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CUserArea> page = cUserAreaService.findPage(new Page<CUserArea>(request, response), cUserArea); 
		model.addAttribute("page", page);
		model.addAttribute("cUserArea", cUserArea);
		return "modules/ck/cUserAreaList";
	}

	@RequiresPermissions("ck:cUserArea:view")
	@RequestMapping(value = "form")
	public String form(CUserArea cUserArea, Model model) {
		model.addAttribute("cUserArea", cUserArea);
		return "modules/ck/cUserAreaForm";
	}

	@RequiresPermissions("ck:cUserArea:edit")
	@RequestMapping(value = "save")
	public String save(CUserArea cUserArea, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cUserArea)){
			return form(cUserArea, model);
		}
		cUserAreaService.save(cUserArea);
		addMessage(redirectAttributes, "保存人员区域表成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cUserArea/?repage";
	}
	
	@RequiresPermissions("ck:cUserArea:edit")
	@RequestMapping(value = "delete")
	public String delete(CUserArea cUserArea, RedirectAttributes redirectAttributes) {
		cUserAreaService.delete(cUserArea);
		addMessage(redirectAttributes, "删除人员区域表成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cUserArea/?repage";
	}

}