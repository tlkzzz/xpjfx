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
import com.tlkzzz.jeesite.modules.cw.entity.FTransferAccount;
import com.tlkzzz.jeesite.modules.cw.service.FTransferAccountService;

/**
 * 转账调账Controller
 * @author xrc
 * @version 2017-04-05
 */
@Controller
@RequestMapping(value = "${adminPath}/cw/fTransferAccount")
public class FTransferAccountController extends BaseController {

	@Autowired
	private FTransferAccountService fTransferAccountService;
	
	@ModelAttribute
	public FTransferAccount get(@RequestParam(required=false) String id) {
		FTransferAccount entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = fTransferAccountService.get(id);
		}
		if (entity == null){
			entity = new FTransferAccount();
		}
		return entity;
	}
	
	@RequiresPermissions("cw:fTransferAccount:view")
	@RequestMapping(value = {"list", ""})
	public String list(FTransferAccount fTransferAccount, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FTransferAccount> page = fTransferAccountService.findPage(new Page<FTransferAccount>(request, response), fTransferAccount); 
		model.addAttribute("page", page);
		return "modules/cw/fTransferAccountList";
	}

	@RequiresPermissions("cw:fTransferAccount:view")
	@RequestMapping(value = "form")
	public String form(FTransferAccount fTransferAccount, Model model) {
		model.addAttribute("fTransferAccount", fTransferAccount);
		return "modules/cw/fTransferAccountForm";
	}

	@RequiresPermissions("cw:fTransferAccount:edit")
	@RequestMapping(value = "save")
	public String save(FTransferAccount fTransferAccount, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fTransferAccount)){
			return form(fTransferAccount, model);
		}
		fTransferAccountService.save(fTransferAccount);
		addMessage(redirectAttributes, "保存转账调账成功");
		return "redirect:"+Global.getAdminPath()+"/cw/fTransferAccount/?repage";
	}
	
	@RequiresPermissions("cw:fTransferAccount:edit")
	@RequestMapping(value = "delete")
	public String delete(FTransferAccount fTransferAccount, RedirectAttributes redirectAttributes) {
		fTransferAccountService.delete(fTransferAccount);
		addMessage(redirectAttributes, "删除转账调账成功");
		return "redirect:"+Global.getAdminPath()+"/cw/fTransferAccount/?repage";
	}

}