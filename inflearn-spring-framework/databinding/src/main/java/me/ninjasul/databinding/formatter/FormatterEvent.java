package me.ninjasul.databinding.formatter;

import lombok.Data;

@Data
public class FormatterEvent {
    private Integer id;
    private String title;

    public FormatterEvent(int id) {
        this.id = id;
    }
}