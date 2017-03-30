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
import com.tlkzzz.jeesite.modules.ck.entity.CCarUser;
import com.tlkzzz.jeesite.modules.ck.service.CCarUserService;

/**
 * 车辆人员表生成Controller
 * @author xrc
 * @version 2017-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/ck/cCarUser")
public class CCarUserController extends BaseController {

	@Autowired
	private CCarUserService cCarUserService;
	
	@ModelAttribute
	public CCarUser get(@RequestParam(required=false) String id) {
		CCarUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cCarUserService.get(id);
		}
		if (entity == null){
			entity = new CCarUser();
		}
		return entity;
	}
	
	@RequiresPermissions("ck:cCarUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(CCarUser cCarUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CCarUser> page = cCarUserService.findPage(new Page<CCarUser>(request, response), cCarUser); 
		model.addAttribute("page", page);
		model.addAttribute("cCarUser", cCarUser);
		return "modules/ck/cCarUserList";
	}

	@RequiresPermissions("ck:cCarUser:view")
	@RequestMapping(value = "form")
	public String form(CCarUser cCarUser, Model model) {
		model.addAttribute("cCarUser", cCarUser);
		return "modules/ck/cCarUserForm";
	}

	@RequiresPermissions("ck:cCarUser:edit")
	@RequestMapping(value = "save")
	public String save(CCarUser cCarUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cCarUser)){
			return form(cCarUser, model);
		}
		cCarUserService.save(cCarUser);
		addMessage(redirectAttributes, "保存车辆人员表成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cCarUser/?repage";
	}
	
	@RequiresPermissions("ck:cCarUser:edit")
	@RequestMapping(value = "delete")
	public String delete(CCarUser cCarUser, RedirectAttributes redirectAttributes) {
		cCarUserService.delete(cCarUser);
		addMessage(redirectAttributes, "删除车辆人员表成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cCarUser/?repage";
	}

}