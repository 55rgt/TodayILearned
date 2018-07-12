## window 객체의 기본 메서드

##### [window 객체의 메서드]

- **moveBy(x,y)**: 윈도우의 위치를 상대적으로 이동시킨다.
- **moveTo(x,y)**: 윈도우의 위치를 절대적으로 이동시킨다.
- **resizeBy(x,y)**: 윈도우의 크기를 상대적으로 지정한다.
- **resizeTo(x,y)**: 윈도우의 크기를 절대적으로 지정한다.
- **scrollBy(x,y)**: 윈도우 스크롤의 위치를 상대적으로 이동한다.
- **scrollTo(x,y)**: 윈도우 스크롤의 위치를 절대적으로 이동한다.
- **focus()**: 윈도우에 초점을 맞춘다.
- **blur()**: 윈도우에 초점을 제거한다.
- **close()**: 윈도우를 닫는다.

```javascript 1.8
/** 상대 이동과 절대 이동의 차이 */
var child = window.open('', '', 'width=300, height=200');
    
child.moveTo(0, 0);
    
setInterval(function () {
    child.moveBy(10, 10);
}, 1000);
```