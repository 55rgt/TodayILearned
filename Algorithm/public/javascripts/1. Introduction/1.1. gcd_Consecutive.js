function consecutive(m, n) {

    let t = Math.min(m, n);
    while (--t) {
        if (m % t === 0 && n % t === 0) return t;
    }
}

console.log(consecutive(60, 24));

