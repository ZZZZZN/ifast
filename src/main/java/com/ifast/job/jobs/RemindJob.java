package com.ifast.job.jobs;

import com.alibaba.fastjson.JSONObject;
import com.ifast.common.utils.DateUtils;
import com.ifast.sys.domain.PetitionLetterNewDo;
import com.ifast.sys.mail.entity.ToEmail;
import com.ifast.sys.mail.support.ToEmailService;
import com.ifast.sys.service.PetitionLetterService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.List;

@Component
public class RemindJob implements Job {

    @Autowired
    private ToEmailService toEmailService;
    @Autowired
    private PetitionLetterService petitionLetterService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        //处理业务逻辑，获取需要发送邮件的事项和邮箱地址
        List<PetitionLetterNewDo> remind= petitionLetterService.selectRemind();
        //获取当前时间
        Date now =new Date();
        //为时间转换格式
        String nowTime=DateUtils.format(now,DateUtils.DATE_PATTERN_10);
        for (PetitionLetterNewDo item:remind) {
            //如果查询到的规定回复时间和当前时间相同则发送邮件
            if(DateUtils.add(item.getProcesstime(),1).equals(nowTime)){
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
    }

    //发邮件方法
    public void SendEmailForRemind(String[] emailAddress,JSONObject data){
        ToEmail toEmail=new ToEmail();
        //获取当前时间
        Date now =new Date();
        //为时间转换格式
        String nowTime=DateUtils.format(now,DateUtils.DATE_PATTERN_10);
        toEmail.setTos(emailAddress);
        toEmail.setSubject("您有信访件需要按时处理请及时查看");
        toEmail.setContent("提醒：您有信访件（信访编号："+data.get("receiptno")+"）的规定回复时间为"+nowTime+"日，请您登录政务平台，查看您的信访件，完成后返回处理意见");
        try {
            toEmailService.commonEmail(toEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


}
