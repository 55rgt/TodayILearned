var arr = ['bar', 'bas'];
var obj2 = { 0: 'tom', name : 'foo', length : 0};
var obj = { name : 'foo'};

console.dir(obj);
arr.push('baz');
console.log(arr);
Array.prototype.push.apply(obj2, ['baz', 'ab']);
Array.prototype.push.apply(obj2, ['baz', 'ab']);

delete obj2.length;

Array.prototype.push.apply(obj2, ['a', 'b']);


console.log(obj2);

