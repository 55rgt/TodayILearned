## 자바스크립트 내장 함수

자바스크립트에는 자체적으로 몇 가지 내장 함수가 있다.

### 1. 타이머 함수

타이머 함수는 특정한 시간에 특정한 함술르 실행할 수 있게 하는 함수이다.

- setTimeout(function, millisecond): 일정 시간 후 함수를 한 번 실행한다.
- setInterval(function, millisecond): 일정 시간마다 함수를 반복해서 실행한다.
- clearTimeout(id): 일정 시간 후 함수를 한 번 실행하는 것을 중지한다.
- clearInterval(id): 일정 시간마다 함수를 반복하는 것을 중단한다.

```javascript 1.8
/** setTimeout() 함수 */
setTimeout(function() {
    alert('3초 지났음.');
}, 3000);
```

setInterval 함수는 지속적으로 실행되기 때문에 지속적으로 컴퓨터의 자원을 소비한다.
    
```javascript 1.8
/** setInterval() 함수 */

var intervalID = setInterval(function() {
    alert('<p>' + new Date() + '</p>');
}, 1000);

setTimeout(function() {
    clearInterval(intervalID);
}, 10000);
```

#### 자바스크립트의 실행 순서

웹 브라우저에 처리를 부탁하는 함수는 현재 단위가 끝나기 전에는 실행되지 않는다.

```javascript 1.8
alert('A');
    
setTimeout(function() {
    alert('B');
}, 0);
    
alert('C');

// 결과: A, C, B
```

#### 반복문과 콜백 함수

```javascript 1.8

/** setTimeout() 함수를 호출하는 시점이 반복문이 모두 끝난 이후이므로 0, 1, 2가 나타나지 않는다. */
for(var i = 0 ; i < 3 ; i++){
     setTimeout(function() {
         alert(i);
     }, 0);
 }

// 결과: 3, 3, 3
```
```javascript 1.8
/** 자기 호출 함수와 클로저를 통해 위 상황을 해결할 수 있다.*/
for (var i = 0 ; i < 3 ; i++){
    (function (closed_i){
        setTimeout(function(){
            console.log(closed_i);
        }, 10);
    })(i);
}
    
```
```javascript 1.8

/** forEach() 메서드를 활용한 클로저 생성 */

[0, 1, 2].forEach(function (t) {
    setTimeout(function () {
        console.log(t);
    }, 0);
});
    
```

### 2. 인코딩과 디코딩 함수

- escape(): 적절한 정도로 인코딩한다.
- unescape(): 적절한 정도로 디코딩한다.
- encodeURI(url): 최소한의 문자만 인코딩한다.
- decodeURI(encodedURI): 최소한의 문자만 디코딩한다.
- encodeURIComponent(uriComponent): 대부분의 문자를 모두 인코딩한다.
- decodeURIComponent(encodedURI): 대부분의 문자를 모두 디코딩한다.

### 3. 코드 실행 함수

자바스크립트는 문자열을 코드로 실행할 수 있는 특별한 함수를 제공한다.
- eval(string): 문자열(string)을 자바스크립트 코드로 실행한다.

```javascript 1.8
var willEval = '';
    
willEval += 'var number = 10;';
willEval += 'console.log(number);';
    
eval(willEval);
```

eval() 함수는 문자열을 자바스크립트 코드로 실행하는 함수이므로 eval() 함수로 실행된 코드에서 정의한 변수도 활용할 수 있다.

```javascript 1.8
/** eval() 함수 활용 */
    
var willEval = '';
    
willEval += 'var number = 10;';
willEval += 'console.log(number);';
    
eval(willEval);
alert(number * number);
```

### 4. 숫자 확인 함수

자바스크립트에서는 Infinity와 NaN이라는 숫자가 있다.

- isFinite(): number가 무한한 값인지 확인한다.
- isNaN(): number가 NaN인지 확인한다.

```javascript 1.8
var number = 10 / 0;
alert(number);
```

```javascript 1.8
/** isFinite() 함수 */

var number = 1 / 0;
alert(number + ' : ' + isFinite(number));
```
```javascript 1.8
/** Infinite 와 NaN 비교 */

alert('Infinity == Infinity: ' + Infinity === Infinity);
    
if(NaN === NaN) alert('NaN === NaN');
    
else alert('NaN !== NaN'); // 얘가 나옴
```
### 5. 숫자 변환 함수

자바스크립트는 숫자 변환 함수를 제공한다.
- parseInt(string): string을 정수로 바꾸어준다.
- parseFloat(string): string을 유리수로 바꾸어준다.
    
```javascript 1.8
/** Number() 함수의 단점 */

var won = '1000원';
var dollar = '1.5$';
    
alert(Number(won) + ': ' + Number(dollar));
```

Number() 함수는 숫자로 바꿀 수 없으면 NaN으로 반환한다.

반면, parseInt() 함수와 parseFloat() 함수는 숫자로 변환할 수 있는 부분까지 숫자로 변환한다.


```javascript 1.8

var won = '1000원';
var dollar = '1.5$';
    
alert(parseInt(won) + ': ' + parseInt(dollar));
alert(parseFloat(won) + ': ' + parseFloat(dollar));
```

자바스크립트는 0으로 시작하거나 0x로 시작하면 8진수, 16진수로 생각하고 변환한다.

- parseInt('0x273') - 627
- parseInt('273') - 273
- parseInt('0273') - 187

parseInt() 함수의 두 번째 매개변수에 진법을 입력하면 앞의 수를 해당 진법의 수로 인식한다.

- parseInt('FF', 16) - 255
- parseInt('52', 10) - 52
- parseInt('11', 8) - 9
- parseInt('10', 2) - 2

parseFloat() 함수의 중간에 e가 들어가면 e의 앞부분에서 끊지 않고 자리수로 인식한다.

- parseFloat('52.273e5') - 5227300