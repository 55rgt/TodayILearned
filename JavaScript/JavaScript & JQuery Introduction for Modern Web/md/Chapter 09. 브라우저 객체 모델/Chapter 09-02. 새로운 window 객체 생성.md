## 새로운 window 객체 생성

window 객체는 open 메서드로 새로운 window 객체를 생성한다.

- **open(URL, name, features, replace)**: 새로운 window 객체를 생성한다.
    - **URL**: 열고자 하는 HTML 페이지의 URL
    - **name**: 윈도우 간 통신하는 데 사용하는 윈도우 이름
    - **features**: 윈도우를 어떤 모양으로 출력할지 지정하는 옵션

```javascript 1.8
/** open 메서드 */
window.open();
window.open('https://www.naver.com', 'naver', 'width = 600, height = 400', true);
```

##### [윈도우 형태 옵션]

- **height**: 새 윈도우의 높이 / 픽셀 값
- **width**: 새 윈도우의 너비 / 픽셀 값
- **location**: 주소 입력창의 유무 / yes, no, 1, 0
- **menubar**: 메뉴 유무 / yes, no, 1, 0
- **resizable**: 화면 크기 조절 가능 여부 / yes, no, 1, 0
- **status**: 상태 표시줄의 유무 / yes, no, 1, 0
- **toolbar**: 상태 표시줄의 유무 / yes, no, 1, 0

open() 메서드는 윈도우 객체를 리턴하므로 새롭게 만든 객체에 속성과 메서드를 사용할 수 있다.

```javascript 1.8
/** open() 메서드의 리턴값 */
var child = window.open('', '', 'width=300, height=200');
child.document.write('<h1>From Parent Window</h1>');
```