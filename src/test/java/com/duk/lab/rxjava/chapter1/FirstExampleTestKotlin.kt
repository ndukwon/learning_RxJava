package com.duk.lab.rxjava.chapter1

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class FirstExampleTestKotlin {
    @DisplayName("Test First Example with Java")
    @Test
    fun testJavaCode() {
        val demo = FirstExample()
        demo.emit()
    }

    @DisplayName("Test First Example with Java")
    @Test
    fun testKotlinCode() {
        val demo = FirstExampleKotlin()
        demo.emit()
    }
}