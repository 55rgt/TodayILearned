## Rule 09. equals를 재정의할 때는 반드시 hashCode도 재정의하라.
#### equals 메서드를 재정의하는 클래스는 반드시 hashCode 메서드도 재정의 해야 한다.
→ 그렇지 않으면 Object.hashCode 일반 규약을 어기므로 해시 기반 컬렉션과 함께 사용하면 오동작.

### [Object 클래스의 일반 규약]
1. 응용프로그램 실행 중 같은 객체의 hashCode를 여러 번 호출하는 경우, equals가 사용하는 정보들이 변경되지 않았다면 언제나 동일한 정수가 반환되어야 한다. 프로그램이 종료되었다가 다시 실행될 때 같은 값이 나올 필요는 없다.
2. equals(Object) 메서드가 같다고 판정한 두 객체의 hashCode 값은 같아야 한다. 
3. equals(Object) 메서드가 다르다고 판정한 두 객체의 hashCode 값은 꼭 다를 필요는 없다. 그러나 서로 다른 hashCode 값이 나오면 해시 태이블 성능이 향상될 수 있다.
- - - -
hashCode를 재정의하지 않으면 2번 규약이 위반될 수 있다. 

```
/* hashCode 메서드가 없기 때문에 문제가 발생한다. */
public final class PhoneNumber{

    private final short areaCode;
    private final short prefix;
    private final short lineNumber;

    public PhoneNumber(int areaCode, int prefix, int lineNumber){
        rangeCheck(areaCode, 999, "area code");
        rangeCheck(prefix, 999, "prefix");
        rangeCheck(lineNumber, 9999, "line number");

        this.areaCode = (short) areaCode;
        this.prefix = (short) prefix;
        this.lineNumber = (short) lineNumber;
    }

    private static void rangeCheck(int arg, int max, String name){
        if(arg < 0 || arg > max)
            throw new IllegalArgumentException(name + ": " + arg);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!(obj instanceof PhoneNumber)) return false;
        PhoneNumber phoneNumber = (PhoneNumber) obj;

        return
                phoneNumber.lineNumber == lineNumber
                && phoneNumber.prefix == prefix
                && phoneNumber.areaCode == areaCode;
    }
```

```
Map<PhoneNumber, String> map = new HashMap<>();
map.put(new PhoneNumber(031, 999, 2939), "Chester");

/* 아래 코드는 hashCode 메서드를 재정의하지 않았기 때문에 기대한 결과가 나타나지 않는다.
   put과 get에 사용된 객체는 각각 다른 해시 코드를 가지고 있다. */
map.get(new PhoneNumber(031, 999, 2939)); // null이 반환된다.
```

- 따라서, 적절한 hashCode를 구현해야 한다.

```
/* 잘못된 hashCode 구현 예 */
@Override
public int hashCode() { return 24; }
```

- 위 코드는 같은 객체는 같은 코드를 갖지만, 모든 객체가 같은 해시 코드를 가지므로 해시 테이블이 연결 리스트가 되어 버려서 복잡도가 제곱에 비례하게 수렴한다. 따라서 프로그램 성능 저하를 유도한다.
- - - -
#### 다른 객체에는 다른 해시 코드를 반환하는 것이 좋다.
1. 0이 아닌 상수를 result라는 이름의 int 변수에 저장한다.
2. 객체 안에 있는 모든 중요 필드 f(equals 메서드가 사용하는 필드)에 대해서 아래 절차를 시행한다.
    1) 해당 필드에 대한 int 해시 코드 c를 계산한다.
         i. 필드가 boolean이면 (f ? 1:0)을 계산한다.
         ii. 필드가 byte, char, short, int 중 하나이면 (int) f를 계산한다.
         iii. 필드가 long이면 (int)(f^f(>>>32))를 계산한다.
         iv. 필드가 float이면 Float.floatToIntBits(f)를 계산한다.
         v. 필드가 double이면 Double.doubleToLongBits(f)를 계산하고,
              그 결과로 얻은 long 값을 iii.에 따라 해시 코드로 변환한다.
         vi. 필드가 객체 참조이고 equals 메서드가 해당 필드의 equals 메서드를 재귀적으로 호출하면
               해당 필드의 hashCode 메서드를 재귀적으로 호출하여 해시 코드를 계산한다.
               필드 값이 null인 경우에는 0을 반환한다.
         vii. 필드가 배열이라면 배열의 각 원소가 별도 필드인 것처럼 계산하면 된다.

    2) 절차 1)에서 계산된 해시 코드 c를 다음과 같이 결합한다.
```
result = 31 * result + c; // 소수이면서 홀수인 숫자를 사용하면 좋다.
```

3. result를 반환한다.
4. hashCode 구현이 끝났다면, 동치 관계에 있는 객체의 해시 코드 값이 똑같이 계산되는지 점검한다. 그 후, unit test를 시행한다.
- 중복 필드는 해시 코드 계산에서 제외해도 되고, equals 계산 과정에서 안 쓰이는 필드는 반드시 제외한다.
```
@Override public int hashCode() {
    int result = 17;
    result = 31 * result + areaCode;
    result = 31 * result + prefix;
    result = 31 * result + lineNumber;
    return result;
}
```

- 해시 코드 계산 비용이 높은 변경 불가능 클래스를 만들 때는, 객체 안에 해시 코드를 캐시해 두어야 할 수도 있다.
```
/* 초기화 지연 기법을 사용한 해시 코드 캐싱 */
private volatile int hashCode;
    @Override
    public int hashCode() {
        int result = hashCode;
        if(result == 0){
            result = 17;
            result = 31 * result + areaCode;
            result = 31 * result + prefix;
            result = 31 * result + lineNumber;
            hashCode = result;
        }
        return result;
    }
```

- 해시 코드를 계산할 때 객체의 중요 부분을 해시 코드 계산 과정에서 생략하면 안 된다는 것을 유념해야 한다.