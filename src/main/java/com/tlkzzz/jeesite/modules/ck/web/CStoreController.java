/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tlkzzz.jeesite.common.utils.DateUtils;
import com.tlkzzz.jeesite.common.utils.excel.ExportExcel;
import com.tlkzzz.jeesite.modules.ck.entity.CSclass;
import com.tlkzzz.jeesite.modules.ck.service.CSclassService;
import com.tlkzzz.jeesite.modules.sys.service.AreaService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tlkzzz.jeesite.common.config.Global;
import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.web.BaseController;
import com.tlkzzz.jeesite.common.utils.StringUtils;
import com.tlkzzz.jeesite.modules.ck.entity.CStore;
import com.tlkzzz.jeesite.modules.ck.service.CStoreService;

/**
 * 客户表Controller
 * @author xrc
 * @version 2017-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/ck/cStore")
public class CStoreController extends BaseController {

	@Autowired
	private CStoreService cStoreService;
	@Autowired
	private CSclassService sclassService;
	@Autowired
	private AreaService areaService;

	@ModelAttribute
	public CStore get(@RequestParam(required = false) String id) {
		CStore entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = cStoreService.get(id);
		}
		if (entity == null) {
			entity = new CStore();
		}
		return entity;
	}

	@RequiresPermissions("ck:cStore:view")
	@RequestMapping(value = "")
	public String index(Model model) {
		model.addAttribute("sClassList", sclassService.findList(new CSclass()));
		return "modules/ck/cStoreIndex";
	}

	@RequiresPermissions("ck:cStore:view")
	@RequestMapping(value = "list")
	public String list(CStore cStore, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CStore> page = cStoreService.findPage(new Page<CStore>(request, response), cStore);
		model.addAttribute("page", page);
		model.addAttribute("cStore", cStore);
		model.addAttribute("sClassList", sclassService.findList(new CSclass()));
		return "modules/ck/cStoreList";
	}

	@RequiresPermissions("ck:cStore:view")
	@RequestMapping(value = "form")
	public String form(CStore cStore, Model model) {
		model.addAttribute("cStore", cStore);
		model.addAttribute("sClassList", sclassService.findList(new CSclass()));
		return "modules/ck/cStoreForm";
	}

	@ResponseBody
	@RequiresPermissions("ck:cStore:edit")
	@RequestMapping(value = "auditingState")
	public String auditingState(CStore cStore) {
		String retStr = "false";
		if (cStore != null && !cStore.getIsNewRecord()) {
			cStore.setState("1");
			cStoreService.save(cStore);
			retStr = "true";
		}
		return retStr;
	}

	@RequiresPermissions("ck:cStore:edit")
	@RequestMapping(value = "save")
	public String save(CStore cStore, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cStore)) {
			return form(cStore, model);
		}
		if (StringUtils.isBlank(cStore.getState())) cStore.setState("0");
		cStoreService.save(cStore);
		addMessage(redirectAttributes, "保存客户表成功");
		return "redirect:" + Global.getAdminPath() + "/ck/cStore/list?repage";
	}

	@RequiresPermissions("ck:cStore:edit")
	@RequestMapping(value = "delete")
	public String delete(CStore cStore, RedirectAttributes redirectAttributes) {
		cStoreService.delete(cStore);
		addMessage(redirectAttributes, "删除客户表成功");
		return "redirect:" + Global.getAdminPath() + "/ck/cStore/list?repage";
	}

	/**
	 * 地图
	 * @return
	 */
	@RequestMapping(value = "baidumap")
	public String baidumap() {
		return "modules/ck/baidumap";
	}

	/**
	 * 导出客户数据
	 * @param cStore
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "exportFile", method = RequestMethod.POST)
	public String exportFile(CStore cStore, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "客户数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			Page<CStore> page = cStoreService.findUser(new Page<CStore>(request, response, -1), cStore);
			new ExportExcel("导出数据", CStore.class).setDataList(cStoreService.listHandle(cStoreService.findList(cStore))).write(response, fileName).dispose();
			//return null;lass).setDataList(tMdWsService.listHandle(tMdWsService.findList(tMdWs))).write(response, fileName).dispose();
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出用户失败！失败信息：" + e.getMessage());
			e.printStackTrace();
		}
		//return "redirect:" + bsPath + "/cm/tCmMsg/list1320?repage";
		return null;
	}
}