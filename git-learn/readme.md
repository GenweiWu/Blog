### git 忽略本地修改
 `git update-index --assume-unchanged angular/src/index.html`      #忽略跟踪  
 `git update-index --no-assume-unchanged angular/src/index.html`  #恢复跟踪  
 
 
### git clone指定分支
  `git clone -b <branch> <remote_repo>`

### 本地看不到刚刚建好的分支
```
//先进行本地同步
git fetch origin -v
//再次查看
git branch -r
git branch -a
```
