package com.duk.lab.rxjava.chapter4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Chapter4Test {

    @DisplayName("Test interval() Examples")
    @Test
    void test_interval() {
        Chapter4 chapter4 = new Chapter4();

        System.out.println("Test interval() Example 01");
        chapter4.ex1_interval();

        // ex2, 3 are unnecessary.

        System.out.println("Test interval() Example 04");
        chapter4.ex4_intervalWithInitialDelay();
    }

    @DisplayName("Test timer() Examples")
    @Test
    void test_timer() {
        Chapter4 chapter4 = new Chapter4();

        System.out.println("Test timer() Example 05");
        chapter4.ex5_timer();
    }

    @DisplayName("Test range() Examples")
    @Test
    void test_range() {
        Chapter4 chapter4 = new Chapter4();

        System.out.println("Test range() Example 06");
        chapter4.ex6_range();
    }

    @DisplayName("Test intervalRange() Examples")
    @Test
    void test_intervalRange() {
        Chapter4 chapter4 = new Chapter4();

        System.out.println("Test intervalRange() Example 07");
        chapter4.ex7_intervalRange();

        System.out.println("Test intervalRange() Example 08");
        chapter4.ex8_without_intervalRange();
    }

    @DisplayName("Test defer() Examples")
    @Test
    void test_defer() {
        Chapter4 chapter4 = new Chapter4();

        System.out.println("Test defer() Example 09");
        chapter4.ex9_defer();

        System.out.println("Test defer() Example 10");
        chapter4.ex10_withoutDefer();
    }

    @DisplayName("Test repeat() Examples")
    @Test
    void test_repeat() {
        Chapter4 chapter4 = new Chapter4();

        System.out.println("Test repeat() Example 11");
        chapter4.ex11_repeat();

        System.out.println("Test repeat() Example 12");
        chapter4.ex12_heartbeat();
    }

    @DisplayName("Test concatMap() Examples")
    @Test
    void test_concatMap() {
        Chapter4 chapter4 = new Chapter4();

        System.out.println("Test concatMap() Example 14");
        chapter4.ex14_concatMap();

        System.out.println("Test concatMap() Example 15");
        chapter4.ex15_concatMapToFlatMap();
    }

    @DisplayName("Test switchMap() Examples")
    @Test
    void test_switchMap() {
        Chapter4 chapter4 = new Chapter4();

        System.out.println("Test switchMap() Example 16");
        chapter4.ex16_switchMap();

        System.out.println("Test switchMap() Example 17");
        chapter4.ex17_switchMap();
    }

    @DisplayName("Test groupBy() Examples")
    @Test
    void test_groupBy() {
        Chapter4 chapter4 = new Chapter4();

        System.out.println("Test groupBy() Example 18");
        chapter4.ex18_groupBy();

        System.out.println("Test groupBy() Example 20");
        chapter4.ex20_groupByWithFilter();

        System.out.println("Test groupBy() Example 20-2");
        chapter4.ex20_groupByWithFilter_2();

        System.out.println("Test groupBy() Example 20-3");
        chapter4.ex20_groupByWithFilter_3();
    }

    @DisplayName("Test scan() Examples")
    @Test
    void test_scan() {
        Chapter4 chapter4 = new Chapter4();

        System.out.println("Test scan() Example 21");
        chapter4.ex21_scan();
    }
}
