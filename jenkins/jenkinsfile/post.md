

### 根据执行结果，采取不同措施

```
pipeline {
    agent any
    stages {
        stage('Example') {
            steps {
                echo 'Hello World'
                //测试失败用
                sh('pwd |grep xxx')
            }
        }
    }
    post {
        success {
            echo 'only ru when success!'
        }
        failure {
            echo 'only ru when failed!'
        }
        always {
            echo 'I will always say Hello again!'
        }
        cleanup {
            echo 'final clean up'
        }
    }
}
```

