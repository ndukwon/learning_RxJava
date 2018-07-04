package com.duk.lab.rxjava.chapter2

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail

class Chapter2KotlinTest {

    @DisplayName("Test Example 1 with Kotlin")
    @Test
    internal fun testEx1() {
        Chapter2Kotlin().ex1_just()
    }

    @DisplayName("Test Example 2 with Kotlin")
    @Test
    internal fun testEx2() {
        Chapter2Kotlin().ex2_isDisposed()
    }

    @DisplayName("Test Example 3 with Kotlin")
    @Test
    internal fun testEx3() {
        Chapter2Kotlin().ex3_create()
    }

    @DisplayName("Test Example 4 with Kotlin")
    @Test
    internal fun testEx4() {
        Chapter2Kotlin().ex4_createWithoutSubscribing()
    }

    @DisplayName("Test Example 5 with Kotlin")
    @Test
    internal fun testEx5() {
        Chapter2Kotlin().ex5_createWithLambda()
    }

    @DisplayName("Test Example 6 with Kotlin")
    @Test
    internal fun testEx6() {
        Chapter2Kotlin().ex6_createWithAnonymous()
    }

    @DisplayName("Test Example 7 with Kotlin")
    @Test
    internal fun testEx7() {
        Chapter2Kotlin().ex7_fromArray()
        fail("Example 7 is not fixed!")
    }

    @DisplayName("Test Example 8 with Kotlin")
    @Test
    internal fun testEx8() {
        Chapter2Kotlin().ex8_fromArrayWithPrimitiveType()
        fail("Example 8 is not fixed!")
    }

    @DisplayName("Test Example 9 with Kotlin")
    @Test
    internal fun testEx9() {
        fail("Example 9 is not implemented!")
    }

    @DisplayName("Test Example 10 with Kotlin")
    @Test
    internal fun testEx10() {
        Chapter2Kotlin().ex10_fromIterableWithArrayList()
    }

    @DisplayName("Test Example 11 with Kotlin")
    @Test
    internal fun testEx11() {
        Chapter2Kotlin().ex11_fromIterableWithHashSet()
    }

    @DisplayName("Test Example 12 with Kotlin")
    @Test
    internal fun testEx12() {
        Chapter2Kotlin().ex12_fromIterableWithBlockingQueue()
    }

    // NOT Test ex13

    @DisplayName("Test Example 14 with Kotlin")
    @Test
    internal fun testEx14() {
        Chapter2Kotlin().ex14_fromCallable()
    }

    @DisplayName("Test Example 15 with Kotlin")
    @Test
    internal fun testEx15() {
        Chapter2Kotlin().ex15_fromCallableWithoutLambda()
    }

    @DisplayName("Test Example 16 with Kotlin")
    @Test
    internal fun testEx16() {
        Chapter2Kotlin().ex16_fromFuture()
    }

    @DisplayName("Test Example 17 with Kotlin")
    @Test
    internal fun testEx17() {
        Chapter2Kotlin().ex17_fromPublisher()
    }

    @DisplayName("Test Example 18 with Kotlin")
    @Test
    internal fun testEx18() {
        Chapter2Kotlin().ex18_fromPublisherWithoutLambda()
    }

    @DisplayName("Test Example 19 with Kotlin")
    @Test
    internal fun testEx19() {
        Chapter2Kotlin().ex19_Single_just()
    }

    @DisplayName("Test Example 20 with Kotlin")
    @Test
    internal fun testEx20() {
        Chapter2Kotlin().ex20_Single_fromObservableCases()
    }

    // NOT Test ex21

    @DisplayName("Test Example 22 with Kotlin")
    @Test
    internal fun testEx22() {
        Chapter2Kotlin().ex22_Single_multipleData()
        // Exception is True
    }

    @DisplayName("Test Example 23 with Kotlin")
    @Test
    internal fun testEx23() {
        Chapter2Kotlin().ex23_AsyncSubject()
    }

    @DisplayName("Test Example 24 with Kotlin")
    @Test
    internal fun testEx24() {
        Chapter2Kotlin().ex24_AsyncSubject_subscriber()
    }

    @DisplayName("Test Example 25 with Kotlin")
    @Test
    internal fun testEx25() {
        Chapter2Kotlin().ex25_AsyncSubject_afterComplete()
    }

    @DisplayName("Test Example 26 with Kotlin")
    @Test
    internal fun testEx26() {
        Chapter2Kotlin().ex26_BehaviorSubject()
    }

    @DisplayName("Test Example 27 with Kotlin")
    @Test
    internal fun testEx27() {
        Chapter2Kotlin().ex27_PublishSubject()
    }

    @DisplayName("Test Example 28 with Kotlin")
    @Test
    internal fun testEx28() {
        Chapter2Kotlin().ex28_ReplaySubject()
    }

    @DisplayName("Test Example 29 with Kotlin")
    @Test
    internal fun testEx29() {
        Chapter2Kotlin().ex29_ConnectableObservable()
    }
}