## 함수 호출과 this

### 1. arguments 객체

자바스크립트는 다른 엄격한 단어들과 달리 함수를 호출할 때 함수 형식에 맞춰 인자를 넘기지 않더라도 에러가 발생하지 않는다.

```javascript 1.8
function func(arg1, arg2){
    console.log(arg1, arg2);
}
    
func();             // undefined undefined 
func(1);            // 1 undefined
func(1,2);          // 1 2
func(1,2,3);        // 1 2
```
넘기지 않는 인자에 대해서는 **undefined** 값이 할당된다.

정의된 인자 개수보다 많게 함수를 호출했을 경우에는 에러가 발생하지 않고, 초과된 인수는 무시된다.

이러한 자바스크립트의 특성 때문에, **arguments** 객체를 통해 런타임 시에 호출된 인자의 개수를 확인하고 이에 따라 동작을 다르게 할 수 있다.

→ 자바스크립트에서는 함수를 호출할 때 인수들과 함께 암묵적으로 arguments 객체가 함수 내부로 전달되기 때문

→ arguments 객체는 넘긴 인자들이 배열 형태로 지정된 (실제 배열이 아닌) **유사 배열 객체**이다.

```javascript 1.8
function add(a,b){
    
    console.dir(arguments);
    return a + b;
}
    
console.log(add(1));            // NaN
console.log(add(1,2));          // 3
console.log(add(1,2,3));        // 3
```

**arguments** 객체는 세 부분으로 구성되어 있다.

- 함수를 호출할 때 넘겨진 인자 (배열 형태)
- length 프로퍼티: 호출할 때 넘겨진 인자의 개수
- callee 프로퍼티: 현재 실행 중인 함수의 참조값

arguments 객체는 매개변수 개수가 정확하게 정해지지 않은 함수를 구현하거나,
전달된 인자의 개수에 따라 서로 다른 처리를 해 줘야 하는 함수를 개발하는 데 유용하게 사용될 수 있다.

```javascript 1.8
function sum(){
        
    var result = 0;
        
    for (var i = 0  ; i < arguments.length ; i++) result += arguments[i];
    
    return result;
}
    
console.log(sum(1,2,3));                    // 6
console.log(sum(1,2,3,4,5,6,7,8,9));        // 45
```

### 2. 호출 패턴과 this 바인딩

자바스크립트에서는 함수를 호출할 때 기존 매개변수로 전달되는 인자값에 더해, **arguments 객체**와 **this 인자**가 함수 내부로 암묵적으로 전달된다.

자바스크립트의 여러 가지 **함수가 호출되는 방식**(**호출 패턴**)에 따라 this 는 **다른 객체를 참조**(**this binding**)한다.

#### 객체의 메서드를 호출할 때 this 바인딩

객체의 메서드를 호출할 때, 메서도 내부에서 사용된 this는 **해당 메서드를 호출한 객체로 바인딩**된다.

```javascript 1.8

var myObj = {
    
    name: 'foo',
    sayName: function(){
        console.log(this.name);
    }
};
    
var otherObj = {
    name: 'bar'
};
    
otherObj.sayName = myObj.sayName;
    
/** sayName()에서 사용된 this는 자신을 호출한 객체에 바인딩된다. */
myObj.sayName();        // foo
otherObj.sayName();     // bar
```
#### 함수를 호출할 때 this 바인딩

자바스크립트는 함수를 호출하면, 해당 함수 내부에서 사용되는 **this는 전역 객체에 바인딩**된다.

브라우저에서 자바스크립트를 실행하는 경우 전역 객체는 **window 객체**가 된다.

```javascript 1.8
var foo = "I'm foo";
    
console.log(foo);           // I'm foo
console.log(window.foo);    // I'm foo
```
전역 변수는 전역 객체(window)의 프로퍼티로도 접근 가능하다.
```javascript 1.8
var test = 'This is test';
console.log(window.test);       // This is test
    
var sayFoo = function(){
    console.log(this.test);     // This is test
}
    
sayFoo();
```
하지만 이러한 함수 호출에서의 this 바인딩 특성은 **내부 함수를 호출했을 경우**에도 그대로 적용된다.

