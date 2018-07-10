# [**RxJava 프로그래밍**](http://www.hanbit.co.kr/store/books/look.php?p_code=B3448548347/) 배우기
* written by 유동환, 박정준
* published by 한빛미디어


## Plus
* Kotlin trial
* JUnit5


# Chapter 1: 리액티브 프로그래밍 소개
* RxJava는 비동기 프로그래밍 라이브러리이고 함수형 프로그래밍 기법을 도입하여 쓰레드에 안전
* Hot/Cold Observable 개념과 클래스, map-filter-reduce 패턴과 함수를 알아야 한다. 물론 이것이 끝은 아니다.
* 마블 다이어그램을 볼줄 알아야 한다.
* RxJava1: 2013 ~ 2018.03 까지...Java9를 바라보려면 RxJava2를 써야 한다.
* RxJava2는 Java6 이상 가능하다.(Java8에서 쓰는 Consumer, Predicate, Function 등 함수형 인터페이스를 자체 구현함)
* Java 8에서 메소드 레퍼런스(::)가 추가 되었다.
  ex) System.out::println 을
        data -> System.out.println(data) 로 하위호환 할 수 있다.


# Chapter 2: Observable 처음 만들기
### 1. Observable 객체(구독할 대상)를 생성하는 방법: Factory Pattern
	1.1. just(): 임의의 data를 하나씩 순차적으로 인자에 추가하여 생성

	1.2 create(): onNext(), OnError(), onComplete() 를 직접 호출해야 함
        * 잠재적 Memory leak 주의: dispose할때 등록된 콜백을 모두 해지해야 함
	    * subscribe 하는 동안만 onNext, onComplete 호출해야 함
	    * 에러는 onError로만(Exception 안되나?)
	    * 배압(back pressure) 직접 처리해야 함
	
	1.3. fromArray():
	    * 선언부터 array 형태
	    * int[] >> Integer[]로 변형해야 함
	    * ArrayList는 iterable로 가야함

	1.4. fromIterable(): list, set, queue 같은 Iterator를 구현해놓은 Collection 클래스
	    * Map은 Iterator를 구현하지 않아서 X >> github에다가 fromMap() 도전해보길..

	1.5. fromCallable(): Callable API(Java5) 사용시
	    * Callable은 여러 쓰레드를 병렬로 돌리는 조건에서(ex:병렬계산) Thread를 직접 만들지 않고 쓸 수 있게 하는 기능, Runnable과 유사하지만 Return을 할 수 있다는 점에서 다르다.

	1.6. fromFuture(): Future API(Java5) 사용시
	    * Callable의 리턴값을 받아올때 사용.
	    * get()하면 Callable이 리턴할때까지 블로킹 된다.

	1.7. fromPublisher(): Flow API의 일부(Java9)
	    * onNext(), onComplete() 호출할 수 있다.

### 2. Single: 1개의 data만 발행하도록 한정. 발행과 동시에 종료된다.
* onSuccess(): 발행 및 종료 = onNext() + onComplete()
* onError(): 에러
* Observable에서 변환
	a. Single.fromObservable(): 없거나 한개만 있어야 함
	b. observable.single(): 없거나 한개만 있어야 함
	c. observable.first(): 맨 첫번째
	d. observable.take(1): 맨 첫번째


### 3. Maybe = Single + onComplete()
* onSuccess(), onError(), onComplete()
* data 하나만, 발행 없이 종료도 가능
* Chapter 3에서 자세히...


### 4. Hot Observable
* Cold VS Hot
	* Cold:
		* subscribe 하면 data의 처음부터 받는 개념
		* subscribe 하지 않으면 발행되지 않는다.
		* Lazy 접근법
		* ex) 웹 요청, DB 쿼리, 파일 읽기 등

	* Hot:
		* subscribe 하면 그 시점의 data부터 받는 개념
		* subscribe 하지 않아도 발행가능
		* data를 처음부터 모두 수신할 수 있다는 보장이 없음
		* ex) 마우스/키보드 이벤트, 시스템 이벤트, 센서 데이터, 주식 가격 등
		* 주의) back pressure(배압)를 고려해야 함

	Cold Observable >> | Subject 객체 생성 | OR | ConnectableObservable 이용 | >> Hot Observable
	                   

