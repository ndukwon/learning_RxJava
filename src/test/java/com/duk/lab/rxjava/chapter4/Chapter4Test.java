package com.duk.lab.rxjava.chapter4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

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

    @DisplayName("Test zip() Examples")
    @Test
    void test_zip() {
        Chapter4 chapter4 = new Chapter4();

        System.out.println("Test zip() Example 22");
        chapter4.ex22_zip();

        System.out.println("Test zip() Example 24");
        chapter4.ex24_zipForSummation();

        System.out.println("Test zip() Example 25");
        chapter4.ex25_zipWithInterval();

        System.out.println("Test zip() Example 27");
        chapter4.ex27_zipForElectricBills();

        System.out.println("Test zip() Example 28");
        chapter4.ex28_zipWith();
    }

    @DisplayName("Test combineLatest() Examples")
    @Test
    void test_combineLatest() {
        Chapter4 chapter4 = new Chapter4();

        System.out.println("Test combineLatest() Example 29");
        chapter4.ex29_combineLatest();

        System.out.println("Test combineLatest() Example 30");
        StringBuilder inputSb = new StringBuilder();
        inputSb.append("a:100").append("\n")
                .append("b:2020").append("\n")
                .append("a:300").append("\n")
                .append("b:30").append("\n")
                .append("exit");
        ByteArrayInputStream in = new ByteArrayInputStream(inputSb.toString().getBytes());
        System.setIn(in);
        chapter4.ex30_ex29_combineLatestForMS_Excel();

        System.setIn(System.in);
    }

    @DisplayName("Test merge() Examples")
    @Test
    void test_merge() {
        Chapter4 chapter4 = new Chapter4();

        System.out.println("Test merge() Example 31");
        chapter4.ex31_merge();
    }

    @DisplayName("Test concat() Examples")
    @Test
    void test_concat() {
        Chapter4 chapter4 = new Chapter4();

        System.out.println("Test concat() Example 32");
        chapter4.ex32_concat();
    }

    @DisplayName("Test amb() Examples")
    @Test
    void test_amb() {
        Chapter4 chapter4 = new Chapter4();

        System.out.println("Test amb() Example 33");
        chapter4.ex33_amb();
    }

    @DisplayName("Test take/skipUntil() Examples")
    @Test
    void test_takeUntil_skipUntil() {
        Chapter4 chapter4 = new Chapter4();

        System.out.println("Test take/skipUntil() Example 34");
        chapter4.ex34_takeUntil();

        System.out.println("Test take/skipUntil() Example 35");
        chapter4.ex35_skipUntil();
    }

    @DisplayName("Test all() Examples")
    @Test
    void test_all() {
        Chapter4 chapter4 = new Chapter4();

        System.out.println("Test all() Example 36");
        chapter4.ex36_all();
    }
}
