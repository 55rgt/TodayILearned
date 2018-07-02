## 상속

**상속**: 기존의 생성자 함수나 객체를 기반으로 새로운 생성자 함수나 객체를 쉽게 만드는 것

```javascript 1.8
/** 생성자 함수 Square 선언 */
function Square(length) {
    
    var width = length;
    var height = length; 
    
    this.getWidth = function(){ return width; }
    this.getHeight = function(){ return height; }
    this.setWidth = function(w){ width = w; }
    this.setHeight = function(h){ height = h; }
}
    
Square.prototype.getArea = function() {
    
    return this.getWidth() * this.getHeight();
};
```

```javascript 1.8
/** 상속 */
function Rect(w, h) {
    
    var width = w;
    var height = h;
    
    this.getWidth = function() { return width; };
    
    this.getHeight = function() { return height; };
    
    this.setWidth = function(w){ width = w; };
    
    this.setHeight = function(h) { height = h; }
}
    
Rect.prototype.getArea = function() {
    return this.getHeight() * this.getWidth();
}

function Square(length){
    this.base = Rect;
    this.base(length, length);
}
    
Square.prototype = Rect.prototype;

/** 프로토타입의 생성자 함수 재정의
*   아래를 빼고 square 객체의 constructor() 실행하면 다르다.
* */
Square.prototype.constructor = Square;
    
var rect = new Rect(5, 7);
var square = new Square(5);

alert(rect.getArea() + " : " + square.getArea());
console.log(square.constructor);

```

```javascript 1.8
/** 상속 확인 */
var square = new Square(5);
alert(square instanceof Rect);
```