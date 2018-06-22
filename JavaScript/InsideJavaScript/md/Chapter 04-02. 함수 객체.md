## 함수 객체: 함수도 객체다

###1. 자바스크립트에서는 함수도 객체다

자바스크립트에서는 함수도 객체이므로 함수는 기본 기능인 코드 실행뿐만 아니라, 함수 자체가 일반 객체처럼 프로퍼티들을 가질 수 있다.

```javascript 1.8

// add() 함수를 생성할 때 함수 코드는 함수 객체의 [[Code]] 내부 프로퍼티에 자동으로 저장된다
function add(x, y, z){
    
    return x + y + z;
}
    
add.result = add(3, 2, 1);
add.status = 'OK';
    
console.log(add.result);    // 5
console.log(add.status);    // OK
console.log(add);           // { [Function: add] result: 6, status: 'OK' }
console.log(add.length);    // 3
```

    <add 함수 객체>
        
    [[Code]]    -> return x + y;
        
    result      -> 5
        
    status      -> 'OK'
    
###2. 자바스크립트에서 함수는 값으로 취급된다.

자바스크립트에서 함수도 일반 객체처럼 취급될 수 있다.

따라서 자바스크립트 함수는 아래와 같은 동작이 가능하다.

- 리터럴에 의해 생성
- 변수나 배열의 요소, 객체의 프로퍼티 등에 할당 가능
- 함수의 인자로 전달 가능
- 함수의 리턴값으로 리턴 가능
- 동적으로 프로퍼티를 생성 및 할당 가능
 
**일급 객체**: 위에 나열한 기능들이 모두 가능한 객체 → 자바스크립트에서 함수는 일급 객체이다. → 함수형 프로그래밍 가능

#### 변수나 프로퍼티의 값으로 할당

함수는 숫자나 문자열처럼 변수나 프로퍼티의 값으로 할당될 수 있다.

```javascript 1.8
// 변수에 함수 할당
var foo = 100;
var bar = function() { return 100; };
console.log(bar());                 // 100
    
// 프로퍼티에 함수 할당
var obj = {};
obj.baz = function() { return 200; }
console.log(obj.baz());             // 200
```

#### 함수 인자로 전달

함수는 다른 함수의 인자로도 전달이 가능하다.

```javascript 1.8
var foo = function(func){
    func(); // 인자로 받은 func() 함수 호출
};
    
/** foo() 함수를 호출할 때, 함수 리터럴 방식으로 생성한 익명 함수를 func 인자로 넘겼다.
*   따라서 foo() 함수 내부에서는 func 매개변수로 인자에 넘겨진 함수를 호출할 수 있다. */
    
foo(function(){
    console.log('Function can be used as an argument');

});
```
#### 리턴값으로 활용

함수는 다른 함수의 리턴값으로 활용할 수 있다.

```javascript 1.8
var foo = function() {
    return function() {
        console.log('this function is a return value');
    };
};
    
var bar = foo();
    
bar();      // this function is a return value.
```
###3. 함수 객체의 기본 프로퍼티

자바스크립트는 함수도 객체이다.

즉, 함수 역시 일반적인 객체의 기능에 추가로 호출됐을 때 정의된 코드를 실행하는 기능을 가지고 있다는 것이다.

또한, 일반 객체와는 다르게 추가로 **함수 객체만의 표준 프로퍼티**가 정의되어 있다.

```javascript 1.8
function add(x, y){
    return x + y;
}

console.dir(add);
```
ECMA5 스크립트 명세서에는 모든 함수가 **length**와 **prototype**프로퍼티를 가져야 한다고 기술되어 있다.

**name 프로퍼티**는 함수의 이름을 나타낸다. (예제에서는 "add")

만약 이름이 없는 익명 함수라면 name 프로퍼티는 빈 문자열이 된다.

**caller 프로퍼티**는 자신이 호출한 함수를 나타낸다. (에제에서는 null - add 함수를 호출하지 않았으므로)

**argument 프로퍼티**는 함수를 호출할 때 전달된 인자값을 나타내낸다. (예제에서는 null - add 함수를 호출하지 않았으므로)

**__proto__프로퍼티** : [[Prototype]]과 같은 개념으로 보면 된다.

모든 자바스크립트 객체는 자신의 프로토타입을 가리키는 [[Prototype]] 내부 프로퍼티를 가지고 있고 이를 통해 자신의 부모
역할을 하는 프로토타입 객체를 가리킨다.

ECMA 표준에서는 함수 객체의 부모 역할을 하는 프로토타입 객체를 **Function.prototype 객체**라고 명명하고 있으며,
이것 역시 **함수 객체**라고 정의하고 있다. (예시에서는 Function Prototype 객체를 **Empty()** 함수라고 명하고 있다.)

#### length 프로퍼티
**length 프로퍼티**는 함수가 정상적으로 실행될 때 기대되는 이낮의 개수를 나타낸다.
```javascript 1.8
function func0(){
    
};
    
function func1(x){
    return x;
};
    
function func2(x, y){
    return x + y;
};
    
function func3(x, y, z){
    return x + y + z;
};
    
console.log('func0.length - ' + func0.length);  // func0.length - 0
console.log('func1.length - ' + func0.length);  // func1.length - 1
console.log('func2.length - ' + func0.length);  // func2.length - 2
console.log('func3.length - ' + func0.length);  // func3.length - 3
```
#### prototype 프로퍼티

모든 함수는 객체로서 **prototype 프로퍼티**를 가지고 있다.

단, **prototype 프로퍼티**와 모든 객체의 부모를 나타내는 **내부 프로퍼티**인 [[Prototype]]과 혼동해서는 안 된다.

##### prototype 프로퍼티와 [[Prototype]] 프로퍼티
두 프로퍼티 **모두 프로토타입 객체**를 가리킨다는 점에서는 공통점이 있지만,

- [[Prototype]]는 객체 입장에서 자신의 부모 역할을 하는 프로토타입 객체를 가리키는 반면에,
- 함수 객체가 가지는 **prototype 프로퍼티**는 이 함수가 생성자로 사용될 때
  이 함수를 통해 생성된 객체의 부모 역할을 하는 프로토타입 객체를 가리킨다.

자바스크립트에서는 함수를 생성할 때,
함수 자신과 연결된 프로토타입 객체를 동시에 생성하며 이 둘은 prototype 과 constructor 라는 프로퍼티로 서로를 참조하게 된다.

```javascript 1.8
    
// 함수가 생성됨과 동시에 myFunction() 함수의 prototype 프로퍼티에는 이 함수와 연결된 프로토타입 객체가 생성된다.
function myFunction(){
    return true;
}
    
// myFunction.prototype은 myFunction() 함수의 프로토타입 객체를 의미한다.
console.dir(myFunction.prototype);
console.dir(myFunction.prototype.constructor);
```

실행 결과를 살펴보면 myFunction.prototype 객체는 **Constructor**과 **__proto__**라는 두 개의 프로퍼티를 가지고 있다.

myFunction.prototype.constructor 값을 출력하면 myFunction()을 가리키고 있음을 알 수 있다.
