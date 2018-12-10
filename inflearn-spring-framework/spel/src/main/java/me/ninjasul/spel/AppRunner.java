package me.ninjasul.spel;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Log4j2
public class AppRunner implements ApplicationRunner {

    @Value("#{1 + 1}")
    int value;

    @Value("#{'hello' + ' world'}")
    String greeting;

    @Value("#{1 eq 1}")
    boolean trueOrFalse;

    @Value("hello")
    String hello;

    @Value("${my.value}")
    String myValue;

    @Value("#{${my.value} eq 100}")
    boolean isMyValue100;

    @Value("#{'spring'}")
    String spring;

    @Value("#{sample.data}")
    int sampleData;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("===========================");
        log.info(value);
        log.info(greeting);
        log.info(trueOrFalse);
        log.info(hello);
        log.info(myValue);
        log.info(isMyValue100);
        log.info(spring);
        log.info(sampleData);

        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("2 + 100");

        // expression.getValue 를 사용할 때 ConversionService를 사용함.
        Integer value = expression.getValue(Integer.class);
        log.info(value);
    }
}
