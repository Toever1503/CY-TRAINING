package com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @RequestMapping("/test")
    public String test() {
        System.out.println(javaMailSender == null);
        Context context = new Context();
        context.setVariable("hello", "Hello world");
        context.setVariable("title", "Welcome user");
        String emailContent = templateEngine.process("mail/hello.html", context);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            mimeMessage.setFrom(new javax.mail.internet.InternetAddress("shiki@animenews.life"));
            mimeMessage.addRecipients(MimeMessage.RecipientType.TO, "haunvph13497@fpt.edu.vn");
            mimeMessage.setSubject("Welcome to AnimeNews");
            mimeMessage.setContent(emailContent, "text/html");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mimeMessage);

        return "";
    }

    @RequestMapping("/send")
    public String sendMail() {
        return "mail";
    }
}
