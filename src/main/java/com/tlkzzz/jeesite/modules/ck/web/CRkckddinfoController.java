/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tlkzzz.jeesite.modules.ck.entity.CDdinfo;
import com.tlkzzz.jeesite.modules.ck.entity.CShop;
import com.tlkzzz.jeesite.modules.ck.service.CDdinfoService;
import com.tlkzzz.jeesite.modules.ck.service.CShopService;
import com.tlkzzz.jeesite.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tlkzzz.jeesite.common.config.Global;
import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.web.BaseController;
import com.tlkzzz.jeesite.common.utils.StringUtils;
import com.tlkzzz.jeesite.modules.ck.entity.CRkckddinfo;
import com.tlkzzz.jeesite.modules.ck.service.CRkckddinfoService;

import java.util.List;

/**
 * 总订单Controller
 * @author xr4
 * @version 2017-03-27
 */
@Controller
@RequestMapping(value = "${adminPath}/ck/cRkckddinfo")
public class CRkckddinfoController extends BaseController {

	@Autowired
	private CRkckddinfoService cRkckddinfoService;
	@Autowired
	private CDdinfoService cDdinfoService;
	@Autowired
	private CShopService cShopService;
	
	@ModelAttribute
	public CRkckddinfo get(@RequestParam(required=false) String id) {
		CRkckddinfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cRkckddinfoService.get(id);
		}
		if (entity == null){
			entity = new CRkckddinfo();
		}
		return entity;
	}
	
	/*@RequiresPermissions("ck:cRkckddinfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(CRkckddinfo cRkckddinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CRkckddinfo> page = cRkckddinfoService.findPage(new Page<CRkckddinfo>(request, response), cRkckddinfo); 
		model.addAttribute("page", page);
		return "modules/ck/cRkckddinfoList";
	}

	@RequiresPermissions("ck:cRkckddinfo:view")
	@RequestMapping(value = "form")
	public String form(CRkckddinfo cRkckddinfo, Model model) {
		model.addAttribute("cRkckddinfo", cRkckddinfo);
		return "modules/ck/cRkckddinfoForm";
	}

	@RequiresPermissions("ck:cRkckddinfo:edit")
	@RequestMapping(value = "save")
	public String save(CRkckddinfo cRkckddinfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cRkckddinfo)){
			return form(cRkckddinfo, model);
		}
		cRkckddinfoService.save(cRkckddinfo);
		addMessage(redirectAttributes, "保存总订单成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cRkckddinfo/?repage";
	}*/
	
	@RequiresPermissions("ck:cCginfo:edit")
	@RequestMapping(value = "delete")
	public String delete(CRkckddinfo cRkckddinfo, RedirectAttributes redirectAttributes) {
		CDdinfo cd = new CDdinfo();
		cd.setRkckddinfo(cRkckddinfo);
		List<CDdinfo> cdList = cDdinfoService.findList(cd);
		if(cdList.size()==0){
			cRkckddinfoService.delete(cRkckddinfo);
			addMessage(redirectAttributes, "删除总订单成功");
		}else {
			addMessage(redirectAttributes, "删除总订单失败，总订单下存在子订单请删除后再试！");
		}
		return "redirect:"+Global.getAdminPath()+"/ck/cRkckddinfo/?repage";
	}

	/**		采购申请开始 	 */
	@RequiresPermissions("ck:cCginfo:view")
	@RequestMapping(value = "cgList")
	public String cgList(CRkckddinfo cRkckddinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		cRkckddinfo.setLx("0");
		if(StringUtils.isNotBlank(cRkckddinfo.getState())) {
			UserUtils.removeCache("RKCKSTATE");
			UserUtils.putCache("RKCKSTATE", cRkckddinfo.getState());
			Page<CRkckddinfo> page = cRkckddinfoService.findPage(new Page<CRkckddinfo>(request, response), cRkckddinfo);
			model.addAttribute("cRkckddinfo", cRkckddinfo);
			model.addAttribute("page", page);
			return "modules/ck/cRkckddinfoList";
		}else {
			return "error/400";
		}
	}

	@RequiresPermissions("ck:cShop:view")
	@RequestMapping(value = "saveCgInfo")
	public String saveCgInfo(CRkckddinfo cRkckddinfo, Model model, RedirectAttributes redirectAttributes) {
		cRkckddinfo.setIssp("0");
		String retStr = "error/400";
		String state = UserUtils.getCache("RKCKSTATE").toString();
		if(StringUtils.isNotBlank(state)) {
			if("0".equals(state)||"1".equals(state)) {
				cRkckddinfo.setLx("0");//入库
				retStr = "redirect:"+Global.getAdminPath()+"/ck/cRkckddinfo/cgList?repage&state="+state;
			}else {
				cRkckddinfo.setLx("1");//出库
				retStr = "redirect:"+Global.getAdminPath()+"/ck/cRkckddinfo/libraryList?repage&state="+state;
			}
			cRkckddinfo.setState(state);
			CShop cs = new CShop();
			cs.setUserid(UserUtils.getUser().getId());
			List<CShop> shopList = cShopService.findList(cs);
			cRkckddinfoService.saveRkInfo(cRkckddinfo, shopList);
			cShopService.deleteByUserId(cs.getUserid());
		}
		return retStr;
	}



	/**		采购申请结束		*/
	/**		出库开始		**/
	@RequiresPermissions("ck:cCkinfo:view")
	@RequestMapping(value = "libraryList")
	public String libraryList(CRkckddinfo cRkckddinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		cRkckddinfo.setLx("1");//出库
		if(StringUtils.isNotBlank(cRkckddinfo.getState())) {
			UserUtils.removeCache("RKCKSTATE");
			UserUtils.putCache("RKCKSTATE", cRkckddinfo.getState());
			Page<CRkckddinfo> page = cRkckddinfoService.findPage(new Page<CRkckddinfo>(request, response), cRkckddinfo);
			model.addAttribute("cRkckddinfo", cRkckddinfo);
			model.addAttribute("page", page);
			return "modules/ck/cRkckLibraryList";
		}else {
			return "error/400";
		}
	}



	/**		出库结束		**/

}