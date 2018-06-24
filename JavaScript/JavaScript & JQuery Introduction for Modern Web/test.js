
var student = {
    name: 'Tom',
    math: 98,   english: 96,
    korean: 92, science: 98
};

var output = '';

with(student){
    output += 'name: ' + name + '\n';
    output += 'math: ' + math + '\n';
    output += 'english: ' + english + '\n';
    output += 'korean: ' + korean + '\n';
    output += 'science: ' + science + '\n';
}

alert(output);