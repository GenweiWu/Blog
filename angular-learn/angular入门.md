angular入门
==
## 1、环境搭建

#### 1.1 安装nodejs,npm

#### 1.2 安装angularCli



## 2、问题

### 问题1：安装node-sass失败如何解决

### 先根据报错信息里的链接下载对应的node文件，再通过下面的方式来安装(注意：使用cmd，不要使用gitbash)

#### (1)下面的信息里有下载的具体链接
```
E:\software\npm>npm install node-sass
npm http fetch GET 200 http://100.101.204.126:8081/repository/npm-group/node-sas
s 307ms

> node-sass@4.7.2 install E:\software\npm\node_modules\node-sass
> node scripts/install.js

Downloading binary from https://github.com/sass/node-sass/releases/download/v4.7
.2/win32-x64-57_binding.node..                          
```
#### (2)指定路径手动安装
```
- 
npm i node-sass --sass_binary_path=E:\software\npm\win32-x64-57_binding.node
```

### 参考:https://github.com/sass/node-sass/issues/1106
