package com.duk.lab.rxjava.chapter3;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import kotlin.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Chapter3 {

    // map()
    public void ex1_map() {
        String[] balls = {"1", "2", "3", "5"};
        Observable.fromArray(balls)
                .map(ball -> ball + "<>")
                .subscribe(Log::println);

        /*
        main | value = 1<>
        main | value = 2<>
        main | value = 3<>
        main | value = 5<>
         */
    }

    public void ex2_mapWithFunction() {
        Function<String, String> getDiamond = ball -> ball + "<>";

        String[] balls = {"1", "2", "3", "5"};
        Observable.fromArray(balls)
                .map(getDiamond)
                .subscribe(Log::println);
    }

    public void ex3_mapWithConvertingType() {
        Function<String, Integer> ballToIndex = ball -> {
            switch (ball) {
                case "RED":
                    return 1;

                case "YELLOW":
                    return 2;

                case "GREEN":
                    return 3;

                case "BLUE":
                    return 4;

                default:
                    return -1;
            }
        };

        String[] balls = {"RED", "YELLOW", "GREEN", "BLUE"};
        Observable.fromArray(balls)
                .map(ballToIndex)
                .subscribe(Log::println);
    }

    // flatMap()
    public void ex4_flatMap() {
        Function<String, Observable<String>> getDoubleDiamonds =
                ball -> Observable.just(ball + "<>", ball + "<>");

        String[] balls = {"1", "3", "5"};
        Observable.fromArray(balls)
                .flatMap(getDoubleDiamonds)
                .subscribe(Log::println);
    }

    public void ex5_flatMapWithLambda() {
        String[] balls = {"1", "3", "5"};
        Observable.fromArray(balls)
                .flatMap(ball -> Observable.just(ball + "<>", ball + "<>"))
                .subscribe(Log::println);
    }

    // 구구단
    public void ex6_gugudan_traditional() {
//        Scanner in = new Scanner(System.in);
//        System.out.println("Gugudan Input:");
//        int column = Integer.parseInt(in.nextLine());

        for (int column = 1; column <= 16; column++) {
            for (int row = 1; row <= 16; row++) {
                System.out.println(column + " * " + row + " = " + column * row);
            }
        }
    }

    public void ex7_gugudan_observable() {
        Observable.range(1, 16).subscribe(column -> {
            Observable.range(1, 16).subscribe(row -> {
                System.out.println(column + " * " + row + " = " + column * row);
            });
        });
    }

    public void ex8_gugudan_withFlatMap() {
        Function<Integer, Observable<String>> gugudan = column ->
                Observable.range(1, 16)
                    .map(row -> column + " * " + row + " = " + column * row);

        Observable.range(1, 16)
                .flatMap(gugudan)
                .subscribe(System.out::println);
    }

    public void ex9_gugudan_withFlatMapAdvanced() {
        Observable.range(1, 16)
                .flatMap(column ->
                    Observable.range(1, 16)
                            .map(row -> column + " * " + row + " = " + column * row)
                )
                .subscribe(System.out::println);
    }

    public void ex10_gugudan_withFlatMapAndBiFunction() {
        Observable.range(1, 16)
                .flatMap(
                        column -> Observable.range(1, 16),
                        (column, row) -> column + " * " + row + " = " + column * row)
                .subscribe(System.out::println);
    }

    // filter()
    public void ex11_filter() {
        String[] objs = {
                "1 CIRCLE",
                "2 DIAMOND",
                "3 TRIANGLE",
                "4 DIAMOND",
                "5 CIRCLE",
                "6 HEXAGON"
        };

        Observable.fromArray(objs)
                .filter(obj -> obj.endsWith("CIRCLE"))
                .subscribe(Log::println);

        /*
        main | value = 1 CIRCLE
        main | value = 5 CIRCLE
         */
    }

    public void ex12_filter() {
        Integer[] data = {100, 34, 27, 99, 50};
        Observable.fromArray(data)
                .filter(number -> number % 2 == 0)
                .subscribe(Log::println);

        /*
        main | value = 100
        main | value = 34
        main | value = 50
         */
    }

    public void ex13_filterFriends() {
        Integer[] numbers = {100, 200, 300, 400, 500};

        // first(): Single
        Observable.fromArray(numbers)
                .first(-1)      // Converted to Single
                .subscribe(data -> Log.println("first() = " + data));

        /*
        main | value = first() = 100
         */


        // last(): Single
        Observable.fromArray(numbers)
                .last(999)
                .subscribe(data -> Log.println("last() = " + data));

        /*
        main | value = last() = 500
         */


        // take(N)
        Observable.fromArray(numbers)
                .take(3)
                .subscribe(data -> Log.println("take(3) = " + data));

        /*
        main | value = take(3) = 100
        main | value = take(3) = 200
        main | value = take(3) = 300
         */


        Observable.fromArray(numbers)
                .take(9)
                .subscribe(data -> Log.println("take(9) = " + data));

        /*
        main | value = take(9) = 100
        main | value = take(9) = 200
        main | value = take(9) = 300
        main | value = take(9) = 400
        main | value = take(9) = 500
         */


        // takeLast(N)
        Observable.fromArray(numbers)
                .takeLast(3)
                .subscribe(data -> Log.println("takeLast(3) = " + data));
        /*
        main | value = takeLast(3) = 300
        main | value = takeLast(3) = 400
        main | value = takeLast(3) = 500
         */


        Observable.fromArray(numbers)
                .takeLast(9)
                .subscribe(data -> Log.println("takeLast(9) = " + data));

        /*
        main | value = takeLast(9) = 100
        main | value = takeLast(9) = 200
        main | value = takeLast(9) = 300
        main | value = takeLast(9) = 400
        main | value = takeLast(9) = 500
         */


        // skip(N)
        Observable.fromArray(numbers)
                .skip(2)
                .subscribe(data -> Log.println("skip(2) = " + data));

        /*
        main | value = skip(2) = 300
        main | value = skip(2) = 400
        main | value = skip(2) = 500
         */


        Observable.fromArray(numbers)
                .skip(9)
                .subscribe(data -> Log.println("skip(9) = " + data));
        /* Nothing to print
         */


        // skipLast(N)
        Observable.fromArray(numbers)
                .skipLast(2)
                .subscribe(data -> Log.println("skipLast(2) = " + data));

        /*
        main | value = skipLast(2) = 100
        main | value = skipLast(2) = 200
        main | value = skipLast(2) = 300
         */


        Observable.fromArray(numbers)
                .skipLast(9)
                .subscribe(data -> Log.println("skipLast(9) = " + data));

        /* Nothing to print
         */
    }

    public void ex14_reduce() {
        String[] balls = {"1", "3", "5"};
        Maybe<String> source = Observable.fromArray(balls)
                .reduce((prevResult, nextItem) -> nextItem + "(" + prevResult + ")");
        source.subscribe(Log::println);

        /*
        main | value = 5(3(1))
         */
    }

    public void ex15_reduceWithoutLambda() {
        BiFunction<String, String, String> mergeBalls =
                (prevResult, nextItem) -> nextItem + "(" + prevResult + ")";

        String[] balls = {"1", "3", "5"};
        Observable.fromArray(balls)
                .reduce(mergeBalls)
                .subscribe(Log::println);

        /*
        main | value = 5(3(1))
         */
    }

    public void ex16_query() {
        List<Pair<String, Integer>> sales = new ArrayList<>();
        sales.add(new Pair("TV", 2500));
        sales.add(new Pair("Camera", 3000));
        sales.add(new Pair("TV", 1600));
        sales.add(new Pair("Phone", 800));

        Observable.fromIterable(sales)
                .filter(sale -> "TV".equals(sale.getFirst()))
                .map(sale -> sale.getSecond())
                .reduce((sale1, sale2) -> sale1 + sale2)
                .subscribe(tot -> System.out.println("TV Sales: $" + tot));

        /*
        TV Sales: $4100
         */
    }
}
