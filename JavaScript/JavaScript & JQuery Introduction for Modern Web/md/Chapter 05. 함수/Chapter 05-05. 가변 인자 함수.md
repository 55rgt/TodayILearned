## 가변 인자 함수

**가변 인자 함수**: 매개변수의 개수가 변할 수 있는 함수

자바 스크립트의 모든 함수는 내부에 매개변수의 배열인 변수 arguments를 가지고 있다.

```javascript 1.8
function sumAll(){
    alert(typeof (arguments) + ": " + arguments.length);
}

sumAll(1,2,3,4,5,6,7,8,9);
```