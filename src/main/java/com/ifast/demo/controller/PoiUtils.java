package com.ifast.demo.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.ifast.common.base.CoreService;
import com.ifast.sys.domain.DeptDO;
import com.ifast.sys.service.DeptService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.*;

/**
 * excel 表格工具类
 *
 * @author zenglong
 */
public class PoiUtils {

    /**
     * 通用读取方法
     * Excel要求 ：
     * 第一行为表头，表头字段即map的键；
     * 第二行为数据内容；
     * 注意：
     * 这里默认只读取第一个工作簿；
     *
     * @param filePath
     * @return ListMap
     */
    public static List<Map<String, Object>> getList(String filePath) {
        File excelFile = new File(filePath);
        FileInputStream is = null;
        Workbook workbook = null;
        List<Map<String, Object>> listMap = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        try {
            is = new FileInputStream(excelFile);
            if (is != null) {
                workbook = WorkbookFactory.create(is);
                Sheet sheet = workbook.getSheetAt(0);
                Iterator<Row> rows = sheet.rowIterator();
                System.out.println("开始读取表头 ");
                Row title = rows.next();
                // 列数
                int cols = 0;
                for (int i = 0; i < title.getPhysicalNumberOfCells(); i++) {
                    titles.add(title.getCell(i).toString());
                    cols++;
                }
                System.out.println("表头：" + titles);

                System.out.println("开始读取数据 ");
                while (rows.hasNext()) {
                    Map<String, Object> map = new HashMap<>(256);
                    org.apache.poi.ss.usermodel.Row row = rows.next();
                    for (int i = 0; i < cols; i++) {
                        Cell cell = row.getCell(i);
                        if (cell != null) {
                            if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                String temp = new DecimalFormat("#.########").format(cell.getNumericCellValue());
                                map.put(titles.get(i), temp);
                            } else {
                                map.put(titles.get(i), cell.getStringCellValue());
                            }
                        } else {
                            map.put(titles.get(i), "");
                        }
                    }
                    listMap.add(map);
                }
                System.out.println("读取完毕 ,总数：" + listMap.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    System.out.println("流关闭失败");
                }
            }
        }
        return listMap.size() == 0 ? null : listMap;
    }



    // 将数据导出成excel文件
    public static void Export(HttpServletResponse response,String name,String predeptId) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");
//        List<UserDetails> classmateList = userService.getUserDetails();

        // 设置要导出的文件的名字
        String fileName = name  + ".xls";

        // 新增数据行，并且设置单元格数据
        int rowNum = 1;

        // headers表示excel表中第一行的表头 在excel表中添加表头
//        String[] headers = { "id", "uid", "地址", "城市"};
//        HSSFRow row = sheet.createRow(0);
//        for(int i=0;i<headers.length;i++){
//            HSSFCell cell = row.createCell(i);
//            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
//            cell.setCellValue(text);
//        }

        //在表中存放查询到的数据放入对应的列
//        for (UserDetails item : classmateList) {
//            HSSFRow row1 = sheet.createRow(rowNum);
//            row1.createCell(0).setCellValue(item.getId());
//            row1.createCell(1).setCellValue(item.getUid());
//            row1.createCell(2).setCellValue(item.getAddress());
//            row1.createCell(3).setCellValue(item.getCity());
//            rowNum++;
//        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

}
