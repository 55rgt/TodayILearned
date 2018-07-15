## 문서 객체 만들기(3)

문서 객체의 innerHTML 속성을 사용하여 문서 객체를 만들 수 있다.

     <h1>            InnerHTML!          </h1>
    여는 태그         innerHTML 속성        닫는 태그
    
```javascript 1.8
/** innerHTML 속성을 사용한 문서 객체 생성 및 추가 */
window.onload = function () {
    
    var output = '';
    output += '<ul>';
    output += '    <li>JavaScript</li>';
    output += '    <li>jQuery</li>';
    output += '    <li>Ajax</li>';
    output += '</ul>';
    
    document.body.innerHTML = output;
    document.body.innerHTML += '<h1>Earth, Wind and Fire</h1>';
};
```

#### textContent 속성

만약, 아래 문자열을 그대로 화면에 출력하고 싶다면, **textContent** 속성을 사용한다.
    
    "<h1>Earth, Wind and Fire</h1>"
    
```javascript 1.8
/** textContent 사용 */
window.onload = function () {
        
    var output = '';
    output += '<ul>';
    output += '    <li>JavaScript</li>';
    output += '    <li>jQuery</li>';
    output += '    <li>Ajax</li>';
    output += '</ul>';
    
    document.body.textContent = output;
    document.body.textContent += '<h1>Document Object Model</h1>';
};
```