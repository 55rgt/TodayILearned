function uniqueElement(arr) {

    for (let i = 0; i < arr.length - 1; i++) {
        for (let j = i + 1; j < arr.length; j++) {
            if(arr[i] === arr[j]) return false;
        }
    }
    return true;
}

function uniqueElement_kv(arr) {

    let store = {};
    for(let i = 0 ; i < arr.length ; i++){
        let key = arr[i];
        if(store.hasOwnProperty(key)) return false;
        store[key] = true;
    }
    return true;
}

console.log(uniqueElement_kv([1,2,3,6,4,78,5,3,65,87]));
