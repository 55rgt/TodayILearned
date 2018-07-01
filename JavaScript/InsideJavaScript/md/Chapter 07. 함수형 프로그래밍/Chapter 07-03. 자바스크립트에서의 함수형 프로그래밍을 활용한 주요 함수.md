## 자바스크립트에서의 함수형 프로그래밍을 활용한 주요 함수

##### 초반에 삽을 펄텐데 계속 하면서 함수형 프로그래밍에 대한 감을 익혀야 한다.

### 1. 함수 적용

**함수 적용**: 함수형 프로그래밍에서 사용되는 용어

함수형 프로그래밍에서는 특정 데이터를 여러 가지 함수를 적용시키는 방식으로 작업을 수행한다.

여기서 함수는 단순히 입력을 넣고 출력을 받는 기능을 수행하는 것뿐만 아니라, 인자나 반환 값으로 전달된 함수를 특정 데이터에 적용시키는 개념으로 이해해야 한다.

**func.apply**: **'func 함수를 Obj 객체와 Args 인자 배열에 적용시킨다.'**

### 2. 커링

**커링**: 특정 함수에서 정의된 인자의 일부를 넣어 고정시키고, 나머지를 인자로 받는 새로운 함수를 만드는 것

```javascript 1.8

function calculate(a, b, c) {
    return a*b+c;
}
    
function curry(func) {
    var args = Array.prototype.slice.call(arguments, 1);
    
    return function() {
        return func.apply(null, args.concat(Array.prototype.slice.call(arguments)));
    }
}
    
var new_func1 = curry(calculate, 1);
console.log(new_func1(2,3));                // 5
    
var new_func2 = curry(calculate, 1, 3);
console.log(new_func2(3));                  // 6

```

calculate() 함수는 인자 세 개를 받아 연산을 수행하고 결과값을 반환한다.

new_func1() 함수는 curry() 함수로 첫 번째 인자를 1로 고정시켰고,

new_func2() 함수는 curry() 함수로 첫 번째 인자를 1, 두 번째 인자를 3으로 고정시켰다.

curry() 함수는 curry() 함수로 넘어온 인자를 args에 담아 놓고, 새로운 함수 호출로 넘어온 인자와 합쳐서 함수를 적용한다.

자바스크립트에서 커링을 지원하지 않으므로, Function.prototype에 커링 함수를 정의하여 사용할 수 있다.

```javascript 1.8
Function.prototype.curry = function() {
    var fn = this, args = Array.prototype.slice.call(arguments);
    return function() {
        return fn.apply(this, args.concat(Array.prototype.slice.call(arguments)));
    }
}
```

#### slice 메서드

-인자로 첫 인덱스와 마지막 인덱스를 주어 배열을 잘라서 복사본을 반환한다.

```javascript 1.8
/** 첫 번째 인자와 세 번째 인자를 고정하는 코드 */
function calculate(a, b, c) {
    return a*b+c;
}
    
function curry2(func) {
    var args = Array.prototype.slice.call(arguments, 1);
    
    return function() {
        var arg_idx = 0;
        for (var i = 0; i < args.length && arg_idx < arguments.length; i++)
            if (args[i] === undefined) args[i] = arguments[arg_idx++];
        return func.apply(null, args);
    }
}
    
var new_func = curry2(calculate, 1, undefined, 4);
console.log(new_func(3));                           // 1*3+4
```
    
**함수의 부분 적용**: 함수를 부분적으로 적용하여 새로운 함수를 반환받는 방식

### 3. bind

```javascript 1.8
Function.prototype.bind = function(thisArg) {
    var fn = this,
    slice = Array.prototype.slice,
    args = slice.call(arguments, 1);
    return function() {
        return fn.apply(thisArg, args.concat(slice.call(arguments)));
    };
};
    
var print_all = function(arg) {
    for (var i in this) console.log(i + " : " + this[i]);
    for (var j in arguments) console.log(j + " : " + arguments[j]);
};
    
var myObj = { name : "hyeonsik" };
    
var myFunc = print_all.bind(myObj);
myFunc(); // name : hyeonsik
    
var myFunc1 = print_all.bind(myObj, "world", "map");
myFunc1("hello");
```

