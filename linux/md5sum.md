

## 生成md5
```bash
# md5sum 1.txt
c2667e6de21002644d1f17e5d62b5518  1.txt

# md5sum 1.txt > 1.txt.md5
# cat 1.txt.md5
c2667e6de21002644d1f17e5d62b5518  1.txt 
```


## 校验
```
# md5sum -c 1.txt.md5
1.txt: OK
```
