/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.common.utils;

import java.io.*;
import java.util.Map;

import com.tlkzzz.jeesite.common.config.Global;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import freemarker.template.Configuration;
import freemarker.template.Template;

import javax.servlet.http.HttpServletRequest;

/**
 * FreeMarkers工具类
 * @author tlkzzz
 * @version 2013-01-15
 */
public class FreeMarkers {

	public static String renderString(String templateString, Map<String, ?> model) {
		try {
			StringWriter result = new StringWriter();
			Template t = new Template("name", new StringReader(templateString), new Configuration());
			t.process(model, result);
			return result.toString();
		} catch (Exception e) {
			throw Exceptions.unchecked(e);
		}
	}

	public static String renderTemplate(Template template, Object model) {
		try {
			StringWriter result = new StringWriter();
			template.process(model, result);
			return result.toString();
		} catch (Exception e) {
			throw Exceptions.unchecked(e);
		}
	}

	public static Configuration buildConfiguration(String directory) throws IOException {
		Configuration cfg = new Configuration();
		Resource path = new DefaultResourceLoader().getResource(directory);
		cfg.setDirectoryForTemplateLoading(path.getFile());
		return cfg;
	}
	
	public static void main(String[] args) throws IOException {
//		// renderString
//		Map<String, String> model = com.google.common.collect.Maps.newHashMap();
//		model.put("userName", "calvin");
//		String result = FreeMarkers.renderString("hello ${userName}", model);
//		System.out.println(result);
//		// renderTemplate
//		Configuration cfg = FreeMarkers.buildConfiguration("classpath:/");
//		Template template = cfg.getTemplate("testTemplate.ftl");
//		String result2 = FreeMarkers.renderTemplate(template, model);
//		System.out.println(result2);
		
//		Map<String, String> model = com.google.common.collect.Maps.newHashMap();
//		model.put("userName", "calvin");
//		String result = FreeMarkers.renderString("hello ${userName} ${r'${userName}'}", model);
//		System.out.println(result);
	}

	public static String renderFile(String filePath, String fileName, HttpServletRequest request, Map<String,Object> map){
		String content = "";
		map.put("ctx", request.getContextPath()+ Global.getAdminPath());
		map.put("ctxStatic", request.getContextPath()+"/static");
		filePath = request.getSession().getServletContext().getRealPath(filePath);
		if(StringUtils.isNotBlank(fileName)&&fileName.contains(".ftl")) {
			Configuration configuration = new Configuration();
			try {
				configuration.setDirectoryForTemplateLoading(new File(filePath));
				configuration.setDefaultEncoding("UTF-8");
				Template template = configuration.getTemplate(fileName);
				content = renderTemplate(template, map);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(StringUtils.isNotBlank(fileName)&&fileName.contains(".html")){
			InputStream input = null;
			try {
				input = new FileInputStream(filePath+"\\"+fileName);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			StringBuffer buffer = new StringBuffer();
			byte[] bytes = new byte[1024];
			try {
				for(int n ; (n = input.read(bytes))!=-1 ; ){
					buffer.append(new String(bytes,0,n,"UTF-8"));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			content = renderString(buffer.toString(), map);
		}
		return content;
	}
	
}
