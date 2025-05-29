

```bash
-pl, --projects
    Build specified reactor projects instead of all projects
-am, --also-make
    If project list is specified, also build projects required by the list
-amd, --also-make-dependents
    If project list is specified, also build projects that depend on projects on the list
```

## 举例说明

- ROOT
  - A
  - B
  - C
     - C1
     - C2
   
#### 1.pl用于指定模块
```
mvn clean package -pl C

只会打包C
```

#### 2.am 表示也会打包它依赖的模块(即父亲)
```
mvn clean package -pl C -am

打包C+ROOT
```

#### 3.amd 表示也会打包依赖它的模块(即儿子)
```
mvn clean package -pl C -amd

打包ROOT+C+C1+C2
```

