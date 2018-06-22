## Rule 08. equals를 재정의할 때에는 일반 규약을 따르라.
```
/* Object의 equals 메서드는 동일성(identity) 검사를 수행한다.
따라서 동등성(equality) 검사를 하기 위해서는 재정의한 후 사용해야 한다. */
public boolean equals(Object obj) {
        return (this == obj);
    }
```

### [equals 메서드를 재정의하지 않는 경우]
1. 각각의 개체가 고유한 경우
- 값 대신 활성 개체를 나타내는 클래스는 Object의 equals 메서드를 그대로 사용해도 된다.
ex) Thread
2. 클래스에 논리적 동일성 검사가 없어도 되는 경우
- 클래스 내에서 equals 기능이 없어도 되는 경우에는 따로 재정의하지 않아도 된다.
ex) java.util.Random 클래스는 굳이 두 Random 객체가 같은 난수열을 만드는지 검사하지 않아도 됨.
3. 상위 클래스에서 재정의한 equals를 하위 클래스에서 사용해도 되는 경우
ex) AbstractSet → Set / AbstractList → List / AbstractMap → Map
4. 클래스가 private 또는 package-private로 선언되었고, equals를 호출할 일이 없는 경우
- 실수로 equals를 호출할 수 있기 때문에 equals를 재정의해서 에러를 던지는 것이 더 좋을 수 있다.
- 
```
@Override public Boolean equals(Object o){
    throw new AssertionError(); // 호출되면 안 되는 메서드를 호출.
}
```

### [Object.equals 를 재정의하는 것이 바람직한 경우]
1. 객체 동일성(object equality)가 아닌 논리적 동일성(logical equality)의 개념을 지원하는 클래스
2. 상위 클래스의 equals가 하위 클래스의 필요를 충족하지 못할 때
→ 객체를 Map의 키(key)나 Set의 요소로 사용할 수 있게 된다.
ex) 값 클래스(Value Class)
- 값 객체를 비교할 때는 동일한 객체를 보는 것이 아니라 같은 값을 나타내는지를 알고 싶으므로 equals 메서드를 재정의해야 한다.
- 물론, equals 메서드를 재정의할 필요가 없는 값 클래스도 있다.
→ 개체 통제 기능을 사용해 값마다 최대 하나의 객체만 존재하도록 제한하는 클래스. ex) 열거 자료형
→ 이런 클래스는 Object의 equals 만 사용해도 논리적 동일성을 검사할 수 있다.

### [equals 메서드를 정의할 때 준수해야 하는 일반 규약]
- equals 메서드는 동치 관계(equivalence relation)를 구현한다.
1. 반사성(reflexive)
- null이 아닌 참조 x가 있을 때, x.equals(x)는 true를 반환한다.
2. 대칭성(symmetric)
- null이 아닌 참조 x와 y가 있을 때, x.equals(y)는 y.equals(x) = true일 때만 true를 반환한다.

```
/* 대칭성 위반 예시 */
public final class CaseInsensitiveString {
    private final String str;
    public CaseInsensitiveString(String str){
        if(str == null) throw new NullPointerException();
        this.str = str ;
    }
    //대칭성 위반
    @Override public boolean equals(Object o){
        if(o instanceof CaseInsensitiveString)
            return str.equalsIgnoreCase(((CaseInsensitiveString)o).str);
        if(o instanceof String)
            return str.equalsIgnoreCase((String) o); // 한 방향으로만 정상 동작!
        return false;
    }
    ...
}
```
```
/* 위 코드로 s.equals(cis) 와 cis.equals(s) 를 수행하면 다른 결과가 나온다.
   → 대칭성 파괴 */
CaseInsensitive cis = new CaseInsensitiveString("Korea");
String s = "korea";

List<CaseInsensitiveString> list = new ArrayList<CaseInsensitiveString>();
list.add(cis);

list.contains(s); // 어떠한 결과가 나올지 예측할 수 없다.
```
```
/* CaseInsensitive의 equals 메서드가 String 객체와 상호작용하지 않도록 함. */
@Override public boolean equals(Object o){
    return o instanceof CaseInsensitiveString &&
            ((CaseInsensitiveString) o).s.equalsIgnoreCase(s);
}
```

