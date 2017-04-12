/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.cw.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tlkzzz.jeesite.modules.cw.entity.FAccount;
import com.tlkzzz.jeesite.modules.cw.entity.FPayment;
import com.tlkzzz.jeesite.modules.cw.entity.FReceipt;
import com.tlkzzz.jeesite.modules.cw.service.FAccountService;
import com.tlkzzz.jeesite.modules.cw.service.FPaymentService;
import com.tlkzzz.jeesite.modules.cw.service.FReceiptService;
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
import com.tlkzzz.jeesite.modules.cw.entity.FTransferAccount;
import com.tlkzzz.jeesite.modules.cw.service.FTransferAccountService;

/**
 * 转账调账Controller
 * @author xrc
 * @version 2017-04-05
 */
@Controller
@RequestMapping(value = "${adminPath}/cw/fTransferAccount")
public class FTransferAccountController extends BaseController {

	@Autowired
	private FTransferAccountService fTransferAccountService;
	@Autowired
	private FReceiptService fReceiptService;
	@Autowired
	private FPaymentService fPaymentService;
	@Autowired
	private FAccountService fAccountService;

	@ModelAttribute
	public FTransferAccount get(@RequestParam(required=false) String id) {
		FTransferAccount entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = fTransferAccountService.get(id);
		}
		if (entity == null){
			entity = new FTransferAccount();
		}
		return entity;
	}
	
	@RequiresPermissions("cw:fTransferAccount:view")
	@RequestMapping(value = {"list", ""})
	public String list(FTransferAccount fTransferAccount, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(StringUtils.isNotBlank(fTransferAccount.getTransferType())){
			Page<FTransferAccount> page = fTransferAccountService.findPage(new Page<FTransferAccount>(request, response), fTransferAccount);
			model.addAttribute("page", page);
			model.addAttribute("fTransferAccount", fTransferAccount);
			return "modules/cw/fTransferAccountList";
		}else {
			return "error/400";
		}
	}

	@RequiresPermissions("cw:fTransferAccount:view")
	@RequestMapping(value = "form")
	public String form(FTransferAccount fTransferAccount, Model model) {
		model.addAttribute("fTransferAccount", fTransferAccount);
		return "modules/cw/fTransferAccountForm";
	}

	/**
	 * shizx 2017-04-10
	 * 应付款增加
	 * */
	@RequiresPermissions("cw:fTransferAccount:view")
	@RequestMapping(value = "paymentAddForm")
	public String paymentAddForm(FTransferAccount fTransferAccount, Model model) {
		model.addAttribute("orderIdList", fPaymentService.findList(new FPayment()));
		model.addAttribute("fTransferAccount", fTransferAccount);
		return "modules/cw/paymentAddForm";
	}
	/**
	 * shizx 2017-04-10
	 * 应付款减少
	 * */
	@RequiresPermissions("cw:fTransferAccount:view")
	@RequestMapping(value = "paymentReduceForm")
	public String paymentReduceForm(FTransferAccount fTransferAccount, Model model) {
		model.addAttribute("orderIdList", fPaymentService.findList(new FPayment()));
		model.addAttribute("fTransferAccount", fTransferAccount);
		return "modules/cw/paymentReduceForm";
	}
	/**
	 * shizx 2017-04-10
	 * 资金增加
	 * */
	@RequiresPermissions("cw:fTransferAccount:view")
	@RequestMapping(value = "capitalAddForm")
	public String capitalAddForm(FTransferAccount fTransferAccount, Model model) {
		model.addAttribute("IDcarddList", fAccountService.findList(new FAccount()));
		model.addAttribute("fTransferAccount", fTransferAccount);
		return "modules/cw/capitalAddForm";
	}
	/**
	 * shizx 2017-04-10
	 * 资金减少
	 * */
    @RequiresPermissions("cw:fTransferAccount:view")
    @RequestMapping(value = "capitalReduceForm")
    public String capitalReduceForm(FTransferAccount fTransferAccount, Model model) {
        model.addAttribute("IDcarddList", fAccountService.findList(new FAccount()));
        model.addAttribute("fTransferAccount", fTransferAccount);
        return "modules/cw/capitalReduceForm";
    }

	/**
	 * 应付款增加减少页面（通过transferType参数控制）
	 * @param transferAccount
	 * @param model
	 * @return
	 */
	@RequiresPermissions("cw:fTransferAccount:view")
	@RequestMapping(value = "receiptForm")
	public String receiptForm(FTransferAccount transferAccount, Model model){
		if(StringUtils.isNotBlank(transferAccount.getTransferType())) {
			model.addAttribute("fTransferAccount", transferAccount);
			model.addAttribute("orderList", fReceiptService.findList(new FReceipt()));
			model.addAttribute("accountList", fAccountService.findList(new FAccount()));
			return "modules/cw/fTransferReceiptForm";
		}else {
			return "error/400";
		}
	}

	/**
	 * 应收款增加减少保存
	 * @param fTransferAccount
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("cw:fTransferAccount:edit")
	@RequestMapping(value = "receiptSave")
	public String receiptSave(FTransferAccount fTransferAccount, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fTransferAccount)){
			return receiptForm(fTransferAccount, model);
		}
		fTransferAccountService.save(fTransferAccount);
		addMessage(redirectAttributes, "保存转账调账成功");
		return "redirect:"+Global.getAdminPath()+"/cw/fTransferAccount/?repage&transferType="+fTransferAccount.getTransferType();
	}

	/**
	 * 转账单银行账户内部转账
	 * @param transferAccount
	 * @param model
	 * @return
	 */
	@RequiresPermissions("cw:fTransferAccount:view")
	@RequestMapping(value = "transferForm")
	public String transferForm(FTransferAccount transferAccount, Model model){
		transferAccount.setTransferType("0");
		model.addAttribute("fTransferAccount", transferAccount);
		model.addAttribute("accountList", fAccountService.findList(new FAccount()));
		return "modules/cw/fTransferReceiptForm";
	}

	/**
	 * 银行账户转账保存
	 * @param fTransferAccount
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("cw:fTransferAccount:edit")
	@RequestMapping(value = "transferSave")
	public String transferSave(FTransferAccount fTransferAccount, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fTransferAccount)){
			return transferForm(fTransferAccount, model);
		}
		FAccount outAccount = fAccountService.get(fTransferAccount.getOutAccount());
		FAccount inAccount = fAccountService.get(fTransferAccount.getInAccount());
		if(StringUtils.isBlank(inAccount.getAccountBalance()))inAccount.setAccountBalance("0");
		double outBalance = Double.parseDouble(outAccount.getAccountBalance());
		double inBalance = Double.parseDouble(inAccount.getAccountBalance());
		double travelMoney = Double.parseDouble(fTransferAccount.getTransMoney());
		if(outBalance>travelMoney) {
			fTransferAccountService.save(fTransferAccount);
			outAccount.setAccountBalance(String.valueOf(outBalance-travelMoney));
			inAccount.setAccountBalance(String.valueOf(inBalance+travelMoney));
			fAccountService.capitalHtje(outAccount);
			fAccountService.capitalHtje(inAccount);
			addMessage(redirectAttributes, "转账成功");
		}else {
			addMessage(redirectAttributes, "转账失败，转出账户余额不足！");
		}
		return "redirect:"+Global.getAdminPath()+"/cw/fTransferAccount/?repage&transferType="+fTransferAccount.getTransferType();
	}
	/**
	 * shizx 2017-04-10
	 * 应付款增加
	 * */
	@RequiresPermissions("cw:fTransferAccount:edit")
	@RequestMapping(value = "paymentAddSave")
	public String paymentAddSave(FTransferAccount fTransferAccount, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fTransferAccount)){
			return paymentAddForm(fTransferAccount, model);
		}
		fTransferAccount.setTransferType("3");  //页面及状态，3 应付款增加
		fTransferAccount.setApprovalStatus("0"); //审核状态
		fTransferAccountService.save(fTransferAccount);
		addMessage(redirectAttributes, "保存转账调账成功");
		return "redirect:"+Global.getAdminPath()+"/cw/fTransferAccount/?repage&transferType="+fTransferAccount.getTransferType();
	}

	/**
	 * shizx 2017-04-10
	 * 应付款增加
	 * */
	@RequiresPermissions("cw:fTransferAccount:edit")
	@RequestMapping(value = "paymentReduceSave")
	public String paymentReduceSave(FTransferAccount fTransferAccount, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fTransferAccount)){
			return paymentReduceForm(fTransferAccount, model);
		}
		fTransferAccount.setTransferType("4");  //页面及状态，4 应付款减少
		fTransferAccount.setApprovalStatus("0"); //审核状态
		fTransferAccountService.save(fTransferAccount);
		addMessage(redirectAttributes, "保存转账调账成功");
		return "redirect:"+Global.getAdminPath()+"/cw/fTransferAccount/?repage&transferType="+fTransferAccount.getTransferType();
	}

	/**
	 * shizx 2017-04-10
	 * 资金增加
	 * */
	@RequiresPermissions("cw:fTransferAccount:edit")
	@RequestMapping(value = "capitalAddSave")
	public String capitalAddSave(FTransferAccount fTransferAccount, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fTransferAccount)){
			return capitalAddForm(fTransferAccount, model);
		}
		fTransferAccount.setTransferType("5");     //页面及状态，5 资金增加
		fTransferAccount.setApprovalStatus("0");   //审核状态
		fTransferAccountService.save(fTransferAccount);
		addMessage(redirectAttributes, "保存转账调账成功");
		return "redirect:"+Global.getAdminPath()+"/cw/fTransferAccount/?repage&transferType="+fTransferAccount.getTransferType();
	}

	/**
	 * shizx 2017-04-10
	 * 资金减少
	 * */
	@RequiresPermissions("cw:fTransferAccount:edit")
	@RequestMapping(value = "capitalReduceSave")
	public String capitalReduceSave(FTransferAccount fTransferAccount, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fTransferAccount)){
			return capitalReduceForm(fTransferAccount, model);
		}
		fTransferAccount.setTransferType("6");//页面及状态，5 资金减少
		fTransferAccount.setApprovalStatus("0");//审核状态
		fTransferAccountService.save(fTransferAccount);
		addMessage(redirectAttributes, "保存转账调账成功");
		return "redirect:"+Global.getAdminPath()+"/cw/fTransferAccount/?repage&transferType="+fTransferAccount.getTransferType();
	}

	@RequiresPermissions("cw:fTransferAccount:edit")
	@RequestMapping(value = "delete")
	public String delete(FTransferAccount fTransferAccount, RedirectAttributes redirectAttributes) {
		fTransferAccountService.delete(fTransferAccount);
		addMessage(redirectAttributes, "删除转账调账成功");
		return "redirect:"+Global.getAdminPath()+"/cw/fTransferAccount/?repage&transferType="+fTransferAccount.getTransferType();
	}

	@ResponseBody
	@RequiresPermissions("user")
	@RequestMapping(value = "auditing")
	public String auditing(String id,String state) {
		String retStr = "false";
		if(StringUtils.isNotBlank(id)&&StringUtils.isNotBlank(state)) {
			FTransferAccount transferAccount = new FTransferAccount(id);
			transferAccount.setApprovalStatus(state);
			transferAccount.setAuditor(UserUtils.getUser());
			fTransferAccountService.updateApprovalState(transferAccount);
			transferAccount = fTransferAccountService.get(transferAccount);
			if(transferAccount!=null) {
				switch (Integer.parseInt(state)) {
					case 1: {
						FReceipt receipt = new FReceipt(transferAccount.getOrderId());
						receipt.setHtje(transferAccount.getTransMoney());
						fReceiptService.addHTJE(receipt);
						break;
					}
					case 2: {
						FReceipt receipt = new FReceipt(transferAccount.getOrderId());
						receipt.setHtje(transferAccount.getTransMoney());
						fReceiptService.minHTJE(receipt);
						break;
					}
					case 3: {//应付款增加
						FPayment fPayment = new FPayment(transferAccount.getOrderId());
						double money=Double.parseDouble(transferAccount.getTransMoney());
						double ht=Double.parseDouble(fPayment.getHtje());
						double htje=ht+money;
						fPayment.setHtje(Double.toString(htje));
						fPaymentService.paymentAddHtje(fPayment);
						break;
					}
					case 4: {//应付款减少
						FPayment fPayment = new FPayment(transferAccount.getOrderId());
						double money=Double.parseDouble(transferAccount.getTransMoney());
						double ht=Double.parseDouble(fPayment.getHtje());
						double htje=ht-money;
						fPayment.setHtje(Double.toString(htje));
						fPaymentService.paymentAddHtje(fPayment);
						break;
					}
					case 5: {//资金增加
						FAccount fAccount = new FAccount();
						double money=Double.parseDouble(transferAccount.getTransMoney());
						double ht=Double.parseDouble(fAccount.getAccountBalance());
						double htje=ht+money;
						fAccount.setAccountBalance(Double.toString(htje));
						fAccount.setBankCode(transferAccount.getInAccount());
						fAccountService.capitalHtje(fAccount);
						break;
					}
					case 6: {//资金减少
						FAccount fAccount = new FAccount();
						double money=Double.parseDouble(transferAccount.getTransMoney());
						double ht=Double.parseDouble(fAccount.getAccountBalance());
						double htje=ht-money;
						fAccount.setAccountBalance(Double.toString(htje));
						fAccount.setBankCode(transferAccount.getInAccount());
						fAccountService.capitalHtje(fAccount);
						break;
					}
				}
			}
			retStr = "true";
		}
		return retStr;
	}

}