package com.tlkzzz.jeesite.modules.m.web;

import com.tlkzzz.jeesite.common.utils.IdGen;
import com.tlkzzz.jeesite.common.utils.StringUtils;
import com.tlkzzz.jeesite.common.web.BaseController;
import com.tlkzzz.jeesite.modules.ck.entity.*;
import com.tlkzzz.jeesite.modules.ck.service.*;
import com.tlkzzz.jeesite.modules.cw.entity.FArrears;
import com.tlkzzz.jeesite.modules.cw.entity.FDiscount;
import com.tlkzzz.jeesite.modules.cw.service.FArrearsService;
import com.tlkzzz.jeesite.modules.cw.service.FDiscountService;
import com.tlkzzz.jeesite.modules.sys.entity.User;
import com.tlkzzz.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/5/15 0015.erfsdfsdfsdf
 */
@Controller
@RequestMapping(value = "${adminPath}/m/mXsAdd")
public class mXsAddController extends BaseController {

    @Autowired
    private CXsddlsService cXsddlsService;
    @Autowired
    private CCarUserService cCarUserService;
    @Autowired
    private CHgoodsService cHgoodsService;
    @Autowired
    private CHouseService cHouseService;
    @Autowired
    private CGoodsService cGoodsService;
    @Autowired
    private CCkinfoService cCkinfoService;
    @Autowired
    private CDdinfoService cDdinfoService;
    @Autowired
    private FDiscountService fDiscountService;
    @Autowired
    private FArrearsService fArrearsService;
    @Autowired
    private CRkckddinfoService cRkckddinfoService;
    @ResponseBody
    @RequestMapping(value = {"list"})
    public String list(String s,String nub,String sj,String storeId,String tjval,String shje,String dates,String ckxz) {
        CHgoods cHgoods=new CHgoods();
        cHgoods.setHouse(new CHouse(ckxz));
        cHgoods.setGoods(new CGoods(s));
        List<CHgoods> cHgoodsList=cHgoodsService.findList(cHgoods);
        Double kcnub=Double.parseDouble(cHgoodsList.get(0).getKynub());
        Double ckhnub=kcnub-Double.parseDouble(nub);
        if(ckhnub<0){
                return "false";
            }
        CXsddls cXsddls=new CXsddls();
        if(StringUtils.isBlank(tjval)){
    //"".equals(tjval)&&tjval.equals(null
            cXsddls.setFid(IdGen.uuid());
        }else{
            cXsddls.setFid(tjval);
        }
        if(StringUtils.isNotBlank(dates)){
            //"".equals(dates)&&dates.equals(null)
            cXsddls.setDates(dates);
        }
        cXsddls.setNub(nub);
        cXsddls.setSj(sj);
        cXsddls.setStoreId(storeId);
        cXsddls.setGoodsid(s);
        cXsddls.setSjje(shje);
        cXsddls.setUserid(UserUtils.getUser().getId());
        cXsddlsService.save(cXsddls);
        String fid=cXsddls.getFid();
        return fid;
    }

    @ResponseBody
    @RequestMapping(value = {"fylist"})
    public List<CXsddls> fylist(int fybs){
        CXsddls cXsddls=new CXsddls();
        cXsddls.setFybs(fybs);
        String ids= UserUtils.getUser().getId();
        if(ids.equals("1")){
            List<CXsddls> cXsddlsList=cXsddlsService.fyfindList(cXsddls);
            return cXsddlsList;
        }else{
            cXsddls.setUserid(ids);
            List<CXsddls> cXsddlsList=cXsddlsService.fyfindList(cXsddls);
            return cXsddlsList;
        }
    }

    @ResponseBody
    @RequestMapping(value = {"dhxq"})
    public List<CXsddls> dhxq(String ids){
        CXsddls cXsddls=new CXsddls();
        cXsddls.setId(ids);
        List<CXsddls> cXsddlsList=cXsddlsService.findList(cXsddls);
        return cXsddlsList;
    }

    @ResponseBody
    @RequestMapping(value = "delete")
    public String delete(String ss,String tj,String storeId) {
        CXsddls cXsddls=new CXsddls();
        cXsddls.setGoodsid(ss);
        cXsddls.setFid(tj);
        cXsddls.setStoreId(storeId);
        List<CXsddls> cXsddlsList=cXsddlsService.findList(cXsddls);
        if(cXsddlsList.size()==0){
            return "false";
        }else{
            String sj=cXsddlsList.get(0).getSj();
            cXsddlsService.delete(cXsddls);
            return sj;
        }

    }

    @ResponseBody
    @RequestMapping(value = {"xsckck"})
    public List<CCarUser> xsckck(String userid) {
        CCarUser cCarUser=new CCarUser();
        cCarUser.setUser(new User(UserUtils.getUser().getId()));
        List<CCarUser> cCarUserList=cCarUserService.xsckck(cCarUser);
        return cCarUserList;
    }

