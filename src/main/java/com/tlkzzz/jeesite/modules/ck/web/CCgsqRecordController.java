/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tlkzzz.jeesite.modules.ck.entity.CCgzbinfo;
import com.tlkzzz.jeesite.modules.ck.entity.CDdinfo;
import com.tlkzzz.jeesite.modules.ck.entity.CGoods;
import com.tlkzzz.jeesite.modules.ck.service.CCgzbinfoService;
import com.tlkzzz.jeesite.modules.ck.service.CGoodsService;
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
import com.tlkzzz.jeesite.modules.ck.entity.CCgsqRecord;
import com.tlkzzz.jeesite.modules.ck.service.CCgsqRecordService;

import java.util.List;

/**
 * 订单申请记录表Controller
 * @author xlc
 * @version 2017-06-28
 */
@Controller
@RequestMapping(value = "${adminPath}/ck/cCgsqRecord")
public class CCgsqRecordController extends BaseController {

	@Autowired
	private CCgsqRecordService cCgsqRecordService;
	@Autowired
	private CGoodsService cGoodsService;
	@Autowired
	private CCgzbinfoService cCgzbinfoService;
	
	@ModelAttribute
	public CCgsqRecord get(@RequestParam(required=false) String id) {
		CCgsqRecord entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cCgsqRecordService.get(id);
		}
		if (entity == null){
			entity = new CCgsqRecord();
		}
		return entity;
	}
	
	@RequiresPermissions("ck:cCgsqRecord:view")
	@RequestMapping(value = {"list", ""})
	public String list(CCgsqRecord cCgsqRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CCgsqRecord> page = cCgsqRecordService.findPage(new Page<CCgsqRecord>(request, response), cCgsqRecord);
		List<CCgzbinfo>  list= cCgzbinfoService.findList(new CCgzbinfo());
		model.addAttribute("page", page);
		model.addAttribute("cCgsqRecord",cCgsqRecord);
		model.addAttribute("goodsList", cGoodsService.findList(new CGoods()));
		return "modules/ck/cCgsqRecordList";
	}

	@RequiresPermissions("ck:cCgsqRecord:view")
	@RequestMapping(value = "form")
	public String form(CCgsqRecord cCgsqRecord, Model model) {
		model.addAttribute("cCgsqRecord", cCgsqRecord);
		model.addAttribute("goodsList", cGoodsService.findList(new CGoods()));
		return "modules/ck/cCgsqRecordForm";
	}

	@RequiresPermissions("ck:cCgsqRecord:edit")
	@RequestMapping(value = "save")
	public String save(CCgsqRecord cCgsqRecord, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cCgsqRecord)){
			return form(cCgsqRecord, model);
		}
		cCgsqRecordService.savelist(cCgsqRecord);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cCgsqRecord/?repage";
	}
	@RequiresPermissions("ck:cCgsqRecord:view")
	@ResponseBody
	@RequestMapping(value = "ldlist")
	public void ldlist( String goods, HttpServletResponse response) {
		if(StringUtils.isNotBlank(goods)){
			CGoods cGoods = cGoodsService.get(goods);
			renderString(response,cGoods);
		}else {
			renderString(response,false);
		}

	}
	@RequiresPermissions("ck:cCgsqRecord:edit")
	@RequestMapping(value = "delete")
	public String delete(CCgsqRecord cCgsqRecord, RedirectAttributes redirectAttributes) {
		cCgsqRecordService.delete(cCgsqRecord);
		addMessage(redirectAttributes, "删除订单申请记录表成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cCgsqRecord/?repage";
	}

}