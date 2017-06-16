package com.tlkzzz.jeesite.modules.m.web;

import com.tlkzzz.jeesite.common.web.BaseController;
import com.tlkzzz.jeesite.modules.ck.entity.CRkckddinfo;
import com.tlkzzz.jeesite.modules.ck.service.CRkckddinfoService;
import com.tlkzzz.jeesite.modules.sys.entity.User;
import com.tlkzzz.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2017/6/1 0001.
 */
@Controller
@RequestMapping(value = "${adminPath}/m/mMyDd")
public class mMyDdController extends BaseController {
    @Autowired
    private CRkckddinfoService cRkckddinfoService;
    @ResponseBody
    @RequestMapping(value = {"list"})
    public List<CRkckddinfo> list(int fybs, String ywy){
        CRkckddinfo cRkckddinfo=new CRkckddinfo();
        cRkckddinfo.setFybs(fybs);
        cRkckddinfo.setCreateBy(new User(UserUtils.getUser().getId()));
        List<CRkckddinfo> cRkckddinfoList=cRkckddinfoService.ywyfindList(cRkckddinfo);
            return cRkckddinfoList;
    }
}
