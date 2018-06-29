## 캡슐화

**캡슐화**: 관련된 여러 가지 정보(**멤버 변수, 메서드**)를 하나의 틀(**클래스**) 안에 담는 것 → **정보 은닉**

자바스크립트는 public이나 private 키워드가 없다. 하지만, 정보 은닉이 불가능한 것은 아니다.

```javascript 1.8
var Person = function(arg) {
    
    var name = arg ? arg : 'hyeonsik';
  
    this.getName = function() {
        return name;
    };
    
    this.setName = function(arg) {
        this.name = arg;
    };
};
    
var me = new Person();
console.log(me.getName());
   
me.setName('Mike');    
console.log(me.getName());
console.log(me.name);       // undefined

```

private 멤버로 name을 선언하고, public 메서드로 getName()과 setName()을 선언했다.

- this 객체의 프로퍼티로 선언하면 외부에서 new 키워드로 생성한 객체로 접근이 가능하다.
- 하지만, var로 선언된 멤버들은 외부에서 접근이 불가능하다.
- public 메서드가 클로저 역할을 하면서 private 멤버에 접근이 가능하다.

```javascript 1.8
var Person = function(arg) { 
    var name = arg ? arg : "zzoon" ;
	
    return {
        getName : function() {
            return name;
        },
        
        setName : function(arg) {
            name = arg;
        }
    };
 };
 
var me = Person(); /* or var me = new Person(); */
console.log(me.getName());
```  
위 방법에는 문제점이 있다.

→ **접근하는 private 멤버가 객체나 배열이면 얕은 복사로 참조만을 반환하므로 수정될 위험이 있다.**

```javascript 1.8
/** 얕은 복사 문제가 발생하는 예시 */

var ArrCreate = function() { 
    var arr = [1,2,3];
    
    return {
        getArr : function() {
            return arr;
        }
    };
};
    
var obj = ArrCreate(); /* or var me = new Person(); */
var arr = obj.getArr();
arr.push(5);
console.log(obj.getArr()); // [ 1,2,3,5 ]
```

→ 또한, 사용자가 반환받은 객체는 **Person 함수 객체의 프로토타입에 접근할 수 없다**는 단점이 있다.
즉, **Person을 부모로 하는 프로토타입을 이용한 상속 구현이 어려울 수** 있으므로, **객체가 아닌 함수를 반환**하는 것이 좋다.

```javascript 1.8

var Person = function(arg) { 
    var name = arg ? arg : "zzoon" ;
	
    var Func = function() {}
    Func.prototype = {
        getName : function() {
            return name;
        },
        setName : function(arg) {
            name = arg;
        }
    };
    
    return Func;
}();
    
var me = new Person();
console.log(me.getName());
```