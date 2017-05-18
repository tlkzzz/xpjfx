package com.tlkzzz.jeesite.modules.sys.utils;

import com.tlkzzz.jeesite.common.config.Global;
import com.tlkzzz.jeesite.common.utils.FreeMarkers;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 16-5-19.
 */
public class WordCreateUtils {

    /**
     * @Desc：生成word文件
     * @Author：xrc
     * @Date：2016-5-19
     * @param dataMap word中需要展示的动态数据，用map集合来保存
     * @param templateName word模板名称，例如：test.ftl
     * @param filePath 文件生成的目标路径，例如：D:/wordFile/
     * @param fileName 生成的文件名称，例如：test.doc
     */
    @SuppressWarnings("unchecked")
    public static void createWord(Map dataMap,String templateName,String filePath,String fileName){
        try {
            //创建配置实例
            //ftl模板文件统一放至 resources/template 包下面
//            System.out.println("OutFilePath=====>"+filePath);
            Configuration configuration = FreeMarkers.buildConfiguration("classpath:/template");
            Template template = configuration.getTemplate(templateName); //获取模板
            File outFile = new File(filePath+fileName);//输出文件
            if (!outFile.getParentFile().exists()){//如果输出目标文件夹不存在，则创建
                outFile.getParentFile().mkdirs();
            }else {//如果存在则删除文件
                outFile.delete();
            }
            //将模板和数据模型合并生成文件
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"UTF-8"));
            template.process(dataMap, out);//生成文件
            out.flush();//关闭流
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Desc：下载文件
     * @Author：xrc
     * @Date：2016-5-25
     * @param response 获取文件输入网络路径
     * @param filePath 文件的信息，例如：路径
     * @param fileName 文件的信息，例如：名称
     */
    @SuppressWarnings("unchecked")
    public static void fileDownload(HttpServletResponse response, String filePath, String fileName){
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        try {
            response.setContentType("multipart/form-data");
            //2.设置文件头：最后一个参数是设置下载文件名
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            ServletOutputStream out;
            //通过文件路径获得File对象(假如此路径中有一个download.pdf文件)
            File file = new File(Global.getUserfilesBaseDir() + filePath);
            FileInputStream inputStream = new FileInputStream(file);
            //3.通过response获取ServletOutputStream对象(out)
            out = response.getOutputStream();
            int b = 0;
            byte[] buffer = new byte[1024];
            while (b < file.length()){
                int j = inputStream.read(buffer,0,1024);
                b += j;
                //4.写到输出流(out)中
                out.write(buffer,0,j);
            }
            inputStream.close();
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exportExcel(HttpServletResponse response,String fileName,String sheetName,String[] headArr,List<String[]> valueList){
        try{
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();
            workbook.setSheetName(0, sheetName);
            HSSFRow row = sheet.createRow((short) 0);
            HSSFCell cell;
            // 写入各个字段的名称
            for(int i=0;i<headArr.length;i++){
                cell = row.createCell((short) i);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(headArr[i]);
            }
            // 写入各条记录，每条记录对应Excel中的一行
            for (int i = 0; i < valueList.size(); i++) {
                row = sheet.createRow((short) i + 1);
                String value[] = valueList.get(i);
                for(int j=0;j<value.length;j++){
                    cell = row.createCell((short) j);
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    cell.setCellValue(value[j]);
                }
            }
            response.setContentType("multipart/form-data");
            //2.设置文件头：最后一个参数是设置下载文件名
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName+".xls", "UTF-8"));
            ServletOutputStream out = response.getOutputStream();
            workbook.write(out);
            out.flush();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void exportExcelByMode(HttpServletResponse response,String templateName,String fileName,String sheetName,List<String[]> valueList){
        try{
            String path = Global.getUserfilesBaseDir() + Global.USERFILES_BASE_URL+"file/"+ templateName;
            InputStream inputStream = new FileInputStream(new File(path));
            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            workbook.setSheetName(0, sheetName);
            HSSFRow row = sheet.getRow(5);
            HSSFCellStyle styleOne = row.getCell(1).getCellStyle();
            HSSFCell cell;
            //写入表头信息
            row = sheet.getRow(1);
            cell = row.getCell(3);
            cell.setCellValue(valueList.size());
            cell = row.getCell(5);
            cell.setCellValue("");
            // 写入各条记录，每条记录对应Excel中的一行
            for (int i = 0; i < valueList.size(); i++) {
                row = sheet.createRow((short) i + 5);
                String value[] = valueList.get(i);
                for(int j=0;j<value.length;j++){
                    cell = row.createCell((short) j);
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    cell.setCellValue(value[j]);
                    cell.setCellStyle(styleOne);
                }
            }
            response.setContentType("multipart/form-data");
            //2.设置文件头：最后一个参数是设置下载文件名
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName+".xls", "UTF-8"));
            ServletOutputStream out = response.getOutputStream();
            workbook.write(out);
            out.flush();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static HSSFWorkbook importExcelGetMode(HttpServletRequest request){
        HSSFWorkbook workbook = new HSSFWorkbook();
        try{
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)(request);      //spring用法
            Iterator<String> iter = multiRequest.getFileNames();
            MultipartFile file = null;
            while(iter.hasNext()){  //实际只针对单个循环一次 不知道什么原因
                file = multiRequest.getFile(iter.next());
            }
            InputStream inputStream = file.getInputStream();
            workbook = new HSSFWorkbook(inputStream);
        }catch (Exception e){
            e.printStackTrace();
        }
        return workbook;
    }

}
