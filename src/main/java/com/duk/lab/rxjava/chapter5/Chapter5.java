package com.duk.lab.rxjava.chapter5;

import com.duk.lab.rxjava.utils.Log;
import com.duk.lab.rxjava.utils.OkHttpHelper;
import com.duk.lab.rxjava.utils.StringUtil;
import com.duk.lab.rxjava.utils.TimeUtil;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Chapter5 {

    // ex1 is unnecessary.

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
        String root = "/";
        Observable.fromArray(new File(root).listFiles())
                .doOnNext(item -> Log.println("item =" + item))
                .filter(f -> !f.isDirectory())
                .map(f -> f.getAbsolutePath())
                .subscribeOn(Schedulers.io())
                .subscribe(Log::println);

        TimeUtil.sleep(500);

        /*
        RxCachedThreadScheduler-1 | value = item =/.HFS+ Private Directory Data
        RxCachedThreadScheduler-1 | value = item =/home
        RxCachedThreadScheduler-1 | value = item =/usr
        RxCachedThreadScheduler-1 | value = item =/.Spotlight-V100
        RxCachedThreadScheduler-1 | value = item =/net
        RxCachedThreadScheduler-1 | value = item =/.DS_Store
        RxCachedThreadScheduler-1 | value = /.DS_Store
        RxCachedThreadScheduler-1 | value = item =/.PKInstallSandboxManager
        RxCachedThreadScheduler-1 | value = item =/.PKInstallSandboxManager-SystemSoftware
        RxCachedThreadScheduler-1 | value = item =/bin
        RxCachedThreadScheduler-1 | value = item =/installer.failurerequests
        RxCachedThreadScheduler-1 | value = /installer.failurerequests
        RxCachedThreadScheduler-1 | value = item =/Network
        RxCachedThreadScheduler-1 | value = item =/sbin
        RxCachedThreadScheduler-1 | value = item =/.file
        RxCachedThreadScheduler-1 | value = /.file
        RxCachedThreadScheduler-1 | value = item =/etc
        RxCachedThreadScheduler-1 | value = item =/var
        RxCachedThreadScheduler-1 | value = item =/Library
        RxCachedThreadScheduler-1 | value = item =/.Trashes
        RxCachedThreadScheduler-1 | value = item =/System
        RxCachedThreadScheduler-1 | value = item =/.OSInstallerMessages
        RxCachedThreadScheduler-1 | value = /.OSInstallerMessages
        RxCachedThreadScheduler-1 | value = item =/.fseventsd
        RxCachedThreadScheduler-1 | value = item =/private
        RxCachedThreadScheduler-1 | value = item =/.DocumentRevisions-V100
        RxCachedThreadScheduler-1 | value = item =/.vol
        RxCachedThreadScheduler-1 | value = item =/Users
        RxCachedThreadScheduler-1 | value = item =/Applications
        RxCachedThreadScheduler-1 | value = item =/dev
        RxCachedThreadScheduler-1 | value = item =/Volumes
        RxCachedThreadScheduler-1 | value = item =/tmp
        RxCachedThreadScheduler-1 | value = item =/cores
         */
    }

    /*
        trampoline()
     */
    public void ex7_trampolineScheduler() {
        String[] orgs = {"1", "3", "5"};
        Observable<String> source = Observable.fromArray(orgs);

        source.subscribeOn(Schedulers.trampoline())
                .map(item -> "<<" + item + ">>")
                .subscribe(Log::println);

        source.subscribeOn(Schedulers.trampoline())
                .map(item -> "##" + item + "##")
                .subscribe(Log::println);

        TimeUtil.sleep(500);

        /*
        main | value = <<1>>
        main | value = <<3>>
        main | value = <<5>>
        main | value = ##1##
        main | value = ##3##
        main | value = ##5##
         */
    }

    public void ex7_trampolineScheduler2() {
//        Observable<Long> source = Observable.interval(50L, TimeUnit.MILLISECONDS);
        Observable<Long> source = Observable.interval(50L, TimeUnit.MILLISECONDS, Schedulers.trampoline());

//        source.subscribeOn(Schedulers.trampoline())
        source.map(item -> "<<" + item + ">>")
                .take(5)
                .subscribe(Log::println);

//        source.subscribeOn(Schedulers.trampoline())
        source.map(item -> "##" + item + "##")
                .take(5)
                .subscribe(Log::println);

        TimeUtil.sleep(1000);

        /*
        main | value = <<0>>
        main | value = <<1>>
        main | value = <<2>>
        main | value = <<3>>
        main | value = <<4>>
        main | value = ##0##
        main | value = ##1##
        main | value = ##2##
        main | value = ##3##
        main | value = ##4##
         */
    }

    /*
        single()
     */
    public void ex8_singleScheduler() {
        Observable<Integer> numbers = Observable.range(100, 5);
        Observable<String> chars = Observable.range(0, 5)
                .map(StringUtil::numberToCharacter);

        numbers.subscribeOn(Schedulers.single())
                .subscribe(Log::println);
        chars.subscribeOn(Schedulers.single())
                .subscribe(Log::println);

        TimeUtil.sleep(500);

        /*
        RxSingleScheduler-1 | value = 100
        RxSingleScheduler-1 | value = 101
        RxSingleScheduler-1 | value = 102
        RxSingleScheduler-1 | value = 103
        RxSingleScheduler-1 | value = 104
        RxSingleScheduler-1 | value = A
        RxSingleScheduler-1 | value = B
        RxSingleScheduler-1 | value = C
        RxSingleScheduler-1 | value = D
        RxSingleScheduler-1 | value = E
         */
    }

    /*
        from(Executor)
     */
    public void ex9_fromExecutorScheduler() {
        final int THREAD_NUM = 10;

        String[] data = {"1", "3", "5"};
        Executor executor = Executors.newFixedThreadPool(THREAD_NUM);

        Observable<String> source = Observable.fromArray(data);
        source.subscribeOn(Schedulers.from(executor))
                .map(item -> "<<" + item + ">>")
                .subscribe(Log::println);
        source.subscribeOn(Schedulers.from(executor))
                .map(item -> "##" + item + "##")
                .subscribe(Log::println);

        TimeUtil.sleep(500);

        /*
        pool-2-thread-1 | value = <<1>>
        pool-2-thread-1 | value = <<3>>
        pool-2-thread-1 | value = <<5>>
        pool-2-thread-2 | value = ##1##
        pool-2-thread-2 | value = ##3##
        pool-2-thread-2 | value = ##5##
         */
    }

    // ex10 is unnecessary.

    /*
        URL call
     */
    public void ex11_callingURL() {
        final String URL_README = "https://raw.githubusercontent.com/yudong80/reactivejava/master/README.md";
        final OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(URL_README)
                .build();

        client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.println(response.body().string());
                }
        });

        TimeUtil.sleep(1000);

        /*
        OkHttp https://raw.githubuserc... | value = Welcome to Java Reactive Programming!!
         */
    }

    // ex12, 13 are unnecessary.

    public void ex14_callingURL_afterCallingURL() {
        final String FIRST_URL = "https://api.github.com/zen";
        final String GITHUB_ROOT = "https://raw.githubusercontent.com/yudong80/reactivejava/master/";
        final String SECOND_URL = GITHUB_ROOT + "samples/callback_hell";
        final OkHttpClient client = new OkHttpClient();

        // FIRST CALL
        Request request = new Request.Builder()
                .url(FIRST_URL)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.println(response.body().string());

                // SECOND CALL
                Request request = new Request.Builder()
                        .url(SECOND_URL)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.println(response.body().string());
                    }
                });
            }
        });

        TimeUtil.sleep(2000);

        /*
        OkHttp https://api.github.com/... | value = Approachable is better than simple.
        OkHttp https://raw.githubuserc... | value = Welcome to Callback Hell!!
         */
    }

    public void ex15_callingURL_afterCallingURL2() {
        final String FIRST_URL = "https://api.github.com/zen";
        final String GITHUB_ROOT = "https://raw.githubusercontent.com/yudong80/reactivejava/master/";
        final String SECOND_URL = GITHUB_ROOT + "samples/callback_heaven";

        TimeUtil.setStartTime();

        Observable.just(FIRST_URL)
                .subscribeOn(Schedulers.io())
                .map(OkHttpHelper::run)
                .concatWith(Observable.just(SECOND_URL)
                        .map(OkHttpHelper::run))
                .subscribe(Log::printlnWithTime);

        TimeUtil.sleep(5000);

        /*
        RxCachedThreadScheduler-1 | 958 | value = Non-blocking is better than blocking.
        RxCachedThreadScheduler-1 | 1224 | value = Welcome to Callback Hell!!
         */
    }

    public void ex16_callingURLs() {
        final String FIRST_URL = "https://api.github.com/zen";
        final String GITHUB_ROOT = "https://raw.githubusercontent.com/yudong80/reactivejava/master/";
        final String SECOND_URL = GITHUB_ROOT + "samples/callback_heaven";

        TimeUtil.setStartTime();

        Observable<String> first = Observable.just(FIRST_URL)
                .subscribeOn(Schedulers.io())
                .map(OkHttpHelper::run);

        Observable<String> second = Observable.just(SECOND_URL)
                .subscribeOn(Schedulers.io())
                .map(OkHttpHelper::run);

        Observable.zip(first, second, (a, b) -> "\n>> " + a + "\n>> " + b)
                .subscribe(Log::printlnWithTime);

        TimeUtil.sleep(5000);

        /*
        RxCachedThreadScheduler-1 | 242 | value =
        >> Practicality beats purity.
        >> Happy Callback Heaven by RxJava2!!
         */
    }

    /*
        Weather API
        https://openweathermap.org
     */
    private static final String CURRENT_API = "http://api.openweathermap.org/data/2.5/weather?q=London&appid=";
    public void ex17_weatherAPI(String appId) {
        Observable<String> source = Observable.just(CURRENT_API + appId)
                .map(OkHttpHelper::run)
                .subscribeOn(Schedulers.io());

        Observable<String> temperature = source.map(this::parseTemperature);
        Observable<String> city = source.map(this::parseCity);
        Observable<String> country = source.map(this::parseCountry);
    }

    private String parseTemperature(String json) {
        return parse(json, "\"temp\":[0-9]*.[0-9]*");
    }

    private String parseCity(String json) {
        return parse(json, "\"name\":\"[a-zA-Z]*\"");
    }

    private String parseCountry(String json) {
        return parse(json, "\"country\":\"[a-zA-Z]*\"");
    }

    private String parse(String json, String pattern) {
        return "";
        // TODO: make a example
    }
}
