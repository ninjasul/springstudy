package com.ninjasul.tobyspring31.learningtest.spring.pointcut;

import org.junit.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PointcutTest {

    @Test
    public void methodSignaturePointcut() throws NoSuchMethodException {

        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(public int com.ninjasul.tobyspring31.learningtest.spring.pointcut.Target.minus(int, int) " + "throws java.lang.RuntimeException)");

        // Target.minus()
        assertTrue(pointcut.getClassFilter().matches(Target.class) &&
                    pointcut.getMethodMatcher().matches(Target.class.getMethod("minus", int.class, int.class),
        null)
        );

        // Target.plus()
        assertFalse(pointcut.getClassFilter().matches(Target.class) &&
                        pointcut.getMethodMatcher().matches(Target.class.getMethod("plus", int.class, int.class),
            null)
        );

        // Bean.method()
        assertFalse(pointcut.getClassFilter().matches(Bean.class) &&
                        pointcut.getMethodMatcher().matches(Bean.class.getMethod("method", int.class, int.class),
            null)
        );
    }
}