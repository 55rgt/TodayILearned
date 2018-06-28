## 클로저

### 1. 클로저의 개념
    
```javascript 1.8
function outerFunc(){
    var x = 10;
    var innerFunc = function() {
        console.log(x);
    }
    return innerFunc;
}
    
var inner = outerFunc();
inner();
```
    
자바스크립트의 함수는 일급 객체로 취급된다.

-이는 함수를 다른 함수의 인자로 넘길 수도 있고, return으로 함수를 통째로 반환받을 수도 있음을 의미한다.

**클로저**: **이미 생명 주기가 끝난 외부 함수의 변수를 참조하는 함수** → 자유 변수에 엮여 있는 함수 

- 앞 예제에서는 **최종 반환되는 함수가 외부 함수의 지역변수에 접근**하고 있다.
- 이 지역변수에 접근하려면, **함수가 종료되어 외부 함수의 컨텍스트가 반환되더라도 변수 객체가 반환되는 내부 함수의 
스코프 체인에 그대로 남아있어야만 가능**하다.
- 이 때 클로저가 이용된다.

**자유 변수**: **클로저로 참조되는 외부 변수**

-위 예시에서는 outerFunc에서 선언된 x를 참조하는 innerFunc가 **클로저**이다.
또한, outerFunc의 변수 x는 **자유 변수**이다.

```javascript 1.8
function outerFunc(arg1, arg2) {
    var local = 8;
    function innerFunc(innerArg) {
        console.log((arg1 + arg2)/(innerArg + local));      
    }
    return innerFunc;
}
    
var exam1 = outerFunc(2, 4);
exam1(2);
```

- outerFunc()가 실행되면서 생성되는 변수 객체가 스코프 체인에 들어가게 되고, 이 스코프 체인은 innerFunc의 스코프 체인으로 참조된다.
- 즉, outerFunc()가 종료되었지만, 여전히 내부 함수 innerFunc()의 [[scope]]로 참조되므로 GC의 대상이 되지 않고 접근이 가능하다.
- 따라서, exam1(n)을 호출하여도, innerFunc()에서 참조하고자 하는 변수 local에 접근이 가능하다.

### 2. 클로저의 활용

클로저는 성능적인 면과 자원적인 면에서 약간 손해를 볼 수 있으므로 무차별적으로 사용해서는 안 된다.

##### 1) 특정 함수에 사용자가 정의한 객체의 메서드 연결하기

```javascript 1.8
function HelloFunc(func){
    this.greeting = 'hello';
}
    
HelloFunc.prototype.call = function(func) {
    func ? func(this.greeting) : this.func(this.greeting);
}
    
var userFunc = function(greeting) {
    console.log(greeting);
}
    
var objHello = new HelloFunc();
    
objHello.func = userFunc;
objHello.call();
    
// 출력: hello

```
- 함수 HelloFunc는 greeting 변수가 있고, func 프로퍼티로 참조되는 함수를 call() 함수로 호출한다.
- 사용자는 func 프로퍼티에 자신이 정의한 함수를 참조시켜 호출할 수 있다.
- 위 예제에서 사용자는 userFunc() 함수를 정의하여 HelloFunc, func()에 참조시킨 뒤, HelloFunc()의 지역 변수인 greeting을 화면에 출력시킨다.



```javascript 1.8

function HelloFunc(func) {
    this.greeting = "hello";
}
    
HelloFunc.prototype.call = function(func) {
    func ? func(this.greeting) : this.func(this.greeting);
};
    
var userFunc = function(greeting) {
    console.log(greeting);
};
    
var objHello = new HelloFunc();
objHello.func = userFunc;
    
function saySomething(obj, methodName, name) {
    
    return (function(greeting) {
        return obj[methodName](greeting, name);
    });
}
    
function newObj(obj, name) {
    obj.func = saySomething(this, "who", (name || "everyone"));
    
    return obj;
}
    
newObj.prototype.who = function(greeting, name) {
    console.log(greeting + " "  +  (name || "everyone") );
};
    
var obj1 = new newObj(objHello, "zzoon");
obj1.call(); // "hello zzoon"
```
- 첫 번째 인자 obj의 func 프로퍼티에 saysomething() 함수에서 반환되는 함수를 참조하고, 반환한다.
- 결국 obj1은 인자로 넘겼던 objHello 객체에서 func 프로퍼티에 참조된 함수만 바뀐 객체가 된다.
- saySomething() 함수 안에서는 아래와 같은 작업이 수행된다.


    첫 번째 인자: newFunc 객체 - obj1
    두 번째 인자: 사용자가 정의한 메서드 이름 - 'who'
    세 번쨰 인자: 사용자가 원하는 사람 이름 값 - 'zzoon'
        
    반환: 사용자가 정의한 newFunc.prototype.who() 함수를 반환하는 helloFunc()의 func 함수

