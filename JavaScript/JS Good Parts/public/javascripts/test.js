var foo = function() {
    var a = 3, b = 5;

    console.log(a,b);       // 3, 5

    var bar = function() {
        var b = 7, c = 11;

        console.log(a,b,c); // 3, 7, 11

        a += b + c;
        console.log(a,b,c); // 21, 7, 11
    };

    bar();
    console.log(a,b);   // 21, 5

};

foo();