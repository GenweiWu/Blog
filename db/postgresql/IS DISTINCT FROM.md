IS DISTINCT FROM 与 != 的核心区别
==

```
!=  碰到左边或右边是NULL，则会有问题
IS DISTINCT FROM 则能正确处理NULL的情况
```

### 1. 对 NULL 的处理（最关键区别）
```sql
-- 使用 != (或 <>)
NULL != 1     → NULL (视为 false)
NULL != NULL  → NULL (视为 false)
1 != NULL     → NULL (视为 false)

-- 使用 IS DISTINCT FROM
NULL IS DISTINCT FROM 1     → true  (NULL 和 1 不同)
NULL IS DISTINCT FROM NULL  → false (都是 NULL，视为相同)
1 IS DISTINCT FROM NULL     → true  (1 和 NULL 不同)
```

### 2. 实际查询效果对比
```sql
-- 示例数据
CREATE TABLE test (id int, a int, b int);
INSERT INTO test VALUES 
  (1, 10, 20),   -- 不同
  (2, 30, 30),   -- 相同
  (3, NULL, 40), -- a 为 NULL
  (4, 50, NULL), -- b 为 NULL
  (5, NULL, NULL); -- 都为 NULL

-- 使用 != 
SELECT * FROM test WHERE a != b;
-- 结果: 只有 id=1
-- 说明: NULL 相关的行全部被过滤掉

-- 使用 IS DISTINCT FROM
SELECT * FROM test WHERE a IS DISTINCT FROM b;
-- 结果: id=1, id=3, id=4
-- 说明: 包含了任何一方为 NULL 的行（除了都为 NULL 的情况）
```
