/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tlkzzz.jeesite.modules.ck.entity.CCar;
import com.tlkzzz.jeesite.modules.ck.entity.CHouse;
import com.tlkzzz.jeesite.modules.ck.service.CCarService;
import com.tlkzzz.jeesite.modules.ck.service.CHouseService;
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
import com.tlkzzz.jeesite.modules.ck.entity.CHouCar;
import com.tlkzzz.jeesite.modules.ck.service.CHouCarService;

/**
 * 仓库车辆表生成Controller
 * @author xrc
 * @version 2017-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/ck/cHouCar")
public class CHouCarController extends BaseController {

	@Autowired
	private CHouCarService cHouCarService;
	@Autowired
	private CHouseService houseService;
	@Autowired
	private CCarService carService;
	
	@ModelAttribute
	public CHouCar get(@RequestParam(required=false) String id) {
		CHouCar entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cHouCarService.get(id);
		}
		if (entity == null){
			entity = new CHouCar();
		}
		return entity;
	}
	
	@RequiresPermissions("ck:cHouCar:view")
	@RequestMapping(value = {"list", ""})
	public String list(CHouCar cHouCar, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CHouCar> page = cHouCarService.findPage(new Page<CHouCar>(request, response), cHouCar); 
		model.addAttribute("page", page);
		model.addAttribute("cHouCar", cHouCar);
		model.addAttribute("houseList", houseService.findList(new CHouse()));
		model.addAttribute("carList", carService.findList(new CCar()));
		return "modules/ck/cHouCarList";
	}

	@RequiresPermissions("ck:cHouCar:view")
	@RequestMapping(value = "form")
	public String form(CHouCar cHouCar, Model model) {
		if(cHouCar!=null&&StringUtils.isNotBlank(cHouCar.getId()))
			cHouCar = cHouCarService.findUserList(cHouCar);
		model.addAttribute("cHouCar", cHouCar);
		model.addAttribute("houseList", houseService.findList(new CHouse()));
		model.addAttribute("carList", carService.findList(new CCar()));
		return "modules/ck/cHouCarForm";
	}

	@RequiresPermissions("ck:cHouCar:edit")
	@RequestMapping(value = "save")
	public String save(CHouCar cHouCar, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cHouCar)){
			return form(cHouCar, model);
		}
		cHouCarService.save(cHouCar);
		addMessage(redirectAttributes, "保存仓库车辆表成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cHouCar/?repage";
	}
	
	@RequiresPermissions("ck:cHouCar:edit")
	@RequestMapping(value = "delete")
	public String delete(CHouCar cHouCar, RedirectAttributes redirectAttributes) {
		cHouCarService.delete(cHouCar);
		addMessage(redirectAttributes, "删除仓库车辆表成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cHouCar/?repage";
	}

}