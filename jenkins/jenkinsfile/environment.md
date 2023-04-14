

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

```
pipeline {
    agent any 
    environment {
        // Using returnStdout
        CC = """${sh(
                returnStdout: true,
                script: 'echo "clang"'
            )}""" 
        // Using returnStatus 否则用returnStdout会失败
        EXIT_STATUS = """${sh(
                returnStatus: true,
                script: 'exit 1'
            )}"""
    }
    stages {
        stage('Example') {
            environment {
                DEBUG_FLAGS = '-g'
            }
            steps {
                sh 'printenv'
            }
        }
    }
}
```

> 判断文件夹是否存在
```
pipeline {
    options { timestamps() }

    stages {
        stage('debug') {
            environment {  //<--动态设置环境变量
                bbb = """${sh(
                        returnStatus: true,
                        script:'''  //<--复杂脚本
                                if [ -d /home/xxx ]; then
                                    exit 0
                                else
                                    exit 1
                                fi    
                                '''
                )}"""
            }
            steps {
                echo "--> ${env.bbb}"
            }
        }
    }
}

```
