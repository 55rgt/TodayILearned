## 자바스크립트에서의 함수형 프로그래밍

- **일급 객체로서의 함수**
- **클로저**

→ 위 두 가지를 자바스크립트가 지원하기 때문에, 함수형 프로그래밍도 가능하다.

```javascript 1.8
/** 일급 객체, 클로저를 이용한 암호화 예시 */
    
var f1 = function(input) {
    /* */
    var result = 1;
    return result;
};
    
var f2 = function(input) {
    /* */
    var result = 2;
    return result;
};
    
var f3 = function(input) {
    /* */
    var result = 3;
    return result;
};
    
var get_encrypted = function(func) {
    var str = 'zzoon';
    
    return function() {
        return func.call(this, str);
    }
};
    
var encrypted_value = get_encrypted(f1)();
console.log(encrypted_value);
    
var encrypted_value = get_encrypted(f2)();
console.log(encrypted_value);
    
var encrypted_value = get_encrypted(f3)();
console.log(encrypted_value);
```
### 1. 배열의 각 원소 총합 구하기
```javascript 1.8
/** 명령형 프로그래밍 방식으로 작성한 코드 */
function sum(arr) {
        
    var len = arr.length;
    var i = 0 , sum = 0;
    
    for(; i < len ; i++) sum += arr[i];
    
    return sum;
}
    
function multiply(arr) {
        
    var len = arr.length;
    var i = 0 , result = 1;
    
    for(; i < len ; i++) result *= arr[i];
    
    return result;
}
```

위 코드의 단점은, 배열의 각 원소를 다른 방식으로 산술해야 할 때, 새로운 함수를 다시 구현해야 한다는 것이다.

```javascript 1.8
/** 함수형 프로그래밍을 이용하여 높은 모듈화를 이룬 예시 */
    
function reduce(func, arr, memo) {
    var len = arr.length,
        i = 0,
        accum = memo;
    
    for (; i < len; i++) accum = func(accum, arr[i]);
    
    return accum;
}
    
var arr = [1, 2, 3, 4];
    
var sum = function(x, y) {
    return x + y;
};
    
var multiply = function(x, y) {
    return x * y;
};
    
console.log(reduce(sum, arr, 0));
console.log(reduce(multiply, arr, 1));
```
### 2. 팩토리얼

```javascript 1.8

/** 명령형 프로그래밍으로 구현한 팩토리얼 (1) */
function fact_1(num) {
    
    var val = 1;
    for(var i = 2 ; i <= num ; i++) val *= i;
    return val;
    
}
    
console.log(fact_1(100));
    
/** 명령형 프로그래밍으로 구현한 팩토리얼 (2) */
function fact_2(num) {
    
    if (num === 0) return 1;
    else return num * fact_2(num - 1);
    
}
    
console.log(fact_2(100));
```

위 명령형 프로그래밍으로 구현한 팩토리얼 함수는 성능적으로 문제가 있을 수 있다.

앞서 연산한 결과를 캐시에 저장하여 사용할 수 있다면, 성능 향상에 도움이 된다.

```javascript 1.8
var fact = function() {
    
    var cache = {'0' : 1};
    var func = function(n) {
        var result = 0;
    
        if (typeof(cache[n]) === 'number') result = cache[n];
        else result = cache[n] = n * func(n-1);
        
        return result;
    };
    
    return func;
}();
    
console.log(fact(10));
console.log(fact(20));
```

위 코드에서 함수 func는 **cache에 접근할 수 있는 클로저를 반환**받는다.

#### [Memoization Pattern]
→ **계산 결과를 저장해 놓아 이후 다시 계산할 필요 없이 사용할 수 있게 해 놓은 패턴**

→ 기본적으로 계산된 결과를 함수 프로퍼티값으로 담아 놓고 나중에 사용하는 방식이다.

### 3. 피보나치 수열

```javascript 1.8
/** 함수형 프로그래밍과 메모이제이션 기법을 이용하여 구현한 피보나치 수열 */
var fibo = function() {
    var cache = {'0' : 0, '1' : 1};
    
    var func = function(n) {
        if (typeof(cache[n]) === 'number') result = cache[n];
		else result = cache[n] = func(n-1) + func(n-2);
    
        return result;
    };
    
    return func;
}();
    
console.log(fibo(10));
```

```javascript 1.8
/** 모듈화를 이용하여 팩토리얼과 피보나치 수열을 동시에 구현 */
var cacher = function(cache, func) {
    var calculate = function(n) {
        if (typeof(cache[n]) === 'number') result = cache[n];
        else result = cache[n] = func(calculate, n);
    
        return result;
    };
    
    return calculate;
};
    
var fact = cacher({ '0':1 }, function(func, n) {
    return n * func(n-1);
});
    
var fibo = cacher({ '0':0, '1':1 }, function(func, n) {
    return func(n-1) + func(n-2);
});
    
console.log(fact(10));
console.log(fibo(10));

```


