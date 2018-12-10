package me.ninjasul.databinding.propertyeditor;

import java.beans.PropertyEditorSupport;

// PropertyEditor는 Thread-safe 하지 않기 때문에 절대 빈으로 등록하여 사용하면 안됨.
// 대신 Controller에서 WebDataBinder 에 등록 해 주는 방법을 사용해야 함.
// 그리고, Object <-> String 간의 변환만 수행할 수 있는 단점도 있음.
public class EventEditor extends PropertyEditorSupport {

    // Event -> String
    @Override
    public String getAsText() {
        return ((EditorEvent)getValue()).getId().toString();
    }

    // String -> Event
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(new EditorEvent(Integer.parseInt(text)));
    }
}