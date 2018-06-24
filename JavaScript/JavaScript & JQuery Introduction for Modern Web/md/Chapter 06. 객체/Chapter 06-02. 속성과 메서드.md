## 속성과 메서드

**요소**: 배열 내부에 있는 값
**속성**: 객체 내부에 있는 값

```javascript 1.8
/** 객체의 속성이 가질 수 있는 자료형 */
var obj = {
    number: 273,
    string: 'IanTha',
    boolean: true,
    array: [15, { method : function(){ console.log('Hello')} }, 'abc', true],
    method: function() { }
};
```
```javascript 1.8
/** 속성과 메서드의 구분 */
var person = {
    name: 'Tom',
    eat: function(food) {
        alert(name + '이 ' + food + '를 먹는다.');
    }
};
    
person.eat('seafood');

// 출력: 이 seafood를 먹는다.
```

메서드 내에서 자기 자신이 가지는 속성을 출력하고 싶다면 this 키워드를 사용해야 한다.

```javascript 1.8
/** 속성과 메서드의 구분 */
var person = {
    name: 'Tom',
    eat: function(food) {
        alert(this.name + '이 ' + food + '를 먹는다.');
    }
};
    
person.eat('seafood');

// 출력: Tom이 seafood를 먹는다.
```
