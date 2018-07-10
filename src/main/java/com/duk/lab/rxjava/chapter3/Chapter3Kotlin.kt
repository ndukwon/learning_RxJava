package com.duk.lab.rxjava.chapter3

import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import java.util.ArrayList

class Chapter3Kotlin {
    fun ex1_map() {
        val balls = arrayOf("1", "2", "3", "5")
        Observable.fromArray(*balls)
                .map { ball -> "$ball<>" }
                .subscribe { Log.println(it) }

        /*
        main | value = 1<>
        main | value = 2<>
        main | value = 3<>
        main | value = 5<>
         */
    }

    fun ex2_mapWithFunction() {
        val getDiamond = { ball:String -> "$ball<>" }

        val balls = arrayOf("1", "2", "3", "5")
        Observable.fromArray(*balls)
                .map(getDiamond)
                .subscribe{ Log.println(it) }
    }

    fun ex3_mapWithConvertingType() {
        val ballToIndex = { ball:String ->
            when (ball) {
                "RED" -> 1
                "YELLOW" -> 2
                "GREEN" -> 3
                "BLUE" -> 4
                else -> -1
            }
        }

        val balls = arrayOf("RED", "YELLOW", "GREEN", "BLUE")
        Observable.fromArray(*balls)
                .map(ballToIndex)
                .subscribe{ Log.println(it) }
    }

    // flatMap()
    fun ex4_flatMap() {
        val getDoubleDiamonds = { ball:String ->
            Observable.just("$ball<>", "$ball<>")
        }

        val balls = arrayOf("1", "3", "5")
        Observable.fromArray(*balls)
                .flatMap(getDoubleDiamonds)
                .subscribe{ Log.println(it) }
    }

    fun ex5_flatMapWithLambda() {
        val balls = arrayOf("1", "3", "5")
        Observable.fromArray(*balls)
                .flatMap { ball ->
                    Observable.just("$ball<>", "$ball<>")
                }
                .subscribe{ Log.println(it) }
    }

    // 구구단
    fun ex6_gugudan_traditional() {
        for (column in 1..16) {
            for (row in 1..16) {
                val result = column * row
                println("$column * $row = $result")
            }
        }
    }

    fun ex7_gugudan_observable() {
        Observable.range(1, 16).subscribe {
            column -> Observable.range(1, 16).subscribe {
                row -> println("$column * $row = " + (column * row))
            }
        }
    }

    fun ex8_gugudan_withFlatMap() {
        val gugudan = {
            column: Int -> Observable.range(1, 16)
                    .map {
                        row -> "$column * $row = " + (column * row)
                    }
        }

        Observable.range(1, 16)
                .flatMap(gugudan)
                .subscribe{ println(it) }
    }

    fun ex9_gugudan_withFlatMapAdvanced() {
        Observable.range(1, 16)
                .flatMap {
                    column -> Observable.range(1, 16)
                            .map {
                                row -> "$column * $row = " + (column * row)
                            }
                }
                .subscribe { println(it) }
    }

    fun ex10_gugudan_withFlatMapAndBiFunction() {
        Observable.range(1, 16)
                .flatMap(
                        { column -> Observable.range(1, 16) },
                        { column, row -> "$column * $row = " + (column * row) })
                .subscribe{ println(it) }
    }

    // filter()
    fun ex11_filter() {
        val objs = arrayOf(
                "1 CIRCLE",
                "2 DIAMOND",
                "3 TRIANGLE",
                "4 DIAMOND",
                "5 CIRCLE",
                "6 HEXAGON"
        )

        Observable.fromArray(*objs)
                .filter { obj -> obj.endsWith("CIRCLE") }
                .subscribe { Log.println(it) }
    }

    fun ex12_filter() {
        val data = arrayOf(100, 34, 27, 99, 50)
        Observable.fromArray(*data)
                .filter { number -> number % 2 == 0 }
                .subscribe { Log.println(it) }

        /*
        main | value = 100
        main | value = 34
        main | value = 50
         */
    }

