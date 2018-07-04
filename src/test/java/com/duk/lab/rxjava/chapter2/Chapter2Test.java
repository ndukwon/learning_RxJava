package com.duk.lab.rxjava.chapter2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Chapter2Test {

    @DisplayName("Test Example 1 with Java")
    @Test
    void testEx1() {
        new Chapter2().ex1_just();
    }


    @DisplayName("Test Example 2 with Java")
    @Test
    void testEx2() {
        new Chapter2().ex2_isDisposed();
    }

    @DisplayName("Test Example 3 with Java")
    @Test
    void testEx3() {
        new Chapter2().ex3_create();
    }

    @DisplayName("Test Example 4 with Java")
    @Test
    void testEx4() {
        new Chapter2().ex4_createWithoutSubscribing();
    }

    @DisplayName("Test Example 5 with Java")
    @Test
    void testEx5() {
        new Chapter2().ex5_createWithLambda();
    }

    @DisplayName("Test Example 6 with Java")
    @Test
    void testEx6() {
        new Chapter2().ex6_createWithAnonymous();
    }

    @DisplayName("Test Example 7 with Java")
    @Test
    void testEx7() {
        new Chapter2().ex7_fromArray();
    }

    @DisplayName("Test Example 8 with Java")
    @Test
    void testEx8() {
        new Chapter2().ex8_fromArrayWithPrimitiveType();
    }

    @DisplayName("Test Example 9 with Java")
    @Test
    void testEx9() {
        new Chapter2().ex9_fromArrayPrimitiveToReference();
    }

    @DisplayName("Test Example 10 with Java")
    @Test
    void testEx10() {
        new Chapter2().ex10_fromIterableWithArrayList();
    }

    @DisplayName("Test Example 11 with Java")
    @Test
    void testEx11() {
        new Chapter2().ex11_fromIterableWithHashSet();
    }

    @DisplayName("Test Example 12 with Java")
    @Test
    void testEx12() {
        new Chapter2().ex12_fromIterableWithBlockingQueue();
    }

    // NOT Test ex13

    @DisplayName("Test Example 14 with Java")
    @Test
    void testEx14() {
        new Chapter2().ex14_fromCallable();
    }

    @DisplayName("Test Example 15 with Java")
    @Test
    void testEx15() {
        new Chapter2().ex15_fromCallableWithoutLambda();
    }

    @DisplayName("Test Example 16 with Java")
    @Test
    void testEx16() {
        new Chapter2().ex16_fromFuture();
    }

    @DisplayName("Test Example 17 with Java")
    @Test
    void testEx17() {
        new Chapter2().ex17_fromPublisher();
    }

    @DisplayName("Test Example 18 with Java")
    @Test
    void testEx18() {
        new Chapter2().ex18_fromPublisherWithoutLambda();
    }

    @DisplayName("Test Example 19 with Java")
    @Test
    void testEx19() {
        new Chapter2().ex19_Single_just();
    }

    @DisplayName("Test Example 20 with Java")
    @Test
    void testEx20() {
        new Chapter2().ex20_Single_fromObservableCases();
    }

    // NOT Test ex21

    @DisplayName("Test Example 22 with Java")
    @Test
    void testEx22() {
        new Chapter2().ex22_Single_multipleData();
        // Exception is True
    }

    @DisplayName("Test Example 23 with Java")
    @Test
    void testEx23() {
        new Chapter2().ex23_AsyncSubject();
    }

    @DisplayName("Test Example 24 with Java")
    @Test
    void testEx24() {
        new Chapter2().ex24_AsyncSubject_subscriber();
    }

    @DisplayName("Test Example 25 with Java")
    @Test
    void testEx25() {
        new Chapter2().ex25_AsyncSubject_afterComplete();
    }

    @DisplayName("Test Example 26 with Java")
    @Test
    void testEx26() {
        new Chapter2().ex26_BehaviorSubject();
    }

    @DisplayName("Test Example 27 with Java")
    @Test
    void testEx27() {
        new Chapter2().ex27_PublishSubject();
    }

    @DisplayName("Test Example 28 with Java")
    @Test
    void testEx28() {
        new Chapter2().ex28_ReplaySubject();
    }

    @DisplayName("Test Example 29 with Java")
    @Test
    void testEx29() {
        new Chapter2().ex29_ConnectableObservable();
    }
}
