## 실행 컨텍스트 개념

**콜 스택**: 함수를 호출할 때 해당 함수의 호출 정보가 차곡차곡 쌓여있는 스택.

실행 컨텍스트도 콜 스택에 들어가는 실행 정보 하나와 비슷하다.

##### 실행 컨텍스트 (In ECMAScript)
- **실행 가능한 코드를 형상화하고 구분하는 추상적인 개념**
- **실행 가능한 자바스크립트 코드 블록이 실행되는 환경**

##### <실행 컨텍스트가 형성되는 경우>
- 전역 코드를 실행할 경우
- eval() 함수로 실행되는 코드를 실행할 경우
- 함수 안의 코드를 실행할 경우

##### 실행 컨텍스트의 생성
- **현재 실행되는 컨텍스트에서 이 컨텍스트와 관련 없는 실행 코드가 실행되면, 새로운 컨텍스트가 생성되어 스택에 들어가고 제어권이 그 컨텍스트로 이동한다.**

```javascript 1.8
console.log("This is global context.");
    
function ExContext1(){
    console.log("This is ExContext1");
};
    
function ExContext2(){
    ExContext1();
    console.log("This is ExContext2");
};
    
ExContext2();
    
/*
    
This is global context.
This is ExContext1
This is ExContext2
    
 */
```
- 전역 실행 컨텍스트가 가장 먼저 실행된다.
- 이 과정에서 새로운 함수 호출이 발생하면 새로운 컨텍스트가 만들어지고 실행되며, 종료되면 반환된다.
- 이와 같이 과정이 반복된 후, 전역 실행 컨텍스트의 실행이 완료되면 모든 실행이 끝난다.













