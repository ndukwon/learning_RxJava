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
>	* 1.1. just(): 임의의 data를 하나씩 순차적으로 인자에 추가하여 생성
>   * 1.2. create(): onNext(), OnError(), onComplete() 를 직접 호출해야 함
>       * 잠재적 Memory leak 주의: dispose할때 등록된 콜백을 모두 해지해야 함
>       * subscribe 하는 동안만 onNext, onComplete 호출해야 함
>       * 에러는 onError로만(Exception 안되나?)
>       * 배압(back pressure) 직접 처리해야 함
>   * 1.3. fromArray():
>       * 선언부터 array 형태
>       * int[] >> Integer[]로 변형해야 함
>       * ArrayList는 iterable로 가야함
>   * 1.4. fromIterable(): list, set, queue 같은 Iterator를 구현해놓은 Collection 클래스
>       * Map은 Iterator를 구현하지 않아서 X >> github에다가 fromMap() 도전해보길..
>   * 1.5. fromCallable(): Callable API(Java5) 사용시
>       * Callable은 여러 쓰레드를 병렬로 돌리는 조건에서(ex:병렬계산) Thread를 직접 만들지 않고 쓸 수 있게 하는 기능, Runnable과 유사하지만 Return을 할 수 있다는 점에서 다르다.
>   * 1.6. fromFuture(): Future API(Java5) 사용시
>       * Callable의 리턴값을 받아올때 사용.
>       * get()하면 Callable이 리턴할때까지 블로킹 된다.
>   * 1.7. fromPublisher(): Flow API의 일부(Java9)
>       * onNext(), onComplete() 호출할 수 있다.

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
>	* 5.1. AsyncSubject
>       * 완료되기 전 마지막 data만 관심
>       * onComplete() 와 동시에 data 발행 후 종료
>       * subscriber로도 동작가능
>   * 5.2. BehaviorSubject
>       * subscribe 전에 발행되었던 data중 가장 최근 것을 subscribe 직후 발행해줌
>       * createDefault()로 생성 >> Default: 최근 발행한 값이 없을 경우
>       * ex) 온도센서
>   * 5.3. PublishSubject
>       * subscribe 이후부터 발행, 이전은 무시(Hot Observable의 기본개념)
>   * 5.4. ReplaySubject
>       * Cold Observable과 유사
>       * Subscribe하는 구독자마다 처음부터 끝까지 모든 data 발행
>       * 모든 data를 저장해두어야 하는데 이때 Memory Leak 발생 가능성 있음

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


# Chapter 4 : 리액티브 연산자의 활용
### 1. 생성 연산자
>   * 1.1. Observable.interval(초기 Delay(Default==시간간격), 시간간격, 시간단위):
>       * 지정 시간 간격으로 data: 0L++ 발행(default==영원히)
>       * Thread: Computation scheduler
>   * 1.2. Observable.timer(Delay, 시간단위):
>       * 지정 시간 후에 data: 0L 한번만 발행
>       * Thread: Computation scheduler
>   * 1.3. Observable.range(초기값 start, 반복횟수 count):
>       * start, start + 1, start + 2, ... start + count - 1 까지 증가시키며 발행
>       * Thread: None
>   * 1.4. Observable.intervalRange():
>       * interval() + range() : 범위안에 간격을 두어 발행
>       * Thread: Computation scheduler
>   * 1.5. Observable.defer():
>       * Observable대신 data가 담긴 Observable을 생성할 수 있는 Callable 받아서 subscribe 이후에 (반복)생성 후 발행
>       * Thread: None
>   * 1.6. observe.repeat(count (default=infinite)): data가 끝나면 다시 create
>       * data를 onComplete() 없이 계속 처음부터 (count 만큼) 반복 발행, 반복횟수가 끝나야 onComplete() 된다
>       * Thread: None 이지만 create를 하는 함수의 Thread 방식을 따른다.
>       * timer() + repeat() = thread가 매번 다른 interval()

