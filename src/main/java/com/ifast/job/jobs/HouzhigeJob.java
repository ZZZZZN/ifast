package com.ifast.job.jobs;

import com.ifast.common.utils.DateUtils;
import com.ifast.sys.domain.InvestigationDo;
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
public class HouzhigeJob implements Job {

    @Autowired
    private ToEmailService toEmailService;
    @Autowired
    private PetitionLetterService petitionLetterService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        //查询限期整改在当天还没改的负责人邮箱
        List<InvestigationDo> remind= petitionLetterService.selectTerm();

        for (InvestigationDo item:remind) {
            SendEmailForRemind(item.getEmail(),item.getProblem());
        }
    }

    //发邮件方法
    public void SendEmailForRemind(String emailAddress,String problem){
//        ToEmail toEmail=new ToEmail();
//        toEmail.setTos(emailAddress);
//        toEmail.setSubject("您有工程项目隐患问题请及时销号");
//        toEmail.setContent("提醒：您有项目隐患问题仍未销号.请及时销号处理。主要问题是："+problem);
//        try {
//            toEmailService.commonEmail(toEmail);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
    }


}
