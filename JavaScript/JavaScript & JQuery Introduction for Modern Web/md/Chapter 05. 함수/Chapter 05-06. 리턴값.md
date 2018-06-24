## 리턴값

```javascript 1.8
/** return 키워드 */

function returnFunction(){
    alert('A');
    return;
    alert('B');
}

returnFunction();
```

```javascript 1.8
/** return 키워드 */

function returnFunction(){
    alert('A');
    return;
    alert('B');
}
    
var output = returnFunction();              // A
alert(typeof output + ":" + output);        // undefined: undefined
```