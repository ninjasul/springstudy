package com.ninjasul.tobyspring31.user.service.helpers;

import lombok.Getter;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MockMailSender implements MailSender {

    @Getter
    private List<String> requests = new ArrayList<>();

    @Override
    public void send(SimpleMailMessage mailMessage) throws MailException {
        requests.add(mailMessage.getTo()[0]);
    }

    @Override
    public void send(SimpleMailMessage... mailMessages) throws MailException {
        //requests.addAll(Arrays.asList(mailMessages));
    }
}