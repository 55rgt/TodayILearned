## window 객체의 onload 이벤트 속성

```javascript 1.8
/** onload 이벤트 속성 */
window.onload = function(){};
```
'on' 으로 시작하는 속성은 **이벤트 속성**이라고 부르고, 함수를 할당해야 한다.

**window 객체 로드가 완료되는 때**: HTML 페이지에 존재하는 모든 태그가 화면에 올라가는 순간

```html
<!--HTML 페이지 생성 순서(1)-->
<!DOCTYPE html>
<html>
<head>
    <title>Document Object Model</title>
    <script>alert('Process - 0')</script>
</head>
<body>
    <h1>Process - 1</h1>
    <script>alert('Process - 2')</script>
    <h2>Process - 3</h2>
    <script>alert('Process - 4')</script>
</body>
</html>
``` 
- Process 순서대로 출력된다.

```html
<!--HTML 페이지 생성 순서(2)-->
<!DOCTYPE html>
<html>
<head>
    <script>
        window.onload = function () {
            alert('Procsss - 0');
        };
    </script>
</head>
<body>
    <h1>Process - 1</h1>
    <h1>Process - 2</h1>
</body>
</html>
```
- 1 -> 2-> 0 순서대로 출력된다.