/*
    com.duk.lab.rxjava.chapter2.Chapter2Kotlin.java
    learning_RxJava

    Created by Dukwon Nam on 2018. 7. 2..
    Copyright © 2018년 Dukwon Nam. All rights reserved.
*/

package com.duk.lab.rxjava.chapter2

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import org.reactivestreams.Publisher
import org.reactivestreams.Subscriber
import java.util.*
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.Callable
import java.util.concurrent.Executors

class Chapter2Kotlin {
    fun ex1_just() {
        // just()
        Observable.just(1, 2, 3, 4, 5, 6)
                .subscribe(System.out::println)
    }

    fun ex2_isDisposed() {
        val source = Observable.just("RED", "GREEN", "YELLOW")

        // Kotlin SAM conversion
        val d: Disposable = source.subscribe(
                {next -> println("onNext(): " + next)},
                {error -> println("onError(): " + error.message)},
                {println("onComplete()")}
        )

        println("isDisposed():" + d.isDisposed)
    }

    fun ex3_create() {
        val source: Observable<Integer> = Observable.create {
            emitter: ObservableEmitter<Integer> -> run {
                emitter.onNext(Integer(100))
                emitter.onNext(Integer(200))
                emitter.onNext(Integer(300))
                emitter.onComplete()
            }
        }

        source.subscribe{data -> println(data)}
    }

    fun ex4_createWithoutSubscribing() {
        val source = Observable.create { emitter: ObservableEmitter<Int> ->
            emitter.onNext(100)
            emitter.onNext(200)
            emitter.onNext(300)
            emitter.onComplete()
        }
    }

    fun ex5_createWithLambda() {
        ex3_create()    // Same as ex3: Kotlin seems not to have "Method reference"
    }

    fun ex6_createWithAnonymous() {
        val source = Observable.create { emitter: ObservableEmitter<Int> ->
            emitter.onNext(100)
            emitter.onNext(200)
            emitter.onNext(300)
            emitter.onComplete()
        }

        source.subscribe( object: Consumer<Int> {
            override fun accept(data: Int) {
                println(data)
            }
        })
    }

    // FIXME: Kotlin Array to Integer[]
    fun ex7_fromArray() {
        val arr = intArrayOf(100, 200, 300)
        Observable.fromArray(arr)
                .subscribe { println(it) }
    }

    // FIXME: Kotlin Array to int[]
    fun ex8_fromArrayWithPrimitiveType() {
        val arr = intArrayOf(100, 200, 300)
        Observable.fromArray<IntArray>(arr)
                .subscribe { println(it) }
    }

    fun ex10_fromIterableWithArrayList() {
        val names = ArrayList<String>()
        names.add("Jerry")
        names.add("William")
        names.add("Bob")

        Observable.fromIterable(names)
                .subscribe { println(it) }
    }

    fun ex11_fromIterableWithHashSet() {
        val cities = HashSet<String>()
        cities.add("Seoul")
        cities.add("London")
        cities.add("Paris")

        Observable.fromIterable(cities)
                .subscribe { println(it) }
    }

    fun ex12_fromIterableWithBlockingQueue() {
        val orderQueue = ArrayBlockingQueue<Order>(100)
        orderQueue.add(Order("ORD-1"))
        orderQueue.add(Order("ORD-2"))
        orderQueue.add(Order("ORD-3"))

        Observable.fromIterable(orderQueue)
                .subscribe { println(it) }
    }

    fun ex14_fromCallable() {
        val callable = {
            Thread.sleep(1000)
            "Hello Callable!"
        }

        Observable.fromCallable(callable)
                .subscribe { println(it) }
    }

    fun ex15_fromCallableWithoutLambda() {
        val callable = object : Callable<String> {
            override fun call(): String {
                Thread.sleep(1000)
                return "Hello Callable!"
            }
        }

        Observable.fromCallable(callable)
                .subscribe { println(it) }
    }

    fun ex16_fromFuture() {
        val future = Executors.newSingleThreadExecutor()
                .submit<String> {
                    Thread.sleep(1000)
                    "Hello Future"
                }

        Observable.fromFuture(future)
                .subscribe { println(it) }
    }

    fun ex17_fromPublisher() {
        val publisher = Publisher<String> { subscriber ->
            subscriber.onNext("Hello Observable.fromPublisher()")
            subscriber.onComplete()
        }

        Observable.fromPublisher(publisher)
                .subscribe { println(it) }
    }

    fun ex18_fromPublisherWithoutLambda() {
        val publisher = object: Publisher<String> {
            override fun subscribe(subscriber: Subscriber<in String>) {
                subscriber.onNext("Hello Observable.fromPublisher()")
                subscriber.onComplete()
            }
        }

        Observable.fromPublisher(publisher)
                .subscribe { println(it) }
    }

    fun ex19_Single_just() {
        Single.just("Hello Single!")
                .subscribe(Consumer { println(it) })
    }

    fun ex20_Single_fromObservableCases() {
        val defaultItem = "default item"

        // 1. Single.fromObservable()
        val observable = Observable.just("1. fromObservable")
        Single.fromObservable(observable)
                .subscribe(Consumer { println(it) })

        // 2. observable.single()
        Observable.just("2. observable.single()")
                .single(defaultItem)
                .subscribe(Consumer { println(it) })

        // 3. observable.first()
        val colors = arrayOf("RED", "BLUE", "GOLD")
        Observable.fromArray(*colors)
                .first(defaultItem)
                .subscribe(Consumer { println(it) })

        // 4. Observable.empty() 상태에서 single()
        Observable.empty<Any>()
                .single(defaultItem)
                .subscribe(Consumer { println(it) })

        // 5. observable.take()
        Observable.just(Order("ORD-1"), Order("ORD-2"))
                //                .take(2)      // Not work
                .take(1)
                .single(Order(defaultItem))
                .subscribe(Consumer { println(it) })
    }

    fun ex22_Single_multipleData() {
        Observable.just("A", "B")
                .single("C")
                .subscribe(Consumer { println(it) })
    }
}