    fun ex13_filterFriends() {
        val numbers = arrayOf(100, 200, 300, 400, 500)

        // first(): Single
        Observable.fromArray(*numbers)
                .first(-1)      // Converted to Single
                .subscribe { data -> Log.println("first() = $data") }

        /*
        main | value = first() = 100
         */


        // last(): Single
        Observable.fromArray(*numbers)
                .last(999)
                .subscribe { data -> Log.println("last() = $data") }

        /*
        main | value = last() = 500
         */


        // take(N)
        Observable.fromArray(*numbers)
                .take(3)
                .subscribe { data -> Log.println("take(3) = $data") }

        /*
        main | value = take(3) = 100
        main | value = take(3) = 200
        main | value = take(3) = 300
         */


        Observable.fromArray(*numbers)
                .take(9)
                .subscribe { data -> Log.println("take(9) = $data") }

        /*
        main | value = take(9) = 100
        main | value = take(9) = 200
        main | value = take(9) = 300
        main | value = take(9) = 400
        main | value = take(9) = 500
         */


        // takeLast(N)
        Observable.fromArray(*numbers)
                .takeLast(3)
                .subscribe { data -> Log.println("takeLast(3) = $data") }
        /*
        main | value = takeLast(3) = 300
        main | value = takeLast(3) = 400
        main | value = takeLast(3) = 500
         */


        Observable.fromArray(*numbers)
                .takeLast(9)
                .subscribe { data -> Log.println("takeLast(9) = $data") }

        /*
        main | value = takeLast(9) = 100
        main | value = takeLast(9) = 200
        main | value = takeLast(9) = 300
        main | value = takeLast(9) = 400
        main | value = takeLast(9) = 500
         */


        // skip(N)
        Observable.fromArray(*numbers)
                .skip(2)
                .subscribe { data -> Log.println("skip(2) = $data") }

        /*
        main | value = skip(2) = 300
        main | value = skip(2) = 400
        main | value = skip(2) = 500
         */


        Observable.fromArray(*numbers)
                .skip(9)
                .subscribe { data -> Log.println("skip(9) = $data") }
        /* Nothing to print
         */


        // skipLast(N)
        Observable.fromArray(*numbers)
                .skipLast(2)
                .subscribe { data -> Log.println("skipLast(2) = $data") }

        /*
        main | value = skipLast(2) = 100
        main | value = skipLast(2) = 200
        main | value = skipLast(2) = 300
         */


        Observable.fromArray(*numbers)
                .skipLast(9)
                .subscribe { data -> Log.println("skipLast(9) = $data") }

        /* Nothing to print
         */
    }

    fun ex14_reduce() {
        val balls = arrayOf("1", "3", "5")
        val source = Observable.fromArray(*balls)
                .reduce { prevResult, nextItem -> "$nextItem($prevResult)" }
        source.subscribe { Log.println(it) }

        /*
        main | value = 5(3(1))
         */
    }

    fun ex15_reduceWithoutLambda() {
        val mergeBalls = {
            prevResult: String, nextItem: String -> "$nextItem($prevResult)"
        }

        val balls = arrayOf("1", "3", "5")
        Observable.fromArray(*balls)
                .reduce(mergeBalls)
                .subscribe { Log.println(it) }

        /*
        main | value = 5(3(1))
         */
    }

    fun ex16_query() {
        val sales = ArrayList<Pair<String, Int>>()
        sales.add(Pair("TV", 2500))
        sales.add(Pair("Camera", 3000))
        sales.add(Pair("TV", 1600))
        sales.add(Pair("Phone", 800))

        Observable.fromIterable(sales)
                .filter { (first) -> "TV" == first }
                .map { (_, second) -> second }
                .reduce { sale1, sale2 -> sale1 + sale2 }
                .subscribe { tot -> println("TV Sales: $tot") }

        /*
        TV Sales: $4100
         */
    }
}