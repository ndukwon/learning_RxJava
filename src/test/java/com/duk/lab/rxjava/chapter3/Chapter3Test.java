package com.duk.lab.rxjava.chapter3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Chapter3Test {

    @DisplayName("Test Example 01")
    @Test
    void testEx1_map() {
        new Chapter3().ex1_map();
    }

    @DisplayName("Test Example 02")
    @Test
    void testEx2_map() {
        new Chapter3().ex2_mapWithFunction();
    }

    @DisplayName("Test Example 03")
    @Test
    void testEx3_map() {
        new Chapter3().ex3_mapWithConvertingType();
    }

    @DisplayName("Test Example 04")
    @Test
    void testEx4_flatMap() {
        new Chapter3().ex4_flatMap();
    }

    @DisplayName("Test Example 05")
    @Test
    void testEx5_flatMap() {
        new Chapter3().ex5_flatMapWithLambda();
    }

    @DisplayName("Test Example 06")
    @Test
    void testEx6_flatMap() {
//        ByteArrayInputStream in = new ByteArrayInputStream("9".getBytes());
//        System.setIn(in);

        new Chapter3().ex6_gugudan_traditional();

//        System.setIn(System.in);
    }

    @DisplayName("Test Example 07")
    @Test
    void testEx7_flatMap() {
        new Chapter3().ex7_gugudan_observable();
    }

    @DisplayName("Test Example 08")
    @Test
    void testEx8_flatMap() {
        new Chapter3().ex8_gugudan_withFlatMap();
    }

    @DisplayName("Test Example 09")
    @Test
    void testEx9_flatMap() {
        new Chapter3().ex9_gugudan_withFlatMapAdvanced();
    }

    @DisplayName("Test Example 10")
    @Test
    void testEx10_flatMap() {
        new Chapter3().ex10_gugudan_withFlatMapAndBiFunction();
    }

    @DisplayName("Test Example 11")
    @Test
    void testEx11() {
        new Chapter3().ex11_filter();
    }

    @DisplayName("Test Example 12")
    @Test
    void testEx12() {
        new Chapter3().ex12_filter();
    }

    @DisplayName("Test Example 13")
    @Test
    void testEx13() {
        new Chapter3().ex13_filterFriends();
    }

    @DisplayName("Test Example 14")
    @Test
    void testEx14() {
        new Chapter3().ex14_reduce();
    }

    @DisplayName("Test Example 15")
    @Test
    void testEx15() {
        new Chapter3().ex15_reduceWithoutLambda();
    }

    @DisplayName("Test Example 16")
    @Test
    void testEx16() {
        new Chapter3().ex16_query();
    }
}
