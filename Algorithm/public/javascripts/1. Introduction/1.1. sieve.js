function sieve(n) {

    let arr = [];
    let prime = [];

    for(let i = 2 ; i <= n ; i++) arr[i] = i;
    for(let j = 2 ; j <= Math.floor(n) ; j++){
        if(arr[j] !== undefined) {
            let k = j * j;
            while (k <= n) {
                arr[k] = undefined;
                k += j;
            }
        }
    }

    for(let i = 0 ; i < arr.length ; i++){
        if(arr[i] !== undefined) prime.push(arr[i]);
    }

    console.log(prime);
}

sieve(26);