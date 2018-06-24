## 객체와 반복문

객체는 단순 for 반복문으로 객체의 속성을 살펴보는 것이 불가능하다.

→ for in 반복문 사용

```javascript 1.8
var product = {
    name: 'Microsoft',
    price: 15000,
    lang: 'Korean',
    subscription: true
};
    
for (var key in product){
    console.log(product[key]);
}
```