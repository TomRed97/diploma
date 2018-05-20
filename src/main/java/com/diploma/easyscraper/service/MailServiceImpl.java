package com.diploma.easyscraper.service;

import com.diploma.easyscraper.interfaces.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.activation.FileTypeMap;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class MailServiceImpl implements MailService {

    @Autowired
    public JavaMailSender emailSender;

    @Override
    public void send(String email, String content, String attachmentName) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(email);
        helper.setSubject("Notification from EasyScraper");
        helper.setText(content);

        File currDir = new File(".");
        String path = currDir.getAbsolutePath();

        String fileLocation = path.substring(0, path.length() - 1) + attachmentName;
        File file = new File(fileLocation);
        helper.addAttachment(attachmentName, file);
        emailSender.send(message);

        file.delete();
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
