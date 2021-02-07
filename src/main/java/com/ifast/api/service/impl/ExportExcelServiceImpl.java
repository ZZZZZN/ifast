package com.ifast.api.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.fastjson.JSONArray;
import com.ifast.api.config.ExcelFillCellMergeStrategy;
import com.ifast.api.mapper.dao.ExportExceldao;
import com.ifast.api.pojo.dto.ProjectDTO;
import com.ifast.api.service.ExportExcelService;
import com.ifast.common.component.oss.support.UploadServer;
import com.ifast.common.utils.FileUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.net.URLEncoder;
import java.util.List;

/**
 * 导出excel
 */
@Service
@AllArgsConstructor
@Data
@Transactional(rollbackFor = Exception.class)
public class ExportExcelServiceImpl implements ExportExcelService {

    @Autowired
    private ExportExceldao exportExceldao;

    @Qualifier("aliyunUploadServer")
    @Autowired
    private UploadServer uploadServer;

    @Override
    public String exportProject() throws IOException {
        List<ProjectDTO> list =exportExceldao.selectProjectList();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int[] mergeColumnIndex = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,30};

            int mergeRowIndex = 5;
            String templateFileName = "C:/temple/project.xlsx";/*FileUtil.getPath() + "templates/excel" + File.separator + "project.xlsx"*/;
            String fileName = URLEncoder.encode("下载后的名称.xls", "utf-8");

            FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
            EasyExcel.write(out, ProjectDTO.class).withTemplate(templateFileName)
//                    excel版本
                    .excelType(ExcelTypeEnum.XLSX)
//                    是否自动关流
                    .autoCloseStream(Boolean.TRUE)
                    .registerWriteHandler(new ExcelFillCellMergeStrategy(mergeRowIndex,mergeColumnIndex)).
                    sheet("项目列表").doFill(list);
           /*EasyExcel.write(out, JSONArray.class).withTemplate(templateFileName).sheet("项目列表").doFill(list,fillConfig);*/
            String url = uploadServer.upload(out.toByteArray(),"项目列表.xlsx");
            out.close();
            return url;
    }
}
