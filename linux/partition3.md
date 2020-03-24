
### 主分区，扩展分区，逻辑分区
- 主分区和扩展分区加起来最多只能建立 4 个，而扩展分区最多只能建立 1 个
- 磁盘容量与主分区、扩展分区、逻辑分区的关系：
```
硬盘的容量＝主分区的容量＋扩展分区的容量

扩展分区的容量＝各个逻辑分区的容量之和
```

#### 参考
> 主分区xvde1,xvde2,xvde3  
> 扩展分区 xvde4  
  -- 逻辑分区 xvde5  
  -- 逻辑分区 xvde6  
```
Device Boot      Start         End      Blocks   Id  System
/dev/xvde1            2048    52430847    26214400   83  Linux
/dev/xvde2        52430848    62916607     5242880   83  Linux
/dev/xvde3        62916608   104859647    20971520   83  Linux
/dev/xvde4       104859648   209715199    52427776    5  Extended
/dev/xvde5       104861696   157290495    26214400   83  Linux
```



### 参考
- [主分区，扩展分区，逻辑分区](https://blog.csdn.net/zccst/article/details/4771902)

- [实操](https://blog.csdn.net/holly_Z_P_F/article/details/93782454?depth_1-utm_source=distribute.pc_relevant.none-task&utm_source=distribute.pc_relevant.none-task)