### 5. Subject
	5.1. AsyncSubject
	    * 완료되기 전 마지막 data만 관심
	    * onComplete() 와 동시에 data 발행 후 종료
	    * subscriber로도 동작가능

	5.2. BehaviorSubject
	    * subscribe 전에 발행되었던 data중 가장 최근 것을 subscribe 직후 발행해줌
	    * createDefault()로 생성 >> Default: 최근 발행한 값이 없을 경우
	    * ex) 온도센서

	5.3. PublishSubject
	    * subscribe 이후부터 발행, 이전은 무시(Hot Observable의 기본개념)

	5.4. ReplaySubject
	    * Cold Observable과 유사
	    * Subscribe하는 구독자마다 처음부터 끝까지 모든 data 발행
	    * 모든 data를 저장해두어야 하는데 이때 Memory Leak 발생 가능성 있음

### 6. ConnectableObservable
* subscribe() + connect() 를 해야 발행된다.
* connect() 를 해서 이미 data가 발행되는 도중 subscribe가 들어오면 PublishSubject와 동일(subscribe 이후부터 발행, 이전은 무시)


# Chapter 3 : 리액티브 연산자 입문
* 언어에 따라 크게 다르지 않아서 RxJava가 익숙해지면 RxJS 등 다른 분야도 쉬울 수 있다.
* Creating: create(), just(), fromArray(), interval(), range(), timer(), defer() 등
* Transforming: map(), flatMap() 등
* Fiter: filter(), first(), take() 등
* Combining: 여러 Observable을 조합
* Error Handling: onErrorReturn(), onErrorResumeNext(), retry() 등
* Utility: subscribeOn(), observeOn() 등
* Conditional: Observable의 흐름 제어
* Math & Aggregate: 4장 참조
* Back Pressure: 8장 참조

### 1. map()
>   * 입력값들이 함수(Function<T, R>)를 거쳐서 원하는 값으로 변환하는 함수 
>   * Input : Output = 1 : 1
>   * Python의 map()과 유사
>   * ex) String to Integer

### 2. flatMap()
>   * 입력값들이 함수(Function<T, Observable<T>>)를 거쳐서 각각 원하는 Observable(여러개)로 치환되고 그것이 다시 일렬로 합쳐지는 함수
>   * Input : Output = 1 : * (>= 1, 1 Observable)
>   * Observable로 치환되었다가 다시 합쳐질때 병렬로 합쳐지므로 순서가 섞일 수 있음(대안: concatMap())

### 3. filter()
>   * 입력값들중에 함수(Predicate<T>)를 통해 True로 판별되는 아이템만 걸러내는 함수
>   * Input : Output = 1 : a (<= 1)
>   * 유사기능 함수
>       * first(default value): 첫번째 값, 없으면 Default value를 사용
>       * last(default value): 마지막 값, 없으면 Default value를 사용
>       * take(N times): 앞에서부터 N개, data개수 < N 이면 data 모두 나옴(오류 안남)
>       * takeLast(N times): 뒤에서부터 N개, data개수 < N 이면 data 모두 나옴(오류 안남)
>       * skip(N times): 앞에서부터 N개 건너뜀, data개수 < N 이면 data 안나옴(오류 안남)
>       * skipLast(N times): 뒤에서부터 N개 건너뜀, data개수 < N 이면 data 안나옴(오류 안남)

### 4. reduce()
>   * 입력값들을 함수(BiFunction<T, T, R>)를 통해 누적될 수 있도록 하는 함수
>   * Input : Output = * : 1 (= Observable대신 Maybe를 반환한다)
>   * BiFunction<T == prev, T == current, R = result>