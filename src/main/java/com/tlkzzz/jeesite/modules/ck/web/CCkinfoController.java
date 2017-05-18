/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tlkzzz.jeesite.modules.ck.entity.*;
import com.tlkzzz.jeesite.modules.ck.service.*;
import com.tlkzzz.jeesite.modules.cw.entity.FReceipt;
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

import java.util.ArrayList;
import java.util.List;

/**
 * 出库信息Controller
 * @author xrc
 * @version 2017-03-21
 */
@Controller
@RequestMapping(value = "${adminPath}/ck/cCkinfo")
public class CCkinfoController extends BaseController {

	@Autowired
	private CCkinfoService cCkinfoService;
	@Autowired
	private CHouseService cHouseService;
	@Autowired
	private CGoodsService cGoodsService;
	@Autowired
	private CStoreService cStoreService;
	@Autowired
	private CSupplierService cSupplierService;
	@Autowired
	private CRkckddinfoService cRkckddinfoService;
	
	@ModelAttribute
	public CCkinfo get(@RequestParam(required=false) String id) {
		CCkinfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cCkinfoService.get(id);
		}
		if (entity == null){
			entity = new CCkinfo();
		}
		return entity;
	}
	
	@RequiresPermissions("ck:cCkinfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(CCkinfo cCkinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CCkinfo> page = cCkinfoService.findPage(new Page<CCkinfo>(request, response), cCkinfo);
		model.addAttribute("supplierList", cSupplierService.findList(new CSupplier()));
		model.addAttribute("houseList", cHouseService.findList(new CHouse()));
		model.addAttribute("goodsList", cGoodsService.findList(new CGoods()));
		model.addAttribute("storeList", cStoreService.findList(new CStore()));
		model.addAttribute("cCkinfo", cCkinfo);
		model.addAttribute("page", page);
		return "modules/ck/cCkinfoList";
	}

	@RequiresPermissions("ck:cCkinfo:view")
	@RequestMapping(value = "form")
	public String form(CCkinfo cCkinfo, Model model) {
		//model.addAttribute("cCkinfo", cCkinfo);
		return "error/400";
	}

	@RequiresPermissions("ck:cCkinfo:view")
	@RequestMapping(value = "library")
	public String library(CCkinfo cCkinfo, Model model) {//出库录单
		model.addAttribute("storeList", cStoreService.findList(new CStore()));
		model.addAttribute("houseList", cHouseService.findList(new CHouse()));
		model.addAttribute("goodsList", cGoodsService.findList(new CGoods()));
		model.addAttribute("cCkinfo", cCkinfo);
		return "modules/ck/cCkInfoLibrary";
	}

	@RequiresPermissions("ck:cCkinfo:edit")
	@RequestMapping(value = "saveLibrary")
	public String saveLibrary(CCkinfo cCkinfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cCkinfo)){//出库录单保存信息
			return form(cCkinfo, model);
		}
		cCkinfoService.outOfTheLibrary(cCkinfo,"4","1");
		addMessage(redirectAttributes, "出库录单成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cCkinfo/?repage";
	}

	@RequiresPermissions("ck:cCkinfo:view")
	@RequestMapping(value = "other")
	public String other(CCkinfo cCkinfo, Model model) {//其他出库
		model.addAttribute("houseList", cHouseService.findList(new CHouse()));
		model.addAttribute("goodsList", cGoodsService.findList(new CGoods()));
		model.addAttribute("cCkinfo", cCkinfo);
		return "modules/ck/cCkInfoOther";
	}

	@RequiresPermissions("ck:cCkinfo:edit")
	@RequestMapping(value = "saveOther")
	public String saveOther(CCkinfo cCkinfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cCkinfo)){//其他出库保存信息
			return form(cCkinfo, model);
		}
		cCkinfoService.outOfTheLibrary(cCkinfo,"3","1");
		addMessage(redirectAttributes, "其他出库成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cCkinfo/?repage";
	}

	@RequiresPermissions("ck:cCkinfo:view")
	@RequestMapping(value = "scrapped")
	public String scrapped(CCkinfo cCkinfo, Model model) {//报废录单
		model.addAttribute("houseList", cHouseService.findList(new CHouse()));
		model.addAttribute("goodsList", cGoodsService.findList(new CGoods()));
		model.addAttribute("cCkinfo", cCkinfo);
		return "modules/ck/cCkInfoScrapped";
	}

	@RequiresPermissions("ck:cCkinfo:edit")
	@RequestMapping(value = "saveScrapped")
	public String saveScrapped(CCkinfo cCkinfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cCkinfo)){//报废录单保存信息
			return form(cCkinfo, model);
		}
		cCkinfoService.outOfTheLibrary(cCkinfo,"2","1");
		addMessage(redirectAttributes, "报废录单成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cCkinfo/?repage";
	}

	@RequiresPermissions("ck:cCkinfo:view")
	@RequestMapping(value = "returnOfGoods")
	public String returnOfGoods(CCkinfo cCkinfo, Model model) {//退货录单
		model.addAttribute("supplierList", cSupplierService.findList(new CSupplier()));
		model.addAttribute("houseList", cHouseService.findList(new CHouse()));
		model.addAttribute("goodsList", cGoodsService.findList(new CGoods()));
		model.addAttribute("cCkinfo", cCkinfo);
		return "modules/ck/cCkInfoReturn";
	}

	@RequiresPermissions("ck:cCkinfo:edit")
	@RequestMapping(value = "saveReturnOfGoods")
	public String saveReturnOfGoods(CCkinfo cCkinfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cCkinfo)){//退货录单保存信息
			return form(cCkinfo, model);
		}
		cCkinfoService.outOfTheLibrary(cCkinfo,"1","1");
		addMessage(redirectAttributes, "退货录单成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cCkinfo/?repage";
	}

	@RequiresPermissions("ck:cCkinfo:edit")
	@RequestMapping(value = "save")
	public String save(CCkinfo cCkinfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cCkinfo)){
			return form(cCkinfo, model);
		}
