package com.tlkzzz.jeesite.modules.m.web;

import com.tlkzzz.jeesite.common.web.BaseController;
import com.tlkzzz.jeesite.modules.ck.entity.CRkckddinfo;
import com.tlkzzz.jeesite.modules.ck.service.CRkckddinfoService;
import com.tlkzzz.jeesite.modules.cw.entity.FAccount;
import com.tlkzzz.jeesite.modules.cw.entity.FPayment;
import com.tlkzzz.jeesite.modules.cw.entity.FReceipt;
import com.tlkzzz.jeesite.modules.cw.service.FAccountService;
import com.tlkzzz.jeesite.modules.cw.service.FPaymentService;
import com.tlkzzz.jeesite.modules.cw.service.FReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/5/23 0023.
 */
@Controller
@RequestMapping(value = "${adminPath}/m/mkhShouk")
public class mkhShoukController extends BaseController {
    @Autowired
    private FAccountService fAccountService;
    @Autowired
    private CRkckddinfoService cRkckddinfoService;
    @Autowired
    private FReceiptService fReceiptService;
    @Autowired
    private FPaymentService fPaymentService;
    @ResponseBody
    @RequestMapping(value = {"list"})
    public String list() {
        return "false";
    }

    @ResponseBody
    @RequestMapping(value = {"zhanghuXz"})
    public List<FAccount> zhanghuXz() {
        List<FAccount> fAccountList=fAccountService.findList(new FAccount());
        return fAccountList;
    }

    @ResponseBody
    @RequestMapping(value = {"ddbhxz"})
    public List<CRkckddinfo> ddbhxz(String fybs,String sous) {
        CRkckddinfo cRkckddinfo=new CRkckddinfo();
        cRkckddinfo.setFybs(Integer.parseInt(fybs));
        cRkckddinfo.setDdbh(sous);
        List<CRkckddinfo> cRkckddinfoList=cRkckddinfoService.fyfindList(cRkckddinfo);
        return cRkckddinfoList;
    }

    @ResponseBody
    @RequestMapping(value = {"kehskSave"})
    public String kehskSave(String khxz,String ddbh,String zh,String fyType,String je) {
        FReceipt fReceipt=new FReceipt();
        fReceipt.setReceiptDate(new Date());
        fReceipt.setReceiptCode(ddbh);
//        fReceipt.setTravelUnit(khxz);
        fReceipt.setReceiptAccount(zh);
        fReceipt.setReceiptType(fyType);
        fReceipt.setReceiptMode("0");
        fReceipt.setApprovalStatus("0");
        fReceipt.setJe(je);
        fReceiptService.save(fReceipt);
        return "true";
    }

    @ResponseBody
    @RequestMapping(value = {"kehfkSave"})
    public String kehfkSave(String fkeh,String fddbh,String fkzh,String ffyType,String fje) {
        FPayment fPayment=new FPayment();
        fPayment.setPaymentDate(new Date());
        fPayment.setPaymentCode(fddbh);
        fPayment.setPaymentAccount(fkzh);
//        fPayment.setTravelUnit(new C);   来往单位客户类型
        fPayment.setPaymentType(ffyType);
        fPayment.setPaymentMode("0");
        fPayment.setApprovalStatus("0");
        fPayment.setJe(fje);
        fPaymentService.save(fPayment);
        return "true";
    }

    }