```javascript 1.8
var value = 100;
    
var myObject = {
    
    value: 1,
    func1: function() {
            
        this.value += 1;
        console.log('func1() called. this.value: ' + this.value);
        
        func2 = function() {
                
            this.value += 1;
            console.log('func2() called. this.value: ' + this.value);
            
            func3 = function(){
                    
                this.value += 1;
                console.log('func2() called. this.value: ' + this.value);   
            }
                
            func3();
        }
            
        func2();
    }
};

myObject.func1();

/*
    func1() called. this.value: 2
    func2() called. this.value: 101
    func3() called. this.value: 102
 */
```

자바스크립트에서는 **내부 함수 호출 패턴**을 정의해 놓지 않기 때문에, 1번에서 this가 해당 메서드를 호출한 객체로 바인딩이 된 것에 반해
2, 3번에서는 내부 함수의 this는 전역 객체(window)에 바인딩된다.

이러한 점을 해결하는 방법으로는 **부모 함수의 this**를 내부 함수가 접근 가능한 **다른 변수에 저장**하는 방법이 있다.

```javascript 1.8
/** 내부 함수의 this 바인딩 문제를 해결한 예제 코드 */
    
// 내부 함수 this 바인딩
var value = 100;
    
var myObject = {
    
    value: 1,
    func1: function() {
        
        var that = this;
            
        this.value += 1;
        console.log('func1() called. this.value: ' + this.value);
        
        func2 = function() {
                
            that.value += 1;
            console.log('func2() called. this.value: ' + that.value);
            
            func3 = function(){
                    
                that.value += 1;
                console.log('func2() called. this.value: ' + that.value);   
            }
                
            func3();
        }
            
        func2();
    }
};

myObject.func1();

/*
    func1() called. this.value: 2
    func2() called. this.value: 3
    func3() called. this.value: 4
 */

``` 

#### 생성자 함수를 호출할 때 this 바인딩

자바스크립트의 생성자 함수는 다른 객체지향 언어에서의 생성자 함수의 형식과는 달리 형식이 정해져 있지 않고, 
**기존 함수에 new 연산자를 붙여서 호출하면 해당 함수는 생성자 함수로 동작한다.**

→ 자바스크립트에서는 특정 함수가 생성자 함수로 정의되어 있는 것을 알리기 위해 **함수 이름의 첫 문자를 대문자로 쓰기**를 권장한다.

자바스크립트에서는 생성자 함수를 호출할 때, 생성자 함수 코드 내부에서 this는 메서드와 함수 호출 방식에서의 this 바인딩과는 다르게 동작한다.

**<생성자 함수가 동작하는 방식>**

new 연산자로 자바스크립트 함수를 생성자로 호출하면, 아래와 같은 순서로 동작한다.

- 빈 객체 생성 및 this 바인딩
    - 생성자 함수 코드가 실행되기 전에 **빈 객체**가 생성된다. 이 객체가 생성자 함수가 새로 생성하는 객체이며, this로 바인딩된다.
    - 따라서 이후 생성자 함수의 코드 내부에서 사용된 this는 이 빈 객체를 가리킨다.
    - 하지만, 자바스크립트의 모든 객체는 자신의 부모인 프로토타입 객체와 연결되어 있으므로, 생성자 함수가 생성한 비어 보이는 객체는**자신을 생성한
    생성자 함수의 prototype 프로퍼티가 가리키는 객체를 자신의 프로토타입 객체**로 설정한다.

- this를 통한 프로퍼티 생성
    - 이후, 함수 코드 내부에서 this를 사용하여 앞에서 생성된 빈 객체에 동적으로 프로퍼티나 메서드를 생성할 수 있다.

