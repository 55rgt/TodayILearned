##연산자

### 1. + 연산자

+연산자는 **더하기 연산**과 **문자열 연결 연산**을 수행한다.

```javascript 1.8
/** 두 연산자가 모두 숫자일 때만 더하기 연산이 수행되고, 나머지는 문자열 연결 연산이 이루어진다. */
var add1 = 1 + 2;
var add2 = "my" + "string";
var add3 = 1 + "string";
var add4 = "string" + 2;

console.log(add1);      // 3
console.log(add2);      // mystring
console.log(add3);      // 1string
console.log(add4);      // string2

```
### 2. typeof 연산자

typeof 연산자는 피연산자의 타입을 문자열 형태로 리턴한다. null과 배열은 'object'이고 함수는 'function'이다.

- 기본타입 - 숫자 - 'number'
- 기본타입 - 문자열 - 'string'
- 기본타입 - 불리언값 - 'boolean'
- 기본타입 - null - 'object'
- 기본타입 - undefined - 'undefined'
- 참조타입 - 객체 - 'object'
- 참조타입 - 배열 - 'object'
- 참조타입 - 함수 - 'function'

### 3. ==(동등) 연산자와 ===(일치) 연산자

== 연산자는 비교하려는 피연산자의 타입이 다를 경우에 타입 변환을 거친 다음 비교하고,

=== 연산자는 타입 변환을 하지 않고 비교한다.

가급적 === 연산자로 비교한다.

```javascript 1.8
console.log(1 == '1');      // true
console.log(1 === '1');     // false
```

### 4. !! 연산자
!!연산자는 피연산자를 불리언값으로 변환한다.

```javascript 1.8
console.log(!!0);               // false
console.log(!!1);               // true
console.log(!!'string');        // true
console.log(!!'');              // false
console.log(!!true);            // true
console.log(!!false);           // false
console.log(!!null);            // false
console.log(!!undefined);       // false
console.log(!!{});              // true : 객체는 빈 연산자라도 true를 출력한다.
console.log(!![1,2,3]);         // true

```
