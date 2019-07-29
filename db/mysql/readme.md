## yum 安装mysql
要只安装mysql(客户端)，你应该执行  
`yum install mysql`  

安装mysql客户端和mysql服务器：  
`yum install mysql mysql-server`  

## 批量插入
```sql
INSERT INTO `t_test` (
`id`,
`type`,
`tool`
)
VALUES
(
  'a111',
  'type1',
  'tool1'
),
(
  'a222',
  'type2',
  'tool2'
)
```  
