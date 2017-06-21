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
import com.tlkzzz.jeesite.modules.ck.entity.CHouseCheck;
import com.tlkzzz.jeesite.modules.ck.service.CHouseCheckService;

import java.util.List;

/**
 * 仓库总盘点Controller
 * @author xrc
 * @version 2017-04-12
 */
@Controller
@RequestMapping(value = "${adminPath}/ck/cHouseCheck")
public class CHouseCheckController extends BaseController {

	@Autowired
	private CHouseCheckService cHouseCheckService;
	@Autowired
	private CHouseCheckInventoryService checkInventoryService;
	@Autowired
	private CHouseService cHouseService;
	
	@ModelAttribute
	public CHouseCheck get(@RequestParam(required=false) String id) {
		CHouseCheck entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cHouseCheckService.get(id);
		}
		if (entity == null){
			entity = new CHouseCheck();
		}
		return entity;
	}
	
	@RequiresPermissions("ck:cHouseCheck:view")
	@RequestMapping(value = {"list", ""})
	public String list(CHouseCheck cHouseCheck, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CHouseCheck> page = cHouseCheckService.findPage(new Page<CHouseCheck>(request, response), cHouseCheck); 
		model.addAttribute("cHouseCheck", cHouseCheck);
		model.addAttribute("houseList", cHouseService.findList(new CHouse()));
		model.addAttribute("page", page);
		return "modules/ck/cHouseCheckList";
	}

	@RequiresPermissions("ck:cHouseCheck:view")
	@RequestMapping(value = "form")
	public String form(CHouseCheck cHouseCheck, Model model) {
		model.addAttribute("houseList",cHouseService.findList(new CHouse()));
		model.addAttribute("cHouseCheck", cHouseCheck);
		return "modules/ck/cHouseCheckForm";
	}

	@RequiresPermissions("ck:cHouseCheck:edit")
	@RequestMapping(value = "save")
	public String save(CHouseCheck cHouseCheck, Model model, RedirectAttributes redirectAttributes) {
		CHouseCheckInventory checkInventory = new CHouseCheckInventory();
		checkInventory.setHouse(cHouseCheck.getHouse());
		List<CHouseCheckInventory> inventoryList = checkInventoryService.getByHouse(checkInventory);
		if (!beanValidator(model, cHouseCheck)||inventoryList.size()==0){
			model.addAttribute("message","未发现抄帐记录，请抄帐后再进行盘点");
			return form(cHouseCheck, model);
		}
		List<CHgoods> cHgoodsList = checkInventoryService.JsonToList(checkInventory);
		cHouseCheckService.saveByInventory(cHouseCheck, cHgoodsList);
		addMessage(redirectAttributes, "保存仓库总盘点成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cHouseCheck/?repage";
	}
	
	@RequiresPermissions("ck:cHouseCheck:edit")
	@RequestMapping(value = "delete")
	public String delete(CHouseCheck cHouseCheck, RedirectAttributes redirectAttributes) {
		cHouseCheckService.delete(cHouseCheck);
		addMessage(redirectAttributes, "删除仓库总盘点成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cHouseCheck/?repage";
	}

	/**
	 * 通过仓库ID查询
	 * @param cHouseCheck
	 * @return true:允许操作，false:不允许操作
	 */
	@ResponseBody
	@RequestMapping(value = "checkHouse")
	public String checkHouse(CHouseCheck cHouseCheck){
		String retStr = "true";
		if(cHouseCheck!=null&&cHouseCheck.getHouse()!=null){
			cHouseCheck = cHouseCheckService.getByHouse(cHouseCheck);
			if(cHouseCheck!=null)retStr = "false";
		}
		return retStr;
	}

}