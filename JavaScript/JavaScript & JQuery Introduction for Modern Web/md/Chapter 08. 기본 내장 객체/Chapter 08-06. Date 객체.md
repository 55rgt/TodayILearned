## Date 객체

### 1. 생성

```javascript 1.8
/** 생성자 함수에 매개변수가 없으면 현재 시간으로 초기화된다. */
    
var date = new Date();
alert(date);
```

```javascript 1.8
/** 문자열을 사용한 Date 객체 생성 */
    
var date1 = new Date('July 9');
console.log('date1', date1);
    
var date2 = new Date('July 9, 2018');
console.log('date2', date2);
    
var date3 = new Date('July 9, 2018 13:06:08');
console.log('date3', date3);
```

```javascript 1.8
/** 숫자를 사용한 Date 객체 생성
    (연 → 월 → 일 → 시 → 분 → 초 → 밀리초 순서) */
    
var date4 = new Date(1991, 11, 9);
var date5 = new Date(1991, 11, 9, 2, 24, 23);
var date6 = new Date(1991, 11, 9, 2, 24, 23, 1);
```

```javascript 1.8
/** Unix Time을 사용한 Date 객체 생성 */
var date = new Date(2732741033257);
```

### 2. 메서드

```javascript 1.8
/** 일주일 후의 시간 구하기 */

var date = new Date();
    
date.setDate(date.getDate() + 7);
    
alert(date);
```
```javascript 1.8
/** Date 객체의 toString 메서드 */
var date = new Date(2018, 7, 9);
    
var output = '';
    
output += '-toDateString: ' + date.toDateString() + '\n';
output += '-toLocaleDateString: ' + date.toLocaleDateString() + '\n';
output += '-toLocaleString: ' + date.toLocaleString() + '\n';
output += '-toLocaleTimeString: ' + date.toLocaleTimeString() + '\n';
output += '-toString: ' + date.toString() + '\n';
output += '-toTimeString: ' + date.toTimeString() + '\n';
output += '-toUTCString: ' + date.toUTCString() + '\n';
    
alert(output);

```

### 3. 시간 간격 계산

```javascript 1.8
/** 날짜 간격 구하기 */
    
var now = new Date();
var before = new Date('December 9, 1991');
    
var interval = now.getTime() - before.getTime();
interval = Math.floor(interval / (1000 * 60 * 60 * 24));
    
alert('Interval: ' + interval + '일');

```

```javascript 1.8
/** 프로토타입에 날짜 간격 구하는 메서드 추가 */
    
Date.prototype.getInterval = function (otherDate) {
        
    var interval;
    
    if (this > otherDate) interval = this.getTime() - otherDate.getTime();
    else interval = otherDate.getTime() - this.getTime();
    
    return Math.floor(interval / (1000 * 60 * 60 * 24));
};
    
var now = new Date();
var before = new Date('December 9, 1991');
    
alert('Interval: ' + now.getInterval(before) + '일');
```