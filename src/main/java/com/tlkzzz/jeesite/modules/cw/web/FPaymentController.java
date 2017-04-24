/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.cw.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tlkzzz.jeesite.modules.cw.entity.FReceipt;
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
import com.tlkzzz.jeesite.modules.cw.entity.FPayment;
import com.tlkzzz.jeesite.modules.cw.service.FPaymentService;

/**
 * 付款Controller
 * @author xrc
 * @version 2017-04-05
 */
@Controller
@RequestMapping(value = "${adminPath}/cw/fPayment")
public class FPaymentController extends BaseController {

	@Autowired
	private FPaymentService fPaymentService;
	
	@ModelAttribute
	public FPayment get(@RequestParam(required=false) String id) {
		FPayment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = fPaymentService.get(id);
		}
		if (entity == null){
			entity = new FPayment();
		}
		return entity;
	}
	
	@RequiresPermissions("cw:fPayment:view")
	@RequestMapping(value = {"list", ""})
	public String list(FPayment fPayment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FPayment> page = fPaymentService.findPage(new Page<FPayment>(request, response), fPayment); 
		model.addAttribute("page", page);
		model.addAttribute("fPayment", fPayment);
		return "modules/cw/fPaymentList";
	}

	@RequiresPermissions("cw:fPayment:view")
	@RequestMapping(value = "form")
	public String form(FPayment fPayment, Model model) {
		model.addAttribute("fPayment", fPayment);
		return "error/400" ;
	}

	/**
	 * 现金费用单
	 * @param fPayment
	 * @param model
	 * @return
	 */

	@RequiresPermissions("cw:fPayment:view")
	@RequestMapping(value = "xjform")
	public String xjform(FPayment fPayment, Model model) {//现金费用单
		model.addAttribute("fPayment", fPayment);
		return "modules/cw/fPaymentxjForm";
	}

	@RequiresPermissions("cw:fPayment:edit")
	@RequestMapping(value = "xjsave")
	public String xjsave(FPayment fPayment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fPayment)){//现金费用单保存信息
			return form(fPayment, model);
		}
		fPaymentService.outOfTheLibrary(fPayment,"6","0");
		addMessage(redirectAttributes, "现金费用单成功");
		return "redirect:"+Global.getAdminPath()+"/cw/fPayment/?repage";
	}

	/**
	 * 一般费用单
	 * @param fPayment
	 * @param model
	 * @return
	 */
	@RequiresPermissions("cw:fPayment:view")
	@RequestMapping(value = "ybform")
	public String ybform(FPayment fPayment, Model model) {//一般费用单
		model.addAttribute("fPayment", fPayment);
		return "modules/cw/fPaymentybForm";
	}

	@RequiresPermissions("cw:fPayment:edit")
	@RequestMapping(value = "ybsave")
	public String ybsave(FPayment fPayment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fPayment)){//一般费用单保存
			return form(fPayment, model);
		}
		fPaymentService.outOfTheLibrary(fPayment,"7","0");
		addMessage(redirectAttributes, "一般费用单成功");
		return "redirect:"+Global.getAdminPath()+"/cw/fPayment/?repage";
	}

	/**
	 * 其他费用单
	 * @param fPayment
	 * @param model
	 * @return
	 */
	@RequiresPermissions("cw:fPayment:view")
	@RequestMapping(value = "qtform")
	public String qtform(FPayment fPayment, Model model) {//其他费用单
		model.addAttribute("fPayment", fPayment);
		return "modules/cw/fPaymentqtForm";
	}

	@RequiresPermissions("cw:fPayment:edit")
	@RequestMapping(value = "qtsave")
	public String qtsave(FPayment fPayment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fPayment)){//其他费用单保存
			return form(fPayment, model);
		}
		fPaymentService.outOfTheLibrary(fPayment,"8","0");
		addMessage(redirectAttributes, "其他费用单成功");
		return "redirect:"+Global.getAdminPath()+"/cw/fPayment/?repage";
	}

	/**
	 * 审核
	 * @param fPayment
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "shenhe")
	public String shenhe(FPayment fPayment) {
		String retStr = "false";
		if (fPayment != null && !fPayment.getIsNewRecord()) {
			fPayment.setApprovalStatus("1");
			//获取当前登入者
			fPayment.setAuditor(UserUtils.getUser());
			fPaymentService.save(fPayment);
			retStr = "true";
		}
		return retStr;
	}
	@RequiresPermissions("cw:fPayment:edit")
	@RequestMapping(value = "delete")
	public String delete(FPayment fPayment, RedirectAttributes redirectAttributes) {
		fPaymentService.delete(fPayment);
		addMessage(redirectAttributes, "删除付款成功");
		return "redirect:"+Global.getAdminPath()+"/cw/fPayment/?repage";
	}

/**
 *
 * 销售退货单List
 * */
	@RequiresPermissions("cw:fPayment:view")
	@RequestMapping(value = {"returnGoodsList", ""})
	public String returnGoodsList(FPayment fPayment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FPayment> page = fPaymentService.findPage(new Page<FPayment>(request, response), fPayment);
		fPayment.setThstatus("0");
		model.addAttribute("fPaymentList", fPaymentService.findList(fPayment));
		model.addAttribute("page", page);
		return "modules/ck/returnGoodsList";
	}

}