# Chapter 1
- RxJava는 비동기 프로그래밍 라이브러리이고 함수형 프로그래밍 기법을 도입하여 쓰레드에 안전
- Hot/Cold Observable 개념과 클래스, map-filter-reduce 패턴과 함수를 알아야 한다. 물론 이것이 끝은 아니다.
- 마블 다이어그램을 볼줄 알아야 한다.
- RxJava1: 2013 ~ 2018.03 까지...Java9를 바라보려면 RxJava2를 써야 한다.
- RxJava2는 Java6 이상 가능하다.(Java8에서 쓰는 Consumer, Predicate, Function 등 함수형 인터페이스를 자체 구현함)
- Java 8에서 메소드 레퍼런스(::)가 추가 되었다.
  ex) System.out::println 을
        data -> System.out.println(data) 로 하위호환 할 수 있다.


# Chapter 2
1. Observable 객체(구독할 대상)를 생성하는 방법: Factory Pattern
  1) just(): 임의의 data를 하나씩 순차적으로 인자에 추가하여 생성
  2) create(): onNext(), OnError(), onComplete() 를 직접 호출해야 함
     - 잠재적 Memory leak 주의: dispose할때 등록된 콜백을 모두 해지해야 함
     - subscribe 하는 동안만 onNext, onComplete 호출해야 함
     - 에러는 onError로만(Exception 안되나?)
     - 배압(back pressure) 직접 처리해야 함
  3) fromArray():
     - 선언부터 array 형태
     - int[] >> Integer[]로 변형해야 함
     - ArrayList는 iterable로 가야함
  4) fromIterable(): list, set, queue 같은 Iterator를 구현해놓은 Collection 클래스
     - Map은 Iterator를 구현하지 않아서 X >> github에다가 fromMap() 도전해보길..
  5) fromCallable(): Callable API(Java5) 사용시
     - Callable은 여러 쓰레드를 병렬로 돌리는 조건에서(ex:병렬계산) Thread를 직접 만들지 않고 쓸 수 있게 하는 기능
       Runnable과 유사하지만 Return을 할 수 있다는 점에서 다르다.
  6) fromFuture(): Future API(Java5) 사용시
     - Callable의 리턴값을 받아올때 사용. get()하면 Callable이 리턴할때까지 블로킹 된다.
  7) fromPublisher(): Flow API의 일부(Java9)
     - onNext(), onComplete() 호출할 수 있다.

2. Single: 1개의 data만 발행하도록 한정. 발행과 동시에 종료된다.
   - onSuccess(): 발행 및 종료 = onNext() + onComplete()
   - onError(): 에러
   - Observable에서 변환
     1) Single.fromObservable(): 없거나 한개만 있어야 함
     2) observable.single(): 없거나 한개만 있어야 함
     3) observable.first(): 맨 첫번째
     4) observable.take(1): 맨 첫번째


3. Maybe

4. Hot Observable
  Cold Observable >> | Subject 객체 생성           | >> Hot Observable
                     | ConnectableObservable 이용 |
5. Subject
  1) AsyncSubject
  2) Behavior
  3) PublishSubject
  4) Replay

6. ConnectableObservable