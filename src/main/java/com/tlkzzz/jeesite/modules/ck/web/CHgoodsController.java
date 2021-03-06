/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tlkzzz.jeesite.common.utils.DateUtils;
import com.tlkzzz.jeesite.common.utils.excel.ExportExcel;
import com.tlkzzz.jeesite.modules.ck.entity.*;
import com.tlkzzz.jeesite.modules.ck.service.*;
import com.tlkzzz.jeesite.modules.cw.entity.FArrears;
import com.tlkzzz.jeesite.modules.cw.entity.FExpenRecord;
import com.tlkzzz.jeesite.modules.cw.entity.FPayment;
import com.tlkzzz.jeesite.modules.cw.service.FArrearsService;
import com.tlkzzz.jeesite.modules.cw.service.FExpenRecordService;
import com.tlkzzz.jeesite.modules.cw.service.FPaymentService;
import com.tlkzzz.jeesite.modules.sys.utils.ExcelCreateUtils;
import com.tlkzzz.jeesite.modules.sys.utils.UserUtils;
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

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 * 仓库商品Controller
 * @author xrc
 * @version 2017-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/ck/cHgoods")
public class CHgoodsController extends BaseController {

	@Autowired
	private CHgoodsService cHgoodsService;
	@Autowired
	private CHouseService cHouseService;
	@Autowired
	private CGoodsService cGoodsService;
	@Autowired
	private CCgzbinfoService cCgzbinfoService;
	@Autowired
	private CSupplierService cSupplierService;
	@Autowired
	private FPaymentService fPaymentService;
	@Autowired
	private FExpenRecordService fExpenRecordService;
	@Autowired
	private FArrearsService fArrearsService;
	@Autowired
	private CCkinfoService cCkinfoService;
	@Autowired
	private CRkinfoService cRkinfoService;
	@ModelAttribute
	public CHgoods get(@RequestParam(required=false) String id) {
		CHgoods entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cHgoodsService.get(id);
		}
		if (entity == null){
			entity = new CHgoods();
		}
		return entity;
	}

	@RequiresPermissions("ck:cHgoods:view")
	@RequestMapping(value = "")
	public String index() {
		return "modules/ck/cHgoodsIndex";
	}

	@RequiresPermissions("ck:cHgoods:view")
	@RequestMapping(value = "tree")
	public String tree(Model model) {
		model.addAttribute("houseList", cHouseService.findList(new CHouse()));
		return "modules/ck/cHgoodsTree";
	}

	@RequiresPermissions("ck:cHgoods:view")
	@RequestMapping(value = "none")
	public String none() {
		return "modules/ck/cHgoodsNone";
	}

	@RequiresPermissions("ck:cHgoods:view")
	@RequestMapping(value = "list")
	public String list(CHgoods cHgoods, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CHgoods> page = cHgoodsService.findPage(new Page<CHgoods>(request, response), cHgoods); 
		model.addAttribute("page", page);
		model.addAttribute("cHgoods", cHgoods);
		model.addAttribute("goodsList", cGoodsService.findList(new CGoods()));
		model.addAttribute("houseList", cHouseService.findList(new CHouse()));
		return "modules/ck/cHgoodsList";
	}

	@RequiresPermissions("ck:cHgoods:view")
	@RequestMapping(value = "form")
	public String form(CHgoods cHgoods, Model model) {//其他入库
		if(cHgoods!=null&&cHgoods.getGoods()!=null)
		    cHgoods.setCbj(Double.parseDouble(cHgoods.getGoods().getCbj()));
		model.addAttribute("cHgoods", cHgoods);
		model.addAttribute("houseList", cHouseService.findList(new CHouse()));
		model.addAttribute("goodsList", cGoodsService.findList(new CGoods()));
		return "modules/ck/cHgoodsForm";
	}

	@RequiresPermissions("ck:cHgoods:view")
	@RequestMapping(value = "cgInHouse")
	public String cgInHouse(String cGddId, Model model) {//采购入库
		if(StringUtils.isNotBlank(cGddId)) {
			CHgoods cHgoods = new CHgoods();
			CCgzbinfo cCgzbinfo = cCgzbinfoService.get(cGddId);
			cHgoods.setGoods(cCgzbinfo.getGoods());
			cHgoods.setCkState(cGddId);
			model.addAttribute("cHgoods", cHgoods);
			model.addAttribute("cCgzbinfo", cCgzbinfo);

			model.addAttribute("houseList", cHouseService.findList(new CHouse()));
			model.addAttribute("supplierList", cSupplierService.findList(new CSupplier()));
			return "modules/ck/cHgoodsCgHouse";
		}else {
			return "error/400";
		}
	}

	@RequiresPermissions("ck:cHgoods:edit")
	@RequestMapping(value = "move")
	public String move(Model model) {
		model.addAttribute("cHgoods", new CHgoods());
		model.addAttribute("houseList", cHouseService.findList(new CHouse()));
		model.addAttribute("goodsList", cGoodsService.findList(new CGoods()));
		return "modules/ck/cHgoodsMove";
	}

	@RequiresPermissions("ck:cHgoods:edit")
	@RequestMapping(value = "save")
	public String save(CHgoods cHgoods, Model model, RedirectAttributes redirectAttributes) {
		//其他入库库存保存方法
		if (!beanValidator(model, cHgoods)){
			return form(cHgoods, model);
		}
		cHgoodsService.save(cHgoods);
		addMessage(redirectAttributes, "保存仓库商品成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cHgoods/list?repage";
	}

	@RequiresPermissions("ck:cHgoods:edit")
	@ResponseBody
	@RequestMapping(value = "saveCG")
	public String saveCG(CHgoods cHgoods, Model model, RedirectAttributes redirectAttributes) {
		//采购入库库存保存方法
		if (!beanValidator(model, cHgoods)){
			return form(cHgoods, model);
		}
		//shizx 支出记录实体对象
		FPayment fPayment = cHgoods.getfPayment();
		//shizx set 订单id
		fPayment.setPaymentCode(cHgoods.getCkState());
		//shizx set 支出方式
		fPayment.setPaymentType("0");
		//计算出合同价格
		double cbj=cHgoods.getCbj();
		double nub=Double.parseDouble(cHgoods.getNub());
		double htje=cbj*nub;
		//set 合同金额
		fPayment.setHtje(Double.toString(htje));

		/**添加支出记录表*/
		FExpenRecord fExpenRecord=new FExpenRecord();
		fExpenRecord.setOrderId(cHgoods.getCkState());
		fExpenRecord.setExpenAccount(fPayment.getPaymentAccount());
		fExpenRecord.setTravelAccount(fPayment.getTravelAccount());
		fExpenRecord.setExpenMoney(fPayment.getJe());
		fExpenRecord.setExpenDate(fPayment.getPaymentDate());
		fExpenRecord.setJsr(fPayment.getJsr().getId());
		fExpenRecord.setExpenMode(fPayment.getPaymentMode());
		fExpenRecord.setExpenType("0");
		fExpenRecordService.save(fExpenRecord);

		/**添加欠款表*/
		double shzc=Double.parseDouble(fPayment.getJe());
		if(htje!=shzc){
			FArrears fArrears = new FArrears();
			fArrears.setArrearsUnit(fPayment.getTravelUnit().getId());
			fArrears.setArrearsType("1");   //set欠款类型   0客户   1供应商
			fArrears.setArrearsMode(fPayment.getPaymentMode());
			double ce=htje-shzc;
			fArrears.setTotal(Double.toString(ce));
			fArrears.setArrearsDate(fPayment.getPaymentDate());
			fArrearsService.save(fArrears);
		}
		//保存至支出记录表
		fPaymentService.save(fPayment);
		cHgoodsService.save(cHgoods);
		cCgzbinfoService.updateOrderCode(cHgoods.getCkState(),fPayment.getDdbh());//保存批次编号
		cCgzbinfoService.savePrice(cHgoods);//添加入库信息到采购订单表
		addMessage(redirectAttributes, "保存仓库商品成功");
		return "true";
	}

	@RequiresPermissions("ck:cHgoods:edit")
	@RequestMapping(value = "moveSave")
	public String moveSave(CHgoods cHgoods, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cHgoods)){
			return form(cHgoods, model);
		}
		cHgoodsService.moveSave(cHgoods);
		//出库记录
		CCkinfo cCkinfo=new CCkinfo();
		CGoods cGoods=new CGoods();
		cGoods.setId(cHgoods.getGoods().getId());
		List<CGoods> cGoodsList=cGoodsService.findList(cGoods);
		cCkinfo.setCkdate(new Date());
		cCkinfo.setCkqcbj(cGoodsList.get(0).getCbj());
		cCkinfo.setCkhcbj(cGoodsList.get(0).getCbj());
		cCkinfo.setJe(cGoodsList.get(0).getCbj());
		cCkinfo.setNub(cHgoods.getNub());
		cCkinfo.setGoods(cHgoods.getGoods());
		cCkinfo.setHouse(cHgoods.getHouse());
		cCkinfo.setState("9");
		cCkinfo.setIssp("1");
		cCkinfo.setJsr(UserUtils.getUser());
		cCkinfoService.save(cCkinfo);
		CRkinfo cRkinfo = new CRkinfo();
		cRkinfo.setGoods(cHgoods.getGoods());
		cRkinfo.setHouse(cHgoods.getHouse());
		cRkinfo.setRknub(cHgoods.getNub());
		List<CHgoods> cHgoodsList=cHgoodsService.findList(cHgoods);
		cRkinfo.setRkhnub(cHgoodsList.get(0).getNub());
		cRkinfo.setRkqcbj(cGoodsList.get(0).getCbj());
		cRkinfo.setRkhcbj(cGoodsList.get(0).getCbj());
		cRkinfo.setState("9");
		cRkinfoService.save(cRkinfo);
		addMessage(redirectAttributes, "保存仓库商品成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cYkinfo/list?repage";
	}

	/**
	 * 库存调整列表
	 * @param cHgoods
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("ck:cHgoods:edit")
	@RequestMapping(value = "changeStore")
	public String changeStore(CHgoods cHgoods, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CHgoods> page = cHgoodsService.findPage(new Page<CHgoods>(request, response), cHgoods);
		model.addAttribute("page", page);
		model.addAttribute("cHgoods", cHgoods);
		model.addAttribute("goodsList", cGoodsService.findList(new CGoods()));
		model.addAttribute("houseList", cHouseService.findList(new CHouse()));
		return "modules/ck/cHgoodsStoreChangeList";
	}

	@RequiresPermissions("ck:cHgoods:edit")
	@RequestMapping(value = "changeStoreForm")
	public String changeStoreForm(CHgoods cHgoods, Model model, RedirectAttributes redirectAttributes){
		if(cHgoods.getId()==null){
			addMessage(redirectAttributes,"库存商品不存在");
			return "redirect:"+Global.getAdminPath()+"/ck/cHgoods/changeStore?repage";
		}
		cHgoods.setUnit(cHgoods.getNub());
		model.addAttribute("cHgoods", cHgoods);
		return "modules/ck/cHgoodsStoreChangeForm";
	}

	@ResponseBody
	@RequiresPermissions("ck:cHgoods:edit")
	@RequestMapping(value = "changeStoreSave")
	public String changeStoreSave(CHgoods cHgoods){
		if(StringUtils.isBlank(cHgoods.getId()))return "库存商品不存在";
		if(StringUtils.isBlank(cHgoods.getNub())||StringUtils.isBlank(cHgoods.getUnit()))return "调整数量不能为空";
		if(Integer.parseInt(cHgoods.getNub())==Integer.parseInt(cHgoods.getUnit()))return "调整数量差不能为0";
		cHgoods.setNub(cHgoods.getNub().trim());//调整后数量
		cHgoods.setUnit(cHgoods.getUnit().trim());//调整前数量
		if(Integer.parseInt(cHgoods.getNub())>Integer.parseInt(cHgoods.getUnit())){//增加库存,入库记录
			CRkinfo cRkinfo = new CRkinfo();
			cRkinfo.setState("8");//调整库存
			cRkinfo.setHouse(cHgoods.getHouse());
			cRkinfo.setGoods(cHgoods.getGoods());
			cRkinfo.setRknub(String.valueOf(Integer.parseInt(cHgoods.getNub())-Integer.parseInt(cHgoods.getUnit())));
			cRkinfo.setRkhnub(cHgoods.getNub());
			cRkinfo.setRkqcbj(cHgoods.getGoods().getCbj());
			cRkinfo.setRkhcbj(cHgoods.getGoods().getCbj());
			cRkinfoService.save(cRkinfo);
		}else {//减少库存，出库记录
			CCkinfo cCkinfo = new CCkinfo();
			cCkinfo.setState("8");//调整库存
			cCkinfo.setHouse(cHgoods.getHouse());
			cCkinfo.setGoods(cHgoods.getGoods());
			cCkinfo.setNub(String.valueOf(Integer.parseInt(cHgoods.getUnit())-Integer.parseInt(cHgoods.getNub())));
			cCkinfo.setCkqcbj(cHgoods.getGoods().getCbj());
			cCkinfo.setCkhcbj(cHgoods.getGoods().getCbj());
			cCkinfo.setJe(cHgoods.getGoods().getCbj());
			cCkinfo.setCkdate(new Date());
			cCkinfo.setIssp("1");
			cCkinfo.setJsr(UserUtils.getUser());
			cCkinfoService.save(cCkinfo);
		}
		cHgoodsService.kcsl(cHgoods);
		return "true";
	}


	@RequiresPermissions("ck:cHgoods:edit")
	@RequestMapping(value = "delete")
	public String delete(CHgoods cHgoods, RedirectAttributes redirectAttributes) {
		cHgoodsService.delete(cHgoods);
		addMessage(redirectAttributes, "删除仓库商品成功");
		return "redirect:"+Global.getAdminPath()+"/ck/cHgoods/list?repage";
	}

	@ResponseBody
    @RequestMapping(value = "checkStockNum")
    public String checkStockNum(CHgoods cHgoods,String goodsId,String outId){
		String retStr = "true";
		if(StringUtils.isNotBlank(goodsId)&&StringUtils.isNotBlank(outId)){
			cHgoods.setGoods(new CGoods(goodsId));
			cHgoods.setHouse(new CHouse(outId));
			String numStr = String.valueOf(cHgoodsService.findStockNum(cHgoods));
			int sunNum = (!"null".equals(numStr))?Integer.parseInt(numStr):0;
			if(sunNum<Integer.parseInt(cHgoods.getNub()))retStr = "false";
		}
	    return retStr;
    }
	/**
	 * 导出供应商数据
	 * @param cHgoods
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "exportFile", method = RequestMethod.POST)
	public String exportFile(CHgoods cHgoods, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "供应商数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";

			Page<CHgoods> page = cHgoodsService.findUser(new Page<CHgoods>(request, response, -1), cHgoods);
			new ExportExcel("导出数据", CHgoods.class).setDataList(page.getList()).write(response, fileName).dispose();
			//return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出用户失败！失败信息：" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
/**
 *  库存明细
 *   报表开始
 */