3. 추이성(transitive)
- null 아닌 참조 x, y, z가 있을 때 x.equals(y) = true, y.equals(z) = true 이면 x.equals(z) = true이다.
```
/* 2차원 공간상의 점을 나타내는 변경 불가능(immutable) 클래스 예시. */
public class Point {
     final int x;
     final int y;
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
    @Override public boolean equals(Object o){
        if(!(o instanceof Point)) return false;
        Point p = (Point) o;
        return p.x == x && p.y == y;
    }
}
```
```
/* Point 클래스를 계승한 클래스 */
class ColorPoint extends Point{
    private final Color color;
    public ColorPoint(int x, int y, Color color){
        super(x, y);
        this.color = color;
    }
}

// 대칭성 위반
@Override public boolean equals(Object o) {
    if(!(o instanceof ColorPoint)) return false;
    return super.equals(o) && ((ColorPoint) o).color == color;
}
```
```
Point p = new Point(3, 5);
ColorPoint cp = new ColorPoint(3, 5, Color.CYAN);

/* 위 코드로 아래 코드를 수행하면 다음과 같은 결과가 나타난다. */
p.equals(cp); // true
cp.equals(p); // false
```
```
/* 그렇다고 Point와 ColorPoint 비교 시 색상 정보를 무시할 수도 없다. */
// 추이성 위반
@Override public boolean equals(Object o){
    if (!(o instanceof Point)) return false;
    // o가 Point 객체이면 색상은 비교되지 않는다.
    if (!(o instanceof ColorPoint)) return o.equals(this);
    // o가 ColorPoint이므로 모든 정보를 비교한다.
    return super.equals(o) && ((ColorPoint) o).color == color;
}
```
```
ColorPoint p1 = new ColorPoint(5, 17, Color.BLUE);
Point p2 = new Point(5, 17);
ColorPoint p3 = new ColorPoint( 5, 17, Color.GREEN);

/* 위 코드로 아래 코드를 수행하면 다음과 같은 결과가 나타난다. */
p1.equals(p2) // true
p2.equals(p3) // true
p1.equals(p3) // false -> 추이성 위반
```

- 위 클래스 문제는 객체 지향 언어에서 동치 관계를 구현할 때 발생하는 본질적 문제이다. 즉, 객체 생성 가능 클래스를 계승하여 새로운 값 컴포넌트를 추가하면서 equals 를 어기지 않을 방법은 없다.

```
/* 단위 원 상의 모든 점(정수로 구성)을 포함하도록 초기화 */
private static final Set<Point> unitCircle;
static {
    unitCircle = new HashSet<Point>();
    unitCircle.add(new Point(1, 0));
    unitCircle.add(new Point(-1, 0));
    unitCircle.add(new Point(0, 1));
    unitCircle.add(new Point(0, -1));
}

public static boolean onUnitCircle(Point p){
    return unitCircle.contains(p);
}

// Liskov Substitution Principle 위반
@Override public boolean equals(Object o){
    if (o == null || o.getClass() != getClass()) return false;
    Point p = (Point) o;
    return p.x == x && p.y == y;
}
```
```
public class CountPoint extends Point {
    private static final AtomicInteger counter = new AtomicInteger();
    public CounterPoint(int x, int y) {
        super(x, y);
        counter.incrementAndGet();
    }
}

/*
리스코프 대체 원칙: 어떤 자료형의 중요한 속성은 하위 자료형에도 그대로 유지되므로
그 자료형에 대한 메서드는 하위 자료형에도 잘 동작해야 한다는 원칙.
CounterPoint 객체를 onUnitCirCle의 메서드의 인자로 넘길 때, equals가 getClass를 사용한다면
onUnitCircle은 객체의 x, y 값에 상관없이 false를 반환할 것이다.
*/
```
```
/* 계승이 아닌 구성을 통해 equals 규약을 위반하지 않으면서 값 컴포넌트 추가 예제 */
public class ColorPoint {
    private final Point point;
    private final Color color;
    public ColorPoint(int x, int y, Color color) {
        if (color == null) throw new NullPointerException();
        point = new Point(x, y);
        this.color = color;
    }
    // ColorPoint의 Point 뷰 변환
    public Point asPoint() {
        return point;
    }

    @Override public boolean equals(Object o){
        if(!(o instanceof ColorPoint)) return false;
        ColorPoint cp = (ColorPoint) o;
        return cp.point.equals(point) && cp.color.equals(color);
    }
}
```

