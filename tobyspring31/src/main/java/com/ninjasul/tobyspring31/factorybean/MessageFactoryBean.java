package com.ninjasul.tobyspring31.factorybean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;

public class MessageFactoryBean implements FactoryBean<Message> {

    @Getter @Setter
    @Value("Factory Bean")
    String text;

    @Override
    public Message getObject() throws Exception {
        return Message.newMessage(text);
    }

    @Override
    public Class<?> getObjectType() {
        return Message.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}