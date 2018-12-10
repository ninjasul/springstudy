package me.ninjasul.databinding.propertyeditor;

import lombok.Data;

@Data
public class EditorEvent {
    private Integer id;
    private String title;

    public EditorEvent(int id) {
        this.id = id;
    }
}