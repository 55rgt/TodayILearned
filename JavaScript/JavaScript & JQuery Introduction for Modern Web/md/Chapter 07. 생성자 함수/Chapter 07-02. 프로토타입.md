## 프로토타입

자바스크립트는 프로토타입 공간을 가진다.

**프로토타입**: 생성자 함수로 생성된 객체가 공통으로 가지는 공간

```javascript 1.8
function Student(name, korean, math, english, science){
    this.name = name;
    this.korean = korean;
    this.math = math;
    this.english = english;
    this.science = science;
}
```

자바스크립트의 모든 함수는 변수(객체) **prototype**을 갖는다.

```javascript 1.8
function Student(name, korean, math, english, science){
    this.name = name;
    this.korean = korean;
    this.math = math;
    this.english = english;
    this.science = science;
}
    
Student.prototype.getSum = function(){};
Student.prototype.getAverage = function(){};
Student.prototype.toString = function(){};
```