package com.ninjasul.tobyspring31.learningtest.spring.pointcut;

public interface TargetInterface {
    void hello();
    void hello( String str );
    int minus( int a, int b ) throws RuntimeException;
    int plus( int a, int b );
}