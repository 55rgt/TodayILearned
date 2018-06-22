## Rule 06. 유효기간 지난 객체 참조 폐기
```
/* 메모리 누수가 발생하는 클래스 예제
위 코드는 스택이 줄어들면서 제거한 객체들을 garbage collector가 처리하지 못한다.
왜냐하면 스택이 그러한 객체에 대한 만기 참조(obsolete reference)를 제거하지 않기 때문이다. */
public class MemoryLeakStack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public MemoryLeakStack(){elements = new Object[DEFAULT_INITIAL_CAPACITY]; }

    public void push(Object e){
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop(){
        if(size == 0) throw new EmptyStackException();
        return elements[--size];
    }
    private void ensureCapacity(){
        if(elements.length == size)
           elements = Arrays.copyOf(elements, 2 * size + 1);
    }
}
```

#### 만기 참조(obsolete reference)
- 다시 이용되지 않을 참조
- 위 코드에서는 elements 배열에서 실제로 이용되는 부분이 아닌 나머지 영역에 위치한 참조들이 만기 참조이다.
- 의도치 않은 객체 보유(unintentional object retention)를 찾아내는 것이 중요하다.
→ 객체 참조를 실수로 유지했을 경우, 연쇄적으로 그 객체가 참조하는 객체들 역시 gc에서 제외되므로 성능에 굉장한 악영향을 끼칠 가능성을 배제할 수 없다.

```
/* 쓸 일이 없는 객체 참조를 null로만 바꾼다면, 의도치 않은 객체 보유 현상을 없앨 수 있다.
또한, 실수로 인해 그 참조를 사용하려고 할 때 NullPointerException을 던지기 때문에
오류 상황을 곧바로 알아차릴 수 있다. */
public Object pop(){
        if(size == 0) throw new EmptyStackException();
        Object present = elements[--size];
        elements[size] = null;
        return present;
} 
```

- 하지만, 객체 참조를 null로 만드는 것보다 좋은 방법은 해당 참조가 보관된 변수가 유효범위(scope)를 벗어나게끔 두는 것이다.

### [메모리 누수가 흔히 발생하는 장소]

1. 자체적으로 관리하는 메모리가 있는 클래스
- 더 이상 사용 되지 않는 원소 안에 있는 객체 참조는 반드시 null로 바꿔야 한다.

2. 캐시(cache)
Solution 1) WeakHashMap을 가지고 캐시 구현
- 캐시 바깥에서 key를 참조하고 있을 때만 value를 보관하면 될 때 쓸 수 있다.
- key에 대한 참조가 만기참조가 되는 순간 캐시 안의 key - value는 자동 삭제.
- 캐시 안에 보관되는 항목의 수명이 키에 대한 외부 참조의 수명에 따라 결정될 때만 적용 가능.
Solution 2) 후면 스레드(background thread) 또는 캐시에 새로운 항목 추가 시 처리(LinkedHashMap 클래스 등 이용)
- 일반적으로 캐시에 보관되는 항목의 수명은 캐시에 보관된 기간에 따라 처리되기 때문에 이런 캐시는 사용하지 않는 항목들을 가끔 비워야 한다.

3. 리스너(listener) 등의 역호출자(callback)
- 역호출자 등록 기능을 제공하는 API를 사용하는 클라이언트가 역호출자를 명시적으로 제거하지 않을 경우에는, 메모리는 조치를 취하기 전까지 점유된 상태로 남아있다.
→ 역호출자에 대한 약한 참조만 저장하게끔 해서 gc가 역호출자를 즉시 처리하도록 할 수 있다.