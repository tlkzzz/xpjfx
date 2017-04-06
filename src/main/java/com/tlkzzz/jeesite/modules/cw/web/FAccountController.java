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
import com.tlkzzz.jeesite.modules.cw.entity.FAccount;
import com.tlkzzz.jeesite.modules.cw.service.FAccountService;

/**
 * 账户管理Controller
 * @author xrc
 * @version 2017-04-05
 */
@Controller
@RequestMapping(value = "${adminPath}/cw/fAccount")
public class FAccountController extends BaseController {

	@Autowired
	private FAccountService fAccountService;
	
	@ModelAttribute
	public FAccount get(@RequestParam(required=false) String id) {
		FAccount entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = fAccountService.get(id);
		}
		if (entity == null){
			entity = new FAccount();
		}
		return entity;
	}
	
	@RequiresPermissions("cw:fAccount:view")
	@RequestMapping(value = {"list", ""})
	public String list(FAccount fAccount, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FAccount> page = fAccountService.findPage(new Page<FAccount>(request, response), fAccount); 
		model.addAttribute("page", page);
		model.addAttribute("fAccount", fAccount);
		return "modules/cw/fAccountList";
	}

	@RequiresPermissions("cw:fAccount:view")
	@RequestMapping(value = "form")
	public String form(FAccount fAccount, Model model) {
		model.addAttribute("fAccount", fAccount);
		return "modules/cw/fAccountForm";
	}

	@RequiresPermissions("cw:fAccount:edit")
	@RequestMapping(value = "save")
	public String save(FAccount fAccount, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fAccount)){
			return form(fAccount, model);
		}
		fAccountService.save(fAccount);
		addMessage(redirectAttributes, "保存账户管理成功");
		return "redirect:"+Global.getAdminPath()+"/cw/fAccount/?repage";
	}
	
	@RequiresPermissions("cw:fAccount:edit")
	@RequestMapping(value = "delete")
	public String delete(FAccount fAccount, RedirectAttributes redirectAttributes) {
		fAccountService.delete(fAccount);
		addMessage(redirectAttributes, "删除账户管理成功");
		return "redirect:"+Global.getAdminPath()+"/cw/fAccount/?repage";
	}

}