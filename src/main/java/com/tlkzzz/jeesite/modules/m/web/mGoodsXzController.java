package com.tlkzzz.jeesite.modules.m.web;

import com.tlkzzz.jeesite.common.web.BaseController;
import com.tlkzzz.jeesite.modules.ck.entity.CGclass;
import com.tlkzzz.jeesite.modules.ck.entity.CGoods;
import com.tlkzzz.jeesite.modules.ck.service.CGclassService;
import com.tlkzzz.jeesite.modules.ck.service.CGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/12 0012.
 */
@Controller
@RequestMapping(value = "${adminPath}/m/mGoodsXz")
public class mGoodsXzController extends BaseController {
    @Autowired
    private CGclassService cGclassService;
    @Autowired
    private CGoodsService cGoodsService;
    @ResponseBody
    @RequestMapping(value = {"list"})
    public Map<String,Object> list() {
        Map<String,Object> map =new HashMap<String,Object>();
        CGclass cGclass=new CGclass();
        List<CGclass> cGclassList=cGclassService.findList(cGclass);
        CGoods cGoods=new CGoods();
        List<CGoods> cGoodsList=cGoodsService.findList(cGoods);
        map.put("li",cGclassList);
        map.put("list",cGoodsList);

        return map;
    }

    @ResponseBody
    @RequestMapping(value = {"goodsList"})
    public List<CGoods> goodsList(String goodsId) {
        CGoods cGoods=new CGoods(goodsId);
        List<CGoods> cGoodsList=cGoodsService.GoodsFind(cGoods);
        return cGoodsList;
    }
}