### 4. 래퍼(Wrapper)

**래퍼**: 특정 함수를 자신의 함수로 덮어쓰는 것

객체지향 프로그래밍에서 다형성을 살리기위한 오버라이드 개념과 비슷하다.

단, 원래 함수 기능을 잃어버리지 않은 상태로 자신의 로직을 수행할 수 있어야 한다.
 
```javascript 1.8
function wrap(object, method, wrapper){
    
    var fn = object[method];
    return object[method] = function(){
        
        /** 기존 함수의 참조[fn]을 첫 번째 인자로 넘김으로써 이 함수에 접근 가능하다. 클로저를 활용한 예. 
            하지만, 이렇게 할 경우, 원래 함수 original()이 호출될 때의 this와 반환되는 익명 함수가 호출될 때의 this가 다르다. */
        return wrapper.apply(this, [ fn ].concat(Array.prototype.slice.call(arguments)));
        /** 따라서, 아래 코드를 이용하여 문제를 해결한다.*/
        //return wrapper.apply(this, [ fn.bind(this) ].concat(Array.prototype.slice.call(arguments)));
    };
}
    
Function.prototype.original = function(value) {
    
    this.value = value;
    console.log("value : " + this.value);
};

var myWrap = wrap(Function.prototype, "original", function(orig_func, value) {
    
    this.value = 20;
    orig_func(value);
    console.log("wrapper value : " + this.value);
});
    
var obj = new myWrap("hyeonsik");
```

Function.prototype에 original이라는 함수가 있고, 이는 인자로 넘어온 값을 value에 할당하고 출력하는 기능을 한다.

이를 사용자가 덮어쓰기 위해 wrap 함수를 호출하여 세 번째 인자로 넘긴 자신의 익명 함수를 Function.prototype.original에 덮어쓰려 한다.

이 때, 사용자는 자신의 익명 함수의 첫 번째 인자로 원래 함수의 참조를 받을 수 있다.

### 5. 반복 함수

#### 1) each

each 함수는 배열의 각 요소 혹은 객체의 각 프로퍼티를 하나씩 꺼내서 특정 함수에 차례대로 인자로 넣어 실행시킨다.

```javascript 1.8
function each( obj, fn, args ) {
    if ( obj.length === undefined )
        for ( var i in obj ) fn.apply( obj[i], args || [i, obj[i]] );
    else
        for ( var j = 0; j < obj.length; j++ ) fn.apply( obj[j], args || [j, obj[j]] );
    return obj;
}
  
each([1,2,3], function(idx, num) { console.log(idx + ": " + num); });
    
var hs = {
    
    name : "zzoon",
    age : 30,
    sex : "Male"
	
};
    
each(hs, function(idx, value) { console.log(idx + ": " + value);});
```

#### 2) map

map 함수는 주요 배열에 많이 사용되는데, 배열의 각 요소를 꺼내어 사용자 정의 함수를 적용시켜 새로운 값을 얻은 후, 새로운 배열에 넣는다.

```javascript 1.8
Array.prototype.map = function(callback) { 
	  
    var obj = this;
    var value, mapped_value;
    A = new Array(obj.length);
    
    for ( var i = 0; i < obj.length; i++ ) {
        value = obj[i];
        mapped_value = callback.call(null, value); 
        A[i] = mapped_value;
    }
     
    return A;
};  
  
var arr = [1,2,3];
var new_arr = arr.map(function(value) { return value*value; });
 
console.log(new_arr);

```
#### 3) reduce

reduce 함수는 배열의 각 요소를 하나씩 꺼내어 사용자의 함수를 적용시킨 후, 그 값을 계속해서 누적시킨다.

```javascript 1.8

Array.prototype.reduce = function(callback) { 
	
	var obj = this;
	var value, accumulated_value = 0;
 
	for ( var i = 0; i < obj.length; i++ ) {
        value = obj[i];
        //console.log("exe");
        accumulated_value = callback.call(null, accumulated_value, value); 
    }
     
    return accumulated_value;
};  
    
var arr = [1,2,3];
var accumulated_val = arr.reduce(function(a, b) { return a + b*b;});
    
console.log(accumulated_val);

```