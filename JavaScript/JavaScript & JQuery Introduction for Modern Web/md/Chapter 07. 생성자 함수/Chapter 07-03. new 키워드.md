## new 키워드

```javascript 1.8
/** new 키워드 */
function Constructor(value) {
    this.value = value;
}
    

var constructor = new Constructor('Hello');
    
/** new 키워드를 사용하지 않으면, this.value 는 window 객체에 속성을 추가하는 것이 된다. */
// var constructor = Constructor('Hello');
    
alert(constructor.value);
```