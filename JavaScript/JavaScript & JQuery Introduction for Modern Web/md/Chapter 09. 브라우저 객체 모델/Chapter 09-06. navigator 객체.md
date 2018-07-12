## navigator 객체

navigator 객체는 웹 페이지를 실행하고 있는 브라우저에 대한 정보가 있다.

- **appCodeName**: 브라우저의 코드명
- **appName**: 브라우저의 이름
- **appVersion**: 브라우저의 버전
- **platform**: 사용 중인 운영체제의 시스템 환경
- **userAgent**: 브라우저의 전체적인 정보

```javascript 1.8
var output = '';
for (var key in navigator) output += '●' + key + ': ' + navigator[key] + '\n';      
alert(output);
```