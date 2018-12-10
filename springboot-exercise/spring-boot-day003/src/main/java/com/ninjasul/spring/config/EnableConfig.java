package com.ninjasul.spring.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({Config1.class, Config2.class})
public @interface EnableConfig {
}
