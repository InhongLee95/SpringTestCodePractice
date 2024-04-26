package com.jyujyu.dayonetest.dayonetest;

import org.junit.jupiter.api.*;

import java.util.List;



@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class JUnitPracticeTest {

    @Test
    @DisplayName("Assert Equals 메서드 테스트")
    public void assert_Equals_Test() {
        String expect = "Something";
        String actual = "Something";

        Assertions.assertEquals(expect, actual);
    }

    @Test
    @DisplayName("Assert NotEquals 메서드 테스트")
    public void assertNotEqualsTest() {
        String expect = "Something";
        String actual = "Hello";

        Assertions.assertNotEquals(expect, actual);
    }

    @Test
    @DisplayName("Assert True 메서드 테스트")
    public void assertTrueTest() {
        Integer a = 10;
        Integer b = 10;

        Assertions.assertTrue(a.equals(b));
    }

    @Test
    @DisplayName("Assert False 메서드 테스트")
    public void assertFalseTest() {
        Integer a = 10;
        Integer b = 20;

        Assertions.assertFalse(a.equals(b));
    }

    @Test
    @DisplayName("Assert Throws 메서드 테스트")
    public void assertThrowsTest() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("임의로 발생시킨 에러");
        });

    }

    @Test
    @DisplayName("Assert Not Null 메서드 테스트")
    public void assertNotNullTest() {
        String value = "Hello";
        Assertions.assertNotNull(value);


    }

    @Test
    public void assertNullTest() {
        String value = null;
        Assertions.assertNull(value);

    }

    @Test
    public void assertIterableEquals() {
        List<Integer> list1 = List.of(1, 2);
        List<Integer> list2 = List.of(1, 2);

        Assertions.assertIterableEquals(list1, list2);

    }


    @Test
    public void assertAllTest() {
        String expect = "Something";
        String actual = "Something";



        List<Integer> list1 = List.of(1, 2);
        List<Integer> list2 = List.of(1, 2);



        Assertions.assertAll("Assert All", List.of(
                () -> {Assertions.assertEquals(expect, actual); },
                () -> {Assertions.assertIterableEquals(list1, list2); }
        ));
    }

}
