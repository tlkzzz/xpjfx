/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tlkzzz.jeesite.modules.ck.entity.CHouse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tlkzzz.jeesite.common.config.Global;
import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.web.BaseController;
import com.tlkzzz.jeesite.common.utils.StringUtils;
import com.tlkzzz.jeesite.modules.ck.entity.CCar;
import com.tlkzzz.jeesite.modules.ck.service.CCarService;

import java.util.List;

/**
 * 车辆表生成Controller
 * @author xrc
 * @version 2017-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/ck/cCar")
public class CCarController extends BaseController {

	@Autowired
	private CCarService cCarService;
	
	@ModelAttribute
	public CCar get(@RequestParam(required=false) String id) {
		CCar entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cCarService.get(id);
		}
		if (entity == null){
			entity = new CCar();
		}
		return entity;
	}
	
	@RequiresPermissions("ck:cCar:view")
	@RequestMapping(value = {"list", ""})
	public String list(CCar cCar, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CCar> page = cCarService.findPage(new Page<CCar>(request, response), cCar); 
		model.addAttribute("page", page);
		model.addAttribute("cCar", cCar);
		return "modules/ck/cCarList";
	}

	@RequiresPermissions("ck:cCar:view")
	@RequestMapping(value = "form")
	public String form(CCar cCar, Model model) {
		model.addAttribute("cCar", cCar);
		return "modules/ck/cCarForm";
	}

	@RequiresPermissions("ck:cCar:edit")
	@RequestMapping(value = "save")
	public String save(CCar cCar, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cCar)){
			return form(cCar, model);
		}
		cCarService.save(cCar);
		addMessage(redirectAttributes, "保存车辆表成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cCar/?repage";
	}
	
	@RequiresPermissions("ck:cCar:edit")
	@RequestMapping(value = "delete")
	public String delete(CCar cCar, RedirectAttributes redirectAttributes) {
		cCarService.delete(cCar);
		addMessage(redirectAttributes, "删除车辆表成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cCar/?repage";
	}
	@ResponseBody
	@RequestMapping(value = "checkcode")
	public String checkcode(String id ,String code ) {
		CCar ch=new CCar();
		if(StringUtils.isNotEmpty(id) && StringUtils.isNotEmpty(code)){
			//修改
			List<CCar> list = cCarService.getcode(code);
			if(list.size()>0){
				String i = list.get(0).getId();
				if(i.equals(id)){
					return  "true";
				}else{
					return "false";
				}
			}else{
				return  "true";
			}
		}else{
			//添加
			if(StringUtils.isNotEmpty(code)){
				List<CCar> list=cCarService.getcode(code);
				if(list.size()>0){
					return "false";
				}else{
					return "true";
				}
			}else{
				return "false";
			}
		}
	}
}