4. 일관성(consistency)
- null이 아닌 참조 x, y가 있을 때, equals를 통해 비교되는 정보에 아무 변화가 없다면 x.equals(y) 호출 결과는 호출 횟수에 상관없이 항상 같아야 한다.
- 신뢰성이 보장되지 않는 자원들을 비교하는 equals 구현은 하지 않아야 한다.
ex) java.net.URL의 equals 메서드

5. null이 아닌 참조 x에 대해서 x.equals(null)은 항상 false이다.
- 상당수의 클래스는 equals 안에서 null 조건을 명시적으로 검사해서 NullPointerException 예외를 발생하지 않도록 한다.
```
/* 불필요한 null 검사 예시 */
@Override public boolean equals(Object o) {
     if( o == null ) return false;
    ...
}
```
```
/* 인자와의 비교를 위해 equals 메서드는 먼저 인자를 형변환해야 하므로,
   instanceof 연산자에서 o가 null이라면 무조건 false를 반환한다. */
@Override public boolean equals(Object o) {
    if( !(o instanceof ObjectType)) return false;
    ObjectType ot = (ObjectType) o;
    ...
}
```

### [equals 메서드를 구현하기 위해 따라야 할 지침]
1. == 연산자를 사용하여 equals의 인자가 자기 자신인지 검사하기
- 만약 그렇다면 true를 반환한다. (굳이 검사를 할 필요가 없다는 얘기)
→ 성능 최적화를 위한 것으로, 객체 비교 오버헤드가 클 경우에 좋다.
2. instanceof 연산자를 사용하여 인자의 자료형이 정확한지 검사하기
- 그렇지 않다면 false를 반환한다.
3. equals의 인자를 정확한 자료형으로 반환하기
→ 2번으로 인해 형 변환은 항상 성공한다.
4. 필드 각각이 인자로 주어진 객체의 해당 필드와 일치하는지 검사하기
- 모두 일치하면 true를 반환한다.
    a) float나 double 이외의 기본 자료형은 == 연산자로 비교하면 된다.
    b) 객체 참조 필드는 equals 메서드를 재귀적으로 호출하여 검사한다.
    c) float는 Float.compare, double은 Double.compare 이용한다.
    d) 객체 참조 필드 가운데 null이 허용된다면, 아래를 이용한다.
```
(field == null ? o.field == null : field.equals(o.field))
```

    e) field와 o.field가 같을 때가 많다면, 아래와 같이 하면 더 빠르다.
```
(field == o.field || (field != null && field.equals(o.field)))
```

    f) 필드를 비교하기가 까다로운 클래스는 불완전일치 비교 대신 비용이 저렴한 완전일치 비교할 수 있는 대표 형태(canonical form)로 필드를 변환해서 저장한 다음, 대표 형태끼리 비교할 수도 있다.
→ 대체로 변경 불가능 클래스에 적합하다.
    (변경 가능 객체라면 대표 형태도 상태 변화에 맞춰 갱신해야 한다.)
    g) equals 메서드의 성능은 필드 비교 순서에도 영향을 받을 수 있다.
→ 다를 가능성이 가장 높거나 비교 비용이 낮은 필드부터 비교하면 좋다.

5. equals 메서드 구현을 끝냈다면, 대칭성, 추이성, 일관성의 세 속성이 만족되는지 검토하기.
→ 추가적으로 단위 테스트로 검사하라. 또한, 반사성과 null에 대한 비일치성도 검토하면 된다.

6. equals를 구현할 때에는 hashCode도 재정의하기.
7. 동치성에 너무 집착하지 마라.
8. equals 메서드의 인자 형을 Object에서 다른 것으로 바꾸면 안 된다.
```
/* Object.equals()를 재정의하지 않는 예시. 아래 메서드는 오버로딩이다.
   @Override를 사용하여 이런 실수를 피한다. */
public boolean equals(MyClass o){

}
```




