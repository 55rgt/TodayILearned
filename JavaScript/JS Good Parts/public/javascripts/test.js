var stooge = {
    "first-name": "Moore",
    "last-name": "Roger"
};

stooge.nickname = "The Freak";

if(typeof Object.create !== 'function') {
    Object.create = function(o) {

        var F = function() { };
        F.prototype = o;
        return new F();
    };
}

var another_stooge = Object.create(stooge);

/** 프로토타입 연결은 값의 갱신에 영향을 받지 않는다. 즉, 객체를 변경하더라도 객체의 프로토타입에는 영향을 미치지 않는다. */
another_stooge["first-name"] = "Nick";
another_stooge["last-name"] = "Make";
another_stooge.nickname = "The Horror";

/** 객체에서 특정 속성을 삭제했는데 같은 이름의 속성이 프로토타입 체인에 있다면, 그 프로토타입의 속성이 나타난다. */
console.log(another_stooge.nickname);

delete another_stooge.nickname;

console.log(another_stooge.nickname);