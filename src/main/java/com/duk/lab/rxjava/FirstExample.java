package com.duk.lab.rxjava;/*
    com.duk.lab.rxjava.FirstExampleKotlin.java
    learning_RxJava

    Created by Dukwon Nam on 2018. 6. 29..
    Copyright © 2018년 Dukwon Nam. All rights reserved.
*/

import io.reactivex.Observable;

public class FirstExample {
    public void emit() {
        Observable.just("Hello", "RxJava 2!!")
                .subscribe(System.out::println);
    }
}
