var myObject = {
    value: 0,
    increment: function(inc) {
        this.value += typeof inc === 'number' ? inc : 1;
    },
    getValue: function () {
        return this.value;
    }
};

myObject.increment();
console.log(myObject.value);        // 1
myObject.increment(2);
console.log(myObject.value);        // 3

myObject.double = function() {

    var that = this;
    var helper = function() {
        that.value *= 2;
    };

    helper();
};

myObject.double();
console.log(myObject.getValue());