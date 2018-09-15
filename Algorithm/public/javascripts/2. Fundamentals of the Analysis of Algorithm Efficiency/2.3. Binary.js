function binary(n) {
    let count = 1;
    while(n > 1){
        count++;
        n = Math.floor(n / 2);
    }
    return count;
}

console.log(binary(15));
