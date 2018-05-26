基于jackson
==

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