- 생성된 객체 리턴
    - 특별히 리턴문이 없을 경우, **this로 바인딩된 새로 생성한 객체**가 리턴된다.
    - 리턴값이 새로 생성한 객체(this)가 아닌 다른 객체를 반환하는 경우는, 생성자 함수를 호출했다고 해도 this가 아닌 해당 객체가 리턴된다.
```javascript 1.8
var Person = function(name){
    // 빈 객체 생성, this 바인딩
    this.name = name;
    // 함수 리턴
};

var foo = new Person('foo');
console.log(foo.name);          // foo
```

####<객체 리터럴 방식과 생성자 함수를 통한 객체 생성 방식의 차이>

```javascript 1.8

var foo = {
    
    name: 'foo',
    age: 35,
    gender: 'man'
    
};
console.dir(foo);
    
function Person(name, age, gender, position){
    
    this.name = name;
    this.age = age;
    this.gender = gender;
}
    
var bar = new Person('bar', 32, 'woman');
console.dir(bar);
    
var baz = new Person('baz', 22, 'woman');
console.dir(baz);

```
- 객체 리터럴 방식으로 생성된 객체는 같은 형태의 객체를 재생성할 수 없다. 반면, 생성자 함수를 사용해서 객체를 생성한다면, 같은 형태의 서로 다른 객체들을 생성할 수 있다.
- 또한, console.dir()를 이용한 객체의 출력 결과도 차이가 있다.
    - 객체 리터럴과 생성자 함수 방식의 차이가 프로토타입 객체__proto__ 프로퍼티에 있다.
    - 객체 리터럴 방식은 자신의 프로토타입 객체가 **Object**(Object.prototype)이고,
    - 생성자 함수 방식의 경우는 **Person**(Person.prototype)이다.
    
    → 이러한 차이가 발생하는 이유는 자바 스크립트 객체가 자신의 생성한 **생성자 함수의 prototype 프로퍼티**가 가리키는 객체를
    자신의 **프로토타입 객체**로 설정하기 때문이다.
    → 객체 리터럴 방식에서는 객체 생성자 함수가 **Object()**이고, 생성자 함수 방식에서는 **생성자 함수 자체**이므로 차이가 발생한다.

#### 생성자 함수를 new를 붙이지 않고 호출할 경우

객체 생성을 목적으로 작성한 생성자 함수를 new 없이 호출하거나 일반 함수를 new를 붙여서 호출할 경우에 코드에서 오류가 발생할 수 있다.

```javascript 1.8

function Person(name, age, gender, position){
    
    this.name = name;
    this.age = age;
    this.gender = gender;
}
    
/** 생성자 함수를 new 없이 일반 함수 형태로 호출하면, this는 함수 호출이므로 전역 객체인 window 객체로 바인딩된다.
    따라서 이 코드는 this가 바인딩된 window 객체에 동적으로 세 프로퍼티가 생성된다. */
var qux = Person('qux', 20, 'man');         
    
/** Person()함수는 리턴값이 특별히 없다. 생성자 함수는 리턴값이 없을 때는 새로 생성되는 객체가 리턴되지만,
    일반 함수가 호출될 때에는 undefined가 리턴된다. */
console.log(qux);                           // undefined
console.log(window.name);                   // qux
console.log(window.age);                    // 20
console.log(window.gender);                 // man
```
new를 사용해서 호출하지 않을 경우에 코드의 에러가 발생할 수 있으므로 아래와 같은 패턴도 존재한다.

#### <강제로 인스턴스 생성하기>

````javascript 1.8
function A(arg){
        
    if(!(this instanceof A)) return new A(arg);
        
    this.value = arg ? arg : 0;
}

var a = new A(100);
var b = A(10);
    
console.log(a.value);       // 100
console.log(b.value);       // 10
console.log(global.value);  // undefined
````
위 코드와 같이 하면 this가 A의 인스턴스가 아닌 경우(즉, new로 호출한 것이 아닌 경우)에도 new로 새 인스턴스를 생성할 수 있다.

