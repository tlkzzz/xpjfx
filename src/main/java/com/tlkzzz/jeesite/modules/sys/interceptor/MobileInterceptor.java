/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.sys.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tlkzzz.jeesite.common.service.BaseService;
import com.tlkzzz.jeesite.common.utils.ObjectUtils;
import com.tlkzzz.jeesite.common.utils.StringUtils;
import com.tlkzzz.jeesite.common.utils.UserAgentUtils;
import com.tlkzzz.jeesite.modules.sys.entity.User;
import com.tlkzzz.jeesite.modules.sys.utils.UserUtils;
import net.sf.json.JSONObject;
import org.apache.shiro.session.Session;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 手机端视图拦截器
 * @author tlkzzz
 * @version 2014-9-1
 */
public class MobileInterceptor extends BaseService implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler) throws Exception {
		System.out.print("******************************************之前访问手机访问拦截器");
		String url=	request.getServletPath();
		if(url.equals("/a/m/mLogin/login") || url.equals("/a/m/mLogin/logout")){
			return  true;
		}else{
			//判断是否登录 如果没登录返回
			Map<String, Object> map = new HashMap<String, Object>();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8");
			PrintWriter writer = null;
			User user=   UserUtils.getUser();
			if(user==null||StringUtils.isBlank(user.getId())){//未登录
				try {
					map.put("islogin", false);
					JSONObject jsonObject = JSONObject.fromObject(map);
					writer = response.getWriter();
					writer.print(jsonObject.toString());
					writer.flush();
					writer.close();
					logger.debug(jsonObject.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
				return false;
			}else {//已经登录
//				try {
//					map.put("islogin", true);
//					JSONObject jsonObject = JSONObject.fromObject(map);
//					writer = response.getWriter();
//					writer.print(jsonObject.toString());
//					writer.flush();
//					writer.close();
//					logger.debug(jsonObject.toString());
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
				return true;
			}
		}

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, 
			ModelAndView modelAndView) throws Exception {
		System.out.print("******************************************之后手机访问拦截器"+modelAndView);
		if (modelAndView != null){
			// 如果是手机或平板访问的话，则跳转到手机视图页面。
			if(UserAgentUtils.isMobileOrTablet(request) && !StringUtils.startsWithIgnoreCase(modelAndView.getViewName(), "redirect:")){
				modelAndView.setViewName("mobile/" + modelAndView.getViewName());
			}
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
			Object handler, Exception ex) throws Exception {
		System.out.print("*****************");
	}

}
