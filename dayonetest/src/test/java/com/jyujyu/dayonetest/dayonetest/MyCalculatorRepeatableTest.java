package com.jyujyu.dayonetest.dayonetest;

import com.jyujyu.dayonetest.MyCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class MyCalculatorRepeatableTest {



    @DisplayName("덧셈을 5번 단순 반복하며 테스트")
    @RepeatedTest(5)
    public void repeatedAddTest() {

        MyCalculator myCalculator = new MyCalculator();

        myCalculator.add(10.0);

        Assertions.assertEquals(10.0, myCalculator.getResult());

        }

    @DisplayName("덧셈을 5번 파라미터를 넣어주며 반복하며 테스트")
    @ParameterizedTest
    @MethodSource("parameterizedTestParameters")
    public void prameterizedTest(Double addValue, Double expectValue) {
        // 준비
        MyCalculator myCalculator = new MyCalculator(0.0);

        // 행동
        myCalculator.add(addValue);

        // 검증
        Assertions.assertEquals(expectValue, myCalculator.getResult());


    }

    public static Stream<Arguments> parameterizedTestParameters() {
        return Stream.of(
                Arguments.of(10.0, 10.0),
                Arguments.of(8.0, 8.0),
                Arguments.of(4.0, 4.0),
                Arguments.of(16.0, 16.0),
                Arguments.of(16.0, 16.0)
        );
    }


    @DisplayName("파라미터를 넣으며 사칙연산 2번 반복 테스트")
    @ParameterizedTest
    @MethodSource("parameterizedComplicateCalculateTestParameters")
    public void parameterizedComplicateCalculateTest(
            Double addValue,
            Double minusValue,
            Double multiplyValue,
            Double divideValue,
            Double expectValue

    ) {

        // given
        MyCalculator myCalculator = new MyCalculator(0.0);

        //when
        Double result = myCalculator
                .add(addValue)
                .minus(minusValue)
                .multiply(multiplyValue)
                .divide(divideValue)
                .getResult();

        //then
        Assertions.assertEquals(expectValue, result);

    }

    public static Stream<Arguments> parameterizedComplicateCalculateTestParameters() {
        return Stream.of(
                Arguments.of(10.0, 4.0, 2.0, 3.0, 4.0),
                Arguments.of(4.0, 2.0, 4.0, 4.0, 2.0)
        );
    }


}
