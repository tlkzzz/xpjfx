/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tlkzzz.jeesite.modules.ck.entity.CGoods;
import com.tlkzzz.jeesite.modules.ck.entity.CHouse;
import com.tlkzzz.jeesite.modules.ck.service.CGoodsService;
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
import com.tlkzzz.jeesite.modules.ck.entity.CRkinfo;
import com.tlkzzz.jeesite.modules.ck.service.CRkinfoService;

import java.util.ArrayList;
import java.util.List;

/**
 * 入库记录Controller
 * @author xrc
 * @version 2017-03-15
 */
@Controller
@RequestMapping(value = "${adminPath}/ck/cRkinfo")
public class CRkinfoController extends BaseController {

	@Autowired
	private CRkinfoService cRkinfoService;
	@Autowired
	private CGoodsService cGoodsService;
	@Autowired
	private CHouseService cHouseService;
	
	@ModelAttribute
	public CRkinfo get(@RequestParam(required=false) String id) {
		CRkinfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cRkinfoService.get(id);
		}
		if (entity == null){
			entity = new CRkinfo();
		}
		return entity;
	}
	
	@RequiresPermissions("ck:cRkinfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(CRkinfo cRkinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CRkinfo> page = cRkinfoService.findPage(new Page<CRkinfo>(request, response), cRkinfo); 
		model.addAttribute("cRkinfo", cRkinfo);
		model.addAttribute("page", page);
		return "modules/ck/cRkinfoList";
	}

	@RequiresPermissions("ck:cRkinfo:view")
	@RequestMapping(value = "form")
	public String form(CRkinfo cRkinfo, Model model) {
		model.addAttribute("cRkinfo", cRkinfo);
		return "modules/ck/cRkinfoForm";
	}

	@RequiresPermissions("ck:cRkinfo:edit")
	@RequestMapping(value = "save")
	public String save(CRkinfo cRkinfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cRkinfo)){
			return form(cRkinfo, model);
		}
//		cRkinfoService.save(cRkinfo);
		addMessage(redirectAttributes, "保存入库记录成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cRkinfo/?repage";
	}
	
	@RequiresPermissions("ck:cRkinfo:edit")
	@RequestMapping(value = "delete")
	public String delete(CRkinfo cRkinfo, RedirectAttributes redirectAttributes) {
//		cRkinfoService.delete(cRkinfo);
		addMessage(redirectAttributes, "删除入库记录成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cRkinfo/?repage";
	}

	/** 	报表	start	**/
	@RequiresPermissions("ck:cRkinfoInquire:view")
	@RequestMapping(value = "rkInquire")//单据查询
	public String rkInquire(CRkinfo cRkinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CRkinfo> page = cRkinfoService.findPage(new Page<CRkinfo>(request, response), cRkinfo);
		model.addAttribute("cRkinfo", cRkinfo);
		model.addAttribute("page", page);
		return "modules/report/cRkinfoInquireList";
	}

	/**
	 * 入库报表显示
	 * @param cRkinfo
	 * @param type 1或null:商品明细，2:商品汇总，3:单据明细
	 * @param model
	 * @return
	 */
//	@RequiresPermissions("ck:cRkinfoReport:view")
	@RequestMapping(value = "rkReport")//报表
	public String rkReport(CRkinfo cRkinfo, String type, Model model) {
		List<CRkinfo> list = new ArrayList<CRkinfo>();
		if(StringUtils.isNotBlank(type)&&"2".equals(type)){
			list = cRkinfoService.findReportList(cRkinfo);
		}else if(StringUtils.isNotBlank(type)&&"3".equals(type)) {
			list = cRkinfoService.findList(cRkinfo);
		}else {
			list = cRkinfoService.findList(cRkinfo);
		}
		model.addAttribute("goodsList", cGoodsService.findList(new CGoods()));
		model.addAttribute("houseList", cHouseService.findList(new CHouse()));
		model.addAttribute("cRkinfo", cRkinfo);
		model.addAttribute("type", type);
		model.addAttribute("list", list);
		return "modules/report/cRkinfoReportList";
	}

}