## 문서 객체 만들기(2)

텍스트 노드를 갖지 않는 노드 생성

```javascript 1.8
/** 문서 객체의 생성 및 속성 지정 */
window.onload = function() {
    
    var img = document.createElement('img');
    img.src = 'cloud.jpg';
    img.width = 654;
    img.height = 321;
    
    document.body.appendChild(img);
};
```

위 방법은 웹 표준이 정의하거나 웹 브라우저가 지원하는 태그의 속성에만 사용할 수 있다는 단점이 있다.

아래 메서드를 이용하여 해결할 수 있다.

##### [문서 객체의 속성 메서드]

- **setAttribute(name, value)**: 객체의 속성을 지정한다.
- **getAttribute(name)**: 객체의 속성을 가져온다.

```javascript 1.8

window.onload = function () {
    
    var img = document.createElement('img');
    img.setAttribute('src', 'Penguins.jpg');
    img.setAttribute('width', 500);
    img.setAttribute('height', 350);
    
    // 아래 속성은 setAttribute를 이용해야 추가할 수 있다.
    img.setAttribute('data-property', 350);
    
    document.body.appendChild(img);
    
}
```