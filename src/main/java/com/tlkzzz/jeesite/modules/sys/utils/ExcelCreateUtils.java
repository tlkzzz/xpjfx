package com.tlkzzz.jeesite.modules.sys.utils;

import com.tlkzzz.jeesite.common.utils.StringUtils;
import com.tlkzzz.jeesite.modules.sys.entity.User;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 16-7-25.
 */
public class ExcelCreateUtils {

    /**
     * 处理原始数据后导出信息为Excel
     * @param response 返回信息
     * @param list 原始数据列表
     * @param status 导出表格类型
     */
    public static void exportNonRegExcel(HttpServletResponse response,List<Object> list,String status){
        String[] headArr = {};
        List<String[]> valueList = new ArrayList<String[]>();
        if(StringUtils.isNotBlank(status)&&status.equals("3")){
            String head[] = {"","","","","",""};
            headArr = head;
            for(Object o : list){
                String[] value = {"","","","","",""};
                valueList.add(value);
            }
        }else {
            String head[] = {"","","","","",""};
            headArr = head;
            for(Object o : list){
                String[] value = {"","","","","",""};
                valueList.add(value);
            }
        }
        WordCreateUtils.exportExcel(response,"文件名称","表格名称",headArr,valueList);
    }

    /**
     * 获取字符串数组元素（防止越界）
     * @param arr
     * @param index
     * @return
     */
    public static String getStrArray(String[] arr,int index){
        String retStr = "";
        if(arr.length>index)retStr = arr[index];
        return getStr(retStr);
    }

    /**
     * 处理字符串为NULL的返回""
     * @param str
     * @return
     */
    public static String getStr(String str){
        return (str==null||"".equals(str))?"":str;
    }

}
