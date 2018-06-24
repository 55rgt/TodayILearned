## 객체 개요

배열은 요소에 접근할 때 인덱스를 사용하고, 객체는 키를 사용한다.
```javascript 1.8
/** 배열 선언 */
var array = ['A','B','C','D'];
    
/** 객체 선언 */
var product = {
    name: 'Tom',
    age : '45',
    height : '180',
    weight : '60'
};
```

객제의 키는 식별자와 문자열을 모두 사용할 수 있다.

```javascript 1.8
/** 문자열을 키로 사용 */

var obj = {
    'with space' : 266,
    'widt @#%$^#@%#$!$%' : 52 
};
```