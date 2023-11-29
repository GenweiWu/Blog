> jackson

## @JsonProperty指定顺序只适用序列化，不适用反序列化  

1.`JsonProperty("id","name")`  
只能用于序列化，即将对象转换为json字符串返回给前台时

2.至于反序列化，是前台提交的json串中key的顺序决定的

> 这里后台会先调用setId再调用setName
```json
{
  "id":"",
  "name":""
}
```

> 这里后台会先调用setName再调用setId
```json
{
  "name":"",
  "id":""
}
```
