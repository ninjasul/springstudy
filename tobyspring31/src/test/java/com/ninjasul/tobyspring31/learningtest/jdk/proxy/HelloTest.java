package com.ninjasul.tobyspring31.learningtest.jdk.proxy;

import com.ninjasul.tobyspring31.learningtest.jdk.proxy.*;
import org.junit.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

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

    @Test
    public void proxyFactoryBean() {

        ProxyFactoryBean pfBean = new ProxyFactoryBean();
        pfBean.setTarget(new HelloTarget());
        pfBean.addAdvice(new UppercaseAdvice());

        Hello proxiedHello = (Hello)pfBean.getObject();

        assertEquals("HELLO TOBY", proxiedHello.sayHello("Toby"));
        assertEquals("HI TOBY", proxiedHello.sayHi("Toby"));
        assertEquals("THANK YOU TOBY", proxiedHello.sayThankYou("Toby"));
    }

    @Test
    public void pointcutAdvisor() {
        ProxyFactoryBean pfBean = new ProxyFactoryBean();
        pfBean.setTarget(new HelloTarget());

        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedName("sayH*");

        // pointcut + advice 쌍을 Advisor 로 묶어 추가할 수 있는 구조임.
        pfBean.addAdvisor(new DefaultPointcutAdvisor(pointcut, new UppercaseAdvice()));

        Hello proxiedHello = (Hello)pfBean.getObject();

        assertEquals("HELLO TOBY", proxiedHello.sayHello("Toby"));
        assertEquals("HI TOBY", proxiedHello.sayHi("Toby"));
        assertEquals("Thank You Toby", proxiedHello.sayThankYou("Toby"));
    }

    @Test
    public void classNamePointcutAdvisor() {
        HelloNameMatchMethodPointcut classMethodPointcut = new HelloNameMatchMethodPointcut();
        classMethodPointcut.setMappedName("sayH*");

        checkAdviced(new HelloTarget(), classMethodPointcut, true);

        class HelloWorld extends HelloTarget {};
        checkAdviced(new HelloWorld(), classMethodPointcut, false);

        class HelloToby extends HelloTarget {};
        checkAdviced(new HelloToby(), classMethodPointcut, true);
    }

    private void checkAdviced(Object target, Pointcut pointcut, boolean adviced) {
        ProxyFactoryBean pfBean = new ProxyFactoryBean();
        pfBean.setTarget(target);
        pfBean.addAdvisor(new DefaultPointcutAdvisor(pointcut, new UppercaseAdvice()));

        Hello proxiedHello = (Hello)pfBean.getObject();

        if( adviced ) {
            assertEquals("HELLO TOBY", proxiedHello.sayHello("Toby"));
            assertEquals("HI TOBY", proxiedHello.sayHi("Toby"));
            assertEquals("Thank You Toby", proxiedHello.sayThankYou("Toby"));
        }
        else {
            assertEquals("Hello Toby", proxiedHello.sayHello("Toby"));
            assertEquals("Hi Toby", proxiedHello.sayHi("Toby"));
            assertEquals("Thank You Toby", proxiedHello.sayThankYou("Toby"));
        }
    }

    static class HelloNameMatchMethodPointcut extends NameMatchMethodPointcut {

        @Override
        public ClassFilter getClassFilter() {
            return new ClassFilter() {
                @Override
                public boolean matches(Class<?> clazz) {
                    return clazz.getSimpleName().startsWith("HelloT");
                }
            };
        }

    }
}