## 문서 객체의 스타일 조작

문서 객체의 **style 속성**을 사용하면 해당 문서 객체의 스타일을 변경할 수 있다.

```javascript 1.8
window.onload = function () {
    
    var header = document.getElementById('header');
    
    header.style.border = '2px solid black';
    header.style.color = 'orange';
    header.style.fontFamily = 'helvetica';
};
```

#### 참고: 스타일 속성 이름

자바스크립트에서는 특수 기호 "-"를 사용할 수 없으므로 "-"로 연결된 속성은 아래와 같이 사용한다.

```javascript 1.8
var header = document.getElementById('header');
header.style.backgroundColor = 'pink';
```
##### 스타일 식별자 변환 예시)

- background-image ----------> backgroundImage
- background-color ----------> backgroundColor
- box-sizing       ----------> boxSizing
- list-style       ----------> listStyle

문자열로 스타일 속성에 접근할 때에는 아래 두 가지 방법 모두 사용 가능하다.

```javascript 1.8
document.body.style['backgroundColor'] = 'red';
document.body.style['background-color'] = 'red';
```
