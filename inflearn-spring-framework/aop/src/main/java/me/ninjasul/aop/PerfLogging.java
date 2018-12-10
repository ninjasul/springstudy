package me.ninjasul.aop;

import java.lang.annotation.*;

// 컴파일러가 클래스를 참조할 때까지 유효합니다. (기본값)
@Retention(RetentionPolicy.CLASS)

// 컴파일 이후에도 JVM에 의해서 참조가 가능합니다.
// @Retention(RetentionPolicy.RUNTIME)

// 어노테이션 정보는 컴파일 이후 없어집니다.
//@Retention(RetentionPolicy.SOURCE)
@Documented
@Target(ElementType.METHOD)
public @interface PerfLogging {
}
