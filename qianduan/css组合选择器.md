css组合选择器
--

- 群组选择器：同时针对多个元素设置样式

```css
  div,p{
     border-color:blue;
  }
```

- 后代选择器：当前元素的所有后代元素（包括儿子辈的和孙子辈的）

```css
  div p{
     border-color:blue;
  }
```

- 子选择器：当前元素的直接后代元素(只包括儿子辈)
  - 包括所有的儿子，而不仅仅是第一个

```css
  div>p{
     border-color:blue;
  }
```

 - 同级元素选择器~：该元素所有的兄弟元素
   - 只包括后面的，不包括前面的兄弟
 ```css
  div~p{
     border-color:blue;
  }
```
 
 - 同级元素选择器+：该元素下一个兄弟元素
 ```css
  div+p{
     border-color:blue;
  }
```
