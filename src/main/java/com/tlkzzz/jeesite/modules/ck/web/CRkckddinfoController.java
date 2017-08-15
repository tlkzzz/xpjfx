/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tlkzzz.jeesite.common.mapper.JsonMapper;
import com.tlkzzz.jeesite.common.utils.Encodes;
import com.tlkzzz.jeesite.modules.ck.entity.*;
import com.tlkzzz.jeesite.modules.ck.service.*;
import com.tlkzzz.jeesite.modules.cw.entity.*;
import com.tlkzzz.jeesite.modules.ck.entity.CDdinfo;
import com.tlkzzz.jeesite.modules.ck.entity.CShop;
import com.tlkzzz.jeesite.modules.ck.entity.CStore;
import com.tlkzzz.jeesite.modules.ck.service.CDdinfoService;
import com.tlkzzz.jeesite.modules.ck.service.CShopService;
import com.tlkzzz.jeesite.modules.ck.service.CStoreService;
import com.tlkzzz.jeesite.modules.cw.service.*;
import com.tlkzzz.jeesite.modules.sys.entity.User;
import com.tlkzzz.jeesite.modules.sys.utils.NumberToCN;
import com.tlkzzz.jeesite.modules.sys.utils.UserUtils;
import net.sf.json.JSONArray;
import netscape.security.UserTarget;
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

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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
	private FReceiptService fReceiptService;
	@Autowired
	private FPaymentService fPaymentService;
	@Autowired
	private CStoreService cStoreService;
	@Autowired
	private CGclassService cGclassService;
	@Autowired
	private CSupplierService cSupplierService;
	@Autowired
	private CDdinfoService cDdinfoService;
	@Autowired
	private CShopService cShopService;
	@Autowired
	private FAccountService fAccountService;
	@Autowired
	private CHouseService houseService;

	@Autowired
	private CCarService cCarService;
	@Autowired
	private FDiscountService fDiscountService;
	@Autowired
	private FExpenRecordService fExpenRecordService;
	@Autowired
	private  FArrearsService fArrearsService;
	@Autowired
	private  CGoodsService cGoodsService;
	@Autowired
	private  CCgzbinfoService cCgzbinfoService;




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
	
	@RequiresPermissions("ck:cRkckddinfo:view")
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
	}

	/**
	 * shizx销售退货单List
	 * */
	@RequiresPermissions("ck:cRkckddinfo:view")
	@RequestMapping(value = "returnGoodsList")
	public String returnGoodsList(CRkckddinfo cRkckddinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CRkckddinfo> page = cRkckddinfoService.findPage(new Page<CRkckddinfo>(request, response), cRkckddinfo);
		model.addAttribute("page", page);
		model.addAttribute("CRkckddinfo", cRkckddinfo);
		return "modules/ck/returnGoodsList";
	}
	/**
 	* shizx销售退货单Form
 	* */
	@RequiresPermissions("ck:cDdinfo:view")
	@RequestMapping(value = "returnGoodsForm")
	public String returnGoodsForm(CRkckddinfo cRkckddinfo, Model model) {
		model.addAttribute("houseList", houseService.findList(new CHouse()));
		model.addAttribute("IDcarddList", fAccountService.findList(new FAccount()));
		model.addAttribute("orderIdList",cRkckddinfoService.findListId(new CRkckddinfo()));
		model.addAttribute("cRkckddinfo", cRkckddinfo);
		return "modules/ck/returnGoodsForm";
	}
	/**
	 * shizx获取子订单信息ajax
	 * */
	@ResponseBody
	@RequestMapping(value = "checkId",method = RequestMethod.POST)
	public Map<String,Object> checkId(String ids) {
		Map<String,Object>  map = new HashMap<String,Object> ();
		CDdinfo cDdinfo=new CDdinfo();
		cDdinfo.setRkckddinfo(new CRkckddinfo(ids));
		List<CDdinfo> cDdinfoList= cDdinfoService.findList(cDdinfo);
		for(CDdinfo cd:cDdinfoList){
			if(StringUtils.isNotBlank(cd.getJe())&&StringUtils.isNotBlank(cd.getYhje())){
				cd.setSjje(String.valueOf(Double.parseDouble(cd.getJe())-Double.parseDouble(cd.getYhje())));
			}else{
				cd.setSjje(cd.getJe());
			}
		}
		/*for (int i=0;i<cDdinfoList.size();i++){
			String ss=cDdinfoList[i]
			double yhje=Double.parseDouble();
		}*/
//		model.addAttribute("IdList", cDdinfoService.findList(cDdinfo));
		map.put("rows",cDdinfoList);
//		map.put("page",1);
//		map.put("total", 2);
//		map.put("records", 2);
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "jqGrid",method = RequestMethod.POST)
	public Map<String,Object> jqGrid(String ids) {

		return null;
	}

	@RequiresPermissions("ck:cCginfo:edit")
	@RequestMapping(value = "delete")
	public String delete(CRkckddinfo cRkckddinfo, String pageName, RedirectAttributes redirectAttributes) {
		CDdinfo cd = new CDdinfo();
		cd.setRkckddinfo(cRkckddinfo);
		List<CDdinfo> cdList = cDdinfoService.findList(cd);
		if(cdList.size()==0){
			cRkckddinfoService.delete(cRkckddinfo);
			addMessage(redirectAttributes, "删除总订单成功");
		}else {
			addMessage(redirectAttributes, "删除总订单失败，总订单下存在子订单请删除后再试！");
		}
		return "redirect:"+Global.getAdminPath()+"/ck/cRkckddinfo/"+pageName+"?repage&state="+cRkckddinfo.getState();
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
			model.addAttribute("supplierList", cSupplierService.findList(new CSupplier()));
			model.addAttribute("page", page);
			return "modules/ck/cRkckddinfoList";
		}else {
			return "error/400";
		}

	}

	@RequiresPermissions("ck:cCginfo:view")
	@RequestMapping(value = "saveCgInfo")
	public String saveCgInfo(CRkckddinfo cRkckddinfo, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request,HttpServletResponse response) {
		String a=request.getParameter("cHouse.code");
		String b=request.getParameter("cHouse.state");
		int htje=Integer.parseInt(a);
		int je=Integer.parseInt(b);
		cRkckddinfo.setIssp("0");
		String retStr = "error/400";
		String state = UserUtils.getCache("RKCKSTATE").toString();
		if(StringUtils.isNotBlank(state)) {
			if ("0".equals(state) || "1".equals(state)) {
				cRkckddinfo.setLx("0");//入库
				retStr = "redirect:" + Global.getAdminPath() + "/ck/cRkckddinfo/cgList?repage&state=" + state;
			} else {
				cRkckddinfo.setLx("1");//出库
				retStr = "redirect:" + Global.getAdminPath() + "/ck/cRkckddinfo/libraryList?repage&state=" + state;
			}
			cRkckddinfo.setState(state);
			CShop cs = new CShop();
			cs.setState(state);
			cs.setUserid(UserUtils.getUser().getId());
			List<CShop> shopList = cShopService.findList(cs);
			for (int i = 0; i < shopList.size(); i++) {
				cs = shopList.get(i);
				//更新ccgzbinfo的数量
				cShopService.upsave(cs);
			}
			cRkckddinfoService.saveRkInfo(cRkckddinfo, shopList);
			cShopService.deleteByUserId(cs.getUserid(), state);
			if (htje == je) {//全部还款
				FExpenRecord fex = new FExpenRecord();
				fex.setDdbh(cs.getSpbh());
				fex.setJsr(UserUtils.getUser().getId());
				fex.setExpenMoney(String.valueOf(htje));
				fex.setExpenDate(new Date());
				fExpenRecordService.save(fex);
				FPayment fpa = new FPayment();
				fpa.setPaymentDate(new Date());
				fpa.setPaymentCode(cs.getSpbh());
				fpa.setDdbh(cs.getSpbh());
				fpa.setHtje(String.valueOf(htje));
				fpa.setJe(String.valueOf(je));
				fpa.setJsr(UserUtils.getUser());
				fPaymentService.save(fpa);
			} else if (je < htje) { //部分还款
				int qkje = htje - je;
				FExpenRecord fex = new FExpenRecord();
				fex.setDdbh(cs.getSpbh());
				fex.setJsr(UserUtils.getUser().getId());
				fex.setExpenMoney(String.valueOf(htje));
				fex.setExpenDate(new Date());
				fExpenRecordService.save(fex);
				FPayment fpa = new FPayment();
				fpa.setPaymentDate(new Date());
				fpa.setPaymentCode(cs.getSpbh());
				fpa.setDdbh(cs.getSpbh());
				fpa.setHtje(String.valueOf(htje));
				fpa.setJe(String.valueOf(je));
				fpa.setJsr(UserUtils.getUser());
				fPaymentService.save(fpa);
				FArrears far = new FArrears();
				far.setArrearsType("1");
				far.setTotal(String.valueOf(qkje));
				far.setArrearsDate(new Date());
				fArrearsService.save(far);
			} else if (je == 0) { //未付款
				int qkje = htje - je;
				FArrears far = new FArrears();
				far.setArrearsType("1");
				far.setTotal(String.valueOf(qkje));
				far.setArrearsDate(new Date());
				fArrearsService.save(far);
			}
			else {
				addMessage(redirectAttributes, "保存失败，请联系管理员");
				return "error/500";
			}


//			if(cRkckddinfo.getReceipt()!=null) {//保存订单ID到收款表
//				//如果receipt中有ID则为出库财务信息保存后跳转过来，并且带有收款记录ID为参数
//				if (!"5".equals(state)) {
//					cRkckddinfo.getReceipt().setReceiptCode(cRkckddinfo.getId());
//					fReceiptService.updateReceiptCode(cRkckddinfo.getReceipt());
//					FReceipt receipt = fReceiptService.get(cRkckddinfo.getReceipt());
//					cRkckddinfo.setStore(receipt.getTravelUnit());
//					cRkckddinfoService.save(cRkckddinfo);//保存来往单位（客户）
//					double yhTotal = 0.0;//优惠总金额
//					double sjTotal = 0.0;//实际总金额
//					for (CShop cShop : shopList) {
//						if (cShop.getYhje() != null && cShop.getYhje() > 0) yhTotal += cShop.getYhje();
//						if (cShop.getJe() != null && cShop.getJe() > 0) sjTotal += cShop.getJe();
//					}
//					if (yhTotal > 0 || Double.parseDouble(receipt.getHtje()) > sjTotal) {
//						FDiscount discount = new FDiscount();
//						discount.setDdid(cRkckddinfo);
//						//		discount.setStore(receipt.getTravelUnit());
//						discount.setYhje(String.valueOf(yhTotal));
//						discount.setLx((yhTotal > 0) ? "0" : "1");
//						fDiscountService.save(discount);
//					}
//				}else {
//					FPayment payment = fPaymentService.get(cRkckddinfo.getReceipt().getId());
//					if(payment!=null) {
//						cRkckddinfo.setSupplier(payment.getTravelUnit());
//						cRkckddinfoService.save(cRkckddinfo);//保存来往单位（供应商）
//						payment.setPaymentCode(cRkckddinfo.getId());
//						fPaymentService.save(payment);
//					}else {
//						redirectAttributes.addAttribute("message","付款信息不存在，系统出现异常，请与管理员联系！");
//					}
//				}
//			}

		}
		return retStr;
	}

	@RequiresPermissions("ck:cCkinfo:view")
	@RequestMapping(value = "selectHouse")
	public String selectHouse(CHouse house, Model model){
	     CShop cShop=new CShop();
		 cShop.setUserid(UserUtils.getUser().getId());
		 cShop.setState(UserUtils.getCache("RKCKSTATE").toString());
		 List<CShop> list=cShopService.findList(cShop);
		for (CShop cs: list){
			Double 	htje=(cs.getJe()-cs.getYhje())*Integer.parseInt(cs.getNub());
			double htje1=Double.valueOf(htje);
			int htje3=(int)htje1;
			house.setCode(String.valueOf(htje3));
		}
		model.addAttribute("houseList", houseService.findList(new CHouse()));
		model.addAttribute("supplierList", cSupplierService.findList(new CSupplier()));
		model.addAttribute("house", house);
		return "modules/ck/selectHouse";
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

	/**		新版出库入库方法开始		**/
	/**
	 * 入库订单填写页面
	 * @param pageName
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("ck:cCkinfo:view")
    @RequestMapping(value = "order/{pageName}")
    public String order(@PathVariable("pageName")String pageName,String id,String review,Model model){
		if(StringUtils.isNotBlank(id)){
			CDdinfo cDdinfo = new CDdinfo();
			cDdinfo.setRkckddinfo(cRkckddinfoService.get(id));
			List<CDdinfo> cDdinfoList = cDdinfoService.findList(cDdinfo);
			JSONArray array	= new JSONArray();
			Double total = 0.0;
			for(CDdinfo cd : cDdinfoList){
				array.add(JsonMapper.toJsonString(cd.getGoods()));
				total += Integer.parseInt(cd.getNub())*Double.parseDouble(cd.getJe());
			}
			model.addAttribute("goodsJSON", array.toString());
			model.addAttribute("total", total);
			model.addAttribute("cRkckddinfo", cDdinfo.getRkckddinfo());
			model.addAttribute("json",cDdinfoService.processJSON(cDdinfoList).toString());
			if(StringUtils.isNotBlank(review)){
				FPayment payment = new FPayment();
				payment.setPaymentCode(cDdinfo.getRkckddinfo().getId());
				model.addAttribute("review", review);
				model.addAttribute("payment", fPaymentService.getByPaymentCode(payment));
				model.addAttribute("accountList", fAccountService.findList(new FAccount()));
				model.addAttribute("supplier", cSupplierService.get(cDdinfo.getRkckddinfo().getSupplier()));
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		CGclass gclass = new CGclass();
		gclass.setParent(new CGclass("0"));
		model.addAttribute("createDate",sdf.format(new Date()));
		model.addAttribute("name",UserUtils.getUser().getName());
//		model.addAttribute("name", UserUtils.getUser());
		model.addAttribute("cCar",cCarService.findList(new CCar()));
		model.addAttribute("cStorelist", cStoreService.findList(new CStore()));
		model.addAttribute("gClass", cGclassService.findList(gclass));
		model.addAttribute("houseList", houseService.findList(new CHouse()));
		model.addAttribute("supplierList", cSupplierService.findList(new CSupplier()));
		return "modules/ck/"+pageName;
    }

	/**
	 * 入库订单保存方法
	 * @param cRkckddinfo
	 * @param jsonData
	 * @return
	 */
    @RequiresPermissions("ck:cCkinfo:edit")
	@RequestMapping(value = "rkOrderSave")
	public String rkOrderSave(CRkckddinfo cRkckddinfo,String jsonData,String pageName,String review){
    	if(StringUtils.isBlank(cRkckddinfo.getLx())||StringUtils.isBlank(cRkckddinfo.getState())||
                StringUtils.isBlank(jsonData)||cRkckddinfo.getcHouse()==null){
    		return "error/400";
		}
		JSONArray jsonArray = JSONArray.fromObject(Encodes.unescapeHtml(jsonData));
		if(jsonArray.size()>0){
			cRkckddinfo.setIssp("0");
			cRkckddinfoService.saveRkCkInfo(cRkckddinfo,jsonArray);
		}
    	return "redirect:"+Global.getAdminPath()+"/ck/cRkckddinfo/order/"+pageName+"?id="+cRkckddinfo.getId()+"&review="+review;
	}

	/**
	 * 入库订单选择页面
	 * @param cRkckddinfo
	 * @param model
	 * @return
	 */
    @RequiresPermissions("ck:cCkinfo:edit")
	@RequestMapping(value = "rkOrderSelect")
	public String rkSelect(CRkckddinfo cRkckddinfo,HttpServletRequest request,HttpServletResponse response,Model model){
    	if(cRkckddinfo==null||StringUtils.isBlank(cRkckddinfo.getLx())){
    		return "error/400";
		}
		Page<CRkckddinfo> page = cRkckddinfoService.findPage(new Page<CRkckddinfo>(request,response),cRkckddinfo);
		model.addAttribute("houseList",houseService.findList(new CHouse()));
		model.addAttribute("supplierList",cSupplierService.findList(new CSupplier()));
		model.addAttribute("allNotIsspCount",cRkckddinfoService.getNotIsspCount(null));
		model.addAttribute("lsNotIsspCount",cRkckddinfoService.getNotIsspCount("0"));
		model.addAttribute("ckNotIsspCount",cRkckddinfoService.getNotIsspCount("1"));
		model.addAttribute("qtNotIsspCount",cRkckddinfoService.getNotIsspCount("3"));
		model.addAttribute("thNotIsspCount",cRkckddinfoService.getNotIsspCount("5"));
		model.addAttribute("cRkckddinfo",cRkckddinfo);
		model.addAttribute("page",page);
    	return "modules/ck/ckOrderSelect";
	}

	/**
	 * 入库审核
	 * @param cRkckddinfo 总订单ID
	 * @param account 付款账户ID
	 * @param travelAccount 来往单位账户
	 * @param total 付款金额
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("ck:cCkinfo:view")
	@RequestMapping(value = "rkReview")
	public String rkReview(CRkckddinfo cRkckddinfo,String account,String travelAccount,String total){
		if(cRkckddinfo==null||StringUtils.isBlank(cRkckddinfo.getId())){
			return "false";
		}
		if(StringUtils.isBlank(account)||StringUtils.isBlank(travelAccount)||StringUtils.isBlank(total)){
			return "false";
		}
		CDdinfo cDdinfo = new CDdinfo();
		cDdinfo.setRkckddinfo(cRkckddinfo);
		List<CDdinfo> cdList = cDdinfoService.findList(cDdinfo);
		cCgzbinfoService.saveCdList(cdList,cRkckddinfo,account,travelAccount,total);
		return "true";
	}

	/**
	 * 入库单详细信息页面
	 * @param cRkckddinfo
	 * @param model
	 * @return
	 */
	@RequiresPermissions("ck:cCkinfo:view")
	@RequestMapping(value = "RKDDetails")
	public String RKDDetails(CRkckddinfo cRkckddinfo,Model model){
		if(cRkckddinfo==null||StringUtils.isBlank(cRkckddinfo.getId())){
			return "error/400";
		}
		CDdinfo cd = new CDdinfo();
		cd.setRkckddinfo(cRkckddinfo);
		List<CDdinfo> cdList = cDdinfoService.findList(cd);
		model.addAttribute("cRkckddinfo",cRkckddinfo);
		model.addAttribute("cdList",cDdinfoService.processUnit(cdList));
		return "modules/ck/rkDDetails";
	}

	/**
	 * 订单备注修改
	 * @param ids
	 * @param remark
	 * @param cRkckddinfo
	 * @return
	 */
	@RequiresPermissions("ck:cCkinfo:view")
	@RequestMapping(value = "orderRemarkChange")
	public String orderRemarkChange(String[] ids,String[] remark,CRkckddinfo cRkckddinfo){
		if(cRkckddinfo==null||StringUtils.isBlank(cRkckddinfo.getId())){
			return "error/400";
		}
		cRkckddinfoService.updateRemark(cRkckddinfo);
		if(ids.length==remark.length) {
			for (int i = 0; i < ids.length; i++) {
				CDdinfo cd = new CDdinfo(ids[i]);
				cd.setRemarks(remark[i]);
				cDdinfoService.updateRemark(cd);
			}
		}
		return "redirect:"+Global.getAdminPath()+"/ck/cRkckddinfo/RKDDetails?repage&id="+cRkckddinfo.getId();
	}



	/**
	 * 销售出库审核接口
	 * **/
	@ResponseBody
	@RequestMapping(value = "xsckSh")
	public String xsckSh(String zddId,String lwzh,String skzh,String skfs){//总订单ID 来往单位账户 收款账户 收款方式
		String biaoshi="";
		//通过总订单ID 获取订单信息
		CRkckddinfo cRkckddinfo=new CRkckddinfo(zddId);//总订单
		FDiscount fDiscount=new FDiscount();//new优惠记录表对象
		fDiscount.preInsert();
		CDdinfo cDdinfo=new CDdinfo();//new 子订单
		cDdinfo.setRkckddinfo(new CRkckddinfo(zddId));//set父级订单ID
		List<CDdinfo> cDdinfoList=new ArrayList<CDdinfo>();//new 子订单对象
		FReceipt fReceipt=new FReceipt();//new 收款表对象
		fReceipt.preInsert();
		FIncomeRecord fIncomeRecord=new FIncomeRecord();//new 收款记录表对象
		fIncomeRecord.preInsert();
		FAccount fAccount=new FAccount();//new 账户对象
		FAccount fAccounttwo=new FAccount();//new 账户对象
		List<CRkckddinfo> cRkckddinfoList=cRkckddinfoService.findList(cRkckddinfo);//获取总订单信息
		Double htje=Double.parseDouble(cRkckddinfoList.get(0).getHtje());//获取总订单合同金额
		Double sjje=Double.parseDouble(cRkckddinfoList.get(0).getSjje());//获取总订单实际金额
		Double yhje=htje-sjje;
		fDiscount.setStore(new CStore(cRkckddinfoList.get(0).getStore().getId()));//set客户ID
		fDiscount.setDdid(new CRkckddinfo(zddId));//set总订单ID
		if(yhje!=0){//判断总订单中是否存在优惠 存在
			//增加优惠记录
			fDiscount.setYhje(yhje.toString());//set优惠金额
			fDiscount.setLx("1");//set优惠状态，总体抹零
			//--------------------------调用优惠记录保存方法
		}else{//判断总订单中是否存在优惠 不存在
			if(cRkckddinfoList.get(0).getIssp().equals("0")) {//判断审批状态为0未审核
				if (cRkckddinfoList.get(0).getLx().equals("1")) {//判断状态为1出库
					cDdinfoList=cDdinfoService.findList(cDdinfo);//查询子订单集合
					if (cRkckddinfoList.get(0).getState().equals("5")) {//判断状态为5退货录单
						biaoshi="5";
//					收款表添加

						fReceipt.setReceiptDate(new Date());//set 收款时间
						fReceipt.setReceiptCode(zddId);//set 收据编号
						fReceipt.setTravelUnit(new CStore(cRkckddinfoList.get(0).getSupplier().getId()));//set 来往单位
						fReceipt.setTravelAccount(lwzh);//set 来往账户
						fReceipt.setReceiptAccount(skzh);//set收款账户
						fReceipt.setJe(cRkckddinfoList.get(0).getSjje());//set 收款金额
						fReceipt.setReceiptType("5");//set 类型5退货录单   9预售录单
						fReceipt.setReceiptMode(skfs);//set 收款方式 现金  银行卡
						fReceipt.setJsr(new User(UserUtils.getUser().getId()));//set 经手人
						fReceipt.setApprovalStatus("1");//set 审批状态1已审批
						//----------------------------调用收款账户保存方法

						//    收款记录表添加

						fIncomeRecord.setOrderId(zddId);
						fIncomeRecord.setIncomeAccount(skzh);
						fIncomeRecord.setTraverAccount(lwzh);
						fIncomeRecord.setIncomeMoney(cRkckddinfoList.get(0).getSjje());
						fIncomeRecord.setIncomeDate(new Date());
						fIncomeRecord.setJsr(UserUtils.getUser().getId());
						fIncomeRecord.setIncomeMode("5");
						fIncomeRecord.setIncomeType(skfs);
						//--------------------------调用收款记录表保存方法

						//账户表余额减少f_account

						fAccount.setId(skzh);
						fAccount.setAccountBalance(cRkckddinfoList.get(0).getSjje());
						//------------------调用账户增加方法

						fAccounttwo.setId(lwzh);
						fAccounttwo.setAccountBalance(cRkckddinfoList.get(0).getSjje());
						//------------------调用账户减少方法
					} else if (cRkckddinfoList.get(0).getState().equals("9")) {//判断状态为9预收录单
						biaoshi="9";
						//					收款表添加

						fReceipt.setReceiptDate(new Date());//set 收款时间
						fReceipt.setReceiptCode(zddId);//set 收据编号
						fReceipt.setTravelUnit(new CStore(cRkckddinfoList.get(0).getStore().getId()));//set 来往单位
						fReceipt.setTravelAccount(lwzh);//set 来往账户
						fReceipt.setReceiptAccount(skzh);//set收款账户
						fReceipt.setJe(cRkckddinfoList.get(0).getSjje());//set 收款金额
						fReceipt.setReceiptType("9");//set 类型5退货录单   9预售录单
						fReceipt.setReceiptMode(skfs);//set 收款方式 现金  银行卡
						fReceipt.setJsr(new User(UserUtils.getUser().getId()));//set 经手人
						fReceipt.setApprovalStatus("1");//set 审批状态1已审批
						//----------------------------调用收款账户保存方法

						//    收款记录表添加

						fIncomeRecord.setOrderId(zddId);
						fIncomeRecord.setIncomeAccount(skzh);
						fIncomeRecord.setTraverAccount(lwzh);
						fIncomeRecord.setIncomeMoney(cRkckddinfoList.get(0).getSjje());
						fIncomeRecord.setIncomeDate(new Date());
						fIncomeRecord.setJsr(UserUtils.getUser().getId());
						fIncomeRecord.setIncomeMode("9");
						fIncomeRecord.setIncomeType(skfs);
						//--------------------------调用收款记录表保存方法

						//账户表余额减少f_account

						fAccount.setId(skzh);
						fAccount.setAccountBalance(cRkckddinfoList.get(0).getSjje());
						//------------------调用账户增加方法

						fAccounttwo.setId(lwzh);
						fAccounttwo.setAccountBalance(cRkckddinfoList.get(0).getSjje());
						//------------------调用账户减少方法
					}
				}
			}
		}

		cRkckddinfoService.saveInfo(cRkckddinfo,fDiscount,cDdinfoList,fReceipt,fIncomeRecord,fAccount,fAccounttwo,biaoshi);
		return "true";
	}

	/**		新版出库入库方法结束		**/

	@RequiresPermissions("ck:cCkinfo:view")
	@RequestMapping(value = "libraryPrint")//打印销售单
	public String libraryPrint(CRkckddinfo cRkckddinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(cRkckddinfo!=null&&StringUtils.isNotBlank(cRkckddinfo.getId())) {
			CDdinfo cDdinfo = new CDdinfo();
			cDdinfo.setRkckddinfo(cRkckddinfo);
			List<CDdinfo> list = cDdinfoService.findList(cDdinfo);
			cDdinfoService.processUnit(list);
			double sumMoney = 0;
			int sumNub = 0;
			for(CDdinfo cd: list){
				int nub = Integer.parseInt(cd.getNub());
				sumMoney += Double.parseDouble(cd.getJe())*nub;
				sumNub += nub;
			}
			BigDecimal numberOfMoney = new BigDecimal(sumMoney);
			model.addAttribute("CNMoney", NumberToCN.number2CNMontrayUnit(numberOfMoney));
			model.addAttribute("store", cStoreService.get(cRkckddinfo.getStore()));
			model.addAttribute("cRkckddinfo", cRkckddinfo);
			model.addAttribute("list", list);
			model.addAttribute("date", new Date());
			model.addAttribute("sumMoney", sumMoney);
			model.addAttribute("sumNub", sumNub);
			model.addAttribute("user", UserUtils.getUser());
			return "modules/ck/cDdinfoPrint";
		}else {
			return "error/400";
		}
	}
	@RequestMapping(value = "query")
	public String query(CRkckddinfo cRkckddinfo,String pageName,HttpServletRequest request,HttpServletResponse response,Model model,String id,String name){
       if(StringUtils.isNotBlank(id)) {
		   cRkckddinfo.setCreateBy(new User(id));
	   }
		if(StringUtils.isNotBlank(name)) {
			cRkckddinfo.setStore(new CStore(name));
		}
		Page<CRkckddinfo> page = cRkckddinfoService.findPage(new Page<CRkckddinfo>(request,response),cRkckddinfo);
		int cd=page.getList().size();
		Double  mony=0.0;
		Double htje=0.0;
		Double sum =0.0;
		for(int i=0; i<page.getList().size();i++){
			cRkckddinfo=page.getList().get(i);
			htje =(StringUtils.isNotBlank(cRkckddinfo.getHtje()))?Double.parseDouble(cRkckddinfo.getHtje()):0.0;
			mony=htje;
			sum=sum+mony;
		}
		List<CRkckddinfo> list=cRkckddinfoService.findOrderCodeList(cRkckddinfo);
		List<CRkckddinfo> storelist=cRkckddinfoService.findcxOrderList(cRkckddinfo);
		model.addAttribute("storelist",storelist);
		model.addAttribute("list",list);
		model.addAttribute("houseList",houseService.findList(new CHouse()));
		model.addAttribute("goodsList",cGoodsService.findList(new CGoods()));
		model.addAttribute("cd",cd);
//		model.addAttribute("ywycd",ywycd);
		model.addAttribute("list",list);
		model.addAttribute("page",page);
		model.addAttribute("sum",sum);
		model.addAttribute("cRkckddinfo",cRkckddinfo);
		return "modules/ck/ckQuery";
	}



	/**
	 * 业务员销售单查询
	 * @param cRkckddinfo
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("ck:cCkinfo:view")
	@RequestMapping(value = "clerkSales")
	public String clerkSales(CRkckddinfo cRkckddinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		cRkckddinfo.setLx("1");//出库
		cRkckddinfo.setState("2");
		Page<CRkckddinfo> page = cRkckddinfoService.findPage(new Page<CRkckddinfo>(request, response), cRkckddinfo);
		model.addAttribute("cRkckddinfo", cRkckddinfo);
		model.addAttribute("page", page);
		return "modules/ck/cRkckClerkSales";
	}

	@RequiresPermissions("ck:cRkckddinfo:view")
	@RequestMapping(value = "shenhelist")
	public String shenhelist(CRkckddinfo cRkckddinfo,CStore cStore, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<CRkckddinfo> list = cRkckddinfoService.selectList("2",cRkckddinfo);
		int a= list.size();
			model.addAttribute("a", a);
	    List<CRkckddinfo> list1=cRkckddinfoService.selectList("5",cRkckddinfo);
	     int th=list1.size();
	     model.addAttribute("th",th);
	     List<CRkckddinfo> list2=cRkckddinfoService.selectList("4",cRkckddinfo);
	     int bf=list2.size();
	     model.addAttribute("bf",bf);
	     List<CRkckddinfo> list3=cRkckddinfoService.selectlx("1",cRkckddinfo);
	     int ruku=list3.size();
         model.addAttribute("ruku",ruku);
         List<CStore> list4=cStoreService.tslist(cStore);
         int kehu=list4.size();
         model.addAttribute("kehu",kehu);
			return "modules/ck/shenheList";
	}

	/**
	 * 出库提交订单打开填写财务信息页面（收款）
	 * @param fReceipt
	 * @param model
	 * @return
	 */
	@RequiresPermissions("ck:cCkinfo:edit")
	@RequestMapping(value = "submitOrder")
	public String submitOrder(FReceipt fReceipt, Model model){
		CShop cs = new CShop();
		cs.setUserid(UserUtils.getUser().getId());
		cs.setState(UserUtils.getCache("RKCKSTATE").toString());
		List<CShop> csList = cShopService.findList(cs);
		double orderTotal = 0.0;
		double yhTotal = 0.0;
		for (CShop cShop: csList){

			orderTotal += (cShop.getJe()*Integer.parseInt(cShop.getNub()));
			if(cShop.getYhje()!=null)yhTotal += cShop.getYhje();
		}
		fReceipt.setHtje(String.valueOf(orderTotal));
		fReceipt.setJe(String.valueOf(orderTotal-yhTotal));
		model.addAttribute("storeList", cStoreService.findList(new CStore()));
		model.addAttribute("houseList", houseService.findList(new CHouse()));
		model.addAttribute("toDiscount", (yhTotal > 0));
		model.addAttribute("fReceipt", fReceipt);
		return "modules/ck/submitOrder";
	}

	/**
	 * 出库提交订单财务信息(收款)保存
	 * @param fReceipt
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("ck:cCkinfo:edit")
	@RequestMapping(value = "submitOrderSave")
	public String submitOrderSave(FReceipt fReceipt, Model model) {
		if (!beanValidator(model, fReceipt)){
			return null;
		}
		fReceipt.setReceiptType(UserUtils.getCache("RKCKSTATE").toString());
		if(fReceipt.getReceiptDate()==null)fReceipt.setReceiptDate(new Date());
		fReceiptService.save(fReceipt);
		return fReceipt.getId()+","+fReceipt.getHouseId();
	}
	/**
	 * 出库提交订单打开填写财务信息页面（付款）
	 * @param payment
	 * @param model
	 * @return
	 */
	@RequiresPermissions("ck:cCkinfo:view")
	@RequestMapping(value = "submitOrderPayment")
	public String submitOrderPayment(FPayment payment, Model model){
		CShop cs = new CShop();
		cs.setUserid(UserUtils.getUser().getId());
		cs.setState(UserUtils.getCache("RKCKSTATE").toString());
		List<CShop> csList = cShopService.findList(cs);
		double orderTotal = 0.0;
		double yhTotal = 0.0;
		for (CShop cShop: csList){
			orderTotal += (cShop.getJe()*Integer.parseInt(cShop.getNub()));
			yhTotal += cShop.getYhje();
		}
		payment.setJe(String.valueOf(orderTotal));
		model.addAttribute("storeList", cStoreService.findList(new CStore()));
		model.addAttribute("houseList", houseService.findList(new CHouse()));
		model.addAttribute("toDiscount", (yhTotal > 0));
		model.addAttribute("payment", payment);
		return "modules/ck/submitOrderPayment";
	}

	/**
	 * 出库提交订单财务信息(收款)保存
	 * @param payment
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("ck:cCkinfo:edit")
	@RequestMapping(value = "submitOrderPaymentSave")
	public String submitOrderPaymentSave(FPayment payment, Model model) {
		if (!beanValidator(model, payment)){
			return null;
		}
		payment.setPaymentType(UserUtils.getCache("RKCKSTATE").toString());
		fPaymentService.save(payment);
		return payment.getId()+","+payment.getHouseId();
	}

	/**		出库结束		**/


	@ResponseBody
	@RequestMapping(value = "zhanghuAdd")
	public List<FAccount> zhanghuAdd() {
		List<FAccount> fAccountList=fAccountService.findList(new FAccount());
		return fAccountList;
	}



	@RequiresPermissions("ck:cCginfo:view")
	@RequestMapping(value = "thSave")
	public String thSave(CRkckddinfo cRkckddinfo, Model model, RedirectAttributes redirectAttributes) {
	String thxx=cRkckddinfo.getThxx();
	if(thxx.equals("")){
		return "modules/ck/returnGoodsList";
	}else{
//		ArrayList<Object> thxxList=new ArrayList<Object>();
		String[] ss=thxx.split(",");
		for(int i=0;i<ss.length;i++){
			CDdinfo cddinfo=new CDdinfo();
			String[] thss=ss[i].split("-");
			cddinfo.setId(thss[0]);   //设置ID
			cddinfo.setThsl(thss[1]);  //设置退货数量
			cddinfo.setThje(cRkckddinfo.getSjje()); //设置实际金额
			cddinfo.setZh(cRkckddinfo.getfAccount().getName());//设置收付款账户
			cddinfo.setThsj(new Date());//设置退货日期
			cddinfo.setThck(cRkckddinfo.getcHouse().getId()); //设置出入库仓库
			cddinfo.setSpr(UserUtils.getUser()); //设置审批人
			cddinfo.setSpzt("0");  //审批状态     0待审批    1审批通过

			//判断是供应商还是客户
			CDdinfo cddinfos=new CDdinfo();
			cddinfos.setId(thss[0]);
			List<CDdinfo> cDdinfos=cDdinfoService.findList(cddinfos);
			CSupplier cSupplier=new CSupplier();
			String ddid=cDdinfos.get(0).getStore().getId();
			if(StringUtils.isNotBlank(ddid)){//客户
				//添加付款单
				FPayment fPayment=new FPayment();
				fPayment.setPaymentDate(new Date());//付款日期
				fPayment.setPaymentCode(cRkckddinfo.getId());//设置单据编号
				fPayment.setJe(cRkckddinfo.getSjje());//设置金额
				cSupplier.setId(ddid);
				fPayment.setTravelUnit(cSupplier);
				String faName=cRkckddinfo.getfAccount().getName();
				if(StringUtils.isNotBlank(faName)){
					fPayment.setTravelAccount(faName);//设置账户
				}
				fPayment.setPaymentMode(cRkckddinfo.getfPayment().getPaymentMode());
				fPayment.setPaymentType("0");
				fPaymentService.saveDefualt(fPayment);
				//设置子订单表中首付款ID
				cddinfo.setSfkId(fPayment.getId());
				cDdinfoService.thUpdate(cddinfo);
			}else{
				//添加收款单
				FReceipt fReceipt=new FReceipt();//供应商
				fReceipt.setReceiptDate(new Date()); //设置收款日期
				fReceipt.setReceiptCode(cRkckddinfo.getId());//设置单据编号
				CStore cStore=new CStore();
				cStore.setId(cDdinfos.get(0).getSupplier().getId());
				fReceipt.setTravelUnit(cStore);//设置来往单位
				fReceipt.setReceiptAccount(cRkckddinfo.getfAccount().getName());//设置账户
				fReceipt.setJe(cRkckddinfo.getSjje());
				fReceipt.setReceiptMode(cRkckddinfo.getfPayment().getPaymentMode());
				fReceipt.setReceiptType("0");
				fReceiptService.saveDefualt(fReceipt);
				//设置子订单表中首付款ID
				cddinfo.setSfkId(fReceipt.getId());
				cDdinfoService.thUpdate(cddinfo);
			}


		}
	}
		return "modules/ck/returnGoodsList";
	}
}