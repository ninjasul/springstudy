package chap16_springtest.bank.domain;

import chap16_springtest.bank.domain.InterestCalculator;
import chap16_springtest.bank.domain.SimpleInterestCalculator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleInterestCalculatorTest {

    InterestCalculator interestCalculator;

    @Before
    public void setUp() throws Exception {
        interestCalculator = new SimpleInterestCalculator();
        interestCalculator.setRate(0.05);
    }

    @Test
    public void calculate() {
        double interest = interestCalculator.calculate( 10000, 2 );
        assertEquals(1000.0, interest, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalAmountCalculate() {
        interestCalculator.calculate( -10000, 2 );
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalYearCalculate() {
        interestCalculator.calculate( 10000, -0.5 );
    }
}