package com.tlkzzz.jeesite.modules.m.web;

import com.tlkzzz.jeesite.common.web.BaseController;
import com.tlkzzz.jeesite.modules.ck.entity.CSupplier;
import com.tlkzzz.jeesite.modules.ck.service.CSupplierService;
import com.tlkzzz.jeesite.modules.cw.entity.FPayment;
import com.tlkzzz.jeesite.modules.cw.entity.FReceipt;
import com.tlkzzz.jeesite.modules.cw.service.FPaymentService;
import com.tlkzzz.jeesite.modules.cw.service.FReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/5/25 0025.
 */
@Controller
@RequestMapping(value = "${adminPath}/m/mGysSf")
public class mGysSfController extends BaseController {
    @Autowired
    private FReceiptService fReceiptService;
    @Autowired
    private FPaymentService fPaymentService;
    @Autowired
    private CSupplierService cSupplierService;
    @ResponseBody
    @RequestMapping(value = {"gysskSave"})
    public String gysskSave(String khxz,String ddbh,String lwzh,String zh,String fyType,String je) {
        FReceipt fReceipt=new FReceipt();
        fReceipt.setReceiptDate(new Date());
        fReceipt.setReceiptCode(ddbh);
//        fReceipt.setTravelUnit(khxz);
        fReceipt.setReceiptAccount(zh);
        fReceipt.setTravelAccount(lwzh);
        fReceipt.setReceiptType(fyType);
        fReceipt.setReceiptMode("0");
        fReceipt.setApprovalStatus("0");
        fReceipt.setJe(je);
        fReceiptService.save(fReceipt);
        return "true";
    }

    @ResponseBody
    @RequestMapping(value = {"gysfkSave"})
    public String gysfkSave(String fkeh,String fddbh,String flwzh,String fkzh,String ffyType,String fje) {
        FPayment fPayment=new FPayment();
        fPayment.setPaymentDate(new Date());
        fPayment.setPaymentCode(fddbh);
        fPayment.setPaymentAccount(fkzh);
        fPayment.setTravelAccount(flwzh);
//        fPayment.setTravelUnit(new C);   来往单位客户类型
        fPayment.setPaymentType(ffyType);
        fPayment.setPaymentMode("0");
        fPayment.setApprovalStatus("0");
        fPayment.setJe(fje);
        fPaymentService.save(fPayment);
        return "true";
    }

    @ResponseBody
    @RequestMapping(value = {"gysxz"})
    public List<CSupplier> gysxz(int fybs){
        CSupplier cSupplier=new CSupplier();
        cSupplier.setFybs(fybs);
        List<CSupplier> cSupplierList=cSupplierService.fyfindList(cSupplier);
        return cSupplierList;
    }

    @ResponseBody
    @RequestMapping(value = {"gysList"})
    public List<CSupplier> gysList(String storeId){
        List<CSupplier> cSupplierList=cSupplierService.findList(new CSupplier(storeId));
        return cSupplierList;
    }
}
