/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.cw.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tlkzzz.jeesite.modules.ck.entity.CStore;
import com.tlkzzz.jeesite.modules.ck.entity.CSupplier;
import com.tlkzzz.jeesite.modules.cw.entity.FFixedAssets;
import com.tlkzzz.jeesite.modules.cw.service.FFixedAssetsService;
import com.tlkzzz.jeesite.modules.sys.entity.User;
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
import com.tlkzzz.jeesite.modules.cw.entity.FFixedAssetsCgbm;
import com.tlkzzz.jeesite.modules.cw.service.FFixedAssetsCgbmService;

import java.util.List;

/**
 * 固定资产采购变卖Controller
 * @author xrc
 * @version 2017-04-05
 */
@Controller
@RequestMapping(value = "${adminPath}/cw/fFixedAssetsCgbm")
public class FFixedAssetsCgbmController extends BaseController {

	@Autowired
	private FFixedAssetsCgbmService fFixedAssetsCgbmService;
	@Autowired
	private FFixedAssetsService fFixedAssetsService;
	
	@ModelAttribute
	public FFixedAssetsCgbm get(@RequestParam(required=false) String id) {
		FFixedAssetsCgbm entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = fFixedAssetsCgbmService.get(id);
		}
		if (entity == null){
			entity = new FFixedAssetsCgbm();
		}
		return entity;
	}
	
	@RequiresPermissions("cw:fFixedAssetsCgbm:view")
	@RequestMapping(value = {"list", ""})
	public String list(FFixedAssetsCgbm fFixedAssetsCgbm, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FFixedAssetsCgbm> page = fFixedAssetsCgbmService.findPage(new Page<FFixedAssetsCgbm>(request, response), fFixedAssetsCgbm);
		model.addAttribute("page", page);
		model.addAttribute("fFixedAssetsCgbm",fFixedAssetsCgbm);
		model.addAttribute("FFixedAssetslist", fFixedAssetsService.findList(new FFixedAssets()));
		return "modules/cw/fFixedAssetsCgbmList";
	}

	@RequiresPermissions("cw:fFixedAssetsCgbm:view")
	@RequestMapping(value = "form")
	public String form(FFixedAssetsCgbm fFixedAssetsCgbm, Model model) {
		model.addAttribute("fFixedAssetsCgbm", fFixedAssetsCgbm);
	    model.addAttribute("FFixedAssetslist", fFixedAssetsService.findList(new FFixedAssets()));
		return "modules/cw/fFixedAssetsCgbmForm";
	}

	@RequiresPermissions("cw:fFixedAssetsCgbm:edit")
	@RequestMapping(value = "save")
	public String save(FFixedAssetsCgbm fFixedAssetsCgbm, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, fFixedAssetsCgbm)){
			return form(fFixedAssetsCgbm, model);
		}
		if(fFixedAssetsCgbm.getApprovalStatus()==null){
			fFixedAssetsCgbm.setApprovalStatus("0");
		}
		fFixedAssetsCgbmService.save(fFixedAssetsCgbm);
		addMessage(redirectAttributes, "保存固定资产采购变卖成功");
		return "redirect:"+Global.getAdminPath()+"/cw/fFixedAssetsCgbm/?repage";
	}

	@ResponseBody
	@RequestMapping(value = "shenhe")
	public String shenhe(FFixedAssetsCgbm fFixedAssetsCgbm) {
		String retStr = "false";
		if (fFixedAssetsCgbm != null && !fFixedAssetsCgbm.getIsNewRecord()) {
			fFixedAssetsCgbm.setApprovalStatus("1");
			//获取当前登入者
			String a=UserUtils.getUser().getName();
			fFixedAssetsCgbm.setAuditor(UserUtils.getUser().getName());
			fFixedAssetsCgbmService.save(fFixedAssetsCgbm);
			retStr = "true";
		}
		return retStr;
	}

	
	@RequiresPermissions("cw:fFixedAssetsCgbm:edit")
	@RequestMapping(value = "delete")
	public String delete(FFixedAssetsCgbm fFixedAssetsCgbm, RedirectAttributes redirectAttributes) {
		fFixedAssetsCgbmService.delete(fFixedAssetsCgbm);
		addMessage(redirectAttributes, "删除固定资产采购变卖成功");
		return "redirect:"+Global.getAdminPath()+"/cw/fFixedAssetsCgbm/?repage";
	}

}