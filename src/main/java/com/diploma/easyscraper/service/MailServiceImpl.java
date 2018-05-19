package com.diploma.easyscraper.service;

import com.diploma.easyscraper.interfaces.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailServiceImpl implements MailService {

    @Autowired
    public JavaMailSender emailSender;

    @Override
    public void send(String email, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Notification from EasyScraper");
        message.setText(content);
        emailSender.send(message);
    }

//    MimeMessage message = emailSender.createMimeMessage();
//      
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//     
//            helper.setTo(to);
//    helper.setSubject(subject);
//    helper.setText(text);
//         
//            FileSystemResource file
//      = new FileSystemResource(new File(pathToAttachment));
//    helper.addAttachment("Invoice", file);
// 
//            emailSender.send(message);
}
