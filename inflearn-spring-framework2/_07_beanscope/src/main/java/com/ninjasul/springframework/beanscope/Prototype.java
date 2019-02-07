package com.ninjasul.springframework.beanscope;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
//@Scope(value="prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Scope(value="prototype")
public class Prototype {
}