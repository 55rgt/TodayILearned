## 문서 객체 만들기(1)

텍스트 노드를 갖는 문서 객체를 생성하려면 요소 노드와 텍스트 노드를 생성하고 텍스트 노드를 요소 노드에 붙여준다.

##### [document 객체의 노드 생성 및 연결 메서드]

- **createElement(tagName)**: 요소 노드를 생성한다.
- **createTextNode(text)**: 텍스트 노드를 생성한다.
- **appendChild(node)**: 객체에 노드를 연결한다.

```javascript 1.8
/** 문서 객체 생성 및 연결 */
    
window.onload = function() {
    
    // 문서 객체 생성
    var header = document.createElement('h1');
    var textNode = document.createTextNode('DOM Roger');
    
    // 노드 연결
    header.appendChild(textNode);
    document.body.appendChild(header); 
  
};

```