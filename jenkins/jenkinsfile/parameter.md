
### 定义参数
```
pipeline {
    agent any
    parameters {
        string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')

        text(name: 'BIOGRAPHY', defaultValue: '', description: 'Enter some information about the person')

        booleanParam(name: 'TOGGLE', defaultValue: true, description: 'Toggle this value')

        choice(name: 'CHOICE', choices: ['One', 'Two', 'Three'], description: 'Pick something')

        password(name: 'PASSWORD', defaultValue: 'SECRET', description: 'Enter a password')
    }
    stages {
        stage('Example') {
            steps {
                echo "Hello ${params.PERSON}"

                echo "Biography: ${params.BIOGRAPHY}"

                echo "Toggle: ${params.TOGGLE}"

                echo "Choice: ${params.CHOICE}"

                echo "Password: ${params.PASSWORD}"
            }
        }
    }
}
```

### 动态设置变量
```
pipeline {
    agent any

    options {
        timestamps()
    }

    stages {
        stage("Build") {
            steps {
                script {
                    def now = """${sh(
                            returnStdout: true,
                            script: 'date'
                    )}"""
                    echo "${now}"
                }
            }
        }
    }
}
```


### 参数映射

```
pipeline {
    agent any

    parameters {
        choice(name: 'deployEnv', choices: ['环境1', '环境2'], description: '选择环境进行部署')
    }

    options {
        timestamps()
    }

    stages {
        stage("Build") {
            steps {
                script {
                    def map = [:]
                    map['环境1'] = [name: '环境名称11', timeout: 1]
                    map['环境2'] = [name: '环境名称22', timeout: 2]

                    def deployEnv = map[params.deployEnv]

                    echo "name ==> ${deployEnv['name']}"
                    echo "timeout ==> ${deployEnv['timeout']}"
                }
            }
        }
    }
}
```



### 防止参数注入

> 使用单引号，避免使用双引号

```
pipeline {
  agent any
  parameters {
    string(name: 'STATEMENT', defaultValue: 'hello; ls /', description: 'What should I say?')   
  }
  stages {
    stage('Example') {
      steps {
        /* WRONG! */
        sh("echo ${STATEMENT}")
        // 正确方式，不会注入
        sh('echo ${STATEMENT}')
      }
    }
  }
}
```



