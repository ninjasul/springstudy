package com.springframework.eventpublisher;

import lombok.*;

@Data
@Builder
public class MyEvent {

    private Object source;
    private int data;

}