package chap16_springtest.bank.domain;

public interface InterestCalculator {
    void setRate(double rate);
    double calculate(double amount, double year);
}