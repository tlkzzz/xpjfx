/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tlkzzz.jeesite.modules.ck.entity.CUnit;
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
import com.tlkzzz.jeesite.modules.ck.entity.CSpec;
import com.tlkzzz.jeesite.modules.ck.service.CSpecService;

import java.util.List;

/**
 * 规格表Controller
 * @author xrc
 * @version 2017-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/ck/cSpec")
public class CSpecController extends BaseController {

	@Autowired
	private CSpecService cSpecService;
	
	@ModelAttribute
	public CSpec get(@RequestParam(required=false) String id) {
		CSpec entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cSpecService.get(id);
		}
		if (entity == null){
			entity = new CSpec();
		}
		return entity;
	}
	
	@RequiresPermissions("ck:cSpec:view")
	@RequestMapping(value = {"list", ""})
	public String list(CSpec cSpec, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CSpec> page = cSpecService.findPage(new Page<CSpec>(request, response), cSpec); 
		model.addAttribute("page", page);
		model.addAttribute("cSpec", cSpec);
		return "modules/ck/cSpecList";
	}

	@RequiresPermissions("ck:cSpec:view")
	@RequestMapping(value = "form")
	public String form(CSpec cSpec, Model model) {
		model.addAttribute("cSpec", cSpec);
		return "modules/ck/cSpecForm";
	}

	@RequiresPermissions("ck:cSpec:edit")
	@RequestMapping(value = "save")
	public String save(CSpec cSpec, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cSpec)){
			return form(cSpec, model);
		}
		cSpecService.save(cSpec);
		addMessage(redirectAttributes, "保存规格表成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cSpec/?repage";
	}
	
	@RequiresPermissions("ck:cSpec:edit")
	@RequestMapping(value = "delete")
	public String delete(CSpec cSpec, RedirectAttributes redirectAttributes) {
		cSpecService.delete(cSpec);
		addMessage(redirectAttributes, "删除规格表成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cSpec/?repage";
	}
//	@ResponseBody
//	@RequiresPermissions("ck:cSpec:edit")
//	@RequestMapping(value = "checkName")
//	public String checkName(String name) {
//				CSpec cs = new CSpec();
//		if (StringUtils.isNotBlank(name)){
//			cs.setName(name);
//			List<CSpec> list = cSpecService.findList(cs);
//			if(list.size()>0){
//				return  "false";
//			}else{
//				return "true";
//			}
//		}else{
//			return "false";
//		}
//	}
//}

	@ResponseBody
	@RequestMapping(value = "checkName")
	public String checkName(String id,String name) {
		CUnit ch=new CUnit();
		if(StringUtils.isNotEmpty(id) && StringUtils.isNotEmpty(name)){
			//修改
			ch.setName(name);
			List<CSpec> list = cSpecService.getname(name);
//
//			for(CSpec cSpec: list){
//				if(cHouse!=null&&id.equals(cHouse.getId()))list.remove(cHouse);
//			}
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
			if(StringUtils.isNotEmpty(name)){
				ch.setName(name);
				List<CSpec> list=cSpecService.getname(name);
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