## 기본 타입과 표준 메서드

Q) 기본 타입은 객체가 아닌데 어떻게 메서드를 호출할 수 있을까?

A) 기본값은 메서드 처리 순간에 객체로 변환된 다음 각 타입별 표준 메서드를 호출한다. 그 후, 메서드 호출이 끝나면 다시 기본값으로 복귀하게 된다.
```javascript 1.8
var num = 0.5;
console.log(num.toExponential(1));      // '5.0e-1'
    
console.log("test".charAt(2));          // 's'
```
