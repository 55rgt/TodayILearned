## 배열

자바스크립트의 배열은 C나 자바와 달리 굳이 크기를 지정하지 않아도 되며,
어떤 위치에 어느 타입의 데이터를 지정하더라도 에러가 발생하지 않는다.

### 1. 배열 리터럴
**배열 리터럴**은 자바스크립트에서 새로운 배열을 만드는 데 사용하는 표기법이다.

배열 리터럴은 **대괄호**(**[]**)를 사용한다.

```javascript 1.8
/* 배열 리터럴을 통한 배열 생성 */
var colorArr = ['orange', 'yellow', 'blue', 'green', 'red'];
console.log(colorArr[0]);   // orange
console.log(colorArr[1]);   // yellow
console.log(colorArr[4]);   // red
```
객체 리터럴에서는 **프로퍼티 이름**과 **값 쌍**을 표기했지만, 배열 리터럴에서는 **각 요소의 값**만을 표기한다.

### 2. 배열의 요소 생성

자바스크립트 배열은 값을 순차적으로 넣을 필요 없이 아무 인덱스 위치에나 값을 동적으로 추가할 수 있다.
```javascript 1.8

var arr = [];
    
console.log(arr[0]); //undefined
                       
    
arr[0] = 100;
arr[3] = 'eight';
arr[9] = true;
    
console.log(arr); // [ 100, <2 empty items>, 'eight', <5 empty items>, true ]
```

### 3. 배열의 length 프로퍼티
자바스크립트의 모든 배열은 **length 프로퍼티**가 있다.  
하지만, 자바스크립트 배열은 값을 동적으로 처리할 수 있으므로 length 프로퍼티는 **배열 내에 가장 큰 인덱스에 1은 더한 값**이다.

```javascript 1.8
var arr = [];
console.log(arr.length);    // 0
    
arr[0] = 0;
arr[1] = 1;
arr[2] = 2;
arr[100] = 100;
console.log(arr.length);    // 101

```
배열의 length 프로퍼티 값을 명시적으로 변경할 수도 있다.

```javascript 1.8
var arr = [0, 1, 2];
console.log(arr.length);
    
arr.length = 5;
console.log(arr); // [ 0, 1, 2, <2 empty items> ]

arr.length = 2;
console.log(arr);   // [0, 1]
console.log(arr[2]); // undefined
```

#### 배열 표준 메서드와 length 프로퍼티

배열 메서드는 **length 프로퍼티**를 기반으로 동작하고 있다.

```javascript 1.8
var arr = ['one', 'two', 'three'];
    
arr.push('four');
console.log(arr);   // ['one','two','three','four']
    
arr.length = 5;
arr.push('four');
console.log(arr);   // ['one','two','three', <1 empty item>, 'four']
```

### 4. 배열과 객체

```javascript 1.8

var colorsArray = ['orange','yellow','green'];
console.log(colorsArray[0]);    // orange
console.log(colorsArray[1]);    // yellow
console.log(colorsArray[2]);    // green
    
var colorsObj = {
    
    '0': 'orange',
    '1': 'yellow',
    '2': 'green' 
};
    
/** []연산자 내에 숫자가 사용되면, 해당 숫자를 자동적으로 문자열 형태로 바꿔준다. */

console.log(colorsObj[0]);      // orange
console.log(colorsObj[1]);      // yellow
console.log(colorsObj[2]);      // green
    
/** colorObj는 객체이므로 length 프로퍼티가 없다. */
console.log(typeof colorsArray);    //object
console.log(typeof colorsObj);      //object
    
console.log(colorsArray.length);    //3
console.log(colorsObj.length);      //undefined
    
/** 부모 객체 프로토타입: Object.prototype vs Array.prototype 
*   Object.prototype은 Array.prototype의 부모이다. */
colorsArray.push('red');        // ['orange','yellow','green','red']
colorsObj.push('red');          // Error Occurred

```

크롬 브라우저를 통해 아래의 코드를 실행하면 객체의 프로토타입을 살펴볼 수 있다.

```javascript 1.8
var emptyArray = [];    // 배열 리터럴을 통한 빈 배열 생성
var emptyObj = {};      // 객체 리터럴
console.dir(emptyArray.__proto__);
console.dir(emptyObj.__proto__);
```
### 5. 배열의 프로퍼티 동적 생성
배열도 객체처럼 동적으로 프로퍼티를 추가할 수 있다.

