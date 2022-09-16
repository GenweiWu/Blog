
### 使用场景
```
commit1
commit2
然后重新修改commit1的提交
```

### 方法
```
$ git rebase -i HEAD~3
Stopped at f7f3f6d... changed my name a bit
You can amend the commit now, with

       git commit --amend

Once you're satisfied with your changes, run

       git rebase --continue
```

### 详细说明 
> https://git-scm.com/book/zh/v2/Git-%E5%B7%A5%E5%85%B7-%E9%87%8D%E5%86%99%E5%8E%86%E5%8F%B2
```bash
# 1.
git rebase -i head~2

# 2.针对要修改的commit编辑成edit

# 3.修改内容修改，修改好后
 git commit --amend
## 如果出现冲突，就修改后 
 git commit --amend
 
# 4.修改好后
git rebase --continue

```
