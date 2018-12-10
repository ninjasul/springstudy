package me.ninjasul.beanscope;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Single {


    /*
    @Autowired
    Proto proto;
    */

    /*
    public Proto getProto() {
        return proto;
    }
    */

    // Proxy 를 사용하지 않고 ObjectProvider 를 사용할 수도 있음.
    @Autowired
    private ObjectProvider<Proto> proto;

    public Proto getProto() {
        return proto.getIfAvailable();
    }
}