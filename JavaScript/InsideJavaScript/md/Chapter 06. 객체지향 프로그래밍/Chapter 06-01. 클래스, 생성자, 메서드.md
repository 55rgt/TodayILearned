## [클래스 기반의 언어 vs 프로토타입 기반의 언어]

**클래스 기반의 언어**
- 클래스로 객체의 기본적인 형태와 기능을 정의하고, 생성자로 인스턴스를 만들어서 사용할 수 있다.
- 클래스에 정의된 메서드로 여러 기능을 수행할 수 있다.
- 모든 인스턴스가 클래스에 정의된 대로 같은 구조이고 보통 런타임에 바꿀 수 없다.
- 정확성, 안정성, 예측성이 좋다.
- ex) Java, C++

**프로토타입 기반의 언어**
- 객체의 자료구조나 메서드 등을 동적으로 바꿀 수 있다.
- 동적으로 자유롭게 객체 구조나 동작 방식을 바꿀 수 있다.
- ex) JavaScript

## 클래스, 생성자, 메서드

자바스크립트에는 클래스의 개념이 없다.

하지만, 자바스크립트에서는 거의 모든 것이 객체이므로 함수로 구현할 수 있다.

```javascript 1.8
function Person(arg) {
    this.name = arg;
       
    this.getName = function(){
        return this.name;
    }
    
    this.setName = function(value) {
        this.name = value;
    }
}
    
var me = new Person('Mike');
console.log(me.getName());  // Mike
    
me.setName("Shinoda");
console.log(me.getName());  // Shinoda

```

-위의 예제는 문제가 있다. 가령 아래와 같이 Person을 생성자로 하여 여러 개의 객체를 생성한다고 하자.

```javascript 1.8
var me = new Person('me');
var you = new Person('you');
var us = new Person('us');
```  

-각 객체는 공통적으로 사용할 수 있는 setName()과 getName()을 따로 생성하고 있다. 이런 중복은 불필요하다.

```javascript 1.8
/** 함수 객체의 프로토타입 이용 */

function Person(arg) {
    this.name = arg;
}
    
Person.prototype.getName = function() {
    return this.name;
};
    
Person.prototype.setName = function(value) {
    this.name = value;
}
    
var me = new Person('me');
var you = new Person('you');
    
console.log(me.getName());
console.log(you.getName());
```
-위 예제에서는 Person 함수 객체의 prototype 프로퍼티에 getName()과 setName()을 지정하여
각 객체는 따로 함수 객체를 생성할 필요 없이 setName()과 getName()을 프로토타입 체인으로 접근할 수 있다.

-이와 같이 자바스크립트에서 클래스 안의 메서드를 정의할 때는
**프로토타입 객체에 정의한 후, new로 생성한 객체에서 접근할 수 있게 하는 것이 좋다.**

```javascript 1.8
/** 더클라스 클락포드가 제시한 메서드를 정의하는 방법 */
Function.prototype.method = function(name, func){
    if(!this.prototype[name]) this.prototype[name] = func;
}
```

```javascript 1.8
Function.prototype.method = function(name, func) {
    this.prototype[name] = func;
};
    
function Person(arg){
    this.name = arg;
}
    
Person.method('setName', function(value) {
    this.name = value;  
});
    
Person.method('getName', function() {
    return this.name;
});
    
var me = new Person('me');
var you = new Person('you');
    
console.log(me.getName());
console.log(you.getName());

```




