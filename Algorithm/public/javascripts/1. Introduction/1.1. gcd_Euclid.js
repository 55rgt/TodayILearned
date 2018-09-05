function Euclid(m, n) {

    let r;

    while(n !== 0){
        r = m % n;
        m = n;
        n = r;
    }
    return m;
}

console.log(Euclid(60, 24));