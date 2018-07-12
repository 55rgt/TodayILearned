## screen 객체

screen 객체는 웹 므라우저의 화면이 아니라 운영체제 화면의 속성을 가지는 객체이다.

```javascript 1.8
var output = '';
for (var key in screen) output += '●' + key + ': ' + screen[key] + '\n';
alert(output);
```

##### [screen 객체의 속성]

- **width**: 화면의 너비
- **height**: 화면의 높이
- **availWidth**: 실제 화면에서 사용 가능한 너비
- **availHeight**: 실제 화면에서 사용 가능한 높이
- **colorDepth**: 사용 가능한 색상 수
- **pixelDepth**: 한 픽셀 당 비트 

```javascript 1.8
/** 브라우저의 크기 화면에 일치 */
var child = window.open('', '', 'width=300, height=200');
var width = screen.width;
var height = screen.height;
    
child.moveTo(0, 0);
child.resizeTo(width, height);
    
setInterval(function () {
    child.resizeBy(-20, -20);
    child.moveBy(10, 10);
}, 1000);
```