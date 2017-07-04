package com.tlkzzz.jeesite.modules.m.web;

import com.adobe.xmp.impl.Base64;
import com.tlkzzz.jeesite.common.config.Global;
import com.tlkzzz.jeesite.common.utils.IdGen;
import com.tlkzzz.jeesite.common.web.BaseController;
import com.tlkzzz.jeesite.modules.ck.entity.CSclass;
import com.tlkzzz.jeesite.modules.ck.entity.CStore;
import com.tlkzzz.jeesite.modules.ck.service.CSclassService;
import com.tlkzzz.jeesite.modules.ck.service.CStoreService;
import com.tlkzzz.jeesite.modules.sys.entity.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by Administrator on 2017/5/31 0031.
 */
@Controller
@RequestMapping(value = "${adminPath}/m/mStore")
public class mStorecontroller extends BaseController {
    @Autowired
    private CStoreService cStoreService;
    @Autowired
    private CSclassService cSclassService;

    @ResponseBody
    @RequestMapping(value = {"list"})
    public List<CSclass> list() {
        List<CSclass> cSclassList = cSclassService.findList(new CSclass());
        return cSclassList;
    }


    @ResponseBody
    @RequestMapping(value = {"tpAdd"})
    public String tpAdd(HttpServletRequest request, HttpServletResponse response) {
        String imgStr = request.getParameter("imgData");
        if (imgStr == null) {// 图像数据为空
            return "false";
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            // 生成jpeg图
            String path=Global.getUserfilesBaseDir();
            String fileName = IdGen.uuid()+".jpg";
            path=path+"/static/images/"+fileName;
            String paths="http://192.168.0.150:8080/static/images/"+fileName;
            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();
            return paths;
        } catch (Exception e) {
            return "false";
        }
    }



    @ResponseBody
    @RequestMapping(value = {"storelist"})
    public List<CStore> storelist(int fybs) {
        CStore cStore=new CStore();
        cStore.setFybs(fybs);
        List<CStore> cStoreList=cStoreService.fyfindList(cStore);
        return cStoreList;
    }

    @ResponseBody
    @RequestMapping(value = {"save"})
    public String save(String mdtp,String khfl,String qy,String xxdz,
                             String cgy,String dpmc,String khmc,String sjhm,
                             String cgylxfs,String yb,String wx,String qq,
                             String hzzq,String ed,String xsqd,String jhqd,
                             String dh,String dzyj,String jfxs,String khhmc,
                             String khhzh,String khr,String jd,String wd) {
        CStore cStore=new CStore();
        cStore.setMdtp(mdtp);
        cStore.setSclass(new CSclass(khfl));
//        cStore.setArea(new Area(qy));
        cStore.setDz(xxdz);
        cStore.setCgy(cgy);
        cStore.setDpmc(dpmc);
        cStore.setName(khmc);
        cStore.setPhone(sjhm);
        cStore.setCgydh(cgylxfs);
        cStore.setYb(yb);
        cStore.setWeixin(wx);
        cStore.setQq(qq);
        cStore.setHzzq(hzzq);
        cStore.setEdu(ed);
        cStore.setXsqd(xsqd);
        cStore.setJhqd(jhqd);
        cStore.setDh(dh);
        cStore.setEmail(dzyj);
        cStore.setJfxs(jfxs);
        cStore.setKhh(khhmc);
        cStore.setKhhzh(khhzh);
        cStore.setKhr(khr);
        cStore.setJd(jd);
        cStore.setWd(wd);
        cStore.setState("1");
        cStoreService.save(cStore);
        return "true";
    }
}
