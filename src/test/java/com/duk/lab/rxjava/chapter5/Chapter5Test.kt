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

    @DisplayName("Test Schedulers.io() Examples")
    @Test
    internal fun test_io() {
        val chapter5 = Chapter5()

        println("Test Schedulers.io() Example 6")
        chapter5.ex6_ioScheduler()
    }

    @DisplayName("Test Schedulers.trampoline() Examples")
    @Test
    internal fun test_trampoline() {
        val chapter5 = Chapter5()

        println("Test Schedulers.trampoline() Example 7")
        chapter5.ex7_trampolineScheduler()

        println("Test Schedulers.trampoline() Example 7-2")
        chapter5.ex7_trampolineScheduler2()
    }

    @DisplayName("Test Schedulers.single() Examples")
    @Test
    internal fun test_single() {
        val chapter5 = Chapter5()

        println("Test Schedulers.single() Example 8")
        chapter5.ex8_singleScheduler()
    }

    @DisplayName("Test Schedulers.from(Executor) Examples")
    @Test
    internal fun test_from_Executor() {
        val chapter5 = Chapter5()

        println("Test Schedulers.from(Executor) Example 9")
        chapter5.ex9_fromExecutorScheduler()
    }
}