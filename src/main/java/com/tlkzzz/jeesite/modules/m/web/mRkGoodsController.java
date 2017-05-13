package com.tlkzzz.jeesite.modules.m.web;

import com.tlkzzz.jeesite.common.web.BaseController;
import com.tlkzzz.jeesite.modules.ck.entity.CGoods;
import com.tlkzzz.jeesite.modules.ck.entity.CHgoods;
import com.tlkzzz.jeesite.modules.ck.entity.CRkinfo;
import com.tlkzzz.jeesite.modules.ck.service.CGoodsService;
import com.tlkzzz.jeesite.modules.ck.service.CHgoodsService;
import com.tlkzzz.jeesite.modules.ck.service.CRkinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

    /**
     * APP入库添加，入库记录，库存增加
     * */
    @ResponseBody
    @RequestMapping(value = {"list"})
    public String list(String rkMode, String ckxz,String gysxz,String xzGoods,String shul,String cbj) {
    //添加库存
    CHgoods cHgoods=new CHgoods();
        cHgoods.getGoods().setId(xzGoods);
        cHgoods.getHouse().setId(ckxz);
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
            cRkinfo.getGoods().setId(xzGoods);
            cRkinfo.getHouse().setId(ckxz);
            cRkinfo.setRknub(shul);
            cRkinfo.setRkhnub(rkhnub.toString());
            cRkinfo.setRkqcbj(qcbj.toString());
            cRkinfo.setRkhcbj(rkhcbj.toString());
            cRkinfo.setState(rkMode);
            cRkinfo.getSupplier().setId(gysxz);
            cRkinfoService.save(cRkinfo);
            return "true";
        }else{
            cHgoods.setNub(shul);
            cHgoodsService.save(cHgoods);
            //添加入库记录表
            cRkinfo.getGoods().setId(xzGoods);
            cRkinfo.getHouse().setId(ckxz);
            cRkinfo.setRknub(shul);
            cRkinfo.setRkhnub(shul);
            cRkinfo.setRkqcbj(cbj);
            cRkinfo.setRkhcbj(cbj);
            cRkinfo.setState(rkMode);
            cRkinfo.getSupplier().setId(gysxz);
            cRkinfoService.save(cRkinfo);
            return "true";
        }
    }

}
