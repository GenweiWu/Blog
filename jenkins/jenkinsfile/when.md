
```
pipeline {
    agent any

    stages {
        stage('Deploy') {
            when {
              expression {
                currentBuild.result == null || currentBuild.result == 'SUCCESS' 
              }
            }
            steps {
                sh 'make publish'
            }
        }
    }
}
```

> 利用env判断
```
stage('plan') {
  when {
     environment name: 'ExecuteAction', value: 'plan'
  }
  steps {
     sh 'cd $dir && $tf plan'
  }
}
```
