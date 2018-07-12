# 브라우저 객체 모델

**브라우저 객체 모델(BOM, Browser Object Model)**: 웹 브라우저와 관련된 객체의 집합

##### [브라우저 관련 객체]
- window 객체
    - location 객체: 주소와 관련된 객체
    - navigator 객체: 웹 브라우저와 관련된 객체
    - history 객체: 기록과 관련된 객체
    - screen 객체: 화면 전체와 관련된 객체
    - document 객체: HTML 문서와 관련된 객체
    
브라우저 객체 모델은 간단히 **문서 객체 모델(DOM, Document Object Model)** 이라고 부르기도 하지만 document 객체만을 DOM으로 부르기도 한다.

## window 객체

```javascript 1.8
/** window 객체의 속성 */
var output = '';
for (var key in window) output += '●' + key + ': ' + window[key] + '\n';
alert(output);
```