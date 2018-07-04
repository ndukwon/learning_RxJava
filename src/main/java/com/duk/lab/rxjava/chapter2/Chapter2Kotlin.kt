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
import io.reactivex.subjects.AsyncSubject
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject
import org.reactivestreams.Publisher
import org.reactivestreams.Subscriber
import java.util.*
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

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

    fun ex23_AsyncSubject() {
        val subject: AsyncSubject<String> = AsyncSubject.create()
        subject.subscribe { data -> println("Subscriber #1 => $data") }
        subject.onNext("1")
        subject.onNext("3")
        subject.subscribe { data -> println("Subscriber #2 => $data") }
        subject.onNext("5")
        subject.onComplete()

        /*
        Subscriber #1 => 5
        Subscriber #2 => 5
         */
    }

    fun ex24_AsyncSubject_subscriber() {
        // Subscriber
        val subject = AsyncSubject.create<Float>()
        subject.subscribe { data -> println("Subscriber #1 => " + data!!) }

        // Observable
//        val temperature = arrayOf(10.1f, 13.4f, 12.5f)
        val temperature = ArrayList<Float>()
        temperature.add(10.1f)
        temperature.add(13.4f)
        temperature.add(12.5f)
        Observable.fromIterable(temperature)
                .subscribe(subject)

        /*
        Subscriber #1 => 12.5
         */
    }

    fun ex25_AsyncSubject_afterComplete() {
        val subject = AsyncSubject.create<Int>()
        subject.onNext(10)
        subject.onNext(11)
        subject.subscribe { data -> println("Subscriber #1 => " + data!!) }
        subject.onNext(12)
        subject.onComplete()
        subject.onNext(13)
        subject.subscribe { data -> println("Subscriber #2 => " + data!!) }
        subject.subscribe { data -> println("Subscriber #3 => " + data!!) }

        /*
        Subscriber #1 => 12
        Subscriber #2 => 12
        Subscriber #3 => 12
         */
    }

    fun ex26_BehaviorSubject() {
        val behavior = BehaviorSubject.createDefault("6")
        behavior.subscribe { data -> println("Subscriber #1 => $data") }
        behavior.onNext("1")
        behavior.onNext("3")
        behavior.subscribe { data -> println("Subscriber #2 => $data") }
        behavior.onNext("5")
        behavior.onComplete()

        /*
        Subscriber #1 => 6
        Subscriber #1 => 1
        Subscriber #1 => 3
        Subscriber #2 => 3
        Subscriber #1 => 5
        Subscriber #2 => 5
         */
    }

    fun ex27_PublishSubject() {
        val publish = PublishSubject.create<String>()
        publish.subscribe { data -> println("Subscriber #1 => $data") }
        publish.onNext("1")
        publish.onNext("3")
        publish.subscribe { data -> println("Subscriber #2 => $data") }
        publish.onNext("5")
        publish.onComplete()

        /*
        Subscriber #1 => 1
        Subscriber #1 => 3
        Subscriber #1 => 5
        Subscriber #2 => 5
         */
    }

    fun ex28_ReplaySubject() {
        val replay = ReplaySubject.create<String>()
        replay.subscribe { data -> println("Subscriber #1 => $data") }
        replay.onNext("1")
        replay.onNext("3")
        replay.subscribe { data -> println("Subscriber #2 => $data") }
        replay.onNext("5")
        replay.onComplete()

        /*
        Subscriber #1 => 1
        Subscriber #1 => 3
        Subscriber #2 => 1
        Subscriber #2 => 3
        Subscriber #1 => 5
        Subscriber #2 => 5
         */
    }

    fun ex29_ConnectableObservable() {
        val dt = ArrayList<String>()
        dt.add("1")
        dt.add("3")
        dt.add("5")

        val balls = Observable.interval(100L, TimeUnit.MILLISECONDS) // interval(100개, 밀리세컨즈 단위)
                .map(Long::toInt)
                .map { i -> dt.get(i) }
                .take(dt.size.toLong())

        val source = balls.publish()
        source.subscribe { data -> println("Subscriber #1 => $data") }
        source.subscribe { data -> println("Subscriber #2 => $data") }
        source.connect()

        try {
            Thread.sleep(250)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        source.subscribe { data -> println("Subscriber #3 => $data") }

        try {
            Thread.sleep(100)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        /*
        Subscriber #1 => 1
        Subscriber #2 => 1
        Subscriber #1 => 3
        Subscriber #2 => 3
        Subscriber #1 => 5
        Subscriber #2 => 5
        Subscriber #3 => 5
         */
    }
}