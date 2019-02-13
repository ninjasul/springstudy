package com.ninjasul.tobyspring31.learningtest.jdk.proxy;

import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@AllArgsConstructor
public class UppercaseHandler implements InvocationHandler {

    Hello target;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object ret = method.invoke(target, args);

        if( ret instanceof String &&
            method.getName().startsWith("say")) {
            return ((String) ret).toUpperCase();
        }

        return ret;
    }
}