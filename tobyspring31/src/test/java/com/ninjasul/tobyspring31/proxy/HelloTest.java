package com.ninjasul.tobyspring31.proxy;

import org.junit.Test;

import java.lang.reflect.Proxy;

import static org.junit.Assert.*;

public class HelloTest {

    @Test
    public void simpleProxy() {

        Hello hello = new HelloTarget();
        assertEquals("Hello Toby", hello.sayHello("Toby"));
        assertEquals("Hi Toby", hello.sayHi("Toby"));
        assertEquals("Thank You Toby", hello.sayThankYou("Toby"));
    }

    @Test
    public void helloUppercase() {
        Hello hello = new HelloUppercase(new HelloTarget());

        assertEquals("HELLO TOBY", hello.sayHello("Toby"));
        assertEquals("HI TOBY", hello.sayHi("Toby"));
        assertEquals("THANK YOU TOBY", hello.sayThankYou("Toby"));
    }

    @Test
    public void helloDynamicProxy() {
        Hello proxiedHello = (Hello) Proxy.newProxyInstance( getClass().getClassLoader(),
                                                            new Class[] { Hello.class },
                                                            new UppercaseHandler(new HelloTarget()));

        assertEquals("HELLO TOBY", proxiedHello.sayHello("Toby"));
        assertEquals("HI TOBY", proxiedHello.sayHi("Toby"));
        assertEquals("THANK YOU TOBY", proxiedHello.sayThankYou("Toby"));
    }
}