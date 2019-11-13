添加ssh key的步骤
==

### 1. 配置git用户名和邮箱
```
git config user.name "dave"
git config user.email "xxx@mail.com"
```
在config后加上 --global 即可全局设置用户名和邮箱。

### 2. 生成ssh key
```
ssh-keygen -t rsa -C "xxx@mail.com"
```
然后根据提示连续回车即可在~/.ssh目录下得到id_rsa和id_rsa.pub两个文件，id_rsa.pub文件里存放的就是我们要使用的key。

### 3. 上传key到github
copy `~/.ssh/id_rsa.pub`的内容添加到服务器上
