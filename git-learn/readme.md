### 分支合并
- https://www.ruanyifeng.com/blog/2012/07/git.html
```
git merge --no-ff dev
```
 
 
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