### 2. 변환 연산자
>   * 2.1. concatMap(item -> Observable):
>       * Input : Output = 1 : * (>= 1, 1 Observable)
>       * flatMap() 과 유사하지만 각각의 item들이 부르는 Observable들을 차례차례 실행시켜서 interleaving(=병렬로 처리되어 중간에 끼워지는 현상) 없이 들어간다.
>       * data 발행, 구독 전달 Thread를 각각 다르게 사용한다. Thread 1: data 발행, Thread 2~ : 구독 전달
>   * 2.2. switchMap(item -> Observable):
>       * Input : Output = 1 : * (<>= 1)
>       * flatMap(), concatMap()과 유사하지만 다음 item이 들어오면 이전 item이 만들던 Observable을 중단시키고 병합시키지도 않음
>       * data 발행, 구독 전달 Thread를 각각 다르게 사용한다. Thread 1: data 발행, Thread 2~ : 구독 전달
>       * ex) 센서 값 얻어와서 처리하는 경우
>   * 2.3. groupBy(Function<>):
>       * Input : Output = N : M = 전체가 몇개의 그룹으로 묶여지며 이들은 다시 합쳐지지 않고 M개 그룹의 Observable로 발행된다.
>       * 그룹을 구분할 조건식을 넣으면 각 해당하는 그룹 Observable이 생성된다. 즉, 모든 아이템이 group으로 묶어지며 그룹 각각이 item(Observable)으로 된다.
>       * 각각 발행되는 item들을 subscribe 해야한다.
>   * 2.4. scan():
>       * Input : Output = 1 : 1
>       * reduce()와 거의 유사하지만 reduce()는 N:1 대응 결과값인 반면, scan()은 중간전달값도 발행한다.

### 3. 결합 연산자
>   * 3.1. Observable.zip(Observable..., BiFuction)
>       * 두개 이상의 Observable 각각에서 나오는 item들을(Cold: index가 같은것 끼리, Hot: ??) 하나의 묶음으로 만들고
>           이 묶음을 BiFuction에서 하나의 Return으로 만들어 주는 처리를 할 수 있게 한다.
>       * Input : Output = (n, m) : n (if n < m) = item이 많은 m쪽의 나머지는 결합할 다른쪽 item이 없기에 발행되지 않는다.
>       * zipInterval 기법: interval과 결합하여 item이 시간간격을 두고 발행될 수 있게 한다. 결과는 Computation Thread에서 발행
>   * 3.2. Observable.combineLatest(Observable...)
>       * 두개 이상의 Observable(최대 9개)에서 각각 item들이 발행될때 마다 결합시켜 발행시키는데, 발행안된 쪽은 가장 최신에 발행했던 item과 결합시켜 발행
>       * item이 발행된 적이 없는 상황에서는 결합시킬 최신이 없으므로 결합 발행이 되지 않는다. startWith()를 넣으면 초기값이 설정되므로 처음부터 발행될 수 있다.
>   * 3.3. Observable.merge(Observable...)
>       * 여러개의 Observable에서 발행된 순서대로 단순하게 합쳐서 1열로 만든다.
>   * 3.4. Observable.concat(Observable...)
>       * 여러개의 Observable의 끝과 시작을 이어준다. onComplete() 기준으로 이어줌
>       * A, B Observable을 이어줄때 A에서 onComplete()이 발생하지 않으면 B는 영원히 대기하게 되어 잠재적인 Memory leak 위험이 있다.

### 4. 조건 연산자
>   * 4.1. Observable.amb(Iterable<Observable>): Observable 여러개를 list형태로 받는다.
>       * 여러개의 Observable 중에서 먼저 발행되는 단 하나의 Observable만 발행하고 나머지 Observable은 무시함
>   * 4.2. source.takeUntil(condition: Observable)
>       * source를 발행하다가 주어진 condition Observable이 발행을 하나라도 하면 기존 source 발행은 중단한다.
>       * timer()와 함께 활용 편리
>   * 4.3. source.skipUntil(condition: Observable)
>       * source를 발행하지 않다가 주어진 condition Observable이 발행을 하나라도 하면 기존 observable 발행을 시작한다.
>       * takeUntil()과 반대의 상황
>       * timer()와 함께 활용 편리
>   * 4.4. source.all(Predicate) -> Single
>       * source의 모든 item들이 주어진 Predicate를 true로 통과하면, 단 하나의 true를 발행
>       * item중 Pradicate를 false로 통과하는게 단 하나라도 있다면, 단 하나의 false를 발행

### 5. 수학 및 기타 연산자
>   * 5.1. 수학함수
>       * observable.count(): 개수를 센다.
>       * flowable.max(): max를 찾는다. Flowable로 반환된다.
>       * flowable.min(): min를 찾는다. Flowable로 반환된다.
>       * flowable.sum(): sum을 구한다. Flowable로 반환된다.
>       * flowable.average(): average를 구한다. Flowable로 반환된다.
>   * 5.2. delay():
>       * 발행의 시작을 Delay 시킨다.
>       * Thread: Computation scheduler
>   * 5.3. timeInterval()
>       * item을 발행할때, 이전 발행과의 시간차이도 알려줌. 최초발행은 subscribe() 시점부터...
>       * item이 시간정보를 포함한 Timed 객체에 담기어 발행된다.