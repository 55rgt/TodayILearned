## 상속

자바스크립트는 클래스를 기반으로 하는 전통적인 상속을 지원하지는 않는다.

하지만 자바스크립트의 객체 프로토타입 체인을 이용하여 상속을 구현할 수 있다.

- 1. **클래스 기반 전통적인 상속 방식 흉내**
- 2. **클래스 개념 없이 객체의 프로토타입으로 상속을 구현하는 방식** (**프로토타입을 이용한 상속**)

### 1. 프로토타입을 이용한 상속

```javascript 1.8

/** 더글라스 크락포드가 소개한 자바스크립트 객체를 상속하는 방법 */
    
function create_object(o) {
    function F(){}
    F.prototype = o;
    return new F();
}
```
-create_object() 함수는 인자로 들어온 객체를 부모로 하는 자식 객체를 생성하여 반환한다.

-새로운 빈 함수 객체 F를 만들고, F.prototype 프로퍼티에 인자로 들어온 객체를 참조한다.

-함수 객체 F를 생성자로 하는 새로운 객체를 만들어 반환한다.

→ 이렇게 반환된 객체는 부모 객체의 프로퍼티에 접근 가능하고, 자신만의 프로퍼티를 만들 수도 있다.

```javascript 1.8
/** create_object() 함수를 이용하여 상속을 구현한 함수. */
    
var person = {
    
    name : 'zzoon',
    getName : function() {
        return this.name;
    },
    setName : function(arg) {
        this.name = arg;
    }
};
    
function create_object(o) {
    
    function F() {}
    F.prototype = o;
    return new F();
	
}
    
var student = create_object(person);
    
student.setName("me");
console.log(student.getName());     // me
```
-person 객체를 상속하여 student 객체를 만들었다.

-클래스에 해당하는 상성자 함수를 만들거나, 그 클래스의 인스턴스를 따로 생성하지도 않고 단지 부모 객체에 해당하는 person 객체와
이 객체를 프로토타입 체인으로 참조할 수 있는 자식 객체 student를 만들어서 사용했다.

     -------------------------          new F()               
    |        Function F       |---------------------------
     -------------------------                            |
                 |                                        |
                 | prototype                              |
                 ↓                                        |
     --------------------------                           |
    |         Person           |                          |
    |                          |                          |
    |    -----------------     |                          |
    |   |      name       |    |                          |
    |    -----------------     |                          ↓
    |    -----------------     |  [[Prototype]]  -------------------
    |   |     setName     |    |<---------------|      student      |
    |    -----------------     |                 -------------------  
    |    -----------------     |
    |   |     getName     |    |
    |    -----------------     |
    |                          |
     --------------------------
                 |
                 | [[Prototype]]
                 ↓
      -------------------------                         
     |    Object.Prototype     |
      -------------------------                           

위 예제는 부모 객체의 메서드를 그대로 상속받아 사용하는 방법이다.

자식은 **자신의 메서드를 재정의**하거나 **추가로 기능을 더 확장**시킬 수 있어야 한다.

```javascript 1.8
    student.setAge = function(age){ /*...*/ }
    student.getAge = function(){ /*...*/ }
```

위 예제와 같이 기능을 확장시킬 수는 있지만, 코드가 지저분해질 수 있다.

자바스크립트에서는 범용적으로 **extend()** 라는 이름의 함수로 객체에 자신이 원하는 객체 혹은 함수를 추가시킨다.

→ 하지만, JQuery 등의 extend() 함수는 **얕은 복사**를 지원하므로, **깊은 복사**를 해야 한다.

→ 복사 대상이 객체인 경우 재귀적으로 extend() 함수를 호출하여 해결할 수 있다.
 
```javascript 1.8
/** 얕은 복사 예시 */
    
var person = {
    
    name : "zzoon",
	
    getName : function() {
        return this.name;
    },
	
    setName : function(arg) {
        this.name = arg;
    }
};
    
function create_object(o) {
    
    function F() {}
	
    F.prototype = o;
	
    return new F();
}
    
function extend(obj,prop) {
    
    if ( !prop ) { prop = obj; obj = this; }
    for ( var i in prop ) obj[i] = prop[i];
    return obj;
}
    
var student = create_object(person);
    
student.setName("me");
console.log(student.getName());
    
var added = {
    setAge : function(age) {
        this.age = age;
    },
    
    getAge : function() {
        return this.age;
    }
};
    
extend(student, added);
    
student.setAge(25);
console.log(student.getAge());
```
### 2. 클래스 기반의 상속

```javascript 1.8
function Person(arg){
    this.name = arg;
}
    
Person.prototype.setName = function(value){
    this.name = value;
};
    
Person.prototype.getName = function() {
    return this.name;
};
    
function Student(arg){  }
    
var you = new Person('I am Groot.');
Student.prototype = you;
    
var me = new Student('Hyeonsik');
me.setName('hyeonsik');
    
console.log(me.getName());
```
위 예제에는 문제점이 있다.

me 인스턴스를 생성할 때 부모 클래스인 Person의 생성자를 호출하지 않는다.

따라서, 'Hyeonsik'을 인자로 넘겼지만, 이를 반영하지 못한다.

이를 해결하기 위해서는 Student 함수에 아래 코드를 추가하여 부모 생성자를 호출해야 한다.

```javascript 1.8
function Student(arg){
    Person.apply(this, arguments);
}
```

지금까지는 자식 클래스의 객체가 부모 클래스의 객체를 프로토타입 체인으로 직접 접근한다. 

→ 자식 클래스의 prototype에 메서드를 추가할 때 문제가 발생할 수 있다.

→ **부모 클래스의 인스턴스와 자식 클래스의 인스턴스는 서로 독립적일 필요**가 있다.

```javascript 1.8
/** 부모 클래스와 자식 클래스의 프로토타입 사이에 중개자 추가 예시 */
    
function Person(arg) {
    this.name = arg;
}
    
Function.prototype.method = function(name, func) {
    this.prototype[name] = func;
};
    
Person.method("setName", function(value) {
    this.name = value;
});
    
Person.method("getName", function() {
    return this.name;
});
    
function Student(arg) {
}
    
function F() {}
F.prototype = Person.prototype;
    
Student.prototype = new F();
    
//Student.prototype.constructor = Student;
//Student.super = Person.prototype;
    
var me = new Student();
me.setName("zzoon");
    
console.log(me.getName());
```
**빈 함수의 객체**를 중간에 두어 **Person의 인스턴스와 Student의 인스턴스를 서로 독립적**으로 만들었다.

→ Person 함수 객체에서 this에 바인딩되는 것을 Student의 인스턴스는 접근할 수 없다.

```javascript 1.8
/** Javascript Pattern의 저자 스토얀 스테파노프가 제시한 상속 관계 최적화 함수
*   : 즉시 실행 함수와 클로저 이용함. */

var inherit = function(Parent, Child){
    var F = function(){};
        
    return function(Parent, Child) {
            
        F.prototype = Parent.prototype;
        Child.prototype = new F();
        Child.prototype.constructor = Child;
        Child.super = Parent.prototype;
    };
}();
```

위 코드에서 클로저는 F() 함수를 지속적으로 참조하기 때문에 F()는 GC의 수집 대상이 되지 않는다.

즉, 함수 F()의 생성은 단 한 번 이루어지고, 다시 생성될 필요가 없다.