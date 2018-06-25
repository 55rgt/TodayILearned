## 참조 타입의 특성

자바에서 기본 타입(숫자, 문자열, 불리언값, null, undefined)을 제외한 모든 값은 객체이다.

객체의 모든 연산은 실제 값이 아닌 참조 타입으로 처리되므로 이러한 객체는 자바스크립트에서 참조 타입이라고 부른다.

```javascript 1.8
/* 동일한 객체를 참조하는 두 변수 */
      
// objA 변수는 객체 자체를 저장하고 있는 것이 아니라 생성된 객체를 가리키는 참조값을 저장하고 있다.
var objA = {
    val : 40
};
    
var objB = objA;
    
console.log(objA.val);  // 40
console.log(objB.val);  // 40
    
objB = 50;
    
console.log(objA.val); // 50
console.log(objB.val); // 50
```
###1. 객체 비교
동등 연산자(==)를 사용하여 두 객체를 비교할 때도 객체의 참조값을 비교한다.

```javascript 1.8
var a = 100;
var b = 100;
    
var objA = { value : 100};
var objB = { value : 100};
var objC = objB;
    
//기본 타입의 경우, 동등 연산자(==)를 이용하여 비교할 때 값을 비교한다.
console.log(a == b);        // true
    
//참조 타입의 경우, 동등 연산자(==)는 참조값을 비교한다.
console.log(objA == objB);  // false
console.log(objB == objC);  // true
```
###2. 참조에 의한 함수 호출 방식

**기본 타입**: **값에 의한 호출**
→ 객체의 프로퍼티 값이 복사되어 전달. 따라서 실제로 값이 변경되지 않는다.

**참조 타입**: **참조에 의한 호출**
→ 인자로 넘긴 객체의 참조값이 그대로 함수 내부로 전달되므로 실제로 값이 변경 가능하다.

```javascript 1.8
var a = 100;
var objA = { value : 100 };
    
function changeArg(num, obj) {
    num = 200;
    obj.value = 200;
    console.log(num);
    console.log(obj);
}
    
changeArg(a, objA);
    
/*
    200
    { value : 200 }
 */

console.log(a);     // 100
console.log(objA);  // 200
```