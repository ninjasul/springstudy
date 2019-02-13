package com.springframework.aop;

import java.lang.annotation.*;

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
@Documented
public @interface PerfLogging {
}