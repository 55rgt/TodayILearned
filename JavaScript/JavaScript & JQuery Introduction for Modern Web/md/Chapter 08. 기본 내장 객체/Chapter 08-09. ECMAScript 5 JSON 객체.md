## ECMAScript 5 JSON 객체

JSON: JavaScript Object Notation

- **JSON.stringify()**: 자바스크립트 객체를 JSON 문자열로 변환한다.
- **JSON.parse()**: JSON 문자열을 자바스크립트 객체로 변환한다.

```javascript 1.8
/** JSON.stringify() 메서드 */
var object = {
    name: 'Roger',
    region: 'San Francisco'
};
    
alert(JSON.stringify(object));
```
```javascript 1.8
/** JSON.parse() 메서드 */
var object = {
    name: 'Roger',
    region: 'San Francisco'
};
    
var copy = JSON.parse(JSON.stringify(object));
    
alert(copy.name + ' : ' + copy.region);
```

- JSON.stringify() 메서드의 매개변수에 넣은 객체에 toJSON() 메서드가 없다면 객체 전체를 JSON으로 변환하고,
toJSON() 메서드가 있다면 toJSON() 메서드에서 리턴한 객체를 JSON으로 변환한다.

```javascript 1.8
/** toJSON() 메서드 */
    
var object = {
    name: 'object',
    prop: 'object',
    toJSON: function () { return { custom: 'custom' }; }
};
    
alert(JSON.stringify(object));
```