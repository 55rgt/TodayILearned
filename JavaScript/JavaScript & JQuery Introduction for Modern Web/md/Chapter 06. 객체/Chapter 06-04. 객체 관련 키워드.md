## 객체 관련 키워드

### in 키워드

in 키워드를 통해 객체에 해당하는 속성이 있는지 알아볼 수 있다.

```javascript 1.8
var student = {
    name: 'Tom',
    math: 98,   english: 96,
    korean: 92, science: 98
};
    
var output = '';
output += "'name' in student: " + ('name' in student) + '\n';       // true
output += "'school' in student: " + ('school' in student) + '\n';   // false

```

### with 키워드

객체를 with 키워드 내부에 넣어주면 괄호 안에서 객체를 명시할 필요 없이 속성을 쉽게 쓸 수 있다.

    with(객체) {
        // 코드
    }

```javascript 1.8
/** with 키워드를 사용한 경우 */

var student = {
    name: 'Tom',
    math: 98,   english: 96,
    korean: 92, science: 98
};
    
var output = '';
    
with(student){
    output += 'name: ' + name + '\n';
    output += 'math: ' + math + '\n';
    output += 'english: ' + english + '\n';
    output += 'korean: ' + korean + '\n';
    output += 'science: ' + science + '\n';
}

alert(output);
```