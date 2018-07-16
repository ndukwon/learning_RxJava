package com.duk.lab.rxjava.chapter4;

import com.duk.lab.rxjava.utils.Log;
import com.duk.lab.rxjava.utils.Shape;
import com.duk.lab.rxjava.utils.TimeUtil;
import io.reactivex.Observable;
import kotlin.Pair;

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
}
