package com.ninjasul.tobyspring31.factorybean;

import lombok.Getter;
import lombok.Setter;

public class Message {

    @Getter @Setter
    String text;

    private Message(String text) {
        this.text = text;
    }

    public static Message newMessage(String text) {
        return new Message(text);
    }

}