package com.duk.lab.rxjava.chapter5;

import com.duk.lab.rxjava.utils.Log;
import com.duk.lab.rxjava.utils.TimeUtil;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class Chapter5 {

    // ex1 are unnecessary.

    public void ex2_subscribeOn_observeOn() {
        String[] objs = {"1-5", "2-T", "3-P"};
        Observable.fromArray(objs)
                .doOnNext(item -> Log.println("Original item=" + item))
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .map(item -> "flipped " + item)
                .subscribe(Log::println);

        TimeUtil.sleep(500);

        /*
        RxNewThreadScheduler-1 | value = Original item=1-5
        RxNewThreadScheduler-1 | value = Original item=2-T
        RxNewThreadScheduler-1 | value = Original item=3-P
        RxNewThreadScheduler-2 | value = flipped 1-5
        RxNewThreadScheduler-2 | value = flipped 2-T
        RxNewThreadScheduler-2 | value = flipped 3-P
         */
    }

    public void ex3_subscribeOnOnly() {
        String[] objs = {"1-5", "2-T", "3-P"};
        Observable.fromArray(objs)
                .doOnNext(item -> Log.println("Original item=" + item))
                .subscribeOn(Schedulers.newThread())
//                .observeOn(Schedulers.newThread())
                .map(item -> "flipped " + item)
                .subscribe(Log::println);

        TimeUtil.sleep(500);

        /*
        RxNewThreadScheduler-3 | value = Original item=1-5
        RxNewThreadScheduler-3 | value = flipped 1-5
        RxNewThreadScheduler-3 | value = Original item=2-T
        RxNewThreadScheduler-3 | value = flipped 2-T
        RxNewThreadScheduler-3 | value = Original item=3-P
        RxNewThreadScheduler-3 | value = flipped 3-P
         */
    }

    /*
        newThread()
     */
    public void ex4_newThreadScheduler() {
        String[] orgs = {"1", "3", "5"};

        Observable.fromArray(orgs)
                .doOnNext(item -> Log.println("Original item=" + item))
                .map(item -> "<<" + item + ">>")
                .subscribeOn(Schedulers.newThread())
                .subscribe(Log::println);

        TimeUtil.sleep(500);

        Observable.fromArray(orgs)
                .doOnNext(item -> Log.println("Original item=" + item))
                .map(item -> "##" + item + "##")
                .subscribeOn(Schedulers.newThread())
                .subscribe(Log::println);

        TimeUtil.sleep(500);

        /*
        RxNewThreadScheduler-1 | value = Original item=1
        RxNewThreadScheduler-1 | value = <<1>>
        RxNewThreadScheduler-1 | value = Original item=3
        RxNewThreadScheduler-1 | value = <<3>>
        RxNewThreadScheduler-1 | value = Original item=5
        RxNewThreadScheduler-1 | value = <<5>>
        RxNewThreadScheduler-2 | value = Original item=1
        RxNewThreadScheduler-2 | value = ##1##
        RxNewThreadScheduler-2 | value = Original item=3
        RxNewThreadScheduler-2 | value = ##3##
        RxNewThreadScheduler-2 | value = Original item=5
        RxNewThreadScheduler-2 | value = ##5##
         */
    }

    public void ex4_newThreadScheduler_2() {
        String[] orgs = {"1", "3", "5"};

        Observable.fromArray(orgs)
                .doOnNext(item -> Log.println("Original item=" + item))
                .map(item -> "<<" + item + ">>")
                .subscribeOn(Schedulers.newThread())
                .subscribe(Log::println);

//        TimeUtil.sleep(500);

        Observable.fromArray(orgs)
                .doOnNext(item -> Log.println("Original item=" + item))
                .map(item -> "##" + item + "##")
                .subscribeOn(Schedulers.newThread())
                .subscribe(Log::println);

//        TimeUtil.sleep(500);

        /*
        RxNewThreadScheduler-3 | value = Original item=1
        RxNewThreadScheduler-3 | value = <<1>>
        RxNewThreadScheduler-3 | value = Original item=3
        RxNewThreadScheduler-3 | value = <<3>>
        RxNewThreadScheduler-3 | value = Original item=5
        RxNewThreadScheduler-3 | value = <<5>>
        RxNewThreadScheduler-4 | value = Original item=1
        RxNewThreadScheduler-4 | value = ##1##
        RxNewThreadScheduler-4 | value = Original item=3
        RxNewThreadScheduler-4 | value = ##3##
        RxNewThreadScheduler-4 | value = Original item=5
        RxNewThreadScheduler-4 | value = ##5##
         */
    }

    /*
        computation()
     */
    public void ex5_computationScheduler() {
        String[] orgs = {"1", "3", "5"};

        Observable<String> source = Observable.fromArray(orgs)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a, b) -> a);

        source.map(item -> "<<" + item + ">>")
                .subscribe(Log::println);

        source.map(item -> "##" + item + "##")
                .subscribe(Log::println);

        TimeUtil.sleep(1000);

        /*
        RxComputationThreadPool-2 | value = ##1##
        RxComputationThreadPool-1 | value = <<1>>
        RxComputationThreadPool-1 | value = <<3>>
        RxComputationThreadPool-2 | value = ##3##
        RxComputationThreadPool-1 | value = <<5>>
        RxComputationThreadPool-2 | value = ##5##
         */
    }

    /*
        io()
     */
    public void ex6_ioScheduler() {

    }
}
