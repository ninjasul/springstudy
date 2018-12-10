package me.ninjasul.databinding.converter;

import lombok.Data;

@Data
public class ConverterEvent {
    private Integer id;
    private String title;

    public ConverterEvent(int id) {
        this.id = id;
    }
}