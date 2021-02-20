package com.ifast.job.jobs;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonArray;
import com.ifast.common.utils.DateUtils;
import com.ifast.sys.domain.PetitionLetterNewDo;
import com.ifast.sys.mail.entity.ToEmail;
import com.ifast.sys.mail.support.ToEmailService;
import com.ifast.sys.service.PetitionLetterService;
import org.apache.commons.lang.StringUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class SubmitJob implements Job {

    @Autowired
    private ToEmailService toEmailService;
    @Autowired
    private PetitionLetterService petitionLetterService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        //处理业务逻辑，获取需要发送邮件的事项和邮箱地址
        List<PetitionLetterNewDo> remind= petitionLetterService.selectsubmit();
        //获取当前时间
        Date now =new Date();
        //为时间转换格式
        String nowTime=DateUtils.format(now,DateUtils.DATE_PATTERN_10);

        for (PetitionLetterNewDo item:remind) {
            //如果查询到的规定回复时间和当前时间相同则发送邮件
                //获取部门邮箱

                JSONObject data=new JSONObject();
                String[] emailAddress=item.getEmail().split(",");
                data.put("sourcepetition",item.getSourcepetition());
                data.put("processtime",DateUtils.format(item.getProcesstime(),DateUtils.DATE_PATTERN_10));
                data.put("receiptno",item.getReceiptno());
                if(emailAddress != null && emailAddress.length > 0){

                    try {
                        SendEmailForRemind(emailAddress,data);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                        return;
                    }finally {
                        return;
                    }
                }

                //修改提醒状态
                Integer id= Integer.parseInt(item.getId().toString());
                petitionLetterService.updateTxStatus(id);
        }
    }

    //发邮件方法
    public void SendEmailForRemind(String[] emailAddress,JSONObject data) throws MessagingException {
        ToEmail toEmail=new ToEmail();
        toEmail.setTos(emailAddress);
        toEmail.setSubject("您有信访件需要按时处理请及时查看");
        toEmail.setContent("提醒：您有来自"+data.get("sourcepetition").toString()+"的信访件(信访编号："+data.get("receiptno")+")待处理，请在"+data.get("processtime").toString()+"日前处理并登入平台回复处理情况。");
        try {
            toEmailService.commonEmail(toEmail);
        } catch (MessagingException e) {
            throw e;
        }
    }


}
