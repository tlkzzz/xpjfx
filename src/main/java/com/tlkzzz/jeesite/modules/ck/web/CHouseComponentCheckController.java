/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tlkzzz.jeesite.modules.ck.entity.CHgoods;
import com.tlkzzz.jeesite.modules.ck.entity.CHouse;
import com.tlkzzz.jeesite.modules.ck.entity.CHouseCheckInventory;
import com.tlkzzz.jeesite.modules.ck.service.CHouseCheckInventoryService;
import com.tlkzzz.jeesite.modules.ck.service.CHouseService;
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
import com.tlkzzz.jeesite.modules.ck.entity.CHouseComponentCheck;
import com.tlkzzz.jeesite.modules.ck.service.CHouseComponentCheckService;

import java.util.List;

/**
 * 分量盘点Controller
 * @author xrc
 * @version 2017-04-12
 */
@Controller
@RequestMapping(value = "${adminPath}/ck/cHouseComponentCheck")
public class CHouseComponentCheckController extends BaseController {

	@Autowired
	private CHouseComponentCheckService cHouseComponentCheckService;
	@Autowired
	private CHouseService cHouseService;
	@Autowired
	private CHouseCheckInventoryService checkInventoryService;
	
	@ModelAttribute
	public CHouseComponentCheck get(@RequestParam(required=false) String id) {
		CHouseComponentCheck entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cHouseComponentCheckService.get(id);
		}
		if (entity == null){
			entity = new CHouseComponentCheck();
		}
		return entity;
	}
	
	@RequiresPermissions("ck:cHouseComponentCheck:view")
	@RequestMapping(value = {"list", ""})
	public String list(CHouseComponentCheck cHouseComponentCheck, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CHouseComponentCheck> page = cHouseComponentCheckService.findPage(new Page<CHouseComponentCheck>(request, response), cHouseComponentCheck); 
		model.addAttribute("cHouseComponentCheck", cHouseComponentCheck);
		model.addAttribute("page", page);
		return "modules/ck/cHouseComponentCheckList";
	}

	@RequiresPermissions("ck:cHouseComponentCheck:view")
	@RequestMapping(value = "form")
	public String form(CHouseComponentCheck cHouseComponentCheck, Model model) {
		model.addAttribute("houseList", cHouseService.findList(new CHouse()));
		model.addAttribute("cHouseComponentCheck", cHouseComponentCheck);
		return "modules/ck/cHouseComponentCheckForm";
	}

	@RequiresPermissions("ck:cHouseComponentCheck:edit")
	@RequestMapping(value = "save")
	public String save(CHouseComponentCheck cHouseComponentCheck, Model model, RedirectAttributes redirectAttributes) {
		CHouseCheckInventory checkInventory = new CHouseCheckInventory();
		checkInventory.setHouse(cHouseComponentCheck.getHouse());
		List<CHouseCheckInventory> inventoryList = checkInventoryService.getByHouse(checkInventory);
		if (!beanValidator(model, cHouseComponentCheck)||inventoryList.size()==0){
			model.addAttribute("message","请先进行抄帐后再进行盘点");
			return form(cHouseComponentCheck, model);
		}
		for(CHouseCheckInventory ci:inventoryList) {
			List<CHgoods> cHgoodss = checkInventoryService.JsonToList(ci);
			cHouseComponentCheckService.saveBycHgoodsList(cHouseComponentCheck, cHgoodss);
		}
		addMessage(redirectAttributes, "保存分量盘点成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cHouseComponentCheck/?repage";
	}
	
	@RequiresPermissions("ck:cHouseComponentCheck:edit")
	@RequestMapping(value = "delete")
	public String delete(CHouseComponentCheck cHouseComponentCheck, RedirectAttributes redirectAttributes) {
		cHouseComponentCheckService.delete(cHouseComponentCheck);
		addMessage(redirectAttributes, "删除分量盘点成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cHouseComponentCheck/?repage";
	}

	/**
	 * 通过仓库ID查询
	 * @param cHouseComponentCheck
	 * @return true:允许操作，false:不允许操作
	 */
	@ResponseBody
	@RequestMapping(value = "checkHouse")
	public String checkHouse(CHouseComponentCheck cHouseComponentCheck){
		String retStr = "true";
		if(cHouseComponentCheck!=null&&cHouseComponentCheck.getHouse()!=null){
			if(cHouseComponentCheckService.getByHouse(cHouseComponentCheck).size()>0){
				retStr = "false";
			}
		}
		return retStr;
	}

}