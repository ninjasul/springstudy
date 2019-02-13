package com.springframework.nullsafety;

import lombok.extern.log4j.Log4j2;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class EventService {

    @NonNull
    public String createEvent(@NonNull String name) {
        return "Created " + name + " event";
    }

    @NonNull
    public String getEvent() {
        return null;
    }
}