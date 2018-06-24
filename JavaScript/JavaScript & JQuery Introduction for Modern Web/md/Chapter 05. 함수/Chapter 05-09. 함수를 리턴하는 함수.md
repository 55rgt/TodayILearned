## 함수를 리턴하는 함수

자바스크립트에서는 함수를 리턴할 수 있다.

```javascript 1.8
/** 익명 함수를 리턴하는 함수 */

function returnFunction() {
    return function() {
        alert('Hello!');
    };
}
    
/** 함수가 리턴되기 때문에 한 번 더 호출해서 alert()함수를 실행한다. */
returnFunction()();
```
    
**클로저**에서 함수를 리턴하는 함수를 사용할 수 있다. 