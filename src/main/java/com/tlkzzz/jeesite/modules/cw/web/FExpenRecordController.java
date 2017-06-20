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
import com.tlkzzz.jeesite.modules.cw.entity.FExpenRecord;
import com.tlkzzz.jeesite.modules.cw.service.FExpenRecordService;

/**
 * 支出记录Controller
 * @author xrc
 * @version 2017-04-05
 */
@Controller
@RequestMapping(value = "${adminPath}/cw/fExpenRecord")
public class FExpenRecordController extends BaseController {

	@Autowired
	private FExpenRecordService fExpenRecordService;
	
	@ModelAttribute
	public FExpenRecord get(@RequestParam(required=false) String id) {
		FExpenRecord entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = fExpenRecordService.get(id);
		}
		if (entity == null){
			entity = new FExpenRecord();
		}
		return entity;
	}
	
	@RequiresPermissions("cw:fExpenRecord:view")
	@RequestMapping(value = {"list", ""})
	public String list(FExpenRecord fExpenRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FExpenRecord> page = fExpenRecordService.findPage(new Page<FExpenRecord>(request, response), fExpenRecord); 
		model.addAttribute("page", page);
		model.addAttribute("fExpenRecord",fExpenRecord);
		return "modules/cw/fExpenRecordList";
	}

	@RequiresPermissions("cw:fExpenRecord:view")
	@RequestMapping(value = "form")
	public String form(FExpenRecord fExpenRecord, Model model) {
		model.addAttribute("fExpenRecord", fExpenRecord);
		return "modules/cw/fExpenRecordForm";
	}

	@RequiresPermissions("cw:fExpenRecord:edit")
	@RequestMapping(value = "save")
	public String save(FExpenRecord fExpenRecord, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fExpenRecord)){
			return form(fExpenRecord, model);
		}
		fExpenRecordService.save(fExpenRecord);
		addMessage(redirectAttributes, "保存支出记录成功");
		return "redirect:"+Global.getAdminPath()+"/cw/fExpenRecord/?repage";
	}
	
	@RequiresPermissions("cw:fExpenRecord:edit")
	@RequestMapping(value = "delete")
	public String delete(FExpenRecord fExpenRecord, RedirectAttributes redirectAttributes) {
		fExpenRecordService.delete(fExpenRecord);
		addMessage(redirectAttributes, "删除支出记录成功");
		return "redirect:"+Global.getAdminPath()+"/cw/fExpenRecord/?repage";
	}
}