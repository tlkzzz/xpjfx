package com.tlkzzz.jeesite.modules.sys.utils;

import com.tlkzzz.jeesite.common.utils.StringUtils;
import com.tlkzzz.jeesite.modules.ck.entity.*;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by Administrator on 16-7-25.
 */
public class ExcelCreateUtils {

    /**
     * 入库单导出
     * 处理原始数据后导出信息为Excel
     * @param response 返回信息
     * @param list 原始数据列表 CRkinfo
     * @param status 导出表格类型
     */
    public static void exportNonRegExcel(HttpServletResponse response, List<CRkinfo> list, String status){
        String[] headArr = {};
        List<String[]> valueList = new ArrayList<String[]>();
        if(StringUtils.isNotBlank(status)&&status.equals("1,2,3,4,5")) {
            String head[] = {"商品", "仓库", "入库数量", "入库后的总数量", "入库前成本价", "入库成本价", "入库类型", "入库时间", "备注"};
            headArr = head;
            for (CRkinfo o : list) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String str = sdf.format(o.getCreateDate());
                String[] value = {o.getGoods().getName(), o.getHouse().getName(), o.getRknub(), o.getRkhnub(), o.getRkqcbj(), o.getRkhcbj(),
                        DictUtils.getDictLabel(o.getState(),"rkState",""),str, o.getRemarks()};
                valueList.add(value);
            }
        }
        WordCreateUtils.exportExcel(response,"入库单表格","入库单表格",headArr,valueList);
    }

    /**
     * 销售单导出
     * 处理原始数据后导出信息为Excel
     * @param response 返回信息
     * @param list 原始数据列表 CRkinfo
     * @param status 导出表格类型
     */
    public static void xsexport(HttpServletResponse response, List<CCkinfo> list, String status){
        String[] headArr = {};
        List<String[]> valueList = new ArrayList<String[]>();
        if(StringUtils.isNotBlank(status)&&status.equals("1")) {
            String head[] = {"仓库", "商品", "金额", "出库前成本价", "数量", "供应商","客户","出库时间","出库方式","是否审批","经手人","备注信息"};
            headArr = head;
            for (CCkinfo o : list) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String str = sdf.format(o.getCkdate());
                String[] value = {o.getHouse().getName(),o.getGoods().getName(),o.getJe(),o.getCkqcbj(),
                        o.getNub(),o.getSupplier().getName(),o.getStore().getName(),str,
                        DictUtils.getDictLabel(o.getState(),"ckState",""), DictUtils.getDictLabel(o.getIssp(),"storeState",""),o.getJsr().getName(),o.getRemarks()};
                valueList.add(value);
            }
        }
        WordCreateUtils.exportExcel(response,"销售单表格","销售单表格",headArr,valueList);
    }

    /**
     * 移库单导出
     * 处理原始数据后导出信息为Excel
     * @param response 返回信息
     * @param list 原始数据列表 CYkinfo
     * @param status 导出表格类型
     */
    public static void ykexport(HttpServletResponse response, List<CYkinfo> list, String status){
        String[] headArr = {};
        List<String[]> valueList = new ArrayList<String[]>();
        if(StringUtils.isNotBlank(status)&&status.equals("1")) {
            String head[] = {"起始仓库", "结束仓库", "商品", "数量", "移库时间", "备注"};
            headArr = head;
            for (CYkinfo o : list) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String str = sdf.format(o.getCreateDate());
                String[] value = {o.getStartHouse().getName(),o.getEndHouse().getName(),o.getGoods().getName(),
                                  o.getNub(),str,o.getRemarks()};
                valueList.add(value);
            }
        }
        WordCreateUtils.exportExcel(response,"移库单表格","移库单表格",headArr,valueList);
    }

    /**
     * 退货单导出
     * 处理原始数据后导出信息为Excel
     * @param response 返回信息
     * @param list 原始数据列表 CYkinfo
     * @param status 导出表格类型
     */
    public static void thexport(HttpServletResponse response, List<CCkinfo> list, String status){
        String[] headArr = {};
        List<String[]> valueList = new ArrayList<String[]>();
        if(StringUtils.isNotBlank(status)&&status.equals("1")) {
            String head[] = {"仓库", "商品", "金额", "出库前成本价", "数量", "供应商","客户","出库时间","出库方式","是否审批","经手人","备注信息"};
            headArr = head;
            for (CCkinfo o : list) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String str = sdf.format(o.getCkdate());
                String[] value = {o.getHouse().getName(),o.getGoods().getName(),o.getJe(),o.getCkqcbj(),
                        o.getNub(),o.getSupplier().getName(),o.getStore().getName(),str,
                        DictUtils.getDictLabel(o.getState(),"ckState",""), DictUtils.getDictLabel(o.getIssp(),"storeState",""),o.getJsr().getName(),o.getRemarks()};
                valueList.add(value);
            }
        }
        WordCreateUtils.exportExcel(response,"退货单表格","退货单表格",headArr,valueList);
    }
    /**
     * 报废单导出
     * 处理原始数据后导出信息为Excel
     * @param response 返回信息
     * @param list 原始数据列表 CYkinfo
     * @param status 导出表格类型
     */
    public static void bfexport(HttpServletResponse response, List<CCkinfo> list, String status){
        String[] headArr = {};
        List<String[]> valueList = new ArrayList<String[]>();
        if(StringUtils.isNotBlank(status)&&status.equals("1")) {
            String head[] = {"仓库", "商品", "金额", "出库前成本价", "数量", "供应商","客户","出库时间","出库方式","是否审批","经手人","备注信息"};
            headArr = head;
            for (CCkinfo o : list) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String str = sdf.format(o.getCkdate());
                String[] value = {o.getHouse().getName(),o.getGoods().getName(),o.getJe(),o.getCkqcbj(),
                        o.getNub(),o.getSupplier().getName(),o.getStore().getName(),str,
                        DictUtils.getDictLabel(o.getState(),"ckState",""), DictUtils.getDictLabel(o.getIssp(),"storeState",""),o.getJsr().getName(),o.getRemarks()};
                valueList.add(value);
            }
        }
        WordCreateUtils.exportExcel(response,"报废单表格","报废单表格",headArr,valueList);
    }
    /**
     * 采购单导出
     * 处理原始数据后导出信息为Excel
     * @param response 返回信息
     * @param list 原始数据列表 CCgzbinfoc
     * @param status 导出表格类型
     */
    public static void cgexport(HttpServletResponse response, List<CCgzbinfo> list, String status){
        String[] headArr = {};
        List<String[]> valueList = new ArrayList<String[]>();
        if(StringUtils.isNotBlank(status)&&status.equals("1")) {
            String head[] = {"商品", "采购数量", "供应商", "价格", "实际入库量","入库时间","备注"};
            headArr = head;
            for (CCgzbinfo o : list) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String str = sdf.format(o.getRkDate());
                String[] value = {o.getGoods().getName(),o.getNub(),o.getRkinfo().getSupplier().getName(),
                                  o.getJg(),o.getRknub(),str,o.getRemarks()};
                valueList.add(value);
            }
        }
        WordCreateUtils.exportExcel(response,"采购单表格","采购单表格",headArr,valueList);
    }
    /**
     * 客户订单导出
     * 处理原始数据后导出信息为Excel
     * @param response 返回信息
     * @param list 原始数据列表 CCgzbinfoc
     * @param status 导出表格类型
     */
    public static void khexport(HttpServletResponse response, List<CCkinfo> list, String status){
        String[] headArr = {};
        List<String[]> valueList = new ArrayList<String[]>();
        if(StringUtils.isNotBlank(status)&&status.equals("1")) {
            String head[] = {"仓库", "商品", "金额", "出库前成本价", "数量", "供应商","客户","出库时间","出库方式","是否审批","经手人","备注信息"};
            headArr = head;
            for (CCkinfo o : list) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String str = sdf.format(o.getCkdate());
                String[] value = {o.getHouse().getName(),o.getGoods().getName(),o.getJe(),o.getCkqcbj(),
                        o.getNub(),o.getSupplier().getName(),o.getStore().getName(),str,
                        DictUtils.getDictLabel(o.getState(),"ckState",""), DictUtils.getDictLabel(o.getIssp(),"storeState",""),o.getJsr().getName(),o.getRemarks()};
                valueList.add(value);
            }
        }
        WordCreateUtils.exportExcel(response,"客户订单表格","客户订单表格",headArr,valueList);
    }
    /**
     * 业务员订单导出
     * 处理原始数据后导出信息为Excel
     * @param response 返回信息
     * @param list 原始数据列表 CCgzbinfoc
     * @param status 导出表格类型
     */
    public static void ywyexport(HttpServletResponse response, List<CDdinfo> list,String status){
        String[] headArr = {};
        List<String[]> valueList = new ArrayList<String[]>();
        if(StringUtils.isNotBlank(status)&&status.equals("1")) {
            String head[] = {"商品名称", "所在仓库", "供应商", "规格", "单位", "合计金额","业务员"};
            headArr = head;
            for (CDdinfo o : list) {
                String[] value = {o.getGoods().getName(),o.getHouse().getName(),o.getSupplier().getName(),
                                   o.getGoods().getSpec().getName(),o.getGoods().getBig().getName(),o.getJe(),
                                   o.getCreateBy().getName()
                                  };
                valueList.add(value);
            }
        }
        WordCreateUtils.exportExcel(response,"业务员订单表格","业务员订单表格",headArr,valueList);
    }
    /**
     * 入库单导出
     * 处理原始数据后导出信息为Excel
     * @param response 返回信息
     * @param list 原始数据列表 CDdinfo
     * @param type 导出表格类型
     */
    public static void rkexport(HttpServletResponse response, List<CRkinfo> list,String type){
        String[] headArr = {};
        List<String[]> valueList = new ArrayList<String[]>();
        if(StringUtils.isNotBlank(type)&&type.equals("2")) {
            String head[] = {"商品大类", "商品小类", "商品名称", "规格型号", "入库数量", "金额","成本金额"};
            headArr = head;
            for (CRkinfo o : list) {
                String[] value = {o.getGoods().getGclass().getParent().getName(),o.getGoods().getGclass().getName(),o.getGoods().getName(),
                                  o.getGoods().getSpec().getName(),o.getRknub(),o.getGoods().getSj(),o.getTotal()
                };
                valueList.add(value);
            }
            WordCreateUtils.exportExcel(response,"商品汇总表格","商品汇总表格",headArr,valueList);
        } if(StringUtils.isNotBlank(type)&&type.equals("3")) {
            String head[] = {"仓库名称", "入库类型", "供应商", "入库时间", "金额", "成本金额","实付款","备注","操作人"};
            headArr = head;
            for (CRkinfo o : list) {
               double je=Integer.parseInt(o.getRknub())*Integer.parseInt(o.getGoods().getSj());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String str = sdf.format(o.getCreateDate());
                String[] value = {o.getHouse().getName(),DictUtils.getDictLabel(o.getState(),"rkState",""),
                                  o.getSupplier().getName(),str,
                };
                valueList.add(value);
            }
            WordCreateUtils.exportExcel(response,"单据表格","单据表格",headArr,valueList);
        }

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
