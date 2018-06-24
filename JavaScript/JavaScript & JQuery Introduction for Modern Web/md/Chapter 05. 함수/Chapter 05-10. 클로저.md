## 클로저

클로저를 사용하면 지역 변수의 유효 범위에 대한 이슈를 어느 정도 제어할 수 있다.

```javascript 1.8
/** Closure(1) */

function test(name){
    var output = 'Hello ' + name + '..!';
    return function() {
        alert(output);
    };
}

test('Hello')();
```

클로저의 정의는 다양하다.
    
#### 클로저
- 지역 변수를 남겨두는 현상
- test() 함수로 생성된 공간
- 리턴된 함수 자체
- 살아남은 지역 변수(여기서는 output)

```javascript 1.8
/** Closure(2) */

function test(name) {
    var output = 'Hello ' + name + '..!';
    return function() {   
        alert(output);
    };
}
    
var test_1 = test('Web');
var test_2 = test('JS');

test_1();
test_2();


```