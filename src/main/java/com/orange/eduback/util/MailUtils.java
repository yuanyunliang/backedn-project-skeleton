package com.orange.eduback.util;

import com.orange.eduback.domain.MailCode;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class MailUtils {

    public static void sendMail(JavaMailSender mailSender, String mail, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("442466076@qq.com");
        message.setSubject("千数咨询验证码");
        message.setText("您的验证码是：" + code + "，请在5分钟内输入。");
        message.setTo(mail);
        mailSender.send(message);

    }

    //生成邮箱验证码，这里我们生成一个6位的随机数
    public static String generateMailCode() {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            code.append((int) (Math.random() * 10));
        }
        return code.toString();
    }
}
