
#### 자바스크립트의 값들은 크게 기본 타입과 참조 타입으로 나뉜다.

#### 기본 타입
- 숫자(Number)
- 문자열(String)
- 불리언값(Boolean)
- undefined
- null

#### 참조 타입
- 객체(Object)
    - 배열(Array)
    - 함수(Function)
    - 정규표현식

## 자바스크립트 기본 타입
자바스크립트는 느슨한 타입 체크 언어이다.
```javascript 1.8
var intNum = 10;
var floatNum = 0.1;
var singleQuoteString = 'single';
var doubleQuoteString = 'double';
var singleChar = 'a';
var boolVar = true;
var emptyVar;
var nullVar = null;
console.log(
    typeof intNum,
    typeof floatNum,
    typeof singleQuoteString,
    typeof doubleQuoteString,
    typeof singleChar,
    typeof boolVar,
    typeof nullVar, //null이 아닌 object가 출력된다.
    typeof emptyVar
)
```
### 1. 숫자
자바스크립트에서는 모든 숫자를 64비트 부동 소수점 형태로 저장하기 때문에 정수와 실수 구분 없이 var 키워드로 값을 저장한다.
```javascript 1.8
var num = 5/2;
console.log(num); // 2.5
console.log(Math.floor(num)); // 2
```
### 2. 문자열
자바스크립트에서는 문자 하나만을 별도로 나타내는 데이터 타입은 없으므로, 한 개의 문자를 나타내기 위해서는 길이가 1인 문자열을 사용해야 한다.

자바스크립트에서는 한 번 생성된 문자열은 읽기만 가능하고 수정은 불가능하다.
```javascript 1.8
var str = 'test';
console.log(str); //test
str[0] = 'T';
console.log(str) //test(변하지 않음)
```
### 3. 불리언값
### 4. null과 undefined
자바스크립트에서는 null 타입 변수인지를 확인할 때 typeof 연산자가 아닌 일치 연산자(===)를 사용해서 변수의 값을 직접 확인해야 한다.
```javascript 1.8
var nullVar = null;
console.log(typeof nullVar === null);   //false
console.log(nullVar === null);          //true
```