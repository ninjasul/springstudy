package com.springframework.propertyeditor;

import java.beans.PropertyEditorSupport;

public class EventPropertyEditor extends PropertyEditorSupport {

    @Override
    public String getAsText() {
        return ((Event)getValue()).getId().toString();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Event event = new Event();
        event.setId(Integer.parseInt(text));
        setValue(event);
    }
}