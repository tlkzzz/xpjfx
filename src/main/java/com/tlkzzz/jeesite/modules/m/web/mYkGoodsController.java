package com.tlkzzz.jeesite.modules.m.web;

import com.tlkzzz.jeesite.common.web.BaseController;
import com.tlkzzz.jeesite.modules.ck.entity.*;
import com.tlkzzz.jeesite.modules.ck.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/5/11 0011.
 */
@Controller
@RequestMapping(value = "${adminPath}/m/mYkGoods")
public class mYkGoodsController extends BaseController {
    @Autowired
    private CHgoodsService cHgoodsService;
    @Autowired
    private CGoodsService cGoodsService;
    @Autowired
    private CCkinfoService cCkinfoService;
    @Autowired
    private CRkinfoService cRkinfoService;
    @Autowired
    private CYkinfoService cYkinfoService;
    @ResponseBody
    @RequestMapping(value = {"list"})
    public String list(String ycck, String yrck,String xzGoods,String shul,String cbj) {
        //移出库存减少
        CHgoods cHgoods=new CHgoods();
        cHgoods.getHouse().setId(ycck);
        cHgoods.getGoods().setId(xzGoods);
        List<CHgoods> mkcList=cHgoodsService.mkc(cHgoods);
        Double nub=Double.parseDouble(mkcList.get(0).getNub());//出库库存
        Double shuls=Double.parseDouble(shul);
        Double ckhnub=nub-shuls;
        cHgoods.setId(mkcList.get(0).getId());
        cHgoods.setNub(ckhnub.toString());
        cHgoodsService.kcsl(cHgoods);
        //出库记录添加
        CGoods cGoods=new CGoods();
        cGoods.setId(xzGoods);
        List<CGoods> cGoodsList=cGoodsService.findList(cGoods);
        CCkinfo cCkinfo=new CCkinfo();
        cCkinfo.setJe(cbj);
        cCkinfo.setCkqcbj(cGoodsList.get(0).getCbj());
        cCkinfo.setCkhcbj(cGoodsList.get(0).getCbj());
        cCkinfo.setNub(shul);
        cCkinfo.getGoods().setId(xzGoods);
        cCkinfo.getHouse().setId(ycck);
        cCkinfo.setCkdate(new Date());
        cCkinfo.setState("9");
        cCkinfo.setIssp("1");
        cCkinfoService.save(cCkinfo);
        //移入库存添加
        CHgoods rkcHgoods=new CHgoods();
        rkcHgoods.getHouse().setId(yrck);
        rkcHgoods.getGoods().setId(xzGoods);
        List<CHgoods> rkkcList=cHgoodsService.mkc(cHgoods);
        CRkinfo cRkinfo=new CRkinfo();
        if(rkkcList.size()>0){
            Double rnub=Double.parseDouble(rkkcList.get(0).getNub());
            Double rshuls=Double.parseDouble(shul);
            Double rkhnub=rnub+rshuls;
            rkcHgoods.setId(rkkcList.get(0).getId());
            rkcHgoods.setNub(rkhnub.toString());
            cHgoodsService.kcsl(rkcHgoods);
            Double qcbj=Double.parseDouble(cGoodsList.get(0).getCbj());
            Double rkhcbj=(qcbj*rnub+rshuls*Double.parseDouble(cbj))/rkhnub;//计算入库后成本价
            //添加入库记录表
            cRkinfo.getGoods().setId(xzGoods);
            cRkinfo.getHouse().setId(yrck);
            cRkinfo.setRknub(shul);
            cRkinfo.setRkhnub(rkhnub.toString());
            cRkinfo.setRkqcbj(qcbj.toString());
            cRkinfo.setRkhcbj(rkhcbj.toString());
            cRkinfo.setState("9");
            cRkinfoService.save(cRkinfo);
        }else{
            rkcHgoods.setNub(shul);
            cHgoodsService.save(rkcHgoods);
            //添加入库记录表
            cRkinfo.getGoods().setId(xzGoods);
            cRkinfo.getHouse().setId(yrck);
            cRkinfo.setRknub(shul);
            cRkinfo.setRkhnub(shul);
            cRkinfo.setRkqcbj(cbj);
            cRkinfo.setRkhcbj(cbj);
            cRkinfo.setState("9");
            cRkinfoService.save(cRkinfo);
        }
        //添加移库记录
        CYkinfo cYkinfo=new CYkinfo();
        cYkinfo.getGoods().setId(xzGoods);
        cYkinfo.getStartHouse().setId(ycck);
        cYkinfo.getEndHouse().setId(yrck);
        cYkinfo.setNub(shul);
        cYkinfo.setCbj(cGoodsList.get(0).getCbj());
        cYkinfo.setXsj(cGoodsList.get(0).getSj());
        cYkinfoService.save(cYkinfo);
        return "true";
    }
}
