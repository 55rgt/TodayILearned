## 매개변수

자바스크립트에서는 함수를 생성할 때 지정한 매개변수보다 많거나 적은 매개변수를 사용하는 것을 허용한다.

원래 함수에서 선언된 매개변수보다 많게 사용하면 추가된 매개변수는 일반적으로 무시된다.

원래 함수에서 선언한 매개변수보다 적게 사용하면 지정하지 않는 매개변수는 undefined로 입력된다.

```javascript
/** Array() 함수 */
var array1 = Array();
var array2 = Array(10);
var array3 = Array(273, 103, 57, 32);

alert(array1 + '\n' + array2 + '\n' + array3);
```

#### Array() 함수의 매개변수에 따른 차이

- Array(): 빈 배열 생성
- Array(number): 매개변수만큼의 크기를 가지는 배열 생성
- Array(any, ..., any): 매개변수를 배열로 생성