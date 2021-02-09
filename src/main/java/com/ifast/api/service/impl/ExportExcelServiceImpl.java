package com.ifast.api.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ifast.api.config.ExcelFillCellMergeStrategy;
import com.ifast.api.mapper.dao.ExportExceldao;
import com.ifast.api.pojo.dto.ProjectDTO;
import com.ifast.api.service.ExportExcelService;
import com.ifast.common.component.oss.support.UploadServer;
import com.ifast.common.utils.DateUtils;
import com.ifast.common.utils.FileUtil;
import com.ifast.sys.domain.PetitionLetterNewDo;
import com.ifast.sys.mail.entity.ToEmail;
import com.ifast.sys.mail.support.ToEmailService;
import com.ifast.sys.service.PetitionLetterService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
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

    @Autowired
    private ToEmailService toEmailService;
    @Autowired
    private PetitionLetterService petitionLetterService;

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

    @Override
    public void sendOverDate() {
//处理业务逻辑，获取需要发送邮件的事项和邮箱地址
        List<PetitionLetterNewDo> remind= petitionLetterService.selectOverDate();
        //获取当前时间
        Date now =new Date();
        //为时间转换格式
        String nowTime= DateUtils.format(now,DateUtils.DATE_PATTERN_10);
        for (PetitionLetterNewDo item:remind) {
            //如果查询到的规定回复时间和当前时间相同则发送邮件
                //获取信访件信息
                JSONObject data=new JSONObject();
                data.put("sourcepetition",item.getSourcepetition());
                data.put("processtime",DateUtils.format(item.getProcesstime(),DateUtils.DATE_PATTERN_10));
                data.put("receiptno",item.getReceiptno());
                //获取部门邮箱
                String[] emailAddress=item.getEmail().split(",");
                if(emailAddress != null && emailAddress.length > 0){
                    SendEmailForRemind(emailAddress,data);
                }
        }
    }

    //发邮件方法
    public void SendEmailForRemind(String[] emailAddress,JSONObject data){
        ToEmail toEmail=new ToEmail();
        //获取当前时间
        Date now =new Date();
        //为时间转换格式
        String nowTime=DateUtils.format(now,DateUtils.DATE_PATTERN_10);
        toEmail.setTos(emailAddress);
        toEmail.setSubject("您有信访件已超时未处理");
        toEmail.setContent("提醒：您有信访件（信访编号："+data.get("receiptno")+"）的规定回复时间为"+nowTime+"日，目前该信访件处于超期未处理状态，请及时登录平台回复信访件！");
        try {
            toEmailService.commonEmail(toEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    }


