function maxElement(arr) {

    let maxValue = arr[0];
    for (let i = 1; i < arr.length; i++) {
        if(arr[i] > maxValue) maxValue = arr[i];
    }
    return maxValue;
}
