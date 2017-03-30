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
import com.tlkzzz.jeesite.modules.test.entity.ShopWxpacket;
import com.tlkzzz.jeesite.modules.test.service.ShopWxpacketService;

/**
 * 红包Controller
 * @author xrc
 * @version 2017-03-20
 */
@Controller
@RequestMapping(value = "${adminPath}/test/shopWxpacket")
public class ShopWxpacketController extends BaseController {

	@Autowired
	private ShopWxpacketService shopWxpacketService;
	
	@ModelAttribute
	public ShopWxpacket get(@RequestParam(required=false) String id) {
		ShopWxpacket entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = shopWxpacketService.get(id);
		}
		if (entity == null){
			entity = new ShopWxpacket();
		}
		return entity;
	}
	
	@RequiresPermissions("test:shopWxpacket:view")
	@RequestMapping(value = {"list", ""})
	public String list(ShopWxpacket shopWxpacket, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ShopWxpacket> page = shopWxpacketService.findPage(new Page<ShopWxpacket>(request, response), shopWxpacket); 
		model.addAttribute("page", page);
		return "modules/test/shopWxpacketList";
	}

//	@RequiresPermissions("test:shopWxpacket:view")
	@RequestMapping(value = "exportExcel")
	public String exprotExcle(ShopWxpacket shopWxpacket, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try{
			shopWxpacketService.exportExcel(shopWxpacket, response);
			return null;
		}catch (Exception e){
			e.printStackTrace();
			addMessage(redirectAttributes, "导出列表失败！ 失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/test/shopWxgoods/?repage";
	}

	@RequiresPermissions("test:shopWxpacket:view")
	@RequestMapping(value = "form")
	public String form(ShopWxpacket shopWxpacket, Model model) {
		model.addAttribute("shopWxpacket", shopWxpacket);
		return "modules/test/shopWxpacketForm";
	}

	@RequiresPermissions("test:shopWxpacket:edit")
	@RequestMapping(value = "save")
	public String save(ShopWxpacket shopWxpacket, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, shopWxpacket)){
			return form(shopWxpacket, model);
		}
		shopWxpacketService.save(shopWxpacket);
		addMessage(redirectAttributes, "保存红包成功");
		return "redirect:"+Global.getAdminPath()+"/test/shopWxpacket/?repage";
	}
	
	@RequiresPermissions("test:shopWxpacket:edit")
	@RequestMapping(value = "delete")
	public String delete(ShopWxpacket shopWxpacket, RedirectAttributes redirectAttributes) {
		shopWxpacketService.delete(shopWxpacket);
		addMessage(redirectAttributes, "删除红包成功");
		return "redirect:"+Global.getAdminPath()+"/test/shopWxpacket/?repage";
	}

}