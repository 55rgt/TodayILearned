## 익명 함수

함수는 코드의 집합을 나타내는 자료형이다.

```javascript
/** 함수 생성과 출력 */
var func = function(){
    var output = prompt('숫자를 입력하시오.', '숫자');
    alert(output);
};
    
alert(func);
```

**익명 함수**: 이름이 없는 함수.

**선언적 함수**: 이름이 있는 함수.

자바스크립트에서는 함수를 실행하는 것을 '함수 호출'이라고 한다. 여타 자료형과 다르게 뒤에 괄호를 열고 닫음으로써 코드를 실행한다.

```javascript
/**함수 호출*/
var func = function() {
    var output = prompt("함수 호출!!!!");
    alert(output);
};
    
func();
```