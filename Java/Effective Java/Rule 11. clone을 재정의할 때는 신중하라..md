# Rule 11. clone을 재정의할 때는 신중하라.
#### Cloneable
- 어떤 객체가 복제를 허용한다는 것을 알리기 위해 고안된 믹스인 인터페이스.
- Cloneable은 clone이라는 메서드를 가지고 있는 것이 아니라, Object의 clone을 이용할 때 쓰인다.
→ 복제할 객체는 Cloneable을 구현해야 CloneNotSupportedException이 안 나타나게 되어 있음.
→ (잘못된 설계 방식)

### [clone 메서드 일반 규약]
- 객체의 복사본을 만들어서 반환한다. 클래스마다 '복사'의 의미는 다르다. 
- 일반적으로는 아래의 조건들을 충족한다.
```
/* 이 조건은 참이어야 한다. 즉, 같은 레퍼런스를 참조하고 있지 않다. */
x.clone() != x

/* 이 조건은 참이 되겠지만, 반드시 그래야 하는 것은 아니다. */
x.clone().getClass() == x.getClass()

/* 이 코드를 실행한 결과도 true겠지만, 반드시 그래야 하는 것도 아니다. 객체를 복사하면 보통 같은 클래스의 새로운 객체가 만들어지는데, 내부 자료 구조까지 복사해야 될 수도 있다. 어떤 생성자도 호출되지 않는다. */ 
x.clone().equals(x)
```

- - - -
- clone이 만드는 복사본의 내부 객체는 생성자로 만들 수 있다.
- 비 파이널 클래스에 clone을 재정의할 때는 반드시 super.clone()을 호출해 얻은 객체를 반환해야 한다.
→ 특정 클래스의 모든 상위 클래스가 이 규칙을 따른다면 super.clone()은 최종적으로 Object.clone()을 호출하므로 원하는 클래스의 객체가 만들어질 것이다.
- Cloneable 인터페이스를 구현하는 클래스는 제대로 동작하는 public clone 메서드를 제공해야 한다.
```
/* 클래스 안에 선언된 모든 필드가 기본 자료형이거나 변경 불가능 객체 참조라면
   아래 메서드만으로 clone이 가능하다.
   공변 반환형 도입으로 재정의 메서드의 반환값 자료형은 재정의 되는 메서드의 반환값 자료형의
   하위 클래스가 될 수 있다.
   따라서 클라이언트가 굳이 형변환(cast)을 하지 않아도 된다.
   라이브러리가 할 수 있는 일은 클라이언트에게 미루지 않아야 한다.
*/
@Override public PhoneNumber clone() {
    try {
        return (PhoneNumber) super.clone();
    } catch ( CloneNotSupportedException e ) {
        throw new AssertionError(); // 수행되지 않음.
    }
}
```
```
/* 복제할 객체가 변경 가능 객체에 대한 참조 필드를 가지고 있다면, 위 메서드를 이용하면 안된다.
   아래 Stack 클래스를 복제하기 위해서는 Object[] 타입인 elements에 대해 신경을 써 줘야 한다.
*/
public class Stack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    ...
}
/* clone 메서드는 또다른 형태의 생성자이므로 원래 객체를 손상시키는 일이 없도록 해야 하고
   복사본의 불변식도 제대로 만족시켜야 한다.
   또한 변경 가능 객체의 clone을 재귀적으로 호출해야 한다. */
@Override public Stack clone() {
    try {
        Stack result = (Stack) super.clone();
        result.elements = elements.clone();
        return result;
    } catch ( CloneNotSupportedException e ) {
        throw new AssertionError();
    }
}
```

clone은 final 필드의 용법과 호환되지 않으므로, 복제 가능한 클래스를 만들기 위해서는 필드의 final 선언을 지워야 할 수도 있다.

또한, clone을 재귀적으로 호출하는 것만으로 충분하지 않을 수 있다.

```
public class HashTable implements Cloneable {
    private Entry[] buckets = ...;
    private static class Entry {
        final Object key;
        Object value;
        Entry next;
        Entry(Object key, Object value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
    ... //
    /* bucket의 각 원소가 원래 배열 원소와 동일한 연결 리스트를 참조하므로 잘못되었다. */
    @Override public HashTable clone(){
        try{
            HashTable result = (HashTable) super.clone();
            result.buckets = buckets.clone();
            return result;   
        } catch (CloneNotSupportedException e){
            throw new AssertionError();
        }
    }
}
```

```
public class HashTable implements Cloneable {
    private Entry[] buckets = ...;
    private static class Entry {
        final Object key;
        Object value;
        Entry next;
        Entry(Object key, Object value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
        // 이 Entry 객체가 첫 원소인 연결 리스트를 재귀적으로 복사.
        Entry deepCopy(){
            return new Entry(key, value, next == null ? null : next.deepCopy());
        }
    }
    ... //
    /* deepCopy를 수행한 clone.
       하지만, 리스트의 길이에 따라 stack Overflow가 발생할 수 있다. */
    @Override
    public HashTable clone() {
        try {
            HashTable result = (HashTable) super.clone();
            result.buckets = new Entry[buckets.length];
            for (int i = 0; i < buckets.length; i++) {
                if (buckets[i] != null)
                    result.buckets[i] = buckets[i].deepCopy();
            }
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
```

```
/* 이 Entry 객체가 첫 원소인 연결 리스트를 순환문으로 복사 */
Entry deepCopy(){
    Entry result = new Entry(key, value, next);
    for(Entry p = result ; p.next != null ; p = p.next)
        p.next = new Entry(p.next.key, p.next.value, p.next.next);
    return result;
}
```

- 복잡한 객체를 복제하려면,
1. super.clone 호출 결과로 반환된 객체의 모든 필드를 초기 상태로 되돌려 놓고,
2.  상위 레벨(higher-level) 메서드를 호출해서 객체 상태를 다시 만든다.

- 생성자와 마찬가지로, clone 메서드 역시 복사본의 비 파이널 클래스(재정의 가능 클래스)를 복사 중에 호출해서는 안 된다.
→ 따라서 그러한 메서드는 private 또는 final로 관리해야 한다.

- public clone 메서드는 CloneNotSupportedException을 반드시 생략해야 한다.

- 다중 스레드에 안전해야 하는 클래스를 Clonable로 만들기 위해서는 clone 메서드에 동기화 메커니즘을 적용해야 한다.

- 즉, Cloneable을 구현하는 모든 클래스는 반환값 자료형이 자기 자신인 public clone 메서드를 재정의해야 한다.

- 제대로 된 clone 메서드를 구현하는 것이 아니라면, 객체를 복사한 대안을 만들거나 아예 복제 기능을 제공하지 않는 것이 좋다.
→ 객체 복제를 지원하는 좋은 방법은, 복사 생성자나 복사 팩터리를 제공하는 것이다.

```
/* 복사 생성자와 복사 팩터리는 복잡한 규약에 얽매일 필요가 없고,
   final 용법과 충돌하지 않으며 불필요한 예외 검사도 필요 없고 형 변환도 필요 없다.
   또한, 해당 메서드가 정의된 클래스가 구현하는 인터페이스를 인자로 받을 수 있다. */
// 복사 생성자
public Copy(Copy copy);
// 복사 팩터리
public static Copy newInstance(Copy copy);
```

- 웬만하면 clone을 재정의하지 않고 쓰려는 사람들도 있다.
- 하지만, 계승 목적으로 클래스를 설계할 때라면, clone 재정의를 신중하게 생각해봐야 한다.