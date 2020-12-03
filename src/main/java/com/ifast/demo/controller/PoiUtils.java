package com.ifast.demo.controller;


import com.ifast.common.base.CoreService;
import com.ifast.sys.domain.DeptDO;
import com.ifast.sys.domain.UserDO;
import com.ifast.sys.service.DeptService;
import com.ifast.sys.service.UserService;
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
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * excel 表格工具类
 *
 * @author zenglong
 */
public class PoiUtils {

    private static UserService userService;

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
    public static void Export(HttpServletResponse response,String name,String deptId,List<UserDO>export) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");
//        List<UserDetails> classmateList = userService.getUserDetails();
        Date now=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd") ;
        // 设置要导出的文件的名字

        String fileName ="人员信息"+simpleDateFormat.format(now) +".xls";

        // 新增数据行，并且设置单元格数据
        int rowNum = 1;
//        List<UserDO>export=userService.exportUser(name,deptId);
//         headers表示excel表中第一行的表头 在excel表中添加表头
        String[] headers = { "姓名", "性别","部门", "邮箱","手机号", "创建时间", "修改时间", "出身日期",
                "现居住地", "爱好", "省份", "所在城市", "所在地区", "职级", "民族", "政治面貌", "身份证号", "编制所在单位"
                , "编制类别", "人员类别", "进入单位形式", "参加工作时间", "进入单位时间", "最高学历", "最高学历类型","毕业学校"
                ,"最高学位","毕业时间","专业","职称名称","职称取得时间","职称等级"};
        HSSFRow row = sheet.createRow(0);
        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        //在表中存放查询到的数据放入对应的列
        for (UserDO item : export) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(item.getName());//姓名
            row1.createCell(1).setCellValue(item.getSexName()==null?"":item.getSexName());//性别
            row1.createCell(2).setCellValue(item.getDeptName()==null?"":item.getDeptName());//部门
            row1.createCell(3).setCellValue(item.getEmail()==null?"":item.getEmail());//邮箱
            row1.createCell(4).setCellValue(item.getMobile()==null?"":item.getMobile());//手机号
            row1.createCell(5).setCellValue(simpleDateFormat.format(item.getGmtCreate()==null?now:item.getGmtCreate()));//创建时间
            row1.createCell(6).setCellValue(simpleDateFormat.format(item.getGmtModified()==null?now:item.getGmtModified()));//修改时间
            row1.createCell(7).setCellValue(simpleDateFormat.format(item.getBirth()==null?now:item.getBirth()));//出生时间
            row1.createCell(8).setCellValue(item.getLiveAddress()==null?"":item.getLiveAddress());//现居住地
            row1.createCell(9).setCellValue(item.getHobby()==null?"":item.getHobby());//爱好
            row1.createCell(10).setCellValue(item.getProvince()==null?"":item.getProvince());//省份
            row1.createCell(11).setCellValue(item.getCity()==null?"":item.getCity());//所在城市
            row1.createCell(12).setCellValue(item.getDistrict()==null?"":item.getDistrict());//所在地区
            row1.createCell(13).setCellValue(item.getRank()==null?"":item.getRank());//职级
            row1.createCell(14).setCellValue(item.getNation()==null?"":item.getNation());//民族
            row1.createCell(15).setCellValue(item.getPolitical()==null?"":item.getPolitical());//政治面貌
            row1.createCell(16).setCellValue(item.getIdnumber()==null?"":item.getIdnumber());//身份证号
            row1.createCell(17).setCellValue(item.getBzszdw()==null?"":item.getBzszdw());//编制所在单位
            row1.createCell(18).setCellValue(item.getBzlb()==null?"":item.getBzlb());//编制类别
            row1.createCell(19).setCellValue(item.getRyzt()==null?"":item.getRyzt());//人员类别
            row1.createCell(20).setCellValue(item.getJrdwxs()==null?"":item.getJrdwxs());//进入单位形式
            row1.createCell(21).setCellValue(simpleDateFormat.format(item.getJobStart()==null?now:item.getJobStart()));//参加工作时间
            row1.createCell(22).setCellValue(simpleDateFormat.format(item.getJrdwsj()==null?now:item.getJrdwsj()));//进入单位时间
            row1.createCell(23).setCellValue(item.getEducationTop()==null?"":item.getEducationTop());//最高学历
            row1.createCell(24).setCellValue(item.getEducationType()==null?"":item.getEducationType());//最高学历类型
            row1.createCell(25).setCellValue(item.getSchool()==null?"":item.getSchool());//毕业学校
            row1.createCell(26).setCellValue(item.getDegreeTop()==null?"":item.getDegreeTop());//最高学位
            row1.createCell(27).setCellValue(simpleDateFormat.format(item.getGraduationTime()==null? now:item.getGraduationTime()));//毕业时间
            row1.createCell(28).setCellValue(item.getMajor()==null?"":item.getMajor());//专业
            row1.createCell(29).setCellValue(item.getTitle()==null?"":item.getTitle());//职称名称
            row1.createCell(30).setCellValue(simpleDateFormat.format(item.getTitleTime()==null?now:item.getTitleTime()));//职称取得时间
            row1.createCell(31).setCellValue(item.getTitleLeve()==null?"":item.getTitleLeve());//职称等级
            rowNum++;
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" +new String(fileName.getBytes("utf-8") ,"ISO8859-1"));
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

}
