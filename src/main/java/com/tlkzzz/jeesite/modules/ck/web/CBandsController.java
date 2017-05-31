/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tlkzzz.jeesite.modules.sys.security.SystemAuthorizingRealm;
import com.tlkzzz.jeesite.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tlkzzz.jeesite.common.config.Global;
import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.web.BaseController;
import com.tlkzzz.jeesite.common.utils.StringUtils;
import com.tlkzzz.jeesite.modules.ck.entity.CBands;
import com.tlkzzz.jeesite.modules.ck.service.CBandsService;

import java.util.HashMap;
import java.util.Map;

/**
 * 品牌Controller
 * @author xrc
 * @version 2017-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/ck/cBands")
public class CBandsController extends BaseController {

	@Autowired
	private CBandsService cBandsService;
	
	@ModelAttribute
	public CBands get(@RequestParam(required=false) String id) {
		CBands entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cBandsService.get(id);
		}
		if (entity == null){
			entity = new CBands();
		}
		return entity;
	}
	
	@RequiresPermissions("ck:cBands:view")
	@RequestMapping(value = {"list", ""})
	public String list(CBands cBands, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CBands> page = cBandsService.findPage(new Page<CBands>(request, response), cBands); 
		model.addAttribute("page", page);
		model.addAttribute("cBands", cBands);
		return "modules/ck/cBandsList";
	}

	@RequiresPermissions("ck:cBands:view")
	@RequestMapping(value = "form")
	public String form(CBands cBands, Model model) {
		model.addAttribute("cBands", cBands);
		return "modules/ck/cBandsForm";
	}

	@RequiresPermissions("ck:cBands:edit")
	@RequestMapping(value = "save")
	public String save(CBands cBands, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cBands)){
			return form(cBands, model);
		}
		cBandsService.save(cBands);
		addMessage(redirectAttributes, "保存品牌成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cBands/?repage";
	}
	
	@RequiresPermissions("ck:cBands:edit")
	@RequestMapping(value = "delete")
	public String delete(CBands cBands, RedirectAttributes redirectAttributes) {
		cBandsService.delete(cBands);
		addMessage(redirectAttributes, "删除品牌成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cBands/?repage";
	}

	@ResponseBody
	@RequestMapping(value = {"list"}, method = RequestMethod.POST)
	public String list(HttpServletRequest request, HttpServletResponse response) {
		SystemAuthorizingRealm.Principal principal = UserUtils.getPrincipal();
		Map<String,Object> map =new HashMap<String, Object>();
		map.put("aa","bbbs");
		principal.setMap(map);
		System.out.println("---------------手机访问拦截器");
		return renderString(response, principal);
	}

	@ResponseBody
	@RequestMapping(value = "/logout",method = RequestMethod.GET)
	public String logout( HttpServletRequest request, HttpServletResponse response){
		SystemAuthorizingRealm.Principal principal = UserUtils.getPrincipal();
		Session session= UserUtils.getSession();
		UserUtils.getSubject().logout();
		Map<String,Object> map =new HashMap<String, Object>();
		map.put("out","登出");
		map.put("out",session);
		principal.setMap(map);
		return renderString(response, principal);
	}
}