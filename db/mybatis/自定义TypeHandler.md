

1.现在有个类，有一个自定义类型`I18nStringProperty`
```java
public class ParamConfigEntity {
    private String key;
    private I18nStringProperty display;
}
```

2、定义转换器
```java
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class I18nStringTypeHandler extends BaseTypeHandler<I18nStringProperty> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, I18nStringProperty parameter, JdbcType jdbcType) throws SQLException {
        //no need
    }

    @Override
    public I18nStringProperty getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String i18nStr = rs.getString(columnName);
        return convertToI18n(i18nStr);
    }

    @Override
    public I18nStringProperty getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String i18nStr = rs.getString(columnIndex);
        return convertToI18n(i18nStr);
    }

    @Override
    public I18nStringProperty getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String i18nStr = cs.getString(columnIndex);
        return convertToI18n(i18nStr);
    }

    private I18nStringProperty convertToI18n(String i18nStr) {
        if (ValidateUtil.isEmptyString(i18nStr)) {
            return null;
        }
        return JsonUtil.toBean(i18nStr, I18nStringProperty.class);
    }
}
```

> 另参考针对pg的jsonb类型的处理：https://www.cnblogs.com/liangyy/p/13573043.html 
```java
public class JsonTypeHandler extends BaseTypeHandler<Object> {

    private static final PGobject jsonObject = new PGobject();

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Object o, JdbcType jdbcType) throws SQLException {
        jsonObject.setType("jsonb");
        jsonObject.setValue(JSON.toJSONString(o));
        preparedStatement.setObject(i, jsonObject);
    }

    @Override
    public Object getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return resultSet.getString(s);
    }

    @Override
    public Object getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getString(i);
    }

    @Override
    public Object getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return callableStatement.getString(i);
    }
}
```

3、注册转换器
```java
@Configuration
public class MybatisTypeHandlerConfiguration {

    @Bean
    ConfigurationCustomizer typeHandlerRegistry() {
        return configuration -> {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            typeHandlerRegistry.register(I18nStringTypeHandler.class);
        };
    }
}
```