//		cCkinfoService.save(cCkinfo);
		addMessage(redirectAttributes, "保存出库信息成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cCkinfo/?repage";
	}
	
	@RequiresPermissions("ck:cCkinfo:edit")
	@RequestMapping(value = "delete")
	public String delete(CCkinfo cCkinfo, RedirectAttributes redirectAttributes) {
//		cCkinfoService.delete(cCkinfo);
		addMessage(redirectAttributes, "删除出库信息成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cCkinfo/?repage";
	}
//客户订单
	@RequiresPermissions("ck:cCkinfoInquire:view")
	@RequestMapping(value = "listInquire")
	public String listInquire(CCkinfo cCkinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<CCkinfo> list = cCkinfoService.selectList("2,3,4,9",new CCkinfo());
		model.addAttribute("supplierList", cSupplierService.findList(new CSupplier()));
		model.addAttribute("houseList", cHouseService.findList(new CHouse()));
		model.addAttribute("goodsList", cGoodsService.findList(new CGoods()));
		model.addAttribute("storeList", cStoreService.findList(new CStore()));
		model.addAttribute("cCkinfo", cCkinfo);
		model.addAttribute("list", list);
		return "modules/report/cCkinfoInquireList";
	}
	/**
	 * 销售单查询state
	 * @param cCkinfo
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */

	@RequiresPermissions("ck:cCkinfoInquire:view")
	@RequestMapping(value = "xsInquire")
	public String xsInquire(CCkinfo cCkinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CCkinfo> page = cCkinfoService.findPage(new Page<CCkinfo>(request, response), cCkinfo);
		model.addAttribute("supplierList", cSupplierService.findList(new CSupplier()));
		model.addAttribute("houseList", cHouseService.findList(new CHouse()));
		model.addAttribute("goodsList", cGoodsService.findList(new CGoods()));
		model.addAttribute("storeList", cStoreService.findList(new CStore()));
		model.addAttribute("cCkinfo", cCkinfo);
		model.addAttribute("page", page);
		return "modules/report/cCkinfoxsInquireList";
	}



	/**
	 * 出库报表显示
	 * @param cCkinfo
	 * @param type
	 * @param model
	 * @return
	 */
//	@RequiresPermissions("ck:cRkinfoReport:view")
	@RequestMapping(value = "rkReport")//报表
	public String rkReport(CCkinfo cCkinfo, String type, Model model ) {
	//	cCkinfo.setState("1,2,3,4");
		List<CCkinfo> list = new ArrayList<CCkinfo>();
			if (StringUtils.isNotBlank(type) && "2".equals(type)
					) {
				list = cCkinfoService.selectList("1,2,3,4",new CCkinfo());
			} else if (StringUtils.isNotBlank(type) && "3".equals(type)) {
				list = cCkinfoService.selectList("1,2,3,4",new CCkinfo());
			} else if (StringUtils.isNotBlank(type) && "4".equals(type)) {
				list = cCkinfoService.storeList("1,2,3,4",new CCkinfo());
			} else if (StringUtils.isNotBlank(type) && "5".equals(type)) {
				list = cCkinfoService.storeList("1,2,3,4",new CCkinfo());
			}else if (StringUtils.isNotBlank(type) && "6".equals(type)) {
				list = cCkinfoService.storeList("1,2,3,4",new CCkinfo());
			}else if (StringUtils.isNotBlank(type) && "7".equals(type)) {
				list = cCkinfoService.bandsList("1,2,3,4",new CCkinfo());
			} else if (StringUtils.isNotBlank(type) && "8".equals(type)) {
				list = cCkinfoService.bandsList("1,2,3,4",new CCkinfo());
			}else {
				list = cCkinfoService.selectList("1,2,3,4",new CCkinfo());
			}
		model.addAttribute("goodsList", cGoodsService.findList(new CGoods()));
		model.addAttribute("houseList", cHouseService.findList(new CHouse()));
		model.addAttribute("cCkinfo", cCkinfo);
		model.addAttribute("type", type);
		model.addAttribute("list", list);
		return "modules/report/cCkinfoReportList";
	}
	//退货单查询
	@RequiresPermissions("ck:cCkinfoInquire:view")
	@RequestMapping(value = "listInquiret")
	public String listInquiret(CCkinfo cCkinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<CCkinfo> list = cCkinfoService.selectList("1",new CCkinfo());
		model.addAttribute("supplierList", cSupplierService.findList(new CSupplier()));
		model.addAttribute("houseList", cHouseService.findList(new CHouse()));
		model.addAttribute("goodsList", cGoodsService.findList(new CGoods()));
		model.addAttribute("storeList", cStoreService.findList(new CStore()));
		model.addAttribute("cCkinfo", cCkinfo);
		model.addAttribute("list", list);
		return "modules/report/cCkinfothList";
	}
	//报废单查询
	@RequiresPermissions("ck:cCkinfoInquire:view")
	@RequestMapping(value = "bfInquiret")
	public String bfInquiret(CCkinfo cCkinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<CCkinfo> list = cCkinfoService.selectList("2",new CCkinfo());
		model.addAttribute("supplierList", cSupplierService.findList(new CSupplier()));
		model.addAttribute("houseList", cHouseService.findList(new CHouse()));
		model.addAttribute("goodsList", cGoodsService.findList(new CGoods()));
		model.addAttribute("storeList", cStoreService.findList(new CStore()));
		model.addAttribute("cCkinfo", cCkinfo);
		model.addAttribute("list", list);
		return "modules/report/cCkinfobfList";
	}
}