
## 目录对文件的权限的影响
**只有目录的x权限会影响其中的文件的查看和执行权限**

### 1) 样例1

> 执行文件权限777，根目录权限也是777；但是父目录700，文件无法读取和执行
![image](https://user-images.githubusercontent.com/16630659/71791533-59d7b280-3070-11ea-878c-88357ff3df91.png)

> 所有父目录添加执行权限后，文件可以读取和执行了
![image](https://user-images.githubusercontent.com/16630659/71791587-7f64bc00-3070-11ea-8e35-9c2420573536.png)

> 进一步测试去掉直接父目录的读写权限，可以看出：只有目录的x权限会影响其中的文件的查看和执行权限
![image](https://user-images.githubusercontent.com/16630659/71791649-bfc43a00-3070-11ea-9c79-e21bfd18ce4d.png)

### 2) 样例2

> 目录只有x权限，文件可读取可执行
![image](https://user-images.githubusercontent.com/16630659/71792488-d9b34c00-3073-11ea-890b-1a038615f3e9.png)

> 目录只有r权限，文件名可联想，但是文件不可读取不可执行
![image](https://user-images.githubusercontent.com/16630659/71792590-36166b80-3074-11ea-8174-790d7980822c.png)

> 目录只有w权限，文件不可读取不可执行
![image](https://user-images.githubusercontent.com/16630659/71792643-77a71680-3074-11ea-835f-b5cd0297e2a9.png)
