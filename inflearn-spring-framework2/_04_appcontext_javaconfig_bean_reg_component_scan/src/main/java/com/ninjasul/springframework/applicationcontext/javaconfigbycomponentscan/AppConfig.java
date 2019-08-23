package com.ninjasul.springframework.applicationcontext.javaconfigbycomponentscan;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {ApplicationcontextApplication.class})
public class AppConfig {
}
