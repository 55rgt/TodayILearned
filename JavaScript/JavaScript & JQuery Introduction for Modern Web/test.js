
var child = window.open('', '', 'width=300, height=200');
var width = screen.width;
var height = screen.height;

child.moveTo(0, 0);
child.resizeTo(width, height);

setInterval(function () {
    child.resizeBy(-20, -20);
    child.moveBy(10, 10);
}, 1000);