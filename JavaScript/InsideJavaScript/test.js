function countSeconds(howMany) {
    for (var i = 1; i <= howMany; i++) {
        (function (currentI) {
            setTimeout(function () {
                console.log(currentI);
            }, currentI * 1000);
        }(i));
    }
};
countSeconds(3);