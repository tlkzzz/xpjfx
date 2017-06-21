package com.tlkzzz.jeesite.modules.m.web;

import com.tlkzzz.jeesite.common.web.BaseController;
import com.tlkzzz.jeesite.modules.ck.entity.CStore;
import com.tlkzzz.jeesite.modules.ck.service.CStoreService;
import com.tlkzzz.jeesite.modules.cw.entity.FPayment;
import com.tlkzzz.jeesite.modules.cw.entity.FReceipt;
import com.tlkzzz.jeesite.modules.cw.service.FPaymentService;
import com.tlkzzz.jeesite.modules.cw.service.FReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2017/5/16 0016.
 */
@Controller
@RequestMapping(value = "${adminPath}/m/mKhxx")
public class mKhxxController extends BaseController {
    @Autowired
    private CStoreService cStoreService;
    @Autowired
    private FPaymentService fPaymentService;
    @Autowired
    private FReceiptService fReceiptService;
    @ResponseBody
    @RequestMapping(value = {"list"})
    public List<CStore> list(int fybs) {
        CStore cStore=new CStore();
        cStore.setFybs(fybs);
        List<CStore> cStoreList=cStoreService.fyfindList(cStore);
        return cStoreList;
    }

    @ResponseBody
    @RequestMapping(value = "khxxlist")
    public List<CStore> khxxlist(String storeId) {
        CStore cStore=new CStore(storeId);
        List<CStore> cStoreList=cStoreService.findList(cStore);
        return cStoreList;
    }

    @ResponseBody
    @RequestMapping(value = "fukList")
    public List<FPayment> fukList(int fybs) {
        FPayment fPayment=new FPayment();
        fPayment.setFybs(fybs);
        List<FPayment> fPaymentList=fPaymentService.fyfindList(fPayment);
        return fPaymentList;
    }

    @ResponseBody
    @RequestMapping(value = "shoukList")
    public List<FReceipt> shoukList(int fybs) {
        FReceipt fReceipt=new FReceipt();
        fReceipt.setFybs(fybs);
        List<FReceipt> fReceiptList=fReceiptService.fyfindList(fReceipt);
        return fReceiptList;
    }

}
