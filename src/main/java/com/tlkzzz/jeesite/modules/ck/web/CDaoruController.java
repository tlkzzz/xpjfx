package com.tlkzzz.jeesite.modules.ck.web;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.web.BaseController;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;

/**
 * Created by Administrator on 2017/6/7 0007.
 */
@Controller
@RequestMapping(value = "${adminPath}/ck/CDaoru")
public class CDaoruController extends BaseController {

    @RequestMapping(value = {"list", ""})
    public String list() {
        return "modules/ck/cDaoru";
    }

    @RequestMapping(value = "guigeDaoru")
    public String guigeDaoru(HttpServletRequest request, HttpServletResponse response) throws IOException{
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)(request);
        Iterator<String> iter = multiRequest.getFileNames();
        MultipartFile file = null;
        while(iter.hasNext()){  //实际只针对单个循环一次 不知道什么原因
            file = multiRequest.getFile(iter.next());
        }
        try {
            InputStream inputStream = file.getInputStream();
            HSSFWorkbook workbook =new HSSFWorkbook(inputStream);



            System.out.println("****************************");
/*            byte[] buffer = new byte[1024];
            int len = 0;

            String fileName = "e:\\aaa.xls";//文件最终上传的位置
            System.out.println(fileName);
            OutputStream out = new FileOutputStream(fileName);

            while ((len = inputStream.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }

            out.close();
            inputStream.close();*/
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "modules/ck/cDaoru";
    }
}