```javascript 1.8
/** 배열에 동적 프로퍼티가 추가되면 length 값이 바뀌지 않는다. */
var arr = ['zero','one','two'];
console.log(arr.length);        // 3
    
arr.color = 'blue';
arr.name = 'number_array';
console.log(arr.length);        // 3
    
arr[3] = 'red';
console.log(arr.length);        // 4
    
console.dir(arr);               // [ 'zero', 'one', 'two', 'red', color: 'blue', name: 'number_array' ]

``` 
### 6.배열의 프로퍼티 열거

배열을 출력할 때 for ~ in과 for 문 중 어떤 것을 쓸 지 비교하자.

```javascript 1.8
for (var prop in arr){
    console.log(prop, arr[prop]);
}

/*
    0 zero
    1 one
    2 two
    3 red
    color blue
    name number_array
 */
    
/** for 문은 배열의 요소만을 출력한다. */
for(var i = 0 ; i < arr.length ; i++){
    console.log(i, arr[i]);
}
/*
    0 'zero'
    1 'one'
    2 'two'
    3 'red'
 */
```

### 7. 배열 요소 삭제

배열도 객체이므로, 배열 요소나 프로퍼티를 삭제하는 데 delete 연산자를 사용할 수 있다.

delete 연산자로 배열 요소를 삭제하는 것은 원소 자체를 삭제하지는 않는다. (undefined로 만들 뿐..)
```javascript 1.8
var arr = ['zero','one','two','three'];
delete arr[2];
console.log(arr);           // [ 'zero', 'one', <1 empty item>, 'three' ]
console.log(arr.length);    // 4
```

배열에서 요소를 완전 삭제할 때에는 **splice() 배열 메서드**를 사용한다.

    splice() 배열 메서드
        
    - start: 배열에서 시작 위치
    - deleteCount: start에서 지정한 시작 위치부터 삭제할 요소의 수
    - item: 삭제할 위치에 추가할 요소
```javascript 1.8
var arr = ['zero','one','two','three'];
arr.splice(2, 1);           // 2번째 요소를 시작점으로 1개의 원소를 삭제한다.
console.log(arr);           // ['zero','one','three'];
console.log(arr.length);    // 3
```

### 8. Array() 생성자 함수
배열 리터럴도 Array() 생성자 함수로 배열을 생성하는 과정을 단순화시킨 것이다.
    
생성자 함수로 배열과 같은 객체를 생성할 때에는 반드시 **new 연산자**를 같이 써야 한다.

Array() 생성자 함수는 호출할 때 인자 개수에 따라 동작이 다르므로 주의해야 한다.

- 호출할 때 인자가 1개이고, 숫자인 경우: 호출된 인자를 length로 갖는 빈 배열 생성
- 그 외의 경우: 호출된 인자를 요소로 갖는 배열 생성
```javascript 1.8

var foo = new Array(3);
console.log(foo);           // [ <3 empty items> ]
console.log(foo.length);    // 3
        
var bar = new Array(1,2,3); 
console.log(bar);           // [ 1, 2, 3 ]
console.log(bar.length);    // 3
```

### 9. 유사 배열 객체

**유사 배열 객체(array-like objects)**: length 프로퍼티를 가진 객체

유사 배열 객체는 객체임에도 불구하고 자바스크립트의 표준 배열 메서드를 사용하는 게 가능하다는 것이다.


```javascript 1.8
var arr = ['bar'];
var obj = {
    name : 'foo',
    length: 1
};
    
arr.push('baz');
console.log(arr);       // [ 'bar', 'baz' ]
  
/** 유사 배열 객체는 배열이 아니므로 에러가 발생한다.*/   
obj.push('baz');        // Error occurred!
```
유사 배열 객체는 **apply() 메서드**를 사용하여 객체지만 표준 배열 메서드를 활용하는 것이 가능하다.

```javascript 1.8
var arr = ['bar'];
var obj = { name : 'foo', length: 1};
    
arr.push('baz');
console.log(arr);       // [ 'bar', 'baz' ]
    
Array.prototype.push.apply(obj, ['baz']);
console.log(obj);       // { '1': 'baz', name: 'foo', length: 2 }
```
