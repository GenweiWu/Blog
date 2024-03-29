基于jackson
==

## readStringAsList

```java
    public static <T> List<T> readStringAsList(String jsonStr, Class<T> valueType) {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        try {
            return objectMapper.readValue(jsonStr, typeFactory.constructCollectionType(List.class, valueType));
        } catch (Exception e) {
            //log.error("readStringAsList failed with str:{}", jsonStr, e);
            return Collections.emptyList();
        }
    }
```

## readValueAsList

```java
public static <T> List<T> readValueAsList(Object srcData, Class<T> valueType)
        throws IOException
    {
        JsonNode jsonData = objectMapper.valueToTree(srcData);

        if (null == jsonData)
        {
            return null;
        }
        else
        {
            TypeFactory typeFactory = getMapper().getTypeFactory();
            CollectionType collectionType = typeFactory.constructCollectionType(List.class, valueType);
            return objectMapper.readValue(jsonData.traverse(), collectionType);
        }
    }
```

```java
ObjectMapper objectMapper = JsonUtil.getMapper();
MenuConfig[] menuConfigs = objectMapper.readValue(inputStream, MenuConfig[].class);
return Arrays.asList(menuConfigs);
```

## readValueAsClass
```java
public static <T> T readValueAsType(Object srcData, Class<T> valueType)
        throws IOException
    {
        ObjectNode jsonData = objectMapper.valueToTree(srcData);

        if (null == jsonData)
        {
            return null;
        }
        else
        {
            return objectMapper.readValue(jsonData.traverse(), valueType);
        }
    }
```    

## readValueWithType
> https://stackoverflow.com/a/6852184    
```java
public class MyWrapper<T> {

    private MyRequest<T> request;

    public MyRequest<T> getRequest() {
        return request;
    }

    public void setRequest(MyRequest<T> request) {
        this.request = request;
    }
}
```

- 读取方法 
```java
 JavaType topMost = mapper.getTypeFactory().constructParametricType(MyWrapper.class, ActualClassRuntime.class);
 mapper.readValue(new File("input.json"), type);
```

```java
    private static <T> MyWrapper<T> jsonToBean(String jsonStr, Class<T> clazz) {
        ObjectMapper mapper = JsonUtil.mapper;
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(MyWrapper.class, clazz);
            MyWrapper<T> xxx = mapper.readValue(jsonStr, javaType);
            return xxx;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
```
