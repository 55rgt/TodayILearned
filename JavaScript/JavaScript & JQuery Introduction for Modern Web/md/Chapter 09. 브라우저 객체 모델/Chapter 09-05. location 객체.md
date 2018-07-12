## location 객체

location 객체는 브라우저의 주소 표시줄과 관련된 객체이다.

```javascript 1.8
var output = '';
for (var key in location) output += '●' + key + ': ' + location[key] + '\n';
alert(output);
```

##### [location 객체의 속성]
- **href**: 문서의 URL 주소
- **host**: 호스트 이름과 포트 번호 / localhost:30763 
- **hostname**: 호스트 이름 / localhost
- **port**: 포트 번호 / 30763
- **pathname**: 디렉토리 경로 / /Projects/Location.htm
- **hash**: 앵커 이름 / #beta
- **search**: 요청 매개변수 / ?param=10
- **protocol**: 프로토콜 종류 / http:

##### [location 객체의 메서드]
- **assign(link)**: 현재 위치를 이동한다. 뒤로 가기 불가
- **reload()**: 새로고침한다.
- **replace(link)**: 현재 위치를 이동한다. 뒤로 가기 가능