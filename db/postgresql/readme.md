
## 基本命令
```bash
\l   //展示所有database
\l+   //显示更多列
\c postgres   //进入database "postgres"
\d   //列出当前数据库的所有表格。
\d [table_name]   //列出某一张表格的结构。
\q + enter   //退出
select * from tale1;  //注意末尾要加分号
```

```
//删除database
drop database gitlabhq_production;
//新建database
create database gitlabhq_production owner=gitlab;
```

> 导入导出，供参考(gitlab版的psql (9.6.3))
```
//登录
/opt/gitlab/embedded/bin/chpst -u gitlab-psql -U gitlab-psql /opt/gitlab/embedded/bin/psql -p 5432 -h /var/opt/gitlab/postgresql gitlabhq_production

//导出
/opt/gitlab/embedded/bin/chpst -u gitlab-psql -U gitlab-psql /opt/gitlab/embedded/bin/pg_dump -p 5432 -h /var/opt/gitlab/postgresql gitlabhq_production > gitlabhq_production.dump

//导入
/opt/gitlab/embedded/bin/chpst -u gitlab-psql -U gitlab-psql /opt/gitlab/embedded/bin/psql -p 5432 -h /var/opt/gitlab/postgresql gitlabhq_production < gitlabhq_production.dump
```

## 参考
- https://www.a2hosting.com/kb/developer-corner/postgresql/import-and-export-a-postgresql-database#Method-1.3A-Use-the-pg-dump-program
- https://blog.csdn.net/ghostliming/article/details/54287769
- https://blog.csdn.net/baidu_33387365/article/details/80883142
