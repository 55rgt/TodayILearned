## Number 객체

Number 객체는 자바스크립트에서 숫자를 표현할 때 사용한다.

```javascript 1.8
/** Number 객체 생성 */

var literalNumber = 27;
var objectNumber = new Number(27);
    
alert(typeof(literalNumber));   // number
alert(typeof(objectNumber));    // object
```
### 1. 메서드

#### Number 객체의 메서드

- **toExponential(num)**:  숫자를 지수 표시로 나타낸 문자열을 리턴한다. **(num: 유효 숫자의 자리수)**
- **toFixed(num)**:        숫자를 고정 소수점 표시로 나타낸 문자열을 리턴한다. **(num: 소수점 몇 째 자리까지 나타낼 것인지)**
- **toPrecision(num)**:    숫자를 길이에 따라 지수 표시 또는 고정 소수점 표시로 나타낸 문자열을 리턴한다. **(num: 소수점 몇 째 자리까지 나타낼 것인지)**

위 메서드들은 모두 매개변수로 숫자를 하나 입력받는다.

```javascript 1.8
/** Number 객체의 메서드 */
var number = 283.5210332;
    
var output = '';
output += number.toFixed(1) + '\n';     // 273.5
output += number.toFixed(4);            // 273.5210
alert(output);
```

### 2. 생성자 함수의 속성

```javascript 1.8
/** 생성자 함수의 속성과 메서드 생성 */
 
function Constructor(){ }
Constructor.property = 273;
Constructor.method = function() { };
    
alert(Constructor.property);
```
#### Number 생성자 함수의 속성

- **MAX_VALUE**: 자바스크립트의 숫자가 나타낼 수 있는 최대의 숫자
- **MIN_VALUE**: 자바스크립트의 숫자가 나타낼 수 있는 최소의 숫자
- **NaN**: 자바스크립트의 숫자로 나타낼 수 없는 문자
- **POSITIVE_INFINITY**: 양의 무한대 숫자
- **NEGATIVE_INFINITY**: 음의 무한대 숫자