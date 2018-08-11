function scopeExam(){
    scope = 20;
    console.log("scope = " +scope);
}

function scopeExam2(){
    console.log("scope = " + scope);
}
scopeExam();
scopeExam2();
//실행결과
/*
scope=20
scope=20
*/