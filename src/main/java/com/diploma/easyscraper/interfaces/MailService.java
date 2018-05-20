package com.diploma.easyscraper.interfaces;

import javax.mail.MessagingException;

public interface MailService {

    void send(String email, String content, String attachmentName) throws MessagingException;

}
