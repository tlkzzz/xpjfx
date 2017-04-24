/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.cw.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tlkzzz.jeesite.modules.ck.entity.CCkinfo;
import com.tlkzzz.jeesite.modules.ck.entity.CGoods;
import com.tlkzzz.jeesite.modules.ck.entity.CHouse;
import com.tlkzzz.jeesite.modules.ck.entity.CStore;
import com.tlkzzz.jeesite.modules.cw.entity.FFixedAssetsCgbm;
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
import com.tlkzzz.jeesite.modules.cw.entity.FReceipt;
import com.tlkzzz.jeesite.modules.cw.service.FReceiptService;

/**
 * 收款Controller
 * @author xrc
 * @version 2017-04-10
 */
@Controller
@RequestMapping(value = "${adminPath}/cw/fReceipt")
public class FReceiptController extends BaseController {

	@Autowired
	private FReceiptService fReceiptService;

	@ModelAttribute
	public FReceipt get(@RequestParam(required=false) String id) {
		FReceipt entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = fReceiptService.get(id);
		}
		if (entity == null){
			entity = new FReceipt();
		}
		return entity;
	}

	/**
	 * 现金费用list
	 * @param fReceipt
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */

	@RequiresPermissions("cw:fReceipt:view")
	@RequestMapping(value = {"list", ""})
	public String list(FReceipt fReceipt, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FReceipt> page = fReceiptService.findPage(new Page<FReceipt>(request, response), fReceipt);
		model.addAttribute("page", page);
		model.addAttribute("fReceipt", fReceipt);
		return "modules/cw/fReceiptList";
	}
	/**
	 * 现金费用单
	 * @param fReceipt
	 * @param model
	 * @return
	 */

	@RequiresPermissions("cw:fReceipt:view")
	@RequestMapping(value = "xjform")
	public String xjform(FReceipt fReceipt, Model model) {//现金费用单
		model.addAttribute("fReceipt", fReceipt);
		return "modules/cw/fReceiptxjForm";
	}

	@RequiresPermissions("cw:fReceipt:edit")
	@RequestMapping(value = "xjsave")
	public String xjsave(FReceipt fReceipt, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fReceipt)){//现金费用单保存信息
			return form(fReceipt, model);
		}
		fReceiptService.outOfTheLibrary(fReceipt,"6","0");
		addMessage(redirectAttributes, "现金费用单成功");
		return "redirect:"+Global.getAdminPath()+"/cw/fReceipt/?repage";
	}
	/**
	 * 一般费用单
	 * @param fReceipt
	 * @param model
	 * @return
	 */
	@RequiresPermissions("cw:fReceipt:view")
	@RequestMapping(value = "ybform")
	public String ybform(FReceipt fReceipt, Model model) {//一般费用单
		model.addAttribute("fReceipt", fReceipt);
		return "modules/cw/fReceiptybForm";
	}

	@RequiresPermissions("cw:fReceipt:edit")
	@RequestMapping(value = "ybsave")
	public String ybsave(FReceipt fReceipt, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fReceipt)){//一般费用单保存
			return form(fReceipt, model);
		}
		fReceiptService.outOfTheLibrary(fReceipt,"7","0");
		addMessage(redirectAttributes, "一般费用单成功");
		return "redirect:"+Global.getAdminPath()+"/cw/fReceipt/?repage";
	}
	@RequiresPermissions("cw:fReceipt:view")
	@RequestMapping(value = "form")
	public String form(FReceipt fReceipt, Model model) {
		model.addAttribute("fReceipt", fReceipt);
		return "error/400" ;
	}
	/**
	 * 其他费用单
	 * @param fReceipt
	 * @param model
	 * @return
	 */
	@RequiresPermissions("cw:fReceipt:view")
	@RequestMapping(value = "qtform")
	public String qtform(FReceipt fReceipt, Model model) {//其他费用单
		model.addAttribute("fReceipt", fReceipt);
		return "modules/cw/fReceiptqtForm";
	}

	@RequiresPermissions("cw:fReceipt:edit")
	@RequestMapping(value = "qtsave")
	public String qtsave(FReceipt fReceipt, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fReceipt)){//其他费用单保存
			return form(fReceipt, model);
		}
		fReceiptService.outOfTheLibrary(fReceipt,"8","0");
		addMessage(redirectAttributes, "其他费用单成功");
		return "redirect:"+Global.getAdminPath()+"/cw/fReceipt/?repage";
	}

	/**
	 * 审核
	 * @param fReceipt
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "shenhe")
	public String shenhe(FReceipt fReceipt) {
		String retStr = "false";
		if (fReceipt != null && !fReceipt.getIsNewRecord()) {
			fReceipt.setApprovalStatus("1");
			//获取当前登入者
			fReceipt.setAuditor(UserUtils.getUser());
			fReceiptService.save(fReceipt);
			retStr = "true";
		}
		return retStr;
	}

	@RequiresPermissions("cw:fReceipt:edit")
	@RequestMapping(value = "delete")
	public String delete(FReceipt fReceipt, RedirectAttributes redirectAttributes) {
		fReceiptService.delete(fReceipt);
		addMessage(redirectAttributes, "删除收款成功");
		String str = "";
		if(fReceipt.getReceiptType()==""){
			return  "error/404" ;
		}
		return "redirect:"+Global.getAdminPath()+"/cw/fReceipt/?repage";
	}

	@RequiresPermissions("cw:fReceipt:view")
	@RequestMapping(value = "GysReturn")
	public String GysReturn(FReceipt fReceipt, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FReceipt> page = fReceiptService.findPage(new Page<FReceipt>(request, response), fReceipt);
		model.addAttribute("page", page);
		model.addAttribute("fReceipt", fReceipt);
		return "modules/cw/GysReturn";
	}

}