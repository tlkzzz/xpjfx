/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tlkzzz.jeesite.modules.ck.entity.*;
import com.tlkzzz.jeesite.modules.ck.service.*;
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

import java.util.List;

/**
 * 商品生成Controller
 * @author xrc
 * @version 2017-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/ck/cGoods")
public class CGoodsController extends BaseController {

	@Autowired
	private CGoodsService cGoodsService;
	@Autowired
	private CBandsService cBandsService;
	@Autowired
	private CSupplierService supplierService;
	@Autowired
	private CUnitService unitService;
	@Autowired
	private CSpecService specService;
	@Autowired
	private CGclassService gclassService;
	
	@ModelAttribute
	public CGoods get(@RequestParam(required=false) String id) {
		CGoods entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cGoodsService.get(id);
		}
		if (entity == null){
			entity = new CGoods();
		}
		return entity;
	}


	@RequiresPermissions("ck:cGoods:view")
	@RequestMapping(value = "")
	public String index() {
		return "modules/ck/cGoodsIndex";
	}

	@RequiresPermissions("ck:cGoods:view")
	@RequestMapping(value = "tree")
	public String tree(Model model) {
		model.addAttribute("areaList", gclassService.findList(new CGclass()));
		return "modules/ck/cGoodsTree";
	}

	@RequiresPermissions("ck:cGoods:view")
	@RequestMapping(value = "none")
	public String none() {
		return "modules/ck/cGoodsNone";
	}

	@RequiresPermissions("ck:cGoods:view")
	@RequestMapping(value = "list")
	public String list(CGoods cGoods, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CGoods> page = cGoodsService.findPage(new Page<CGoods>(request, response), cGoods);
		model.addAttribute("page", page);
		model.addAttribute("cGoods", cGoods);
		model.addAttribute("bandsList", cBandsService.findList(new CBands()));
		model.addAttribute("supplierList", supplierService.findList(new CSupplier()));
		return "modules/ck/cGoodsList";
	}

	@RequiresPermissions("ck:cGoods:view")
	@RequestMapping(value = "form")
	public String form(CGoods cGoods, Model model) {
		model.addAttribute("cGoods", cGoods);
		model.addAttribute("bandsList", cBandsService.findList(new CBands()));//品牌列表
		model.addAttribute("supplierList", supplierService.findList(new CSupplier()));//供应商列表
		model.addAttribute("unitList", unitService.findList(new CUnit()));//单位列表
		model.addAttribute("specList", specService.findList(new CSpec()));//规格列表
		return "modules/ck/cGoodsForm";
	}

	@RequiresPermissions("ck:cGoods:edit")
	@RequestMapping(value = "save")
	public String save(CGoods cGoods, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cGoods)){
			return form(cGoods, model);
		}
		cGoodsService.save(cGoods);
		addMessage(redirectAttributes, "保存商品成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cGoods/list?repage";
	}

	@RequiresPermissions("ck:cGoods:edit")
	@RequestMapping(value = "delete")
	public String delete(CGoods cGoods, RedirectAttributes redirectAttributes) {
		cGoodsService.delete(cGoods);
		addMessage(redirectAttributes, "删除商品成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cGoods/list?repage";
	}

	@RequiresPermissions("ck:cGoodsAnalysis:view")
	@RequestMapping(value = "goodsAnalysisIndex")
	public String goodsAnalysisIndex(){
		return "modules/report/cGoodsAnalysisIndex";
	}

	@RequiresPermissions("ck:cGoodsAnalysis:view")
	@RequestMapping(value = "goodsAnalysis")
	public String goodsAnalysis(CGoods cGoods, Model model){
		model.addAttribute("list", cGoodsService.findList(cGoods));
		return "modules/report/cGoodsAnalysisList";
	}

	@RequestMapping(value = "goodslistexcel")
	public String goodslistexcel(CGoods cGoods, Model model,HttpServletResponse response){
		List<CGoods> lsit=cGoodsService.findList(cGoods);
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "getGoods")
	public CGoods getGoods(CGoods goods){
		return goods;
	}

	@ResponseBody
	@RequestMapping(value = "checksort")
	public String checksort(String id ,String sort ) {
		CGoods ch=new CGoods();
		if(StringUtils.isNotEmpty(id) && StringUtils.isNotEmpty(sort)){
			//修改
			List<CGoods> list = cGoodsService.getsort(sort);
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
			if(StringUtils.isNotEmpty(sort)){
				List<CGoods> list=cGoodsService.getsort(sort);
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