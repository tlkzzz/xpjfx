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
import com.tlkzzz.jeesite.modules.ck.entity.CDdinfo;
import com.tlkzzz.jeesite.modules.ck.service.CDdinfoService;

/**
 * 订单Controller
 * @author xrc
 * @version 2017-03-27
 */
@Controller
@RequestMapping(value = "${adminPath}/ck/cDdinfo")
public class CDdinfoController extends BaseController {

	@Autowired
	private CDdinfoService cDdinfoService;
	
	@ModelAttribute
	public CDdinfo get(@RequestParam(required=false) String id) {
		CDdinfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cDdinfoService.get(id);
		}
		if (entity == null){
			entity = new CDdinfo();
		}
		return entity;
	}
	/**		采购部分代码开始		 **/
	@RequiresPermissions("ck:cCginfo:view")
	@RequestMapping(value = "cgDdList")
	public String cgDdList(CDdinfo cDdinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CDdinfo> page = cDdinfoService.findPage(new Page<CDdinfo>(request, response), cDdinfo);
		model.addAttribute("cDdinfo", cDdinfo);
		model.addAttribute("page", page);
		return "modules/ck/cDdinfoList";
	}

	/**		采购部分代码结束		 **/

	@RequiresPermissions("ck:cDdinfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(CDdinfo cDdinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CDdinfo> page = cDdinfoService.findPage(new Page<CDdinfo>(request, response), cDdinfo); 
		model.addAttribute("cDdinfo", cDdinfo);
		model.addAttribute("page", page);
		return "modules/ck/cDdinfoList";
	}

	@RequiresPermissions("ck:cDdinfo:view")
	@RequestMapping(value = "form")
	public String form(CDdinfo cDdinfo, Model model) {
		model.addAttribute("cDdinfo", cDdinfo);
		return "modules/ck/cDdinfoForm";
	}

	@RequiresPermissions("ck:cDdinfo:edit")
	@RequestMapping(value = "save")
	public String save(CDdinfo cDdinfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cDdinfo)){
			return form(cDdinfo, model);
		}
		cDdinfoService.save(cDdinfo);
		addMessage(redirectAttributes, "保存订单成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cDdinfo/?repage";
	}
	
	@RequiresPermissions("ck:cDdinfo:edit")
	@RequestMapping(value = "delete")
	public String delete(CDdinfo cDdinfo, RedirectAttributes redirectAttributes) {
		cDdinfoService.delete(cDdinfo);
		addMessage(redirectAttributes, "删除订单成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cDdinfo/?repage";
	}

}