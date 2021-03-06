基于jackson
==

## readStringAsList

```java
[List<T> list = (List<T>)new ObjectMapper().readValue(jsonStr,
                    new TypeReference<List<T>>()
                    {
                    });
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
