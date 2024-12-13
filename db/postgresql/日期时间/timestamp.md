


### 样例

```
1. timestamp 查询时，无论当前时区是啥，查出来的值都是一样的(其实代表的时间已经不一样了)
2. timestamptz 查询时，会根据当前的时区查询出不同的效果，但是其实时间是一致的(根据不同时区转换值)
```


> https://stackoverflow.com/a/77723348/6182927 
```sql
create table test(tstz timestamptz, ts timestamp);
insert into test 
select '2012-08-24 14:00:00+03:00'::timestamptz,
       '2012-08-24 14:00:00+03:00'::timestamp
returning *;
```

| tstz                   | ts                  |
| :--------------------- | :------------------ |
| 2012-08-24 11:00:00+00 | 2012-08-24 14:00:00 |

The offset you see by default when you select a `timestamptz` is your current timezone: it basically means *this timestamp, as observed in a timezone with this offset*. Note that unless you add the minutes `:00`, it's trimmed off as insignificant both in default output and in [`to_char()` formatting function](https://www.postgresql.org/docs/current/functions-formatting.html). If you really want to get the output you specified, for a reason only known to you, by all means you can - simply set the setting accordingly:

```sql
set timezone='utc-03:00';
select tstz,ts from test;
```

| tstz                   | ts                  |
| :--------------------- | :------------------ |
| 2012-08-24 14:00:00+03 | 2012-08-24 14:00:00 |

```sql
select to_char(tstz,'YYYY-MM-DD HH-MI-SSAMOF'),
       to_char(ts,'YYYY-MM-DD HH-MI-SSAMOF') from test;
```

| to_char                  | to_char                  |
| :----------------------- | :----------------------- |
| 2012-08-24 02-00-00PM+03 | 2012-08-24 02-00-00PM+00 |

```sql
--Standard time: Australian Central Western Standard Time (ACWST)
--Example city: Eucla
set timezone='UTC +8:45';
select tstz,ts from test;
```

| tstz                      | ts                  |
| :------------------------ | :------------------ |
| 2012-08-24 02:15:00-08:45 | 2012-08-24 14:00:00 |

```sql
select to_char(tstz,'YYYY-MM-DD HH-MI-SSAMOF'),
       to_char(ts,'YYYY-MM-DD HH-MI-SSAMOF') from test;
```

| to_char                     | to_char                  |
| :-------------------------- | :----------------------- |
| 2012-08-24 02-15-00AM-08:45 | 2012-08-24 02-00-00PM+00 |

