## ECMAScript 5 String 객체

- **trim()**: 문자열 양쪽 끝의 공백을 제거한다.

```javascript 1.8
var text = '  text  ';
    
var output = '';
output += '++' + text + '++\n';
output += '++' + text.trim() + '++';
alert(output);
```
