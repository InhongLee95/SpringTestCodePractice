package com.jyujyu.dayonetest.dayonetest;

import com.jyujyu.dayonetest.TestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class LombokTestDataTest {

    @Test
    public void testDataTest() {
        TestData testData = new TestData();

        testData.setName("leeinhong");

        Assertions.assertEquals("leeinhong", testData.getName());



    }

}