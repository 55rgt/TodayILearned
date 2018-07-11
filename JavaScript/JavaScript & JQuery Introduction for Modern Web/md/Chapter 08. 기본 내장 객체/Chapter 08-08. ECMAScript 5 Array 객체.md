## ECMAScript 5 Array 객체

### 1. 확인 메서드

- **Array.isArray()**: 배열인지 확인한다.

```javascript 1.8
/** Array.isArray() 메서드 */
alert(Array.isArray([1,2,3]));
alert(Array.isArray({}));
alert(Array.isArray(1));
```
### 2. 탐색 메서드

- **indexOf()**: 특정 요소를 앞쪽부터 검색한다.
- **lastIndexOf()**: 특정 요소를 뒤쪽부터 검색한다.

두 메서드 모두 특정 요소가 없다면 -1을 리턴한다.

```javascript 1.8
var array = [1, 2, 3, 4, 5, 5, 4, 3, 2, 1];
    
var output1 = array.indexOf(4);
var output2 = array.indexOf(8);
var output3 = array.lastIndexOf(4);
var output4 = array.lastIndexOf(8);
    
var output = '';
output += output1 + ' : ' + output2 + '\n';
output += output3 + ' : ' + output4;
    
alert(output);
```
### 3. 반복 메서드

- **forEach()**: 배열의 각각의 요소를 사용해 특정 함수를 for in 반복문처럼 실행한다.
- **map()**: 기존의 배열에 특정 규칙을 적용해 새로운 배열을 만든다.

```javascript 1.8
/** forEach() 메서드 */
var array = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
    
var sum = 0;
var output = '';
array.forEach(function (element, index, array) {
    sum += element;
    output += index + ': ' + element + ' → ' + sum + '\n';
});
    
alert(output);
```
```javascript 1.8
/** map() 메서드 */
var array = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
    
var output = array.map(function (element) {
    return element * element;
});
    
alert(output);
```

### 4. 조건 메서드

- **filter()**: 특정 조건을 만족하는 요소를 추출해 새로운 배열을 만든다.
- **every()**: 배열의 요소가 특정 조건을 모두 만족하는지 확인한다.
- **some()**: 배열의 요소가 특정 조건을 적어도 하나 만족하는지 확인한다.

```javascript 1.8
/** filter 메서드 */
var array = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
    
array = array.filter(function (element, index, array) {
    return element <= 5; // 리턴 값이 true 인 것만 반환한다.
});
    
alert(array);
```
```javascript 1.8
/** every() 메서드와 some() 메서드 */
var array = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
    
function lessThanFive(element, index, array) {
    return element < 5;
}
    
function lessThanTwenty(element, index, array) {
    return element < 20;
}
    
var output1 = array.every(lessThanFive);
var output2 = array.every(lessThanTwenty);
var output3 = array.some(lessThanFive);
var output4 = array.some(lessThanTwenty);
    
var output = '';
output += output1 + ' : ' + output2 + '\n';
output += output3 + ' : ' + output4 + '\n';
alert(output);
```
### 5. 연산 메서드

- **reduce()**: 배열의 요소가 하나가 될 때까지 요소를 왼쪽부터 두 개씩 묶는 함수를 실행한다.
- **reduceRight()**: 배열의 요소가 하나가 될 때까지 요소를 오른쪽부터 두 개씩 묶는 함수를 실행한다.

```javascript 1.8
/** reduce() 메서드 */
var test = [0, 1, 2, 3, 4].reduce(function(accumulator, currentValue) {
    return accumulator + currentValue;
});
    
console.log(test);
```