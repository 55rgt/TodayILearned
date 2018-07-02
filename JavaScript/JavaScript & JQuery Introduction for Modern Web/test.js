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
};

function Square(length){
    this.base = Rect;
    this.base(length, length);
}

Square.prototype = Rect.prototype;
Square.prototype.constructor = Square;

var rect = new Rect(5, 7);
var square = new Square(5);

console.log(square.constructor);

alert(square instanceof Rect);