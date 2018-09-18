### Vue 인스턴스 만들기
-모든 Vue 앱은 **Vue** 함수로 새 인스턴스를 만드는 것에서 시작한다.

```javascript 1.8
var vm = new Vue({
  // 옵션
})
```

-Vue 인스턴스를 인스턴스화 할 때는 데이터, 템플릿, 마운트할 엘리먼트, 메소드, 라이프사이클 콜백 등의 옵션을 포함 할 수 있는 **options 객체**를 전달해야 한다.

-Vue 생성자는 미리 정의된 옵션으로 재사용 가능한 **컴포넌트 생성자**를 생성하도록 확장될 수 있다.

-Vue 앱은 **new Vue**를 통해 만들어진 **루트 Vue 인스턴스**로 구성되며 선택적으로 중첩 및 재사용이 가능한 컴포넌트 트리로 구성된다.

```text
[Todo 앱의 컴포넌트 트리 예시]

Root Instance
└─ TodoList
   ├─ TodoItem
   │  ├─ DeleteTodoButton
   │  └─ EditTodoButton
   └─ TodoListFooter
      ├─ ClearTodosButton
      └─ TodoListStatistics
```
-모든 Vue 컴포넌트 본질적으로 확장된 Vue 인스턴스이다.

### 속성과 메서드

-각 Vue 인스턴스는 **data** 객체에 있는 모든 속성을 **프록시** 처리한다.
```javascript 1.8
// 데이터 객체
var data = { a: 1 }

// Vue인스턴스에 데이터 객체를 추가합니다.
var vm = new Vue({
  data: data
})

// 같은 객체를 참조합니다!
console.log(vm.a === data.a); // => true

// 속성 설정은 원본 데이터에도 영향을 미칩니다.
vm.a = 2
console.log(data.a); // => 2

// ... 당연하게도
data.a = 3
console.log(vm.a); // => 3
```
-데이터가 변경되면 화면은 다시 렌더링되는데, **data**에 있는 속성들은 인스턴스가 생성될 때 존재한 것들만 **반응형**이라는 것을 유념해야 한다.
```javascript 1.8
/** 아래 새 속성을 추가하면, b가 변경되어도 화면이 갱신되지 않는다. */
vm.b = 'hi';
// 만약 data.b = sth을 한다변 vm에는 추가가 되는지?
```
-어떤 속성이 나중에 필요하다는 것을 알고 있고, 빈 값이거나 존재하지 않은 상태로 시작한다면 초기값을 지정할 필요가 있다.
```javascript 1.8
data: {
  newTodoText: '',
  visitCount: 0,
  hideCompletedTodos: false,
  todos: [],
  error: null
}
``` 
-하지만 **Object.freeze()** 를 사용하면 기존 속성이 변경되는 것이 막혀서 반응성 시스템이 추적할 수 없게 된다.
```javascript 1.8
var obj = {
  foo: 'bar'
}
Object.freeze(obj);
new Vue({
  el: '#app',
  data: obj
})
```
```html
<div id="app">
  <p>{{ foo }}</p>
  <!-- obj.foo는 더이상 변하지 않습니다! -->
  <button v-on:click="foo = 'baz'">Change it</button>
</div>
```
-Vue 인스턴스는 데이터 속성 이외에도 유용한 인스턴스 속성 및 메서드를 제공한다. (**$** 접두어를 붙인다.)

```javascript 1.8
var data = { a: 1 }
var vm = new Vue({
  el: '#example',
  data: data
})
console.log(vm.$data === data); // => true
console.log(vm.$el === document.getElementById('example')); // => true
// $watch 는 인스턴스 메소드 입니다.
vm.$watch('a', function (newVal, oldVal) {
  // `vm.a`가 변경되면 호출 됩니다.
})
```
### 인스턴스 라이프사이클 
-각 Vue 인스턴스는 데이터 관찰을 설정하고, 템플릿을 컴파일하고, 인스턴스를 DOM에 마운트하고, 데이터가 변경될 때, DOM을 업데이트해야 할 때 초기화 단계를 거친다.

-그 과정에서 사용자 정의 로직을 실행할 수 있는 **라이프사이클 훅**이 호출된다.

-예를 들어, **created** 훅은 인스턴스가 생성된 후에 호출된다.

```javascript 1.8
new Vue({
  data: {
    a: 1
  },
  created: function () {
    // `this` 는 vm 인스턴스를 가리킵니다.
    console.log('a is: ' + this.a)
  }
})
// => "a is: 1"
```
-인스턴스 라이프사이클의 여러 단계에서 호출될 **mounted**, **updated**, **destroyed** 등의 다른 훅도 있다.

-모든 라이프사이클 훅은 **this** 컨텐스트가 **호출하는 Vue 인스턴스**를 가리키며 호출된다.

#### 참고 사항

-options 속성이나 콜백에 람다 함수 사용을 지양하자!

-람다 함수(화살표 함수)는 부모 컨텍스트에 바인딩되기 때문에 **this**가 다르게 바인딩된다! 
