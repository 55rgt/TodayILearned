## Rule 05. 불필요한 객체 생성 방지
### 변경 불가능(Immutable) 객체
- 언제나 재사용할 수 있는 객체로, 기능적으로 동일한 객체는 필요할 때마다 만드는 것보다 낫기 때문에 사용한다.
```
/* 실행될 때마다 객체를 만드는 좋지 않은 예시 */
String s = new String("ChesterBennington");
/* 아래처럼 한다면, 실행할 때마다 동일한 객체를 사용한다. */
String s = "ChesterBennington";
```

- 생성자와 정적 팩터리 메서드를 함께 제공하는 변경 불가능 클래스의 경우에는, 생성자 대신 정적 팩터리 메서드를 이용하면 객체 생성이 불필요하게 생성되는 것을 피할 수도 있다.

- 변경 가능한 객체도 재사용할 수 있지만, 굳이 객체를 만들 필요가 없다면 정적 초기화 블록을 사용하면 좋다.
```
/* 비효율적인 코드: 아래 메서드는 호출될 때마다 모든 클래스가 공유할 수 있는
Calendar, TimeZone, Date 객체를 계속해서 만들어 낸다. */
public class Human{
    private final Date birthDate;

    public boolean isBabyBoomer(){

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        calendar.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
        Date boomStart = calendar.getTime();
        calendar.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
        Date boomEnd = calendar.getTime();

        return birthDate.compareTo(boomStart) >= 0
               && birthDate.compareTo(boomEnd) < 0;
    }
}
/* 정적 초기화 블록(Static initializer)을 통해 개선할 수 있다. */
public class Human{
    private final Date birthDate;
    private static final Date BOOM_START, BOOM_END;
    static{
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        calendar.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
        BOOM_START = calendar.getTime();
        calendar.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
        BOOM_END = calendar.getTime();
    }

    public boolean isBabyBoomer(){
        return birthDate.compareTo(BOOM_START) >= 0
               && birthDate.compareTo(BOOM_END) < 0;
    }
}
```
- 어떤 상황에서는 재사용 가능 여부가 불분명할 수도 있다.
ex) - 뷰(어댑터)

### 어댑터(adapter)
- 실제 기능은 후면 객체에 위임하고, 후면 객체에 대한 또다른 인터페이스를 제공하는 객체.
- JDK 1.5부터는 자동 객체화(autoboxing)를 통해서 객체를 만들 수도 있다.
 
### 자동 객체화(Autoboxing)
- 프로그래머들이 자바의 기본 자료형과 객체 표현형을 섞어 사용할 수 있게 해 줌.
- 객체 표현형 대신 기본 자료형을 사용하는 것이 훨씬 좋을 때가 있다.
```
/* 자동 객체화는 객체를 쓸데없이 만들 수 있다.
아래 코드는 for문을 수행할 때마다 새로운 객체가 만들어지므로 Long -> long으로 변경해야 한다. */
public static void main(String[] args){
    Long sum = 0L;
    for(long i = 0 ; i < Integer.MAX_VALUE ; i++) sum += i;
    System.out.println(sum);
}
```

- 하지만, 새로운 객체가 만들어져야 한다면 기존 객체는 재사용하지 말아야 할 때도 있다.(방어적 복사)