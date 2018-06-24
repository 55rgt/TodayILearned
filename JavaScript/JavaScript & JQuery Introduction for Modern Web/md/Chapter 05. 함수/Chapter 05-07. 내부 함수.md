## 내부 함수

```javascript 1.8
/** 함수 이름 충돌 */

function square(x){
    return x * x;
}
    
function pythagoras(width, height){
    return Math.sqrt(square(width) + square(height))
}
    
alert(pythagoras(3, 4));
    
function square(width, height, hypotenuse){
    return width * width + height * height === hypotenuse * hypotenuse;
}
```

위 코드에서는 함수 이름 중복(square)으로 인해 원하는 결과가 나오지 않을 수 있다.