package me.ninjasul.applicationcontext.event.sync.oldstyle;

import lombok.Data;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

// Spring Framework 4.2 미만에서는 ApplicationEvent 를 구현하여 이벤트 클래스를 정의해야 함.
@Getter
public class SyncOldEvent extends ApplicationEvent {

    private int data;

    public SyncOldEvent(Object source) {
        super(source);
    }

    public SyncOldEvent(Object source, int data) {
        super(source);
        this.data = data;
    }
}