## Rule 01. 생성자 대신 정적 팩터리 메서드 사용
### [클래스를 통해 객체를 만드는 방법]
1. public으로 선언된 생성자를 이용한다.
2. 클래스에 public으로 선언된 정적 팩터리 메서드를 추가한다.
```
/* 정적 팩터리 메서드 사용 예시 */
public static Boolean valueOf(boolean b){
    return b ? Boolean.TRUE : Boolean.FALSE;
}
```

### [정적 팩터리 메서드의 장점]
1. 생성자와는 달리 정적 팩터리 메서드에는 이름이 있다.
- 생성자에 전달되는 인자들은 어떤 객체가 생성되는지 설명하지 못한다.

```
/* 생성자 사용 예시 */
public BigInteger(int bitLength, int certainty, Random rnd) {
    BigInteger prime;
    if (bitLength < 2)
        throw new ArithmeticException("bitLength < 2");
    prime = (bitLength < SMALL_PRIME_THRESHOLD ? smallPrime(bitLength, certainty, rnd) : largePrime(bitLength, certainty, rnd));
    signum = 1;
    mag = prime.mag;

} 
```

- 반면, 정적 팩터리 메서드는 이름을 잘 짓는다면 가독성이 높고, 사용하기에도 용이하다.

```
/* 정적 팩터리 메서드 사용 예시 */
public static BigInteger probablePrime(int bitLength, Random rnd) {
        if (bitLength < 2)
            throw new ArithmeticException("bitLength < 2");

        return (bitLength < SMALL_PRIME_THRESHOLD ?
                smallPrime(bitLength, DEFAULT_PRIME_CERTAINTY, rnd) :
                largePrime(bitLength, DEFAULT_PRIME_CERTAINTY, rnd));
    }
```

- 클래스에는 시그너쳐(signature)별로 하나의 생성자만 넣을 수 있다.

```
/* 아래와 같이 동일한 시그너쳐를 갖는 생성자를 여러 개 만들 수 없다. */
Shape(Vector2D size, Color color){
        this.size = size;
        this.color = color;
        start = new Vector2D(0, 0);
    }

Shape(Vector2D start, Color color){
        this.color = color;
        this.start = start;
        size = new Vector2D(30, 30);
    }
```

- 이를 해결하는 방법은 인자의 순서를 바꾸는 것인데, 이는 사용자가 각각의 생성자 용도를 알 수 없을 뿐더러 생성자를 호출할 때 실수를 유발할 수도 있다. 또한 생성자를 사용할 때마다 설명서를 봐야 하는 번거로움이 생긴다.

```
/* 아래와 같이 인자의 순서를 바꾸면 문제가 해결은 되지만, 사용자가 사용하기에는 번거롭다. */
Shape(Vector2D size, Color color){
        this.size = size;
        this.color = color;
        start = new Vector2D(0, 0);
    }

Shape(Color color, Vector2D start){
        this.color = color;
        this.start = start;
        size = new Vector2D(30, 30);
    }

```

- 하지만, 정적 팩터리 메서드를 사용할 때에는 메서드의 이름이 있으므로 그런 문제가 발생하지 않는다.
```
/* 생성자들을 정적 팩터리 메서드에서 호출하게 만들면, 사용자들은 정적 팩터리 메서드의 이름만 보고
객체를 생성할 수 있다. (작명에 주의!) */
static Shape newRectWithSizeVector2D(Vector2D size, Color color){
        return new Shape(size, color);
    }

static Shape newRectWithStartVector2D(Vector2D start, Color color){
        return new Shape(color, start);
    }
```

2. 생성자와는 달리 호출할 때마다 새로운 객체를 생성할 필요가 없다.
- 변경 불가능 클래스라면 이미 만들어 둔 객체를 활용할 수 있고, 만든 객체를 캐시(cache)한 후에 재사용하여 같은 객체를 불필요하게 다시 생성하지 않게끔 할 수 있다.
- 캐시(cache): 자주 액세스하는 데이터나 프로그램 명령을 반복해서 검색하지 않고도 즉각 사용할 수 있도록 저장해두는 영역.

