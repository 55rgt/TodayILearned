String.prototype.deEntity = function() {

    var entity = {
        quot: '"',
        lt: '<',
        gt: '>'
    };

    return function() {
        return this.replace(/&([^&;]+);/g, function(a,b) {
            let r = entity[b];
            return typeof r === 'string' ? r : a;
        });
    };

}();

console.log('&lt;&quot;&gt;'.deEntity());
