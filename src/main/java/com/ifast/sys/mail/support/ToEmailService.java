package com.ifast.sys.mail.support;


import javax.mail.MessagingException;

import com.ifast.sys.mail.entity.ToEmail;
import com.ifast.sys.mail.util.response.JsonReturn;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author leilei
 * @version 1.0
 * @date 2020/3/12 14:32
 * @desc: 邮件发送接口
 */
public interface ToEmailService {


  /**
   * @param toEmail 发送对象
   * @return 统一返回ajax
   * @desc 发送普通邮件 （无其他资源 无html 无附件）
   */
  JsonReturn commonEmail(ToEmail toEmail) throws MessagingException;

  /**
   * @param toEmail 发送对象
   * @return 统一返回ajax
   * @Desc 发送html形式的邮件
   */
  JsonReturn htmlEmail(ToEmail toEmail) throws MessagingException;

  /**
   * 带附件 邮件发送
   */
  JsonReturn enclosureEmail(ToEmail toEmail, MultipartFile multipartFile);

  /**
   * 一同发送静态资源 图片等
   *
   * @param resId 每个资源对饮给一个Id
   */
  JsonReturn staticEmail(ToEmail toEmail, MultipartFile multipartFile, String resId);
}
