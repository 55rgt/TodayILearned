## 기본 자료형과 객체의 차이점

```javascript 1.8
/** 기본 자료형과 객체 */
var primitive = 273;
var object = new Number(273);
        
var output= '';
output += typeof(primitive) + ': ' + primitive + '\n';
output += typeof(object) + ': ' + object + '\n';
    
alert(output);
```

기본 자료형에도 속성과 메서드가 있다.

→ 기본 자료형의 속성이나 메서드를 사용하면 **기본 자료형이 자동으로 객체로 변환**된다.

기본 자료형은 **객체가 아니므로 속성과 메서드를 추가할 수 없다.**

```javascript 1.8

/** 기본 자료형에 메서드 추가 */

var primitive = 273;
    
primitive.method = function() {  
    return 'Primitive';
};
    
var output = primitive.method() + '\n';
alert(output);  // Uncaught TypeError: primitive.method is not a function
```

기본 자료형에 속성이나 메서드를 추가하기 위해서는, **생성자 함수의 프로토타입에 메서드를 추가하면 된다.**

```javascript 1.8
/** 생성자 함수에 메서드 추가 */
var primitive = 273;
var object = new Number(273);
    
Number.prototype.method = function() {
    return 'Method on Prototype';
};
    
var output = '';
    
//프로토타입에 메서드를 추가하면 기본 자료형에게도 새로운 메서드가 생성된다.
output += primitive.method() + '\n';
output += object.method() + '\n';
    
alert(output);
```


