var object = { name: 'Tom' };

Object.defineProperty(object, 'region', {
    value: 'Seoul'
});

alert(Object.keys(object));
alert(Object.getOwnPropertyNames(object));