@RequiresPermissions("report:cHgoodsInquiry:view")
@RequestMapping(value = "kcInquiry")
public String kcInquiry(CHgoods cHgoods, HttpServletRequest request, HttpServletResponse response, Model model) {
	Page<CHgoods> page = cHgoodsService.findPage(new Page<CHgoods>(request, response), cHgoods);
	model.addAttribute("page", page);
	model.addAttribute("cHgoods", cHgoods);
	return "modules/report/cHgoodsInquiryList";
}

	@RequiresPermissions("ck:cHgoodsReport:view")
	@RequestMapping(value = "aqkcReport")
	public String aqkcReport(CHgoods cHgoods, Model model){
		model.addAttribute("list", cHgoodsService.findList(cHgoods));
		model.addAttribute("houseList", cHouseService.findList(new CHouse()));
		model.addAttribute("goodsList", cGoodsService.findList(new CGoods()));
		model.addAttribute("cHgoods", cHgoods);
		return "modules/report/cHgoodsReportList";
	}

	@RequestMapping(value = "aqkExcel")
	public String aqkExcel(CHgoods cHgoods, Model model,HttpServletResponse response){
		List<CHgoods> list = cHgoodsService.findList(cHgoods);
		ExcelCreateUtils.kcexport(response,list,"1");
		model.addAttribute("list", cHgoodsService.findList(cHgoods));
		model.addAttribute("houseList", cHouseService.findList(new CHouse()));
		model.addAttribute("goodsList", cGoodsService.findList(new CGoods()));
		model.addAttribute("cHgoods", cHgoods);
		return null;
	}

//	@RequiresPermissions("ck:cHgoodsReport:view")
	@RequestMapping(value = "kcReport")
	public String kcReport(CHgoods cHgoods, String type, Model model){
		List<CHgoods> list = new ArrayList<CHgoods>();
		if(StringUtils.isBlank(type)||"1".equals(type)){
			list = cHgoodsService.findList(cHgoods);
		}else if("2".equals(type)){
			list = cHgoodsService.findReportListByGoods(cHgoods);
		}else if("3".equals(type)){
			list = cHgoodsService.findReportListByBands(cHgoods);
		}
		model.addAttribute("list", list);
		model.addAttribute("houseList", cHouseService.findList(new CHouse()));
		model.addAttribute("goodsList", cGoodsService.findList(new CGoods()));
		model.addAttribute("cHgoods", cHgoods);
		model.addAttribute("type", type);
		return "modules/report/cHgoodsReportList";
	}

}