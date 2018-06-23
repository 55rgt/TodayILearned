## 함수 정의

자바스크립트에서 함수를 생성하는 방식은 아래 3가지가 있다.

- 함수 선언문(function statement)
- 함수 표현식(function expression)
- Function() 생성자 함수

### 1. 함수 리터럴

자바스크립트에서는 함수도 일반 객체처럼 값으로 취급한다.
따라서 함수 리터럴을 통해 함수를 생성할 수 있다.

```javascript 1.8
/** 함수 리터럴을 통한 add() 함수 정의*/
function add(x, y){
    return x + y;
}
    
/*
    function 키워드: 자바스크립트 함수 리터럴은 function 키워드로 시작한다.
    함수명: 함수명은 함수 몸체의 내부 코드에서 자신을 재귀적으로 호출하거나 디버거가 해당 함수를 구분하는 식별자로 사용된다.
          → 함수명은 선택 사항이다.
          → 익명 함수: 함수명이 없는 함수
    매개 변수: 자바스크립트의 매개 변수는 타입을 기술하지 않는다.
*/
```

### 2. 함수 선언문 방식으로 함수 생성

함수 선언문 방식으로 정의된 함수의 경우는 반드시 함수명이 정의되어 있어야 한다.

function 이라는 키워드를 명시적으로 사용하고, 변수 타입(리턴 타입, 매개변수)은 기술하지 않는다.

```javascript 1.8
function add(x, y){
    return x + y;
}
    
console.log(add(3,4));
```

### 3. 함수 표현식 방식으로 함수 생성

자바스크립트에서는 함수도 하나의 값처럼 취급된다. 따라서 함수도 숫자나 문자열처럼 변수로 할당하는 것이 가능하다.

**함수 표현식**: 함수 리터럴로 하나의 함수를 만들고, 여기서 생성된 함수를 변수에 할당하여 함수를 생성하는 것

함수 리터럴로 생성한 함수는 함수명이 없으므로 익명 함수이다.

```javascript 1.8
// add() 함수 표현식
    
/** add 변수는 함수 리터럴로 생성한 함수를 참조하는 변수이지, 함수 이름이 아니다.
*   add와 같이 함수가 할당된 변수를 함수 변수라고 한다.
*   아래는 인자로 넘겨진 두 수를 더하는 익명 함수를 만들고 이를 add 변수에 할당한 것이다.
*   → 익명 함수를 이용한 함수 표현식(익명 함수 표현식) */
var add = function(x, y){
    return x + y;
};
    
var plus = add;
    
console.log(add(3,4));      // 7
console.log(plus(5,6));     // 11
    
/** add와 plus 함수 변수는 두 개의 인자를 더하는 동일한 익명 함수를 참조한다. */
```
익명 함수의 호출은 함수 변수에 함수 호출 연산자인 ()를 붙여서 기술하는 것으로 가능하다.

**기명 함수 표현식**: 함수 이름이 포함된 함수 표현식

```javascript 1.8
var add = function sum(x, y){
    return x + y;
};

/** 함수 표현식에서 사용된 함수 이름은 외부에서 접근이 불가능하다. */
console.log(add(3,4));      // 7
console.log(sum(3,4));      // Uncaught ReferenceError: sum is not defined
```

함수 표현식에서 사용된 함수 이름은 정의된 함수 내부에서 해당 함수를 재귀적으로 호출하거나, 디버거 등에서 함수를 구분할 때 사용된다.

위 코드에서 add() 함수는 자바스크립트 엔진에 의해 다음과 같은 함수 표현식 형태로 변경되므로 함수 이름으로 함수 외부에서 호출이 가능하다.

```javascript 1.8
/** 즉, 실제로는 add 함수 변수로 함수 외불에서 호출이 가능하게 된 것이다. */
var add = function add(x, y){
    return x + y;  
};
    
/** add 함수 변수 ----------> add 함수 */
```
함수 이름을 이용하면 함수 코드 내부에서 재귀적인 함수 호출이 가능하다.

```javascript 1.8
var factorialVar = function factorial(n){
    if(n <= 1){
        return 1;
    }
    return n * factorial(n - 1);
};

console.log(factorialVar(3));   // 6
console.log(factorial(3));      // ReferenceError: undefined
```

####<function statement와 function expression에서의 세미콜론>

일반적으로 함수 선언문 방식으로 선언된 함수의 경우는 함수 끝에 세미콜론을 붙이지 않지만
함수 표현식 방식의 경우는 세미콜론을 붙이는 것을 권장한다.

### 4. Function() 생성자 함수를 통한 함수 생성하기

자바스크립트의 함수도 Function()이라는 기본 내장 생성자 함수로부터 생성된 **객체**라고 볼 수 있다.


    /** Function() 생성자 함수로 함수를 생성하는 문법 */
        
    new Function(arg1, arg2, ... , argN, functionBody )
        
    - arg1, arg2, ..., argN : 함수의 매개변수
    - funtionBody : 함수가 호출될 때 실행될 코드를 포함한 문자열

```javascript 1.8
var add = new Function('x','y','return x+y');
console.log(add(3,4));      // 7
``` 

### 5. 함수 호이스팅

```javascript 1.8

add(2,3);       // 5
    
// 함수 선언문 형태로 add() 함수 정의
function add(x, y){
    return x + y;   
}

add(3, 4);      // 7
```

위 코드에서 add(2,3); 부분이 제대로 동작한다.

이 것은 함수가 자신이 위치한 코드에 상관없이 함수 선언문 형태로 정의한 함수의 유효 범위는
코드의 맨 처음부터 시작하기 때문이다.

→ **함수 호이스팅**

더글라스 크락포드는 함수 호이스팅이 함수를 사용하기 전에는 선언해야 한다는 규칙을 무시하기 때문에 코드 구조를 엉성하게 만들 수 있기 때문에
함수 표현식 사용을 권장한다.

```javascript 1.8

/** add() 함수가 아래에서 함수 표현식 형태로 정의되어 있으므로 호이스팅이 일어나지 않는다. */
add(2, 3);      // uncaught type error
    
// 함수 표현식 형태로 add() 함수 정의
var add = function(x, y){
    return x + y;
};
    
add(3, 4);      // 7
```
함수 호이스팅이 발생하는 원인은 자바스크립트의 **변수 생성**과 **초기화**의 작업이 분리되어 진행되기 때문이다.
