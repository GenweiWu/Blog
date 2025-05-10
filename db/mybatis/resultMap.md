

## 一、result: 简单字段名映射
```xml
<resultMap id="getUserByIdMap" type="User">
	<result property="id" column="uid"></result>
</resultMap>
```

## 二、association: 关联一个对象

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

#### 支持嵌套resultMap

```java 
@Data
public class Room{
   private String roomName;
    private User user;
}

@Data
public class User {
    //省略用户属性...
	
    //角色信息
    private Role role;
}
```

```xml
<resultMap id="roomMap" type="Room">
   <result property="roomName" column="room_name"/>
   <association property="user" resultMap="userMap"/>
</resultMap>

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

## 三、collection: 关联多个对象

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

### `List<String>`的写法

```sql
select stu_name from ...
```

```
private Integer id;
private List<String> names;
```

```xml
<collection property="names" ofType="java.lang.String">
    <result column="stu_name"/>
</collection>
```



## 参考
> https://juejin.cn/post/6844903858477481992


