/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tlkzzz.jeesite.modules.ck.entity.CGoods;
import com.tlkzzz.jeesite.modules.ck.entity.CStore;
import com.tlkzzz.jeesite.modules.ck.entity.CSupplier;
import com.tlkzzz.jeesite.modules.ck.service.CGoodsService;
import com.tlkzzz.jeesite.modules.ck.service.CStoreService;
import com.tlkzzz.jeesite.modules.ck.service.CSupplierService;
import com.tlkzzz.jeesite.modules.sys.utils.UserUtils;
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
import com.tlkzzz.jeesite.modules.ck.entity.CShop;
import com.tlkzzz.jeesite.modules.ck.service.CShopService;

/**
 * 购物车Controller
 * @author xrc
 * @version 2017-03-23
 */
@Controller
@RequestMapping(value = "${adminPath}/ck/cShop")
public class CShopController extends BaseController {

	@Autowired
	private CShopService cShopService;
	@Autowired
	private CGoodsService cGoodsService;
	@Autowired
	private CStoreService cStoreService;
	@Autowired
	private CSupplierService cSupplierService;
	
	@ModelAttribute
	public CShop get(@RequestParam(required=false) String id) {
		CShop entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cShopService.get(id);
		}
		if (entity == null){
			entity = new CShop();
		}
		return entity;
	}
	
	@RequiresPermissions("ck:cCginfo:view")
	@RequestMapping(value = "cgAdd")
	public String list(CShop cShop, HttpServletRequest request, HttpServletResponse response, Model model) {
		String URL = "error/400";
		if(StringUtils.isBlank(cShop.getState())) {
			cShop.setState(UserUtils.getCache("RKCKSTATE").toString());
		}
		if(StringUtils.isNotBlank(cShop.getState())) {
			URL = "modules/ck/cShopList";
			int state = Integer.parseInt(cShop.getState());
			if(state<2){//0入库保留，1:采购入库，2：出库录单，3：其他出库，4：报废录单，5：退货录单
				model.addAttribute("urlParam", "cgList");
			}else{
				model.addAttribute("urlParam", "libraryList");
			}
			Page<CShop> page = cShopService.findPage(new Page<CShop>(request, response), cShop);
			model.addAttribute("cShop", cShop);
			model.addAttribute("page", page);
		}
		return URL;
	}

	@RequiresPermissions("ck:cCginfo:view")
	@RequestMapping(value = "form")
	public String form(CShop cShop, Model model) {
		if(StringUtils.isBlank(cShop.getState())) {
			cShop.setState(UserUtils.getCache("RKCKSTATE").toString());
		}
		model.addAttribute("goodsList", cGoodsService.findList(new CGoods()));
		model.addAttribute("cShop", cShop);
		return "modules/ck/cShopForm";
	}

	@RequiresPermissions("ck:cCginfo:edit")
	@ResponseBody
	@RequestMapping(value = "save")
	public String save(CShop cShop, Model model) {
		if("".equals(cShop.getId()))cShop.setId(null);
		if(StringUtils.isBlank(cShop.getState())) {
			cShop.setState(UserUtils.getCache("RKCKSTATE").toString());
		}
		if (!beanValidator(model, cShop)){
			return form(cShop, model);
		}
		cShopService.save(cShop);
		return "true";
	}
	
	@RequiresPermissions("ck:cCginfo:edit")
	@RequestMapping(value = "delete")
	public String delete(CShop cShop, RedirectAttributes redirectAttributes) {
		cShopService.delete(cShop);
		addMessage(redirectAttributes, "删除购物车成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cShop/cgAdd?repage&state="+cShop.getState();
	}

}