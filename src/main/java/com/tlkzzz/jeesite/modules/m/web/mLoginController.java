package com.tlkzzz.jeesite.modules.m.web;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.utils.StringUtils;
import com.tlkzzz.jeesite.common.web.BaseController;
import com.tlkzzz.jeesite.modules.ck.entity.CHouse;
import com.tlkzzz.jeesite.modules.sys.entity.User;
import com.tlkzzz.jeesite.modules.sys.security.FormAuthenticationFilter;
import com.tlkzzz.jeesite.modules.sys.utils.UserUtils;
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
    public static final String DEFAULT_CAPTCHA_PARAM = "validateCode";
    public static final String DEFAULT_MOBILE_PARAM = "mobileLogin";
    public static final String DEFAULT_REMEMBER_ME_PARAM = "rememberMe";
    private String captchaParam = DEFAULT_CAPTCHA_PARAM;
    private String mobileLoginParam = DEFAULT_MOBILE_PARAM;
    private String rememberMeParam = DEFAULT_REMEMBER_ME_PARAM;

    @ResponseBody
    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("---------------手机访问拦截器");
          String username=  request.getParameter("username");
          String password = request.getParameter("password");
        String host = StringUtils.getRemoteAddr((HttpServletRequest)request);
        if (password==null){
            password = "";
        }
        boolean rememberMe = isRememberMe(request);

        String captcha = getCaptcha(request);
        boolean mobile = isMobileLogin(request);
        //得到当前执行数据库的用户
        Subject subject = SecurityUtils.getSubject();
        //创建token令牌,前台传过来的 用户和密码
        UsernamePasswordToken token = new com.tlkzzz.jeesite.modules.sys.security.UsernamePasswordToken(username, password.toCharArray(), rememberMe, host, captcha, mobile);
        try{
            //登录,身份验证
            subject.login(token);
//            User uers=   UserUtils.getUser();
            System.out.println("登录成功");
            return "true";
        }catch(AuthenticationException e){
            e.printStackTrace();
            System.out.println("登录失败");
            return "false";
        }
    }

    @ResponseBody
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public Map<String,Object> list(HttpServletRequest request, HttpServletResponse response) {
        User uer=   UserUtils.getUser();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username",uer);

      return map;
    }
    @ResponseBody
    @RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        //退出登录
        UserUtils.getSubject().logout();
        return "true";

    }
    protected boolean isRememberMe(ServletRequest request) {
        return WebUtils.isTrue(request, getRememberMeParam());
    }

    public String getRememberMeParam() {
        return rememberMeParam;
    }

    protected String getCaptcha(ServletRequest request) {
        return WebUtils.getCleanParam(request, getCaptchaParam());
    }
    public String getCaptchaParam() {
        return captchaParam;
    }
    protected boolean isMobileLogin(ServletRequest request) {
        return WebUtils.isTrue(request, getMobileLoginParam());
    }
    public String getMobileLoginParam() {
        return mobileLoginParam;
    }
}
