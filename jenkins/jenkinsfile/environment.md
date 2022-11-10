

### 打印env
```
pipeline {
    agent any
    stages {
        stage('debug') {
            steps {
                sh 'printenv'
            }
        }
    }
}
```



### 设置环境变量

```
pipeline {
    agent any
    environment { //<--全局的环境变量
        test111 = 'hello'
    }
    stages {
        stage('debug') {
            environment {  //<--当前stage的环境变量
                test222 = 'world'
            }
            steps {
                sh 'printenv | grep test'  //test111=hello和test222=world
            }
        }
        stage('test') {
            steps {
                sh 'printenv | grep test'  //只有test111=hello
            }
        }
    }
}
```



### 动态设置环境变量

```
pipeline {
    agent any
    stages {
        stage('debug') {
            environment {  //<--动态设置环境变量
                now = """${sh(
                        returnStdout: true,
                        script: 'date'
                )}"""
            }
            steps {
                echo "--> ${env.now}"
            }
        }
    }
}
```

