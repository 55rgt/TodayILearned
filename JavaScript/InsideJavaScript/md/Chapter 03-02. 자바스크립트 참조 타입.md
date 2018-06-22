## 자바스크립트 참조 타입(객체 타입)
자바스크립트에서 객체는 단순히 '이름:값' 형태의 프로퍼티들을 저장하는 컨테이너이다.

자바스크립트에서 기본 타입은 하나의 값만을 가지는 데 비해,
참조 타입인 객체는 여러 개의 프로퍼티들을 포함할 수 있고,
이러한 객체의 프로퍼티는 기본 타입의 값을 포함하거나, 다른 객체를 가리킬 수도 있다.

자바스크립트에서는 이러한 프로퍼티를 메서드라고 부르고, 이러한 메서드의 성질에 따라 객체의 프로퍼티는 함수로 포함될 수 있다. 

###1. 객체 생성

자바에서는 클래스를 정의하고, 클래스의 인스턴스를 생성하는 과정에서 객체가 만들어진다.

이에 비해 자바스크립트에서는 클래스라는 개념이 없고, 객체 리터럴이나 생성자 함수 등 별도의 생성 방식이 존재한다.

####<자바스크립트에서 객체를 생성하는 방법>
- **기본 제공 Object() 객체 생성자 함수를 이용**
- **객체 리터럴 이용**
- **생성자 함수 이용**

####1) Object 생성자 함수 이용
```javascript 1.8
// Object()를 이용하여 foo 빈 객체 생성
var foo = new Object();

// foo 객체 프로퍼티 생성
foo.name = 'foo';
foo.age = 30;
foo.gender = 'male';

console.log(typeof foo); //object
console.log(foo); //{ name: 'foo', age: 30, gender: 'male }

```
####2) 객체 리터럴 방식 이용
**객체 리터럴**: 객체를 생성하는 표기법

중괄효를 이용하여 객체를 생성하고, **"프로퍼티 이름" : "프로퍼티 값"**의 형태로 프로퍼티를 추가할 수 있다.

프로퍼티 값으로는 자바스크립트의 값을 나타내는 어떤 표현식도 올 수 있다. (메서드도 가능하다.)

```javascript 1.8
var foo = {
  name : 'foo',
  age : 30,
  gender : 'male'  
};

console.log(typeof foo); //object
console.log(foo); //{ name: 'foo', age: 30, gender: 'male }
```

###2. 객체 프로퍼티 읽기 / 쓰기 / 갱신

객체는 새로운 값을 가진 프로퍼티를 생성하고, 생성된 프로퍼티에 접근해서 해당 값을 읽거나 원하는 값으로 갱신할 수 있다.

객체의 프로퍼티에 접근하려면 다음과 같이 두 가지 방법을 사용한다.
- **대괄호([]) 표기법**
- **마침표(.) 표기법**
```javascript 1.8
// 객체 리터럴 방식을 통항 foo 객체 생성
var foo = {
  name: 'foo',
  major: 'Computer'  
};
    
// 객체 프로퍼티 읽기
// 객체에 없는 프로퍼티에 접근한다면 undefined 값이 출력된다.
console.log(foo.name);      //foo
console.log(foo['name']);   //foo
console.log(foo.nickname);  //undefined
    
// 객체 프로퍼티 갱신
foo.major = 'English';
console.log(foo.major);     // English
console.log(foo['major']);  // English
    
// 객체 프로퍼티 동적 생성
// 자바스크립트 객체의 프로퍼티에 값을 할당할 때,
// 프로퍼티가 이미 있다면 값이 갱신되고 없다면 새로운 프로퍼티가 동적으로 생성되어 값이 할당된다.
foo.age = 30;
console.log(foo.age);
    
// 대활호 표기법만을 사용해야 하는 경우
// 접근하려는 프로퍼티가 예약어이거나 표현식이라면 대괄호 표기법만을 사용해야 한다.
// 'full-name' 프로퍼티는 '-' 연산자가 있는 표현식이므로 대괄호 표기법을 이용해야 한다.
// foo.full 과 name은 존재하지 않는 프로퍼티이므로 undefined 값을 가진다.
foo['full-name'] = 'foo bar';
console.log(foo['full-name']);  // foo bar
console.log(foo.full-name);     // JavaScript에서는 undefined - undefined = NaN이므로 NaN이 출력된다.
console.log(foo.full);          // undefined
console.log(name);              // undefined
```

###3. for in 문과 객체 프로퍼티 출력
for in 문을 사용하면, 객체에 포함된 모든 프로퍼티에 대해 로프를 수행할 수 있다.

```javascript 1.8
// 객체 리터럴을 통한 foo 객체 생성
var foo = {
    name: 'foo',
    age: 30,
    major: 'Computer'
};
    
// for in 문을 이용한 객체 프로퍼티 출력
var prop;
for(prop in foo){
    console.log(prop, foo[prop])
}
    
/*
    name 'foo'
    age 30
    major 'Computer'
*/
```
###4. 객체 프로퍼티 삭제

자바스크립트에서는 객체의 프로퍼티를 delete 연산자를 이용해 즉시 삭제할 수 있다.

하지만, delete 연산자는 객체의 프로퍼티를 삭제할 뿐, 객체 자체를 삭제하지는 못한다.

```javascript 1.8
var foo = {
    name: 'foo',
    nickname: 'babo'
};

console.log(foo.nickname);  // babo
delete foo.nickname;        // nickname 프로퍼티 삭제
console.log(foo.nickname);  // undefined
delete foo;                 // foo 객체 삭제 시도 (delete 연산자는 프로퍼티만 삭제할 수 있다.)
console.log(foo.name);      // foo
```