package com.tlkzzz.jeesite.modules.p.web;

import com.tlkzzz.jeesite.common.config.Global;
import com.tlkzzz.jeesite.common.utils.Encodes;
import com.tlkzzz.jeesite.common.utils.FreeMarkers;
import com.tlkzzz.jeesite.common.utils.StringUtils;
import com.tlkzzz.jeesite.common.web.BaseController;
import com.tlkzzz.jeesite.modules.ck.service.CGoodsService;
import com.tlkzzz.jeesite.modules.ck.service.CHgoodsService;
import com.tlkzzz.jeesite.modules.p.entity.ShopAdv;
import com.tlkzzz.jeesite.modules.p.entity.ShopAdvPosition;
import com.tlkzzz.jeesite.modules.p.entity.ShopGoods;
import com.tlkzzz.jeesite.modules.p.entity.ShopMember;
import com.tlkzzz.jeesite.modules.p.service.ShopAdvPositionService;
import com.tlkzzz.jeesite.modules.p.service.ShopAdvService;
import com.tlkzzz.jeesite.modules.p.service.ShopGoodsService;
import com.tlkzzz.jeesite.modules.p.service.ShopRecommendClassService;
import com.tlkzzz.jeesite.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WT on 2017-05-23.
 */
@Controller
@RequestMapping(value = "${adminPath}/p")
public class ShopController extends BaseController {
    @Autowired
    private ShopAdvPositionService shopAdvPositionService;
    @Autowired
    private ShopRecommendClassService shopRecommendClassService;
    @Autowired
    private ShopAdvService shopAdvService;
    @Autowired
    private ShopGoodsService shopGoodsService;
    @Autowired
    private CGoodsService cGoodsService;
    @Autowired
    private CHgoodsService cHgoodsService;

//    @RequiresPermissions("p:shop:view")
    @RequestMapping(value = {"list", ""})
    public void shop(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> model = new HashMap();
        model.put("headClassList", shopRecommendClassService.findRecommendList("0"));
        List<ShopAdvPosition> list = shopAdvPositionService.findList(new ShopAdvPosition());
        for (ShopAdvPosition sa: list){
            sa.setAdvList(shopAdvService.findListByApKey(sa.getApKey()));
            model.put(sa.getApKey(),sa);
        }
        renderString(response,FreeMarkers.renderFile("/WEB-INF/views/static/one","home1.html", request, model),"text/html;charset=UTF-8");
    }

//    @RequiresPermissions("p:shop:view")
    @RequestMapping(value = "goods")
    public void goods(String id,HttpServletRequest request, HttpServletResponse response){
        String content = "";
        if(StringUtils.isNotBlank(id)) {
            ShopGoods shopGoods = shopGoodsService.get(id);
            if(shopGoods!=null) {
                shopGoods.setGoodsBody(Encodes.unescapeHtml(shopGoods.getGoodsBody()));
                ShopMember member = UserUtils.getMember();//当前登录会员
                member.setId("3");
                shopGoodsService.saveMemberGoodsHistory(shopGoods.getId(),member);
                Map<String, Object> model = new HashMap();
                model.put("goods", shopGoods);//商品信息
                model.put("member", member);//会员登录信息
                model.put("houseNum", cHgoodsService.findStockSumNum(shopGoods.getId()));//所有仓库商品总库存
                model.put("gradePrice", shopGoodsService.getByIdCode(shopGoods.getId(), member.getMemberGradeid()));//会员等级商品价格
                model.put("browseList", shopGoodsService.findHistoryList(shopGoods.getId(), member.getId()));//会员浏览记录列表
                content = FreeMarkers.renderFile("/WEB-INF/views/static/one", "introduction.html", request, model);
            }else {
                content = "购买商品不存在！";
            }
        }else {
            content = "请选择商品！";
        }
        renderString(response, content,"text/html;charset=UTF-8");
    }
}
