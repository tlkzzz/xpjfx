package com.tlkzzz.jeesite.modules.sys.utils;

import com.tlkzzz.jeesite.common.utils.StringUtils;
import com.tlkzzz.jeesite.modules.ck.entity.*;
import com.tlkzzz.jeesite.modules.cw.entity.FDiscount;
import com.tlkzzz.jeesite.modules.cw.entity.FReceipt;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        } else  if(StringUtils.isNotBlank(type)&&type.equals("3")){
            String head[] = {"仓库名称", "入库类型", "供应商", "入库时间", "金额", "成本金额","实付款","备注","操作人"};
            headArr = head;
            for (CRkinfo o : list) {
                 double je=Double.parseDouble(o.getRknub())*Double.parseDouble(o.getGoods().getSj());
                double cbje=Double.parseDouble(o.getRknub())*Double.parseDouble(o.getRkhcbj());


                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String str = sdf.format(o.getCreateDate());
                String[] value = {o.getHouse().getName(),DictUtils.getDictLabel(o.getState(),"rkState",""),
                                  o.getSupplier().getName(),str,String.valueOf(je),String.valueOf(cbje),String.valueOf(cbje),
                                  o.getRemarks(),o.getCreateBy().getName()
                };
                valueList.add(value);
            }
            WordCreateUtils.exportExcel(response,"单据明细表格","单据明细表格",headArr,valueList);
        } else {
            String head[] = {"仓库名称", "商品名称", "规格型号", "入库数量", "单价", "金额","入库类型","入库时间","备注","操作人"};
            headArr = head;
            for (CRkinfo o : list) {
                 double cbje=Double.parseDouble(o.getRknub())*Double.parseDouble(o.getRkhcbj());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String str = sdf.format(o.getCreateDate());
                String[] value = {o.getHouse().getName(),o.getGoods().getName(),o.getGoods().getSpec().getName(),o.getRknub(),
                                  o.getRkhcbj(),String.valueOf(cbje),DictUtils.getDictLabel(o.getState(),"rkState",""),
                                  str,o.getRemarks(),o.getCreateBy().getName()
                };
                valueList.add(value);
            }
            WordCreateUtils.exportExcel(response,"入库表格","入库表格",headArr,valueList);
        }

    }
    /**
     * 报废
     * 处理原始数据后导出信息为Excel
     * @param response 返回信息
     * @param list 原始数据列表 CCgzbinfoc
     * @param type 导出表格类型
     */
    public static void bfexportlist(HttpServletResponse response, List<CDdinfo> list,String type) {
        String[] headArr = {};
        List<String[]> valueList = new ArrayList<String[]>();
        if (StringUtils.isNotBlank(type) && type.equals("2")) {
            String head[] = {"商品名称", "规格型号", "数量", "最小单位数量", "小单位", "金额"};
            headArr = head;
            for (CDdinfo o : list) {
                double je = Double.parseDouble(o.getGoods().getSj()) * Double.parseDouble(o.getNub());
                String[] value = {o.getGoods().getName(),o.getGoods().getSpec().getName(),
                        o.getType(),o.getNub(),o.getGoods().getSmall().getName(),
                        String.valueOf(je)
                };
                valueList.add(value);

            }
            WordCreateUtils.exportExcel(response, "报废商品汇总表格", "报废商品汇总表格", headArr, valueList);
        }else {
            String head[] = {"报废单号", "仓库", "商品名称", "规格型号", "报废时间", "数量", "单位", "金额", "操作人"};
            headArr = head;
            for (CDdinfo o : list) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String str = sdf.format(o.getCreateDate());
                double je = Double.parseDouble(o.getNub()) * Double.parseDouble(o.getJe());
                String[] value = {o.getDdbh(), o.getHouse().getName(), o.getGoods().getName(), o.getGoods().getSpec().getName(),
                        str, o.getType(), o.getJe(), String.valueOf(je), o.getCreateBy().getName()
                };
                valueList.add(value);

            }
            WordCreateUtils.exportExcel(response, "报废商品明细表格", "报废商品明细表格", headArr, valueList);
        }
    }

    /**
     * 库存预警导出
     * 处理原始数据后导出信息为Excel
     * @param response 返回信息
     * @param list 原始数据列表 CHgoods
     * @param status 导出表格类型
     */
    public static void kcexport(HttpServletResponse response, List<CHgoods> list, String status){
        String[] headArr = {};
        List<String[]> valueList = new ArrayList<String[]>();
        if(StringUtils.isNotBlank(status)&&status.equals("1")) {
            String head[] = {"仓库名称", "商品名称", "规格型号", "大单位", "安全库存", "当前库存","差量"};
            headArr = head;
            for (CHgoods o : list) {
                double  c1=0.0;
                if(o.getYjnub().equals("null") && o.getYjnub().equals("")){
                      c1=0.0;
                }else {
                     c1 = Double.parseDouble(o.getYjnub()) - Double.parseDouble(o.getNub());
                }
                      String[] value = {getStr(o.getHouse().getName()), getStr(o.getGoods().getName()), getStr(o.getGoods().getSpec().getName()),
                              o.getGoods().getBig().getName(),getStr(o.getYjnub()), o.getNub(),getStr(String.valueOf(c1))
                      };
                      valueList.add(value);
                  }
        }
        WordCreateUtils.exportExcel(response,"库存预警表格","库存预警表格",headArr,valueList);
    }

    /**
     * 移库
     * 处理原始数据后导出信息为Excel
     * @param response 返回信息
     * @param list 原始数据列表 CCgzbinfoc
     * @param type 导出表格类型
     */
    public static void ykexcellist(HttpServletResponse response, List<CYkinfo> list,String type) {
        String[] headArr = {};
        List<String[]> valueList = new ArrayList<String[]>();
        if (StringUtils.isNotBlank(type) && type.equals("2")) {
            String head[] = {"仓库", "商品名称", "规格型号", "数量", "小单位数量", "小单位", "成本金额"};
            headArr = head;
            for (CYkinfo o : list) {

                double je = Double.parseDouble(o.getGoods().getCbj()) * Double.parseDouble(o.getNub());

                String[] value = {o.getStartHouse().getName(),o.getGoods().getName(),o.getGoods().getSpec().getName(),
                                  o.getSpecNub(),o.getNub(),o.getGoods().getSmall().getName(),String.valueOf(je)
                };
                valueList.add(value);

            }
            WordCreateUtils.exportExcel(response, "移库商品汇总表格", "移库商品汇总表格", headArr, valueList);
        }else {
            String head[] = {"仓库名称", "商品名称", "规格型号", "数量", "成本单价", "成本金额", "操作人", "操作时间"};
            headArr = head;
            for (CYkinfo o : list) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String str = sdf.format(o.getCreateDate());
                double je = Double.parseDouble(o.getNub()) * Double.parseDouble(o.getGoods().getCbj());
                String[] value = {o.getStartHouse().getName(),o.getGoods().getName(),o.getGoods().getSpec().getName(),
                                  o.getSpecNub(),o.getGoods().getCbj(),String.valueOf(je),o.getCreateBy().getName(),str
                };
                valueList.add(value);

            }
            WordCreateUtils.exportExcel(response, "移库商品明细表格", "移库商品明细表格", headArr, valueList);
        }
    }

    /**
     * 应收款导出
     * 处理原始数据后导出信息为Excel
     * @param response 返回信息
     * @param list 原始数据列表 CDdinfo
     * @param type 导出表格类型
     */
    public static void yskexport(HttpServletResponse response, List<FReceipt> list, String type){
        String[] headArr = {};
        List<String[]> valueList = new ArrayList<String[]>();
        if(StringUtils.isNotBlank(type)&&type.equals("2")) {
            String head[] = {"客户类别", "客户名称", "应收款", "实收款"};
            headArr = head;
            for (FReceipt o : list) {
                String[] value = {o.getTravelUnit().getSclass().getName(),o.getTravelUnit().getName(),
                                  o.getHtje(),o.getJe()
                };
                valueList.add(value);
            }
            WordCreateUtils.exportExcel(response,"应收款汇总表格","应收款汇总表格",headArr,valueList);
        } else  if(StringUtils.isNotBlank(type)&&type.equals("3")){
            String head[] = {"客户类别", "客户名称", "订单单号", "欠款日期", "应收款", "实收款","欠款金额"};
            headArr = head;
            for (FReceipt o : list) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String str = sdf.format(o.getReceiptDate());
                String[] value = {o.getTravelUnit().getSclass().getName(), o.getTravelUnit().getName(), o.getDdbh(),
                        str, o.getHtje(), o.getJe(), o.getReceiptMode()
                };
                valueList.add(value);
            }
            WordCreateUtils.exportExcel(response,"应收款欠款明细","应收款欠款明细",headArr,valueList);
        } else {
            String head[] = {"收款日期", "客户名称", "订单单号", "实付金额", "合同金额", "经手人"};
            headArr = head;
            for (FReceipt o : list) {

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String str = sdf.format(o.getReceiptDate());
                String[] value = {str,o.getTravelUnit().getName(),o.getDdbh(),o.getJe(),
                                  o.getHtje(),o.getJsr().getName()
                };
                valueList.add(value);
            }
            WordCreateUtils.exportExcel(response,"应收款明细表格","应收款明细表格",headArr,valueList);
        }

    }

    /**
     * 客户优惠导出
     * 处理原始数据后导出信息为Excel
     * @param response 返回信息
     * @param list 原始数据列表 CHgoods
     * @param status 导出表格类型
     */
    public static void khyhexcel(HttpServletResponse response, List<FDiscount> list, String status){
        String[] headArr = {};
        List<String[]> valueList = new ArrayList<String[]>();
        if(StringUtils.isNotBlank(status)&&status.equals("1")) {
            String head[] = {"客户名称", "订单编号", "优惠金额", "优惠时间", "备注"};
            headArr = head;
            for (FDiscount o : list) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String str = sdf.format(o.getCreateDate());
                String[] value = {o.getStore().getName(),o.getDdid().getDdbh(),o.getYhje(),
                                   str,o.getRemarks()
                };
                valueList.add(value);
            }
        }
        WordCreateUtils.exportExcel(response,"客户优惠表格","客户优惠表格",headArr,valueList);
    }
    /**
     * 客户优惠导出
     * 处理原始数据后导出信息为Excel
     * @param response 返回信息
     * @param list 原始数据列表 CHgoods
     * @param status 导出表格类型
     */
    public static void goodsexcel(HttpServletResponse response, List<CDdinfo> list, String status){
        String[] headArr = {};
        List<String[]> valueList = new ArrayList<String[]>();
        if(StringUtils.isNotBlank(status)&&status.equals("1")) {
            String head[] = {"客户名称", "商品名称", "规格型号", "规格数量", "最小单位数量","售价","优惠金额"};
            headArr = head;
            for (CDdinfo o : list) {
                String[] value = {o.getStore().getName(),o.getGoods().getName(),o.getGoods().getSpec().getName(),
                                 o.getType(),o.getNub(),o.getJe(),o.getYhje()
                };
                valueList.add(value);
            }
        }
        WordCreateUtils.exportExcel(response,"客户商品表格","客户商品表格",headArr,valueList);
    }

    /**
     * 商品价格导出
     * 处理原始数据后导出信息为Excel
     * @param response 返回信息
     * @param list 原始数据列表 CHgoods
     * @param status 导出表格类型
     */
    public static void goodslistexcel(HttpServletResponse response, List<CGoods> list, String status){
        String[] headArr = {};
        List<String[]> valueList = new ArrayList<String[]>();
        if(StringUtils.isNotBlank(status)&&status.equals("1")) {
            String head[] = {"商品名称", "品牌", "商品名称", "商品编码", "规格型号","成本小单价","成本中单价","成本大单价","参考成本价","销售小单价","销售中单价","销售大单价"};
            headArr = head;

            for (CGoods o : list) {
                String[] a=o.getSpec().getArrSpec();
                String[] value = {o.getGclass().getName(),o.getBands().getName(),o.getName(),o.getSort(),
                                  o.getSpec().getName(),o.getCbj(),
                };
                valueList.add(value);
            }
        }
        WordCreateUtils.exportExcel(response,"商品价格表格","商品价格表格",headArr,valueList);
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
