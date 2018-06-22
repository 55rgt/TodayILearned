## 프로토타입 체이닝

###1. 프로토타입의 두 가지 의미

자바스크립트는 기본 객체지향 프로그래밍 언어와는 다른 **프로토타입 기반**의 객체지향 프로그래밍을 지원한다.

자바스크립트의 모든 객체는 자신의 부모인 프로토타입 객체를 가리키는 참조 링크 형태의 숨겨진 프로퍼티가 있다.

→ **암묵적 프로토타입 링크(implicit prototype link)**

→ 이러한 링크는 모든 객체의 [[Prototype]] 프로퍼티에 저장된다. (**[[Prototype]] 링크**)

자바스크립트에서 모든 객체는 자신을 생성한 생성자 함수의 **prototype 프로퍼티**가 가리키는 **프로토타입 객체**를
자신의 부모 객체로 설정하는 **[[Prototype]] 링크**로 연결한다.

```javascript 1.8
/** prototype 프로퍼티와 [[Prototype]] 링크 구분 */
    
function Person(name) {
    this.name = name;
}
    
var foo = new Person('foo');
    
console.dir(Person);
console.dir(foo);
```
Person() 생성자 함수는 prototype 프로퍼티로 자신과 링크된 **프로토타입 객체_Person.prototype**를 가리킨다.

Person() 생성자 함수로 생성된 foo 객체는 Person() 함수의 프로토타입 객체를 [[Prototype]] 링크로 연결한다.

즉, prototype 프로퍼티나 [[Prototype]] 링크는 같은 프로토타입 객체를 가리키고 있다.

prototype 프로퍼티는 **함수의 입장**에서 자신과 링크된 프로토타입 객체를 가리키고 있으며,

[[Prototype]] 링크는 **객체의 입장**에서 자신의 부모 객체인 프로토타입 객체를 내부의 숨겨진 링크로 가리키고 있다.

결국, 자바스크립트에서 객체를 생성하는 것은 생성자 함수의 역할이지만,
생성된 객체의 실제 부모 역할을 하는 것은 생성자 자신이 아닌 생성자의 prototype 프로퍼티가 가리키는 프로토타입 객체이다.

###2. 객체 리터럴 방식으로 생성된 객체의 프로토타입 체이닝

자바스크립트에서 객체는 자기 자신의 프로퍼티뿐만이 아니라, 자신의 부모 역할을 하는 프로토타입 객체의 프로퍼티 또한 마치 자신의 것처럼 접근하는 것이 가능하다.

이것을 가능하게 하는 것이 **프로토타입 체이닝**이다.

```javascript 1.8
/** 객체 리터럴 방식에서의 프로토타입 체이닝 */
var myObj = {
    name : 'foo',
    sayName : function() {
        console.log('My Name is ' + this.name);
    }
};

myObj.sayName();                                // my name is foo.
console.log(myObj.hasOwnProperty('name'));      // true
console.log(myObj.hasOwnProperty('nickName'));  // false
myObj.sayNickName();                            // Uncaught TypeError
```

위 예제에서 console.log(myObj.hasOwnProperty('nickName'))은 오류가 발생하지 않는다.

→ 객체 리터럴로 생성한 객체는 **Object**()라는 내장 생성자 함수로 생성되었다.

→ **프로토타입 체이닝**: 자바스크립트에서 특정 객체의 프로퍼티나 메서드에 접근하려고 할 때, 해당 객체에 접근하려는 프로퍼티 또는 메서드가 없다면
**[[Prototype]] 링크**를 따라 자신의 부모 역할을 하는 프로토타입 객체의 프로퍼티를 차례대로 검색하는 것

→ 따라서, 위 예제에서는 myObj 객체에는 hasOwnProperty 메서드가 없지만,
[[Prototype]] 링크를 따라 올라간 Object.prototype 객체에서는 hasOwnProperty 메서드가 있기 때문에 오류가 나지 않는 것이다.

→ 똑같은 방식으로 sayNickname() 메서드가 오류를 나는 원인도 알아볼 수 있다.

###3. 생성자 함수로 생성된 객체의 프로토타입 체이닝

