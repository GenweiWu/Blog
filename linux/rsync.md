
## 如果scp -p无法满足要求，就使用rsync

> https://serverfault.com/a/371423  
> scp不保留属主信息  
```
That is correct. "-p" does not do that. See the man page:

     -p      Preserves modification times, access times, and modes from the
             original file.
Notice it says times and modes, NOT user/group ownership.
```

## rsync
```


```


## 参考
- https://www.ruanyifeng.com/blog/2020/08/rsync.html

