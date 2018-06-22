## Rule 02. Builder 패턴 적용
### [정적 팩터리와 생성자의 문제점]
- 선택적 인자가 많은 상황에  잘 적응하지 못한다.

### [해결 방법]
1. 점층적 생성자 패턴(Telescoping Constructor Pattern) 적용
- 점층적 생성자 패턴: 필수 인자만 받는 생성자를 정의한 후, 선택적 인자를 받는 생성자를 쌓아 올리듯 추가하는 패턴.
```
class TeleScopingPattern{
    private final int servingSize;      // 필수
    private final int servings;         // 필수
    private final int calories;         // 선택
    private final int fat;              // 선택
    private final int sodium;           // 선택
    private final int carbohydrate;     // 선택

    public TelescopingPattern(int servingSize, int servings){
        this(servingSize, servings, 0);
    }
    public TelescopingPattern(int servingSize, int servings, int calories){
        this(servingSize, servings, calories, 0);
    }
    public TelescopingPattern(int servingSize, int servings, int calories, int fat){
        this(servingSize, servings, calories, fat, 0);
    }
    public TelescopingPattern(int servingSize, int servings, int calories, int fat, int sodium){
        this(servingSize, servings, calories, fat, sodium, 0);
    }
    public TelescopingPattern(int servingSize, int servings, int calories, int fat, int sodium, int carbohydrate) {
        this.servingSize = servingSize;
        this.servings = servings;
        this.calories = calories;
        this.fat = fat;
        this.sodium = sodium;
        this.carbohydrate = carbohydrate;
}
```
```
TelescopingPattern example = new TelescopingPattern(240, 10, 110, 3, 35, 30);
```

- 하지만, 이 방식은 설정할 필요가 없는 필드에도 인자를 전달해야 하는 경우가 생긴다.
- 점층적 생성자 패턴은 잘 동작하지만 인자 수가 늘어나면 클라이언트 코드를 작성하기가 어려워지고, 무엇보다 읽기 어려운 코드가 되고 만다.

2. 자바빈(JavaBeans) 패턴
- 인자 없는 생성자를 호출하여 객체부터 만든 다음, 설정 매서드를 호출하여 필수 필드 및 선택적 필드의 값까지 모두 채운다.
```
public class JavaBeansPattern {
    private int servingSize = -1;     // 필수
    private int servings = -1;        // 필수
    private int calories = 0;         // 선택
    private int fat = 0;              // 선택
    private int sodium = 0;           // 선택
    private int carbohydrate = 0;     // 선택
    public JavaBeansPattern(){ }
    public void setServingSize(int servingSize){ this.servingSize = servingSize; }
    public void setServings(int servings) { this.servings = servings; }
    public void setCalories(int calories) { this.calories = calories; }
    public void setFat(int fat) { this.fat = fat; }
    public void setSodium(int sodium) { this.sodium = sodium; }
    public void setCarbohydrate(int carbohydrate) { this.carbohydrate = carbohydrate; }
    public static void main(String[] args) {
        JavaBeansPattern a = new JavaBeansPattern();
        a.setServingSize(240);
        a.setServings(40);
        a.setCalories(30);
        a.setFat(35);
        a.setSodium(30);
        a.setCarbohydrate(500);
    }
}
```

- 하지만 자바빈 패턴에서는 1회의 함수 호출로 객체 생성을 끝낼 수 없으므로, 객체 일관성이 일시적으로 깨질 수 있다는 단점이 있다.
- 또한, 자바빈 패턴으로는 변경 불가능 클래스를 만들 수 없다.
- 스레드 안정성을 제공하기 위해 추가적으로 조치를 취할 것이 많아진다.

3. 빌더(Builder) 패턴
- 필요한 객체를 직접 생성하는 대신, 클라이언트는 먼저 필수 인자들을 생성자에 전달하여 빌더 객체를 만든다.
- 그 후, 빌더 객체에 정의된 설정 메서드들을 호출하여 선택적 인자들을 추가해 나간다.
- 마지막으로, 아무런 인자 없는 build 메서드를 호출하여 변경 불가능(immutable) 객체를 만든다.
```
/* BuilderPattern 객체는 변경 불가능하고, 모든 인자의 기본값이 한 곳에 모여 있다. */
public class BuilderPattern {

    private final int servingSize;      // 필수
    private final int servings;         // 필수
    private final int calories;         // 선택
    private final int fat;              // 선택
    private final int sodium;           // 선택
    private final int carbohydrate;     // 선택
    public static class Builder{

        private final int servingSize;
        private final int servings;   
        private int calories = 0;     
        private int fat = 0;          
        private int sodium = 0;       
        private int carbohydrate = 0; 

        public Builder(int servingSize, int servings){
            this.servingSize = servingSize;
            this.servings = servings;
        }
        public Builder calories(int val){
            calories = val; return this;
        }
        public Builder fat(int val){
            fat = val; return this;
        }
        public Builder sodium(int val){
            sodium = val; return this;
        }
        public Builder carbohydrate(int val){
            carbohydrate = val; return this;
        }
        public BuilderPattern build(){
            return new BuilderPattern(this);
        }
    }
    private BuilderPattern(Builder builder){
        servingSize = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }
}
```
```
BuilderPattern example
= new BuilderPattern.Builder(240, 8).calories(100).sodium(30).carbohydrate(10).build();
```

### [Builder 패턴의 장점]
1. 작성하기 쉽고, 가독성이 좋다.
2. 생성자와 마찬가지로, 인자에 불변식(invariant)을 적용할 수 있다.
- 불변식: 올바른 값인지 확인하는 조건
3. 빌더 객체는 여러 개의 varargs 인자를 받을 수 있다.
- 인자마다 별도의 설정 메서드를 사용하므로, 생성자 및 메서드와는 달리 필요한 만큼 많은 varargs를 사용할 수 있다.
4. 유연하다. (하나의 빌더 객체로 여러 객체를 만들 수 있다.)
5. 훌륭한 추상적 팩터리로 기능한다.
- 즉 클라이언트는 빌더를 어떤 메서드에 넘긴 다음, 해당 메서드가 클라이언트에게 하나 이상의 객체를 만들어 주도록 할 수 있다.
```
/* 자료형이 T인 객체에 대한 빌더 */
public interface Builder<T>{
    public T build();
}
```

- 추상적 팩터리인 class객체의 newInstance 메서드가 있지만, class.newInstance()는 컴파일 시점에 예외 검사가 가능해야 한다는 규칙을 깨뜨린다. 하지만 Builder 인터페이스에는 그런 문제가 없다.

### [Builder 패턴의 단점]
1. 객체를 생성하기 위해서는 먼저 빌더 객체를 생성해야 한다. (오버헤드의 문제)
2. 점층적 생성자 패턴보다 많은 코드를 요구하기 때문에 인자가 충분히 많은 상황에서 이용해야 한다.

#### 요약: Builder 패턴은 인자가 많은 생성자나 정적 팩터리가 필요한 클래스를 설계할 때,  특히 대부분의 인자가 선택적 인자인 상황에 유용하다.







