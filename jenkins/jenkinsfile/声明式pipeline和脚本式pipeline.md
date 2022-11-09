推荐使用声明式pipeline
==



### 1. 报错提前

> 声明式：Build跑之前就会发现

```groovy
pipeline { 
    agent any 
 
    stages { 
        stage("Build") { 
            steps { 
                echo "Some code compilation here..." 
            } 
        } 
 
        stage("Test") { 
            steps { 
                echo "Some tests execution here..." 
                echo 1 
            } 
        } 
    } 
} 
```

> 脚本式：Build正常跑，Test阶段才会发现错误

```groovy
node { 
    stage("Build") { 
        echo "Some code compilation here..." 
    } 
 
    stage("Test") { 
        echo "Some tests execution here..." 
        echo 1 
    } 
} 
```

### 2.可以指定stage重跑

> 声明式

可以通过`Open Blue Ocean`或`Restart from Stage`重跑某个stage



### 3. 添加options更清晰

> 声明式
```groovy
pipeline { 
    agent any 
    
    options {   //<---
        timestamps() 
    } 
    
    stages { 
        stage("Build") { 
            options {    //<---
                timeout(time: 1, unit: "MINUTES") 
            } 
            steps { 
                echo "Some code compilation here..." 
            } 
        } 
 
        stage("Test") { 
            options {  //<---
                timeout(time: 2, unit: "MINUTES") 
            } 
            steps { 
                echo "Some tests execution here..." 
                echo '1 '
            } 
        } 
    } 
} 
```

> 脚本式

```groovy
node { 
    
     timestamps {  //<---
    
        stage("Build") {   //<---
            timeout(time: 1, unit: "MINUTES") { 
                echo "Some code compilation here..." 
            }
        } 

        stage("Test") {   //<---
            timeout(time: 2, unit: "MINUTES") { 
                echo "Some tests execution here..." 
                echo  '1'
            }
        } 
    
     }
} 
```





### 4.条件判断时，声明式可以明显看出绕过

>  声明式

```groovy
pipeline {
    agent any

    options {
        timestamps()
    }

    stages {
        stage("Build") {
            steps {
                echo "Some code compilation here..."
            }
        }

        stage("Test") {
            when {
                environment name: "FOO", "value": "bar"
            }
            steps {
                echo "Some tests execution here..."
                echo '1 '
            }
        }
    }
}
```

> 脚本式

```groovy
node {

    timestamps {

        stage("Build") {
            echo "Some code compilation here..."

        }

        if (env.FOO == 'bar') {
            stage("Test") {
                echo "Some tests execution here..."
                echo '1'
            }
        }

    }
} 
```






### 参考

> https://www.51cto.com/article/639661.html
