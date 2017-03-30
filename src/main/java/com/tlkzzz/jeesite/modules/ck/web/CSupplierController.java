/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tlkzzz.jeesite.common.utils.DateUtils;
import com.tlkzzz.jeesite.common.utils.excel.ExportExcel;
import com.tlkzzz.jeesite.modules.ck.entity.CCar;
import com.tlkzzz.jeesite.modules.ck.entity.CHouse;
import com.tlkzzz.jeesite.modules.sys.entity.User;
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
import com.tlkzzz.jeesite.modules.ck.entity.CSupplier;
import com.tlkzzz.jeesite.modules.ck.service.CSupplierService;

import java.util.List;

/**
 * 供应商表Controller
 * @author xrc
 * @version 2017-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/ck/cSupplier")
public class CSupplierController extends BaseController {

	@Autowired
	private CSupplierService cSupplierService;
	
	@ModelAttribute
	public CSupplier get(@RequestParam(required=false) String id) {
		CSupplier entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cSupplierService.get(id);
		}
		if (entity == null){
			entity = new CSupplier();
		}
		return entity;
	}
	
	@RequiresPermissions("ck:cSupplier:view")
	@RequestMapping(value = {"list", ""})
	public String list(CSupplier cSupplier, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CSupplier> page = cSupplierService.findPage(new Page<CSupplier>(request, response), cSupplier); 
		model.addAttribute("page", page);
		model.addAttribute("cSupplier", cSupplier);
		return "modules/ck/cSupplierList";
	}

	@RequiresPermissions("ck:cSupplier:view")
	@RequestMapping(value = "form")
	public String form(CSupplier cSupplier, Model model) {
		model.addAttribute("cSupplier", cSupplier);
		return "modules/ck/cSupplierForm";
	}

	@RequiresPermissions("ck:cSupplier:edit")
	@RequestMapping(value = "save")
	public String save(CSupplier cSupplier, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cSupplier)){
			return form(cSupplier, model);
		}
		cSupplierService.save(cSupplier);
		addMessage(redirectAttributes, "保存供应商表成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cSupplier/?repage";
	}
	
	@RequiresPermissions("ck:cSupplier:edit")
	@RequestMapping(value = "delete")
	public String delete(CSupplier cSupplier, RedirectAttributes redirectAttributes) {
		cSupplierService.delete(cSupplier);
		addMessage(redirectAttributes, "删除供应商表成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cSupplier/?repage";
	}

	/**
	 * 导出供应商数据
	 * @param cSupplier
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "exportFile", method = RequestMethod.POST)
	public String exportFile(CSupplier cSupplier, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "供应商数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";

			Page<CSupplier> page = cSupplierService.findUser(new Page<CSupplier>(request, response, -1), cSupplier);
			new ExportExcel("导出数据", CSupplier.class).setDataList(page.getList()).write(response, fileName).dispose();
			//return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出用户失败！失败信息：" + e.getMessage());
			e.printStackTrace();
		}
		//return "redirect:" + bsPath + "/cm/tCmMsg/list1320?repage";
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "checkName")
	public String checkName(String id ,String name ) {
		CSupplier ch=new CSupplier();
		if(StringUtils.isNotEmpty(id) && StringUtils.isNotEmpty(name)){
			//修改
			List<CSupplier> list = cSupplierService.getname(name);
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
				List<CSupplier> list=cSupplierService.getname(name);
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
		CSupplier ch=new CSupplier();
		if(StringUtils.isNotEmpty(id) && StringUtils.isNotEmpty(code)){
			//修改
			List<CSupplier> list = cSupplierService.getcode(code);
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
				List<CSupplier> list=cSupplierService.getcode(code);
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