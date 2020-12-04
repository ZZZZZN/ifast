package com.ifast.sys.mail.test;


import com.ifast.sys.mail.entity.ToEmail;
import com.ifast.sys.mail.support.ToEmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringbootEmailApplicationTests {

    @Autowired
    private ToEmailService toEmailService;

/*    @Test
    void contextLoads() {
    }*/
    @Test
    public void testHtml() throws Exception {
        String content = "<html>\n" +
            "<body>\n" +
            "    <h1>这是Html格式邮件!,不信你看邮件，我字体比一般字体还要大</h1>\n" +
            "</body>\n" +
            "</html>";
        toEmailService.htmlEmail(new ToEmail(new String[]{"1044890220@qq.com"},"Html邮件",content));
    }

}
