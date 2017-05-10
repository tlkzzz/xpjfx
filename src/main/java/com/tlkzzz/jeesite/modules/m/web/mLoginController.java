package com.tlkzzz.jeesite.modules.m.web;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.web.BaseController;
import com.tlkzzz.jeesite.modules.ck.entity.CHouse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017-05-08.
 */
@Controller
@RequestMapping(value = "${adminPath}/m/mLogin")
public class mLoginController extends BaseController {
    @ResponseBody
    @RequestMapping(value = {"list"})
    public String list(String user,String pass) {
//        response.setHeader("Access-Control-Allow-Origin","*");
//// 响应类型
//        response.setHeader("Access-Control-Allow-Methods","POST");
//// 响应头设置
//        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type");
        if("11".equals(user)&&"22".equals(pass)){
           return "true";
        }else{
            return "false";
        }
    }
}