- obj1.call()로 실행되는 것은 실질적으로 newFunc.prototype.who()가 된다.

##### 2) 함수의 캡슐화

```javascript 1.8
var getCompletedStr = (function(){
        
    var buffAr = [
        'I am ',
        '',
        '. I live in ',
        '',
        '. I\'am ',
        '',
        ' years old.'
    ];
    
    return (function(name, city, age) {
        buffAr[1] = name;
        buffAr[3] = city;
        buffAr[5] = age;
    
        return buffAr.join('');
    });
})();
    
var str = getCompletedStr('zzoon', 'seoul', 16);
    
console.log(str);

```

##### 3) setTimeout()에 지정되는 함수의 사용자 정의

setTimeout 함수는 웹 브라우저에서 제공하는 함수인데, 첫 번째 인자로 넘겨지는 함수 실행의 스케줄링을 할 수 있다.

두 번째 인자인 밀리 초 단위 숫자만큼의 시간 간격으로 해당 함수를 호출한다.

setTimeout()으로 자신의 코드를 호출하고 싶다면, 첫 번째 인자로 해당 함수 객체의 참조를 넘겨주면 되지만 실제 실행될 때 함수에 인자를 줄 수 없다.

클로저로 이 문제를 해결할 수 있다.

```javascript 1.8
function callLater(obj, a, b) {
    
    return (function(){
        obj["sum"] = a + b;
        console.log(obj["sum"]);
    });
}
    
var sumObj = {
	sum : 0
};
    
var func = callLater(sumObj, 1, 2);
    
setTimeout(func, 500);
```
사용자가 정의한 함수 callLater를 setTimeout 함수로 호출하려면, 변수 func에 함수를 반환받아 setTimeout() 함수의 첫 번째 인자로 넣어주면 된다.

### 3. 클로저 활용 시 주의사항

##### 1) 클로저의 프로퍼티 값이 쓰기 가능하므로 그 값이 여러 번 호출로 항상 변할 수 있음에 유의하자.
```javascript 1.8
function outerFunc(argNum) {
  var num = argNum;
  return function(x) {
      num += x;
      console.log('num: ' + num );
    }
}
 
var exam = outerFunc(40);
 
exam(5);
exam(-10);
```

##### 2) 하나의 클로저가 여러 함수 객체의 스코프 체인에 들어가 있는 경우도 있다.
```javascript 1.8

function func(){
   var x = 1;
   return {
      func1 : function(){ console.log(++x); },
      func2 : function(){ console.log(-x); }
   };
};
    
var exam = func();
exam.func1();
exam.func2();

```

##### 3) 루프 안에서 클로저를 활용할 때는 주의하자.

```javascript 1.8
function countSeconds(howMany) {
  for (var i = 1; i <= howMany; i++) {
    setTimeout(function () {
      console.log(i);
    }, i * 1000);
  }
};
countSeconds(3);
```
- 위 코드는 4가 연속으로 3번 1초 간격으로 출력된다.
- setTimeout 함수의 인자로 들어가는 함수는 자유 변수 i를 참조하는데, 이 함수가 실행되는 시점은 countSeconds 함수 실행이 종료된 이후이므로 i는 이미 4가 되었다.

```javascript 1.8
function countSeconds(howMany) {
  for (var i = 1; i <= howMany; i++) {
      (function (currentI) {
      setTimeout(function () {
        console.log(currentI);
      }, currentI * 1000);
    }(i));
  }
};
countSeconds(3);
```
- 즉시 실행 함수를 실행시켜서 루프 i 값을 currentI에 복사하여 원하는 결과를 얻는다.