## 콜백 함수

자바스크립트에서는 함수도 하나의 자료형이므로 매개변수로 전달할 수 있다.

**콜백 함수**: 매개변수로 전달하는 함수

```javascript 1.8
function callTenTimes(callback){
    for(var i = 0 ; i < 10 ; i++) callback();
}
    
var callback = function() {
    alert("call function!");
}
    
callTenTimes(callback);
```

콜백 함수는 익명 함수로 사용하는 경우가 많다.

```javascript 1.8
function callTenTimes(callback){
    for(var i = 0 ; i < 10 ; i++) callback();
}
    
callTenTimes(function(){
    alert("call function");
});
```