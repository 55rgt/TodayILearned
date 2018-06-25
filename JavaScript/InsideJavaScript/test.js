var value = "value1";

function printFunc(func) {
    var value = "value2";

    function printValue() {
        return value;
    }

    console.log(printValue());
}

printFunc();


var value = "value1";

function printValue() {
    return value;
}
function printFunc(func) {
    var value = "value2";
    console.log(func());
}

printFunc(printValue);
