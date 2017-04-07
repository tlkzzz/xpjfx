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
import com.tlkzzz.jeesite.modules.ck.entity.CKm;
import com.tlkzzz.jeesite.modules.ck.service.CKmService;

/**
 * 科目类别表Controller
 * @author szx
 * @version 2017-04-05
 */
@Controller
@RequestMapping(value = "${adminPath}/ck/cKm")
public class CKmController extends BaseController {

	@Autowired
	private CKmService cKmService;
	
	@ModelAttribute
	public CKm get(@RequestParam(required=false) String id) {
		CKm entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cKmService.get(id);
		}
		if (entity == null){
			entity = new CKm();
		}
		return entity;
	}
	
	@RequiresPermissions("ck:cKm:view")
	@RequestMapping(value = {"list", ""})
	public String list(CKm cKm, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<CKm> list = cKmService.findList(cKm); 
		model.addAttribute("list", list);
		model.addAttribute("cKm", cKm);
		return "modules/ck/cKmList";
	}

	@RequiresPermissions("ck:cKm:view")
	@RequestMapping(value = "form")
	public String form(CKm cKm, Model model) {
		if (cKm.getParent()!=null && StringUtils.isNotBlank(cKm.getParent().getId())){
			cKm.setParent(cKmService.get(cKm.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(cKm.getId())){
				CKm cKmChild = new CKm();
				cKmChild.setParent(new CKm(cKm.getParent().getId()));
				List<CKm> list = cKmService.findList(cKm); 
				if (list.size() > 0){
					cKm.setSort(list.get(list.size()-1).getSort());
					if (cKm.getSort() != null){
						cKm.setSort(cKm.getSort() + 30);
					}
				}
			}
		}
		if (cKm.getSort() == null){
			cKm.setSort(30);
		}
		model.addAttribute("cKm", cKm);
		return "modules/ck/cKmForm";
	}

	@RequiresPermissions("ck:cKm:edit")
	@RequestMapping(value = "save")
	public String save(CKm cKm, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cKm)){
			return form(cKm, model);
		}
		cKmService.save(cKm);
		addMessage(redirectAttributes, "保存科目类别表成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cKm/?repage";
	}
	
	@RequiresPermissions("ck:cKm:edit")
	@RequestMapping(value = "delete")
	public String delete(CKm cKm, RedirectAttributes redirectAttributes) {
		cKmService.delete(cKm);
		addMessage(redirectAttributes, "删除科目类别表成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cKm/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<CKm> list = cKmService.findList(new CKm());
		for (int i=0; i<list.size(); i++){
			CKm e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getKmname());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
}