자바스크립트에서 모든 **객체**는 자신을 생성한 **생성자 함수의 prototype 프로퍼티가 가리키는 객체**를 자신의 **프로토타입 객체**(**부모 객체**)로 취급한다.

```javascript 1.8
function Person(name, age ,hobby){
    this.name = name;
    this.age = age;
    this.hobby = hobby;
}
    
var foo = new Person('foo', 30, 'tennis');
    

console.dir(foo.hasOwnProperty('name'));        // true
    
console.dir(Person.prototype);
```
위 예제에서 foo.hasOwnProperty() 메서드를 호출했지만, foo 객체는 hasOwnProperty() 메서드가 없어서 프로토타입 체이닝으로 
foo의 부모 객체인 Person.prototype 객체에서 hasOwnProperty() 메서드를 찾는다. 

하지만, 함수에 연결된 프로토타입 객체는 디폴트로 constructor 프로퍼티만을 가진 객체이므로 hasOwnProperty()가 없다.

그렇다면 왜 true 일까?

→ Person.prototype 역시 자바스크립트 객체이므로 Object.prototype 을 프로토타입 객체로 가지기 때문이다.

→ 즉, 프로토타입 체이닝이 Object.prototype 객체로 계속 이어진다는 것이다.

###4. 프로토타입 체이닝의 종점

자바스크립트에서 Object.prototype 객체는 **프로토타입 체이닝의 종점**이다.

즉, 모든 자바스크립트 객체들은 프로토타입 체이닝으로 Object.prototype 객체가 가진 프로퍼티와 메서드에 접근이 가능하다.

###5. 기본 데이터 타입 확장

Object.prototype 객체가 가진 프로퍼티와 메서드를 모든 자바스크립트 객체들이 접근할 수 있는 것처럼,
**Number.prototype**, **String.prototype**, **Array.prototype** 도 있다.

또한, 자바스크립트는 Object.prototype,, String.prototype 등과 같은 표준 빌트인 프로토타입 객체에도 사용자가 직접 정의한 메서드들을 추가할 수 있게 해 놨다.

```javascript 1.8
/** String 기본 타입에 메서드 추가 */
String.prototype.testMethod = function(){
    
    console.log('This is the String.prototype.testMethod()');
}
    
var str = "this is a test.";
str.testMethod();
    
console.dir(String.prototype);
``` 

###6. 프로토타입도 자바스크립트 객체다

함수가 생성될 때, 자신의 prototype 프로퍼티에 연결되는 프로토타입 객체는 디폴트로 constructor 프로퍼티만을 가진 객체이다.

**프로토타입 객체 역시 자바스크립트 객체**이므로 일반 객체처럼 동적으로 프로퍼티를 추가 및 삭제하는 것이 가능하다.

그리고 이렇게 변경된 프로퍼티는 실시간으로 프로토타입 체이닝에 반영된다.

```javascript 1.8
/** 프로토타입 객체의 동적 메서드 생성 예제 코드*/

function Person(name){
    this.name = name;
}
    
var foo = new Person('foo');
    
// foo.sayHello();                          // Error Occurred.
    
// 프로토타입 객체에 sayHello() 메서드 정의
Person.prototype.sayHello = function() {
    console.log('Hello');
}
    
foo.sayHello();

```
###7. 프로토타입 메서드와 this 바인딩

프로토타입 객체는 메서드를 가질 수 있다.

프로토타입 메서드 내부에서 this를 사용해도 객체의 메서드를 호출할 때 this 바인딩 규칙을 적용하면 된다.

→ 즉, 메서드 호출 패턴에서의 this는 그 메서드를 호출한 객체에 바인딩된다.

```javascript 1.8
/** 프로토타입 메서드와 this 바인딩 */
function Person(name){
    this.name = name;
}
    
Person.prototype.getName = function(){
    return this.name;
};
    
var foo = new Person('foo');
    
console.log(foo.getName());     // foo
    
// Person.prototype 객체에 name 프로퍼티를 동적으로 추가
Person.prototype.name = 'person';
    
console.log(Person.prototype.getName());    // person

```

foo 객체에서 getName() 메서드를 호출하면, getName() 메서드는 foo 객체에서 찾을 수 없으므로 프로토타입 체이닝이 발생한다.

→ foo 객체의 프로토타입 객체인 Person.prototype 에서 getName() 메서드가 있으므로 이 메서드가 호출된다.

