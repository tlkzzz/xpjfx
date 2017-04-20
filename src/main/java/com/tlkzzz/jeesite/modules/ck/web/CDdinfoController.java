/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tlkzzz.jeesite.modules.ck.entity.*;
import com.tlkzzz.jeesite.modules.ck.service.CGoodsService;
import com.tlkzzz.jeesite.modules.ck.service.CHgoodsService;
import com.tlkzzz.jeesite.modules.ck.service.CHouseService;
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
import com.tlkzzz.jeesite.modules.ck.service.CDdinfoService;

import java.util.List;

/**
 * 订单Controller
 * @author xrc
 * @version 2017-03-27
 */
@Controller
@RequestMapping(value = "${adminPath}/ck/cDdinfo")
public class CDdinfoController extends BaseController {

	@Autowired
	private CDdinfoService cDdinfoService;
	@Autowired
	private CHgoodsService cHgoodsService;
	@Autowired
	private CGoodsService cGoodsService;
	@ModelAttribute
	public CDdinfo get(@RequestParam(required=false) String id) {
		CDdinfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cDdinfoService.get(id);
		}
		if (entity == null){
			entity = new CDdinfo();
		}
		return entity;
	}
	/**		采购部分代码开始		 **/
	@RequiresPermissions("ck:cCginfo:view")
	@RequestMapping(value = "cgDdList")
	public String cgDdList(CDdinfo cDdinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CDdinfo> page = cDdinfoService.findPage(new Page<CDdinfo>(request, response), cDdinfo);
		model.addAttribute("cDdinfo", cDdinfo);
		model.addAttribute("page", page);
		return "modules/ck/cDdinfoList";
	}

	/**		采购部分代码结束		 **/

	@RequiresPermissions("ck:cDdinfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(CDdinfo cDdinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CDdinfo> page = cDdinfoService.findPage(new Page<CDdinfo>(request, response), cDdinfo); 
		model.addAttribute("cDdinfo", cDdinfo);
		model.addAttribute("page", page);
		return "modules/ck/cDdinfoList";
	}

	@RequiresPermissions("ck:cDdinfo:view")
	@RequestMapping(value = {"returnGoodsList", ""})
	public String returnGoodsList(CDdinfo cDdinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CDdinfo> page = cDdinfoService.findPage(new Page<CDdinfo>(request, response), cDdinfo);
		model.addAttribute("cDdinfoList", cDdinfoService.thfindList(cDdinfo));
		model.addAttribute("cDdinfo", cDdinfo);
		model.addAttribute("page", page);
		return "modules/ck/returnGoodsList";
	}

	@RequiresPermissions("ck:cDdinfo:view")
	@RequestMapping(value = "form")
	public String form(CDdinfo cDdinfo, Model model) {
		model.addAttribute("cDdinfo", cDdinfo);
		return "modules/ck/cDdinfoForm";
	}

	@RequiresPermissions("ck:cDdinfo:edit")
	@RequestMapping(value = "save")
	public String save(CDdinfo cDdinfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cDdinfo)){
			return form(cDdinfo, model);
		}
		cDdinfoService.save(cDdinfo);
		addMessage(redirectAttributes, "保存订单成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cDdinfo/?repage";
	}
	
	@RequiresPermissions("ck:cDdinfo:edit")
	@RequestMapping(value = "delete")
	public String delete(CDdinfo cDdinfo, RedirectAttributes redirectAttributes) {
		cDdinfoService.delete(cDdinfo);
		addMessage(redirectAttributes, "删除订单成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cDdinfo/?repage";
	}


	/**
	 * 销售退货单审批
	 * */
	@RequiresPermissions("User")
	@RequestMapping(value = "thsp")
	public String thsp(String ids) {
		CDdinfo cDdinfo=new CDdinfo();
		cDdinfo.setId(ids);
		List<CDdinfo> cDdinfoList=cDdinfoService.thfindList(cDdinfo);//子订单信息
		String supplier=cDdinfoList.get(0).getSupplier().getId();
		if(StringUtils.isNotBlank(supplier)){

		}else{
			//客户退货
			String goodsId=cDdinfoList.get(0).getGoods().getId();
			String housId=cDdinfoList.get(0).getHouse().getId();
			CHgoods cHgoods=new CHgoods();
			cHgoods.setGoods(new CGoods(goodsId));
			cHgoods.setHouse(new CHouse(housId));
			List<CHgoods> cHgoodsList=cHgoodsService.findList(cHgoods);//库存信息
			//库存表添加
			Double nub=Double.parseDouble(cHgoodsList.get(0).getNub());//库存数量
			Double thsl=Double.parseDouble(cDdinfoList.get(0).getThsl());//退货数量
			Double kcsl=nub+thsl;
			if(cHgoodsList.size()>0){//库存中存在此商品,更新库存
				String id=cHgoodsList.get(0).getId();
				cHgoods.setNub(kcsl.toString());
				cHgoods.setId(id);
				cHgoodsService.kcsl(cHgoods);
			}else{//库存中不存在此商品,新增商品库存
				cHgoods.setNub(cDdinfoList.get(0).getThsl());
				cHgoodsService.save(cHgoods);
			}
			//入库记录表添加
			CRkinfo cRkinfo=new CRkinfo();
			cRkinfo.setGoods(new CGoods(goodsId));
			cRkinfo.setHouse(new CHouse(housId));
			cRkinfo.setRknub(cDdinfoList.get(0).getThsl());
			cRkinfo.setRkhnub(kcsl.toString());
			CGoods cGoods=new CGoods();
			cGoods.setId(goodsId);
			List<CGoods> cGoodsList=cGoodsService.findList(cGoods);
			cRkinfo.setRkqcbj(cGoodsList.get(0).getCbj());
			Double cbj=Double.parseDouble(cGoodsList.get(0).getCbj());

			cRkinfo.setStoreId(cDdinfoList.get(0).getStore());
		}

		return "redirect:"+Global.getAdminPath()+"/ck/cDdinfo/?repage";
	}

}