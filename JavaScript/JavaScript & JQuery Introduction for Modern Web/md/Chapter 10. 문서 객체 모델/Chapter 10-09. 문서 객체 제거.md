## 문서 객체 제거

문서 객체를 제거할 때에는 아래 메서드를 사용할 수 있다.

- **removeChild(child)**: 문서 객체의 자식 노드를 제거한다.

```javascript 1.8
window.onload = function () {
    
    var willRemove = document.getElementById('will-remove');
   
    document.body.removeChild(willRemove);
};
```

일반적으로 문서 객체를 제거할 때는 아래 코드를 사용한다.

    willRemove.parentNode.removeChild(willRemove);
