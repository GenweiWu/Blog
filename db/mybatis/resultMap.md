

## result: 简单字段名映射
```xml
<resultMap id="getUserByIdMap" type="User">
	<result property="id" column="uid"></result>
</resultMap>
```

## association: 关联一个对象

```java
@Data
public class User {
    //省略用户属性...
	
    //角色信息
    private Role role;
}
```

```xml
<resultMap id="userMap" type="User">
	<id property="id" column="id"></id>
	<result property="username" column="username"></result>
	<result property="password" column="password"></result>
	<result property="address" column="address"></result>
	<result property="email" column="email"></result>
	
	<association property="role" javaType="Role">
		<id property="id" column="role_id"></id>
		<result property="name" column="role_name"></result>
	</association>
</resultMap>
```

## collection: 关联多个对象

```java
@Data
public class User {
    //省略用户属性...
	
    //角色信息
    private List<Role> roles;
}
```


```xml
<resultMap id="userMap" type="User">
	<id property="id" column="id"></id>
	<result property="username" column="username"></result>
	<result property="password" column="password"></result>
	<result property="address" column="address"></result>
	<result property="email" column="email"></result>
	
	<collection property="roles" ofType="Role">
		<id property="id" column="role_id"></id>
		<result property="name" column="role_name"></result>
	</collection>
</resultMap>
```



## 参考
> https://juejin.cn/post/6844903858477481992


