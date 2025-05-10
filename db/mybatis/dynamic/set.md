
## 1. 避免if都不匹配的情况，使用id=#{id}规避   
> https://www.cnblogs.com/zwwhnly/p/11157005.html

```
- 为了避免所有的条件都不满足，生成的Sql语句没有set标签，因此在最后加上了id = #{id},这样必然存在的赋值。  
- set 1=1 不行，至少pg会报错
```

```sql
<update id="updateByIdSelectiveSet">
    UPDATE sys_user
    <set>
        <if test="userName != null and userName != ''">
            user_name = #{userName},
        </if>
        <if test="userPassword != null and userPassword != ''">
            user_password = #{userPassword},
        </if>
        <if test="userEmail != null and userEmail != ''">
            user_email = #{userEmail},
        </if>
        <if test="userInfo != null and userInfo != ''">
            user_info = #{userInfo},
        </if>
        <if test="headImg != null">
            head_img = #{headImg,jdbcType=BLOB},
        </if>
        <if test="createTime != null">
            create_time = #{createTime,jdbcType=TIMESTAMP},
        </if>
        id = #{id},
    </set>
    WHERE id = #{id}
</update>
```