```
/* Boolean.valueOf 메서드는 결코 객체를 생성하지 않는 좋은 예제이다. */
public static Boolean valueOf(boolean b){
        return b ? Boolean.TRUE : Boolean.FALSE;
    }
```

- 이 기법은 경량 패턴(Flyweight Pattern)과 유사하다.
- 경량 패턴(Flyweight Pattern): 리소스를 관리할 때, 효율적으로 유사한 객체들에 대해서 공유를 제공하여 메모리 사용을 줄이는 설계 방식.
- 따라서, 동일한 객체가 요청되는 일이 빈번하거나 객체를 만드는 비용이 클 때 적용하면 성능 개선에 큰 도움이 된다.
- 정적 팩터리 메서드를 사용하면 같은 객체를 반복해서 반환할 수 있기 때문에 어느 시점에 어떤 객체가 얼마나 존재하는지를 정밀하게 통제할 수 있다. (개체 통제 클래스)

#### [참고. 개체 통제 클래스의 장점]
- 싱글턴 패턴을 따르도록 할 수 있다. (특정 클래스의 객체가 오직 하나만 존재하도록 보장)
- 객체 생성이 불가능한 클래스를 만들 수 있다.
- 변경이 불가능한 클래스는 두 개의 같은 객체가 존재하지 못하게 만들 수 있다. (즉, a.equals(b)가 아닌 a == b를 사용하여 비교하므로 성능을 향상시킨다.)

3. 생성자와는 달리 반환값 자료형의 하위 자료형 객체를 반환할 수 있다.
```
/* Rect가 Shape를 상속받는 자식 클래스일 경우, 부모(Shape) 클래스의 정적 팩터리 메서드에서
자식(Rect) 객체를 반환할 수 있다. */
static Shape newRectWithSizeVector2D(Vector2D size, Color color){
        return new Rect(size, color);
   }
```

- 이는 반환되는 객체의 클래스를 훨씬 유연하게 결정할 수 있고, public으로 선언되지 않은 클래스의 객체를 반환할 수 있다.
- 이 기법은 API 규모와 개념적인 무게감을 축소할 수 있으므로 인터페이스 기반 프레임워크 구현에 적합하다.

4. 형인자 자료형 객체를 만들 때 편하다.
- 이런 클래스의 생성자를 호출할 때는, 반드시 인자로 형인자를 전달해야 한다.
```
/* 자료형 명세를 중복하면, 형인자가 늘어남에 따라 길고 복잡한 코드가 만들어진다. */
Map<String, List<String>> m = new HashMap<String, List<String>>();
```

- 하지만 정적 팩터리 메서드를 사용하면 자료형 유추를 할 수 있다.
- 자료형 유추: 컴파일러가 형인자를 스스로 알아낼 수 있도록 하는 기법.
```
/* 제네릭 정적 팩터리 메서드를 가지고 있다고 할 때, 앞선 선언문을 조금 더 간결히 작성할 수 있다. */
public static <K, V> HashMap<K, V> newInstance(){
    return new HashMap<K, V>();
}

Map<String, List<String>> m = HashMap.newInstance();

/* 하지만, 자바 1.7부터는 생성자를 호출할 때도 자료형을 사용할 수 있기 때문에,
위의 정적 팩터리 메서드를 추가할 필요는 없어졌다. */
Map<String, List<String>> myMap = new HashMap<>();
/* <> : diamond operator -> 이를 생략하면 무점검 형변환 경고가 발생한다. */
```

### [정적 팩터리 메서드의 단점]
1. 정적 팩터리 메서드만 있는 클래스를 만들면 public이나 protected로 선언된 생성자가 없으므로 하위 클래스를 만들 수 없다.
- 하지만, inheritance 대신 composition 기법을 쓰는 측면에서는 좋을 수 있다.
2. 정적 팩터리 메서드가 다른 정적 메서드와 확연히 구분되지 않는다.











