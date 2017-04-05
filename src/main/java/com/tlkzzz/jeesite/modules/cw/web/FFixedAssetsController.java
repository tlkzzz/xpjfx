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
import com.tlkzzz.jeesite.modules.cw.entity.FFixedAssets;
import com.tlkzzz.jeesite.modules.cw.service.FFixedAssetsService;

/**
 * 固定资产登记Controller
 * @author xrc
 * @version 2017-04-05
 */
@Controller
@RequestMapping(value = "${adminPath}/cw/fFixedAssets")
public class FFixedAssetsController extends BaseController {

	@Autowired
	private FFixedAssetsService fFixedAssetsService;
	
	@ModelAttribute
	public FFixedAssets get(@RequestParam(required=false) String id) {
		FFixedAssets entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = fFixedAssetsService.get(id);
		}
		if (entity == null){
			entity = new FFixedAssets();
		}
		return entity;
	}
	
	@RequiresPermissions("cw:fFixedAssets:view")
	@RequestMapping(value = {"list", ""})
	public String list(FFixedAssets fFixedAssets, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FFixedAssets> page = fFixedAssetsService.findPage(new Page<FFixedAssets>(request, response), fFixedAssets); 
		model.addAttribute("page", page);
		return "modules/cw/fFixedAssetsList";
	}

	@RequiresPermissions("cw:fFixedAssets:view")
	@RequestMapping(value = "form")
	public String form(FFixedAssets fFixedAssets, Model model) {
		model.addAttribute("fFixedAssets", fFixedAssets);
		return "modules/cw/fFixedAssetsForm";
	}

	@RequiresPermissions("cw:fFixedAssets:edit")
	@RequestMapping(value = "save")
	public String save(FFixedAssets fFixedAssets, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fFixedAssets)){
			return form(fFixedAssets, model);
		}
		fFixedAssetsService.save(fFixedAssets);
		addMessage(redirectAttributes, "保存固定资产登记成功");
		return "redirect:"+Global.getAdminPath()+"/cw/fFixedAssets/?repage";
	}
	
	@RequiresPermissions("cw:fFixedAssets:edit")
	@RequestMapping(value = "delete")
	public String delete(FFixedAssets fFixedAssets, RedirectAttributes redirectAttributes) {
		fFixedAssetsService.delete(fFixedAssets);
		addMessage(redirectAttributes, "删除固定资产登记成功");
		return "redirect:"+Global.getAdminPath()+"/cw/fFixedAssets/?repage";
	}

}