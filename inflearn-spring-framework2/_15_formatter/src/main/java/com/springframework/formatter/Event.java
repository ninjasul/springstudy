package com.springframework.formatter;

import lombok.Data;

@Data
public class Event {
    private Integer id;
    private String title;

    public Event(Integer id) {
        this.id = id;
    }
}