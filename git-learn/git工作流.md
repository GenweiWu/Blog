基于git rebase的git工作流
==

若存在两个分支
主线分支 master,本地分支 mydev  
如果没有：通过`git checkout -b mydev`创建

安装GIT后，在项目下Git Bash，打开命令窗口。
默认在主线分支上。

### 1、开发分支代码提交
1.	`git checkout mydev`
切换分支到 mydev
2.	`git status`查看代码修改情况
3.	`git add file1 file2`   确定要提交的代码
4.	`git commit –m “xxx”` 提交并注释

### 2、更新master分支
5.	`git checkout master` 切会主分支
6.	`git pull`  更新服务器上的最新代码

### 3、进行代码rebase
7. `git checkout mydev`
切换分支到mydev  
8. `git stash` 将不想提交的文件stash。等代码提交后可以git pop 出来还原  
9.	在本地开发分支是上进行 `git rebase master mydev `

### 4、代码提交
10. 执行`git checkout master` 切回主分支
11. 无冲突后，合并代码`git merge mydev`
12. 执行`git push`将代码推至服务器

### 5、继续在开发分支进行开发
13. `git checkout mydev`
14. `git stash pop`还原
