/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.web;

import java.util.List;
import java.util.Map;

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

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tlkzzz.jeesite.common.config.Global;
import com.tlkzzz.jeesite.common.web.BaseController;
import com.tlkzzz.jeesite.common.utils.StringUtils;
import com.tlkzzz.jeesite.modules.ck.entity.CGclass;
import com.tlkzzz.jeesite.modules.ck.service.CGclassService;

/**
 * 商品分类表生成Controller
 * @author xrc
 * @version 2017-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/ck/cGclass")
public class CGclassController extends BaseController {

	@Autowired
	private CGclassService cGclassService;
	
	@ModelAttribute
	public CGclass get(@RequestParam(required=false) String id) {
		CGclass entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cGclassService.get(id);
		}
		if (entity == null){
			entity = new CGclass();
		}
		return entity;
	}
	
	@RequiresPermissions("ck:cGclass:view")
	@RequestMapping(value = {"list", ""})
	public String list(CGclass cGclass, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<CGclass> list = cGclassService.findList(cGclass); 
		model.addAttribute("list", list);
		model.addAttribute("cGclass", cGclass);
		return "modules/ck/cGclassList";
	}

	@RequiresPermissions("ck:cGclass:view")
	@RequestMapping(value = "form")
	public String form(CGclass cGclass, Model model) {
		if (cGclass.getParent()!=null && StringUtils.isNotBlank(cGclass.getParent().getId())){
			cGclass.setParent(cGclassService.get(cGclass.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(cGclass.getId())){
				CGclass cGclassChild = new CGclass();
				cGclassChild.setParent(new CGclass(cGclass.getParent().getId()));
				List<CGclass> list = cGclassService.findList(cGclass); 
				if (list.size() > 0){
					cGclass.setSort(list.get(list.size()-1).getSort());
					if (cGclass.getSort() != null){
						cGclass.setSort(cGclass.getSort() + 30);
					}
				}
			}
		}
		if (cGclass.getSort() == null){
			cGclass.setSort(30);
		}
		model.addAttribute("cGclass", cGclass);
		return "modules/ck/cGclassForm";
	}

	@RequiresPermissions("ck:cGclass:edit")
	@RequestMapping(value = "save")
	public String save(CGclass cGclass, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cGclass)){
			return form(cGclass, model);
		}
		cGclassService.save(cGclass);
		addMessage(redirectAttributes, "保存商品分类表成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cGclass/?repage";
	}
	
	@RequiresPermissions("ck:cGclass:edit")
	@RequestMapping(value = "delete")
	public String delete(CGclass cGclass, RedirectAttributes redirectAttributes) {
		cGclassService.delete(cGclass);
		addMessage(redirectAttributes, "删除商品分类表成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cGclass/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<CGclass> list = cGclassService.findList(new CGclass());
		for (int i=0; i<list.size(); i++){
			CGclass e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "listByParent")
	public List<CGclass> listByParent(CGclass gclass, HttpServletResponse response) {
		if(gclass==null){
			gclass = new CGclass();
		}
		if(gclass.getParent()==null){
			gclass.setParent(new CGclass("0"));
		}
		List<CGclass> list = cGclassService.findListContainGoodsNum(gclass);
		return list;
	}

	@ResponseBody
	@RequestMapping(value = "checkcode")
	public String checkcode(String id ,String code ) {
		CGclass ch=new CGclass();
		if(StringUtils.isNotEmpty(id) && StringUtils.isNotEmpty(code)){
			//修改
			List<CGclass> list = cGclassService.getcode(code);
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
				List<CGclass> list=cGclassService.getcode(code);
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