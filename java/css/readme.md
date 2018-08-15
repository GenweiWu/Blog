
## java中读取css文件

参考：http://cssparser.sourceforge.net/outputFormatting.html

### example1：读取css文件中的background属性或者background-image属性

### example2：读取css内容，进行修改，并且重新格式化输出到文件
```java
//write to new file
String cssText =
    stylesheet.getCssText(new CSSFormat().setPropertiesInSeparateLines(4).setUseSourceStringValues(true));
FileOutputStream fileOutputStream = new FileOutputStream("e:/new.css");
fileOutputStream.write(cssText.getBytes());
fileInputStream.close();
```        
