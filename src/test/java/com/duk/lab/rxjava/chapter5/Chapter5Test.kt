package com.duk.lab.rxjava.chapter5

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class Chapter5Test {

    @DisplayName("Test subscribeOn/observeOn Examples")
    @Test
    internal fun test_subscribeOn_observeOn() {
        val chapter5 = Chapter5()

        println("Test subscribeOn/observeOn, Example 2")
        chapter5.ex2_subscribeOn_observeOn()

        println("Test subscribeOn only, Example 3")
        chapter5.ex3_subscribeOnOnly()
    }

    @DisplayName("Test Schedulers.newThread() Examples")
    @Test
    internal fun test_newThread() {
        val chapter5 = Chapter5()

        println("Test Schedulers.newThread() Example 4")
        chapter5.ex4_newThreadScheduler()

        println("Test Schedulers.newThread() Example 4-2")
        chapter5.ex4_newThreadScheduler_2()
    }

    @DisplayName("Test Schedulers.computation() Examples")
    @Test
    internal fun test_computation() {
        val chapter5 = Chapter5()

        println("Test Schedulers.computation() Example 5")
        chapter5.ex5_computationScheduler()
    }
}