## 실행 컨텍스트 생성 과정

```javascript 1.8
function execute(param1, param2){
        
    var a = 1, b = 2;
        
    function func(){
        return a + b;
    }
        
    return param1 + param2 + func();
}
    
execute(3, 4);
```

### 1. 활성 객체 생성

##### 활성 객체
- 실행 컨텍스트가 생성된 후, 자바스크립트 엔진이 해당 컨텍스트에서 실행에 필요한 여러 가지 정보를 담기 위해 생성하는 객체.
- 이 객체에 앞으로 매개변수나 사용자가 정의한 변수 및 객체를 저장하고, 새로 만들어진 컨텍스트로 접근 가능하게 된다.
- (엔진 내부에서 접근 가능 O / 사용자가 접근 가능 X) 

### 2. arguments 객체 생성

- arguments 객체를 생성한다.
- 앞서 만들어진 활성 객체는 arguments 프로퍼티로 이 arguments 객체를 참조한다.

    
    arguments → [param1, param2]
        
    function(param1, param2){
        ...
    }

### 3. 스코프 정보 생성

- 현재 컨텍스트의 유효 범위를 나타내는 스코프 정보를 생성한다.
- 이 스코프 정보는 현재 실행 중인 실행 컨텍스트 안에서 연결 리스트와 유사한 형식으로 만들어진다.
- 현재 컨텍스트에서 특정 변수에 접근해야 할 경우, 이 리스트를 활용한다.
- 이 리스트로 현재 컨텍스트의 변수뿐만 아니라 상위 실행 컨텍스트의 변수도 접근이 가능하다.
- 이 리스트를 **스코프 체인**이라고 부른다. ([[scope]] 프로퍼티로 참조된다.)

여기서는 현재 생성된 활성 객체가 스코프 체인의 제일 앞에 추가되며, execute() 함수의 인자나 지역 변수 등에 접근할 수 있다.

    arguments → [param1, param2]
        
    [[scope]] → [List]
    스코프 체인

### 4. 변수 생성

- 현재 실행 컨텍스트 내부에서 사용되는 지역 변수의 생성이 이루어진다.
- 변수 객체(활성 객체) 안에서 호출된 함수 인자는 각각의 프로퍼티가 만들어진 다음, 값이 할당된다.
- 값이 넘겨지지 않았다면 undefined가 할당된다.

예제에서는 execute() 함수 안에 정의된 변수 a, b와 함수 func가 생성된다.

**이 때 변수나 내부 함수를 단지 메모리에 생성하고, 초기화는 각 변수나 함수에 해당하는 표현식이 실행되기 전까지는 이루어지지 않는다.**

→ 표현식의 실행은 변수 객체 생성이 다 이루어진 후 시작된다.

    arguments   →   [param1, param2]
        
    [[scope]]   →   [List]
    스코프 체인
        
    param1: value   param2: value
    a: undefined    b: undefined
        
    func        →   Function object
                    
                    function(param1, param2){
                        var a = 1, b = 2;
                        function func(){}
                    }

### 5. this 바인딩

- this 키워드를 사용하는 값이 할당된다.
- this가 참조하는 객체가 없으면 전역 객체를 참조한다.


    arguments   →   [param1, param2]
        
    [[scope]]   →   [List]
    스코프 체인
        
    param1: value   param2: value
    a: undefined    b: undefined
        
    func        →   Function object
                    
    this        →   Object
    
### 6. 코드 실행

- 하나의 실행 컨텍스트가 생성되고, 변수 객체가 만들어진 후에 코드에 있는 여러 표현식 실행이 이루어진다.
- 변수의 초기화 및 연산, 또 다른 함수 실행 등이 이루어진다.
- 전역 실행 컨텍스트는 일반적인 실행 컨텍스트와 달리, arguments 객체가 없고, 전역 객체 하나만을 포함하는 스코프 체인이 있다.