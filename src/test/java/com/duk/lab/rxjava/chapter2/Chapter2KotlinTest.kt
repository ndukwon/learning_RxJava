package com.duk.lab.rxjava.chapter2

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail

class Chapter2KotlinTest {

    @DisplayName("Test Example 1 with Kotlin")
    @Test
    internal fun testEx1Java() {
        Chapter2Kotlin().ex1_just()
    }

    @DisplayName("Test Example 2 with Kotlin")
    @Test
    internal fun testEx2Java() {
        Chapter2Kotlin().ex2_isDisposed()
    }

    @DisplayName("Test Example 3 with Kotlin")
    @Test
    internal fun testEx3Java() {
        Chapter2Kotlin().ex3_create()
    }

    @DisplayName("Test Example 4 with Kotlin")
    @Test
    internal fun testEx4Java() {
        Chapter2Kotlin().ex4_createWithoutSubscribing()
    }

    @DisplayName("Test Example 5 with Kotlin")
    @Test
    internal fun testEx5Java() {
        Chapter2Kotlin().ex5_createWithLambda()
    }

    @DisplayName("Test Example 6 with Kotlin")
    @Test
    internal fun testEx6Java() {
        Chapter2Kotlin().ex6_createWithAnonymous()
    }

    @DisplayName("Test Example 7 with Kotlin")
    @Test
    internal fun testEx7Java() {
        Chapter2Kotlin().ex7_fromArray()
        fail("Example 7 is not fixed!")
    }

    @DisplayName("Test Example 8 with Kotlin")
    @Test
    internal fun testEx8Java() {
        Chapter2Kotlin().ex8_fromArrayWithPrimitiveType()
        fail("Example 8 is not fixed!")
    }

    @DisplayName("Test Example 9 with Kotlin")
    @Test
    internal fun testEx9Java() {
        fail("Example 9 is not implemented!")
    }

    @DisplayName("Test Example 10 with Kotlin")
    @Test
    internal fun testEx10Java() {
        Chapter2Kotlin().ex10_fromIterableWithArrayList()
    }

    @DisplayName("Test Example 11 with Kotlin")
    @Test
    internal fun testEx11Java() {
        Chapter2Kotlin().ex11_fromIterableWithHashSet()
    }

    @DisplayName("Test Example 12 with Kotlin")
    @Test
    internal fun testEx12Java() {
        Chapter2Kotlin().ex12_fromIterableWithBlockingQueue()
    }

    // NOT Test ex13

    @DisplayName("Test Example 14 with Kotlin")
    @Test
    internal fun testEx14Java() {
        Chapter2Kotlin().ex14_fromCallable()
    }

    @DisplayName("Test Example 15 with Kotlin")
    @Test
    internal fun testEx15Java() {
        Chapter2Kotlin().ex15_fromCallableWithoutLambda()
    }

    @DisplayName("Test Example 16 with Kotlin")
    @Test
    internal fun testEx16Java() {
        Chapter2Kotlin().ex16_fromFuture()
    }

    @DisplayName("Test Example 17 with Kotlin")
    @Test
    internal fun testEx17Java() {
        Chapter2Kotlin().ex17_fromPublisher()
    }

    @DisplayName("Test Example 18 with Kotlin")
    @Test
    internal fun testEx18Java() {
        Chapter2Kotlin().ex18_fromPublisherWithoutLambda()
    }

    @DisplayName("Test Example 19 with Kotlin")
    @Test
    internal fun testEx19Java() {
        Chapter2Kotlin().ex19_Single_just()
    }

    @DisplayName("Test Example 20 with Kotlin")
    @Test
    internal fun testEx20Java() {
        Chapter2Kotlin().ex20_Single_fromObservableCases()
    }

    // NOT Test ex21

    @DisplayName("Test Example 22 with Kotlin")
    @Test
    internal fun testEx22Java() {
        Chapter2Kotlin().ex22_Single_multipleData()
        // Exception is True
    }
}