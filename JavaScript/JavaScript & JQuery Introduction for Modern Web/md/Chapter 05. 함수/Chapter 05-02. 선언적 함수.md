## 선언적 함수

```javascript
/** 선언적 함수의 형식*/

function func(){
    
}
```
```javascript
/** 선언적 함수의 재정의(1) */

function func() { alert('A'); }
function func() { alert('B'); }
    
func();
```

```javascript
/** 익명 함수의 재정의(1) */

var func = function() { alert('A'); };
var func = function() { alert('B'); };
    
func();
```

웹 브라우저는 선언적 함수부터 읽는다.

```javascript
/** 익명 함수의 재정의(2) ERROR!*/

func();
    
var func = function() { alert('A'); };
var func = function() { alert('B'); };

```
```javascript
/** 선언적 함수의 재정의(2) */

func();
    
function func() { alert('A'); }
function func() { alert('B'); }
    
```

아래 예제 코드에서는 'A'가 출력된다.

(선언적 함수가 먼저 생성되고 익명 함수가 나중에 생성되기 때문)

```javascript
var func = function(){
    alert('A');
}
    
function func(){
    alert('B');
}
    
func();
```