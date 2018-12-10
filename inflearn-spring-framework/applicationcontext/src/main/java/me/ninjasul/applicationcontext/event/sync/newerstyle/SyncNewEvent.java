package me.ninjasul.applicationcontext.event.sync.newerstyle;

import lombok.Getter;

// Spring Framework 4.2 이상 부터는 ApplicationEvent 를 구현하지 않고 POJO 형태로 Event 클래스를 정의할 수 있음.
@Getter
public class SyncNewEvent {

    private Object source;
    private int data;

    public SyncNewEvent(Object source, int data) {
        this.source = source;
        this.data = data;
    }
}