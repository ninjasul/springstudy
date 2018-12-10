package me.ninjasul.beanscope;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

// prototype 빈이 singleton 빈의 필드로 참조될 경우 원래 prototype 의 의도와는 다르게 서로 다른 빈을 참조할 수 없다.
// 이 때 proxyMode 를 설정하여 Proto 빈을 proxy 빈으로 한번 감싸주도록 하여 매번 새로운 객체를 생성하게 할 수 있음.
@Component
@Scope(value = "prototype")
//@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Proto {

}