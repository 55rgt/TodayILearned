## Math 객체

Math 객체는 자바스크립트의 기본 내장 객체 중 유일하게 생성자 함수를 사용하지 않는 객체이다.

```javascript 1.8
/** Math 객체의 속성 */
alert(Math.E);
alert(Math.LN2);
alert(Math.LN10);
alert(Math.LOG2E);
alert(Math.LOG10E);
alert(Math.PI);
alert(Math.SQRT1_2);
alert(Math.SQRT2);
```

Math 객체의 메서드로 넘겨진 모든 매개변수는 종류에 관계 없이 모두 숫자로 변환된다.

- **abs(x)**: x의 절대값 리턴
- **acos(x)**: x의 아크 코사인 값 리턴
- **asin(x)**: x의 아크 사인 값 리턴
- **atan(x)**: x의 아크 탄젠트 값 리턴
- **atan2(x,y)**: x와 y의 비율로 아크 탄젠트 값을 구해 리턴
- **ceil(x)**: x보다 크거나 같은 가장 작은 정수 리턴
- **cos(x)**: x의 코사인 값 티런
- **exp(x)**: 자연 로그의 x제곱 리턴
- **floor(x)**: x보다 작거나 같은 가장 큰 정수 리턴
- **log(x)**: x의 로그 값 리턴
- **max(x,y,z,...,n)**: 매개변수 중 가장 큰 값 리턴
- **min(x,y,z,...,n)**: 매개변수 중 가장 작은 값 리턴
- **pow(x,y)**: x의 y제곱 리턴
- **random()**: 0부터 1까지의 임의의 수 리턴
- **round(x)**: x를 반올림하여 리턴
- **sin(x)**: x의 사인 값 리턴
- **sqrt(x)**: x의 제곱근 리턴
- **tan(x)**: x의 탄젠트 값 리턴
