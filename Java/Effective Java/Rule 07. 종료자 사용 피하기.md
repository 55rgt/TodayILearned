## Rule 07. 종료자 사용 피하기
- 종료자(finalizer)는 예측 불가능하며, 대체로 위험하고, 일반적으로 불필요하다.
#### 종료자(finalizer)
- JVM에서 GC가 수행될 때 더 이상 사용하지 않는 자원을 정리하기 위해 호출되는 메서드.
- 종료자의 단점은, 즉시 실행된다는 보장이 없다는 것이다.
- 따라서 긴급한 작업을 종료자 안에서 처리하면 안 된다.
ex) 파일 닫기
- 종료자 실행시점은 GC 알고리즘에 좌우되는데, 이는 JVM 구현마다 다르기 때문에 JVM마다 시점이 많이 다를 수 있다.
- 종료자의 더딘 실행과 이식성을 둘 다 보장하면서 해결할 수 있는 방법은 존재하지 않으므로, 종료자 사용을 피하는 것이 좋다.
→ 따라서, 지속성이 보장되어야 하는 중요 상태 정보는 종료자로 갱신하면 안 된다.
- System.gc 나 System.runFinalization 같은 메서드도 종료자가 실행될 가능성을 높여주지만, 보장하는 것은 아니다.
- 또한, 종료자로 종료 처리를 하는 중 무점검 예외(Uncaught exception)가 던져지면, 해당 예외는 무시되고 종료 과정은 중단되기 때문에 이런 예외는 객체 상태를 망가뜨릴 수 있다.
- 이렇게 되면 일반적인 무점검 예외가 stack trace를 나타내는 것과는 다르게 종료자 안에서는 경고 문구도 출력되지 않는다.
- 종료자의 또다른 문제는 종료자를 사용하면 프로그램 성능이 심각하게 떨어진다는 것이다.
- 만약 파일이나 스레드처럼 명시적으로 반환하거나 삭제해야 하는 자원을 포함하는 객체 클래스는 명시적인 종료 메서드(termination method)를 하나 정의하고, 더 이상 필요하지 않는 객체라면 클라이언트가 해당 메서드를 호출하도록 하면 된다.
ex) 파일 입출력의 close 메서드
- 보통 이러한 명시적 종료 메서드는 객체 종료를 보장하기 위해 try - finally 와 함께 쓰인다.

```
/* try-finally 블록을 통해 종료 메서드 실행을 보장하는 예시 코드. */
Term term = new Term(...);
try{
    // term 으로 해야 하는 작업 수행
} finally {
    term.terminate(); // 명시적 종료 메서드를 호출한다.
    /* java 1.7 이후부터는 try-with-resources 문을 지원하는데, 이를 이용하면 finally 블록은 
       사용하지 않아도 된다. */
} 
```

### [종료자가 사용되는 곳]
1. 명시적 종료 메서드 호출을 잊을 경우에 대비하는 안전망으로서의 역할
→ 종료자가 언제 호출될 지 모르기 때문에 자원 반환이 늦어지지만, 클라이언트가 명시적으로 종료 메서드를 호출하는 것을 잊더라도 자원은 반환된다.
→ 하지만, 종료자는 그런 자원을 발견하게 될 경우에는 반드시 경고 메시지를 로그로 넘겨야 한다.

2. 네이티브 피어(native peer)와 연결된 객체를 다룰 때
#### 네이티브 피어(native peer)
- 일반 자바 객체가 네이티브 메서드를 통해 기능 수행을 위임하는 네이티브 객체
- 네이티브 피어는 일반 객체가 아니므로, GC가 알 수 없고 자바 피어 객체가 반환될 때 같이 반환할 수도 없다.
- 네이티브 피어가 즉시 종료되어야 하는 자원을 포함하는 경우에는, 명시적인 종료 메서드를 클래스에 추가해야 한다.
- - - -
- 종료자 연결은 자동으로 이루어지지 않는다.
```
/* 수동 종료자 연결(Manual finalizer chaining) */
@Override protected void finalize() throws Throwable{
    try {
        ... // 하위 클래스의 상태를 종료함.
    } finally {
        super.finalize(); // 상위 클래스 종료자 호출.
    } 
}
```

- 하위 클래스에서 상위 클래스 종료자를 재정의 한 후에 상위 클래스 종료자 호출을 잊어버릴 수 있기 때문에, 종료되어야 하는 모든 객체마다 여벌의 객체를 하나 더 만들고 종료되어야 하는 객체의 클래스 안에 종료자를 정의하는 대신, 익명 클래스 안에 종료자를 정의할 수 있다.
→ 이 익명 클래스는 해당 클래스의 객체를 포함하는 객체를 종료시킨다. (종료 보호자)

```
/* 종료 보호자 숙어(Finalizer Guardian idiom) */
public class Foo{
    // 이 객체는 바깥 객체(Foo)를 종료시키는 역햘만 수행한다.
    private final Object finalizerGuardian = new Object() {
        @Override protected void finalize() throws Throwable {
            ... // 바깥 Foo 객체를 종료시킴
        }
    };
    ...
}
```

#### 요약)
- 자원 반환에 대한 최종적 안전장치를 구현하거나, 그다지 중요하지 않은 네이티브 자원을 종료시키는 것이 아니라면 종료자는 사용하지 않는 것이 낫다.
- 만약 종료자를 굳이 사용해야 한다면, super.finalize()를 사용하고 로그를 남기자.
- 또한, 실수로 super.finalize()를 잊어도 종료가 가능하도록 종료 보호자 패턴을 도입하는 것에 대해 생각해보자.