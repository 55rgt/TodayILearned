## ECMAScript 5 Object 객체

### 1. 객체 속성 추가

- **Object.defineProperty()**: 객체에 속성을 추가한다.
- **Object.defineProperties()**: 객체에 속성들을 추가한다.

```javascript 1.8
/** Object.defineProperty() 메서드
*   첫 번째 매개변수에는 속성을 추가하려는 객체를 입력하고,
*   두 번째 매개변수에는 속성의 이름을 입력한다. */
var object = {};
Object.defineProperty(object, 'name', {
    
});
```

#### [ECMAScript 5의 객체 속성 관련 옵션]

- **value**: 속성의 값을 의미
- **writable**: 객체의 속성 값을 변경할 수 있는지를 의미
- **get**: 게터
- **set**: 세터
- **configurable**: 속성의 설정을 변경할 수 있는지를 의미
- **enumerable**: for in 반복문으로 검사할 수 있는지를 의미

옵션을 입력하지 않으면 자동으로 false 또는 undefined가 입력된다.

```javascript 1.8
/** 옵션 값 설정 */    
var object = {};
Object.defineProperty(object, 'name', {
    value: 'Roger',
    writable: false,
    enumerable: false
});
    
// writable이 false이므로 값이 변경되지 않는다.    
object.name = 'OTHER';
    
// enumerable이 false이므로 반복문이 작동하지 않는다.
for (var i in object) {
    alert(i + ' : ' + object[i]);
}
    
alert(object.name);
```
```javascript 1.8
/** get과 set 옵션 */
var object = {};
var value = 'test';
Object.defineProperty(object, 'name', {
    
    // 별도의 메서드를 사용하지 않았지만, 객체를 사용할 때 자동으로 게터와 세터를 실행하는 것을 알 수 있다.
    get: function () {
        alert('GETTER');
        return value;
    },
    set: function (newValue) {
        alert('SETTER: ' + newValue);
        value = newValue;
    }
});
    
object.name = 'ALPHA';
alert(object.name);
```
configurable 옵션을 true로 설정하면 이미 지정한 속성의 설정을 변경할 수 있다.
```javascript 1.8
/** configurable 옵션 */
var object = {};
var value = 'test';
    
Object.defineProperty(object, 'name', {
    get: function () { return value; },
    set: function (newValue) { value = newValue; },
    configurable: true
});
    
Object.defineProperty(object, 'name', {
    enumerable: true
});
```

```javascript 1.8
/** Object.defineProperties() 메서드 */
var object = {};
Object.defineProperties(object, {
    name: { value: 'Roger' },
    region: { value: 'San Francisco' }
});
```

### 2. 객체 생성

- **Object.create()**: 객체를 생성한다.

**생성자 함수는 틀을 기반으로 객체를 찍어내 객체를 생성하지만, create() 메서드는 기존에 있던 객체를 복제하고 새로운 속성을 추가하여 객체를 생성한다.**

첫 번째 매개변수에는 원본이 되는 객체를 입력하고, 두 번째 매개변수에는 추가하고자 하는 속성을 넣는다.

```javascript 1.8
/** Object.create() 메서드 */
var object = Object.create({}, {
    name: { value: 'Roger', enumerable: true },
    region: { value: 'Finland', enumerable: true }
});
    
alert(Object.keys(object));
```

create() 메서드를 사용하면 기존의 객체를 기반으로 새로운 속성을 지정할 수 있으므로 상속이라고 볼 수 있다.
```javascript 1.8
/** 상속 */
var object = Object.create({}, {
    name: { value: 'Roger', enumerable: true },
    region: { value: 'Pusan', enumerable: true }
});
    
var person = Object.create(object, {
    gender: { value: '남자', enumerable: true },
    hobby: { value: 'sleeping', enumerable: true }
});
    
alert(Object.getOwnPropertyNames(person) + '\n' + Object.keys(person)); // gender, hobby    gender, hobby
alert(person.name + ' : ' + person.region);                             // Roger: Pusan
```

