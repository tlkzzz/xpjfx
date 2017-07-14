package com.tlkzzz.jeesite.modules.m.web;

    import com.tlkzzz.jeesite.common.web.BaseController;
    import com.tlkzzz.jeesite.modules.ck.entity.CJdlddk;
    import com.tlkzzz.jeesite.modules.ck.entity.CStore;
    import com.tlkzzz.jeesite.modules.ck.service.CJdlddkService;
    import com.tlkzzz.jeesite.modules.ck.service.CStoreService;
    import com.tlkzzz.jeesite.modules.sys.utils.UserUtils;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.ResponseBody;

    import java.text.SimpleDateFormat;
    import java.util.Date;
    import java.util.List;

/**
 * Created by Administrator on 2017/6/20 0020.
 */
@Controller
@RequestMapping(value = "${adminPath}/m/mJdlddk")
public class mJdlddkController extends BaseController{
    @Autowired
    private CJdlddkService CJdlddkService;
    @Autowired
    private CStoreService cStoreService;
    @ResponseBody
    @RequestMapping(value = {"list"})
    public List<CJdlddk> list() {
        String userId=UserUtils.getUser().getId();
        CJdlddk cJdlddk=new CJdlddk();
        cJdlddk.setUserid(userId);
        List<CJdlddk> cJdlddkList=CJdlddkService.dkfindList(cJdlddk);
        return cJdlddkList;
    }

    @ResponseBody
    @RequestMapping(value = {"dkjlList"})
    public List<CJdlddk> dkjlList(int fybs) {
        String userId=UserUtils.getUser().getId();
        CJdlddk cJdlddk=new CJdlddk();
        cJdlddk.setFybs(fybs);
        if(userId.equals("1")){
            List<CJdlddk> cJdlddkList=CJdlddkService.fyfindList(cJdlddk);
            return cJdlddkList;
        }else{
            cJdlddk.setUserid(userId);
            List<CJdlddk> cJdlddkList=CJdlddkService.fyfindList(cJdlddk);
            return cJdlddkList;
        }

    }

    @ResponseBody
    @RequestMapping(value = {"jddk"})
    public String jddk(String khxz,String currentLon,String currentLat) {
        List<CStore> cStoreList=cStoreService.findList(new CStore(khxz));
        String jds=cStoreList.get(0).getJd();
        Double jd=Double.parseDouble(cStoreList.get(0).getJd());
        Double djd=Double.parseDouble(currentLon);
        if(djd>jd+0.01||djd<jd-0.01){
            return "false";
        }
        Double wd=Double.parseDouble(cStoreList.get(0).getWd());
        Double dwd=Double.parseDouble(currentLat);
        if(dwd>wd+0.01||dwd<wd-0.01){
            return "false";
        }
        CJdlddk cJdlddk=new CJdlddk();
//        Date d = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String str= sdf.format(d);
//        try {
//            Date toDay = sdf.parse(str);
//            cJdlddk.setDkdate(toDay);
//        }catch (Exception e ){
//
//        }

        cJdlddk.setUserid(UserUtils.getUser().getId());
        cJdlddk.setDkdate(new Date());
        cJdlddk.setStatus("1");//1 进店打卡
        cJdlddk.setJdjd(currentLon);
        cJdlddk.setJdwd(currentLat);
        cJdlddk.setStoreId(khxz);
        CJdlddkService.save(cJdlddk);
        return "ture";
    }
    @ResponseBody
    @RequestMapping(value = {"lddk"})
    public String lddk(String khxz,String currentLon,String currentLat) {
        CJdlddk cJdlddk=new CJdlddk();
        cJdlddk.setUserid(UserUtils.getUser().getId());
        cJdlddk.setLdjd(currentLon);
        cJdlddk.setLdwd(currentLat);
        cJdlddk.setLdDate(new Date());
        cJdlddk.setStatus("2");//2 离店打卡
        cJdlddk.setStoreId(khxz);
        CJdlddkService.ldupdate(cJdlddk);
        return "ture";
    }
}