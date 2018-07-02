package com.duk.lab.rxjava.chapter1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class FirstExampleTest {

    @DisplayName("Test First Example with Java")
    @Test
    void testJavaCode() {
        FirstExample demo = new FirstExample();
        demo.emit();
    }

    @DisplayName("Test First Example with Kotlin")
    @Test
    void testKotlinCode() {
        FirstExampleKotlin demo = new FirstExampleKotlin();
        demo.emit();
    }
}
