## Rule 04. private 생성자 사용
### [정적 메서드나 필드만 모은 클래스들의 장점]

1. 자바의 기본 자료형 값(primitive value)이나 배열에 적용되는 메서드를 한군데 모아둘 때 유용함.
ex) java.lang.Math / java.util.Arrays
2. 특정 인터페이스를 구현하는 객체를 만드는 팩터리 메서드 등의 정적 메서드를 모을 때도 사용.
ex) java.util.Collections
3. final 클래스에 적용할 메서드들을 모아놓을 때도 활용 가능.

- - - -
- 이러한 클래스들은 객체를 생성하기 위한 클래스가 아니므로 객체 생성을 방지해야 한다.
```
/* 객체를 만들 수 없는 클래스. abstract로 클래스를 선언하는 것은
   자식 클래스가 객체 생성을 할 수 있기 때문에 객체 생성 방지에 부적합하다. */
public class UtilClass{
    /* 기본 생성자 자동 생성 방지 */
    private UtilClass(){ throw new AssertionError(); }
}
```