package com.duk.lab.rxjava.chapter1

import io.reactivex.Observable

/*
    com.duk.lab.rxjava.chapter1.FirstExampleKotlin.ktlin.kt
    learning_RxJava

    Created by Dukwon Nam on 2018. 6. 29..
    Copyright © 2018년 Dukwon Nam. All rights reserved.
*/

class FirstExampleKotlin {
    fun emit() {
        Observable.just("Hello", "RxJava 2 with Kotlin!!").
                subscribe(System.out::println)
    }
}