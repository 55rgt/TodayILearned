## String 객체

### 1. 생성

```javascript 1.8
var literalString = 'Hello World..!';
var constructorString = new String('Hello World..!');
    
var output = '';
output += typeof (literalString) + '\n';
output += typeof (constructorString) + '\n';
alert(output);
```
### 2. 기본 속성과 메서드

#### String 객체의 속성

-**length**: 문자열의 길이를 나타낸다.

#### String 객체의 메서드

- **charAt(position)**: position에 위치하는 문자를 리턴한다.
- **charCodeAt(position)**: position에 위치하는 문자의 유니코드 번호를 리턴한다.
- **concat(args)**: 매개변수로 입력한 문자열을 이어서 리턴한다.
- **indexOf(searchString, position)**: 앞에서부터 일치하는 문자열의 위치를 리턴한다.
- **lastIndexOf(searchString, position)**: 뒤에서부터 일치하는 문자열의 위치를 리턴한다.
- **match(regExp)**: 문자열 내에 regExp가 있는지 확인한다.
- **replace(regExp, replacement)**: regExp를 replacement로 바꾼 뒤 리턴한다.
- **search(regExp)**: regExp와 일치하는 문자열의 위치를 리턴한다.
- **slice(start, end)**: 특정 위치의 문자열을 추출해 리턴한다.
- **split(separator, limit)**: separator로 문자열을 잘라서 배열을 리턴한다.
- **substr(start, count)**: start부터 count만큼 문자열을 잘라서 리턴한다.
- **substring(start, end)**: start부터 end까지 문자열을 잘라서 리턴한다.
- **toLowerCase()**: 문자열을 소문자로 바꾸어 리턴한다.
- **toUpperCase()**: 문자열을 대문자로 바꾸어 리턴한다.

```javascript 1.8
var string = 'abcdefg';
    
// 아래 코드로 하면 변경되지 않는다.
// string.toUpperCase();
    
string = string.toUpperCase();
alert(string);
```
### HTML 관련 메서드

#### String 객체의 HTML 관련 메서드

- **anchor()**: a 태그로 문자열을 감싸 리턴한다. 
- **big()**: big 태그로 문자열을 감싸 리턴한다. 
- **blink()**: blink 태그로 문자열을 감싸 리턴한다. 
- **bold()**: b 태그로 문자열을 감싸 리턴한다. 
- **fixed()**: tt 태그로 문자열을 감싸 리턴한다. 
- **fontcolor(colorString)**: font 태그로 문자열을 감싸고 color 속성을 주어 리턴한다. 
- **fontsize(fontSize)**: font 태그로 문자열을 감싸고 size 속성을 주어 리턴한다. 
- **italics()**: l 태그로 문자열을 감싸 리턴한다. 
- **link(linkRef)**: a 태그에 href 속성을 지정해 리턴한다. 
- **small()**: small 태그로 문자열을 감싸 리턴한다. 
- **strike()**: strike 태그로 문자열을 감싸 리턴한다. 
- **sub()**: sub 태그로 문자열을 감싸 리턴한다. 
- **sup()**: sup 태그로 문자열을 감싸 리턴한다. 

```javascript 1.8
/** String 객체의 HTML 관련 메서드 예시 */
    
var string = 'JavaScript';
var output = '';
output += 'anchor: ' + string.anchor() + '<br/>';
output += 'big: ' + string.big() + '<br/>';
output += 'blink: ' + string.blink() + '<br/>';
output += 'bold: ' + string.bold() + '<br/>';
output += 'fixed: ' + string.fixed() + '<br/>';
output += 'string: ' + string.fontcolor('red') + '<br/>';
output += 'fontsize: ' + string.fontsize(15) + '<br/>';
output += 'italics: ' + string.italics() + '<br/>';
output += 'link: ' + string.link('http://www.naver.com') + '<br/>';
output += 'small: ' + string.small() + '<br/>';
output += 'strike: ' + string.strike() + '<br/>';
output += 'sub: ' + string.sub() + '<br/>';
output += 'sup: ' + string.sup() + '<br/>';
    
// 출력합니다.
document.write(output);
```

#### 메서드 체이닝

**메서드 체이닝**: 메서드를 줄줄이 사용한다.

```javascript 1.8
/** 메서드 체이닝 적용 예시 */
var output = 'Hello World..!';
    
// 메서드 체이닝
output = output.toLowerCase().substring(0, 10).anchor('name');
    
alert(output);
```










