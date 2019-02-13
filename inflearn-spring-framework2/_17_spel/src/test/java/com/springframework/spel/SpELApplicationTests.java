package com.springframework.spel;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class SpELApplicationTests {

    @Value("#{2 + 3}")
    int value;

    @Value("#{'Hello ' + 'Spring'}")
    String greeting;

    @Value("#{1 eq 1}")
    boolean trueOrFalse;

    @Value("#{'hello'}")
    String hello;

    @Value("spring")
    String spring;

    @Value("${my.value}")
    int myValue;

    @Value("#{${my.value} eq 100}")
    boolean isMyValueEqualTo100;

    @Value("#{sample.data}")
    int sampleData;

    @Autowired
    Sample autowiredSample;

    @Test
    public void spel() {
        assertEquals( (2 + 3), value );
        assertEquals( "Hello Spring", greeting );
        assertTrue( trueOrFalse );
        assertEquals( "hello", hello );
        assertEquals( "spring", spring );
        assertEquals(100, myValue);
        assertTrue( isMyValueEqualTo100 );
        assertEquals(9999, sampleData);
    }

    @Test
    public void expressionParser() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("100 * 4");
        int result = expression.getValue(Integer.class);

        assertEquals( 100 * 4, result );

        StandardEvaluationContext sampleContext = new StandardEvaluationContext(autowiredSample);
        Expression expression2 = parser.parseExpression("message");
        String result2 = expression2.getValue(sampleContext, String.class);
        assertEquals( autowiredSample.getMessage(), result2 );
    }


}

