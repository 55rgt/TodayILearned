## 객체의 속성 추가와 제거

### 1. 속성 추가
```javascript 1.8
/** 동적으로 속성 추가 */
var student = {};
    
student.name = 'Tom';
student.hobby = 'Sleeping';
student.goal = 'Scientist';
student.skill = 'Programming';
    
/** 동적으로 메서드도 추가 가능하다.  */
student.toString = function() {
        
    var output = '';
    for (var key in student){
        if(key !== 'toString') output += key + '\t' + this[key] + '\n';
    }
    return output;
}
    
alert(student.toString());
```
### 2. 속성 제거

동적으로 객체의 속성을 제거할 때에는 delete 키워드를 사용한다.

    delete (student.name);
