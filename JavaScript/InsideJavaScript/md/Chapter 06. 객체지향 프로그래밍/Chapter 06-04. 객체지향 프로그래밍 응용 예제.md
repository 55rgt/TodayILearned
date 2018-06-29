## 객체지향 프로그래밍 응용 예제

### 1. 클래스의 기능을 가진 subClass 함수

아래 세 가지 요소를 활용하여 구현한다.
- 1. 함수의 프로토타입 체인
- 2. extend 함수
- 3. 인스턴스를 생성할 때 생성자(_init 함수) 호출

#### 1) subClass 함수 구조

subClass는 상속받을 클래스에 넣을 변수 및 메서드가 담긴 객체를 인자로 받아 부모 함수를 상속받는 자식 클래스를 만든다.

    var SuperClass = subClass(obj);
    var SubClass = SuperClass.subClass(obj);
    
SuperClass를 상속 받는 SubClass를 만들 때, SuperClass.subClass()로 호출하게 구현한다.

또한, 최상위 클래스인 SuperClass는 자바스크립트의 Function을 상속받게 한다.

##### [subClass의 구조]

    function subClass(obj){
        
        /* (1) 자식 클래스 (함수 객체) 생성                             */
        /* (2) 생성자 호출                                          */
        /* (3) 프로토타입 체인을 활용한 상속 구현                        */
        /* (4) obj를 통해 들어온 변수 및 메서드를 자식 클래스에 추가        */
        /* (5) 자식 함수 객체 반환                                  */
    
    }

#### 2) 자식 클래스 생성 및 상속

```javascript 1.8
function subClass(obj) {
    
    var parent = this;
    var F = function() { };
        
    var child = function() { };
    
    /* 프로토타입 체이닝 */
    F.prototype = parent.prototype;
    child.prototype = new F();
    child.prototype.constructor = child;
    child.parent = parent.prototype;
    child.parent_constructor = parent;
    
    //.....
    
    return child;
  
}
```

이런 식으로 프로토타입 체인을 구성하여 부모를 상속 받는 새로운 자식 클래스를 만들어 반환한다.

#### 3) 자식 클래스 확장

```javascript 1.8

/* 얕은 복사로 객체의 프로퍼티 복사 */
for (var i in obj){
    if(obj.hasOwnProperty(i)) child.prototype[i] = obj[i];
}
```

##### [참고: hasOwnProperty 메서드]

- hasOwnProperty 메서드는 프로퍼티를 찾을 때 프로토타입 체인을 타고 올라가지 않고 해당 객체 내에서만 찾는다.

```javascript 1.8

o = new Object();
o.prop = 'exists';
o.hasOwnProperty('prop');               // true
o.hasOwnProperty('toString');           // false
o.hasOwnProperty('hasOwnProperty');     // false
```

#### 4) 생성자 호출

클래스의 인스턴스가 생성될 때, 클래스 내에 정의된 생성자가 호출돼야 한다. 또한 부모 클래스의 생성자 역시 호출되어야 한다.

```javascript 1.8
var child = function(){
    if(parent._init) parent._init.apply(this, arguments);
    if(child.prototype._init) child.prototype._init.apply(this, arguments);
};
```

위 코드는 parent._init이나 child.prototype._init을 찾을 때
_init 프로퍼티가 없으면 프로토타입 체인으로 상위 클래스의 _init 함수를 찾아 호출할 수 있으므로 문제가 있다.

따라서, hasOwnProperty 함수를 활용하여 이 문제를 해결한다.

```javascript 1.8
var child = function(){
    if(parent.hasOwnProperty('_init')) parent._init.apply(this, arguments);
    if(child.prototype.hasOwnProperty('_init')) child.prototype._init.apply(this, arguments);
};
```
위 코드는 부모와 자식이 한 쌍을 이루었을 때만 동작하므로 2세대에 거친 상속 관계에서는 안 된다.

따라서, 부모 클래스의 생성자를 호출하는 코드는 재귀적으로 구현해야 한다.

```javascript 1.8
var child = function() {
    
    var _parent = child.parent_constructor;
    
    /** 현재 클래스의 부모 생성자가 Function이면 최상위 클래스므로 실행하지 않는다. */
    if(_parent && _parent !== Function) _parent.apply(this, arguments);
    if(child.prototype.hasOwnProperty('_init')) child.prototype._init.apply(this, arguments);
    
};
```

#### 5) subClass 보완

최상위 클래스를 Function을 상속 받는 것으로 정하는 코드를 추가한다.

따라서 아래 코드를

```javascript 1.8
    parent = this;
```

아래와 같이 수정한다.

