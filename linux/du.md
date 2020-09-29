du用于查看磁盘使用空间
==

- `du`  
  查看当前目录下子目录的大小+当前目录总的大小(但是不会显示目录下的文件)
- `du -a`  
  文件和目录的大小都展示
  
- `du -h`  
  使用M或G等便于识别的大小
- `du -h --max-depth=1`  
  等同于  `du -h -d 1`
  ```
  -d, --max-depth=N     print the total for a directory (or file, with --all)
                          only if it is N or fewer levels below the command
                          line argument;  --max-depth=0 is the same as
                          --summarize
  ``` 
  只展示当前目录的子目录
  
- `du -h --max-depth=1 |sort -h`  
  根据大小排序(`du -h |sort -n`是错误的,因为它无法正确识别1G大于255M,[参见](https://serverfault.com/questions/62411/how-can-i-sort-du-h-output-by-size))

- `du -h dir01` 或  `du -h 1.txt` 或 `du -h dir01 1.txt`  
  显示指定文件夹、文件的大小
- `du -h -d 0 /home/dir1`  
  显示文件夹大小，不显示子文件夹


---
#### 参考网址：
http://www.cnblogs.com/peida/archive/2012/12/10/2810755.html
