package com.tlkzzz.jeesite.modules.m.web;

import com.tlkzzz.jeesite.common.utils.StringUtils;
import com.tlkzzz.jeesite.common.web.BaseController;
import com.tlkzzz.jeesite.modules.ck.entity.*;
import com.tlkzzz.jeesite.modules.ck.service.*;
import com.tlkzzz.jeesite.modules.sys.entity.User;
import com.tlkzzz.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/10 0010.
 */
@Controller
@RequestMapping(value = "${adminPath}/m/mRkGoods")
public class mRkGoodsController extends BaseController{
    @Autowired
    private CHgoodsService cHgoodsService;
    @Autowired
    private CGoodsService cGoodsService;
    @Autowired
    private CRkinfoService cRkinfoService;
    @Autowired
    private CHouseService cHouseService;
    @Autowired
    private CSupplierService cSupplierService;

    /**
     * APP入库添加，入库记录，库存增加
     * */
    @ResponseBody
    @RequestMapping(value = {"list"})
    public String list(String rkMode, String ckxz,String gysxz,String xzGoods,String shul,String cbj) {
    //添加库存
    CHgoods cHgoods=new CHgoods();
        cHgoods.setGoods(new CGoods(xzGoods));
        cHgoods.setHouse(new CHouse(ckxz));
        List<CHgoods> mkcList=cHgoodsService.mkc(cHgoods);
        CRkinfo cRkinfo=new CRkinfo();
        if(mkcList.size()>0){
            Double nub=Double.parseDouble(mkcList.get(0).getNub());
            Double shuls=Double.parseDouble(shul);
            Double rkhnub=nub+shuls;
            cHgoods.setId(mkcList.get(0).getId());
            cHgoods.setNub(rkhnub.toString());
            cHgoodsService.kcsl(cHgoods);
            CGoods cGoods=new CGoods();
            cGoods.setId(xzGoods);
            List<CGoods> cGoodsList=cGoodsService.findList(cGoods);
            Double qcbj=Double.parseDouble(cGoodsList.get(0).getCbj());
            Double rkhcbj=(qcbj*nub+shuls*Double.parseDouble(cbj))/rkhnub;//计算入库后成本价
            //添加入库记录表
            cRkinfo.setGoods(new CGoods(xzGoods));
            cRkinfo.setHouse(new CHouse(ckxz));
            cRkinfo.setRknub(shul);
            cRkinfo.setRkhnub(rkhnub.toString());
            cRkinfo.setRkqcbj(qcbj.toString());
            cRkinfo.setRkhcbj(rkhcbj.toString());
            cRkinfo.setState(rkMode);
            cRkinfo.setSupplier(new CSupplier(gysxz));
            cRkinfoService.save(cRkinfo);
            return "true";
        }else{
            cHgoods.setNub(shul);
            cHgoodsService.appSave(cHgoods);
            //添加入库记录表
            cRkinfo.setGoods(new CGoods(xzGoods));
            cRkinfo.setHouse(new CHouse(ckxz));
            cRkinfo.setRknub(shul);
            cRkinfo.setRkhnub(shul);
            cRkinfo.setRkqcbj(cbj);
            cRkinfo.setRkhcbj(cbj);
            cRkinfo.setState(rkMode);
            cRkinfo.setSupplier(new CSupplier(gysxz));
            cRkinfoService.save(cRkinfo);
            return "true";
        }
    }

    @ResponseBody
    @RequestMapping(value = {"ckxz"})
    public List<CHouse> ckxz(){
        List<CHouse> cHouseList=cHouseService.findList(new CHouse());
        return cHouseList;
    }

    @ResponseBody
    @RequestMapping(value = {"gysxz"})
    public List<CSupplier> gysxz(){
        List<CSupplier> cSupplierList=cSupplierService.findList(new CSupplier());
        return cSupplierList;
    }

    @ResponseBody
    @RequestMapping(value = {"rukjl"})
    public List<CRkinfo> rukjl(int fybs){
            CRkinfo cRkinfo=new CRkinfo();
            cRkinfo.setFybs(fybs);
            String ids=UserUtils.getUser().getId();
            if(ids.equals("1")){
                List<CRkinfo> cRkinfoList=cRkinfoService.fyfindList(cRkinfo);
                return cRkinfoList;
            }else{
                cRkinfo.setCreateBy(new User(UserUtils.getUser().getId()));
                List<CRkinfo> cRkinfoList=cRkinfoService.fyfindList(cRkinfo);
                return cRkinfoList;
            }
    }

    @ResponseBody
    @RequestMapping(value = {"rukxq"})
    public List<CRkinfo> rukxq(String ids){
        CRkinfo cRkinfo=new CRkinfo();
        cRkinfo.setId(ids);
        List<CRkinfo> cRkinfoList=cRkinfoService.findList(cRkinfo);
        return cRkinfoList;
    }
}
