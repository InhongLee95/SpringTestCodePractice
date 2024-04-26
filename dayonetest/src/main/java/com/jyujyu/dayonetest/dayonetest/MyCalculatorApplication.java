package com.jyujyu.dayonetest.dayonetest;


import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyCalculatorApplication {

    public static void main(String [] args) {
        MyCalculator myCalculator = new MyCalculator();

        myCalculator.add(10.0);
        myCalculator.minus(2.0);
        myCalculator.multiply(2.0);

        // 예외를 바로 던져서 호출하는 쪽에서 처리
        myCalculator.divide(0.0);

        System.out.println(myCalculator.getResult());
    }
}
