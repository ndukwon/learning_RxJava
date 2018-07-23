package com.duk.lab.rxjava.chapter4;

import com.duk.lab.rxjava.utils.Log;
import com.duk.lab.rxjava.utils.OkHttpHelper;
import com.duk.lab.rxjava.utils.Shape;
import com.duk.lab.rxjava.utils.TimeUtil;
import io.reactivex.Observable;
import kotlin.Pair;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class Chapter4 {

    /*
        interval()
     */
    public void ex1_interval() {
        TimeUtil.setStartTime();
        Observable.interval(100L, TimeUnit.MILLISECONDS)
                .take(5)
                .subscribe(Log::printlnWithTime);

        TimeUtil.sleep(1000);

        /*
        RxComputationThreadPool-1 | 258 | value = 0
        RxComputationThreadPool-1 | 358 | value = 1
        RxComputationThreadPool-1 | 455 | value = 2
        RxComputationThreadPool-1 | 558 | value = 3
        RxComputationThreadPool-1 | 657 | value = 4
         */
    }

    // ex2, 3 are unnecessary.

    public void ex4_intervalWithInitialDelay() {
        TimeUtil.setStartTime();
        Observable.interval(0L, 100L, TimeUnit.MILLISECONDS)
                .take(5)
                .subscribe(Log::printlnWithTime);

        TimeUtil.sleep(1000);

        /*
        RxComputationThreadPool-2 | 1 | value = 0
        RxComputationThreadPool-2 | 102 | value = 1
        RxComputationThreadPool-2 | 206 | value = 2
        RxComputationThreadPool-2 | 305 | value = 3
        RxComputationThreadPool-2 | 401 | value = 4
         */
    }

    /*
        timer()
     */
    public void ex5_timer() {
        TimeUtil.setStartTime();
        Observable.timer(500L, TimeUnit.MILLISECONDS)
                .map(notUsed -> new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()))
                .subscribe(Log::printlnWithTime);

        TimeUtil.sleep(1000);

        /*
        RxComputationThreadPool-1 | 707 | value = 2018/07/16 14:33:44
         */
    }

    /*
        range()
     */
    public void ex6_range() {
        Observable.range(3, 5)
                .filter(number -> number % 2 == 0)
                .subscribe(Log::println);

        /*
        main | value = 4
        main | value = 6
         */
    }

    /*
        intervalRange()
     */
    public void ex7_intervalRange() {
        TimeUtil.setStartTime();
        Observable.intervalRange(
                1,
                5,
                150L,
                100L,
                TimeUnit.MILLISECONDS)
                .subscribe(Log::println);

        TimeUtil.sleep(1000);

        /*
        RxComputationThreadPool-1 | value = 1
        RxComputationThreadPool-1 | value = 2
        RxComputationThreadPool-1 | value = 3
        RxComputationThreadPool-1 | value = 4
        RxComputationThreadPool-1 | value = 5
         */
    }

    public void ex8_without_intervalRange() {
        TimeUtil.setStartTime();
        Observable.interval(150L, 100L, TimeUnit.MILLISECONDS)
                .map(value -> value + 1)
                .take(5)
                .subscribe(Log::println);

        TimeUtil.sleep(1000);

        /*
        RxComputationThreadPool-2 | value = 1
        RxComputationThreadPool-2 | value = 2
        RxComputationThreadPool-2 | value = 3
        RxComputationThreadPool-2 | value = 4
        RxComputationThreadPool-2 | value = 5
         */
    }

    /*
        defer()
     */
    public void ex9_defer() {
        Iterator<String> numbers = Arrays.asList("1", "3", "5", "6").iterator();

        Observable source = Observable.defer(() -> getNumberedShapeObservable(numbers));

        source.subscribe(value -> Log.println("Subscriber #1: " + value));
        source.subscribe(value -> Log.println("Subscriber #2: " + value));

        /*
        main | value = Subscriber #1: (1, BALL)
        main | value = Subscriber #1: (1, RECTANGLE)
        main | value = Subscriber #1: (1, PENTAGON)
        main | value = Subscriber #2: (3, BALL)
        main | value = Subscriber #2: (3, RECTANGLE)
        main | value = Subscriber #2: (3, PENTAGON)
         */
    }

    private Observable getNumberedShapeObservable(Iterator<String> numbers) {
        if (numbers.hasNext()) {
            String number = numbers.next();
            return Observable.just(
                    new Pair(number, Shape.BALL),
                    new Pair(number, Shape.RECTANGLE),
                    new Pair(number, Shape.PENTAGON));
        }

        return Observable.empty();
    }

    public void ex10_withoutDefer() {
        Iterator<String> numbers = Arrays.asList("1", "3", "5", "6").iterator();

        Observable source = getNumberedShapeObservable(numbers);

        source.subscribe(value -> Log.println("Subscriber #1: " + value));
        source.subscribe(value -> Log.println("Subscriber #2: " + value));

        /*
        main | value = Subscriber #1: (1, BALL)
        main | value = Subscriber #1: (1, RECTANGLE)
        main | value = Subscriber #1: (1, PENTAGON)
        main | value = Subscriber #2: (1, BALL)
        main | value = Subscriber #2: (1, RECTANGLE)
        main | value = Subscriber #2: (1, PENTAGON)
         */
    }

    /*
        repeat()
     */
    public void ex11_repeat() {
        String[] balls = {"1", "3", "5"};
        Observable.fromArray(balls)
                .repeat(3)
                .doOnComplete(() -> Log.println("onComplete()"))
                .subscribe(Log::println);

        /*
        main | value = 1
        main | value = 3
        main | value = 5
        main | value = 1
        main | value = 3
        main | value = 5
        main | value = 1
        main | value = 3
        main | value = 5
        main | value = onComplete()
         */
    }

    public void ex12_heartbeat() {
        TimeUtil.setStartTime();
        String serverUrl = "https://api.github.com/zen";    // 계속 다른 문장을 주는 API

        Observable.timer(2, TimeUnit.SECONDS)
                .map(notUsed -> serverUrl)
                .map(OkHttpHelper::run)
                .repeat()
                .subscribe(res -> Log.printlnWithTime("Ping Result:" + res));

        TimeUtil.sleep(10000);

        /*
        RxComputationThreadPool-1 | 4484 | value = Ping Result:Non-blocking is better than blocking.
        RxComputationThreadPool-2 | 6759 | value = Ping Result:Favor focus over features.
        RxComputationThreadPool-3 | 9033 | value = Ping Result:Design for failure.
         */
    }

    // ex13 are unnecessary.

    /*
        concatMap()
     */
    public void ex14_concatMap() {
        TimeUtil.setStartTime();

        String[] balls = {"1", "3", "5"};
        Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map(Long::intValue)
                .map(idx -> balls[idx])
                .take(balls.length)
                .doOnNext(item -> Log.debugWithTime("doOnNext():" + item))
                .concatMap(ball -> Observable.interval(200L, TimeUnit.MILLISECONDS)
                        .map(notUsed -> ball + "<>")
                        .take(2))
                .subscribe(Log::printlnWithTime);

        TimeUtil.sleep(2000);

        /*
        RxComputationThreadPool-1 | 406 | debug : doOnNext():1
        RxComputationThreadPool-1 | 505 | debug : doOnNext():3
        RxComputationThreadPool-1 | 602 | debug : doOnNext():5
        RxComputationThreadPool-2 | 612 | value = 1<>
        RxComputationThreadPool-2 | 812 | value = 1<>
        RxComputationThreadPool-3 | 1022 | value = 3<>
        RxComputationThreadPool-3 | 1213 | value = 3<>
        RxComputationThreadPool-4 | 1417 | value = 5<>
        RxComputationThreadPool-4 | 1618 | value = 5<>
         */
    }

    public void ex15_concatMapToFlatMap() {
        TimeUtil.setStartTime();

        String[] balls = {"1", "3", "5"};
        Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map(Long::intValue)
                .map(idx -> balls[idx])
                .take(balls.length)
                .doOnNext(item -> Log.debugWithTime("doOnNext():" + item))
                .flatMap(ball -> Observable.interval(200L, TimeUnit.MILLISECONDS)
                        .map(notUsed -> ball + "<>")
                        .take(2))
                .subscribe(Log::printlnWithTime);

        TimeUtil.sleep(2000);

        /*
        RxComputationThreadPool-2 | 384 | value = 1<>
        RxComputationThreadPool-3 | 480 | value = 3<>
        RxComputationThreadPool-4 | 580 | value = 5<>
        RxComputationThreadPool-2 | 586 | value = 1<>
        RxComputationThreadPool-3 | 681 | value = 3<>
        RxComputationThreadPool-4 | 782 | value = 5<>
         */
    }

    /*
        switchMap()
     */
    public void ex16_switchMap() {
        TimeUtil.setStartTime();

        String[] balls = {"1", "3", "5"};
        Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map(Long::intValue)
                .map(idx -> balls[idx])
                .take(balls.length)
                .switchMap(ball -> Observable.interval(200L, TimeUnit.MILLISECONDS)
                        .map(notUsed -> ball + "<>")
                        .take(2))
                .subscribe(Log::printlnWithTime);

        TimeUtil.sleep(2000);

        /*
        RxComputationThreadPool-4 | 831 | value = 5<>
        RxComputationThreadPool-4 | 1029 | value = 5<>
         */
    }

    public void ex17_switchMap() {
        TimeUtil.setStartTime();

        String[] balls = {"1", "3", "5"};
        Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map(Long::intValue)
                .map(idx -> balls[idx])
                .take(balls.length)
                .doOnNext(item -> Log.debugWithTime("doOnNext():" + item))
                .switchMap(ball -> Observable.interval(200L, TimeUnit.MILLISECONDS)
                        .map(notUsed -> ball + "<>")
                        .take(2))
                .subscribe(Log::printlnWithTime);

        TimeUtil.sleep(2000);

        /*
        RxComputationThreadPool-1 | 108 | debug : doOnNext():1
        RxComputationThreadPool-1 | 204 | debug : doOnNext():3
        RxComputationThreadPool-1 | 307 | debug : doOnNext():5
        RxComputationThreadPool-4 | 511 | value = 5<>
        RxComputationThreadPool-4 | 708 | value = 5<>
         */
    }


    /*
        groupBy()
     */
    public void ex18_groupBy() {
        Pair<String, Shape>[] objs = new Pair[]{
                new Pair<>("6", Shape.BALL),
                new Pair<>("4", Shape.BALL),
                new Pair<>("2", Shape.TRIANGLE),
                new Pair<>("2", Shape.BALL),
                new Pair<>("6", Shape.TRIANGLE),
                new Pair<>("4", Shape.TRIANGLE),
        };

        Observable.fromArray(objs)
                .groupBy(Pair::getSecond)
                .subscribe(
                        obj -> obj.subscribe(
                                groupItem -> Log.println("Group: " + obj.getKey() + "\t Value: " + groupItem)
                        )
                );

        /*
        main | value = Group: BALL	 Value: (6, BALL)
        main | value = Group: BALL	 Value: (4, BALL)
        main | value = Group: TRIANGLE	 Value: (2, TRIANGLE)
        main | value = Group: BALL	 Value: (2, BALL)
        main | value = Group: TRIANGLE	 Value: (6, TRIANGLE)
        main | value = Group: TRIANGLE	 Value: (4, TRIANGLE)
         */
    }

    // ex19 are unnecessary.

    public void ex20_groupByWithFilter() {
        Pair<String, Shape>[] objs = new Pair[]{
                new Pair<>("6", Shape.BALL),
                new Pair<>("4", Shape.BALL),
                new Pair<>("2", Shape.TRIANGLE),
                new Pair<>("2", Shape.BALL),
                new Pair<>("6", Shape.TRIANGLE),
                new Pair<>("4", Shape.TRIANGLE),
        };

        Observable.fromArray(objs)
                .groupBy(Pair::getSecond)
                .subscribe(
                        obj -> obj
                                .filter(notUsed -> obj.getKey().equals(Shape.BALL))
                                .subscribe(
                                groupItem -> Log.println("Group: " + obj.getKey() + "\t Value: " + groupItem)
                        )
                );

        /*
        main | value = Group: BALL	 Value: (6, BALL)
        main | value = Group: BALL	 Value: (4, BALL)
        main | value = Group: BALL	 Value: (2, BALL)
         */
    }

    public void ex20_groupByWithFilter_2() {
        Pair<String, Shape>[] objs = new Pair[]{
                new Pair<>("6", Shape.BALL),
                new Pair<>("4", Shape.BALL),
                new Pair<>("2", Shape.TRIANGLE),
                new Pair<>("2", Shape.BALL),
                new Pair<>("6", Shape.TRIANGLE),
                new Pair<>("4", Shape.TRIANGLE),
        };

        Observable.fromArray(objs)
                .filter(item -> item.getSecond().equals(Shape.BALL))
                .subscribe(Log::println);

        /*
        main | value = (6, BALL)
        main | value = (4, BALL)
        main | value = (2, BALL)
         */
    }

    public void ex20_groupByWithFilter_3() {
        Pair<String, Shape>[] objs = new Pair[]{
                new Pair<>("6", Shape.BALL),
                new Pair<>("4", Shape.BALL),
                new Pair<>("2", Shape.TRIANGLE),
                new Pair<>("2", Shape.BALL),
                new Pair<>("6", Shape.TRIANGLE),
                new Pair<>("4", Shape.TRIANGLE),
        };

        Observable.fromArray(objs)
                .groupBy(Pair::getSecond)
                .subscribe(Log::println);

        /*
        main | value = io.reactivex.internal.operators.observable.ObservableGroupBy$GroupedUnicast@143640d5
        main | value = io.reactivex.internal.operators.observable.ObservableGroupBy$GroupedUnicast@6295d394
         */
    }

    public void ex21_scan() {
        String[] balls = {"1", "3", "5"};
        Observable.fromArray(balls)
                .scan((prevResult, nextItem) -> nextItem + "(" + prevResult + ")")
                .subscribe(Log::println);

        /*
        main | value = 1
        main | value = 3(1)
        main | value = 5(3(1))
         */
    }

    /*
        zip()
     */
    public void ex22_zip() {
        Shape[] shapes = {Shape.BALL, Shape.DIAMOND, Shape.PENTAGON};
        Integer[] numbers = {1, 2, 3};

        Observable.zip(
                Observable.fromArray(shapes).map(item -> item.name()),
                Observable.fromArray(numbers),
                (item1, item2) -> item1 + "-" + item2
        ).subscribe(Log::println);

        /*
        main | value = BALL-1
        main | value = DIAMOND-2
        main | value = PENTAGON-3
         */
    }

    // ex23 are unnecessary.

    public void ex24_zipForSummation() {
        Observable.zip(
                Observable.just(100, 200, 300),
                Observable.just(10, 20, 30),
                Observable.just(1, 2, 3),
                (a, b, c) -> a+b+c
        ).subscribe(Log::println);

        /*
        main | value = 111
        main | value = 222
        main | value = 333
         */
    }

    public void ex25_zipWithInterval() {
        TimeUtil.setStartTime();
        Observable.zip(
                Observable.just("RED", "GREEN", "BLUE"),
                Observable.interval(200L, TimeUnit.MILLISECONDS),
                (value, notUsed) -> value
        ).subscribe(Log::printlnWithTime);

        TimeUtil.sleep(1000);

        /*
        RxComputationThreadPool-1 | 229 | value = RED
        RxComputationThreadPool-1 | 432 | value = GREEN
        RxComputationThreadPool-1 | 629 | value = BLUE
         */
    }

    // ex26 are unnecessary.

    /**
     * Electric Bill is made with "basic" rate and "section" rate
     * 1. Basic rate: the usage is
     *  <= 200 : 910
     *  <= 400 : 1,600
     *  > 400 : 7,300
     *
     * 2. Section rate: the range part of the usage is
     *  <= 200 : 93.3
     *  <= 400 : 187.9
     *  > 400 : 280.6
     */
    public void ex27_zipForElectricBills() {
        Integer[] data = {
                100,        // Basic: 910 + Section: 93.3 * 100 = 10,240
                300,        // Basic: 1600 + Sections: 93.3 * 200 + 187.9 * 100 = 39,050
                500         // Basic: 7300 + Sections: 93.3 * 200 + 187.9 * 200 + 280.6 * 100 = 7300 + 18660 + 37580 + 28060
        };

        Observable<Integer> basicPrice = Observable.fromArray(data)
                .map(usage -> {
                    if (usage <= 200) return 910;
                    else if (usage <= 400) return 1600;
                    else return 7300;
                });

        Observable<Double> sectionPrice = Observable.fromArray(data)
                .map(usage -> {
                    double section1 = Math.min(200, usage) * 93.3;
                    double section2 = Math.min(200, Math.max(usage - 200, 0)) * 187.9;
                    double section3 = Math.max(0, usage - 400) * 280.6;

                    return section1 + section2 + section3;
                });

        Observable.zip(
                basicPrice,
                sectionPrice,
                Observable.fromArray(data),
                (item1, item2, item3) -> new Pair(item3, new DecimalFormat("#,###").format(item1 + item2))
        ).map(
                pair -> {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Usage: ").append(pair.getFirst()).append(" kWh");
                    sb.append(" => ");
                    sb.append("Price: ").append(pair.getSecond()).append("KRW");
                    return sb.toString();
                }
        ).subscribe(Log::println);

        /*
        main | value = Usage: 100 kWh => Price: 10,240KRW
        main | value = Usage: 300 kWh => Price: 39,050KRW
        main | value = Usage: 500 kWh => Price: 91,600KRW
         */
    }

    public void ex28_zipWith() {
        Observable.zip(
                Observable.just(100, 200, 300),
                Observable.just(10, 20, 30),
//                Observable.just(1, 2, 3),
                (a, b) -> a + b
        ).zipWith(Observable.just(1, 2, 3), (x, c) -> x + c)
                .subscribe(Log::println);

        /*
        main | value = 111
        main | value = 222
        main | value = 333
         */
    }
}