```javascript 1.8
    
    var parent = this === global ? Function : this;
```
또한, subClass 안에서 생성하는 자식 클래스의 역할을 하는 함수는 subClass 함수가 있어야 한다.

따라서 아래 코드를 추가한다.
```javascript 1.8
    /** arguments.callee는 현재 호출된 함수를 의미한다.
        현재 호출된 함수가 subClass이므로 child.subClass는 subClass 함수를 참조한다.
    */
    child.subClass = arguments.callee;
```

```javascript 1.8
/** 완성된 코드 */

function subClass(obj) {
    var parent = this === window ? Function : this;
    var F = function() {};
    
    var child = function() {
        var _parent = child.parent;
        
        if (_parent && _parent !== Function) {
            _parent.apply(this, arguments);
        }
		    
        if (child.prototype._init) {
            child.prototype._init.apply(this, arguments);
        }
    };
    
    F.prototype = parent.prototype;
    child.prototype = new F();
    child.prototype.constructor = child;
    child.parent = parent;
    child.subClass = arguments.callee;
    
    for (var i in obj) {
        if (obj.hasOwnProperty(i)) {
            child.prototype[i] = obj[i];
        }
    }
    
    return child;
}
```

#### 6) SubClass 활용

```javascript 1.8
function subClass(obj) {
    var parent = this === global ? Function : this;
    var F = function() {};
    
    var child = function() {
        var _parent = child.parent;
		    
        if (_parent && _parent !== Function) _parent();
        if (child.prototype._init) child.prototype._init.apply(this, arguments);
    };
    
    F.prototype = parent.prototype;
    child.prototype = new F();
    child.prototype.constructor = child;
    child.parent = parent;
    child.subClass = arguments.callee;
    
    for (var i in obj) {
        if (obj.hasOwnProperty(i)) child.prototype[i] = obj[i];
    }
    return child;
}
    
var person_obj = {
    _init : function() {
        console.log("person init");
    },
    
    getName : function() {
        return this._name;
    },
    
    setName : function(name) {
        this._name = name;
    }
};
    
var student_obj = {
    _init: function () {
        console.log("student init");
    },
    
    getName: function () {
        return "Student Name: " + this._name;
    }
};
    
var Person = subClass(person_obj);          // Person 클래스 정의
var person = new Person();                  // person init 출력
person.setName("Hyeonsik");                 
console.log(person.getName());              // Hyeonsik
    
var Student = Person.subClass(student_obj); // Student 클래스 정의
var student = new Student();                // person init, student init 출력
student.setName("Roger");
console.log(student.getName());             // Student name: Roger
console.log(Person.toString());             // Person이 Function을 상속받는지 확인
```
#### 7) subClass 함수에 클로저 적용

위 subClass 함수 예제에서 임시 함수 객체 F는 subClass 함수가 호출될 때마다 생성되므로 한 번만 생성되게 클로저를 사용한다.

```javascript 1.8
var subClass = function() {
    
    var F = function() { };
    
    var subClass = function(obj) {
      
    };
    
    return subClass;
    
  
}();
```

### 2. subClass 함수와 모듈 패턴을 이용한 객체지향 프로그래밍

```javascript 1.8
var subClass = function () {
    var F = function () {
    };

    var subClass = function (obj) {

        //var parent = this === global ? Function.prototype : this.prototype;
        var parent = this === global ? Function : this;

        var child = function () {
            //console.log("calling const");
            var _parent = child.parent_constructor;

            if (_parent && _parent !== Function) {
                _parent.apply(this, arguments);
            }

            /*
            if (parent.hasOwnProperty("_init")) {
                parent._init.apply(this, arguments);
            }
            */
            if (child.prototype.hasOwnProperty("_init")) {
                child.prototype._init.apply(this, arguments);
            }
        };

        F.prototype = parent.prototype;
        child.prototype = new F();
        child.prototype.constructor = child;
        child.parent = parent.prototype;
        child.parent_constructor = parent;
        child.subClass = arguments.callee;

        for (var i in obj) {
            if (obj.hasOwnProperty(i)) {
                child.prototype[i] = obj[i];
            }
        }

        return child;
    }

    return subClass;
}();

var person = function (arg) {
    var name = undefined;

    return {
        _init: function (arg) {
            name = arg ? arg : "zzoon";
        },
        getName: function () {
            return name;
        },
        setName: function (arg) {
            name = arg;
        }
    };
}

Person = subClass(person());
var iamhjoo = new Person("iamhjoo");
console.log(iamhjoo.getName());

Student = Person.subClass();
var student = new Student("student");
console.log(student.getName());
```
