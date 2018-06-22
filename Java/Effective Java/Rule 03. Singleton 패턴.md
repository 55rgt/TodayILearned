## Rule 03. Singleton 패턴
### 싱글턴(Singleton)
- 객체를 하나만 만들 수 있는 클래스로, 보통 유일할 수밖에 없는 시스템 컴포넌트를 나타냄.
ex) Window manager, File system
- 하지만, 클래스를 싱글턴으로 만들면 클라이언트를 테스트하기가 어려워질 수 있다.

### [싱글턴을 구현하는 방법]
1. 생성자를 private, 싱글턴 객체는 static final 멤버를 통해 이용하는 방법
```
/* public final 필드를 이용한 싱글턴
private 생성자는 INSTANCE를 초기화할 때 한 번만 호출된다. */
public class SingletonFirst{
    public static final SingletonFirst INSTANCE = new SingletonFirst();
    private SingletonFirst(){ /*...*/ }
    public void methodSingleton(){ /*...*/ }
}
```

- AccessibleObject.setAccessible 메서드를 이용하여 클라이언트는 reflection 기능을 통해 private 생성자를 호출할 수 있으므로, 이런 종류의 호출도 막고 싶다면 두 번째 객체를 생성하라는 요청이 들어올 때 예외를 던지도록 생성자를 수정해야 한다.

```
import java.lang.reflect.Constructor;
/* Reflection과 setAccessible 메서드를 통해 private 클래스 생성자의 호출 권한 획득. */
public class PrivateAccessor{
    public static void main(String[] args) throws Exception{
        Constructor<?> constructor = Private.class.getDeclaredConstructors()[0];
        constructor.setAccessible(true);
        Private p = (Private) constructor.newInstance();
    }
}

class Private{
    private Private(){
        System.out.println("Private Constructor Called!");
    }
}
```

2. public으로 선언된 정적 팩터리 메서드를 이용하는 방법

```
/* 정적 팩터리를 이용한 싱글턴.
public 필드를 사용하면 선언만 보고서도 클래스가 싱글턴인지 알 수 있다. */
public class SingletonSecond{
    private static final SingletonSecond INSTANCE = new SingletonSecond();
    private SingletonSecond(){ /*...*/ }
    public static SingletonSecond getInstance(){ return INSTANCE; }
    public void methodSingleton(){ /*...*/ }
}
```

#### [팩터리 메서드 사용 시 장점]
1. API를 변경하지 않고도 싱글턴 패턴을 포기할 수 있다.
- 스레드마다 별도의 객체를 반환하도록 팩터리 메서드를 수정하는 것도 간단하다.
2. 제네릭 타입을 수용하기 쉽다.
- - - -
- 싱글턴 클래스를 직렬화 가능(Serializable) 클래스로 만들기 위해서는 implements Serializable을 추가하는 데 더하여 모든 필드를 transient로 선언하고 readResolve 메서드를 추가해야 한다.
- 그렇지 않으면, 직렬화된 객체가 역직렬화 될 때마다 새로운 객체가 생기게 된다.

```
/* 싱글턴 상태를 유지하기 위한 메서드 구현
동일한 객체가 반환되도록 하는 동시에, 가짜 객체는 gc가 처리하도록 만든다. */
private Object readResolve(){
   return INSTANCE;
}
```

- JDK 1.5 이후부터는 싱글턴을 구현할 때 원소가 하나뿐인 enum 자료형을 정의할 수 있다. 원소가 하나뿐인 enum 자료형은 싱글턴을 구현하는 가장 좋은 방법이다.

```
/* Enum 싱글턴을 이용하면, 간결할 뿐만 아니라 직렬화가 자동으로 처리된다.
즉, 직렬화가 복잡하더라도 여러 객체가 생길 일이 없고, 리플렉션에도 안전하다. */
public enum SingletonEnum{
    INSTANCE;
    public void methodSingleton(){/* ... */}
}
```
