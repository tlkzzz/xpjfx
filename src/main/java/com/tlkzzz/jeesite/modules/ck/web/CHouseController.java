/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tlkzzz.jeesite.common.utils.DateUtils;
import com.tlkzzz.jeesite.common.utils.excel.ExportExcel;
import com.tlkzzz.jeesite.modules.ck.entity.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tlkzzz.jeesite.common.config.Global;
import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.web.BaseController;
import com.tlkzzz.jeesite.common.utils.StringUtils;
import com.tlkzzz.jeesite.modules.ck.service.CHouseService;

import java.util.List;
import java.util.Map;

/**
 * 仓库表Controller
 * @author xrc
 * @version 2017-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/ck/cHouse")
public class CHouseController extends BaseController {

	@Autowired
	private CHouseService cHouseService;
	
	@ModelAttribute
	public CHouse get(@RequestParam(required=false) String id) {
		CHouse entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cHouseService.get(id);
		}
		if (entity == null){
			entity = new CHouse();
		}
		return entity;
	}

	@RequiresPermissions("ck:cHouse:view")
	@RequestMapping(value = {"list", ""})
	public String list(CHouse cHouse, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CHouse> page = cHouseService.findPage(new Page<CHouse>(request, response), cHouse); 
		model.addAttribute("page", page);
		model.addAttribute("cHouse", cHouse);
		return "modules/ck/cHouseList";
	}

	@RequiresPermissions("ck:cHouse:view")
	@RequestMapping(value = "form")
	public String form(CHouse cHouse, Model model) {
		model.addAttribute("cHouse", cHouse);
		return "modules/ck/cHouseForm";
	}

	@RequiresPermissions("ck:cHouse:edit")
	@RequestMapping(value = "save")
	public String save(CHouse cHouse, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cHouse)){
			return form(cHouse, model);
		}
		cHouseService.save(cHouse);
		addMessage(redirectAttributes, "保存仓库表成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cHouse/?repage";
	}
	
	@RequiresPermissions("ck:cHouse:edit")
	@RequestMapping(value = "delete")
	public String delete(CHouse cHouse, RedirectAttributes redirectAttributes) {
		cHouseService.delete(cHouse);
		addMessage(redirectAttributes, "删除仓库表成功");
		return "redirect:" + Global.getAdminPath() + "/ck/cHouse/?repage";
	}


    @ResponseBody
    @RequiresPermissions("ck:cHouse:edit")
    @RequestMapping(value = "changeIsMainStock")
    public String changeIsMainStock(String id) {
        try{
       //     cHouseService.clearMainStock();
            cHouseService.changeIsMainStock(id);
            return "true";
        }catch (Exception e){
            e.printStackTrace();
            return "false";
        }
    }

	@ResponseBody
	@RequestMapping(value = "checkName")
	public String checkName(String id ,String name ) {
		CHouse ch=new CHouse();
		if(StringUtils.isNotEmpty(id) && StringUtils.isNotEmpty(name)){
			//修改
			List<CHouse> list = cHouseService.getname(name);
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
				List<CHouse> list=cHouseService.getname(name);
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
	@ResponseBody
	@RequestMapping(value = "checkcode")
	public String checkcode(String id ,String code ) {
		CHouse ch=new CHouse();
		if(StringUtils.isNotEmpty(id) && StringUtils.isNotEmpty(code)){
			//修改
			List<CHouse> list = cHouseService.getcode(code);
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
				List<CHouse> list=cHouseService.getcode(code);
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
	
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "lists")
	public List<Map<String, Object>> lists(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<CHouse> list = cHouseService.findList(new CHouse());
		for (int i=0; i<list.size(); i++){
			CHouse e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()))){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", 0);
				String a = e.getState();
//				if(a.equals("1")){
//					map.put("name", e.getName()+"(主)");
//				}else{
				map.put("name", e.getName());
//				}
				mapList.add(map);
			}
		}
		return mapList;
	}
}