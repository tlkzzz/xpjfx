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
import com.tlkzzz.jeesite.modules.cw.entity.FFixedAssetsCgbm;
import com.tlkzzz.jeesite.modules.cw.service.FFixedAssetsCgbmService;

/**
 * 固定资产采购变卖Controller
 * @author xrc
 * @version 2017-04-05
 */
@Controller
@RequestMapping(value = "${adminPath}/cw/fFixedAssetsCgbm")
public class FFixedAssetsCgbmController extends BaseController {

	@Autowired
	private FFixedAssetsCgbmService fFixedAssetsCgbmService;
	
	@ModelAttribute
	public FFixedAssetsCgbm get(@RequestParam(required=false) String id) {
		FFixedAssetsCgbm entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = fFixedAssetsCgbmService.get(id);
		}
		if (entity == null){
			entity = new FFixedAssetsCgbm();
		}
		return entity;
	}
	
	@RequiresPermissions("cw:fFixedAssetsCgbm:view")
	@RequestMapping(value = {"list", ""})
	public String list(FFixedAssetsCgbm fFixedAssetsCgbm, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FFixedAssetsCgbm> page = fFixedAssetsCgbmService.findPage(new Page<FFixedAssetsCgbm>(request, response), fFixedAssetsCgbm); 
		model.addAttribute("page", page);
		return "modules/cw/fFixedAssetsCgbmList";
	}

	@RequiresPermissions("cw:fFixedAssetsCgbm:view")
	@RequestMapping(value = "form")
	public String form(FFixedAssetsCgbm fFixedAssetsCgbm, Model model) {
		model.addAttribute("fFixedAssetsCgbm", fFixedAssetsCgbm);
		return "modules/cw/fFixedAssetsCgbmForm";
	}

	@RequiresPermissions("cw:fFixedAssetsCgbm:edit")
	@RequestMapping(value = "save")
	public String save(FFixedAssetsCgbm fFixedAssetsCgbm, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fFixedAssetsCgbm)){
			return form(fFixedAssetsCgbm, model);
		}
		fFixedAssetsCgbmService.save(fFixedAssetsCgbm);
		addMessage(redirectAttributes, "保存固定资产采购变卖成功");
		return "redirect:"+Global.getAdminPath()+"/cw/fFixedAssetsCgbm/?repage";
	}
	
	@RequiresPermissions("cw:fFixedAssetsCgbm:edit")
	@RequestMapping(value = "delete")
	public String delete(FFixedAssetsCgbm fFixedAssetsCgbm, RedirectAttributes redirectAttributes) {
		fFixedAssetsCgbmService.delete(fFixedAssetsCgbm);
		addMessage(redirectAttributes, "删除固定资产采购变卖成功");
		return "redirect:"+Global.getAdminPath()+"/cw/fFixedAssetsCgbm/?repage";
	}

}