package com.ifast.job.jobs;

import com.ifast.common.utils.DateUtils;
import com.ifast.sys.mail.entity.ToEmail;
import com.ifast.sys.mail.support.ToEmailService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.util.Date;

/**
 * <pre>
 * </pre>
 * 
 * <small> 2018年3月22日 | Aron</small>
 */
@Component
public class MailJob implements Job {

    @Autowired
    private ToEmailService toEmailService;
    @Override
    public void execute(JobExecutionContext context){
        String[] to= new String[1];
        ToEmail toEmail=new ToEmail();
        //处理业务逻辑，获取需要发送邮件的事项和邮箱地址
        to[0]="buildtest1202@163.com";
        toEmail.setTos(to);
        toEmail.setSubject("工作提醒");
        toEmail.setContent("请参照上一份邮件进行日常工作，完成后返回处理意见");
        try {
            toEmailService.commonEmail(toEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

}
