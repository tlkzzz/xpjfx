/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.test.web;

import com.tlkzzz.jeesite.common.config.Global;
import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.utils.StringUtils;
import com.tlkzzz.jeesite.common.web.BaseController;
import com.tlkzzz.jeesite.test.entity.TestData;
import com.tlkzzz.jeesite.test.service.TestDataService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 单表生成Controller
 * @author tlkzzz
 * @version 2015-04-06
 */
@Controller
@RequestMapping(value = "${adminPath}/test/shenhe")
public class shenheController extends BaseController {
	@RequiresPermissions("test:shenhe:view")
	@RequestMapping(value = "list")
	public String list( HttpServletRequest request, HttpServletResponse response, Model model) {
		return "jeesite/test/shenheList";
	}
}