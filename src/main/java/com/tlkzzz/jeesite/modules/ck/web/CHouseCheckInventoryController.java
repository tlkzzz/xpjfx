/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tlkzzz.jeesite.modules.ck.entity.*;
import com.tlkzzz.jeesite.modules.ck.service.CGclassService;
import com.tlkzzz.jeesite.modules.ck.service.CHgoodsService;
import com.tlkzzz.jeesite.modules.ck.service.CHouseService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
import com.tlkzzz.jeesite.modules.ck.service.CHouseCheckInventoryService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 盘点抄帐Controller
 * @author xrc
 * @version 2017-04-12
 */
@Controller
@RequestMapping(value = "${adminPath}/ck/cHouseCheckInventory")
public class CHouseCheckInventoryController extends BaseController {

	@Autowired
	private CHouseCheckInventoryService cHouseCheckInventoryService;
	@Autowired
	private CHgoodsService cHgoodsService;
	@Autowired
	private CHouseService cHouseService;
	@Autowired
	private CGclassService cGclassService;
	
	@ModelAttribute
	public CHouseCheckInventory get(@RequestParam(required=false) String id) {
		CHouseCheckInventory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cHouseCheckInventoryService.get(id);
		}
		if (entity == null){
			entity = new CHouseCheckInventory();
		}
		return entity;
	}
	
	@RequiresPermissions("ck:cHouseCheckInventory:view")
	@RequestMapping(value = {"list", ""})
	public String list(CHouseCheckInventory cHouseCheckInventory, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CHouseCheckInventory> page = cHouseCheckInventoryService.findPage(new Page<CHouseCheckInventory>(request, response), cHouseCheckInventory); 
		model.addAttribute("cHouseCheckInventory", cHouseCheckInventory);
		model.addAttribute("houseList", cHouseService.findList(new CHouse()));
		model.addAttribute("page", page);
		return "modules/ck/cHouseCheckInventoryList";
	}

	@RequiresPermissions("ck:cHouseCheckInventory:view")
	@RequestMapping(value = "form")
	public String form(CHouseCheckInventory cHouseCheckInventory, Model model) {
		if(cHouseCheckInventory.getCheckDate()==null)cHouseCheckInventory.setCheckDate(new Date());
		model.addAttribute("houseList", cHouseService.findList(new CHouse()));
		model.addAttribute("gclassList", cGclassService.findList(new CGclass()));
		model.addAttribute("cHouseCheckInventory", cHouseCheckInventory);
		return "modules/ck/cHouseCheckInventoryForm";
	}

	@RequiresPermissions("ck:cHouseCheckInventory:view")
	@RequestMapping(value = "inventoryList")
	public String inventoryList(CHouseCheckInventory cHouseCheckInventory, Model model) {
		List<CHgoods> cHgoodsList = cHouseCheckInventoryService.JsonToList(cHouseCheckInventory);
		model.addAttribute("cHgoodsList", cHgoodsList);
		return "modules/ck/cHouseCheckHgoodsList";//这里打开抄帐列表页面；
	}

	@RequiresPermissions("ck:cHouseCheckInventory:edit")
	@RequestMapping(value = "save")
	public String save(CHouseCheckInventory cHouseCheckInventory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cHouseCheckInventory)){
			return form(cHouseCheckInventory, model);
		}
		CHgoods hgoods = new CHgoods();
		hgoods.setHouse(cHouseCheckInventory.getHouse());
		hgoods.setGoods(new CGoods());
		hgoods.getGoods().setGclass(cHouseCheckInventory.getGclass());
		List<CHgoods> cHgoodsList = cHgoodsService.findList(hgoods);
		if(StringUtils.isBlank(cHouseCheckInventory.getState()))cHouseCheckInventory.setState("0");
		cHouseCheckInventoryService.save(cHouseCheckInventory,cHgoodsList);
		addMessage(redirectAttributes, "保存盘点抄帐成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cHouseCheckInventory/?repage";
	}
	
	@RequiresPermissions("ck:cHouseCheckInventory:edit")
	@RequestMapping(value = "delete")
	public String delete(CHouseCheckInventory cHouseCheckInventory, RedirectAttributes redirectAttributes) {
		cHouseCheckInventoryService.delete(cHouseCheckInventory);
		addMessage(redirectAttributes, "删除盘点抄帐成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cHouseCheckInventory/?repage";
	}

	/**
	 * 通过仓库ID查询
	 * @param cHouseCheckInventory
	 * @return true:允许操作，false:不允许操作
	 */
	@ResponseBody
	@RequestMapping(value = "checkHouse")
	public String checkHouse(CHouseCheckInventory cHouseCheckInventory){
		String retStr = "true";
		if(cHouseCheckInventory!=null&&cHouseCheckInventory.getHouse()!=null){
			List<CHouseCheckInventory> inventoryList = cHouseCheckInventoryService.getByHouse(cHouseCheckInventory);
			if(inventoryList.size()>0)retStr = "false";
		}
		return retStr;
	}

}