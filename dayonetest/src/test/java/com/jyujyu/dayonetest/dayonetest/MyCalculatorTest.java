package com.jyujyu.dayonetest.dayonetest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyCalculatorTest {

    /**
     * MyCalculator 기본생성자는 myCalculator객체를 0.0으로 초기화한다.
     */

    @Test
    void addTest() {
        MyCalculator myCalculator = new MyCalculator();
        myCalculator.add(10.0);
        Assertions.assertEquals(10.0, myCalculator.getResult());

    }

    @Test
    void minusTest() {
        MyCalculator myCalculator = new MyCalculator(10.0);

        myCalculator.minus(5.0);

        Assertions.assertEquals(5.0, myCalculator.getResult());

    }

    @Test
    void multiply() {
    }

    @Test
    void divide() {
    }

    @Test
    void complicatedCalculateTest() {
        // given
        MyCalculator myCalculator = new MyCalculator(0.0);

        myCalculator
                .add(10.0)
                .minus(4.0)
                .multiply(2.0)
                .divide(3.0)
                .getResult();

        Assertions.assertEquals(4.0, myCalculator.getResult());
    }

    @Test
    void divideZeroTest() {
        // given
        MyCalculator myCalculator = new MyCalculator(10.0);

        //when & then
        Assertions.assertThrows(MyCalculator.ZeroDivisionException.class, () -> {
                    myCalculator.divide(0.0);
                });

    }

}