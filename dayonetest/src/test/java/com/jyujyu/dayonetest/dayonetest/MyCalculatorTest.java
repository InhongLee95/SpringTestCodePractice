package com.jyujyu.dayonetest.dayonetest;

import com.jyujyu.dayonetest.MyCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MyCalculatorTest {

    /**
     * MyCalculator 기본생성자는 myCalculator객체를 0.0으로 초기화한다.
     */

    @Test
    @DisplayName("MyCalculator 덧셈 테스트")
    void addTest() {
        MyCalculator myCalculator = new MyCalculator();
        myCalculator.add(10.0);
        Assertions.assertEquals(10.0, myCalculator.getResult());

    }

    @Test
    @DisplayName("MyCalculator 뺄셈 테스트")
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

        //when
        Double result = myCalculator
                .add(10.0)
                .minus(4.0)
                .multiply(2.0)
                .divide(3.0)
                .getResult();

        Assertions.assertEquals(4.0, result);
    }

    @Test
    @DisplayName("0으로 나누었을 때에는 ZeroDivisionException을 발생시켜야 한다.")
    void divideZeroTest() {
        // given
        MyCalculator myCalculator = new MyCalculator(10.0);

        //when & then
        Assertions.assertThrows(MyCalculator.ZeroDivisionException.class, () -> {
                    myCalculator.divide(0.0);
                });

    }

}