```javascript 1.8

function A(arg){

    /** 이 코드를 싸면 함수 이름과 관계없는 일반적인 모듈을 작성할 수 있다. */
    if(!(this instanceof arguments.callee))
        return new A(arg);
    this.value = arg ? arg : 0;
}

var a = new A(100);
var b = A(10);

console.log(a.value);       // 100
console.log(b.value);       // 10
console.log(global.value);  // undefined
```

#### call과 apply 메서드를 이용한 명시적인 this 바인딩

자바스크립트에서는 this를 특정 객체에 **명시적으로 바인딩**시키는 방법도 존재한다.
→ **apply()**, **call()** 메서드
→ 이 메서드들은 모든 함수의 부모 객체인 Function.prototype 객체의 메서드이므로 아래와 같은 형식으로 호출 가능하다.

    function.apply(thisArg, argArray)

apply() 메서드를 호출하는 주체가 함수고, apply() 메서드도 this를 특정 객체에 바인딩 할 뿐 본질적인 기능은 **함수 호출**이다.

첫 번째 인자 thisArg는 apply() 메서드를 호출한 함수 내부에서 사용한 this에 바인딩할 객체를 가리킨다.

두 번째 argArray 인자는 함수를 호출할 때 넘길 인자들의 배열을 가리킨다.

```javascript 1.8
/** apply() 메서드를 이용한 명시적인 this 바인딩*/
    
// 생성자 함수  
function Person(name, age, gender) {
    
    this.name = name;
    this.age = age;
    this.gender = gender;
}
    
// 빈 객체 생성
var foo = {};
    
// apply() 메서드 호출

/** 이 코드는 결국 Person('foo', 30, 'man') 함수를 호출하면서, this를 foo 객체에 명시적으로 바인딩하는 것을 의미한다. */
Person.apply(foo, ['foo', 30, 'man']);
console.dir(foo);

```

이러한 apply()나 call() 메서드는 this를 원하는 값으로 명시적으로 매핑해서 특정 함수나 메서드를 호출할 수 있다는 장점이 있다.

→ arguments 객체와 같은 **유사 배열 객체에서 배열 메서드를 사용하는 경우**이다.

```javascript 1.8
function myFunction(){
        
    console.dir(arguments);
    
    
    /** arguments 객체는 유사 객체 배열이므로 에러가 발생한다. */
    // arguments.shift();   // 에러 발생
    
    // arguments 객체를 배열로 변환
    
    
    /** Array.prototype.slice() 메서드를 호출해라. 이 때 this는 arguments 객체로 바인딩하라. */
    var args = Array.prototype.slice.apply(arguments);
    console.dir(args);
    

}
    
myFunction(1,2,3);
```
### 3. 함수 리턴

**자바스크립트 함수는 항상 리턴값을 반환한다.** 리턴문을 사용하지 않았더라도 아래 규칙들로 리턴값을 항상 전달한다.

#### 규칙: 일반 함수나 메서드는 리턴값을 지정하지 않을 경우, undefined 값이 리턴된다.
```javascript 1.8

var noReturnFunc = function(){
    console.log('This function has no return statement.');
};
    
var result = noReturnFunc();    // This function has no return statement.
console.log(result);            // undefined
```

#### 규칙: 생성자 함수에서 리턴값을 지정하지 않을 경우 생성된 객체가 리턴된다.
    
생성자 함수에서 별도의 리턴값을 지정하지 않을 경우 this로 바인딩된 새로 생성된 객체가 리턴된다.

따라서, 생성자 함수에서는 일반적으로 리턴값을 지정하지 않는다.

생성자 함수의 경우는 리턴값을 처리하는 몇 가지 예외 상황이 있다.

```javascript 1.8
/** 생성자 함수에서 명시적으로 객체를 리턴했을 경우 */
function Person(name, age, gender){
       
    this.name = name;
    this.age = age;
    this.gender = gender;
    
    /** 명시적으로 다른 객체 반환 */
    return { name : 'bar', age : 23, gender : "woman"};
}
    
var foo = new Person('foo', 30, 'man');
console.log(foo);                           // { name: 'bar', age: 23, gender: 'woman' }
```
