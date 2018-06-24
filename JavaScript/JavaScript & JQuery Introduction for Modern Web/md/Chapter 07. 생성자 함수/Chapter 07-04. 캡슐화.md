## 캡슐화

**캡슐화**: 잘못 사용될 수 있는 객체의 특정 부분을 사용자가 사용할 수 없게 막는 기술

```javascript 1.8
/** 캡슐화의 예시*/
    
/** 외부에서는 width와 height를 사용할 수 없다. */
function Rect(w, h){
        
    var width = w;
    var height = h;
    
    this.getWidth = function(){ return width; }
    this.getHeight = function(){ return height; }
    this.setWidth = function(w){ width = w; }
    this.setHeight = function(h){ height = h; }
}
```
