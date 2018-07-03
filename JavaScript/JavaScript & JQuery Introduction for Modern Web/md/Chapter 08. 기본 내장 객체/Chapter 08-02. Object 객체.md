## Object 객체

### 1. 생성

**Object 객체**: 자바스크립트의 가장 기본적인 내장 객체

자바스크립트의 모든 기본 내장 객체는 Object 객체를 기본으로 만들어진다.

아래 두 가지 방법으로 Object 객체를 생성한다.

```javascript 1.8
var obj = {};
var obj2 = new Object();
```

### 2. 속성과 메서드

#### Object 객체의 메서드

- **constructor()**: 객체의 생성자 함수를 나타낸다. 
- **hasOwnProperty(name)**: 객체가 name 속성이 있는지 확인한다.
- **isPrototypeOf(object)**: 객체가 object의 프로토타입인지 검사한다.
- **propertyIsEnumerable(name)**: 반복문으로 열거할 수 있는지 확인한다.
- **toLocaleString()**: 객체를 호스트 환경에 맞는 언어의 문자열로 바꾼다.
- **toString()**: 객체를 문자열로 바꾼다. _(객체를 문자열로 변환할 때 자동 호출)_
- **valueOf()**: 객체의 값을 나타낸다.

### 3. 자료형 구분

```javascript 1.8
/** typeof 연산자의 문제점 */


var literalNumber = 273;
var objectNumber = new Number(273);
    
var output = '';
output += '1. ' + typeof(literalNumber) + '\n';     // number
output += '2. ' + typeof(objectNumber) + '\n';      // object
alert(output);
    
if(typeof (literalNumber) === 'number') console.log('literalNumber은 숫자(typeof)');           // 출력 O
if(typeof (objectNumber) === 'number') console.log('objectNumber은 숫자(typeof)');             // 출력 X
if(literalNumber.constructor === 'number') console.log('literalNumber은 숫자(constructor)');   // 출력 O
if(objectNumber.constructor === 'number') console.log('objectNumber은 숫자(constructor)');     // 출력 O

```
### 4. 모든 객체에 메서드 추가

Object 객체의 프로토타입에 속성 또는 메서드를 추가하면 모든 객체에서 활용 가능하다.
```javascript 1.8
Object.prototype.test = function() { return this; };
    
var number = 23352;
console.log(number.test());
```