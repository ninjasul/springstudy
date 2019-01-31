package com.ninjasul.tobyspring31.user.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

@Log4j2
public class DummyMailService implements MailSender {

    @Override
    public void send(SimpleMailMessage simpleMessage) throws MailException {
        log.info("send a message");
    }

    @Override
    public void send(SimpleMailMessage... simpleMessages) throws MailException {
        log.info("send messages");
    }
}