getOwnPropertyNames() 메서드나 keys() 메서드를 사용하면 현재 객체에서 새로 지정한 객체만 출력한다.

하지만 부모 객체가 가진 name 속성과 region 속성도 사용할 수 있다.

일반적으로 속성은 캡슐화하지만 메서드는 공개하기 위해 만들기 때문에,
메서드는 defineProperty()와 같은 속성 지정 함수가 아니라 기존 자바스크립트처럼 간단하게 추가한다.

### 3. 동적 속성 추가 제한

ECMAScript 5 부터는 동적으로 속성을 추가하는 것을 제한할 수 있다.

- **Object.preventExtensions()**: 객체의 속성 추가를 제한한다.
- **Object.isExtensible()**: 객체에 속성 추가가 가능한지 확인한다.

```javascript 1.8
/** 객체의 동적 속성 추가 */
var object = {};
    
object.region = 'Seoul';
    
Object.defineProperty(object, 'name', {
    value: 'Kim',
    writable: false
});
```

```javascript 1.8
/** preventExtensions 사용 */
    
var object = {};
    
object.region = 'Seoul';
    
Object.defineProperty(object, 'name', {
    value: 'Kim',
    writable: false
});
    
alert(Object.isExtensible(object)); // true 
Object.preventExtensions(object);
alert(Object.isExtensible(object)); // false
    
object.dream = 'Billionaire'; // 오류는 발생하지 않지만, 추가되진 않음.
    
Object.defineProperty(object, 'oops', { // 오류 발생
    value: true
});
```

### 4. 동적 속성 삭제 제한

ECMAScript 5 부터는 동적으로 속성을 삭제하는 것을 제한할 수 있다.

- **Object.seal()**: 객체의 속성 삭제를 제한한다.
- **Object.isSealed()**: 객체에 속성 삭제가 가능한지 확인한다.

```javascript 1.8
var person = {
    name: 'Kim',
    region: 'Seoul',
    hobby: 'Sleeping'
};
    
Object.seal(person);
delete person.name; // 제거하지 못한다.
    
alert(person.name);
```

- **Object.freeze()**: 객체의 속성 삭제와 수정를 제한한다.
- **Object.isFrozen()**: 객체에 속성 삭제와 수정이 가능한지 확인한다.

### 5. 객체 보조 메서드

##### [ECMAScript 5의 객체 보조 메서드]

- **Object.keys()**: 순환 가능한 객체 자신 소유의 속성을 배열로 만든다.
- **Object.getOwnPropertyNames()**: 모든 객체 자신 소유의 속성을 배열로 만든다.
- **Object.getOwnPropertyDescription()**: 특정 속성의 옵션 객체를 추출한다.

```javascript 1.8
/** keys() 메서드와 getOwnPropertyNames() 메서드 비교 */
var object = { name: 'Tom' };
    
Object.defineProperty(object, 'region', {
    value: 'Seoul'
});
    
alert(Object.keys(object));                     // name
alert(Object.getOwnPropertyNames(object));      // name, region
```

defineProperty() 메서드로 속성을 추가하면 기본 enumerable 옵션은 false이다.

keys() 메서드는 enumerable이 true인 것만 배열로 만들고,
getOwnpropertyNames() 메서드는 가지고 있는 모든 속성을 배열로 만들기 때문에 차이가 나타난다.

```javascript 1.8
/** Object.getOwnPropertyDescriptor() 메서드 */    
var object = { name: 'Tom' };
Object.defineProperty(object, 'region', { value: 'Seoul' });
    
var descriptors = [];
descriptors.push(Object.getOwnPropertyDescriptor(object, 'name'));
descriptors.push(Object.getOwnPropertyDescriptor(object, 'region'));
    
var output = '';
for (var i in descriptors) {
    var item = descriptors[i];
    for (var key in item) output += key + ' : ' + item[key] + '\n';
    output += '--------------------\n';
}
alert(output);
```