    @ResponseBody
    @RequestMapping(value = {"save"})
    public String save(String xsck,String tjval,String state) {
        Double zysj=0.00;
        Double zhtje=0.00;
        Double zsjje=0.00;
        //根据名称获取仓库ID
//        List<CHouse> cHouseList=cHouseService.getname(xsck);
//        String houseId=cHouseList.get(0).getId();//仓库ID
        //获取临时表中数据，销售订单出库信息
        if(StringUtils.isNotBlank(state)){
            //!"".equals(state)&&!state.equals(null)
            CXsddls tcXsddls=new CXsddls();
            tcXsddls.setFid(tjval);
            tcXsddls.setState(state);
            cXsddlsService.stateUpdate(tcXsddls);
        }
        CXsddls cXsddls=new CXsddls();
        cXsddls.setFid(tjval);
        List<CXsddls> cXsddlsList=cXsddlsService.findList(cXsddls);
        for(int i=0;i<cXsddlsList.size();i++){
            //减少库存
            String goodsid=cXsddlsList.get(i).getGoodsid();
            Double nub=Double.parseDouble(cXsddlsList.get(i).getNub());
            CHgoods cHgoods=new CHgoods();
            cHgoods.setHouse(new CHouse(xsck));
            cHgoods.setGoods(new CGoods(goodsid));
            List<CHgoods> cHgoodsList=cHgoodsService.findList(cHgoods);
            Double kcnub=Double.parseDouble(cHgoodsList.get(0).getKynub());
            Double ckhnub=kcnub-nub;
//            if(ckhnub<0){
//                return "false";
//            }else{
                cHgoods.setId(cHgoodsList.get(0).getId());
                cHgoods.setKynub(ckhnub.toString());
                cHgoodsService.kcsl(cHgoods);
//            }
            //查询商品成本价
            CGoods cGoods=new CGoods();
            cGoods.setId(goodsid);
            List<CGoods> cGoodsList=cGoodsService.findList(cGoods);
            //添加出库记录表
            CCkinfo cCkinfo=new CCkinfo();
            cCkinfo.setJe(cXsddlsList.get(i).getSj());
            cCkinfo.setCkqcbj(cGoodsList.get(0).getCbj());
            cCkinfo.setCkhcbj(cGoodsList.get(0).getCbj());
            cCkinfo.setNub(cXsddlsList.get(i).getNub());
            cCkinfo.setGoods(new CGoods(goodsid));
            cCkinfo.setHouse(new CHouse(xsck));
            cCkinfo.setStore(new CStore(cXsddlsList.get(i).getStoreId()));
            cCkinfo.setCkdate(new Date());
            cCkinfo.setState("4");
            cCkinfo.setIssp("1");
            cCkinfoService.save(cCkinfo);
            //添加子订单
            CDdinfo cDdinfo=new CDdinfo();
            cDdinfo.setRkckddinfo(new CRkckddinfo(cXsddlsList.get(i).getFid()));
            cDdinfo.setHouse(new CHouse(xsck));
            cDdinfo.setGoods(new CGoods(goodsid));
            cDdinfo.setStore(new CStore(cXsddlsList.get(i).getStoreId()));
            cDdinfo.setJe(cXsddlsList.get(i).getSj());
            cDdinfo.setDdbh("P"+new Date().getTime());
            cDdinfo.setNub(cXsddlsList.get(i).getNub());
            cDdinfo.setRkqcbj(cGoodsList.get(0).getCbj());
            cDdinfo.setRksjcbj(cGoodsList.get(0).getCbj());
            cDdinfo.setRkckdate(new Date());
            Double cbj=Double.parseDouble(cGoodsList.get(0).getCbj());
            Double htje=Double.parseDouble(cXsddlsList.get(i).getSj());
            Double ysj=cbj*nub;
            Double yhje=ysj-htje;
            if(yhje!=0){
                cDdinfo.setYhje(yhje.toString());
                cDdinfoService.save(cDdinfo);
            }
            //添加总订单
            zysj=zysj+ysj;
            zhtje=zhtje+htje;
            Double sjje=Double.parseDouble(cXsddlsList.get(i).getSjje());
            zsjje=zsjje+sjje;
            if(i==cXsddlsList.size()-1){
                CRkckddinfo cRkckddinfo=new CRkckddinfo();
                cRkckddinfo.setId(cXsddlsList.get(i).getFid());
                cRkckddinfo.setDdbh("Z"+new Date().getTime());
                cRkckddinfo.setLx("1");
                cRkckddinfo.setState("2");
                cRkckddinfo.setIssp("0");
                cRkckddinfo.setJe(zysj.toString());
                cRkckddinfo.setHtje(zhtje.toString());
                cRkckddinfo.setSjje(zsjje.toString());
                cRkckddinfo.setcHouse(new CHouse(xsck));
                cRkckddinfoService.save(cRkckddinfo);
                //添加优惠记录表
                Double zyhje=zysj-zhtje;
                if(zyhje!=0){
                    FDiscount fDiscount=new FDiscount();
                    fDiscount.setLx("0");
                    fDiscount.setYhje(zyhje.toString());
                    fDiscount.setStore(new CStore(cXsddlsList.get(i).getStoreId()));
                    fDiscount.setDdid(new CRkckddinfo(cXsddlsList.get(i).getFid()));
                    fDiscountService.save(fDiscount);
                }
                Double zqk=zhtje-zsjje;
                //添加欠款表
                if(zqk!=0){
                    FArrears fArrears=new FArrears();
                    fArrears.setRkckdd(new CRkckddinfo(cXsddlsList.get(i).getFid()));
                    fArrears.setArrearsUnit(cXsddlsList.get(i).getStoreId());
                    fArrears.setArrearsMode("0");
                    fArrears.setArrearsType("0");
                    fArrears.setTotal(zqk.toString());
                    fArrears.setArrearsDate(new Date());
                    fArrearsService.save(fArrears);
                }
            }
        }
        return "true";
    }
}
