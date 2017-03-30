/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.test.web;

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
import com.tlkzzz.jeesite.modules.test.entity.ShopWxgoods;
import com.tlkzzz.jeesite.modules.test.service.ShopWxgoodsService;

/**
 * 微信红包Controller
 * @author xrc
 * @version 2017-03-20
 */
@Controller
@RequestMapping(value = "${adminPath}/test/shopWxgoods")
public class ShopWxgoodsController extends BaseController {

	@Autowired
	private ShopWxgoodsService shopWxgoodsService;
	
	@ModelAttribute
	public ShopWxgoods get(@RequestParam(required=false) String id) {
		ShopWxgoods entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = shopWxgoodsService.get(id);
		}
		if (entity == null){
			entity = new ShopWxgoods();
		}
		return entity;
	}
	
	@RequiresPermissions("test:shopWxgoods:view")
	@RequestMapping(value = {"list", ""})
	public String list(ShopWxgoods shopWxgoods, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ShopWxgoods> page = shopWxgoodsService.findPage(new Page<ShopWxgoods>(request, response), shopWxgoods); 
		model.addAttribute("page", page);
		return "modules/test/shopWxgoodsList";
	}

	@RequiresPermissions("test:shopWxgoods:view")
	@RequestMapping(value = "form")
	public String form(ShopWxgoods shopWxgoods, Model model) {
		model.addAttribute("shopWxgoods", shopWxgoods);
		return "modules/test/shopWxgoodsForm";
	}

	@RequiresPermissions("test:shopWxgoods:edit")
	@RequestMapping(value = "save")
	public String save(ShopWxgoods shopWxgoods, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, shopWxgoods)){
			return form(shopWxgoods, model);
		}
		shopWxgoodsService.save(shopWxgoods);
		addMessage(redirectAttributes, "保存微信红包成功");
		return "redirect:"+Global.getAdminPath()+"/test/shopWxgoods/?repage";
	}

	@RequiresPermissions("test:shopWxgoods:edit")
	@RequestMapping(value = "createSave")
	public String createSave(ShopWxgoods shopWxgoods, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, shopWxgoods)){
			return form(shopWxgoods, model);
		}
		shopWxgoodsService.createSave(shopWxgoods);
		addMessage(redirectAttributes, "保存微信红包成功");
		return "redirect:"+Global.getAdminPath()+"/test/shopWxgoods/?repage";
	}
	
	@RequiresPermissions("test:shopWxgoods:edit")
	@RequestMapping(value = "delete")
	public String delete(ShopWxgoods shopWxgoods, RedirectAttributes redirectAttributes) {
		shopWxgoodsService.delete(shopWxgoods);
		addMessage(redirectAttributes, "删除微信红包成功");
		return "redirect:"+Global.getAdminPath()+"/test/shopWxgoods/?repage";
	}

}