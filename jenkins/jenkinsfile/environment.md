

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
