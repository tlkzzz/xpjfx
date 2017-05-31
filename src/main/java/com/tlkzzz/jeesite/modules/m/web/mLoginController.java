package com.tlkzzz.jeesite.modules.m.web;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.utils.StringUtils;
import com.tlkzzz.jeesite.common.web.BaseController;
import com.tlkzzz.jeesite.modules.ck.entity.CHouse;
import com.tlkzzz.jeesite.modules.sys.security.FormAuthenticationFilter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.tlkzzz.jeesite.common.utils.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017-05-08.
 */
@Controller
@RequestMapping(value = "${adminPath}/m/mLogin")
public class mLoginController extends BaseController {



    @ResponseBody
    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public String login(String  name,String pass) {
        System.out.println("---------------手机访问拦截器");
//        if("11".equals(user)&&"22".equals(pass)){
//                return "true";
//        }else{
//            return "false";
//        }
//
//        boolean rememberMe = isRememberMe(request);
//        String host = StringUtils.getRemoteAddr((HttpServletRequest)request);
//        String captcha = getCaptcha(request);
//        boolean mobile = isMobileLogin(request);
//

        UsernamePasswordToken token = new UsernamePasswordToken(name, pass);
        token.setRememberMe(true);
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.login(token);
//           String message = (String)request.getAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM);


        return "true";
    }

}
