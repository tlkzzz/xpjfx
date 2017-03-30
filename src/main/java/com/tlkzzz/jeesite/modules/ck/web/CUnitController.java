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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tlkzzz.jeesite.common.config.Global;
import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.web.BaseController;
import com.tlkzzz.jeesite.common.utils.StringUtils;
import com.tlkzzz.jeesite.modules.ck.entity.CUnit;
import com.tlkzzz.jeesite.modules.ck.service.CUnitService;

import java.util.List;

/**
 * 单位表Controller
 * @author xrc
 * @version 2017-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/ck/cUnit")
public class CUnitController extends BaseController {

	@Autowired
	private CUnitService cUnitService;

	@ModelAttribute
	public CUnit get(@RequestParam(required = false) String id) {
		CUnit entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = cUnitService.get(id);
		}
		if (entity == null) {
			entity = new CUnit();
		}
		return entity;
	}

	@RequiresPermissions("ck:cUnit:view")
	@RequestMapping(value = {"list", ""})
	public String list(CUnit cUnit, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CUnit> page = cUnitService.findPage(new Page<CUnit>(request, response), cUnit);
		model.addAttribute("page", page);
		model.addAttribute("cUnit", cUnit);
		return "modules/ck/cUnitList";
	}

	@RequiresPermissions("ck:cUnit:view")
	@RequestMapping(value = "form")
	public String form(CUnit cUnit, Model model) {
		model.addAttribute("cUnit", cUnit);
		return "modules/ck/cUnitForm";
	}

	@RequiresPermissions("ck:cUnit:edit")
	@RequestMapping(value = "save")
	public String save(CUnit cUnit, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cUnit)) {
			return form(cUnit, model);
		}
		cUnitService.save(cUnit);
		addMessage(redirectAttributes, "保存单位表成功");
		return "redirect:" + Global.getAdminPath() + "/ck/cUnit/?repage";
	}

	@RequiresPermissions("ck:cUnit:edit")
	@RequestMapping(value = "delete")
	public String delete(CUnit cUnit, RedirectAttributes redirectAttributes) {
		cUnitService.delete(cUnit);
		addMessage(redirectAttributes, "删除单位表成功");
		return "redirect:" + Global.getAdminPath() + "/ck/cUnit/?repage";
	}

	/**
	 * 修改方法
	 * @param id
	 * @param name
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "checkName")
	public String checkName(String id,String name) {
		CUnit ch=new CUnit();
		if(StringUtils.isNotEmpty(id) && StringUtils.isNotEmpty(name)){
			//修改
			ch.setName(name);
			List<CUnit> list = cUnitService.getname(name);
//
//			for(CUnit cUnit: list){
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
				List<CUnit> list=cUnitService.getname(name);
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