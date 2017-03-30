package com.tlkzzz.jeesite.modules.sys.utils;

import com.tlkzzz.jeesite.common.utils.Encodes;
import com.tlkzzz.jeesite.common.utils.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.List;

/**
 * 工具类
 * @author xrc
 * @version 2017-03-13
 */
public class ToolsUtils {
	static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
	/**
	 * 单位工具方法（合成商品规格单位）
	 * @param spec
	 * @param unit
	 * @return
	 */
	public static String unitTools(String spec,String[] unit){
		StringBuffer str = new StringBuffer();
		if(StringUtils.isNotBlank(spec)){
			String[] specList = spec.split("\\*");
			for(int i=0;i<specList.length;i++){
				if(unit.length>i)str.append(specList[i]+unit[i]);
			}
		}
		return str.toString();
	}

	/**
	 * 单位工具方法（合成商品规格数量）
	 * @param spec
	 * @param unit
	 * @param num
	 * @return
	 */
	public static String unitTools(String spec,String[] unit,Integer num){
		StringBuffer str = new StringBuffer();
		num = (String.valueOf(num).equals("null"))?0:num;
		if(StringUtils.isNotBlank(spec)&&num>0){
			String[] specList = spec.split("\\*");
			for(int i=0;i<specList.length;i++){
				if(unit.length>i){
					if(num>Integer.parseInt(specList[i])){
						int total = 0;
						String formula = "";
						for(int j=i+1;j<specList.length;j++){
							formula += specList[j].toString() + ((j<specList.length-1)?"*":"");
						}
						try{
							total = (int)Double.parseDouble(jse.eval(formula).toString());
						}catch (Exception e){}
						int subTotal = (int)Math.floor(num/total);
						str.append(subTotal+unit[i]);
						if(subTotal>0)num-=(subTotal*total);
					}else {
						str.append(num+unit[i]);
					}
				}
			}
		}
		return str.toString();
	}

	/**
	 * 价格动态平衡工具
	 * @param stockNum
	 * @param addNum
	 * @param stockPrice
	 * @param addPrice
	 * @return
	 */
	public static Double priceDynamicBalanceTools(Integer stockNum, Integer addNum, Double stockPrice, Double addPrice){
		DecimalFormat df = new DecimalFormat("#.####");
		double balancePrice = addPrice;
		if(!String.valueOf(stockNum).equals("null")&&!String.valueOf(stockPrice).equals("null")) {
			balancePrice = (stockPrice * stockNum + addPrice * addNum) / (stockNum + addNum);
			balancePrice = Double.parseDouble(df.format(balancePrice));
		}
		return balancePrice;
	}

	/**
	 * 生成商品编号（一位大写字母六位数字）
	 * @param length
	 * @return
	 */
	public static String randomGoodsNO(int length){
		String retStr = "";
		int param = 1;
		for(int i=length-2;i>0;i--){param=param*10;}
		char c = (char)(int)(Math.random()*26+65);
		retStr =  String.valueOf(c);
		retStr += (int)((Math.random()*9+1)*param);
		if(StringUtils.isBlank(retStr))retStr = randomGoodsNO(length);
		return retStr;
	}

	public static void exportExcel(HttpServletResponse response, String sheetName, String[] title, List<String[]> list){
		// 第一步，创建一个workBook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		SXSSFWorkbook W = new  SXSSFWorkbook();

		// 第二步，在workBook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet(sheetName);
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("序号");
		cell.setCellStyle(style);
		for(int i=0;i<title.length;i++) {
			cell = row.createCell((short) i+1);
			cell.setCellValue(title[i]);
			cell.setCellStyle(style);
		}
		// 第五步，写入实体数据
		for (int i = 0; i < list.size(); i++)
		{
			row = sheet.createRow((int) i + 1);
			row.createCell(0).setCellValue(i+1);
			String[] stu = list.get(i);
			// 第四步，创建单元格，并设置值
			for(int j=0;j<stu.length;j++) {
				row.createCell((short) j+1).setCellValue(stu[j]);
			}
		}
		// 第六步，将文件存到指定位置
		try
		{
//			FileOutputStream fout = new FileOutputStream("E:/students.xls");//保存到本地磁盘
			response.reset();
			response.setContentType("application/octet-stream; charset=utf-8");
			response.setHeader("Content-Disposition", "attachment; filename="+ Encodes.urlEncode(sheetName+".xls"));
			OutputStream fout	 = response.getOutputStream();
			wb.write(fout);
			fout.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
