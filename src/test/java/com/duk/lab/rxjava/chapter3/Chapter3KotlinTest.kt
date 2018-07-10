package com.duk.lab.rxjava.chapter3

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class Chapter3KotlinTest {

    @DisplayName("Test Example 01")
    @Test
    internal fun testEx1_map() {
        Chapter3Kotlin().ex1_map()
    }

    @DisplayName("Test Example 02")
    @Test
    internal fun testEx2_map() {
        Chapter3Kotlin().ex2_mapWithFunction()
    }

    @DisplayName("Test Example 03")
    @Test
    internal fun testEx3_map() {
        Chapter3Kotlin().ex3_mapWithConvertingType()
    }

    @DisplayName("Test Example 04")
    @Test
    internal fun testEx4_flatMap() {
        Chapter3Kotlin().ex4_flatMap()
    }

    @DisplayName("Test Example 05")
    @Test
    internal fun testEx5_flatMap() {
        Chapter3Kotlin().ex5_flatMapWithLambda()
    }

    @DisplayName("Test Example 06")
    @Test
    internal fun testEx6_flatMap() {
        Chapter3Kotlin().ex6_gugudan_traditional()
    }

    @DisplayName("Test Example 07")
    @Test
    internal fun testEx7_flatMap() {
        Chapter3Kotlin().ex7_gugudan_observable()
    }

    @DisplayName("Test Example 08")
    @Test
    internal fun testEx8_flatMap() {
        Chapter3Kotlin().ex8_gugudan_withFlatMap()
    }

    @DisplayName("Test Example 09")
    @Test
    internal fun testEx9_flatMap() {
        Chapter3Kotlin().ex9_gugudan_withFlatMapAdvanced()
    }

    @DisplayName("Test Example 10")
    @Test
    internal fun testEx10_flatMap() {
        Chapter3Kotlin().ex10_gugudan_withFlatMapAndBiFunction()
    }

    @DisplayName("Test Example 11")
    @Test
    internal fun testEx11() {
        Chapter3Kotlin().ex11_filter()
    }

    @DisplayName("Test Example 12")
    @Test
    internal fun testEx12() {
        Chapter3Kotlin().ex12_filter()
    }

    @DisplayName("Test Example 13")
    @Test
    internal fun testEx13() {
        Chapter3Kotlin().ex13_filterFriends()
    }

    @DisplayName("Test Example 14")
    @Test
    internal fun testEx14() {
        Chapter3Kotlin().ex14_reduce()
    }

    @DisplayName("Test Example 15")
    @Test
    internal fun testEx15() {
        Chapter3Kotlin().ex15_reduceWithoutLambda()
    }

    @DisplayName("Test Example 16")
    @Test
    internal fun testEx16() {
        Chapter3Kotlin().ex16_query()
    }
}