→ 이때 getName() 메서드를 호출한 객체는 foo이므로, this는 foo 객체에 바인딩된다.

→ 따라서 foo가 출력된다.

Person.prototype.getName() 메서드와 같이 프로토타입 체이닝이 아니라, 바로 객체에 접근하면,

→ getName() 메서드를 호출한 객체가 Person.prototype이므로 this도 여기에 바인딩된다.

→ 그리고 Person.prototype 객체에 name 프로퍼티를 동적으로 추가했으므로 'person'이 출력된다.

###8. 디폴트 프로토타입은 다른 객체로 변경이 가능하다.

디폴트 프로토타입 객체는 함수가 생성될 때 같이 생성되며, 함수의 Prototype 프로퍼티에 연결된다.

자바스크립트는 **디폴트 프로토타입 객체를 다른 일반 객체로 변경하는 것이 가능하다.**

→ 이러한 특징을 이용해서 객체지향의 상속을 구현한다.

→ 주의해야 할 점은, 생성자 함수의 프로토타입 객체가 변경되면, 변경된 시점 이후에 생성된 객체들은
변경된 프로토타입 객체로 **[[Prototype]]** 링크를 연결한다는 점을 기억해야 한다.

→ 또한, 생성자 함수의 프로토타입이 변경되기 이전에 생성된 객체들은 기존 프로토타입 객체로의 **[[Prototype]]** 링크를 그대로 유지한다.

```javascript 1.8
/** 프로토타입 객체 변경 */
    
// Person() 생성자 함수

function Person(name){
    this.name = name;
}
    
/** Person() 함수를 생성할 때 디폴트로 같이 생성되는 Person.prototype 객체는 자신과 연결된 Person() 생성자 함수를 가리키는
    constructor 프로퍼티만을 가진다. 따라서, Person.prototype.constructor 는 Person() 생성자 함수를 가리킨다. */
    
console.log(Person.prototype.constructor);  // [Function: Person]
    
// foo 객체 생성  
    
/** foo 객체를 생성했다. 객체 생성 규칙에 따라 foo 객체는 Person.prototype 객체를 자신의 프로토타입으로 연결한다.
*   하지만, foo 객체는 country 프로퍼티가 없고 디폴트 프로토타입 객체 Person.prototype도 country 프로퍼티가 없으므로
*   프로퍼타입 체이닝이 일어나도 undefined 값이 출력된다. */
    
var foo = new Person('foo');
console.log(foo.country);                   // undefined
    
// 디폴트 프로토타입 객체 변경
    
/** 변경한 프로토타입 객체는 country 프로퍼티만 있기 때문에, 디폴트 프로토타입 객체와 달리 constructor 프로퍼티가 없다.
*   따라서 프로토타입 체이닝을 통해 Object.prototype을 [[Prototype]] 링크로 연결하여 Object 생성자 함수가 출력딘다. */
Person.prototype = {
    country : 'Korea'
};
    
console.log(Person.prototype.constructor);  // [Function: Object]
    
// bar 객체 생성
var bar = new Person('bar');
    
/** 서로 다른 프로토타입을 가지고 있기 때문에 서로 다른 프로토타입 체이닝이 발생한다. */
console.log(foo.country);                   // undefined
console.log(bar.country);                   // Korea
console.log(foo.constructor);               // [Function: Person]
console.log(bar.constructor);               // [Function: Object]
```
###9. 객체의 프로퍼티 읽기나 메서드를 실행할 때만 프로토타입 체이닝이 동작한다.

자바스크립트는 객체에 없는 프로퍼티에 값을 쓰려고 할 경우에는 동적으로 객체에 프로퍼티를 추가하기 때문에 이 때 프로토타입 체이닝이 일어나지 않는다.

```javascript 1.8

function Person(name) {
    
    this.name = name;
}
    
Person.prototype.country = 'Korea';
    
var foo = new Person('foo');
var bar = new Person('bar');
    
console.log(foo.country);       // Korea
console.log(bar.country);       // Korea
    
foo.country = 'USA';
    
console.log(foo.country);       // USA          -> 프로토타입 체이닝 없이 출력된다.
console.log(bar.country);       // Korea

```