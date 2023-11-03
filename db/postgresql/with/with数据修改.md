
> https://www.postgresql.org/docs/current/queries-with.html#QUERIES-WITH-MODIFYING

## 返回修改信息

#### a)返回的修改-前-的数据
```sql
WITH t AS (
    UPDATE products SET price = price * 1.05
    RETURNING *
)
SELECT * FROM products;
```

#### b)返回的修改-后-的数据
```sql
WITH t AS (
    UPDATE products SET price = price * 1.05
    RETURNING *
)
SELECT * FROM t;
```

## 删除的数据插入其他表
```sql
WITH moved_rows AS (
    DELETE FROM products
    WHERE
        "date" >= '2010-10-01' AND
        "date" < '2010-11-01'
    RETURNING *
)
INSERT INTO products_log
SELECT * FROM moved_rows;
```
