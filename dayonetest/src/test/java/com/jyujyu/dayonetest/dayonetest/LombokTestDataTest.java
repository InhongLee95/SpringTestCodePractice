package com.jyujyu.dayonetest.dayonetest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class LombokTestDataTest {

    @Test
    public void testDataTest() {
        TestData testData = new TestData();

        testData.setName("leeinhong");

        Assertions.assertEquals("leeinhong", testData.getName());



    }

}