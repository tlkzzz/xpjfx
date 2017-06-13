/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.cw.web;

import com.tlkzzz.jeesite.common.utils.FreeMarkers;
import com.tlkzzz.jeesite.common.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "${adminPath}/cw/cAiwu")
public class CAiwuController extends BaseController {

	@RequestMapping(value = "list")
	public String list(){
		return "modules/ck/cwgl";
	}
}