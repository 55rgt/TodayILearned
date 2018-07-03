## Array 객체

### 1. 생성

#### Array 생성자 함수

- **Array()**: 빈 배열을 만든다.
- **Array(number)**: 매개변수만큼의 크기를 가지는 배열을 만든다.
- **Array(mixed, ..., mixed)**: 매개변수를 배열로 만든다.

```javascript 1.8
/** Array 객체 생성 */
var array1 = [52, 273, 103, 57, 32];
var array2 = new Array();
var array3 = new Array(10);
var array4 = new Array(52, 273, 103, 57, 32);
```

### 2. 속성과 메서드

#### Array 객체의 속성

- **length**: 배열 요소의 개수를 알아냅니다.
```javascript 1.8

var array = ['A', 'B', 'C', 'D'];
    
var output = '';
for (var i = 0; i < array.length; i++) output += i + ' : ' + array[i] + '\n';
    
alert(output);
```
#### Array 객체의 메서드

기울어진 메서드는 자기 자신을 변화시킨다.

- **concat()**: 매개변수로 입력한 배열의 요소를 모두 합쳐 배열을 만들어 리턴한다.
- **join()**: 배열 안의 모든 요소를 문자열로 만들어 리턴한다.
- _**pop()**_: 배열의 마지막 요소를 제거하고 리턴한다.
- _**push()**_: 배열의 마지막 부분에 새 요소를 추가한다.
- _**reverse()**_: 배열의 요소 순서를 뒤집는다.
- **slice()**: 배열 요소의 지정한 부분을 리턴한다.
- _**sort()**_: 배열의 요소를 정렬한다.   (**문자열 오름차순**으로 정렬된다.)
- _**splice()**_: 배열 요소의 지정한 부분을 삭제하고 삭제한 요소를 리턴한다.

### 3. 정렬



### 4. 요소 제거