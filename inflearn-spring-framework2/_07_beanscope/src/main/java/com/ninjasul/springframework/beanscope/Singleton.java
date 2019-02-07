package com.ninjasul.springframework.beanscope;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Setter
public class Singleton {

    //@Autowired
    //Prototype prototype;

    @Autowired
    private ObjectProvider<Prototype> prototype;

    public Prototype getPrototype() {
        return prototype.getIfAvailable();
    }
}