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
import com.tlkzzz.jeesite.modules.cw.entity.FIncomeRecord;
import com.tlkzzz.jeesite.modules.cw.service.FIncomeRecordService;

/**
 * 收入记录Controller
 * @author xrc
 * @version 2017-04-05
 */
@Controller
@RequestMapping(value = "${adminPath}/cw/fIncomeRecord")
public class FIncomeRecordController extends BaseController {

	@Autowired
	private FIncomeRecordService fIncomeRecordService;
	
	@ModelAttribute
	public FIncomeRecord get(@RequestParam(required=false) String id) {
		FIncomeRecord entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = fIncomeRecordService.get(id);
		}
		if (entity == null){
			entity = new FIncomeRecord();
		}
		return entity;
	}
	
	@RequiresPermissions("cw:fIncomeRecord:view")
	@RequestMapping(value = {"list", ""})
	public String list(FIncomeRecord fIncomeRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FIncomeRecord> page = fIncomeRecordService.findPage(new Page<FIncomeRecord>(request, response), fIncomeRecord); 
		model.addAttribute("page", page);
		return "modules/cw/fIncomeRecordList";
	}

	@RequiresPermissions("cw:fIncomeRecord:view")
	@RequestMapping(value = "form")
	public String form(FIncomeRecord fIncomeRecord, Model model) {
		model.addAttribute("fIncomeRecord", fIncomeRecord);
		return "modules/cw/fIncomeRecordForm";
	}

	@RequiresPermissions("cw:fIncomeRecord:edit")
	@RequestMapping(value = "save")
	public String save(FIncomeRecord fIncomeRecord, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fIncomeRecord)){
			return form(fIncomeRecord, model);
		}
		fIncomeRecordService.save(fIncomeRecord);
		addMessage(redirectAttributes, "保存收入记录成功");
		return "redirect:"+Global.getAdminPath()+"/cw/fIncomeRecord/?repage";
	}
	
	@RequiresPermissions("cw:fIncomeRecord:edit")
	@RequestMapping(value = "delete")
	public String delete(FIncomeRecord fIncomeRecord, RedirectAttributes redirectAttributes) {
		fIncomeRecordService.delete(fIncomeRecord);
		addMessage(redirectAttributes, "删除收入记录成功");
		return "redirect:"+Global.getAdminPath()+"/cw/fIncomeRecord/?repage";
	}

}